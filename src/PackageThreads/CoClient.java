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
	 private String pseudo;

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
		try {
			pseudo = sisr.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
	 
	  public void run()
	  {
	     try
	     {
	    	for(int i = 0;i<lstpw.size();i++)
	 		{
	 			if(i != id)
	 			{
	 				lstpw.get(i).println(pseudo+"/"+"rien");
	 			}
	 		}
		     while (waiting) 
		     {
				String str = sisr.readLine(); 
				
				if(str.equals("Demarrer"))//bouton quitter
				{
					for(int i=0; i<lstpw.size(); i++)
					{		
						lstpw.get(i).println(str);
					}
					this.stop();
				}
				else if(str.equals("stop"))//bouton quitter
				{
					
					if(id == 0)
					{
						for(int i = 0 ; i < lstpw.size();i++)
						{
							lstpw.get(i).println("host"+"/"+str);
							lstpw.get(i).close();
						}
						this.stop();
					}
					else {
						for(int i=0; i<lstpw.size(); i++)
						{
								lstpw.get(i).println(pseudo+"/"+str);
						}	
						
					}
				}
				else if(!isAction(str))
				{
					for(int i=0; i<lstpw.size(); i++)
					{
						if(i != id)
						{
							
							lstpw.get(i).println(str);
							
						}
					
					}	
				}
				else
				{
					for(int i=0; i<lstpw.size(); i++)
					{
						if(i != id)
						{
							lstpw.get(i).println(pseudo+"/"+str);
						}
					
					}	
				}
		     }
		        sisr.close();
		        s.close();
		   }catch(IOException e){e.printStackTrace();}
	  }

	
	public boolean isAction(String s)
	{
		boolean res = false;
		for(int i = 0;i < s.length();i++)
		{
			if(s.equals("goennemi"))
			{
				res = true;
			}
			if(s.equals("goplayer"))
			{
				res = true;
			}
			if(s.equals("coop"))
			{
				res = true;
			}
			if(s.equals("equipe"))
			{
				res = true;
			}
				
		}
		return res;
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
