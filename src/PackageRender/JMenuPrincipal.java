package PackageRender;
import javax.swing.*;


import PackageThreads.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JMenuPrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pprincipal;
	private JPanel pbutton;
	private MButton b1;
	private MButton b2;
	private MButton b3;
	private MButton b4;
	private int choix;
	private JFrame f;
	public JMenuPrincipal() {
		f = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setTitle("Pengo !!");
		this.setBackground(Color.black);
		initComposant();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		actionButton();
		}
	
	
	private void initComposant()
	{
		
		pprincipal = new ImagePanel("Image/PengoTitle.png");
		pprincipal.setBackground(Color.black);
		pprincipal.setLayout(null);
		pbutton = new JPanel();
		pbutton.setOpaque(false);
		pbutton.setLayout(new GridLayout(2,2));
		b1 = new MButton("Image/GlaceBlock.png");
		b2 = new MButton("Image/GlaceBlock.png");
		b3 = new MButton("Image/GlaceBlock.png");
		b4 = new MButton("Image/GlaceBlock.png");
		b1.setText("Partie Solo");
		b2.setText("Partie Multi");
		b3.setText("Option");
		b4.setText("Quitter");
		b1.setRollover("Image/blurGlaceBlock.png");
		b2.setRollover("Image/blurGlaceBlock.png");
		b3.setRollover("Image/blurGlaceBlock.png");
		b4.setRollover("Image/blurGlaceBlock.png");
		b1.setPressed("Image/click.png");
		b2.setPressed("Image/click.png");
		b3.setPressed("Image/click.png");
		b4.setPressed("Image/click.png");
		b1.setBorder(null);
		b2.setBorder(null);
		b3.setBorder(null);
		b4.setBorder(null);
		pbutton.add(b1);
		pbutton.add(b2);
		pbutton.add(b3);
		pbutton.add(b4);
		pprincipal.add(pbutton);
		//pbutton.setBounds(new Rectangle((f.getWidth()/4),f.getHeight()/4,(f.getWidth()/4)*2,(f.getHeight()/4)*2));
		pbutton.setBounds((this.getWidth()/4),(this.getHeight()/4)*2,(this.getWidth()/4)*2,(this.getHeight()/4)*1);
		
		this.add(pprincipal);
		
	}
	private void actionButton()
	{
		b1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				choix = 1;
				Menu m = new Menu(f,choix);
				Thread t = new Thread(m);
				t.start();
				f.setVisible(false);
			}
			
		});
		b2.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				JMulti j = new JMulti(f);
				j.setVisible(true);
				f.setVisible(false);
			}
			
		});
		b3.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOption jp = new JOption(f);

				jp.setVisible(true);
				f.setVisible(false);
				
			}
			
		});
		b4.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
			
		});
			
		
	}
	public int getChoix() {
		return choix;
	}
	
	
}

