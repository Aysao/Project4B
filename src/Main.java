import java.io.IOException;

import PackageClass.*;
import PackageThreads.*;
import PackageRender.*;
/*
 * tableau de jeu
 * 15 / 17 le tableau  
 */


public class Main {
	public static void main(String[] args) throws IOException {
		Plateau p = new Plateau();
		Render r = new Render(600,800);
		Score s = new Score();
		s.mstKill();		
		
		
		s.setScore();
		
		
	}
		
}
