package PackageThreads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import PackageClass.Orientation;
import PackageClass.Plateau;
import PackageClass.Player;

public class Client implements Runnable{
	private boolean running= true;
	private int port = 8080;
	private PrintWriter sisw;
	private BufferedReader sisr;
	private Socket socket;
	public Client()
	{
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
		sisw.println("start");
		try {
			Plateau.StringToPlateau(sisr.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		Player p1 = null;
		while(running)
		{
			
			String str="";
			try {
				str = sisr.readLine();// lecture du message
				//System.out.println(str);
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
			case"stop":
			{
				stop();
			}
			case "getplayer":
			{
				p1 = (Player)Plateau.getPlayer().get(0);
			}
			}	
		}
	}
	public void sendLine(String str)
	{
		sisw.println(str);
	} 
	public void stop()
	{
		this.running=false;
	}

	
	
}
