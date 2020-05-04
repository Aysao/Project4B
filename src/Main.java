
import java.io.IOException;

import PackageClass.*;


/*
 * tableau de jeu
 * 15 / 17 le tableau  
 */


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		
		new Menu(1);
		
				
		Thread t = new Thread(Menu.r);
		t.start(); 
		
		
	}
		
}
