package controller;

import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ia.IA;
import ia.IA1;
import ia.IARandom;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Chip;
import model.Game;
import model.Grid;
import model.Player;
import model.Position;
import view.WindowGame;

public class Puissance4 extends Application{
	
	private Stage stage;
		
	public Game game;
	
	public WindowGame windowGame;
	
	public static void main(String[] args) {
		System.out.println("-- Lancement application --");
		
		try{
			Application.launch(Puissance4.class, args);
		}
		catch(Exception e){
			System.out.println(e);
		}
       

    }

	@Override
	public void start(Stage stage) throws Exception {
		
		this.stage = stage;
		
		// Paramètres de la fenêtre
		stage.setWidth(800);
		stage.setHeight(600);
		stage.setTitle("Projet IA : Puissance 4");
		
		//...
		game = new Game();
		
		
		// Déclaration des fenêtres		
		
		windowGame = new WindowGame(this);	
		
		
		this.initGame();
		
		
		// Par défaut : fenêre de la partie affichée
		stage.setScene(windowGame);		
		stage.show();
		
		
		/*@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(this.game.getWinner() == 0){
			

			System.out.println("Veuillez saisir une colonne (j"+game.getCurrentPlayer()+") :");
			String str = sc.nextLine();
			if(str.equals("quit") || str.equals(""))
				return;
			System.out.println(str);
			this.motor.insertChip(Integer.parseInt(str));
			
			this.game.grid.showDebug();
			
			game.setCurrentPlayer( (game.getCurrentPlayer() == 1) ? 2 : 1  );
		}
		
		sc.close();*/
		
		this.updateView();
		
	}
	
	public void stop(){
		System.out.println("-- Fin de l'application --");
		System.exit(1);
	}
	
	public Stage getStage(){
		return this.stage;
	}
	
	public void initGame(){
		this.fixPlayer(0);
		game.setWinner(null);
		
		// Si c'est à l'IA de jouer, elle va jouer, sinon rien faire
		playIA();
	}
	
	public void insertChip(int column){
		Grid grid = game.getGrid();
		int line;
		
		if(!game.gameOver() && !grid.isColumnFull(column)){
			
			line = grid.add(column, new Chip(game.getCurrentPlayer()));
		
			// Vérification si pions alignés par rapport au dernier pion déposé
			//int winner;
			if((grid.existsAlignment(new Position(line,column),game.getCurrentPlayer()))){
				//this.winner=this.pions[line][column].getPlayer();
				game.setWinner(game.getCurrentPlayer());
				windowGame.setIndication("Victoire de "+game.getCurrentPlayer().getName()+"");
				System.out.println("Victoire de "+game.getWinner());
				
			}
			else{
			
				this.switchPlayer();
			
			}
			
			
			this.updateView();
			/*game.grid.showDebug();
			try{
				Thread.sleep(500);
			}
			catch(Exception e){
				
			}*/
			
			
			this.nextRound();
		
		}
	}
	
	public void fixPlayer(int index){
		game.setIndexCurrentPlayer( index  );	
		windowGame.setIndication("Au tour de "+game.getCurrentPlayer().getName()+"");
	}
	
	public void switchPlayer(){		
		this.fixPlayer(   (game.getIndexCurrentPlayer() == 0) ? 1 : 0   );		
	}
	
	public void nextRound(){
		
		if(game.getCurrentPlayer().isIA() && !game.gameOver()){
			this.playIA();
		}
	}
	
	public void playIA(){
		Player curPlayer = game.getCurrentPlayer();
		Position p = null;;
		
		Player playerIA = game.getCurrentPlayer();
		//PlayerOpponent = 
		if(game.getCurrentPlayer().getTypeIA() == IA.IA_RANDOM){
			IARandom ia;
			ia = (IARandom) new IARandom(game.getGrid());	
			p = ia.play();
		}
		if(game.getCurrentPlayer().getTypeIA() == IA.IA_2){
			IA1 ia;
			
			ia = (IA1) new IA1(game.getGrid(), game.getCurrentPlayer(), game.getOpponentPlayer());	
			p = ia.play();
		}
		
		if(p != null)		
			insertChip(p.getCol());
		else
			System.out.println("IA NE SAIT PLUS QUOI FAIRE");
	}
	
	public void updateView(){
		Grid grid = game.getGrid();
		//System.out.println("Mise à jour de la vue demandée par le moteur");
		windowGame.update(grid.getDataView(),grid.nbLines, grid.nbColumns);
	}
	
	
	
}
