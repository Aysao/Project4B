package PackageClass;

import java.util.Random;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import PackageThreads.ThreadEnnemie;

/*
 * un Thread qui gerera un ennemie
 * ces ennemie seront autonome et auront une IA plus ou moins forte (rajout de difficult� ?)
 * ils seront au nombre de 4 et auront pour objectif de tu� le joueur 
 * ils ont 2 etat actif inactif ... ils deviennent inactif lorsque la bordure coller a eux est activ�
 * une fois mort il ne reapparaissent pas
 * il doivent suivre les meme condition qu'un joueur
 */
public class Ennemi extends Entity {

	public final int SCORE_BLOC = 400;
	public final int SCORE_STUN = 100;
	public boolean dead = false;
	public boolean stun =false;
	public int pathX ;
	public int pathY ;
	private ThreadEnnemie runte;
	private Thread te;
	public Ennemi(int x, int y) {
		super(x, y);				
		newPoint();
	}
	public Ennemi() {
		super();		
		newPoint();
	}
	public void start()
	{
		runte = new ThreadEnnemie(this);
		te =new Thread(runte);
		te.start();
	}
	public void stop()
	{
		runte.stop();		
	}
	public synchronized void Deplacement()
	{		
		
		if(this.getClass()==Ennemi.class)
		{				
			if((!(this.getPosX() == 1 && this.getOrientation() == WEST) || !(this.getPosX() == 13 && this.getOrientation() == EAST) ||
					!(this.getPosY() == 1 && this.getOrientation() == NORD) || !(this.getPosY() == 15 && this.getOrientation() == SOUTH)))
			{			
				switch (this.getOrientation()) 
				{
					case SO: {						
					}break;

					case EAST: 
					{	
						if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == Player.class)
						{
							Plateau.refreshEntity(Menu.p1);	
							if(Menu.p1.getVie()-1 >= 0)							{
								
								Menu.p1.setVie(Menu.p1.getVie()-1);
								Menu.p1.setPosX(7);
								Menu.p1.setPosY(7);	
							}	
							Plateau.refreshEntity(Menu.p1);									
						}
						
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()+1) ;
								Plateau.refreshEntity(this);
									
					}break;

					case WEST: 
					{	
						if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == Player.class)
						{
							Plateau.refreshEntity(Menu.p1);
							if(Menu.p1.getVie()-1 >= 0)
							{
								Menu.p1.setVie(Menu.p1.getVie()-1);
								Menu.p1.setPosX(7);
								Menu.p1.setPosY(7);								
							}	
							Plateau.refreshEntity(Menu.p1);								
						}
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.refreshEntity(this);
										
					}break;
						

					case NORD: 
					{			
						if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == Player.class)
						{
							Plateau.refreshEntity(Menu.p1);	
							if(Menu.p1.getVie()-1 >= 0)
							{
								Menu.p1.setVie(Menu.p1.getVie()-1);
								Menu.p1.setPosX(7);
								Menu.p1.setPosY(7);	
							}	
							Plateau.refreshEntity(Menu.p1);	
						}
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.refreshEntity(this);
								
																																		
					}break;

					case SOUTH: 
					{																				
						if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == Player.class)
						{
							Plateau.refreshEntity(Menu.p1);	
							if(Menu.p1.getVie()-1 >= 0)
							{
								Menu.p1.setVie(Menu.p1.getVie()-1);
								Menu.p1.setPosX(7);
								Menu.p1.setPosY(7);								
							}
							Plateau.refreshEntity(Menu.p1);	
						}
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.refreshEntity(this);																	
					}break;					
				}				
			}
		}					
	}
	public void newPoint()
	{
		boolean test = true;
		int x;
		int y;
		do
		{
			x = (new Random()).nextInt(15)+1;
		    y = (new Random()).nextInt(13)+1;
		    if(Plateau.plateau[x][y].getClass()!=BlocSpe.class&&Plateau.plateau[x][y].getClass()!=Bordure.class)
		    {
		    	test = false;
		    }
		}while(test);	    
	    this.pathX=x;
	    this.pathY=y;
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
