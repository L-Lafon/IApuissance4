package model;

public class IA {
	
	/*Game game;
	Board grid;
	
	private Player playerIA, playerOpponent;
	
	IA(Board grid, Player pIA, Player pOpp){
		this.grid = grid;
		this.playerIA = pIA;
		this.playerOpponent = pOpp;
	}
	
	public void play(){
		int val_max = -10000;
		int col_max;
		int val;
		
		int i;
		int line;
		
		for(i=0; i<Board.NB_COLUMNS; i++){
			if(!grid.isColumnFull(i)){
				// Simulation du coup
				line = grid.add(i, new Pion(game.getCurrentPlayer()));
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
	
	public int min(Board currentGrid, int depth){
		if(depth == 0 )// || FIN JEU)
			return this.eval(currentGrid);
		
		int val_min = -10000;
		int val;
		int i;
		int line;
		
		for(i=0; i<Board.NB_COLUMNS; i++){
			if(!currentGrid.isColumnFull(i)){
				// Simulation du coup
				line = currentGrid.add(i, new Pion(game.getCurrentPlayer()));
				val = this.max(game.board,depth-1);
				
				if(val < val_min){
					val_min = val;					
				}
				
				// Annulation du coup joué
				currentGrid.remove(line, i);
				
			}			
		}
		
		return val_min;
		
		
	}
	
	public int max(Board currentGrid, int depth){
		if(depth == 0) // FIN JEU)
			return this.eval(currentGrid);
		
		int val_max = -10000;
		int val;
		int i;
		int line;
		
		for(i=0; i<Board.NB_COLUMNS; i++){
			if(!currentGrid.isColumnFull(i)){
				// Simulation du coup
				line = currentGrid.add(i, new Pion(game.getCurrentPlayer()));
				val = this.min(game.board,depth-1);
				
				if(val > val_max){
					val_max = val;					
				}
				
				// Annulation du coup joué
				currentGrid.remove(line, i);
				
			}			
		}
		
		return val_max;
		
		
	}
	
	public int eval(Board currentGrid, Player currentPlayer){
		return 1;
	}

*/
}

