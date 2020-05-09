package PackageRender;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MButton extends JButton {
	
	private Image block;
	private Image blockpressed;
	private Image blockrollover;
	private String text;
	public MButton(String im)
	{
		block = new ImageIcon(im).getImage();
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
		blockpressed = new ImageIcon(s).getImage();
	}
	public void setRollover(String s)
	{
		blockrollover = new ImageIcon(s).getImage();
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
	

}
