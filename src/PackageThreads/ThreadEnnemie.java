package PackageThreads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import PackageClass.*;
import PackageClass.Entity;
import PackageClass.Menu;

public class ThreadEnnemie implements Runnable{
	
	private Ennemie enemie;
	private KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(enemie.isMouvement() == false)
			{
				if(e.getKeyChar() == 'z'||e.getKeyChar() == 'Z')
				{
					enemie.setOrientation(Entity.NORD);
					enemie.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 'q'||e.getKeyChar() == 'Q')
				{
					enemie.setOrientation(Entity.WEST);
					enemie.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 's'||e.getKeyChar() == 'S')
				{
					enemie.setOrientation(Entity.SOUTH);
					enemie.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 'd'||e.getKeyChar() == 'D')
				{
					enemie.setOrientation(Entity.EAST);
					enemie.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			enemie.setMouvement(false);
			enemie.setOrientation(Entity.SO);
		}
		
	};
	
	
	@Override
	public void run() {
		//while()
	}
	
	
}
