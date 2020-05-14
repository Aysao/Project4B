package PackageThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class CoClient implements Runnable{

	 private boolean waiting = true;
	 private boolean arret=false;
	 private Socket s;
	 private BufferedReader sisr;
	 private PrintWriter sisw;
	 private ArrayList<PrintWriter> lstpw ;
	 private int id;

	 public CoClient( ArrayList<PrintWriter>pw,Socket s,int id)
	 {
		lstpw = pw;
		this.s=s;
		this.setId(id);
		try
		{
			sisr = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		    		
	 }
	 
	  public void run()
	  {
	     try
	     {
	     while (waiting) 
	     {
			String str = sisr.readLine();   
			if(str.matches("stop"))//bouton quitter
			{
				for(int i=0; i<lstpw.size(); i++)
				{				
					lstpw.get(i).println(id+"stop");
				}	
				this.stop();
			}
			for(int i=0; i<lstpw.size(); i++)
			{
			
			
			}	
	     }
	        sisr.close();
	        sisw.close();
	        s.close();
	    }catch(IOException e){e.printStackTrace();}
	  }

	public boolean isArret() {
		return arret;
	}

	public void setArret(boolean arret) {
		this.arret = arret;
	}
	public void stop(){
		waiting = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
