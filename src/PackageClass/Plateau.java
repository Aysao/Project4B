package PackageClass;

import java.util.Random;

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
		pathInit(17,1);
		affichTab(hauteur,largeur); 
	}
	private void affichTab(int x , int y) 
	{
		
		for(int i=0 ; i<x ; i++) 
		{
			for(int j=0 ; j<y ; j++)
			{
				if(plateau[i][j].getClass()==String.class)
				{
					System.out.print("0");//si chemin
				}
				else 
				{					
					if(plateau[i][j].getClass()==Bordure.class)
					{
						System.out.print("/"); //si mur
					}
					else
					{
						if(plateau[i][j].getClass()==BlocN.class)
						{
							System.out.print("#");//si bloc classiqe
						}
						if(plateau[i][j].getClass()==BlocSpe.class)
						{
							System.out.print("*");//si diamant
						}
					}									
				}			
			}
			
			System.out.println("");
		}
	}
	private void initFullBloc(int x , int y) {
		
		for(int i=0 ; i<x ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<y; j++)
			{
				
				if(i==0||i==x-1)
				{
					plateau[i][j]= new Bordure();
				}
				if(j==0||j==y-1)
				{
					plateau[i][j]= new Bordure();
				}
				if(j!=0&&j!=y-1&&i!=0&&i!=x-1)
				{
					plateau[i][j]= new BlocN(i,j);	
				}
				
			}
		}	
		plateau[15][1] = "0";
	}
	
	
	
	
	public void pathInit(int x, int y)
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
							if(j+2>largeur&&plateau[i][j+2]!=null)//l'est
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
				r= new Random().nextInt(4);;
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
		if(x-2>0)
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
			pathInit(17,1);
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
			plateau[e.getPosX()][e.getPosY()] = null;
		}
	}
	public static int getLargeur() {
		return largeur;
	}
	public static int getHauteur() {
		return hauteur;
	}

} 
