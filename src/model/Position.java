package model;
import java.awt.Point;

public class Position extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Position(int row, int col){
		super(col,row);
	}
	
	public int getRow(){
		return this.y;
	}
	
	public int getCol(){
		return this.x;
	}
}
