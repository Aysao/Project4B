package PackageClass;


import java.util.Random;

/*
 * cette classe permettra de g�r� les mouvement possible des joueur et ennemie
 * en gros les actions similaire entre le joueur et l'ennemie
 */
public abstract class Entity {

	
	private boolean mouvement = false;	
	private int posX;
	private int posY;
	private Orientation or = Orientation.SO;
	
	public Entity(int x,int y)
	{
		posX = x;
		posY = y;
		Plateau.plateau[x][y] = this;
	}
	
	public Entity()
	{		
		int hauteur = Plateau.getHauteur();
		int largeur = Plateau.getLargeur();
		int nbBloc = countBloc();	
		int r= new Random().nextInt(nbBloc)+1;
		int cpt=0;
		for(int i=0 ; i<hauteur ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<largeur; j++)
			{					
				if(i==0||i==hauteur-1)
				{
					
				}
				else if(j==0||j==largeur-1)
				{
					
				}
				else if(Plateau.plateau[i][j].getClass()==BlocN.class)
				{
					cpt++;
				}
				if(cpt==r) 
				{
					posX = i;
					posY = j;
					Plateau.plateau[i][j]=this;
					break;
				}			
			}
			if(cpt==r) 
			{
				break;
			}
		}						
	}
	private int countBloc()
	{
		int cpt = 0;
	
		for(int i=0 ; i<Plateau.getHauteur() ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<Plateau.getLargeur(); j++)
			{					
				
				if(Plateau.plateau[i][j].getClass()==BlocN.class)
				{
					cpt++;
				}						
			}
		}
		return cpt;
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
	public int getPosX() {
		return posX;
	}
	public Orientation getOrientation() {
		return or;
	}

	public abstract void Deplacement();
	

	public void setMouvement(boolean mouvement) {
		this.mouvement = mouvement;
	}
	public boolean isMouvement() {
		return mouvement;
	}
		
}

	
	
	
	 

