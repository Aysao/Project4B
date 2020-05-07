package PackageClass;


/*
 * class qui génère les caracteristique de la bordure:
 * l'action du click sur la bordure permettant de stun les ennemies
 * la bordure ce génèrera sur le bord d'un tableau 2D(tableau de jeu)
 */
public class Bordure {
	public final static int SO = 0;
	public final static int NORD = 1;
	public final static int SOUTH = 2;
	public final static int EAST = 3;
	public final static int WEST = 4;
	
	private boolean activate = false;
	private int side;
	private int side2;
	public Bordure(int x)
	{
		setSide(x);
	}
	public Bordure(int a,int b)
	{
		setSide(a);
		setSide2(b);
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
	public int getSide2() {
		return side2;
	}
	public void setSide2(int side2) {
		this.side2 = side2;
	}
}
 