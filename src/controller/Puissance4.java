package controller;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ia.IA;
import ia.IAMinMax;
import ia.IARandom;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;
import model.Chip;
import model.Game;
import model.Grid;
import model.Player;
import model.Position;
import view.WindowGame;

public class Puissance4 extends Application {
	
	public static boolean STAT_ANALYSE_ON = false;
	
	
	private Stage stage;
		
	public Game game;
	
	public WindowGame windowGame;
	
	public int[] histo;
	
	public static void main(String[] args) {
		System.out.println("-- Lancement application --");
		
		/*
		// [X]{1}[_]{3,}|[X]{2}[_]{2,}|[X]{3}[_]{1,}
		Pattern pattern = Pattern.compile("[X]{4}");
	    Matcher matcher = pattern.matcher("XXXX_XXXX");
	    while(matcher.find()) {		    	
	    	System.out.println(matcher.group());
	    }
	    
	    if(true)
	    return ;
	    */
		
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
		
		
		resetGame();
		
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
		game.reset();

		this.histo = new int[42];
		
		// Si c'est à l'IA de jouer, elle va jouer, sinon rien faire
		nextRound();
	}
	
	public void resetGame(){
		initGame();
		
		//game=new Game();
		this.updateView();
		this.updateViewIA();
	}
	
	public void insertChip(int column){
		Grid grid = game.getGrid();
		int line;
		
		if(!game.gameOver() && !grid.isColumnFull(column)){

			// stocke le num�ro de la colonne, column, � chaque tour {0,...,6}
			this.histo[grid.getnbChips()] = column+1;
			//System.out.println(this.histo[grid.getnbChips()]);

			
			line = grid.add(column, new Chip(game.getCurrentPlayer()));
			
			// Vérification si pions alignés par rapport au dernier pion déposé
			//int winner;
			if(existsAlignment()){
				//this.winner=this.pions[line][column].getPlayer();
				game.setWinner(game.getCurrentPlayer());
				//windowGame.setIndication("Victoire de "+game.getCurrentPlayer().getName()+"");
				
				
				
				//System.out.println("Victoire de "+game.getWinner());
				
			}
			else{
			
				this.switchPlayer();
			
			}
			
			
			
			
			this.updateView();
			this.updateViewIA();
	
			
			
			this.nextRound();
		
		}
		
	}
	
	public void fixPlayer(int index){
		game.setIndexCurrentPlayer( index  );	
		windowGame.setIndication("Au tour de "+game.getCurrentPlayer().getName()+" de jouer");
	}
	
	public void switchPlayer(){		
		this.fixPlayer(   (game.getIndexCurrentPlayer() == 0) ? 1 : 0   );		
	}
	
	public void nextRound(){
		if(game.gameOver()){	
			
			if(game.grid.isFull()){
				windowGame.setIndication("Match Nul !");
			}
			else if(game.getWinner() != null){
				windowGame.setIndication("Victoire de "+game.getWinner().getName());
			}
			
			if(Puissance4.STAT_ANALYSE_ON == true)
				statAnalyseOn();
			
		}
		else{
			
			if(game.getCurrentPlayer().isIA() ){
				this.searchIA();
			}
		}
		
	}
	
	public void statAnalyseOn(){
		/*File f = new File("ia_random__random.txt");
		try{
		FileWriter fw = new FileWriter(f,true);
		
		int playerWinner = (game.grid.isFull()) ? 0 : game.getWinner().getId();
		fw.write(""+game.grid.getnbChips()+";"+playerWinner+"\r\n");
		fw.close();
		}
		catch(Exception e){
			
		}
		*/
		int playerWinner = (game.grid.isFull()) ? 0 : game.getWinner().getId();
		Stat.add(game.grid.getnbChips(), playerWinner);
		Stat.showResults();
		
		this.resetGame();
		
	}
	
	public void searchIA(){		
		
		// Si ce ne sont pas 2 IA qui jouent, on indique que l'ordinateur est en train de calculer
		if(!(game.getCurrentPlayer().isIA() && game.getOpponentPlayer().isIA()))
			windowGame.setIndication(""+game.getCurrentPlayer().getName()+" réfléchit...");
		
		Thread threadIA = new Thread(Integer.toString(game.currentGameId)) {
			
			Position p=null;;
			long timeExecution;
			
			public void run() {
				
				int typeIA = game.getCurrentPlayer().getTypeIA();
				IA ia=null;;
				
				
				if(typeIA == IA.IA_RANDOM){					
					ia = new IARandom(typeIA,game.getGrid());					
					
				}
				if(typeIA == IA.IA_MINMAX_H1_1 ||
						typeIA == IA.IA_MINMAX_H1_2||
						typeIA == IA.IA_MINMAX_H2_1 ||
						typeIA == IA.IA_MINMAX_H2_2){
								
					ia = new IAMinMax(typeIA, game.getGrid(), game.getCurrentPlayer(), game.getOpponentPlayer());
					
				}
				
				if(ia == null)
					return;
				
				p = ia.play();
				timeExecution = ia.getTimeSearch();
				int timeToSleep = -3000 - (int)(timeExecution/1000);
				
				try{
					Thread.sleep(timeToSleep);
				}
				catch(Exception e){
					
				}
				
				
				// Si la recherche concerne pas une partie précédente (peut-être réinitialisée)
				if(this.getName().equals(Integer.toString(game.currentGameId))){
					Platform.runLater(
						() -> playIA(p));
				}
				
				
				
				
		
			}
			
		};
		
		threadIA.start();
		
		
		
		
		
		

	}

	public void playHumain(int c){
		if(this.game.getCurrentPlayer().getTypeIA() == 0)
			insertChip(c);
	}
	
	public void playIA(Position p){
		if(p != null)		
			insertChip(p.getCol());
		else
			System.out.println("IA NE SAIT PLUS QUOI FAIRE");
	}
	
	public void setTypeIA(int pId, int tIA){
		Player[] players = game.getPlayers();
		players[pId].setIA(tIA);
		nextRound();
		updateViewIA();
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
	

	public void updateViewIA(){
		Player[] players = game.getPlayers();
		windowGame.updateMenuIA(players[0].getTypeIA(), players[1].getTypeIA());
	}
	
	
	
	

	public String getStrHistoCoups() {
		String s = "";
		for (int i=0; i<42; i++){
			s+= this.histo[i]+", ";
		}
		return s;
	}

	
}
