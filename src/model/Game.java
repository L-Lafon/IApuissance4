package model;

import java.util.Scanner;

import javafx.scene.paint.Color;

public class Game {
	public int currentGameId=0;
	public Grid grid;
	public Player[] players;
	int nbPionToWin=4;
	int currentPlayerIndex;
	Player winner;
	
	
	/*public static void main(String[] args){
		new Game();
		
		
	}*/
	
	
	public Game(){
		
		
		players = new Player[2];
		players[0] = new Player(1,Color.YELLOW, "Joueur 1","0");
		players[0].setIA(3);
	
		players[1] = new Player(2,Color.RED, "Joueur 2","X");
		players[1].setIA(3);
		
		
		this.reset();
				
	}	
	
	

	
	public Grid getGrid(){
		return this.grid;
	}
	
	public int getIndexCurrentPlayer(){
		return currentPlayerIndex;
		//return players[currentPlayer];
	}
	
	public int setIndexCurrentPlayer(int val){
		currentPlayerIndex = val;
		return currentPlayerIndex;
	}
	public Player getCurrentPlayer(){
		return players[currentPlayerIndex];
	}
	
	public Player getOpponentPlayer(){
		return players[  getIndexCurrentPlayer() == 1 ? 0 : 1  ];
	}
	
	public Player getWinner(){
		return this.winner;
	}
	
	public void setWinner(Player val){
		this.winner=val;
	}
	
	public boolean gameOver(){
		return this.winner != null || grid.isFull();
	}
	
	public void reset(){
		
		currentGameId++;
		winner=null;		
		this.currentPlayerIndex=0;	
		grid=new Grid();
		
	}
	
	
}
