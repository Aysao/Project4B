package PackageThreads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import PackageClass.BlocN;
import PackageClass.Ennemi;
import PackageClass.Entity;
import PackageClass.Plateau;
import PackageClass.Player;
import PackageClass.Victory;
import PackageRender.Render;


public class Menu implements Runnable {

	
	private static Menu instance ;
	
	private int scoreTimer=0;
	private ArrayList<Ennemi> lstEnnemi;
	private ArrayList<Player> lstPlayer;
	
	private Player p1;
	private Ennemi p2;
	private int ennemiVie=6;
	private Ennemi e1;
	private Ennemi e2;
	private Ennemi e3;
	private Render r ;	
	private Victory v ;
	private boolean wait;
	private char avancer='Z';		//touche de deplacement une option pour qu'on puisse changer les touches
	private char reculer='S';
	private char droite='D';
	private char gauche='Q';
	private JFrame menuPrincipal;
	private boolean host = true ;
	private Client c;
	private Serveur s;
	private int mode ;
	private boolean gamestart=true;
	private HashMap<Ennemi, ThreadEnnemi> hmThreadE = new HashMap<Ennemi, ThreadEnnemi>();
	private HashMap<Player, ThreadPlayer> hmThreadP = new HashMap<Player, ThreadPlayer>();

	public Menu()
	{
		instance = this;
	}
	public void MenuStart(JFrame f,int i,String pseudo) 
	{
		
		mode=i;
		menuPrincipal = f;
		switch(i)
		{
			case 1://nb de joueur 
			{
				new Plateau();
				p1 = new Player(7,7);
				p1.setPseudo(pseudo);	
				e1 = new Ennemi();				
				e2 = new Ennemi();				
				e3 = new Ennemi();				
				v = new Victory();						
				startSolo();						
			}
		}

				
	}
	
	@Override
	public void run() {	
		if(mode == 2) {
			int vieplayer = 0;
			v = new Victory();
			for(int i = 0;i<lstPlayer.size();i++)
			{
				vieplayer += lstPlayer.get(i).getVie();
			}
			while(!v.isVictory() && vieplayer > 0)
			{
				try {
					Thread.sleep(100);
					Plateau.stopBordure();
					if(host &&mode==2)
					{
						//c.sendLine("bordure");
					}
					scoreTimer++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			while(!v.isVictory() && p1.getVie() > 0)
			{
				try {
					Thread.sleep(1000);
					Plateau.stopBordure();
					scoreTimer++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		fin();
		
	}


	public void startMulti(String Pseudo,Client c,ArrayList<String> len,ArrayList<String> lpl)
	{
		mode = 2;
		this.c = c;
		if(host)
		{
			boolean ennemi = false;
			new Plateau();
			v = new Victory();	
			if(isInArray(len,Pseudo))
			{
				ennemi = true;
			}
			lstEnnemi = new ArrayList<Ennemi>();
			lstPlayer = new ArrayList<Player>();
			if(!ennemi)
			{
				p1 = new Player(7,7);
				p1.setPseudo(Pseudo);
				ThreadPlayer runtp = new ThreadPlayer(p1);
				Thread tp = new Thread(runtp);
				tp.start();
				r = new Render(menuPrincipal,600,800,runtp.getKl());					
				Thread t = new Thread(r);
				t.start();
				hmThreadP.put(p1, runtp);
				lstPlayer.add(p1);
			}
			else
			{
				Ennemi en = new Ennemi(7,7);
				en.setPseudo(Pseudo);
				en.setPlayed(true);
				ThreadEnnemi runtp = new ThreadEnnemi(en);
				Thread tp = new Thread(runtp);
				tp.start();
				r = new Render(menuPrincipal,600,800,runtp.getKl());					
				Thread t = new Thread(r);
				t.start();
				hmThreadE.put(en, runtp);
				lstEnnemi.add(en);
			}
			c.sendLine(Plateau.PlateauToString());
			for(int i = 0;i<len.size();i++)
			{
				System.out.println("ennemi : "+len.get(i));
				if(!len.get(i).equals(Pseudo))
				{
					System.out.println("donc j'arrive la");
					if(!len.get(i).equals("----------"))
					{
						System.out.println("je ne suis pas ----------");
						lstEnnemi.add(new Ennemi());
						lstEnnemi.get(i).setPseudo(len.get(i));
						c.sendLine("("+lstEnnemi.get(i).getPosX()+","+lstEnnemi.get(i).getPosY()+")"+lstEnnemi.get(i).getPseudo());
					}
				}
				else
				{
					
					if(len.get(i).equals(Pseudo))
					{
						c.sendLine("("+lstEnnemi.get(i).getPosX()+","+lstEnnemi.get(i).getPosY()+")"+lstEnnemi.get(i).getPseudo());
					}
				}

			}
			for(int i = 0;i<lpl.size();i++)
			{
				System.out.println("player : "+lpl.get(i));
				if(!lpl.get(i).equals(Pseudo))
				{
					if(!lpl.get(i).equals("----------"))
					{
						lstPlayer.add(new Player());
						lstPlayer.get(i).setPseudo(lpl.get(i));
						c.sendLine("("+lstPlayer.get(i).getPosX()+","+lstPlayer.get(i).getPosY()+")"+lstPlayer.get(i).getPseudo());
					}
				}
				else
				{
					
					if(lpl.get(i).equals(Pseudo))
					{
						c.sendLine("("+lstPlayer.get(i).getPosX()+","+lstPlayer.get(i).getPosY()+")"+lstPlayer.get(i).getPseudo());
					}
				}
			}
			c.sendLine("personne");
		}	
		
	
	}
	
	public void startMultiClient(String Pseudo,Client c,ArrayList<String> len,ArrayList<String> lpl)
	{
		mode = 2;
		this.c = c;
		System.out.println("recherche plateau ... ");
		String plateau = "";
		try {
			plateau = c.getSisr().readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("plateau trouve ... ");
		Plateau.StringToPlateau(plateau);
		System.out.println("plateau crée ... ");
		System.out.println("recuperation des joueur et de leur emplacement ... ");
		String joueur = "";
		lstEnnemi = new ArrayList<Ennemi>();
		lstPlayer = new ArrayList<Player>();
		int i = 0;
		while(!joueur.equals("personne"))
		{
			String x = "";
			String y = "";
			String pse = "";
			int virgule = 0;
			int parenthese = 0;
			joueur = "";
			try {
				joueur = c.getSisr().readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!joueur.equals("personne"))
			{
				if(joueur.charAt(0) == '(')
				{
					for(int j = 1;j < joueur.length();j++)
					{
						if(joueur.charAt(j) == ',')
						{
							virgule = j;
						}
						
					}
					x = joueur.substring(1,virgule);
					for(int j = virgule+1;j < joueur.length();j++)
					{
						if(joueur.charAt(j) == ')')
						{
							parenthese = j;
						}
					}
					y = joueur.substring(virgule+1,parenthese);
				}
				
				if(parenthese != 0)
					pse = joueur.substring(parenthese+1);
				
				if(isInArray(len,pse))
				{
					if(pse.equals(Pseudo))
					{
						Ennemi e = new Ennemi(Integer.parseInt(x),Integer.parseInt(y),pse,true);
						lstEnnemi.add(e);
						ThreadEnnemi runtp = new ThreadEnnemi(e);
	
						Thread tp = new Thread(runtp);
						tp.start();
						hmThreadE.put(e, runtp);
						r = new Render(menuPrincipal,600,800,runtp.getKl());					
						Thread t = new Thread(r);
						t.start();
					}
					else
					{
						Ennemi e = new Ennemi(Integer.parseInt(x),Integer.parseInt(y),pse,false);
						lstEnnemi.add(e);
					}
				}
				else
				{
					if(pse.equals(Pseudo))
					{
						lstPlayer.add(new Player(Integer.parseInt(x),Integer.parseInt(y),pse));
						ThreadPlayer runtp = new ThreadPlayer(researchPlayerInArray(lstPlayer,pse));
						Thread tp = new Thread(runtp);
						tp.start();
						r = new Render(menuPrincipal,600,800,runtp.getKl());					
						Thread t = new Thread(r);
						t.start();
					}
					else
					{
						lstPlayer.add(new Player(Integer.parseInt(x),Integer.parseInt(y),pse));
					}
					
				}
			}
			virgule = 0;
			parenthese = 0;
			System.out.println(joueur);
		}
		
	}
	
	private void startSolo() {
		ThreadPlayer runtp = new ThreadPlayer(p1);
		Thread tp = new Thread(runtp);
		tp.start();	
		ThreadEnnemi runte = new ThreadEnnemi(e1);
		Thread te =new Thread(runte);
		te.start();
		ThreadEnnemi runte1 = new ThreadEnnemi(e2);
		Thread te1 =new Thread(runte1);
		te1.start();
		ThreadEnnemi runte2 = new ThreadEnnemi(e3);
		Thread te2 =new Thread(runte2);
		te2.start();
		r = new Render(menuPrincipal,600,800,runtp.getKl());
		Thread t = new Thread(r);
		t.start();	
		hmThreadP.put(p1, runtp);
		hmThreadE.put(e1, runte);
		hmThreadE.put(e2, runte1);		
		hmThreadE.put(e3, runte2);		
	}
	public void newEnnemiClient(Ennemi en) {
		ennemiVie--;
		if(host)
		{
			List<BlocN> lst = Plateau.getBlocN();
			int i = new Random().nextInt(lst.size());
			if(ennemiVie==0)
			{
				v.setVictory(true);
				return;
			}
			else
			{				
				en.setPosX(lst.get(i).getPosX());
				en.setPosY(lst.get(i).getPosY());
				Plateau.refreshEntity(en);
				c.sendLine("new location");
				System.out.println(Integer.toString(lst.get(i).getPosX()));
				c.sendLine(Integer.toString(lst.get(i).getPosX()));
				System.out.println(Integer.toString(lst.get(i).getPosY()));
				c.sendLine(Integer.toString(lst.get(i).getPosY()));
			}			
		}
		
		
	}
	
	public Player researchPlayerInArray(ArrayList<Player> ls,String name)
	{
		Player p = null;
		for(int i = 0;i<ls.size();i++)
		{
			if(ls.get(i).getPseudo().equals(name))
			{
				p = ls.get(i);
			}
		}
		return p;
	}
	public Ennemi researchEnnemiInArray(ArrayList<Ennemi> ls,String name)
	{
		Ennemi p = null;
		for(int i = 0;i<ls.size();i++)
		{
			if(ls.get(i).getPseudo().equals(name))
			{
				p = ls.get(i);
			}
		}
		return p;
	}
	public void newEnnemi()
	{
		if(mode==1)
		{
			ennemiVie--;
			//System.out.println("vie"+ennemiVie);
			if(ennemiVie==0)
			{
				v.setVictory(true);
				return;
			}
			else if(ennemiVie>=3)
			{
				Ennemi e=new Ennemi();
				ThreadEnnemi runte = new ThreadEnnemi(e);
				Thread te =new Thread(runte);
				te.start();
				hmThreadE.put(e, runte);
			}		
		}				
	}
	
			
		
	

	public void fin() 
	{	
		int x = 1000-scoreTimer*2;
		scoreTimer=0;
		p1.getScr().setPoint(p1.getScr().getPoint()+x);
		try {
			p1.getScr().setScore();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		if(v.isVictory() == false)
		{
			JOptionPane.showMessageDialog(r,"Perdu !");
			if(mode==2 &&host)
			{
				c.sendLine("win");
			}
		}
		if(v.isVictory() == true)
		{
			JOptionPane.showMessageDialog(r,"Gagnï¿½!");
			if(mode==2 &&host)
			{
				c.sendLine("loose");
			}
		}
		clear();
		
	}
	public void clear()
	{
		resetVar();
		
		//supprime thread player et ennemis restant
		Plateau.clearEntity(true);
		r.stop();//stop refresh fenetre
		r.dispose();
		menuPrincipal.setVisible(true);	
		
	}
	private void resetVar() {
		ennemiVie = 6;
		host=true;
		scoreTimer=0;		
		v =new Victory();
		c=null;
		s=null;
		mode =1;
	}
	public void newEntity()
	{
		System.out.println("test");
		Plateau.clearEntity(false);
		for(int i = 0 ;i<3;i++)
		{
			Ennemi e=new Ennemi();
			ThreadEnnemi runte = new ThreadEnnemi(e);
			Thread te =new Thread(runte);
			te.start();
			hmThreadE.put(e, runte);	
		}	
	}

	
	public boolean isInArray(ArrayList<String> l,String pse)
	{
		boolean res = false;
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals(pse))
				res = true;
		}
		return res;
	}
	
	public int nbArray(ArrayList<String> l)
	{
		int res = 0;
		for(int i = 0;i < l.size();i++)
		{
			if(!l.get(i).equals("----------"))
				res++;
		}
		return res;
	}
	
	public static Menu getInstance() {
		return instance;
	}
	public static void setInstance(Menu instance) {
		Menu.instance = instance;
	}
	public Player getP1() {
		return p1;
	}
	public void setP1(Player p1) {
		this.p1 = p1;
	}
	public Ennemi getP2() {
		return p2;
	}
	public void setP2(Ennemi p2) {
		this.p2 = p2;
	}
	public int getEnnemiVie() {
		return ennemiVie;
	}
	public void setEnnemiVie(int ennemiVie) {
		this.ennemiVie = ennemiVie;
	}
	public Ennemi getE1() {
		return e1;
	}
	public void setE1(Ennemi e1) {
		this.e1 = e1;
	}
	public Ennemi getE2() {
		return e2;
	}
	public void setE2(Ennemi e2) {
		this.e2 = e2;
	}
	public Ennemi getE3() {
		return e3;
	}
	public void setE3(Ennemi e3) {
		this.e3 = e3;
	}
	public Render getR() {
		return r;
	}
	public void setR(Render r) {
		this.r = r;
	}
	public Victory getV() {
		return v;
	}
	public void setV(Victory v) {
		this.v = v;
	}
	public char getAvancer() {
		return avancer;
	}
	public void setAvancer(char avancer) {
		this.avancer = avancer;
	}
	public char getReculer() {
		return reculer;
	}
	public void setReculer(char reculer) {
		this.reculer = reculer;
	}
	public char getDroite() {
		return droite;
	}
	public void setDroite(char droite) {
		this.droite = droite;
	}
	public char getGauche() {
		return gauche;
	}
	public void setGauche(char gauche) {
		this.gauche = gauche;
	}
	public JFrame getMenuPrincipal() {
		return menuPrincipal;
	}
	public void setMenuPrincipal(JFrame menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}
	public boolean isHost() {
		return host;
	}
	public void setHost(boolean host) {
		this.host = host;
	}
	public Client getC() {
		return c;
	}
	public void setC(Client c) {
		this.c = c;
	}
	public Serveur getS() {
		return s;
	}
	public void setS(Serveur s) {
		this.s = s;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public boolean isGamestart() {
		return gamestart;
	}
	public void setGamestart(boolean gamestart) {
		this.gamestart = gamestart;
	}
	public HashMap<Ennemi, ThreadEnnemi> getHmThreadE() {
		return hmThreadE;
	}
	public void setHmThreadE(HashMap<Ennemi, ThreadEnnemi> hmThreadE) {
		this.hmThreadE = hmThreadE;
	}
	public HashMap<Player, ThreadPlayer> getHmThreadP() {
		return hmThreadP;
	}
	public void setHmThreadP(HashMap<Player, ThreadPlayer> hmThreadP) {
		this.hmThreadP = hmThreadP;
	}
	public void setLstEnnemi(ArrayList<Ennemi> lstEnnemi) {
		this.lstEnnemi = lstEnnemi;
	}
	public void setLstPlayer(ArrayList<Player> lstPlayer) {
		this.lstPlayer = lstPlayer;
	}
	public ArrayList<Ennemi> getLstEnnemi() {
		return lstEnnemi;
	}
	public ArrayList<Player> getLstPlayer() {
		return lstPlayer;
	}
	
}
