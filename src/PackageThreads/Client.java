package PackageThreads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import PackageClass.Orientation;
import PackageClass.Plateau;
import PackageClass.Player;
import PackageRender.JMenuMulti;
import PackageRender.JMulti;

public class Client implements Runnable{

	private int port = 8080;
	private PrintWriter sisw;
	private BufferedReader sisr;
	private Socket socket;
	private String pseudo;
	private JMenuMulti jmm;
	private boolean host;
	private boolean game = true;
	private boolean flag = false;
	private JMulti parent;
	
	public Client(String s,boolean _host,JMulti j)
	{		
		parent = j;
		pseudo = s;
		this.host = _host;
		
		try {
			socket = new Socket("127.0.0.1", port);
		} catch (IOException e) {						
			e.printStackTrace();
		}
		try {
			sisr = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			sisw = new PrintWriter(new BufferedWriter(
			            new OutputStreamWriter(socket.getOutputStream())),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sisw.println(pseudo);
		
		if(host)
		{
			jmm = new JMenuMulti(j,pseudo,host,this);
			jmm.setVisible(true);
			flag = true;
		}
		else
		{
			jmm = new JMenuMulti(j,pseudo,false,this);
			jmm.setVisible(true);
		}
		
		
			

//		sisw.println("start");
//		try {
//			Plateau.StringToPlateau(sisr.readLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void run() {
		Player p1 = null;
		boolean waiting = true;
		String str = "";
		int slash = 0;
		int point = 0;
		String nbPlayer = "0";
		String nbEnnemi = "0";
		String sender = "";
		String action = "";
	
		while(str != null && waiting)
		{
			
			try {
				str = sisr.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				action = "stop";
			}
			if(str != null)
			{
				for(int i = 0;i<str.length();i++)
				{
					if(str.charAt(i) == '/')
					{
						slash = i;
					}
				}
				for(int i = 0;i<str.length();i++)
				{
					if(str.charAt(i) == '.')
					{
						point = i;
					}
				}
			}
			if(slash != 0)
				sender = str.substring(0,slash);
			if(point == 0 && slash != 0)
			{
				action = str.substring(slash+1);
			}
			if(point != 0 && slash != 0)
			{
				action = str.substring(slash+1,point);
			}
			switch(action)
			{
				case "goennemi":
				{
					System.out.println("sender : "+sender);
					System.out.println("action : "+action);
					jmm.goennemi(sender);
				}break;
				case "goplayer":
				{
					jmm.goplayer(sender);
				}break;
				case "coop":
				{
					jmm.setGoPlayer();
					jmm.setBequipe(false);
					jmm.refreshtable();
				}break;
				case "equipe":
				{
					jmm.setBequipe(true);
					jmm.refreshtable();
				}break;
				case "ennemi":
				{
					if(!jmm.isInArray(jmm.getPlayercollection(), sender) && !jmm.isInArray(jmm.getEnnemicollection(), sender))
					{
						jmm.setArray(jmm.getEnnemicollection(), sender);
						jmm.refreshtable();
						nbEnnemi = str.substring(point+1);
					}
					
				}break;
				case "player":
				{
					if(!jmm.isInArray(jmm.getPlayercollection(), sender) && !jmm.isInArray(jmm.getEnnemicollection(), sender))
					{
						jmm.setArray(jmm.getPlayercollection(), sender);
						jmm.refreshtable();
						nbPlayer = str.substring(point+1);
					}
					
				}break;
				case "rien":
				{
					if(!jmm.isInArray(jmm.getPlayercollection(), sender) && !jmm.isInArray(jmm.getEnnemicollection(), sender))
					{
						if(jmm.isInArray(jmm.getPlayercollection(), pseudo))
						{
							sisw.println(pseudo+"/"+"player."+jmm.nbArray(jmm.getPlayercollection()));
						}
						else
						{
							sisw.println(pseudo+"/"+"ennemi."+jmm.nbArray(jmm.getEnnemicollection()));
						}
						if(jmm.nbArray(jmm.getPlayercollection()) <= jmm.nbArray(jmm.getEnnemicollection()))
						{
							jmm.setArray(jmm.getPlayercollection(), sender);
							jmm.refreshtable();
						}
						else
						{
							jmm.setArray(jmm.getEnnemicollection(), sender);
							jmm.refreshtable();
						}
					
						
					}
				}
				case "stop":
				{
					if(sender.equals("host"))
					{
						this.close();
						game = false;
					}
					else
					{
						if(jmm.isInArray(jmm.getEnnemicollection(), sender))
						{
							jmm.removeArray(jmm.getEnnemicollection(), sender);
						}
						else if(jmm.isInArray(jmm.getPlayercollection(), sender))
						{
							jmm.removeArray(jmm.getPlayercollection(), sender);
						}
					}
				}
			}
			slash = 0;
			point = 0;
			System.out.println("nbplayer : "+nbPlayer);
			System.out.println("nbennemi : "+nbEnnemi);
			System.out.println("nbplayer thread courant : "+jmm.nbArray(jmm.getPlayercollection()));
			System.out.println("nbennemi thread courant : "+jmm.nbArray(jmm.getEnnemicollection()));
			if(flag == false && nbPlayer.equals(""+jmm.nbArray(jmm.getPlayercollection())) && nbEnnemi.equals(""+jmm.nbArray(jmm.getEnnemicollection())))
			{
				System.out.println("je me crée dans ma liste");
				if(jmm.nbArray(jmm.getPlayercollection()) <= jmm.nbArray(jmm.getEnnemicollection()))
				{
					jmm.setArray(jmm.getPlayercollection(), pseudo);
					jmm.refreshtable();
				}
				else
				{
					jmm.setArray(jmm.getEnnemicollection(), pseudo);
					jmm.refreshtable();
				}
				flag = true;
			
			}
			if(action.equals("stop") && sender.equals(pseudo) && host)
			{
				parent.getServ().close();
				waiting = false;
			}
			if(str.equals("Demarrer"))
			{
				waiting = false;
			}
		}
		
		if(game)
		{
			Menu m = new Menu();
			if(host)
			{
				m.setHost(host);
				m.startMulti(pseudo,this,jmm.getEnnemicollection(),jmm.getPlayercollection());
			}
			else
			{
				m.startMultiClient(pseudo,this,jmm.getEnnemicollection(),jmm.getPlayercollection());
			}
			System.out.println("test 3");
		}
		
		while(game)
		{
			
			str = "";
			try {
				str = sisr.readLine();// lecture du message
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch(str)
			{
			case "NORD":
			{
				p1.setOrientation(Orientation.NORD);
				p1.setMouvement(true);
				p1.Deplacement();
			}break;
			
			case "SOUTH":
			{
				p1.setOrientation(Orientation.SOUTH);
				p1.setMouvement(true);
				p1.Deplacement();
			}break;
			case "EAST":
			{
				p1.setOrientation(Orientation.EAST);
				p1.setMouvement(true);
				p1.Deplacement();
			}break;
			case "WEST":
			{
				p1.setOrientation(Orientation.WEST);
				p1.setMouvement(true);
				p1.Deplacement();
			}break;
			case "SO":
			{
				p1.setMouvement(false);
			}break;
			case "bordure":
			{
				Plateau.stopBordure();
			}break;		
			case "getplayer":
			{
				p1 = (Player)Plateau.getPlayer().get(0);
			}break;
			case "new location":
			{			
				boolean x =false;
				boolean y = false;
				while(!y)
				{
					String s="";
					try {
						s = sisr.readLine();// lecture du message
						//System.out.println(str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(!s.isEmpty()&&!s.isBlank())
					{
						if(!x)
						{
							System.out.println(s);
							Menu.getInstance().getP2().setPosX(Integer.parseInt(s));
							x=true;
							
						}
						else
						{
							System.out.println(s);
							Menu.getInstance().getP2().setPosY(Integer.parseInt(s));
							y=true;
						}						
					}							
				}
				Plateau.refreshEntity(Menu.getInstance().getP2());
							
			}break;
			case "win":
			{
				Menu.getInstance().getV().setVictory(true);
				Menu.getInstance().fin();
				close();				
			}break;
			case "loose":
			{
				Menu.getInstance().getV().setVictory(false);
				Menu.getInstance().fin();
				close();				
			}break;
			}
			if(str.matches("win") ||str.matches("loose"))
			{
				break;
			}
		}
	}
	
	private void close() {
		try {
			sisr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sisw.close();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void sendLine(String str)
	{
		sisw.println(str);
	}
	
	public void setGame(boolean game) {
		this.game = game;
	}
	
	public BufferedReader getSisr() {
		return sisr;
	}
	

	

	
	
}
