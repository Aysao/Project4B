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
					player.setOrientation(Entity.NORD);
					player.setMouvement(true);
				}
				else if(e.getKeyChar() == Menu.gauche||e.getKeyChar() == (Menu.gauche-'A'+'a'))
				{
					player.setOrientation(Entity.WEST);
					player.setMouvement(true);
					
				}
				else if(e.getKeyChar() == Menu.reculer||e.getKeyChar() == (Menu.reculer-'A'+'a'))
				{
					player.setOrientation(Entity.SOUTH);
					player.setMouvement(true);
					
				}
				else if(e.getKeyChar() == Menu.droite||e.getKeyChar() == (Menu.droite-'A'+'a'))
				{
					player.setOrientation(Entity.EAST);
					player.setMouvement(true);
				
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.setMouvement(false);
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
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(player.getVie() == 0)
			{
				System.out.println("Perdu !");
				stop();
			}
			else if(Menu.v.isVictory())
			{
				System.out.println("Gagné!");
				stop();
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
