package PackageThreads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


import PackageClass.BlocN;
import PackageClass.Ennemi;
import PackageClass.Entity;
import PackageClass.Plateau;
import PackageClass.Player;


public class ThreadEnnemie implements Runnable {
	private Ennemi en;
	private boolean running = true;
	private int lastX;
	private int lastY;
	
	
	
	private KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {		
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(en.isMouvement() == false)
			{
				if(e.getKeyChar() == Menu.avancer||e.getKeyChar() == (Menu.avancer-'A'+'a'))
				{
					en.setOrientation(Entity.NORD);
					en.setMouvement(true);									
				}
				else if(e.getKeyChar() == Menu.gauche||e.getKeyChar() == (Menu.gauche-'A'+'a'))
				{
					en.setOrientation(Entity.WEST);
					en.setMouvement(true);					
					
				}
				else if(e.getKeyChar() == Menu.reculer||e.getKeyChar() == (Menu.reculer-'A'+'a'))
				{
					en.setOrientation(Entity.SOUTH);
					en.setMouvement(true);				
				}
				else if(e.getKeyChar() == Menu.droite||e.getKeyChar() == (Menu.droite-'A'+'a'))
				{
					en.setOrientation(Entity.EAST);
					en.setMouvement(true);							
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			en.setMouvement(false);
			if(!Menu.host)
			{
				Menu.c.sendLine("SO");
			}
			//player.setOrientation(Entity.SO);
		}		
	};
	
	public ThreadEnnemie(Ennemi e1) {
		System.out.println("Ennemie set");
		en=e1;
		running = true;
	}
	
	@Override
	public void run() 
	{
		if(!Menu.host&&this.en.isPlayed()&&Menu.mode==2)//multi
		{	
			while(running)
			{
				if(en.stun)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Menu.c.sendLine("stunoff");
					en.stun=false;				
				}	
				if(en.isMouvement())
				{					
					en.Deplacement();
					Menu.c.sendLine(Integer.toString(en.getOrientation()));					
					//en.setOrientation(Entity.SO);						
					try {
						Thread.sleep(180);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
							
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					
		}
		else
		{
			try {
				
				Thread.sleep((new Random()).nextInt(500)+500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(running )
			{
				
				int pX = en.pathX;	
				int pY = en.pathY;	
				int x= en.getPosX();
				int y = en.getPosY();
//				System.out.println("x :"+x);
//				System.out.println("y :"+y);
//				System.out.println("lastX :"+lastX);
//				System.out.println("lastY :"+lastY);
				if(en.stun)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					en.stun=false;				
				}			
				else 
				{
					if(lastX==x&&lastY==y)
					{
						System.out.println("looser");
						en.newPoint();
					}
					if(pX==x && pY==y)
					{
						System.out.println("new pt!!!");
						en.newPoint();
					}
					else
					{
						goNS();	
					}	
					lastX=x;
					lastY=y;
					if(en.isMouvement())
					{
						
						en.Deplacement();
						//en.setOrientation(Entity.SO);
						en.setMouvement(false);					
					}
				}

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
		
	}
	private void goNS() {
		int pX = en.pathX;		
		int x= en.getPosX();
		int y = en.getPosY();
		
		if((pX-x)<0)
		{
			
			if(Plateau.plateau[x-1][y].getClass()==BlocN.class)
			{				
					en.setOrientation(Entity.NORD);
					en.setMouvement(true);														
			}
			else if(Plateau.plateau[x-1][y].getClass()==String.class||Plateau.plateau[x-1][y].getClass()==Player.class)
			{
				en.setOrientation(Entity.NORD);
				en.setMouvement(true);	
			}
			else
			{
				goEW();
			}
		}
		else if(((pX-x)>0))
		{
			if(Plateau.plateau[x+1][y].getClass()==BlocN.class)
			{				
					en.setOrientation(Entity.SOUTH);
					en.setMouvement(true);												
			}
			else if(Plateau.plateau[x+1][y].getClass()==String.class||Plateau.plateau[x+1][y].getClass()==Player.class)
			{
				en.setOrientation(Entity.SOUTH);
				en.setMouvement(true);					
			}
			else
			{
				goEW();
			}
		}
		else if((pX-x)==0)
		{
			goEW();
		}	
		
	}
	private void goEW() {
		int pY = en.pathY;
		int x= en.getPosX();
		int y = en.getPosY();		
		if((pY-y)<0)
		{
			
			if(Plateau.plateau[x][y-1].getClass()==BlocN.class)
			{
					en.setOrientation(Entity.WEST);
					en.setMouvement(true);									
			}
			else if(Plateau.plateau[x][y-1].getClass()==String.class||Plateau.plateau[x][y-1].getClass()==Player.class)
			{
				en.setOrientation(Entity.WEST);
				en.setMouvement(true);					
			}
			else
			{
			//	en.setOrientation(Entity.SO);
			}
		}
		else if(((pY-y)>0))
		{
			
			if(Plateau.plateau[x][y+1].getClass()==BlocN.class)
			{		
					en.setOrientation(Entity.EAST);
					en.setMouvement(true);									
			}
			else if(Plateau.plateau[x][y+1].getClass()==String.class||Plateau.plateau[x][y+1].getClass()==Player.class)
			{
				
				en.setOrientation(Entity.EAST);
				en.setMouvement(true);					
			}
			else
			{
			//	en.setOrientation(Entity.SO);
			}
		}					
	}	
	
	
	public void stop()
	{
		running = false;
	}
	
	public KeyListener getKl() {
		return kl;
	}
}
