package ia;

import model.Grid;
import model.Player;

public class IA {
	
	public final static int IA_RANDOM = 1;
	
	Grid grid;
	Player playerIA, playerOpponent;
	
	IA(Grid grid){
		this(grid, null,null);
	}
	
	IA(Grid grid, Player pIA, Player pOpp){
		this.grid = grid;
		this.playerIA = pIA;
		this.playerOpponent = pOpp;
	}
	/*
	
	
	
	

*/
}

