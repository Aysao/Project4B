package PackageThreads;
import java.io.*;
import java.net.*;

import PackageClass.BlocN;
import PackageClass.BlocSpe;
import PackageClass.Bordure;
import PackageClass.Ennemi;
import PackageClass.Entity;
import PackageClass.Plateau;
import PackageClass.Player;


public class Serveur implements Runnable{
	static final int port = 8080;
	private ServerSocket s;
	private BufferedReader sisr;
	private PrintWriter sisw;
	private Socket soc;
	
	public Serveur()
	{		
	
	}
	@Override
	public void run(){
		
		Ennemi e = (Ennemi)Plateau.getEnnemi().get(0);
		try {
			setPlayer();
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
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
			case "1":
			{
				e.setOrientation(Entity.NORD);
				e.setMouvement(true);
				e.Deplacement();
			}break;
			
			case "2":
			{
				e.setOrientation(Entity.SOUTH);
				e.setMouvement(true);
				e.Deplacement();
			}break;
			case "3":
			{
				e.setOrientation(Entity.EAST);
				e.setMouvement(true);
				e.Deplacement();
			}break;
			case "4":
			{
				e.setOrientation(Entity.WEST);
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
			}
			}			
			System.out.println("ECHO = " + str);   // trace locale			
			sisw.println("ECHO = "+str);// renvoi d'un echo
	    }
	}
	public void sendLine(String str)
	{
		sisw.println(str);
	}
	public void setPlayer()throws Exception
	{		
		try {
			s = new ServerSocket(port);
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		System.out.println("en attente...");
        soc = s.accept();
        Menu.gamestart=true;
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
}
