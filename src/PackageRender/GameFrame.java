package PackageRender;
import PackageClass.*;
import PackageThreads.ThreadPlayer;

import java.awt.*;


import javax.swing.*;

public class GameFrame extends JPanel 
{
	private int Largeur = 800-5;
	private int Hauteur = 600;
	private int BlockL = Largeur/Plateau.getLargeur();
	private int BlockH = Hauteur/Plateau.getHauteur();
	private Image iceblock;
	private ImageIcon ii = new ImageIcon("res/GlaceBlock.png");
	private Image fpengo;
	private Image bpengo;
	private Image rpengo;
	private Image lpengo;
	private Image diamond;
	private Image angye;
	private Image e;
	
	public GameFrame(int L,int H)
	{
		iceblock = ii.getImage();
		ii = new ImageIcon("res/Penguin1.png");
		
		fpengo = ii.getImage();
		diamond = new ImageIcon("res/Diamant.png").getImage();
		angye = new ImageIcon("res/Angrycloud.png").getImage();
		e = new ImageIcon("res/Cutecloud.png").getImage();
		bpengo = new ImageIcon("res/PenguinDos.png").getImage();
		lpengo = new ImageIcon("res/PenguinVersGauche.png").getImage();
		rpengo = new ImageIcon("res/PenguinVersDroite.png").getImage();
		Largeur = L-5;
		Hauteur = H-2*(H/15)-37;
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
						g.setColor(Color.BLUE);
						g.fillRect(BlockL*j,BlockH*i,BlockL,BlockH);
						g.drawImage(iceblock,BlockL*j,BlockH*i,BlockL,BlockH, null);
					}
					if(Plateau.plateau[i][j].getClass() == BlocSpe.class)
					{
						g.setColor(Color.black);
						g.fillRect(BlockL*j,BlockH*i,BlockL,BlockH);
						g.drawImage(diamond,BlockL*j,BlockH*i,BlockL,BlockH, null);
					}
					if(Plateau.plateau[i][j].getClass() == Player.class)
					{
						Player p = (Player) Plateau.plateau[i][j];
						g.setColor(Color.BLACK);
						g.fillRect(BlockL*j,BlockH*i,BlockL,BlockH);
						
						switch(p.getOrientation())
						{
						case Entity.NORD:{
                            g.setColor(Color.BLACK);
                            g.drawImage(bpengo,BlockL*j,BlockH*i,BlockL,BlockH, null);
                        }break;
                        case Entity.EAST:{
                            g.setColor(Color.BLACK);
                            g.drawImage(rpengo,BlockL*j,BlockH*i,BlockL,BlockH, null);
                        }break;
                        case Entity.WEST:{
                            g.setColor(Color.BLACK);
                            g.drawImage(lpengo,BlockL*j,BlockH*i,BlockL,BlockH, null);
                        }break;
                        case Entity.SOUTH:{
                            g.setColor(Color.BLACK);
                            g.drawImage(fpengo,BlockL*j,BlockH*i,BlockL,BlockH, null);
                        }break;
                        case Entity.SO:
						{
							g.drawImage(fpengo,BlockL*j,BlockH*i,BlockL,BlockH, null);
						}break;
						}
						
					}
					if(Plateau.plateau[i][j].getClass() == Ennemi.class)
					{
						Ennemi en = (Ennemi)Plateau.plateau[i][j]; 
						g.setColor(Color.black);
						g.fillRect(BlockL*j,BlockH*i,BlockL,BlockH);
						if(en.stun)
						{
							g.drawImage(e,BlockL*j,BlockH*i,BlockL,BlockH, null);
						}
						else
						{
							g.drawImage(angye,BlockL*j,BlockH*i,BlockL,BlockH, null);
						}

						
						switch(en.getOrientation())
						{
						case Entity.NORD:{
                            g.setColor(Color.BLACK);
                            g.fillRect((BlockL*j+(int)(BlockL/2)-5),BlockH*i+5,11,10);
                        }break;
                        case Entity.EAST:{
                            g.setColor(Color.BLACK);
                            g.fillRect(BlockL*(j+1)-5,BlockH*i+(int)(BlockH/2)-5,-10,11);
                        }break;
                        case Entity.WEST:{
                            g.setColor(Color.BLACK);
                            g.fillRect(BlockL*(j)+5,BlockH*i+(int)(BlockH/2)-5,10,11);
                        }break;
                        case Entity.SOUTH:{
                            g.setColor(Color.BLACK);
                            g.fillRect((BlockL*j+(int)(BlockL/2)-5),BlockH*(i+1)-5,11,-10);
                        }break;
						}
					}
					if(Plateau.plateau[i][j].getClass() == Bordure.class)
					{
						Bordure b = (Bordure)Plateau.plateau[i][j];
						if(b.isActivate())					
							g.setColor(Color.GREEN);
						else	
							g.setColor(Color.GRAY);						
						g.fillRect(BlockL*j,BlockH*i,BlockL,BlockH);
					}
					if(Plateau.plateau[i][j].getClass() == String.class)
					{
						g.setColor(Color.BLACK);
						g.fillRect(BlockL*j,BlockH*i,BlockL,BlockH);
					}
					
				}
				g.setColor(Color.BLACK);
				g.drawRect(BlockL*j,BlockH*i,BlockL,BlockH);
				
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


