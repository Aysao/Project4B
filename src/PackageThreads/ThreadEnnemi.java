package PackageThreads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;


import PackageClass.BlocN;
import PackageClass.BlocSpe;
import PackageClass.Ennemi;
import PackageClass.Orientation;
import PackageClass.Plateau;
import PackageClass.Player;


public class ThreadEnnemi implements Runnable {
	private Ennemi en;
	private boolean running = true;
	private int lastX = 0;
	private int lastY = 0;
	
	
	
	private KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {		
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(en.isMouvement() == false)
			{
				if(e.getKeyChar() == Menu.getInstance().getAvancer()||e.getKeyChar() == (Menu.getInstance().getAvancer()-'A'+'a'))
				{
					en.setOrientation(Orientation.NORD);
					en.setMouvement(true);									
				}
				else if(e.getKeyChar() == Menu.getInstance().getGauche()||e.getKeyChar() == (Menu.getInstance().getGauche()-'A'+'a'))
				{
					en.setOrientation(Orientation.WEST);
					en.setMouvement(true);					
					
				}
				else if(e.getKeyChar() == Menu.getInstance().getReculer()||e.getKeyChar() == (Menu.getInstance().getReculer()-'A'+'a'))
				{
					en.setOrientation(Orientation.SOUTH);
					en.setMouvement(true);				
				}
				else if(e.getKeyChar() == Menu.getInstance().getDroite()||e.getKeyChar() == (Menu.getInstance().getDroite()-'A'+'a'))
				{
					en.setOrientation(Orientation.EAST);
					en.setMouvement(true);							
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			en.setMouvement(false);
			if(!Menu.getInstance().isHost())
			{
				Menu.getInstance().getC().sendLine("SO");
			}
			//player.setOrientation(Entity.SO);
		}		
	};
	
	public ThreadEnnemi(Ennemi e1) {
		System.out.println("Ennemie set");
		en=e1;
		running = true;
	}
	
	public Ennemi getEn() {
		return en;
	}

	public void setEn(Ennemi en) {
		this.en = en;
	}

	@Override
	public void run() 
	{
		if(this.en.isPlayed()&&Menu.getInstance().getMode()==2)//multi
		{	
			while(running)
			{
				if(en.stun)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Menu.getInstance().getC().sendLine("stunoff");
					en.stun=false;				
				}	
				if(en.isMouvement())
				{					
					en.Deplacement();
					Menu.getInstance().getC().sendLine(en.getOrientation().toString());					
					//en.setOrientation(Entity.SO);						
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
				
				int pX = en.getPathX();	
				int pY = en.getPathY();	
				int x= en.getPosX();
				int y = en.getPosY();

				if(en.stun)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					en.stun=false;				
				}			
				else 
				{

					if(lastX==x && lastY==y && en.isNearPlayer())
					{
						System.out.println("trouver player");
						Player sp = en.nearPlayer();
						en.newPointNearPlayer(sp.getPosX(), sp.getPosY());
					}
					else if(lastX==x&&lastY==y)
					{
						System.out.println("looser");
						BlocSpe sp = en.nearBlocSpe();
						en.newPointNearBloc(sp.getPosX(), sp.getPosY());
					}
//					if(pX==x && pY==y)
//					{
//						System.out.println("new pt!!!");
//						en.newPoint();
//					}
					if(pX==x && pY==y && en.isNearPlayer())
					{
						System.out.println("trouver player");
						Player sp = en.nearPlayer();
						en.newPointNearPlayer(sp.getPosX(), sp.getPosY());
					}
					else if(pX==x && pY==y)
					{
						System.out.println("new point near diamond");
						BlocSpe sp = en.nearBlocSpe();
						en.newPointNearBloc(sp.getPosX(), sp.getPosY());
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
					e.printStackTrace();
				}			
			}
		}
		
	}
	private void goNS() {
		int pX = en.getPathX();		
		int x= en.getPosX();
		int y = en.getPosY();
		
		if((pX-x)<0)
		{
			
			if(Plateau.plateau[x-1][y].getClass()==BlocN.class)
			{				
					en.setOrientation(Orientation.NORD);
					en.setMouvement(true);														
			}
			else if(Plateau.plateau[x-1][y].getClass()==String.class||Plateau.plateau[x-1][y].getClass()==Player.class)
			{
				en.setOrientation(Orientation.NORD);
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
					en.setOrientation(Orientation.SOUTH);
					en.setMouvement(true);												
			}
			else if(Plateau.plateau[x+1][y].getClass()==String.class||Plateau.plateau[x+1][y].getClass()==Player.class)
			{
				en.setOrientation(Orientation.SOUTH);
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
		int pY = en.getPathY();
		int x= en.getPosX();
		int y = en.getPosY();		
		if((pY-y)<0)
		{
			
			if(Plateau.plateau[x][y-1].getClass()==BlocN.class)
			{
					en.setOrientation(Orientation.WEST);
					en.setMouvement(true);									
			}
			else if(Plateau.plateau[x][y-1].getClass()==String.class||Plateau.plateau[x][y-1].getClass()==Player.class)
			{
				en.setOrientation(Orientation.WEST);
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
					en.setOrientation(Orientation.EAST);
					en.setMouvement(true);									
			}
			else if(Plateau.plateau[x][y+1].getClass()==String.class||Plateau.plateau[x][y+1].getClass()==Player.class)
			{
				
				en.setOrientation(Orientation.EAST);
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
	
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
}
