package PackageClass;

/*
 * bloc abstract qui permet de donn� les caract�ristique similaire au bloc Sp�ciaux et Normal
 * elle sera un thread qui s'activera des que le joueur aura cliqu� dessus
 * et se stoppera a chaque fois qu'elle rencontrera un autre bloc ou la bordure 
 * les bloc seront g�n�r� aleatoirement ? 
 * cependant un chemin devra etre possible pour la circulation du personnage
 */
public abstract class Bloc {

	private int posX;
	private int posY;
	private Orientation or = Orientation.SO; // Orientation;

	 
	public Bloc(int X,int Y)
	{
		setPosX(X);
		setPosY(Y);
	}	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public void setOrientation(Orientation orientation) {
		this.or = orientation;
	}	
	public int getPosY() {
		return posY;
	}
	public Orientation getOrientation() {
		return or;
	}	

}
