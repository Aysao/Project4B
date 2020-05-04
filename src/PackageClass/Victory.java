package PackageClass;


public class Victory {

	private boolean victory = false;
	public Victory()
	{
		
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
					if(Plateau.plateau[i][j+1].getClass()==BlocSpe.class&&Plateau.plateau[i][j+2].getClass()==BlocSpe.class)
					{
						Menu.p1.getScr().setPoint(Menu.p1.getScr().getPoint()+500);
						victory=true;
					}
					else if(Plateau.plateau[i+1][j].getClass()==BlocSpe.class&&Plateau.plateau[i+2][j].getClass()==BlocSpe.class)
					{
						Menu.p1.getScr().setPoint(Menu.p1.getScr().getPoint()+500);
						victory=true;
					}
				}				
			}
			
		}		
		if(victory)
		{
			Winning();
		}
	}
	
	private void  Winning()
	{
		System.out.println("--------------------------------------");
		System.out.println("Vous avez gagné avec : "+Menu.p1.getScr().getPoint()+" pts!");
		System.out.println("-------------------------------------!");
		victory = false;
	}
	public boolean isVictory() {
		return victory;
	}
	public void setVictory(boolean victory) {
		this.victory = victory;
	}
}
