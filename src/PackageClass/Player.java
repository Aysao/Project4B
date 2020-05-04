package PackageClass;



/*
 * toute les fonction du joueur
 * interaction avec bloc et bordure interaction avec joueur ennemie
 * 
 */
public class Player extends Entity {
	
	private int Vie = 3;
	private String pseudo;
	private Score scr = new Score();
	public Player(int x, int y) {
		super(x, y);
		Plateau.plateau[x][y] = this;
	}
	public Player(int x, int y,String str) {
		super(x, y);
		Plateau.plateau[x][y] = this;
		setPseudo(str);
		scr.setName(str);
		
	}
	public boolean nextToEdge()
	{
		if(this.getPosX() == 1 && this.getOrientation() == EAST)
		{
			return true;
		}
		if(this.getPosX() == 13 && this.getOrientation() == WEST)
		{
			return true;
		}
		if(this.getPosY() == 1 && this.getOrientation() == NORD)
		{
			return true;
		}
		if(this.getPosY() == 15 && this.getOrientation() == SOUTH)
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	public void setVie(int vie) {
		Vie = vie;
	}
	
	public int getVie() {
		return Vie;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public Score getScr() {
		return scr;
	}
	public void setScr(Score scr) {
		this.scr = scr;
	}
	
	 
	
} 
