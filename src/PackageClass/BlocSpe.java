package PackageClass;

/*
 * un bloc special qui fait gagne le joueur si les trois bloc sont aligne
 * 
 */
public class BlocSpe extends Bloc {

	public BlocSpe(int X, int Y) {
		super(X, Y);
	}

	//verifie si les 3 bloc sont aligne verticalement ou horizontalement
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
