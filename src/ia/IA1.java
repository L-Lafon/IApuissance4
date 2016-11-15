package ia;

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
					val = this.min(new Position(line, i),grid,0);				
				else
					val = this.min(new Position(line, i),grid,0);				
				
				if(val > val_max){
					
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
		
				
		int winIsPossible = 0;
		int quality = 0;
		
		if( currentGrid.existsAlignment(posPlayed, pCurr) )
			quality += 1000;
		else if( currentGrid.existsAlignment(posPlayed, pOpp) )
			quality += -1000;
		
		
		
		QualityMove qmVertical1 = grid.verticalAlignment(posPlayed,pCurr);
		QualityMove qmVertical2 = grid.verticalAlignment(posPlayed,pOpp);
		
		
		quality+=qmVertical1.aligned-qmVertical2.aligned ;
		
			
		
		QualityMove qmHorizontal1 = grid.horizontalAlignment(posPlayed,pCurr);
		QualityMove qmHorizontal2 = grid.horizontalAlignment(posPlayed,pOpp);
		
		
		quality+=qmHorizontal1.aligned - qmHorizontal2.aligned ;
		
				
		QualityMove qmDiagonal1 = grid.diagonalAlignment(posPlayed,pCurr);
		QualityMove qmDiagonal2 = grid.diagonalAlignment(posPlayed,pOpp);
		
		
		//currentGrid.showDebug();
		System.out.println("Conf testée "+posPlayed+" - score : "+quality+" - "+qmVertical1+" | "+qmVertical2);
		return quality;
	}

}
