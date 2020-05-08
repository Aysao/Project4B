package PackageRender;

import javax.swing.*;

import PackageClass.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JMenuPrincipal extends JFrame {
	private JPanel pprincipal;
	private JPanel pbutton;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private int choix;
	private JFrame f;
	public JMenuPrincipal() {	
		f = this;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800,600);
		this.setTitle("Pengo !!");
		initComposant();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		actionButton();
		}
	
	private void initComposant()
	{
		pprincipal = new JPanel();
		pprincipal.setBackground(Color.black);
		pprincipal.setLayout(null);
		pbutton = new JPanel();
		pbutton.setLayout(new GridLayout(2,2));
		b1 = new JButton("Jouer solo");
		b2 = new JButton("Jouer multi");
		b3 = new JButton("Option");
		b4 = new JButton("Quitter");
		pbutton.add(b1);
		pbutton.add(b2);
		pbutton.add(b3);
		pbutton.add(b4);
		pprincipal.add(pbutton);
		//pbutton.setBounds(new Rectangle((f.getWidth()/4),f.getHeight()/4,(f.getWidth()/4)*2,(f.getHeight()/4)*2));
		pbutton.setBounds((this.getWidth()/4),this.getHeight()/4,(this.getWidth()/4)*2,(this.getHeight()/4)*2);
		
		this.add(pprincipal);
		
	}
	private void actionButton()
	{
		b1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				choix = 1;
				f.setVisible(false);
				Menu m = new Menu(choix);
			}
			
		});
			
		
	}
	public int getChoix() {
		return choix;
	}

}
