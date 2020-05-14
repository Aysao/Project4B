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
			
			String str="";
			try {
				str = sisr.readLine();// lecture du message
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			if(str.equals("start"))
			{
				sisw.println(Plateau.PlateauToString());
			}
			if (str.equals("END")) 
			{
				close();
				break;	
			}	
			switch(str)
			{
			case "NORD":
			{
				e.setOrientation(Orientation.NORD);
				e.setMouvement(true);
				e.Deplacement();
			}break;
			
			case "SOUTH":
			{
				e.setOrientation(Orientation.SOUTH);
				e.setMouvement(true);
				e.Deplacement();
			}break;
			case "EAST":
			{
				e.setOrientation(Orientation.EAST);
				e.setMouvement(true);
				e.Deplacement();
			}break;
			case "WEST":
			{
				e.setOrientation(Orientation.WEST);
				e.setMouvement(true);
				e.Deplacement();
			}break;
			case "SO":
			{
				e.setMouvement(false);
			}break;
			case"stunoff":
			{
				e.stun=false;
			}break;
			case"END":
			{
				close();
				break;				
			}
			}			
			System.out.println("ECHOlocal = " + str);   // trace locale			
			sisw.println("ECHO = "+str);// renvoi d'un echo
	    }
	}
	public void sendLine(String str)
	{
		sisw.println(str);
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
	public void close()
	{
		 try {
			sisr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     sisw.close();
	     try {
			soc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
