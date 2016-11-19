package ia;

import model.Grid;
import model.Player;
import model.Position;

public class IA {
	
	public final static int IA_RANDOM = 1;
	public final static int IA_2 = 2;
	
	Grid grid;
	Player playerIA, playerOpponent;
	int nbChipsToWin=4;
	boolean gameOver;
	
	
	long startExecution, endExecution;
	
	
	
	
	
	
	
	
	IA(Grid grid){
		this(grid, null,null);
	}
	
	IA(Grid grid, Player pIA, Player pOpp){
		this.grid = grid;
		this.playerIA = pIA;
		this.playerOpponent = pOpp;
		this.gameOver=false;
		
		startExecution = endExecution = 0;
		
	}
	
	public boolean gameOver(Position p, Player player){
		if(grid.existsAlignment(p,player))
			return true;
		
		if(grid.isFull())
				return true;
		return false;
	}
	
	public void startSearch(){
		startExecution = System.nanoTime();;
	}
	public void endSearch(){
		endExecution = System.nanoTime();;
	}
	public long getTimeSearch(){
		return (endExecution - startExecution) / 1000;
	}

	
	
}

