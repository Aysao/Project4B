package PackageClass;


import java.util.ArrayList;
import java.util.List;
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
		List<BlocN> lst = Plateau.getBlocN();
		for(int i = 0; i <lst.size();i++)
		{
			int x = lst.get(i).getPosX();
			int y = lst.get(i).getPosY();
			if((x>3&&x<14)||(y>3&&y<12))
			{
				lst.remove(i);
				i--;
			}
		}
		if(lst.size()>0)
		{
			int r= new Random().nextInt(lst.size());													
			Plateau.plateau[lst.get(r).getPosX()][lst.get(r).getPosY()]=this;	
			posX=lst.get(r).getPosX();
			posY=lst.get(r).getPosY();
		}
		else
		{		
			List<Position> lsts = new ArrayList<Position>();
			for(int i = 1;i<16;i++)
			{
				for(int j = 1;j<14;j++)
				{
					if(Plateau.plateau[i][j].getClass() == String.class)
					{
						if((i>3&&i<14)||(j>3&&j<12))
						{
							
						}
						else
						{
							lsts.add(new Position(i,j));
						}
					}
				}
			}
			int r= new Random().nextInt(lsts.size());
			Plateau.plateau[lsts.get(r).getCoX()][lsts.get(r).getCoY()]=this;
			posX=lsts.get(r).getCoX();
			posY=lsts.get(r).getCoY();
		}
		
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

	
	
	
	 

