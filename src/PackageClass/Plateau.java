package PackageClass;

public class Plateau {
	public static Object[][] plateau;
	private static int Largeur = 14;
	private static int hauteur = 17;
	
	public Plateau()
	{
		plateau = new Object[Largeur][hauteur];
		initBloc();
	}
	public void initBloc()
	{
		//initialisation random du terrain
		for(int i = 0;i<Largeur;i++)
		{
			for(int j = 0;j<hauteur; j++)
			{
				if(i == 0 || i == Largeur-1 || j == 0 || j == hauteur-1) 
				{
					plateau[i][j] = new Bordure();
				}
				else
					plateau[i][j] = new BlocN(i,j);
			}
		}
		plateau[1][1] = new Player(0,0);
		plateau[1][2] = null;
		plateau[2][1] = new Ennemie(1,0);
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
	public static int getLargeur() {
		return Largeur;
	}
	public static int getHauteur() {
		return hauteur;
	}

}
