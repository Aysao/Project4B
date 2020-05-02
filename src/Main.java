
import java.io.IOException;

import PackageClass.*;
import PackageRender.*;

/*
 * tableau de jeu
 * 15 / 17 le tableau  
 */


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		Menu m = new Menu(1);
		
				
		Thread t = new Thread(Menu.r);
		t.start(); 
		
		
	}
		
}
