package model;

import javafx.scene.paint.Color;

public class Player {
	
	private int id;
	private Color color;
	private String name;
	private int ia;
	
	Player(int i,Color c, String n){
		id = i;
		color = c;
		name = n;
		ia=0;
	}
	public int getId(){
		return id;
	}
	public Color getColor(){
		return color;
	}
	
	public String getName(){
		return name;
	}
	
	public void setIA(int ia){
		this.ia = ia;
	}
	
	public boolean isIA(){
		return this.ia > 0;
	}
	
	public int getTypeIA(){
		return this.ia;
	}
	
	
	

}
