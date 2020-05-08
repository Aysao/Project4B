package PackageRender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import PackageClass.*;
import PackageClass.Menu;
import PackageThreads.*;





public class Render extends JFrame implements Runnable{
	public static int mode = 0; //mode 0 = IDE / mode 1 = jarfile
	private int Largeur = 800;
	private int Hauteur = 600+37;
	private JPanel bottom;
	private boolean running = true;
	private ScoringFrame sc;
	public Render(int L,int H,KeyListener kl)
	{
		//super(f , b);
		System.out.println("test");
		Largeur = L;
		Hauteur = H+50;
		bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(Largeur,Hauteur/15));
		bottom.setBackground(Color.black);
		sc = new ScoringFrame(Largeur,Hauteur);
		System.out.println("test");
		this.setSize(Largeur, Hauteur);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		System.out.println("test");
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(sc,BorderLayout.NORTH);
		this.getContentPane().add(new GameFrame(Largeur,Hauteur),BorderLayout.CENTER);
		this.getContentPane().add(bottom,BorderLayout.SOUTH);
		this.addKeyListener(kl);
		System.out.println("test");
		this.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					if(running == true)
					{
						stop();
						System.exit(1);
					}
					if(running == false)
					{
						System.exit(1);
					}
				}
				//System.out.println("Player pos x : "+ Menu.p1.getPosX());
				//System.out.println("Player pos y : "+ Menu.p1.getPosY());
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
		this.revalidate();
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
				sc.setScoreP1();
				sc.setHighScore();
				//sc.setScoreP2();
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
		
	} 
	
	public void stop()
	{
		running = false;
	}
}
