package PackageClass;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import PackageRender.Render;
import PackageThreads.ThreadPlayer;

public class Menu implements Runnable {
	public static Player p1;
	public static Entity p2;
	public static int ennemiVie=6;
	public static Ennemi e1;
	public static Ennemi e2;
	public static Ennemi e3;
	public static Ennemi e4;
	public static Render  r ;
	public static Victory  v ;
	public static char avancer='Z';//touche de deplacement
	public static char reculer='S';
	public static char droite='D';
	public static char gauche='Q';
	private ThreadPlayer runtp;
	private JFrame menuPrincipal;
	public Menu(JFrame f,int i) 
	{
		menuPrincipal = f;
		switch(i)
		{
			case 1://nb de joueur 
			{
				new Plateau();
				p1 = new Player(7,7);
				p1.setPseudo(JOptionPane.showInputDialog("Entrer votre pseudo :"));
				runtp = new ThreadPlayer(p1);
				e1 = new Ennemi();				
				e2 = new Ennemi();				
				e3 = new Ennemi();				
				v = new Victory();
				Thread tp = new Thread(runtp);
				e1.start();
				e2.start();//les e ont un thread associï¿½ (pour pouvoir le stop grace a l'ennemi)
				e3.start();				
				tp.start();				
				r = new Render(600,800,runtp.getKl());
				Thread t = new Thread(r);
				t.start();
				/*
				
				*/
				
			}
			case 2:
			{
				v = new Victory();
			}
		}
	}
	public static void newEnnemi()
	{
		ennemiVie--;
		System.out.println("vie"+ennemiVie);
		if(ennemiVie==0)
		{
			v.setVictory(true);
			return;
		}
		else if(ennemiVie>=3)
		{
			Ennemi e=new Ennemi();
			e.start();	
		}
		
	}

	public void fin() 
	{
		try {
			p1.getScr().setScore();
		} catch (IOException e) {
			e.printStackTrace();
		}
		r.stop();
		r.dispose();
		menuPrincipal.setVisible(true);
		runtp = null;
	}
	@Override
	public void run() {
		
		while(!(v.isVictory()) && p1.getVie() > 0)
		{

			try {
				Thread.sleep(100);
				Plateau.stopBordure();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ennemiVie = 6;
		fin();
		if(v.isVictory() == false)
		{
			JOptionPane.showMessageDialog(r, "Perdu !");
		}
		if(v.isVictory() == true)
		{
			JOptionPane.showMessageDialog(r, "Gagné!");
		}
		
	}
}
