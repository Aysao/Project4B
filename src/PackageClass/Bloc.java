package PackageClass;

/*
 * bloc abstract qui permet de donné les caractèristique similaire au bloc Spéciaux et Normal
 * elle sera un thread qui s'activera des que le joueur aura cliqué dessus
 * et se stoppera a chaque fois qu'elle rencontrera un autre bloc ou la bordure 
 * les bloc seront généré aleatoirement ? 
 * cependant un chemin devra etre possible pour la circulation du personnage
 */
public abstract class Bloc {
	public final static int SO = 0;
	public final static int NORD = 1;
	public final static int SOUTH = 2;
	public final static int EAST = 3;
	public final static int WEST = 4;
	private int posX;
	private int posY;
	private boolean dort = true; // Etat dort (ne bouge pas); Etat !dort (interaction avec le joueur);
	private int orientation = SO; // Orientation;
	
	
	public Bloc(int X,int Y)
	{
		posX = X;
		posY = Y;
	}
	
	public void deplacementBloc(int O) {
		orientation = O;
		
	}
	
}
