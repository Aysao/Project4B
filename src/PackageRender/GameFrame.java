package PackageRender;
import PackageClass.*;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class GameFrame extends JPanel {
	
	private int Largeur = 800;
	private int Hauteur = 600;
	private int BlockL = Largeur/Plateau.getLargeur();
	private int BlockH = Hauteur/Plateau.getHauteur();
	
	public GameFrame(int L,int H)
	{
		Largeur = L;
		Hauteur = H-37;
		BlockH = Hauteur/Plateau.getHauteur();
		BlockL = Largeur/Plateau.getLargeur();
	}
	public GameFrame()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0;i<Plateau.getHauteur();i++)
		{
			for(int j = 0;j<Plateau.getLargeur();j++)
			{
				if(Plateau.plateau[i][j] != null)
				{
					if(Plateau.plateau[i][j].getClass() == BlocN.class)
					{
						g.setColor(Color.CYAN);
					}
					if(Plateau.plateau[i][j].getClass() == Player.class)
					{
						g.setColor(Color.yellow);
					}
					if(Plateau.plateau[i][j].getClass() == Ennemie.class)
					{
						g.setColor(Color.red);
					}
					if(Plateau.plateau[i][j].getClass() == BlocSpe.class) 
					{
						g.setColor(Color.blue);
					}
					if(Plateau.plateau[i][j].getClass() == Bordure.class)
					{
						g.setColor(Color.GRAY);
					}
				}
				else
				{
					g.setColor(Color.black);
				}
				g.fillRect(BlockL*i,BlockH*j,BlockL*(i+1),BlockH*(j+1));
				g.setColor(Color.black);
				g.drawRect(BlockL*i,BlockH*j,BlockL*(i+1),BlockH*(j+1));
			}
		}
	}
}
