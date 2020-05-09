package PackageRender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JMulti extends JFrame {

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
		heberger = new MButton("res/GlaceBlock.png");
		rejoindre = new MButton("res/GlaceBlock.png");
		retour = new MButton("res/GlaceBlock.png");
		heberger.setPressed("res/click.png");
		rejoindre.setPressed("res/click.png");
		retour.setPressed("res/click.png");
		heberger.setRollover("res/blurGlaceBlock.png");
		rejoindre.setRollover("res/blurGlaceBlock.png");
		retour.setRollover("res/blurGlaceBlock.png");
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
	}
}
