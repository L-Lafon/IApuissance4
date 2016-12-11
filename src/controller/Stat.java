package controller;

public class Stat {
	
	public static float[] nbJWin={0,0,0};
	

	public static void add(int nbChips, int winner){
		
		nbJWin[winner]++;
	}
	
	public static void showResults(){
		float nbGames = nbJWin[0] + nbJWin[1] + nbJWin[2];
		System.out.println("NbGames : "+nbGames+" - J1 gagnant : "+(nbJWin[1]/nbGames*100)+", J2 gagnant : "+(nbJWin[2]/nbGames*100)+", Match nul : "+(nbJWin[0]/nbGames*100)+"");
	}

}
