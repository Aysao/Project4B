package PackageClass;


/*
 * class qui génère les caracteristique de la bordure:
 * l'action du click sur la bordure permettant de stun les ennemies
 * la bordure ce génèrera sur le bord d'un tableau 2D(tableau de jeu)
 */
public class Bordure {

	private boolean activate = false;
	private Orientation side;	
	public Bordure()
	{
		
	}
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	public Orientation getSide() {
		return side;
	}
	public void setSide(Orientation side) {
		this.side = side;
	}
	
	
}
 