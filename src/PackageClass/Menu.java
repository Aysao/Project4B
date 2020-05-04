package PackageClass;

import PackageRender.Render;
import PackageThreads.ThreadPlayer;


public class Menu {
	public static Player p1;
	public static Entity p2;
	public static Ennemie e1;
	public static Render  r ;
	public static Victory  v ;
	public Menu(int i)
	{
		switch(i)
		{
			case 1: 
			{
				new Plateau();
				p1 = new Player(7,7);
				//ThreadPlayer runtp = new ThreadPlayer(p1);
				//Thread tp = new Thread(runtp);
				//tp.start();
				r = new Render(600,800);	
				v = new Victory();
				
			}
			case 2:
			{
				
			}
		}
	}
}
