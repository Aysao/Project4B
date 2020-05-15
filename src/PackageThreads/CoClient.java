package PackageThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import PackageClass.Orientation;
import PackageClass.Plateau;

public class CoClient implements Runnable{

	 private boolean waiting = true;
	 private boolean arret=false;
	 private Socket s;
	 private BufferedReader sisr;
	 private PrintWriter sisw;
	 private boolean inGame = true;
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
					waiting = false;
					//this.stop();
				}
				else if(str.equals("stop"))//bouton quitter
				{
					
					if(id == 0)
					{
						for(int i = 1 ; i < lstpw.size();i++)
						{
							lstpw.get(i).println("host"+"/"+str);
							lstpw.get(i).close();
						}
						inGame = false;
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
		     
		     if(id == 0) // c'est l'host (le premier connecter)
		     {
			     String plateau = sisr.readLine();
			     for(int i = 1;i<lstpw.size();i++)
			     {
			    	 lstpw.get(i).println(plateau);
			     }
			     for(int i = 0;i<lstpw.size()+1;i++)
			     {
			    	 String str = sisr.readLine();
			    	 System.out.println(""+ str);
			    	 for(int j = 1;j<lstpw.size();j++)
			    	 {
			    		 System.out.println(""+ str);
			    		 lstpw.get(j).println(str);
			    	 }
			     }
		     }
		     while(inGame)
		     {
		    	 System.out.println("on rentre ingame");
		    	 String str="";
					try {
						str = sisr.readLine();// lecture du message
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("str coclien : "+str);
					if (str.equals("END")) 
					{
						close();
						break;	
					}	
					switch(str)
					{
					case "NORD":
					{
						for(int i = 0;i<lstpw.size();i++)
						{
							if(id != i)
							{
								lstpw.get(i).println(pseudo+"/"+str);
							}
						}
					}break;
					
					case "SOUTH":
					{
						for(int i = 0;i<lstpw.size();i++)
						{
							if(id != i)
							{
								lstpw.get(i).println(pseudo+"/"+str);
							}
						}
					}break;
					case "EAST":
					{
						for(int i = 0;i<lstpw.size();i++)
						{
							if(id != i)
							{
								lstpw.get(i).println(pseudo+"/"+str);
							}
						}
					}break;
					case "WEST":
					{
						for(int i = 0;i<lstpw.size();i++)
						{
							if(id != i)
							{
								lstpw.get(i).println(pseudo+"/"+str);
							}
						}
					}break;
					case "SO":
					{
						for(int i = 0;i<lstpw.size();i++)
						{
							if(id != i)
							{
								lstpw.get(i).println(pseudo+"/"+str);
							}
						}
					}break;
					case"stunoff":
					{
						for(int i = 0;i<lstpw.size();i++)
						{
							if(id != i)
							{
								lstpw.get(i).println(pseudo+"/"+str);
							}
						}
					}break;
					case"bordure":
					{
						for(int i = 0;i<lstpw.size();i++)
						{
							if(id != i)
							{
								lstpw.get(i).println(pseudo+"/"+str);
							}
						}
					}break;
					case"END":
					{
						close();
						break;				
					}
					}			
					//System.out.println("ECHOlocal = " + str);
		     }
		        close();
		   }catch(IOException e){e.printStackTrace();}
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
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean isAction(String s)
	{
		boolean res = false;
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
		return res;
	}

	public void sendLine(String str)
	{
		sisw.println(str);
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
