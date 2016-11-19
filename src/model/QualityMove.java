package model;

public class QualityMove {
	
	/* 
	 * 
	 * INFORMATIONS SUR LA LIGNE 
	 * 
	 * */
	
	// Nombre de jetons du joueur courant sur la ligne 
	public int chipsCurPlayer;
	
	// Nombre jetons joueur adversaire sur ligne
	public int chipsOppPlayer;
	
	// Nbr cases libres sur ligne
	public int free;	
	
	// Nbr de jetons alignés sur la ligne
	public int aligned;
	
	// Taille maximale de l'alignement qu'il est encore possible de former 
	public int alignedPossible;
	
	// taille de la série
	public int serieCurrPlayer;
	public int serieOppPlayer;	
	
	// Nbr jetons manquant au milieu d'une série (XX_X : 1, X__X : 2)
	public int freeBetweenSeriesCurr;
	public int freeBetweenSeriesOpp;
	
	// Si on est dans la série de jetons de l'adversaire et qu'on le bloque
	public int stopOpposant;
	
	
	/* ----------
	 * 
	 */
	
	
	
	
	private int tmp_freeBetweenSeriesCurr;	
	//private int tmp_freeBetweenSeriesOpp;	
	
	private int tmp_stopOpposant;	
	
	private int tmp_aligned;
	private int tmp_alignedPossible;	
	
	
	QualityMove(){
		//this.aligned = aligned;
		
	
		
		aligned=tmp_aligned = 0;
		alignedPossible=tmp_alignedPossible = 0;
		freeBetweenSeriesCurr=tmp_freeBetweenSeriesCurr=Integer.MAX_VALUE; // Grande valeur pour faire un max
		serieCurrPlayer=serieOppPlayer=0;
		
		stopOpposant=tmp_stopOpposant=0;
		
		
	}
	
	public void incChipCurPlayer(){
		chipsCurPlayer++;		
		
		tmp_aligned++;
		tmp_alignedPossible++;
		
		if(serieCurrPlayer > 0){
			if(tmp_freeBetweenSeriesCurr < freeBetweenSeriesCurr)
				freeBetweenSeriesCurr = tmp_freeBetweenSeriesCurr;
			tmp_freeBetweenSeriesCurr=0;	
			
		}
		
		if(serieOppPlayer > 0)
			stopOpposant++;
		
		serieCurrPlayer++;		
		serieOppPlayer=0;
	}
	
	public void incChipOppPlayer(){
		chipsOppPlayer++;
		
		clearTmp_aligned();	
		clearTmp_alignedPossible();
		
		tmp_freeBetweenSeriesCurr=0;		
		serieCurrPlayer=0;
		
		
		if(serieOppPlayer>0){
			
			
		}
		
		serieOppPlayer++;
	}
	
	public void incFree(){
		free++;
		tmp_alignedPossible++;
		
		clearTmp_aligned();
		
		if(serieCurrPlayer>0)
			tmp_freeBetweenSeriesCurr++;
			
	}
	
	public void end(){
		clearTmp_aligned();
		clearTmp_alignedPossible();
	}
	
	public void clearTmp_aligned(){
		if(tmp_aligned > aligned){
			aligned = tmp_aligned;
		}
		tmp_aligned=0;
		
		tmp_freeBetweenSeriesCurr=0;
	}
	public void clearTmp_alignedPossible(){
		if(tmp_alignedPossible > alignedPossible){
			alignedPossible = tmp_alignedPossible;
		}
		tmp_alignedPossible=0;
	}
	
	public String toString(){
		return "aligned="+aligned+"&aligned_possible="+alignedPossible;
	}

}
