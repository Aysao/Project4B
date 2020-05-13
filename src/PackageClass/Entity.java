package PackageClass;


import java.util.Random;

/*
 * cette classe permettra de g�r� les mouvement possible des joueur et ennemie
 * en gros les actions similaire entre le joueur et l'ennemie
 */
public abstract class Entity {
	public final int SO = 0;
	public final int NORD = 1;
	public final int SOUTH = 2;
	public final int EAST = 3;
	public final int WEST = 4;
	
	private boolean mouvement = false;
	private Boolean Mort = false;
	private int posX;
	private int posY;
	private int orientation = SO;
	
	public Entity(int x,int y)
	{
		posX = x;
		posY = y;
		Plateau.plateau[x][y] = this;
	}
	
	public Entity()
	{		
		int h = Plateau.getHauteur();
		int l = Plateau.getLargeur();
		int nbR = countBloc();
		int r ;		
		r= new Random().nextInt(nbR)+1;
		int cpt=0;
		for(int i=0 ; i<h ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<l; j++)
			{					
				if(i==0||i==h-1)
				{
					
				}
				else if(j==0||j==l-1)
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
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public int getPosY() {
		return posY;
	}
	public int getPosX() {
		return posX;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setMort(Boolean mort) {
		Mort = mort;
	}
	public Boolean getMort() {
		return Mort;
	}
	public abstract void Deplacement();
	

	public void setMouvement(boolean mouvement) {
		this.mouvement = mouvement;
	}
	public boolean isMouvement() {
		return mouvement;
	}
		
}

	
	
	
	 

