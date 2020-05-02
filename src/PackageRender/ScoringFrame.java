package PackageRender;

import java.awt.*;

import javax.swing.*;
import PackageClass.*;

public class ScoringFrame extends JPanel {
	private JLabel scorep1;
	private JLabel highscore;
	private JLabel scorep2;
	public ScoringFrame(int x,int y)
	{
		initScore();
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(1,3));
		this.add(scorep1);
		this.add(highscore);
		this.add(scorep2);
		this.setPreferredSize(new Dimension(x,y/15));
	}
	
	private void initScore()
	{
		scorep1 = new JLabel();
		highscore = new JLabel();
		scorep2 = new JLabel();
		scorep1.setOpaque(false);
		highscore.setOpaque(false);
		scorep2.setOpaque(false);
		scorep1.setForeground(Color.WHITE);
		highscore.setForeground(Color.WHITE);
		scorep2.setForeground(Color.WHITE);
		scorep1.setFont(new Font(Font.DIALOG, Font.BOLD , 20));
		highscore.setFont(new Font(Font.DIALOG, Font.BOLD , 20));
		scorep2.setFont(new Font(Font.DIALOG, Font.BOLD , 20));
		scorep1.setText("P1: 000000");
		highscore.setText("HI: 000000");
		scorep2.setText("P1: 000000");
		scorep1.setHorizontalAlignment(JLabel.CENTER);
		highscore.setHorizontalAlignment(JLabel.CENTER);
		scorep2.setHorizontalAlignment(JLabel.CENTER);
	}
} 
