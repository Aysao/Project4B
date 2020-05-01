package PackageThreads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import PackageClass.*;

public class ThreadPlayer extends Thread {
	private Entity player;
	private boolean mouvement;
	private int framDelay;
	
	public ThreadPlayer(Entity e) {
		player = e;
	}
	
	public void run()
	{
		try
		{
			while(mouvement && player.getOrientation() != Entity.SO)
			{
				player.Deplacement();
				this.sleep(framDelay);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				this.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	} 
	
	KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT)
			{
				player.setOrientation(Entity.WEST);
			}
			else if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT)
			{
				player.setOrientation(Entity.EAST);
			}
			else if(e.getExtendedKeyCode() == KeyEvent.VK_UP)
			{
				player.setOrientation(Entity.NORD);
			}
			else if(e.getExtendedKeyCode() == KeyEvent.VK_DOWN)
			{
				player.setOrientation(Entity.SOUTH);
			}
			mouvement = true;
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			mouvement = false;
			
		}
		
	};
		
	
}
