package PackageClass;



import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Score {
	
	
	private int point = 0;
	private int mstKill = 0;
	private int blocDestroy = 0;
	private double[] scores;
	private String[] pseudo;
	private String name = null; 
	public Score()
	{
		
	}
	public Score(String name)
	{
		this.name = name;
	}
	public void mstKill()
	{
		this.point=this.point+40;
		this.mstKill++;
	}

	public void BlocDestroy()
	{
		this.point=this.point+30;
		this.blocDestroy++;
	}
	public void getScore()
	{
		scores = new double[10];
		pseudo = new String[10];	    
	    try
	    { // on recupere les nom est score des joueur qu'on isole dans deux tableau;
			InputStream flux=new FileInputStream("test.txt"); 
			InputStreamReader lecture=new InputStreamReader(flux);
			StreamTokenizer st = new StreamTokenizer(lecture);
			for(int i = 0;i<21;i++)
			{
				if(st.ttype == StreamTokenizer.TT_WORD)  // si nom go dans tab String  
				{
					pseudo[(i-1)/2]=st.sval;
				}
				if(st.ttype ==  StreamTokenizer.TT_NUMBER) // si score go dans tab double 
				{
					scores[(i-1)/2]=st.nval;
				}
				st.nextToken();
			} 
		}		
		catch (Exception e)
	    {
			System.out.println(e.toString());
		}
	    
	}
	public void setScore() throws IOException
	{
	    getScore();
	    for(int i = 0;i<10;i++) // on parcours le classement pour voir si le nouveau score y est 
	    {
	    	if(scores[i]<this.point) // si new score > un score 
	    	{		
	    		 for(int j=9;j>i;j--) // on decale tout les top en partant de la fin 
	     		{
	    			 scores[j] = scores[j-1];
	    			 pseudo[j]=pseudo[j-1];
	     		}
	    		 scores[i]= this.getPoint(); // on place notre nouveau top 
	     		if(this.getName()==null)
	     			pseudo[i]="joueur"+i; // si pas de pseudo "joueur" + placement
	     		else
	     			pseudo[i]=this.name;
	     		break;
	    	}	
	    }
	    
	    try{
	    	PrintWriter writer;
	    	writer =  new PrintWriter(new BufferedWriter(new FileWriter("test.txt")));

		    for(int k =0;k<10;k++) // on ecrit dans le fichier les 10 lignes de top nom / score 
		    {
		    	writer.print(scores[k]+" ");  // ecrit nom joueur 
		    	writer.println(""+pseudo[k]);  // ecrit score joueur + changement ligne 
		    }
		    writer.close();  
	    	}		
	    	catch (Exception e){
	    	System.out.println(e.toString());
	    	}
	    
	    
	    
	    
	    
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getMstKill() {
		return mstKill;
	}
	

	public void setMstKill(int mstKill) {
		this.mstKill = mstKill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBlocDestroy() {
		return blocDestroy;
	}
	public void setBlocDestroy(int blocDestroy) {
		this.blocDestroy = blocDestroy;
	}
	
	
} 



