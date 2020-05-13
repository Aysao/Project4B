package PackageClass;

/*
 * un bloc special qui fait gagn� le joueur si les trois bloc sont align�
 * 
 */
public class BlocSpe extends Bloc {

	public BlocSpe(int X, int Y) {
		super(X, Y);
	}

	public boolean alligne()
	{
		if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass()==BlocSpe.class&&Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass()==BlocSpe.class)
		{
			return true;
		}
		else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass()==BlocSpe.class&&Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass()==BlocSpe.class)
		{
			return true;
		}
		return false;
	}
} 
