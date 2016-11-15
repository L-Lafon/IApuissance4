package model;

public class QualityMove {
	
	public int chipsCurPlayer;
	public int chipsOppPlayer;
	public int free;	
	
	
	public int aligned;
	public int tmp_aligned;
	
	public int alignedPossible;
	public int tmp_alignedPossible;
	
	public int freeAround;
	public int winIsPossible;
	
	
	QualityMove(){
		//this.aligned = aligned;
		winIsPossible = 0;
		freeAround=0;
		
		aligned=tmp_aligned = 0;
		alignedPossible=tmp_alignedPossible = 0;
		
	}
	
	public void incChipCurPlayer(){
		chipsCurPlayer++;		
		
		tmp_aligned++;
		tmp_alignedPossible++;
	}
	
	public void incChipOppPlayer(){
		chipsOppPlayer++;
		
		clearTmp_aligned();	
		clearTmp_alignedPossible();
	}
	
	public void incFree(){
		free++;
		tmp_alignedPossible++;
		
		clearTmp_aligned();
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
