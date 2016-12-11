package ia;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import model.Grid;
import model.Player;
import model.Position;

public abstract class IA {
	

	
	int typeIA;
	Grid grid;
	Player playerIA, playerOpponent;
	int nbChipsToWin=4;
	boolean gameOver;
	
	
	long startExecution, endExecution;
	
	
	
	
	
	
	
	
	IA(int typeIA,Grid grid){
		this(typeIA,grid, null,null);
	}
	
	IA(int typeIA,Grid grid, Player pIA, Player pOpp){
		this.typeIA=typeIA;
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
	
	public Position play(){
		return null;
	}
	
	public ArrayList<Integer> getPossibleColumns(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<grid.nbColumns; i++){
			if(!grid.isColumnFull(i)){
				list.add(i);
			}
		}
		
		return list;
	}
	
	public int getRandomInPossible(ArrayList<Integer> p){
		int random = new Random().nextInt(p.size());
		return p.get(random);
	}
	
	public final static int IA_RANDOM = 1;
	public final static int IA_MINMAX_H1_1 = 2;
	public final static int IA_MINMAX_H1_2 = 3;
	public final static int IA_MINMAX_H2_1 = 4;
	public final static int IA_MINMAX_H2_2 = 5;
	
	public static String[] getTypes(){
		String[] tab = new String[6];
		tab[0] = "Humain";
		tab[1] = "AI Random";
		tab[2] = "AI MinMax (h1) - easy";
		tab[3] = "AI MinMax (h1) - hard";
		tab[4] = "AI MinMax (h2) - easy";
		tab[5] = "AI MinMax (h2) - hard";
		
		return tab;
	}

	
	
}

