package PackageClass;

import PackageRender.Render;


public class Menu {
	public static Player p1;
	public static Ennemie e1;
	public static Render  r ;
	public Menu(int i)
	{
		switch(i)
		{
			case 1: 
			{
				new Plateau();
				r = new Render(600,800);	
				p1 = new Player(7,7);
				
				
				Plateau.plateau[7][7]=p1;
			}
			case 2:
			{
				
			}
		}
	}
}
