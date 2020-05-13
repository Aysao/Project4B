package PackageClass;


/*
 * class qui génère les caracteristique de la bordure:
 * l'action du click sur la bordure permettant de stun les ennemies
 * la bordure ce génèrera sur le bord d'un tableau 2D(tableau de jeu)
 */
public class Bordure {
	public final int SO = 0;
	public final int NORD = 1;
	public final int SOUTH = 2;
	public final int EAST = 3;
	public final int WEST = 4;
	
	private boolean activate = false;
	private int side;
	
	public Bordure()
	{
		
	}
	public Bordure(int x)
	{
		setSide(x);
	}
	
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	
	
}
 