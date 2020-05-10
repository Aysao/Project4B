package PackageThreads;

import java.util.Random;


import PackageClass.BlocN;
import PackageClass.Ennemi;
import PackageClass.Entity;
import PackageClass.Plateau;
import PackageClass.Player;


public class ThreadEnnemie implements Runnable {
	private Ennemi en;
	private boolean running = true;
	public ThreadEnnemie(Ennemi e1) {
		System.out.println("Ennemie set");
		en=e1;
		running = true;
	}
	@Override
	public void run() 
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
				if(pX==x && pY==y)
				{
					System.out.println("path FIND!!!");
					en.newPoint();
				}
				else
				{
					goNS();	
				}	
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
	private void goNS() {
		int pX = en.pathX;		
		int x= en.getPosX();
		int y = en.getPosY();
		
		if((pX-x)<0)
		{
			
			if(Plateau.plateau[x-1][y].getClass()==BlocN.class)
			{
				if((new Random()).nextInt(100)>70)
				{
					en.setOrientation(Entity.NORD);
					en.setMouvement(true);	
				}
				else
				{
					goEW();	
				}
					
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
				if((new Random()).nextInt(100)>70)
				{
					en.setOrientation(Entity.SOUTH);
					en.setMouvement(true);					
				}
				else
				{
					goEW();
				}
				
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
}
