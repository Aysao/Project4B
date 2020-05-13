package PackageRender;
import javax.swing.*;

import PackageClass.Plateau;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;







public class Render extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Largeur = 800;
	private int Hauteur = 600+37;
	private JPanel bottom;
	private boolean running = true;
	private ScoringFrame sc;
	private JFrame parent;
	private JFrame me;
	public Render(JFrame j,int L,int H,KeyListener kl)
	{	
		parent = j;
		me = this;
		Largeur = L;
		Hauteur = H+50;
		bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(Largeur,Hauteur/15));
		bottom.setBackground(Color.black);
		sc = new ScoringFrame(Largeur,Hauteur);
		this.setSize(Largeur, Hauteur);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(sc,BorderLayout.NORTH);
		this.getContentPane().add(new GameFrame(Largeur,Hauteur),BorderLayout.CENTER);
		this.getContentPane().add(bottom,BorderLayout.SOUTH);
		this.addKeyListener(kl);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Plateau.clearEntity();
				stop();
				parent.setVisible(true);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					if(running == true)
					{
						stop();
						Plateau.clearEntity();
						me.dispose();
						parent.setVisible(true);
					}
					else if(running == false)
					{
						Plateau.clearEntity();
						me.dispose();
						parent.setVisible(true);
					}
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
	}

	
	@Override
	public void run() 
	{
		while(running)
		{
			sc.setHighScore();
			sc.setScoreP1();
			this.repaint();
			try {
				Thread.sleep(1000/60l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void stop()
	{
		running = false;
	}
}
