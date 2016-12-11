package view;

import java.io.IOException;

import controller.Puissance4;
import ia.IA;
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
	
	
	CheckMenuItem[][] playerMenuItem;
	
	 
	
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
		
		Menu fileMenu = new Menu("Game");
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
	    
	    Menu[] playerXMenu = { new Menu("Player 1") , new Menu("Player 2") };
	    
	    String[] typesIA = IA.getTypes();
	    playerMenuItem = new CheckMenuItem[2][typesIA.length];
	    
	    for(int j=0; j<2;j++){
	    	final int j_id = j;
		    for(int i=0; i<typesIA.length; i++){
		    	playerMenuItem[j][i] = new CheckMenuItem(typesIA[i]);
		    	final int iaId = i;
		    	
		    	playerMenuItem[j][i].setOnAction(new EventHandler<ActionEvent>() {
			        @Override public void handle(ActionEvent e) {
			        	System.out.println("Choix de l'IA pour j"+j_id+": "+iaId);
			        	
			        	selectMenuTypeIA(j_id, iaId);
			        	
			        	
			        }
			    });
		    	
		    	playerXMenu[j].getItems().add(playerMenuItem[j][i]);
		    	
		    }
	    }
	   
	    
	    
	    
	    
	   /* Menu playpauseMenu = new Menu("Play/Pause");
	    //MenuItem playpauseMenuItem = new MenuItem("Play/pause");
	    playpauseMenu.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {

	        	System.out.println("Play/pause");
	        	// do something

	        }
	    });*/
	    //playpauseMenu.getItems().add(playpauseMenuItem);
	    menuBar.getMenus().addAll(fileMenu,playerXMenu[0],playerXMenu[1]/*,playpauseMenu*/);
	    /* fin Menu */
		
		board = new Board(this);
		layout.setCenter(  board  );
		

		
		//System.out.println(layout.getCenter());
		//System.out.println("Window:"+stage.getWidth());
		
		this.setRoot(layout);
		
		
		//System.out.println(layout.getCenter());
		
		
		
		
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
	
	
	
	
	public Puissance4 getApp(){
		return this.app;
	}
	
	public void update(Color [][] data, int lines, int columns){		
		
		board.update(data,lines,columns);	
		
		//System.out.println("Mise à jour de la vue effectuée");
	}
	
	public void selectMenuTypeIA(int pId, int tIA){
		app.setTypeIA(pId,tIA);
	}
	
	public void updateMenuIA(int ia1_choice, int ia2_choice){
		String[] typesIA = IA.getTypes();
		for(int i=0; i < typesIA.length; i++){
			
			playerMenuItem[0][i].setSelected(ia1_choice == i);
			playerMenuItem[1][i].setSelected(ia2_choice == i);
			
			
			
		}
	}
	
	 
	@FXML
	private Label indication;
	 
	public void setIndication(String text){
		//System.out.println("indication : "+text);
		this.indication.setText(text);
	}
	
	public void selectColumnAction(int c){
		this.getApp().playHumain(c);
	}

}
