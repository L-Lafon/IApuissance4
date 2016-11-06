package ihm;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

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
		
		System.out.println("Mise à jour de la vue effectuée");
	}
	
	 
	@FXML
	 private Label indication;
	 
	public void setIndication(String text){
		System.out.println("indication : "+text);
		this.indication.setText(text);
	}
	
	public void selectColumnAction(int c){
		this.getApp().motor.insertChip(c);
	}

}
