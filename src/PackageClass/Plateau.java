package PackageClass;

import java.util.ArrayList;
import java.util.Random;

import PackageThreads.Menu;

public class Plateau {
	public static Object[][] plateau;
	private static int largeur = 15; //13 de plateau + 2 de bordure
	private static int hauteur = 17; // 15 de plateau + 2 de bordure
	public boolean bloque = false;
	public Plateau()
	{
		plateau = new Object[hauteur][largeur];
		//initBloc();

		plateau = new Object[hauteur][largeur];
		initFullBloc(hauteur,largeur);
		pathInit();
		setDiams(hauteur,largeur);
		affichTab(hauteur,largeur); 
		
	}
	
	public static ArrayList<Player> getPlayer()
	{
		ArrayList<Player> res = new ArrayList<Player>();
		for(int i = 1;i<hauteur-1;i++)
		{
			for(int j = 1;j<largeur-1;j++)
			{
				if(plateau[i][j].getClass() == Player.class)
				{
					res.add((Player)plateau[i][j]);
				}
			}
		}
		return res;
	}
	
	public static ArrayList<Ennemi> getEnnemi()
	{
		ArrayList<Ennemi> res = new ArrayList<Ennemi>();
		for(int i = 1;i<hauteur-1;i++)
		{
			for(int j = 1;j<largeur-1;j++)
			{
				if(plateau[i][j].getClass() == Ennemi.class)
				{
					res.add((Ennemi)plateau[i][j]);
				}
			}
		}
		return res;
	}
	
	public static ArrayList<BlocSpe> getBlocSpe()
	{
		ArrayList<BlocSpe> res = new ArrayList<BlocSpe>();
		for(int i = 1;i<hauteur-1;i++)
		{
			for(int j = 1;j<largeur-1;j++)
			{
				if(plateau[i][j].getClass() == BlocSpe.class)
				{
					res.add((BlocSpe)plateau[i][j]);
				}
			}
		}
		return res;
	}
	
	public static ArrayList<BlocN> getBlocN()
	{
		ArrayList<BlocN> res = new ArrayList<BlocN>();
		for(int i = 1;i<hauteur-1;i++)
		{
			for(int j = 1;j<largeur-1;j++)
			{
				if(plateau[i][j].getClass() == BlocN.class)
				{
					res.add((BlocN)plateau[i][j]);
				}
			}
		}
		return res;
	}
	
	private void setDiams(int x , int y)
	{
		
		int r ;
		for(int k = 0;k<3;k++)
		{
			r= new Random().nextInt(84)+1;
			int cpt=0;
			for(int i=0 ; i<x ; i++) // on compte pas la bordure
			{
				for(int j=0 ; j<y; j++)
				{					
					if(i==0||i==x-1)
					{
						
					}
					else if(j==0||j==y-1)
					{
						
					}
					else if(plateau[i][j].getClass()==BlocN.class)
					{
						cpt++;
					}
					if(cpt==r) 
					{
						plateau[i][j]=new BlocSpe(i,j);
						break;
					}			
				}
				if(cpt==r) 
				{				
					break;
				}
			}
		}				
	}
	private void affichTab(int x , int y) 
	{
		String st="";
		for(int i=0 ; i<x ; i++) 
		{
			for(int j=0 ; j<y ; j++)
			{
				if(plateau[i][j].getClass()==String.class)
				{
					st+="0";
					System.out.print("0");//si chemin
				}
				else 
				{					
					if(plateau[i][j].getClass()==Bordure.class)
					{
						st+="/";
						System.out.print("/"); //si mur
					}
					else
					{
						if(plateau[i][j].getClass()==BlocN.class)
						{
							st+="#";
							System.out.print("#");//si bloc classiqe
						}
						if(plateau[i][j].getClass()==BlocSpe.class)
						{
							st+="*";
							System.out.print("*");//si diamant
						}
					}									
				}			
			}			
			System.out.println("");
		}
		System.out.println(st);
	}
	public static String PlateauToString()
	{
		String st="";
		for(int i=0 ; i<Plateau.hauteur ; i++) 
		{
			for(int j=0 ; j<Plateau.largeur ; j++)
			{
				if(plateau[i][j].getClass()==String.class)
				{
					st+="0";
				}
				else 
				{					
					if(plateau[i][j].getClass()==Bordure.class)
					{
						Bordure be = (Bordure)plateau[i][j];
						if(be.isActivate())
							st+="|";
						else
							st+="/";										
					}					
					if(plateau[i][j].getClass()==BlocN.class)
					{
						st+="#";						
					}
					if(plateau[i][j].getClass()==BlocSpe.class)
					{
						st+="*";						
					}
					if(plateau[i][j].getClass()==Player.class)
					{
						st+="P";						
					}
					if(plateau[i][j].getClass()==Ennemi.class)
					{
						Ennemi en = (Ennemi)plateau[i][j];
						if(en.stun)
						st+="e";
						else
						st+="E";
					}														
				}			
			}						
		}
		System.out.println(st);
		return st;
	}
	public static void StringToPlateau(String str)
	{
		plateau = new Object[hauteur][largeur];
		for(int i=0 ; i<Plateau.hauteur ; i++) 
		{
			for(int j=0 ; j<Plateau.largeur ; j++)
			{
				
				if((i*Plateau.largeur)+j==255)
				{	
					break;
				}
				if(str.charAt((i*Plateau.largeur)+j)=='0')
				{
					plateau[i][j]="0";				
				}
				else 
				{					
					if(str.charAt((i*Plateau.largeur)+j)=='/')
					{
						if(i==0)
						{	
							Bordure b = new Bordure();
							b.setSide(Orientation.NORD);
							plateau[i][j]= b ;
						}
						if(i==Plateau.hauteur-1)
						{
							Bordure b = new Bordure();
							b.setSide(Orientation.SOUTH);
							plateau[i][j]= b;
						}
						if(j==0)
						{
							Bordure b = new Bordure();
							b.setSide(Orientation.WEST);
							plateau[i][j]= b;
						}
						if(j==Plateau.largeur-1)
						{
							Bordure b = new Bordure();
							b.setSide(Orientation.EAST);
							plateau[i][j]= b;
						}										
					}
					if(str.charAt((i*Plateau.largeur)+j)=='#')
					{
						plateau[i][j]=new BlocN(i,j);							
					}
					if(str.charAt((i*Plateau.largeur)+j)=='*')
					{
						plateau[i][j]=new BlocSpe(i,j);						
					}
					if(str.charAt((i*Plateau.largeur)+j)=='P')
					{
						plateau[i][j]=new Player(i,j);						
					}	
					if(str.charAt((i*Plateau.largeur)+j)=='E')
					{
						Ennemi en = (Ennemi)plateau[i][j];
						plateau[i][j]=en;
					}	
				}			
			}						
		}
		
	}
	private void initFullBloc(int x , int y) {
		
		for(int i=0 ; i<x ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<y; j++)
			{
				
				if(i==0)
				{					
					Bordure b = new Bordure();
					b.setSide(Orientation.NORD);
					plateau[i][j]= b ;
				}
				if(i==x-1)
				{
					Bordure b = new Bordure();
					b.setSide(Orientation.SOUTH);
					plateau[i][j]= b ;
				}
				if(j==0)
				{
					Bordure b = new Bordure();
					b.setSide(Orientation.WEST);
					plateau[i][j]= b ;
				}
				if(j==y-1)
				{
					Bordure b = new Bordure();
					b.setSide(Orientation.EAST);
					plateau[i][j]= b ;
				}
				if(j!=0&&j!=y-1&&i!=0&&i!=x-1)
				{
					plateau[i][j]= new BlocN(i,j);	
				}				
			}
		}	
		plateau[15][1] = "0";
	}
	
	
	
	
	public void pathInit()
	{							
		for(int i = hauteur-2;i>=1;i=i-2)//on incr�mente de 2
		{		
			for(int j = 1;j<largeur-2;j=j+2)//on incr�mente de 2
			{	
				//on ce place a la premier colonne dernier ligne
					if(plateau[i][j]!=null)
					{
						if(plateau[i][j].getClass()==String.class)
						{
							if(i-2>0&&plateau[i-2][j]!=null) //on verif le nord
							
								if(plateau[i-2][j].getClass()==BlocN.class) //on verif le nord
								{
									pathGeneration(i,j);	
								}
							}
							if(i+2<hauteur&&plateau[i+2][j]!=null)// le sud
							{
								if(plateau[i+2][j].getClass()==BlocN.class) //on verif le nord
								{
									pathGeneration(i,j);
								}
								
							}
							if(j-2>0&&plateau[i][j-2]!=null)//l'west
							{
								if(plateau[i][j-2].getClass()==BlocN.class) //on verif le nord
								{
									pathGeneration(i,j);
								}
							}
							if(j+2<largeur&&plateau[i][j+2]!=null)//l'est
							{
								if(plateau[i][j+2].getClass()==BlocN.class) //on verif le nord
								{
									pathGeneration(i,j);
								}
							}	
						}								
					}		
			//on ce deplace dans la ligne				
			}
			//on ce deplace dans la colonne
		}
		

	
	public void pathGeneration(int x,int y)
	{		
		// on genere 2 chemin au nord sud est ou west 
		int r ;
		boolean test = true;			
		while(test)
		{
			r= new Random().nextInt(4);
			switch(r)
			{
				case 0://test nord
				{
					if(x-2>0&&plateau[x-2][y]!=null)
					{
						if(plateau[x-2][y].getClass()==BlocN.class)
						{
							plateau[x-1][y]="0";
							plateau[x-2][y]="0";
							affichTab(hauteur,largeur);
							test=false;
							pathContinue(x-2,y);								
						}	
					}					
				}break;
				case 1://test sud
				{
					if(x+2<hauteur&&plateau[x+2][y]!=null)
					{
						if(plateau[x+2][y].getClass()==BlocN.class)
						{
							plateau[x+1][y]="0";
							plateau[x+2][y]="0";
							affichTab(hauteur,largeur);
							test=false;
							pathContinue(x+2,y);								
						}	
					}					
				}break;
				case 2://test west
				{
					if(y-2>0&&plateau[x][y-2]!=null)
					{
						if(plateau[x][y-2].getClass()==BlocN.class)
						{
							plateau[x][y-1]="0";
							plateau[x][y-2]="0";
							affichTab(hauteur,largeur);
							test=false;
							pathContinue(x,y-2);									
						}
					}					
				}break;
				case 3://test est
				{
					if(y+2<largeur&&plateau[x][y+2]!=null)
					{
						if(plateau[x][y+2].getClass()==BlocN.class)
						{
							plateau[x][y+1]="0";
							plateau[x][y+2]="0";
							affichTab(hauteur,largeur);
							test=false;
							pathContinue(x,y+2);								
						}
					}					
				}
			}
		}
	}
	public void pathContinue(int x ,int y)
	{
		// on regarde si on peux continuer a generer
		if(x-2>0&&plateau[x-2][y]!=null)
		{
			if(plateau[x-2][y].getClass()==BlocN.class)
			{
				pathGeneration(x,y);
				return;
			}	
		}
		if(x+2<hauteur&&plateau[x+2][y]!=null)
		{
			if(plateau[x+2][y].getClass()==BlocN.class)
			{
				pathGeneration(x,y);
				return;
			}
		}
		if(y-2>0&&plateau[x][y-2]!=null)
		{
			if(plateau[x][y-2].getClass()==BlocN.class)
			{
				pathGeneration(x,y);
				return;
			}
		}
		if(y+2<largeur&&plateau[x][y+2]!=null)
		{
			if(plateau[x][y+2].getClass()==BlocN.class)
			{
				pathGeneration(x,y);
				return;
			}
		}	
		//si on peut pas on cherche in nouveau chemin
		else
		{
			pathInit();
		}
	}
	public static void SwitchBorder(Bordure b)
	{
		switch(b.getSide())
		{
			case NORD ://n
			{
				for(int i = 0;i<largeur;i++)
				{
					Bordure bo = (Bordure)plateau[0][i];
					if(plateau[1][i].getClass()==Ennemi.class)
					{
						Ennemi en = (Ennemi)plateau[1][i];
						en.stun=true;
					}
					bo.setActivate(true);				
				}
			}break;
			case SOUTH ://s
			{
				for(int i = 0;i<largeur;i++)
				{
					Bordure bo = (Bordure)plateau[hauteur-1][i];
					if(plateau[hauteur-2][i].getClass()==Ennemi.class)
					{
						Ennemi en = (Ennemi)plateau[hauteur-2][i];
						en.stun=true;
					}
					bo.setActivate(true);
				}
			}break;
			case EAST ://e
			{
				for(int i = 0;i<hauteur;i++)
				{
					Bordure bo = (Bordure)plateau[i][largeur-1];
					if(plateau[i][largeur-2].getClass()==Ennemi.class)
					{
						Ennemi en = (Ennemi)plateau[i][largeur-2];
						en.stun=true;
					}
					bo.setActivate(true);
				}
			}break;
			case WEST ://w
			{
				for(int i = 0;i<hauteur;i++)
				{
					if(plateau[i][1].getClass()==Ennemi.class)
					{
						Ennemi en = (Ennemi)plateau[i][1];
						en.stun=true;
					}
					Bordure bo = (Bordure)plateau[i][0];
					bo.setActivate(true);
				}
			}break;
			case SO:
			{
				
			}
		}
	}
	public static void stopBordure()
	{
		for(int i=0 ; i<hauteur ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<largeur; j++)
			{					
				if(i==0||i==hauteur-1)
				{
					Bordure b = (Bordure)plateau[i][j];
					b.setActivate(false);
				}
				else if(j==0||j==largeur-1)
				{
					Bordure b = (Bordure)plateau[i][j];
					b.setActivate(false);
				}
							
			}
		}
	}
	public static void refreshEntity(Entity e)
	{
		if(plateau[e.getPosX()][e.getPosY()] != e)
		{
			plateau[e.getPosX()][e.getPosY()] = e;
		}
		else 
		{
			plateau[e.getPosX()][e.getPosY()] = "0";
		}
	}
	public static int getLargeur() {
		return largeur;
	}
	public static int getHauteur() {
		return hauteur;
	}
	public static void clearEntity(boolean b) {
		
	
		for(int i=0 ; i<hauteur ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<largeur; j++)
			{					
				
				if(plateau[i][j].getClass()==Ennemi.class)
				{
					Ennemi en = (Ennemi)plateau[i][j];
					if(Menu.getInstance().getHmThreadE().get(en).isRunning())
					{
						Menu.getInstance().getHmThreadE().get(en).stop();
						Menu.getInstance().getHmThreadE().remove(en);
						plateau[i][j]="0";
						System.out.println("ennemi clear!");
					}
				}
				else if (plateau[i][j].getClass()==Player.class&&b)
				{
					Player p = (Player)plateau[i][j];
					if(Menu.getInstance().getHmThreadP().get(p).isRunning())
					{
						Menu.getInstance().getHmThreadP().get(p).stop();
						Menu.getInstance().getHmThreadP().remove(p);
						plateau[i][j]="0";
						System.out.println("player clear!");
					}
				}
				
			}
		}
		
	}
} 
