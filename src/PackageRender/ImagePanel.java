package PackageRender;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;
import Main.Main;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image TitleScreen;
	public ImagePanel(String s)
	{
		if(Main.mode == 0)
			TitleScreen = new ImageIcon(s).getImage();	
		else
		{
			TitleScreen = new ImageIcon(this.getClass().getResource("/"+getPath(s))).getImage();
		}
	}
	public void paintComponent(Graphics g)
	{
		//paintComponents(g);
		g.drawImage(TitleScreen, 0, 0,this.getWidth(),this.getHeight(), null);
	}
	public String getPath(String s)
	{
		String res = "";
		int l = s.length();
		int i = l-1;
		do
		{
			res = s.charAt(i)+res;
			i--;
		}while(i>0 && s.charAt(i) != '/');
		return res;
	}
}
