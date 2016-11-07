package ia;

import model.Chip;
import model.Grid;
import model.Player;

public class IA1 extends IA{
	
	public IA1(Grid grid, Player pIA, Player pOpp){
		super(grid);
	}
	
	public void play(){
		int val_max = -10000;
		int col_max;
		int val;
		
		int i;
		int line;
		
		for(i=0; i<grid.nbColumns; i++){
			if(!grid.isColumnFull(i)){
				// Simulation du coup
				line = grid.add(i, new Chip(this.playerIA));
				val = this.min(grid,2);
				
				if(val > val_max){
					val_max = val;
					col_max = i;
				}
				
				// Annulation du coup joué
				grid.remove(line, i);
				
			}			
		}
	}
	
	public int min(Grid currentGrid, int depth){
		if(depth == 0 )// || FIN JEU)
			return this.eval(currentGrid);
		
		int val_min = -10000;
		int val;
		int i;
		int line;
		
		for(i=0; i<grid.nbColumns; i++){
			if(!currentGrid.isColumnFull(i)){
				// Simulation du coup
				line = currentGrid.add(i, new Chip(playerOpponent));
				val = this.max(currentGrid,depth-1);
				
				if(val < val_min){
					val_min = val;					
				}
				
				// Annulation du coup joué
				currentGrid.remove(line, i);
				
			}			
		}
		
		return val_min;
		
		
	}
	
	public int max(Grid currentGrid, int depth){
		if(depth == 0) // FIN JEU)
			return this.eval(currentGrid);
		
		int val_max = -10000;
		int val;
		int i;
		int line;
		
		for(i=0; i<grid.nbColumns; i++){
			if(!currentGrid.isColumnFull(i)){
				// Simulation du coup
				line = currentGrid.add(i, new Chip(this.playerIA));
				val = this.min(currentGrid,depth-1);
				
				if(val > val_max){
					val_max = val;					
				}
				
				// Annulation du coup joué
				currentGrid.remove(line, i);
				
			}			
		}
		
		return val_max;
		
		
	}
	
	public int eval(Grid currentGrid, Player currentPlayer){
		return 1;
	}

}
