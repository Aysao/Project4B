package PackageThreads;

import java.io.IOException;
import java.util.HashMap;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import PackageClass.Ennemi;
import PackageClass.Plateau;
import PackageClass.Player;
import PackageClass.Victory;
import PackageRender.Render;


public class Menu implements Runnable {

	private Player p1;
	private Ennemi p2;
	public static int ennemiVie=5;
	private Ennemi e1;
	private Ennemi e2;
	private Ennemi e3;
	private Render r ;	
	public static Victory v ;
	public static char avancer='Z';		//touche de deplacement une option pour qu'on puisse changer les touches
	public static char reculer='S';
	public static char droite='D';
	public static char gauche='Q';
	private JFrame menuPrincipal;
	public static boolean host = true ;
	public static Client c;
	public static Serveur s;
	public static int mode ;
	public static boolean gamestart=true;
    public static HashMap<Ennemi, ThreadEnnemie> hmThreadE = new HashMap<Ennemi, ThreadEnnemie>();
    public static HashMap<Player, ThreadPlayer> hmThreadP = new HashMap<Player, ThreadPlayer>();
	

	
	
	public Menu(JFrame f,int i) 
	{
		mode=i;
		menuPrincipal = f;
		switch(i)
		{
			case 1://nb de joueur 
			{
				new Plateau();
				p1 = new Player(7,7);
				p1.setPseudo(JOptionPane.showInputDialog("Entrer votre pseudo :"));
				if(p1.getPseudo().equals(""))
				{
					p1.setPseudo("Player");
				}
				p1.getScr().setName(p1.getPseudo());
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
					p1.setPseudo(JOptionPane.showInputDialog("Entrer votre pseudo :"));	
					p2= new Ennemi(8,8);
					s = new Serveur();
					Thread th = new Thread(s);
					th.start();
					gamestart=false;
					while(!gamestart)
					{
						System.out.println("waiting...");
					}
					ThreadPlayer runtp = new ThreadPlayer(p1);
					Thread tp = new Thread(runtp);
					tp.start();																						
					r = new Render(menuPrincipal,600,800,runtp.getKl());					
					Thread t = new Thread(r);
					t.start();	
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
	public static void newEnnemi()
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

	public void fin() 
	{	
		try {
			p1.getScr().setScore();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clear();
		if(v.isVictory() == false)
		{
			JOptionPane.showMessageDialog(r,"Perdu !");
		}
		if(v.isVictory() == true)
		{
			JOptionPane.showMessageDialog(r,"Gagn�!");
		}	
		
	}
	public void clear()
	{
		ennemiVie = 6;
		Plateau.clearEntity();//supprime thread player et ennemis restant
		r.stop();//stop refresh fenetre
		r.dispose();
		menuPrincipal.setVisible(true);		
	}
	@Override
	public void run() {		
		while(!(v.isVictory()) && p1.getVie() > 0)
		{
			try {
				Thread.sleep(100);
				Plateau.stopBordure();
				if(host &&mode==2)
				{
					s.sendLine("bordure");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fin();
		
	}
	
}
