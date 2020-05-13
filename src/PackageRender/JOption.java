package PackageRender;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import PackageThreads.Menu;

public class JOption extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lforward;
	private MButton forward;
	private JLabel lback;
	private MButton back;
	private JLabel lright;
	private MButton right;
	private JLabel lleft;
	private MButton left;
	private MButton valider;
	private JPanel pbutton;
	private JPanel jp;
	private JFrame jf;
	private JFrame framep;
	private MButton touche;
	private boolean changetouche = false;
	
	public JOption(JFrame f)
	{
		jf = this;
		framep = f;
		this.setTitle("Raccourci clavier");
		this.setSize(800,600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		initcomposant();
	}
	
	public void initcomposant()
	{
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.black);
		pbutton = new JPanel();
		pbutton.setBackground(Color.black);
		pbutton.setLayout(new GridLayout(2,4));
		lforward = new JLabel("Avancer : ",JLabel.CENTER);
		lback = new JLabel("Reculer : ",JLabel.CENTER);
		lright = new JLabel("Droite : ",JLabel.CENTER);
		lleft = new JLabel("Gauche : ",JLabel.CENTER);
		forward = new MButton("Image/GlaceBlock.png");
		back = new MButton("Image/GlaceBlock.png");
		right = new MButton("Image/GlaceBlock.png");
		left = new MButton("Image/GlaceBlock.png");
		valider = new MButton("Image/GlaceBlock.png");
		lforward.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		lback.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		lleft.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		lright.setFont(new Font(Font.DIALOG,Font.BOLD,18));
		lforward.setForeground(Color.WHITE);
		lback.setForeground(Color.WHITE);
		lleft.setForeground(Color.WHITE);
		lright.setForeground(Color.WHITE);
		forward.setText(""+Menu.avancer);
		forward.setPressed("Image/click.png");
		forward.setRollover("Image/blurGlaceBlock.png");
		back.setText(""+Menu.reculer);
		back.setPressed("Image/click.png");
		back.setRollover("Image/blurGlaceBlock.png");
		right.setText(""+Menu.droite);
		right.setPressed("Image/click.png");
		right.setRollover("Image/blurGlaceBlock.png");
		left.setText(""+Menu.gauche);
		left.setPressed("Image/click.png");
		left.setRollover("Image/blurGlaceBlock.png");
		valider.setText("Valider");
		valider.setPressed("Image/click.png");
		valider.setRollover("Image/blurGlaceBlock.png");
		pbutton.add(lforward);
		pbutton.add(forward);
		pbutton.add(lback);
		pbutton.add(back);
		pbutton.add(lleft);
		pbutton.add(left);
		pbutton.add(lright);
		pbutton.add(right);
		
		jp.add(pbutton);
		pbutton.setBounds(25,50,this.getWidth()-100,this.getHeight()/3);
		jp.add(valider);
		valider.setBounds(this.getWidth()-pbutton.getWidth()/4-25,this.getHeight()-pbutton.getHeight()/2-50,pbutton.getWidth()/4,pbutton.getHeight()/2);
		this.getContentPane().add(jp);
		actionlistener();
		keyl();
		closeConfig();
	}
	
	public void actionlistener() {
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				framep.setVisible(true);
				Menu.avancer = forward.getText().charAt(0);
				Menu.reculer = back.getText().charAt(0);
				Menu.droite = right.getText().charAt(0);
				Menu.gauche = left.getText().charAt(0);
			}
			
		});
		forward.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changetouche = true;
				touche = (MButton) e.getSource();
				enable(false);
				jf.requestFocus();
			}
			
		});
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changetouche = true;
				touche = (MButton) e.getSource();
				enable(false);
				jf.requestFocus();
			}
			
		});
		
		right.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changetouche = true;
				touche = (MButton) e.getSource();
				enable(false);
				jf.requestFocus();
			}
			
		});
		
		left.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changetouche = true;
				touche = (MButton) e.getSource();
				enable(false);
				jf.requestFocus();
			}
			
		});
		
	}
	
	public void keyl()
	{
		jf.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e) {
				if(changetouche)
				{
					changetouche = false;
					enable(true);
					if(e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z')
						touche.setText(""+(char)(e.getKeyChar()-'a'+'A'));
					else
						touche.setText(""+(e.getKeyChar()));
					touche = null;
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
	
	public void closeConfig()
	{
		this.addWindowListener(new WindowListener()
			{

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					framep.setVisible(true);
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
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
	}
	
	public void enable(boolean b)
	{
		forward.setEnabled(b);
		back.setEnabled(b);
		right.setEnabled(b);
		left.setEnabled(b);
		valider.setEnabled(b);
	}
	

	
	

}
