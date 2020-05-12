package Main;



import java.io.IOException;


import PackageRender.JMenuPrincipal;

/*
 * tableau de jeu
 * 15 / 17 le tableau  
 */


public class Main {
	public static int mode = 1; //mode 0 = IDE / mode 1 = jarfile

	public static void main(String[] args) throws IOException {
		
		new JMenuPrincipal();	
		
	}
	
//	public static URL getPath(URL u)
//	{
//		URL url= u;
//		String s = u.toString();
//		int l = s.length();
//		for(int i = l-1;i>=0;i--)
//		{
//			test++;
//			if(s.charAt(i) == '/' && i != l-1)
//			{
//				l = i;
//				break;
//			}
//		}
//		s = s.substring(0,l);
//		return url;
//	}

		
}
