package ihm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Board extends AnchorPane{
	final public WindowGame _wg;
	public int lines=0;
	public int columns=0;
	
	
	Board(WindowGame _wg){
		
		super();
		this._wg=_wg;
		
		
		
		
		
		//this.lines = lines;
		//this.columns = columns;
		
		//System.out.println(lines+"-"+columns);
		
		
        
        
        
      //  System.out.println("test" + this.scene.getStage().getWidth());
        
        //System.out.println( "Pane : "+board_background.getWidth()+"-"+this.getBoundsInParent().getWidth()+"-"+this.getLayoutBounds().getWidth()  );
        
         
	}
	
	public void update(Color[][] data, int lines, int columns){
		
		System.out.println("height"+this.getHeight());
		
		double win_width = this.getWidth();
		double win_height = this.getHeight();
		
		this.getChildren().removeAll();
		
		Rectangle board_background = new Rectangle();
		board_background.setWidth(win_width);
		board_background.setHeight(win_height);
		
		board_background.setArcWidth(30);
		board_background.setArcHeight(30);
		board_background.setFill(Color.GREY);
		
		
		this.getChildren().add(board_background);
		
		//this.setStyle("-fx-background-color:black");
		
		
		
		
		//this.setHgap(6); 
		//this.setVgap(6);
		
        
        //this.setTranslateX(50);//on positionne le groupe plutôt que le rectangle
        //this.setTranslateY(50);
 
        //this.getChildren().add(board_background);
		
		double hSpacing = getWidth() / (columns + 1) ;
		double vSpacing = getHeight() / (lines + 1) ;
		
		// Flèches
		
		
		for(int c=0;c<columns; c++){
			// La colonne n'est pas pleine
			if(data[lines-1][c] == null){
				
				Button arrow = new SelectColumn(this,c,"Placer");
				
				
				//arrow.setRadius( 20);
				//arrow.setFill(Color.GREEN);
				
				
				AnchorPane.setLeftAnchor(arrow, hSpacing * (c+1) - hSpacing/4);
        		AnchorPane.setTopAnchor(arrow, 0.0);
				
        		this.getChildren().add(arrow);
				
			}
			
		}
        
        for(int c=0;c<columns; c++){        	
        	double h = hSpacing * (c+1) - hSpacing/3;
        	for(int l=lines-1;l>=0; l--){        		
        		double v = vSpacing * (l+1) - vSpacing/2;
        		//System.out.println(l+":"+data[l][c]+","+v);
        		Chip chip = new Chip(this,(data[l][c] != null) ? data[l][c] : Color.BLACK);
        		AnchorPane.setLeftAnchor(chip, h);
        		AnchorPane.setBottomAnchor(chip, v);
        		//System.out.println("left:"+h+"-top:"+v);
        		
        		this.getChildren().add(chip);
        	}
        }
		
	}

}
