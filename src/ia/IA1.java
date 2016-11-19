package ia;

import java.util.Random;

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
					val = this.min(new Position(line, i),grid,5);				
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
		if(depth == 0 || this.gameOver(posPlayed,playerOpponent))// || FIN JEU)
			return -1 * this.eval(posPlayed,currentGrid,   playerOpponent, playerIA);
		
		int val_min = Integer.MAX_VALUE;
		int val;
		int i;
		int line;
		
		for(i=0; i<grid.nbColumns; i++){
			if(!currentGrid.isColumnFull(i)){
				// Simulation du coup
				line = currentGrid.add(i, new Chip(playerOpponent));
				val = this.max(new Position(line,i),currentGrid,depth-1);
				
				if(val < val_min){
					val_min = val;					
				}
				
				// Annulation du coup joué
				currentGrid.remove(line, i);
				
			}			
		}
		
		return val_min;
		
		
	}
	
	public int max(Position posPlayed, Grid currentGrid, int depth){
		if(depth == 0 || this.gameOver(posPlayed,playerIA)) // FIN JEU)
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
				
				if(val > val_max){
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
		
		
		QualityMove qmVerticalCurr = grid.verticalAlignment(posPlayed,pCurr);
		QualityMove qmVerticalOpp = grid.verticalAlignment(posPlayed,pOpp);		
		
		QualityMove qmHorizontalCurr = grid.horizontalAlignment(posPlayed,pCurr);
		QualityMove qmHorizontalOpp = grid.horizontalAlignment(posPlayed,pOpp);
		
		QualityMove qmDiagonalCurr = grid.diagonalAlignment(posPlayed,pCurr);
		QualityMove qmDiagonalOpp = grid.diagonalAlignment(posPlayed,pOpp);
		
		
		
		qualityCurr+=getWeight(qmHorizontalCurr,qmHorizontalOpp) ;
		qualityOpp +=getWeight(qmHorizontalOpp,qmHorizontalCurr);		
		
		qualityCurr+=getWeight(qmVerticalCurr,qmVerticalOpp);
		qualityOpp +=  getWeight(qmVerticalOpp,qmVerticalCurr);		
		
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
		
		if(qmCurr.aligned == this.nbChipsToWin){
			weight += 500;
		}		
		
		
		
		// On peut encore gagner
		if(qmCurr.alignedPossible >= nbChipsToWin ){
			weight += 10;
			weight += qmCurr.aligned*10;
			weight += qmCurr.alignedPossible;
			
			if(qmCurr.serieCurrPlayer >= nbChipsToWin ){
				if(qmCurr.freeBetweenSeriesCurr == 1)
					weight += 50;
				else if(qmCurr.freeBetweenSeriesCurr == 2)
					weight += 20;
				else if(qmCurr.freeBetweenSeriesCurr == 3)
					weight += 5;	
			
			}
			
			weight += (qmCurr.stopOpposant);			
		}
		
		// On ne peut plus gagner sur cette ligne
		else
			weight -= -10;
		
		
		
	
		
		
		
		return weight;
	}

}
