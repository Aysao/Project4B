package PackageThreads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import PackageClass.*;

public class ThreadPlayer implements Runnable {
	
	private Player player;
	private boolean running = true;
	
	private KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {		
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(player.isMouvement() == false)
			{
				if(e.getKeyChar() == Menu.avancer||e.getKeyChar() == (Menu.avancer-'A'+'a'))
				{
					player.setOrientation(Orientation.NORD);
					player.setMouvement(true);									
				}
				else if(e.getKeyChar() == Menu.gauche||e.getKeyChar() == (Menu.gauche-'A'+'a'))
				{
					player.setOrientation(Orientation.WEST);
					player.setMouvement(true);					
					
				}
				else if(e.getKeyChar() == Menu.reculer||e.getKeyChar() == (Menu.reculer-'A'+'a'))
				{
					player.setOrientation(Orientation.SOUTH);
					player.setMouvement(true);				
				}
				else if(e.getKeyChar() == Menu.droite||e.getKeyChar() == (Menu.droite-'A'+'a'))
				{
					player.setOrientation(Orientation.EAST);
					player.setMouvement(true);							
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.setMouvement(false);	
			if(Menu.host&&Menu.mode==2)
			{
				Menu.s.sendLine("SO");
			}
			//player.setOrientation(Entity.SO);
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
		while(running)
		{
			
			if(!Menu.v.isVictory() && player.getVie() > 0)
			{
				
				if(player.isMouvement())
				{				
					player.Deplacement();
					Menu.v.testVictory();
					if(Menu.host&&Menu.mode==2)
					{
						Menu.s.sendLine(player.getOrientation().toString());
					}
					try {
						Thread.sleep(180);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
		
	
}
