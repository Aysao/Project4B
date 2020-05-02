package PackageClass;

/*
 * un bloc special qui fait gagn� le joueur si les trois bloc sont align�
 * 
 */
public class BlocSpe extends Bloc {

	public BlocSpe(int X, int Y) {
		super(X, Y);
		this.c="*";
		// TODO Auto-generated constructor stub
	}

	public boolean alligne(BlocSpe[] bspe)
	{
		if(bspe[0].getPosX() == bspe[1].getPosX() && bspe[1].getPosX() == bspe[2].getPosX())
		{
			return true;
		}
		if(bspe[0].getPosY() == bspe[1].getPosY() && bspe[1].getPosY() == bspe[2].getPosY())
		{
			return true;
		}
		else {
			return false;
		}
	}
} 
