package PackageClass;

/*
 * cette classe permettra de g�r� les mouvement possible des joueur et ennemie
 * en gros les actions similaire entre le joueur et l'ennemie
 */
public abstract class Entity {
	public final static int SO = 0;
	public final static int NORD = 1;
	public final static int SOUTH = 2;
	public final static int EAST = 3;
	public final static int WEST = 4;
	
	private Boolean Mort = false;
	private int posX;
	private int posY;
	private int orientation = SO;
	
	public Entity(int x,int y)
	{
		posX = x;
		posY = y;
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
	public void Deplacement()
	{
		if(this.getClass()==Player.class)
		{
			Player p = (Player)this;
			System.out.println(p.getScr().getPoint());
			if((!(posX == 1 && orientation == WEST) || !(posX == 13 && orientation == EAST) ||
					!(posY == 1 && orientation == NORD) || !(posY == 15 && orientation == SOUTH)))
			{			
				switch (orientation) {
					case SO: {						
					}break;

					case EAST: {

					
						System.out.println("Class : " + Plateau.plateau[posX][posY+1].getClass());
						if(Plateau.plateau[posX][posY+1].getClass() == String.class)
						{
							System.out.println("orientation : "+ orientation );
							Plateau.refreshEntity(this); 
							posY += 1;
							Plateau.refreshEntity(this);
						}
						else if(Plateau.plateau[posX][posY+1].getClass() == BlocN.class&&Plateau.plateau[posX][posY+2].getClass() == BlocN.class)
						{
							Plateau.refreshEntity(this);
							posY += 1;
							Plateau.refreshEntity(this);
							p.getScr().BlocDestroy();// modifi le score du joueur
						}
						else if(Plateau.plateau[posX][posY+1].getClass() == BlocN.class&&Plateau.plateau[posX][posY+2].getClass()== String.class)
						{
							int i = 2;
							while(Plateau.plateau[posX][posY+i].getClass()== String.class)
							{
								i++;
							}
							i-=2;
							
							Plateau.refreshEntity(this);
							posY += 1;
							Plateau.plateau[posX][posY+i]=new BlocN(posX,posY+i);					
							Plateau.refreshEntity(this);
							
						}
						
					}break;

					case WEST: {

						System.out.println("Class : " + Plateau.plateau[posX][posY-1].getClass());


						if(Plateau.plateau[posX][posY-1].getClass() == String.class)
						{
							//System.out.println("orientation : "+ orientation );
							Plateau.refreshEntity(this);
							posY -= 1;
							Plateau.refreshEntity(this);
						}
						else if(Plateau.plateau[posX][posY-1].getClass() == BlocN.class&&Plateau.plateau[posX][posY-2].getClass() == BlocN.class)
						{
							Plateau.refreshEntity(this);
							posY -= 1;
							Plateau.refreshEntity(this);
							p.getScr().BlocDestroy();
						}
						else if(Plateau.plateau[posX][posY-1].getClass() == BlocN.class&&Plateau.plateau[posX][posY-2].getClass()== String.class)
						{
							int i = 2;
							while(Plateau.plateau[posX][posY-i].getClass()== String.class)
							{
								i++;
							}
							i-=2;
							
							Plateau.refreshEntity(this);
							posY -= 1;
							Plateau.plateau[posX][posY-i]=new BlocN(posX,posY-i);					
							Plateau.refreshEntity(this);
							
						}
						
					}break;

					case NORD: {
						//System.out.println("Class : " + Plateau.plateau[posX-1][posY].getClass());

					
						System.out.println("Class : " + Plateau.plateau[posX-1][posY].getClass());

						if(Plateau.plateau[posX-1][posY].getClass() == String.class)
						{
							Plateau.refreshEntity(this);
							posX -= 1;
							Plateau.refreshEntity(this);
						}
						else if(Plateau.plateau[posX-1][posY].getClass() == BlocN.class&&Plateau.plateau[posX-2][posY].getClass() == BlocN.class)
						{
							Plateau.refreshEntity(this);
							posX -= 1;
							Plateau.refreshEntity(this);
							p.getScr().BlocDestroy();
						}
						else if(Plateau.plateau[posX-1][posY].getClass() == BlocN.class&&Plateau.plateau[posX-2][posY].getClass()== String.class)
						{
							int i = 2;
							while(Plateau.plateau[posX-i][posY].getClass()== String.class)
							{
								i++;
							}
							i-=2;
							
							Plateau.refreshEntity(this);
							posX -= 1;
							Plateau.plateau[posX-i][posY]=new BlocN(posX-i,posY);					
							Plateau.refreshEntity(this);
							
						}
						
					}break;

					case SOUTH: {						

						
						System.out.println("Class : " + Plateau.plateau[posX+1][posY].getClass());


						if(Plateau.plateau[posX+1][posY].getClass() == String.class)
						{
							System.out.println("orientation : "+ orientation );
							Plateau.refreshEntity(this);
							posX += 1;
							Plateau.refreshEntity(this);
						}
						else if(Plateau.plateau[posX+1][posY].getClass() == BlocN.class&&Plateau.plateau[posX+2][posY].getClass() == BlocN.class)
						{
							Plateau.refreshEntity(this);
							posX += 1;
							Plateau.refreshEntity(this);
							p.getScr().BlocDestroy();
						}
						else if(Plateau.plateau[posX+1][posY].getClass() == BlocN.class&&Plateau.plateau[posX+2][posY].getClass()== String.class)
						{
							int i = 2;
							while(Plateau.plateau[posX+i][posY].getClass()== String.class)
							{
								i++;
							}
							i-=2;
							
							Plateau.refreshEntity(this);
							posX += 1;
							Plateau.plateau[posX+i][posY]=new BlocN(posX+i,posY);					
							Plateau.refreshEntity(this);
											
						}
						
					}break;
					
				}
				
			}
		}
		
	}
		
	
	 
} 
