package PackageClass;

import java.util.ArrayList;
import java.util.Random;

import PackageThreads.Menu;



/*
 * ces ennemie seront autonome et auront une IA plus ou moins forte (rajout de difficult� ?)
 * ils seront au nombre de 4 et auront pour objectif de tu� le joueur 
 * ils ont 2 etat actif inactif ... ils deviennent inactif lorsque la bordure coller a eux est activ�
 * une fois mort il ne reapparaissent pas
 * il doivent suivre les meme condition qu'un joueur
 */
public class Ennemi extends Entity {

	public boolean stun =false;
	private int pathX ;
	private int pathY ;
	
	private boolean played = false;
	public Ennemi(int x, int y) {
		super(x, y);				
		newPoint();
	}
	public Ennemi() {
		super(true);		
		newPoint();
	}
	
	
	public synchronized void Deplacement()
	{				
		if(this.getClass()==Ennemi.class)
		{				
			if((!(this.getPosX() == 1 && this.getOrientation() == Orientation.WEST) || !(this.getPosX() == 13 && this.getOrientation() == Orientation.EAST) ||
					!(this.getPosY() == 1 && this.getOrientation() == Orientation.NORD) || !(this.getPosY() == 15 && this.getOrientation() == Orientation.SOUTH)))
			{			
				switch (this.getOrientation()) 
				{
					case EAST: 
					{	
						
						if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == Player.class)
						{
							
							killPlayer(this.getPosX(),this.getPosY(),"Y",1);								
						}
						else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == String.class)
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
							killPlayer(this.getPosX(),this.getPosY(),"Y",-1);							
						}
						else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == String.class)
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
							killPlayer(this.getPosX(),this.getPosY(),"X",-1);
						}
						else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == String.class)
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
							killPlayer(this.getPosX(),this.getPosY(),"X",1);
						}
						else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == String.class)
						{
							Plateau.refreshEntity(this);
							this.setPosX(this.getPosX()+1) ;
							Plateau.refreshEntity(this);								
						}		
																					
					}break;
					case SO:
					{
						
					}
				}				
			}
		}					
	}
	public void killPlayer(int x,int y,String str,int i)
	{
		switch(str)
		{
		case"X":
		{
									
			if(Plateau.plateau[x+i][y].getClass()==Player.class)
			{				
				Player p1 = (Player) Plateau.plateau[x+i][y];
				Plateau.refreshEntity(p1);
				if(p1.getVie()-1 >= 0)
				{
					p1.setVie(p1.getVie()-1);
					p1.setPosX(7);
					p1.setPosY(7);
					Menu.getInstance().newEntity();
				}
				Plateau.refreshEntity(p1);	
											
			}		
		}break;
		case"Y":
		{
									
			if(Plateau.plateau[x][y+i].getClass()==Player.class)
			{				
				Player p1 = (Player) Plateau.plateau[x][y+i];
				Plateau.refreshEntity(p1);
				if(p1.getVie()-1 >= 0)
				{
					p1.setVie(p1.getVie()-1);
					p1.setPosX(7);
					p1.setPosY(7);
					Menu.getInstance().newEntity();
				}
				Plateau.refreshEntity(p1);	
			

				
			}		
		}break;
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
	    this.setPathX(x);
	    this.setPathY(y);
	}
	
	public void newPointNearBloc(int bX,int bY)
	{
		boolean test = true;
		int x;
		int y;
		do
		{
			x = bX-3+(new Random()).nextInt(6)+1;
		    y = bY-3+(new Random()).nextInt(6)+1;

		    if(x < 15 && y <13 && x > 0 && y > 0)
		    {
			    if(Plateau.plateau[x][y].getClass()!=BlocSpe.class&&Plateau.plateau[x][y].getClass()!=Bordure.class)
			    {
			    	test = false;
			    }
		    }
		}while(test);	    
	    this.pathX=x;
	    this.pathY=y;
	}
	
	public void newPointNearPlayer(int bX,int bY)
	{
		boolean test = true;
		int x;
		int y;
		do
		{
			x = bX;
		    y = bY;

		    if(Plateau.plateau[x][y].getClass()!=BlocSpe.class&&Plateau.plateau[x][y].getClass()!=Bordure.class)
		    {
		    	test = false;
		    }
		}while(test);	    
	    this.pathX=x;
	    this.pathY=y;
	}
	
	public Player nearPlayer()
	{
		Player res = null;
		int distx = 0;
		int disty = 0;
		ArrayList<Player> players = Plateau.getPlayer();
		for(int i = 0;i<players.size();i++)
		{
			distx = Math.abs(players.get(i).getPosX()-this.getPosX());
			disty = Math.abs(players.get(i).getPosY()-this.getPosY());
			if(distx+disty <= 5)
			{
				res = players.get(i);
			}
		}
		return res;
		
	}
	
	public boolean isNearPlayer()
	{
		boolean res = false;
		int distx = 0;
		int disty = 0;
		ArrayList<Player> players = Plateau.getPlayer();
		for(int i = 0;i<players.size();i++)
		{
			distx = Math.abs(players.get(i).getPosX()-this.getPosX());
			disty = Math.abs(players.get(i).getPosY()-this.getPosY());
			if(distx+disty <= 5)
			{
				res = true;
			}
		}
		return res;
		
	}
	
	public BlocSpe nearBlocSpe()
	{
		BlocSpe res = null;
		int distx = 0;
		int disty = 0;
		int mindistx = 20;
		int mindisty = 20;
		ArrayList<BlocSpe> diams = Plateau.getBlocSpe();
		for(int i = 0;i<diams.size();i++)
		{
			distx = Math.abs(diams.get(i).getPosX()-this.getPosX());
			disty = Math.abs(diams.get(i).getPosY()-this.getPosY());
			if(distx+disty <= mindistx+mindisty)
			{
				mindistx = distx;
				mindisty = disty;
				res = diams.get(i);
			}
		}
		return res;
		
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
	
	public boolean isPlayed() {
		return played;
	}
	public void setPlayed(boolean played) {
		this.played = played;
	}
	public int getPathX() {
		return pathX;
	}
	public void setPathX(int pathX) {
		this.pathX = pathX;
	}
	public int getPathY() {
		return pathY;
	}
	public void setPathY(int pathY) {
		this.pathY = pathY;
	}

} 
