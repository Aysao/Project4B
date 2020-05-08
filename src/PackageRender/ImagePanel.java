package PackageRender;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.*;

public class ImagePanel extends JPanel {
	private Image TitleScreen;
	public ImagePanel(String s)
	{
		TitleScreen = new ImageIcon(s).getImage();	
	}
	public void paintComponent(Graphics g)
	{
		//paintComponents(g);
		g.drawImage(TitleScreen, 0, 0,this.getWidth(),this.getHeight(), null);
	}
}
