package PackageThreads;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import PackageClass.Ennemi;
import PackageClass.Orientation;
import PackageClass.Plateau;

public class ServeurMC implements Runnable{
   private int port = 8080;
   private final int maxClients=8;
   private ArrayList<PrintWriter> pw = new ArrayList<PrintWriter>();
   private int numClient=0;
   public ServeurMC() throws Exception  
   {

   }
	@Override
	public void run() {
		
		ServerSocket s = null;
		try {
			s = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SOCKET ECOUTE CREE => "+s);
		//virer le while
		while (numClient<maxClients)
		{
			Socket soc=null;
			try {
				soc = s.accept();
				pw.add(new PrintWriter( new BufferedWriter(
	                     new OutputStreamWriter(soc.getOutputStream())),true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CoClient cc=new CoClient(pw,soc,numClient);
			System.out.println("NOUVELLE CONNEXION - SOCKET =>"+soc);
			numClient++;
			Thread thc = new Thread(cc);
			thc.start();
		}
		
	}
	public ArrayList<PrintWriter> getPw() {
		return pw;
	}
	public void setPw(ArrayList<PrintWriter> pw) {
		this.pw = pw;
	}

   
}
  
 	

