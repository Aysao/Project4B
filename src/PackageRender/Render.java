package PackageRender;
import javax.swing.*;
import com.sun.tools.javac.Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import PackageClass.*;
import PackageClass.Menu;


public class Render extends JFrame implements Runnable{
	public static int mode = 0; //mode 0 = IDE / mode 1 = jarfile
	private int Largeur = 800;
	private int Hauteur = 600+37;
	private JPanel bottom;
	private boolean running = true;
	public Render(int L,int H)
	{
		Largeur = L;
		Hauteur = H+50;
		bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(Largeur,Hauteur/15));
		bottom.setBackground(Color.black);
		this.setSize(Largeur, Hauteur);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new ScoringFrame(Largeur,Hauteur),BorderLayout.NORTH);
		this.getContentPane().add(new GameFrame(Largeur,Hauteur),BorderLayout.CENTER);
		this.getContentPane().add(bottom,BorderLayout.SOUTH);
		this.addKeyListener(new KeyListener() 
		{

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					stop();
					System.exit(1);
				}
				if(e.getKeyChar() == KeyEvent.VK_Q)
				{
					Menu.p1.setOrientation(Player.WEST);
					Menu.p1.Deplacement();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
			
		
		this.repaint();
	}
	@Override
	public void run() 
	{
		int fps = 60;
		double timeTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int tick = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timeTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1)
			{	
				this.repaint();
				delta--;
				tick++;
			}
			if(timer >= 1000000000)
			{
				System.out.println("fps : "+tick);
				tick = 0;
				timer = 0;
			}
		}
		//stop();
		
	} 
	
	public void stop()
	{
		running = false;
	}
}
