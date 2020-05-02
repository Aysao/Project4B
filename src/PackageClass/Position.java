package PackageClass;

public class Position {

	private int coY;
	private int coX;

	public Position(int x ,int y)
	{
		this.setCoX(x);
		this.setCoY(y);
	}

	public int getCoX() {
		return coX;
	}

	public void setCoX(int coX) {
		this.coX = coX;
	}

	public int getCoY() {
		return coY;
	}

	public void setCoY(int coY) {
		this.coY = coY;
	}
} 
