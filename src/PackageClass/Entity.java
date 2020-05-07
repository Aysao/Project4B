package PackageClass;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	
	
	
	 

