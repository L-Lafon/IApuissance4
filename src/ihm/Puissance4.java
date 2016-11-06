package ihm;

import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;
import controller.Motor;

public class Puissance4 extends Application{
	
	private Stage stage;
	public Motor motor;
	
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
		System.out.println("VU!");
		
		// Déclaration du moteur
		motor = new Motor(this);

		// Déclaration des fenêtres
		
		
		
		windowGame = new WindowGame(this);	
		
		
		motor.initGame();
		
		
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
		
		motor.updateView();
		
	}
	
	public void stop(){
		System.out.println("-- Fin de l'application --");
		System.exit(1);
	}
	
	public Stage getStage(){
		return this.stage;
	}
	
	
	
}
