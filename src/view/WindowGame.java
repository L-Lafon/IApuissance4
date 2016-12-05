package view;

import java.io.IOException;

import controller.Puissance4;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Game;

public class WindowGame extends Scene  {
	
	Puissance4 app;
	Board board = null;;
	BorderPane layout;
	
	 
	
	public WindowGame(Puissance4 app) throws IOException {
		super( new Group()  ); // obligatoire car impossible de mettre null en paramètre
		
		this.app = app;
		FXMLLoader fx = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
		fx.setController(this); 
		
		BorderPane layout=null;
		try{
		layout = fx.load();
		}
		catch(Exception e){
			System.out.println(e.getCause());
			System.exit(0);
		}
		
		/* Menu */
		MenuBar menuBar = new MenuBar(); 
	    // primary stage???
	    // vient de public void start(Stage primaryStage) {
		layout.setTop(menuBar);
		
		Menu fileMenu = new Menu("File");
	    MenuItem resetMenuItem = new MenuItem("Reset");
	    MenuItem exitMenuItem = new MenuItem("Exit");
	    /* Fonctions pour les boutons */
	    resetMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	            System.out.println("reset");
	            app.initGame();
	        }
	    });
	    exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	    
	    fileMenu.getItems().addAll(resetMenuItem, new SeparatorMenuItem(), exitMenuItem);
	    
	    Menu p1Menu = new Menu("Player 1");
	    CheckMenuItem playerMenuItem = new CheckMenuItem("Player");
	    CheckMenuItem randomMenuItem = new CheckMenuItem("Random AI");
	    CheckMenuItem simpleMenuItem = new CheckMenuItem("Simple AI");
	    CheckMenuItem minimaxMenuItem = new CheckMenuItem("Minimax AI");
	    playerMenuItem.setSelected(true);
	    /* Fonctions pour les boutons */
	    playerMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	randomMenuItem.setSelected(false);
	        	simpleMenuItem.setSelected(false);
	        	minimaxMenuItem.setSelected(false);
				//players[1].setIA(1);
	        }
	    });
	    randomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	playerMenuItem.setSelected(false);
	        	simpleMenuItem.setSelected(false);
	        	minimaxMenuItem.setSelected(false);
				//players[1].setIA(1);
	        }
	    });
	    minimaxMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	playerMenuItem.setSelected(false);
	        	randomMenuItem.setSelected(false);
	        	simpleMenuItem.setSelected(false);
	            //players[1].setIA(2);
	        }
	    });
	    simpleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	playerMenuItem.setSelected(false);
	        	randomMenuItem.setSelected(false);
	        	minimaxMenuItem.setSelected(false);
	        	//players[1].setIA(3);
	        }
	    });
	    p1Menu.getItems().addAll(playerMenuItem, randomMenuItem, simpleMenuItem, minimaxMenuItem);
	    
	    
	    Menu p2Menu = new Menu("Player 2");
	    CheckMenuItem player2MenuItem = new CheckMenuItem("Player");
	    CheckMenuItem random2MenuItem = new CheckMenuItem("Random AI");
	    CheckMenuItem simple2MenuItem = new CheckMenuItem("Simple AI");
	    CheckMenuItem minimax2MenuItem = new CheckMenuItem("Minimax AI");
	    random2MenuItem.setSelected(true);
	    /* Fonctions pour les boutons */
	    player2MenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	random2MenuItem.setSelected(false);
	        	simple2MenuItem.setSelected(false);
	        	minimax2MenuItem.setSelected(false);
				//players[1].setIA(1);
	        }
	    });
	    random2MenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	player2MenuItem.setSelected(false);
	        	simple2MenuItem.setSelected(false);
	        	minimax2MenuItem.setSelected(false);
				//players[1].setIA(1);
	        }
	    });
	    minimax2MenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	player2MenuItem.setSelected(false);
	        	random2MenuItem.setSelected(false);
	        	simple2MenuItem.setSelected(false);
	            //players[1].setIA(2);
	        }
	    });
	    simple2MenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	// Deselectionne les autres menus (impossible d'en avoir deux en mm tps)
	        	player2MenuItem.setSelected(false);
	        	random2MenuItem.setSelected(false);
	        	minimax2MenuItem.setSelected(false);
	        	//players[1].setIA(3);
	        }
	    });
	    p2Menu.getItems().addAll(player2MenuItem, random2MenuItem, simple2MenuItem, minimax2MenuItem);
	    
	    Menu playpauseMenu = new Menu("Play/Pause");
	    //MenuItem playpauseMenuItem = new MenuItem("Play/pause");
	    playpauseMenu.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	System.out.println("Play/Pause");
	        }
	    });
	    //playpauseMenu.getItems().add(playpauseMenuItem);
	    menuBar.getMenus().addAll(fileMenu,p1Menu,p2Menu,playpauseMenu);
	    /* fin Menu */
		
		board = new Board(this);
		layout.setCenter(  board  );
		

		
		System.out.println(layout.getCenter());
		//System.out.println("Window:"+stage.getWidth());
		
		this.setRoot(layout);
		
		
		System.out.println(layout.getCenter());
		
		
		
		
		/*
		 * 
		 *this.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		        System.out.println("Width board: " + newSceneWidth);
		    }
		});
		this.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		        System.out.println("Height: " + newSceneHeight);
		    }
		});*/
		
		 
	}
	
	public void constructLayout(){
		
	}
	
	public Puissance4 getApp(){
		return this.app;
	}
	
	public void update(Color [][] data, int lines, int columns){		
		System.out.println(lines+"-"+columns);
		board.update(data,lines,columns);	
		
		//System.out.println("Mise à jour de la vue effectuée");
	}
	
	 
	@FXML
	private Label indication;
	 
	public void setIndication(String text){
		//System.out.println("indication : "+text);
		this.indication.setText(text);
	}
	
	public void selectColumnAction(int c){
		this.getApp().insertChip(c);
	}

}
