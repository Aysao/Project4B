package PackageThreads;
import java.io.*;
import java.net.*;


import PackageClass.BlocN;
import PackageClass.BlocSpe;
import PackageClass.Bordure;
import PackageClass.Ennemi;
import PackageClass.Orientation;
import PackageClass.Plateau;
import PackageClass.Player;


public class Serveur implements Runnable{
	final int port = 8080;
	private ServerSocket s;
	private BufferedReader sisr;
	private PrintWriter sisw;
	private Socket soc;

	private boolean waiting =true;
	public Serveur()
	{	
		
	}  

	       
		
	   
	@Override
	public void run(){
		
		
		Ennemi e = (Ennemi)Plateau.getEnnemi().get(0);

		while (true) 
		{		
			
			
	    }
	}
	
	public void setClient()throws Exception
	{		
			
		System.out.println("en attente...");		
        soc = s.accept();                     
        System.out.println("SOCKET "+s);
        System.out.println("SOCKET "+soc);       
        sisr = new BufferedReader(
                new InputStreamReader(soc.getInputStream())
               );     
        sisw = new PrintWriter( new BufferedWriter(
             new OutputStreamWriter(soc.getOutputStream())),true);      
	}

	public void sendUpdate()
	{
		sisw.println("Update");
		for(int i=0 ; i<Plateau.getHauteur() ; i++) 
		{
			for(int j=0 ; j<Plateau.getLargeur() ; j++)
			{
				
				if(Plateau.plateau[i][j].getClass()==String.class)
				{
					sisw.println("0");//si chemin
				}
				else 
				{					
					if(Plateau.plateau[i][j].getClass()==Bordure.class)
					{
						sisw.println("/"); //si mur
					}
					else if(Plateau.plateau[i][j].getClass()==BlocN.class)
					{						
						sisw.println("#");//si bloc classiqe
					}
					else if(Plateau.plateau[i][j].getClass()==BlocSpe.class)
					{
						sisw.println("*");//si diamant
					}
					else if(Plateau.plateau[i][j].getClass()==Player.class)
					{
						sisw.println("P");//si player
					}	
					else if(Plateau.plateau[i][j].getClass()==Ennemi.class)
					{
						sisw.println("E");//si player
					}
				}			
			}						
		}		
	}
	public boolean isWaiting() {
		return waiting;
	}
	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	
}
