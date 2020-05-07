package PackageClass;

import javax.swing.JOptionPane;

import PackageRender.Render;
import PackageThreads.ThreadEnnemie;
import PackageThreads.ThreadPlayer;


public class Menu {
	public static Player p1;
	public static Entity p2;
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
			case 1: 
			{
				new Plateau();
				p1 = new Player(7,7);
				runtp = new ThreadPlayer(p1);
				e1 = new Ennemi(1,2);				
				e2 = new Ennemi(11,3);				
				e3 = new Ennemi(11,11);				
				v = new Victory();
				Thread tp = new Thread(runtp);
				e1.start();
				e2.start();//les e ont un thread associ� (pour pouvoir le stop grace a l'ennemi)
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
					JOptionPane.showMessageDialog(r, "Gagn� !");
				}
				
			}
			case 2:
			{
				
			}
		}
	}
	
	public void fin()
	{
		r.stop();
		runtp = null;
	}
}
