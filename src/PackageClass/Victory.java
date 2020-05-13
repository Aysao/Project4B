package PackageClass;

import java.util.ArrayList;

public class Victory {

	private boolean victory = false;
	private ArrayList<Player> players;
	public Victory()
	{
		players = Plateau.getPlayer();
	}
	public void testVictory()
	{
		int x = Plateau.getHauteur();
		int y = Plateau.getLargeur();
		for(int i=0 ; i<x ; i++) // on compte pas la bordure
		{
			for(int j=0 ; j<y; j++)
			{			
				if(i!=0 && i!=x-1 && j!=0 && j!=y-1 && Plateau.plateau[i][j].getClass()==BlocSpe.class)
				{
					BlocSpe bs =(BlocSpe)Plateau.plateau[i][j];
					if(bs.alligne())
					{
						players.get(0).getScr().setPoint(players.get(0).getScr().getPoint()+500);
						victory=true;
					}				
				}				
			}			
		}		
	}

	public boolean isVictory() {
		return victory;
	}
	public void setVictory(boolean victory) {
		this.victory = victory;
	}
}
