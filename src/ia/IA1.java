package ia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Chip;
import model.Grid;
import model.Player;
import model.Position;
import model.QualityMove;

public class IA1 extends IA{
	
	public IA1(Grid grid, Player pIA, Player pOpp){
		super(grid,pIA,pOpp);
	}
	
	public Position play(){
		
		int val_max = Integer.MIN_VALUE;
		int col_max=0;;
		int val=0;;
		
		int i;
		int line;
		
		for(i=0; i<grid.nbColumns; i++){
			if(!grid.isColumnFull(i)){
				// Simulation du coup				
				line = grid.add(i, new Chip(this.playerIA));
				
				
				if(this.playerIA.getId()==1)
					val = this.min(new Position(line, i),grid,1);				
				else
					val = this.min(new Position(line, i),grid,5);				
				
				if((val == val_max && new Random().nextBoolean()) || val > val_max){
					
					val_max = val;
					col_max = i;
				}
				
				// Annulation du coup joué
				grid.remove(line, i);
				
			}			
		}	
		
		return new Position(-1, col_max);
	}
	
	public int min(Position posPlayed,Grid currentGrid, int depth){
		if(depth == 0 || this.gameOver())
			return 1 * this.eval(posPlayed,currentGrid,     playerIA,playerOpponent);
		
		int val_min = Integer.MAX_VALUE;
		int val;
		int i;
		int line;
		
		for(i=0; i<grid.nbColumns; i++){
			if(!currentGrid.isColumnFull(i)){
				// Simulation du coup
				line = currentGrid.add(i, new Chip(playerOpponent));
				val = this.max(new Position(line,i),currentGrid,depth-1);
				
				if((val == val_min && new Random().nextBoolean()) || val < val_min){
					val_min = val;					
				}
				
				// Annulation du coup joué
				currentGrid.remove(line, i);
				
			}			
		}
		
		return val_min;
		
		
	}
	
	public int max(Position posPlayed, Grid currentGrid, int depth){
		if(depth == 0 || this.gameOver()) 
			return this.eval(posPlayed, currentGrid, playerIA, playerOpponent);
		
		int val_max = Integer.MIN_VALUE;
		int val;
		int i;
		int line;
		
		for(i=0; i<grid.nbColumns; i++){
			if(!currentGrid.isColumnFull(i)){
				// Simulation du coup
				line = currentGrid.add(i, new Chip(this.playerIA));
				val = this.min(new Position(line,i),currentGrid,depth-1);
				
				if((val == val_max && new Random().nextBoolean()) || val > val_max){
					val_max = val;					
				}
				
				// Annulation du coup joué
				currentGrid.remove(line, i);
				
			}			
		}
		
		return val_max;
		
		
	}
	
	
	
	public int eval(Position posPlayed,Grid currentGrid, Player pCurr, Player pOpp){
		int score;
		int qualityCurr = 0;
		int qualityOpp = 0;
		
		Matcher matcher;
		Pattern pattern;
		
		HashMap<String, Integer> weight = new HashMap<String,Integer>();
		weight.put("1111", 1000-currentGrid.getnbChips()*5);
		weight.put("_111_", 50);
		weight.put("111_", 20);
		weight.put("_111", 20);
		weight.put("11_1", 20);
		weight.put("1_11", 20);
		weight.put("1__1", 10);
		weight.put("11__", 10);
		weight.put("__11", 10);		
		weight.put("1___", 2);
		weight.put("_1__", 2);
		weight.put("__1_", 2);
		weight.put("___1", 2);
		weight.put("____", 2);
	
		
		/*weight.put("2212", 30);
		weight.put("2122", 30);
		weight.put("2221", 30);
		weight.put("1222", 30);*/
		
		String currentPattern="";
		String pCurrPattern="";
		String pOppPattern="";
		int currentWeight=0;
		Iterator itePattern = weight.keySet().iterator();
		
		
		
		String fullyState = currentGrid.getStateVerticalLine()+"|"
				+currentGrid.getStateHorizontalLine()+"|"
				+currentGrid.getStateDiagonal1Line()+"|"
				+currentGrid.getStateDiagonal2Line();
		while (itePattern.hasNext())
		{
			
			currentPattern = (String)itePattern.next();
			currentWeight = (int)weight.get(currentPattern);
			
			if(pCurr.getId()!=1){
				pCurrPattern = currentPattern.replace('1', 'X').replace('2', '0');
				pOppPattern = currentPattern.replace('1', '0').replace('2', 'X');
			}
			else{
				pCurrPattern = currentPattern.replace('1', '0').replace('2', 'X');
				pOppPattern = currentPattern.replace('1', 'X').replace('2', '0');
			}
				
		    
		    
		    pattern = Pattern.compile(pCurrPattern);
		    matcher = pattern.matcher(fullyState);
		    while(matcher.find()) {		    	
		    	qualityCurr+= currentWeight;
		    }
		    
		    pattern = Pattern.compile(pOppPattern);
		    matcher = pattern.matcher(fullyState);
		    
		    while(matcher.find()) {		    	
		    	qualityOpp+= currentWeight;
		    }
		    
		   
		}
		
		
		
		
		//System.out.println("Conf testée "+posPlayed+" - j"+pCurr.getId()+"  - score : "+qualityCurr+" - "+qualityOpp+" = "+(qualityCurr - qualityOpp)+" ");
		
		
		if(pCurr != null)
			return( qualityCurr - qualityOpp );
			
		//currentGrid.showDebug();
		//grid.showDebug();
		//System.out.println("_________");
		
		
		QualityMove qmVerticalCurr = currentGrid.verticalAlignment(posPlayed,pCurr);
		QualityMove qmVerticalOpp = currentGrid.verticalAlignment(posPlayed,pOpp);		
		
		QualityMove qmHorizontalCurr = currentGrid.horizontalAlignment(posPlayed,pCurr);
		QualityMove qmHorizontalOpp = currentGrid.horizontalAlignment(posPlayed,pOpp);
		
		QualityMove qmDiagonalCurr = currentGrid.diagonalAlignment(posPlayed,pCurr);
		QualityMove qmDiagonalOpp = currentGrid.diagonalAlignment(posPlayed,pOpp);
		
		
		qualityCurr+=getWeight(qmVerticalCurr,qmVerticalOpp);
		qualityOpp +=  getWeight(qmVerticalOpp,qmVerticalCurr);			
	
		
		qualityCurr+=getWeight(qmHorizontalCurr,qmHorizontalOpp) ;
		qualityOpp +=getWeight(qmHorizontalOpp,qmHorizontalCurr);	
		
		qualityCurr+=getWeight(qmDiagonalCurr,qmDiagonalOpp)  ;
		qualityOpp +=getWeight(qmDiagonalOpp,qmDiagonalCurr);
		//currentGrid.showDebug();
		
		score = qualityCurr - qualityOpp;
		System.out.println("Conf testée "+posPlayed+" - j"+pCurr.getId()+"  - score : "+qualityCurr+" - "+qualityOpp+" = "+score*-1+" ");
		//System.out.println(""+qmVerticalCurr+" | "+qmVerticalOpp +" - "+qmHorizontalCurr+" -"+qmHorizontalOpp+"\n");
		
		return score;
	}
	
	public int getWeight(QualityMove qmCurr, QualityMove qmOpp){
		
		
		
		int weight=0;;
		
		
		// Le joueur gagne
		
		if(qmCurr.aligned >= nbChipsToWin){
			weight += 1000;
		}		
		
		
		if(qmCurr.alignedPossible >= nbChipsToWin ){
			weight+= 10;
			weight += 10*qmCurr.aligned;
			weight += qmCurr.alignedPossible;
		}
		return weight;
		/*
		
		// On peut encore gagner
		if(qmCurr.alignedPossible >= nbChipsToWin ){
			weight += 10;
			
			weight += qmCurr.alignedPossible;
			
			if(qmCurr.serieCurrPlayer >= nbChipsToWin ){
				if(qmCurr.freeBetweenSeriesCurr == 1)
					weight += 50;
				else if(qmCurr.freeBetweenSeriesCurr == 2)
					weight += 20;
				else if(qmCurr.freeBetweenSeriesCurr == 3)
					weight += 5;	
			
			}
			
			//weight += (qmCurr.stopOpposant);			
		}
		
		// On ne peut plus gagner sur cette ligne
		else
			weight -= -0;
		
		
		
	
		
		
		
		return weight;*/
		
	}

}
