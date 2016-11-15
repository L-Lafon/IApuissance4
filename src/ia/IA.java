package ia;

import model.Grid;
import model.Player;
import model.Position;

public class IA {
	
	public final static int IA_RANDOM = 1;
	public final static int IA_2 = 2;
	
	Grid grid;
	Player playerIA, playerOpponent;
	int nbChipsForWinning=4;
	boolean gameOver;
	
	IA(Grid grid){
		this(grid, null,null);
	}
	
	IA(Grid grid, Player pIA, Player pOpp){
		this.grid = grid;
		this.playerIA = pIA;
		this.playerOpponent = pOpp;
		this.gameOver=false;
	}
	
	public boolean gameOver(Position p, Player player){
		if(grid.existsAlignment(p,player))
			return true;
		
		if(grid.isFull())
				return true;
		return false;
	}
}

