package PackageRender;
import javax.swing.*;

import PackageClass.*;

public class Render extends JFrame{

	private int Largeur = 800;
	private int Hauteur = 600+37;
	
	public Render(int L,int H)
	{
		Largeur = L;
		Hauteur = H+37-100;
		this.setSize(Largeur, Hauteur);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.getContentPane().add(new GameFrame(Largeur,Hauteur));
		this.repaint();
	}
}
