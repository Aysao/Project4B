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
	private long lastTime = System.nanoTime();
	private long now ;
	private long second = 1000000000;
	private KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			now = System.nanoTime();
			if(now - lastTime >= second/15)
			{
			
				if(e.getKeyChar() == 'z'||e.getKeyChar() == 'Z')
				{
					setOrientation(NORD);
					Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 'q'||e.getKeyChar() == 'Q')
				{
					setOrientation(WEST);
					Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 's'||e.getKeyChar() == 'S')
				{
					setOrientation(SOUTH);
					Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 'd'||e.getKeyChar() == 'D')
				{
					setOrientation(EAST);
					Deplacement();
					Menu.v.testVictory();
				}
				
				
				lastTime = now;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			now = 0;
			lastTime = now;
		}
		
	};
	
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
	public abstract void Deplacement();
	
	public KeyListener getKl() {
		return kl;
	}
	public void setMouvement(boolean mouvement) {
		this.mouvement = mouvement;
	}
	public boolean isMouvement() {
		return mouvement;
	}
		
}

	
	
	
	 

