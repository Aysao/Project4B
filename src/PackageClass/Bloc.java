package PackageClass;

/*
 * bloc abstract qui permet de donné les caractèristique similaire au bloc Spéciaux et Normal
 * elle sera un thread qui s'activera des que le joueur aura cliqué dessus
 * et se stoppera a chaque fois qu'elle rencontrera un autre bloc ou la bordure 
 * les bloc seront généré aleatoirement ? 
 * cependant un chemin devra etre possible pour la circulation du personnage
 */
public abstract class Bloc {
	public final int SO = 0;
	public final int NORD = 1;
	public final int SOUTH = 2;
	public final int EAST = 3;
	public final int WEST = 4;
	private int posX;
	private int posY;
	private boolean dort = true; // Etat dort (ne bouge pas); Etat !dort (interaction avec le joueur);
	private int orientation = SO; // Orientation;
	
	
	public Bloc(int X,int Y)
	{
		posX = X;
		posY = Y;
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
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public void setDort(boolean dort) {
		this.dort = dort;
	}
	public boolean isDort() {
		return dort;
	}
	public int getPosY() {
		return posY;
	}
	public int getOrientation() {
		return orientation;
	}
	public void Deplacement() {
		switch (orientation) {
			case 0: {
				
			}
			case 3: {
				posX -= 1;
			}
			case 4: {
				posX += 1;
			}
			case 1: {
				posY -= 1;
			}
			case 2: {
				posY += 1;
			}
			
		}
	}
	
	
	
}
