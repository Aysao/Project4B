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
	public boolean dead = false;
	public boolean stun = false;

	
	public Ennemie(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void Deplacement()
	{		
			if(this.getClass()==Ennemie.class)
			{				
				if((!(this.getPosX() == 1 && this.getOrientation() == WEST) || !(this.getPosX() == 13 && this.getOrientation() == EAST) ||
						!(this.getPosY() == 1 && this.getOrientation() == NORD) || !(this.getPosY() == 15 && this.getOrientation() == SOUTH)))
				{			
					switch (this.getOrientation()) {
						case SO: {						
						}break;

						case EAST: {
						
							System.out.println("Class : " + Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass());
							if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == String.class)
							{
								System.out.println("this.getOrientation() : "+ this.getOrientation() );
								Plateau.refreshEntity(this); 
								this.setPosY(this.getPosY()+1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()+1) ;
								Plateau.refreshEntity(this);
							
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocN.class&&Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass()== String.class)
								{
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()+1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()+i]=new BlocN(this.getPosX(),this.getPosY()+i);					
								Plateau.refreshEntity(this);								
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocSpe.class&&Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass()== String.class)
								{
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()+1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()+i]=new BlocSpe(this.getPosX(),this.getPosY()+i);					
								Plateau.refreshEntity(this);								
							}
							
						}break;

						case WEST: {

							System.out.println("Class : " + Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass());


							if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == String.class)
							{
								//System.out.println("this.getOrientation() : "+ this.getOrientation() );
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.refreshEntity(this);
								
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocN.class&&Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass()== String.class)
								{
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()-i]=new BlocN(this.getPosX(),this.getPosY()-i);					
								Plateau.refreshEntity(this);						
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocSpe.class&&Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass()== String.class)
								{
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()-i]=new BlocSpe(this.getPosX(),this.getPosY()-i);					
								Plateau.refreshEntity(this);						
							}
							
						}break;

						case NORD: {
							//System.out.println("Class : " + Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass());

						
							System.out.println("Class : " + Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass());

							if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == String.class)
							{
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.refreshEntity(this);
								
							}
							else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocN.class&&Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass()== String.class)
								{
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.plateau[this.getPosX()-i][this.getPosY()]=new BlocN(this.getPosX()-i,this.getPosY());					
								Plateau.refreshEntity(this);								
							}
							else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocSpe.class&&Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass()== String.class)
								{
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.plateau[this.getPosX()-i][this.getPosY()]=new BlocSpe(this.getPosX()-i,this.getPosY());					
								Plateau.refreshEntity(this);								
							}
							
						}break;

						case SOUTH: {													
							System.out.println("Class : " + Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass());
							if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == String.class)
							{
								System.out.println("this.getOrientation() : "+ this.getOrientation() );
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.refreshEntity(this);
								
							}
							else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocN.class&&Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass()== String.class)
								{
									i++;
								}
								i-=2;							
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.plateau[this.getPosX()+i][this.getPosY()]=new BlocN(this.getPosX()+i,this.getPosY());					
								Plateau.refreshEntity(this);												
							}	
							else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocSpe.class&&Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass()== String.class)
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass()== String.class)
								{
									i++;
								}
								i-=2;							
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.plateau[this.getPosX()+i][this.getPosY()]=new BlocSpe(this.getPosX()+i,this.getPosY());					
								Plateau.refreshEntity(this);												
							}	
						}break;					
					}				
				}
			}					
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
