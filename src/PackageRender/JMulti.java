package PackageRender;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import PackageThreads.Menu;


public class JMulti extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MButton heberger;
	private MButton rejoindre;
	private MButton retour;
	private JFrame jf ;
	public JMulti(JFrame f)
	{
		jf = this;
		this.setSize(800, 600);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Multiplayer");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
	
		initcomponent(f);
	}
	
	public void initcomponent(JFrame f)
	{
		heberger = new MButton("Image/GlaceBlock.png");
		rejoindre = new MButton("Image/GlaceBlock.png");
		retour = new MButton("Image/GlaceBlock.png");
		heberger.setPressed("Image/click.png");
		rejoindre.setPressed("Image/click.png");
		retour.setPressed("Image/click.png");
		heberger.setRollover("Image/blurGlaceBlock.png");
		rejoindre.setRollover("Image/blurGlaceBlock.png");
		retour.setRollover("Image/blurGlaceBlock.png");
		heberger.setText("Heberger");
		rejoindre.setText("Rejoindre");
		retour.setText("Retour");
		heberger.setBounds(this.getWidth()/5,this.getHeight()/4,this.getWidth()/5,this.getHeight()/4);
		rejoindre.setBounds(3*this.getWidth()/5,this.getHeight()/4,this.getWidth()/5,this.getHeight()/4);
		retour.setBounds((this.getWidth()/2)-50,this.getHeight()-100,100,50);
		this.add(heberger);
		this.add(rejoindre);
		this.add(retour);
		actionButton(f);
	}
	public void actionButton(JFrame f)
	{
		retour.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				f.setVisible(true);
				
			}
			
		});
		heberger.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu();
				Menu.getInstance().setHost(true);	
				m.MenuStart(f, 2);						
				Thread t = new Thread(m);
				t.start();
				f.setVisible(false);			
			}
			
		});
		rejoindre.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {	
				Menu m = new Menu();
				Menu.getInstance().setHost(false);	
				m.MenuStart(f, 2);									
				new Thread(m);
				f.setVisible(false);								
			}
			
		});
	}
}
