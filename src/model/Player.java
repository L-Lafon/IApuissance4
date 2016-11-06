package model;

import javafx.scene.paint.Color;

public class Player {
	
	private int id;
	private Color color;
	private String name;
	private boolean ia;
	
	Player(int i,Color c, String n){
		id = i;
		color = c;
		name = n;
		ia=false;
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
	
	public void setIA(boolean bool){
		this.ia = bool;
	}
	
	
	

}
