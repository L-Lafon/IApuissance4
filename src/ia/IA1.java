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
					val = this.min(new Position(line, i),grid,1);				
				else
					val = this.min(new Position(line, i),grid,4);				
				
				if((val >= val_max && new Random().nextBoolean()) || val > val_max){
					
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
		
				
	
		int quality = 0;
		
		if( currentGrid.existsAlignment(posPlayed, pCurr) )
			quality += 1000;
		else if( currentGrid.existsAlignment(posPlayed, pOpp) )
			quality += -1000;
		
		
		
		QualityMove qmVerticalCurr = grid.verticalAlignment(posPlayed,pCurr);
		QualityMove qmVerticalOpp = grid.verticalAlignment(posPlayed,pOpp);
		
		
		quality+=getWeight(qmVerticalCurr,qmVerticalOpp) - getWeight(qmVerticalOpp,qmVerticalCurr);
		
		
		
			
		
		QualityMove qmHorizontalCurr = grid.horizontalAlignment(posPlayed,pCurr);
		QualityMove qmHorizontalOpp = grid.horizontalAlignment(posPlayed,pOpp);
		
		
		//quality+=qmHorizontal1.aligned - qmHorizontal2.aligned ;
		quality+=getWeight(qmHorizontalCurr,qmHorizontalOpp) - getWeight(qmHorizontalOpp,qmHorizontalCurr) ;
				
		QualityMove qmDiagonalCurr = grid.diagonalAlignment(posPlayed,pCurr);
		QualityMove qmDiagonalOpp = grid.diagonalAlignment(posPlayed,pOpp);
		
		//quality+=qmDiagonal1.aligned - qmDiagonal2.aligned ;
		quality+=getWeight(qmDiagonalCurr,qmDiagonalOpp) - getWeight(qmDiagonalOpp,qmDiagonalCurr) ;
		//currentGrid.showDebug();
		System.out.println("Conf testée "+posPlayed+" - score : "+quality+" \n  "+qmVerticalCurr+" | "+qmVerticalOpp +" - "+qmHorizontalCurr+" -"+qmHorizontalOpp+"\n");
		
		return quality;
	}
	
	public int getWeight(QualityMove qmCurr, QualityMove qmOpp){
		
		final int coef_aligned = 10;
		
		int weight=0;;
		
		
		
		
		// It could win
		if(qmCurr.alignedPossible >= nbChipsToWin ){
			weight += 20;
			weight += qmCurr.aligned*coef_aligned;
		}
		else
			weight -= 0;
		
		
		
		return weight;
	}

}
