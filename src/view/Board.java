package view;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
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
		
		//System.out.println("height"+this.getHeight());
		
		double win_width = this.getWidth();
		double win_height = this.getHeight();
		
		this.getChildren().removeAll();
		
		Rectangle board_background = new Rectangle();
		board_background.setWidth(win_width);
		board_background.setHeight(win_height);
		
		//board_background.setArcWidth(30);
		//board_background.setArcHeight(30);
		board_background.setFill(degrade("#9B7A7A","#5E3030"));

		//board_background.setFill(bgImg);
		
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
				arrow.setStyle("-fx-base: #FFFFE0;");
				
				
				//arrow.setRadius( 20);
				//arrow.setFill(Color.GREEN);
				
				
				AnchorPane.setLeftAnchor(arrow, hSpacing * (c+1) - hSpacing/4 );
        		AnchorPane.setTopAnchor(arrow, 15.0);
        		
				
        		this.getChildren().add(arrow);
				
			}
			
		}
        
        for(int c=0;c<columns; c++){        	
        	double h = hSpacing * (c+1) - hSpacing/3;
        	for(int l=lines-1;l>=0; l--){        		
        		double v = vSpacing * (l+1) - vSpacing/2;
        		//System.out.println(l+":"+data[l][c]+","+v);
        		Chip chip = new Chip(this,(data[l][c] != null) ? data[l][c] : Color.GRAY);
        		AnchorPane.setLeftAnchor(chip, h);
        		AnchorPane.setBottomAnchor(chip, v);
        		//System.out.println("left:"+h+"-top:"+v);
        		
        		this.getChildren().add(chip);
        	}
        }
	}
	
	public static Paint degrade(String colorWeb1,String colorWeb2){
    	return new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
			new Stop[] {
				new Stop(0, Color.web(colorWeb1)),
				new Stop(1, Color.web(colorWeb2))
			}
		);
    }
	
	public static void afficheEmoji(Rectangle rect){
		Image imgEmoji = new Image("../emoji-confetti.jpg") ;
		ImagePattern imgPatternEmoji = new ImagePattern(imgEmoji);
	}

}
