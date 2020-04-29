package PackageRender;
import javax.swing.*;
import java.awt.*;

import PackageClass.*;

public class Render extends JFrame{
	public static int mode = 0; //mode 0 = IDE / mode 1 = jarfile
	private int Largeur = 800;
	private int Hauteur = 600+37;
	private JPanel bottom;
	public Render(int L,int H)
	{
		Largeur = L;
		Hauteur = H+50;
		bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(Largeur,Hauteur/15));
		bottom.setBackground(Color.black);
		this.setSize(Largeur, Hauteur);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new ScoringFrame(Largeur,Hauteur),BorderLayout.NORTH);
		this.getContentPane().add(new GameFrame(Largeur,Hauteur),BorderLayout.CENTER);
		this.getContentPane().add(bottom,BorderLayout.SOUTH);
		this.repaint();
	}
}
