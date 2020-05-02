package PackageClass;
/*
 * un Thread qui gerera un ennemie
 * ces ennemie seront autonome et auront une IA plus ou moins forte (rajout de difficulté ?)
 * ils seront au nombre de 4 et auront pour objectif de tué le joueur 
 * ils ont 2 etat actif inactif ... ils deviennent inactif lorsque la bordure coller a eux est activé
 * une fois mort il ne reapparaissent pas
 * il doivent suivre les meme condition qu'un joueur
 */
public class Ennemie extends Entity {

	public final int SCORE_BLOC = 400;
	public final int SCORE_STUN = 100;
	
	public boolean stun = false;

	
	public Ennemie(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public boolean nextToEdge()
	{
		if(this.getPosX() == 1 || this.getPosX() == 13)
		{
			return true;
		}
		if(this.getPosY() == 1 || this.getPosY() == 15)
		{
			return true;
		}
		else {
			return false;
		}
		
	}

} 
