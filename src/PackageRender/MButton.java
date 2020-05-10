package PackageRender;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Main.Main;

public class MButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image block;
	private Image blockpressed;
	private Image blockrollover;
	private String text;
	public MButton(String im)
	{
		if(Main.mode == 0)
			block = new ImageIcon(im).getImage();
		else
			block = new ImageIcon(this.getClass().getResource("/"+getPath(im))).getImage();
	}
	public void setText(String s)
	{
		text = s;
		this.setFont(new Font(Font.DIALOG,Font.BOLD,18));
	}
	public String getText()
	{
		return text;
	}
	public void setPressed(String s)
	{
		if(Main.mode == 0)
			blockpressed = new ImageIcon(s).getImage();
		else
		{
			blockpressed = new ImageIcon(this.getClass().getResource("/"+getPath(s))).getImage();
		}
	}
	public void setRollover(String s)
	{
		if(Main.mode == 0)
			blockrollover = new ImageIcon(s).getImage();
		else
		{
			blockrollover = new ImageIcon(this.getClass().getResource("/"+getPath(s))).getImage();
		}
	}
	
	
	public void paintComponent(Graphics g)
	{
		if(this.blockpressed != null && this.model.isPressed())
		{
			g.drawImage(blockpressed,0,0,this.getWidth(),this.getHeight(),null);
		}
		else if(this.blockrollover != null && this.model.isRollover())
		{
			g.drawImage(blockrollover,0,0,this.getWidth(),this.getHeight(),null);
		}
		else
		{
			g.drawImage(block,0,0,this.getWidth(),this.getHeight(),null);
		}
		if(text != null) {
            FontMetrics fm = g.getFontMetrics();
            int t = (fm.stringWidth(text)/2);
            
            int x = (this.getWidth()/2-t);// + fm.stringWidth(text)/2;
            int y = ((this.getHeight()-fm.getHeight())/2)+fm.getAscent();
            g.drawString(text, x, y);    
        }
		this.repaint();
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
