package PackageClass;

public class Plateau {
	public static Object[][] plateau;
	private static int Largeur = 14;
	private static int hauteur = 17;
	
	public Plateau()
	{
		plateau = new Object[Largeur][hauteur];
	}
	public void initBloc()
	{
		//initialisation random du terrain
		plateau[0][0] = new BlocN(0,0);
		plateau[0][1] = null;
	}
	public static void refreshEntity(Entity e)
	{
		if(plateau[e.getPosX()][e.getPosY()] != e)
		{
			plateau[e.getPosX()][e.getPosY()] = e;
		}
		else 
		{
			plateau[e.getPosX()][e.getPosY()] = null;
		}
	}

}
