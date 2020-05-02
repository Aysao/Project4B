package PackageClass;

public class Menu {
	public static Player p1;
	public static Ennemie e1;
	public Menu(int i)
	{
		switch(i)
		{
			case 1: 
			{
				Plateau plateau = new Plateau();
				p1 = new Player(7,7);
				
			}
			case 2:
			{
				
			}
		}
	}
}
