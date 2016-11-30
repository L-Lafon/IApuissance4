package controller;

import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ia.IA;
import ia.IA1;
import ia.IARandom;
import javafx.application.Application;
import javafx.application.Platform;
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

public class Puissance4 extends Application {
	
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
		stage.setHeight(630);
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
		searchIA();
	}
	
	public void resetGame(){
		initGame();
		game=new Game();
		this.updateView();
	}
	
	public void insertChip(int column){
		Grid grid = game.getGrid();
		int line;
		
		if(!game.gameOver() && !grid.isColumnFull(column)){
			
			line = grid.add(column, new Chip(game.getCurrentPlayer()));
			
			// Vérification si pions alignés par rapport au dernier pion déposé
			//int winner;
			if(existsAlignment()){
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
		if(!game.gameOver()){
			if(game.grid.isFull())
				windowGame.setIndication("Match Nul !");
			
			if(game.getCurrentPlayer().isIA() && !game.gameOver()){
				this.searchIA();
			}
		}
	}
	
	public void searchIA(){		
		
		
		Thread threadIA = new Thread() {
			
			Position p=null;;
			long timeExecution;
			
			public void run() {
				
				
				
				
				
				if(game.getCurrentPlayer().getTypeIA() == IA.IA_RANDOM){
					
					IARandom ia = (IARandom) new IARandom(game.getGrid());
					p = ia.play();
					timeExecution = ia.getTimeSearch();
					
				}
				if(game.getCurrentPlayer().getTypeIA() == IA.IA_2){
								
					IA1 ia = (IA1) new IA1(game.getGrid(), game.getCurrentPlayer(), game.getOpponentPlayer());	
					p = ia.play();
					timeExecution = ia.getTimeSearch();
				}
				
				int timeToSleep = 300 - (int)(timeExecution/1000);
				
				try{
					Thread.sleep(timeToSleep);
				}
				catch(Exception e){
					
				}
				
				
				Platform.runLater(
						() -> playIA(p));
				
				
				
				
		
			}
			
		};
		
		threadIA.start();
		
		
		
		
		
		

	}

	
	public void playIA(Position p){
		if(p != null)		
			insertChip(p.getCol());
		else
			System.out.println("IA NE SAIT PLUS QUOI FAIRE");
	}
	
	public boolean existsAlignment(){
		String state = game.grid.getStateLines();
		
		Pattern pattern = Pattern.compile("XXXX|0000");;
	    return pattern.matcher(state).find();
	}
	
	public void updateView(){
		Grid grid = game.getGrid();
		//System.out.println("Mise à jour de la vue demandée par le moteur");
		windowGame.update(grid.getDataView(),grid.nbLines, grid.nbColumns);
	}
	
	
	
}
