package ia;

import java.util.regex.Pattern;

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
	
	public boolean gameOver(){
		
		
		if(grid.isFull())
				return true;
		
		String state = grid.getStateLines();
		
		Pattern pattern = Pattern.compile("XXXX|0000");;
	    return pattern.matcher(state).find();
		
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

