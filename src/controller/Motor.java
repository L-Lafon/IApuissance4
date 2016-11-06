package controller;

import ihm.Puissance4;
import model.Chip;
import model.Game;
import model.Grid;
import model.Position;

public class Motor {
	
	Puissance4 app;
	
	
	public Motor(Puissance4 app){
		this.app=app;		
	}
	
	public void initGame(){
		this.fixPlayer(0);
		app.game.setWinner(null);
	}
	
	public void insertChip(int column){
		Grid grid = this.app.game.getGrid();
		int line;
		
		if(!app.game.gameOver() && !grid.isColumnFull(column)){
			line = grid.add(column, new Chip(app.game.getCurrentPlayer()));
		
			// Vérification si pions alignés par rapport au dernier pion déposé
			//int winner;
			if((grid.existsAlignment(new Position(line,column))) != false){
				//this.winner=this.pions[line][column].getPlayer();
				app.game.setWinner(app.game.getCurrentPlayer());
				app.windowGame.setIndication("Victoire de "+app.game.getCurrentPlayer().getName()+"");
				System.out.println("Victoire de "+this.app.game.getWinner());
			}
			else{
			
				this.switchPlayer();
			
			}
			
			
			this.updateView();
			app.game.grid.showDebug();
		
		}
	}
	
	public void fixPlayer(int index){
		app.game.setIndexCurrentPlayer( index  );	
		app.windowGame.setIndication("Au tour de "+app.game.getCurrentPlayer().getName()+"");
	}
	
	public void switchPlayer(){		
		this.fixPlayer(   (app.game.getIndexCurrentPlayer() == 0) ? 1 : 0   );		
	}
	
	public void updateView(){
		Grid grid = this.app.game.getGrid();
		System.out.println("Mise à jour de la vue demandée par le moteur");
		this.app.windowGame.update(grid.getDataView(),grid.nbLines, grid.nbColumns);
	}

}
