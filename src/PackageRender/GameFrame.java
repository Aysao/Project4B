package PackageRender;
import PackageClass.*;
import java.awt.*;


import javax.swing.*;

public class GameFrame extends JPanel 
{
	
	private int Largeur = 800-5;
	private int Hauteur = 600;
	private int BlockL = Largeur/Plateau.getLargeur();
	private int BlockH = Hauteur/Plateau.getHauteur();
	//private Image iceblock;
	//private ImageIcon ii = new ImageIcon("res/GlaceBlock.png");
	
	public GameFrame(int L,int H)
	{
		
		Largeur = L-5;
		Hauteur = H-2*(H/15)-37;
		BlockH = Hauteur/Plateau.getHauteur();
		BlockL = Largeur/Plateau.getLargeur();
		
	}
	public GameFrame()
	{
		
	}
	
	private void initImage() 
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
						g.setColor(Color.BLUE);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
						//g.drawImage(iceblock,BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1), null);
					}
					if(Plateau.plateau[i][j].getClass() == BlocSpe.class)
					{
						g.setColor(Color.CYAN);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[i][j].getClass() == Player.class)
					{
						g.setColor(Color.YELLOW);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[i][j].getClass() == Ennemi.class)
					{
						Ennemi en = (Ennemi)Plateau.plateau[i][j]; 
						if(en.stun)
							g.setColor(Color.PINK);
						else
							g.setColor(Color.RED);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[i][j].getClass() == Bordure.class)
					{
						Bordure b = (Bordure)Plateau.plateau[i][j];
						if(b.isActivate())					
							g.setColor(Color.GREEN);
						else	
							g.setColor(Color.GRAY);						
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[i][j].getClass() == String.class)
					{
						g.setColor(Color.BLACK);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					
				}
				g.setColor(Color.black);
				g.drawRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
				
			}
		}
		
		//g.drawImage(iceblock,0,0,Largeur-100,Hauteur-100, null);
		g.dispose();
		
	}

} 
	
	/*
	 * for(int i = 0;i<Plateau.getHauteur();i++)
		{
			for(int j = 0;j<Plateau.getLargeur();j++)
			{
				if(Plateau.plateau[j][i] != null)
				{
					if(Plateau.plateau[j][i].getClass() == BlocN.class)
					{
						g.setColor(Color.CYAN);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
						//g.drawImage(iceblock,BlockL*i,BlockH*j,BlockL*(i+1),BlockH*(j+1), null);
					}
					if(Plateau.plateau[j][i].getClass() == Player.class)
					{
						g.setColor(Color.yellow);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[j][i].getClass() == Ennemie.class)
					{
						g.setColor(Color.red);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[j][i].getClass() == BlocSpe.class) 
					{
						g.setColor(Color.blue);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[j][i].getClass() == Bordure.class)
					{
						g.setColor(Color.GRAY);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
					if(Plateau.plateau[j][i].getClass() == String.class)
					{
						g.setColor(Color.black);
						g.fillRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
					}
				}
				
			
				g.setColor(Color.black);
				g.drawRect(BlockL*j,BlockH*i,BlockL*(j+1),BlockH*(i+1));
			}
		}
		*/


