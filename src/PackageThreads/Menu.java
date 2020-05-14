package PackageThreads;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import PackageClass.BlocN;
import PackageClass.Ennemi;
import PackageClass.Plateau;
import PackageClass.Player;
import PackageClass.Victory;
import PackageRender.Render;


public class Menu implements Runnable {

	
	private static Menu instance ;
	
	private int scoreTimer=0;
	private Player p1;
	private Ennemi p2;
	private int ennemiVie=6;
	private Ennemi e1;
	private Ennemi e2;
	private Ennemi e3;
	private Render r ;	
	private Victory v ;
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
	private HashMap<Ennemi, ThreadEnnemie> hmThreadE = new HashMap<Ennemi, ThreadEnnemie>();
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
			}break;
			case 2:
			{
				if(host)
				{
					new Plateau();
					v = new Victory();	
					p1 = new Player(7,7);
					p1.setPseudo(pseudo);	
					p2= new Ennemi(8,8);
					s = new Serveur();
					Thread th = new Thread(s);
					th.start();				
					gamestart=false;					
					while(!gamestart)
					{
					System.out.println("");
					}						
					ThreadPlayer runtp = new ThreadPlayer(p1);
					Thread tp = new Thread(runtp);
					tp.start();																						
					r = new Render(menuPrincipal,600,800,runtp.getKl());					
					Thread t = new Thread(r);
					t.start();	
					s.sendLine("getplayer");
				}
				else
				{								
					c= new Client();
					Thread tc = new Thread(c);
					tc.start();
					p1 = new Player(7,7);
					p2 = new Ennemi(8,8);
					p2.setPlayed(true);					
					ThreadEnnemie runte = new ThreadEnnemie(p2);
					Thread te = new Thread(runte);
					te.start();	
					v = new Victory();																															
					r = new Render(menuPrincipal,600,800,runte.getKl());					
					Thread t = new Thread(r);
					t.start();					
				}				
			}
		}
	}
	private void startSolo() {
		ThreadPlayer runtp = new ThreadPlayer(p1);
		Thread tp = new Thread(runtp);
		tp.start();	
		ThreadEnnemie runte = new ThreadEnnemie(e1);
		Thread te =new Thread(runte);
		te.start();
		ThreadEnnemie runte1 = new ThreadEnnemie(e2);
		Thread te1 =new Thread(runte1);
		te1.start();
		ThreadEnnemie runte2 = new ThreadEnnemie(e3);
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
				s.sendLine("new location");
				System.out.println(Integer.toString(lst.get(i).getPosX()));
				s.sendLine(Integer.toString(lst.get(i).getPosX()));
				System.out.println(Integer.toString(lst.get(i).getPosY()));
				s.sendLine(Integer.toString(lst.get(i).getPosY()));
			}			
		}
		
		
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
				ThreadEnnemie runte = new ThreadEnnemie(e);
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
				s.sendLine("win");
			}
		}
		if(v.isVictory() == true)
		{
			JOptionPane.showMessageDialog(r,"Gagn�!");
			if(mode==2 &&host)
			{
				s.sendLine("loose");
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
			ThreadEnnemie runte = new ThreadEnnemie(e);
			Thread te =new Thread(runte);
			te.start();
			hmThreadE.put(e, runte);	
		}	
	}
	@Override
	public void run() {	
		while(!v.isVictory() && p1.getVie() > 0)
		{
			try {
				Thread.sleep(100);
				Plateau.stopBordure();
				if(host &&mode==2)
				{
					s.sendLine("bordure");
				}
				scoreTimer++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fin();
		
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
	public HashMap<Ennemi, ThreadEnnemie> getHmThreadE() {
		return hmThreadE;
	}
	public void setHmThreadE(HashMap<Ennemi, ThreadEnnemie> hmThreadE) {
		this.hmThreadE = hmThreadE;
	}
	public HashMap<Player, ThreadPlayer> getHmThreadP() {
		return hmThreadP;
	}
	public void setHmThreadP(HashMap<Player, ThreadPlayer> hmThreadP) {
		this.hmThreadP = hmThreadP;
	}
	
}
