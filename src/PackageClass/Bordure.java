package PackageClass;


/*
 * class qui g�n�re les caracteristique de la bordure:
 * l'action du click sur la bordure permettant de stun les ennemies
 * la bordure ce g�n�rera sur le bord d'un tableau 2D(tableau de jeu)
 */
public class Bordure {
	
	private boolean activate = false;
	public Bordure()
	{
		
	}
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
}
 