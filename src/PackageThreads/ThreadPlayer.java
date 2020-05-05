package PackageThreads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import PackageClass.*;

public class ThreadPlayer implements Runnable {
	
	private Player player;
	private int framDelay;
	private boolean running = true;
	
	private KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(player.isMouvement() == false)
			{
				if(e.getKeyChar() == 'z'||e.getKeyChar() == 'Z')
				{
					player.setOrientation(Entity.NORD);
					player.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 'q'||e.getKeyChar() == 'Q')
				{
					player.setOrientation(Entity.WEST);
					player.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 's'||e.getKeyChar() == 'S')
				{
					player.setOrientation(Entity.SOUTH);
					player.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
				else if(e.getKeyChar() == 'd'||e.getKeyChar() == 'D')
				{
					player.setOrientation(Entity.EAST);
					player.setMouvement(true);
					//Deplacement();
					Menu.v.testVictory();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.setMouvement(false);
			player.setOrientation(Entity.SO);
		}
		
	};
	
	public ThreadPlayer(Player e) {
		player = e;
		System.out.println("Player set");
		running = true;
	}
	
	@Override
	public void run()
	{
		while(running == true)
		{
			if(player.isMouvement())
			{
				player.Deplacement();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public KeyListener getKl() {
		return kl;
	}
	public void stop()
	{
		running = false;
	}
		
	
}
