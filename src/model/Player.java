package model;

import javafx.scene.paint.Color;

public class Player {
	
	private int id;
	private Color color;
	private String name;
	private int ia;
	private String symbol;
	
	Player(int i,Color c, String n,String s){
		id = i;
		color = c;
		name = n;
		ia=0; // 0,1,2 ou 3
		symbol=s;
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
	
	public String getSymbol(){
		return symbol;
	}
	
	public String toString(){
		return "Player";
	}
	
	
	

}
