package PackageRender;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sun.tools.javac.Main;

import PackageClass.Menu;

public class JOptionPanel extends JFrame {

	private JPanel pOption;

	private JPanel pchoose;
	private JTextField z;
	private JLabel zl;
	private JTextField s;
	private JLabel sl;
	private JTextField q;
	private JLabel ql;
	private JTextField d;
	private JLabel dl;
	
	private MButton valider;
	private JFrame f;
	
	public JOptionPanel()
	{
		f=this;		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setTitle("Pengo Option!!");
		this.setBackground(Color.black);	
		initComposant();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}

	private void actionButton() {
		valider.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				System.out.println("test");
				Menu.avancer=z.getText().charAt(0);
				Menu.reculer=s.getText().charAt(0);
				Menu.droite=d.getText().charAt(0);
				Menu.gauche=d.getText().charAt(0);
				f.setVisible(false);
				new JMenuPrincipal();
			}
			
		});
		
	}

	private void initComposant() {
		pOption = new JPanel();
		pchoose = new JPanel();
		pOption.setBackground(Color.black);
		pOption.setLayout(new GridLayout(2,1));	
		pchoose.setLayout(new GridLayout(4,2));
	
		valider = new MButton("res/GlaceBlock.png");
		valider.setText("valider");
		zl= new JLabel("res/GlaceBlock.png");
		zl.setText("avancer:");
		z = new JTextField("res/GlaceBlock.png");
		z.setText(Character.toString(Menu.avancer));
		sl= new JLabel("res/GlaceBlock.png");
		sl.setText("reculer:");
		s = new JTextField("res/GlaceBlock.png");
		s.setText(Character.toString(Menu.reculer));
		ql= new JLabel("res/GlaceBlock.png");
		ql.setText("gauche:");
		q = new JTextField("res/GlaceBlock.png");
		q.setText(Character.toString(Menu.gauche));
		dl= new JLabel("res/GlaceBlock.png");
		dl.setText("droite:");
		d = new JTextField("res/GlaceBlock.png");
		d.setText(Character.toString(Menu.droite));
		
		pchoose.add(zl);
		pchoose.add(z);
		pchoose.add(dl);
		pchoose.add(d);
		pchoose.add(ql);
		pchoose.add(q);		
		pchoose.add(sl);
		pchoose.add(s);	
		
		pOption.add(pchoose);	
		pOption.add(valider);
		this.add(pOption);
	
	}

}
