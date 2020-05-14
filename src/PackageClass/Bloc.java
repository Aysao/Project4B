package PackageClass;

/*
 * bloc abstract qui permet de donne les caracteristique similaire au bloc Speciaux et Normal
 * la classe contient les coo du bloc 
 */
public abstract class Bloc {

	private int posX;
	private int posY;
	 
	public Bloc(int X,int Y)
	{
		setPosX(X);
		setPosY(Y);
	}	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosY() {
		return posY;
	}


}
