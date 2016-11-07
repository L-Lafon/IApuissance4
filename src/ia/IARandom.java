package ia;

import java.util.ArrayList;
import java.util.Random;

import model.Grid;
import model.Position;

public class IARandom extends IA {
	
	
	
	public IARandom(Grid grid){
		super(grid);
	}
	
	public Position play(){
		
		ArrayList<Integer> columnsAvailable = new ArrayList<Integer>();
		for(int i=0; i<grid.nbColumns; i++){
			if(!grid.isColumnFull(i))
				columnsAvailable.add(i);
		}
		
		Random rand = new Random();
		int cRandom = rand.nextInt(columnsAvailable.size());
		return new Position(0,cRandom);
		
	}

}
