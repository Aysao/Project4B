package PackageClass;

import java.io.IOException;

import javax.swing.JOptionPane;

import PackageRender.Render;

import PackageThreads.ThreadPlayer;


public class Menu {
	public static Player p1;
	public static Entity p2;
	public static int ennemiVie=6;
	public static Ennemi e1;
	public static Ennemi e2;
	public static Ennemi e3;
	public static Ennemi e4;
	public static Render  r ;
	public static Victory  v ;
	private ThreadPlayer runtp;
	public Menu(int i) 
	{
		switch(i)
		{
			case 1://nb de joueur 
			{
				new Plateau();
				p1 = new Player(7,7,"karvrak");
				runtp = new ThreadPlayer(p1);
				e1 = new Ennemi();				
				e2 = new Ennemi();				
				e3 = new Ennemi();				
				v = new Victory();
				Thread tp = new Thread(runtp);
				e1.start();
				e2.start();//les e ont un thread associï¿½ (pour pouvoir le stop grace a l'ennemi)
				e3.start();				
				tp.start();				
				r = new Render(600,800,runtp.getKl());
				Thread t = new Thread(r);
				t.start();
				
				while(!(v.isVictory()) && p1.getVie() > 0)
				{

					try {
						Thread.sleep(100);
						Plateau.stopBordure();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				fin();
				if(v.isVictory() == false)
				{
					JOptionPane.showMessageDialog(r, "Perdu !");
				}
				if(v.isVictory() == true)
				{
					JOptionPane.showMessageDialog(r, "Gagné!");
				}
				
			}
			case 2:
			{
				
			}
		}
	}
	public static void newEnnemi()
	{
		ennemiVie--;
		System.out.println("vie"+ennemiVie);
		if(ennemiVie==0)
		{
			v.setVictory(true);
			return;
		}
		else if(ennemiVie>=3)
		{
			Ennemi e=new Ennemi();
			e.start();	
		}
		
	}

	public void fin() 
	{
		try {
			p1.getScr().setScore();
		} catch (IOException e) {
			e.printStackTrace();
		}
		r.stop();
		runtp = null;
	}
}
