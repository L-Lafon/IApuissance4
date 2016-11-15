package model;

public class Chip {
	
	Player player;
	
	// Joueur à qui est le pion
	
	public Chip(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public String toString(){
		return "Joueur";
	}
	

}
