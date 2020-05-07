package PackageClass;

import PackageRender.Render;
import PackageThreads.ThreadPlayer;


public class Menu {
	public static Player p1;
	public static Entity p2;
	public static Ennemie e1;
	public static Ennemie e2;
	public static Ennemie e3;
	public static Ennemie e4;
	public static Render  r ;
	public static Victory  v ;
	public static ThreadPlayer runtp;
	public Menu(int i)
	{
		switch(i)
		{
			case 1: 
			{
				new Plateau();
				p1 = new Player(7,7);
				e1 = new Ennemie(1,1);
				e2 = new Ennemie(11,11);
				e3 = new Ennemie(11,1);
				e4 = new Ennemie(1,11);
				ThreadPlayer runtp = new ThreadPlayer(p1);
				v = new Victory();
				
				Thread tp = new Thread(runtp);
				tp.start();
				r = new Render(600,800,runtp.getKl());
				Thread t = new Thread(Menu.r);
				t.start();
				
				while(!(v.isVictory()) && p1.getVie() > 0)
				{

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				fin();
				
			}
			case 2:
			{
				
			}
		}
	}
	
	public static void fin()
	{
		r.stop();
		runtp = null;
	}
}
