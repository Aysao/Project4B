package PackageClass;

/*
 * un Bloc Normal sans fonction particuliere 
 */
public class BlocN extends Bloc {
	
	private boolean detruit = false;
	
	public BlocN(int X, int Y) {
		super(X, Y);
		this.c="#";		
	}
	
	public boolean isDetruit() {
		return detruit;
	}
	public void setDetruit(boolean detruit) {
		this.detruit = detruit;
	}
 
}
