package PackageRender;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import PackageClass.*;

public class ScoringFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel scorep1;
	private JLabel highscore;
	private ArrayList<Player> players;
	public ScoringFrame(int x,int y)
	{
		initScore();
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(1,3));
		this.add(scorep1);
		this.add(highscore);
		this.setPreferredSize(new Dimension(x,y/15));
	}
	
	private void initScore()
	{
		scorep1 = new JLabel();
		highscore = new JLabel();
		scorep1.setOpaque(false);
		highscore.setOpaque(false);
		scorep1.setForeground(Color.WHITE);
		highscore.setForeground(Color.WHITE);
		scorep1.setFont(new Font(Font.DIALOG, Font.BOLD , 20));
		highscore.setFont(new Font(Font.DIALOG, Font.BOLD , 20));
		scorep1.setText("P1: 000000");
		highscore.setText("HI: 000000");
		scorep1.setHorizontalAlignment(JLabel.CENTER);
		highscore.setHorizontalAlignment(JLabel.CENTER);
		players = Plateau.getPlayer();
	}
	
	public void setScoreP1()
	{
		if(players.size() >= 1)
		{
			Player p = players.get(0);
			String s = "P1 :";
			for(int j = 100000;j > 0;j = j/10)
			{
				if((p.getScr().getPoint()/j) >= 1 && (int)(p.getScr().getPoint()/j) < 10)
				{
					s += p.getScr().getPoint();
					break;
				}
				else
				{
					s += "0";
				}
				
			}
			scorep1.setText(s);
		}
	}
	public void setHighScore()
	{
		String s = "HighS : ";
		for(int j = 100000;j > 0;j = j/10)
		{
			if((int)Score.highscore/j >= 1 && (int)(Score.highscore/j) < 10)
			{
				s += (int)Score.highscore;
				break;
			}
			else
			{
				s += "0";
			}
			
		}
		highscore.setText(s);
	}
	
	
} 
