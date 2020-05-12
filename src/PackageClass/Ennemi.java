package PackageClass;

import java.util.Random;


import PackageThreads.Menu;
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

	public boolean dead = false;
	public boolean stun =false;
	public int pathX ;
	public int pathY ;
	private ThreadEnnemie runte;
	private Thread te;
	private boolean played = false;
	public Ennemi(int x, int y) {
		super(x, y);				
		newPoint();
	}
	public Ennemi() {
		super();		
		newPoint();
	}
	public ThreadEnnemie getpT()
	{
		return this.runte;
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
							Player p1 = (Player) Plateau.plateau[this.getPosX()][this.getPosY()+1];
							Plateau.refreshEntity(p1);	
							if(p1.getVie()-1 >= 0)							{
								
								p1.setVie(p1.getVie()-1);
								p1.setPosX(7);
								p1.setPosY(7);	
							}	
							Plateau.refreshEntity(p1);									
						}
						if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == String.class)
						{
							Plateau.refreshEntity(this);
							this.setPosY(this.getPosY()+1) ;
							Plateau.refreshEntity(this);								
						}
								
									
					}break;

					case WEST: 
					{	
						if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == Player.class)
						{
							Player p1 = (Player) Plateau.plateau[this.getPosX()][this.getPosY()-1];
							Plateau.refreshEntity(p1);
							if(p1.getVie()-1 >= 0)
							{
								p1.setVie(p1.getVie()-1);
								p1.setPosX(7);
								p1.setPosY(7);								
							}	
							Plateau.refreshEntity(p1);								
						}
						if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == String.class)
						{
							Plateau.refreshEntity(this);
							this.setPosY(this.getPosY()-1) ;
							Plateau.refreshEntity(this);								
						}
										
					}break;
						

					case NORD: 
					{			
						if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == Player.class)
						{
							Player p1 = (Player) Plateau.plateau[this.getPosX()-1][this.getPosY()];
							Plateau.refreshEntity(p1);	
							if(p1.getVie()-1 >= 0)
							{
								p1.setVie(p1.getVie()-1);
								p1.setPosX(7);
								p1.setPosY(7);	
							}	
							Plateau.refreshEntity(p1);	
						}
						if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == String.class)
						{
							Plateau.refreshEntity(this);
							this.setPosX(this.getPosX()-1) ;
							Plateau.refreshEntity(this);								
						}
								
																																		
					}break;

					case SOUTH: 
					{																				
						if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == Player.class)
						{
							Player p1 = (Player) Plateau.plateau[this.getPosX()+1][this.getPosY()];
							Plateau.refreshEntity(p1);	
							if(p1.getVie()-1 >= 0)
							{
								p1.setVie(p1.getVie()-1);
								p1.setPosX(7);
								p1.setPosY(7);								
							}
							Plateau.refreshEntity(p1);	
						}
						if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == String.class)
						{
							Plateau.refreshEntity(this);
							this.setPosX(this.getPosX()+1) ;
							Plateau.refreshEntity(this);								
						}																	
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
	public ThreadEnnemie getEnnemiT()
	{
		return this.runte;
	}
	public boolean isPlayed() {
		return played;
	}
	public void setPlayed(boolean played) {
		this.played = played;
	}

} 
