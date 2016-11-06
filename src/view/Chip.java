package view;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import model.Player;

public class Chip extends Circle{
	
	Board board;
	
	
	
	Chip(Board b, Color color){
		super();
		this.board = b;
		
		
		/*Circle board_chip = new Circle();
		board_chip.setCenterX(0.0);
		board_chip.setCenterY(0.0);
		board_chip.setRadius(20);
		//board_chip.setArcHeight(30);
		board_chip.setFill(Color.RED);*/
		
		//System.out.println( "chip="+ this.getBoundsInParent().getHeight()  );
        this.setCenterX(0);
        this.setCenterY(0);
        this.setRadius( board.getWidth() * 0.035);
        
        this.setTranslateX(0);//on positionne le groupe plutôt que le rectangle
        this.setTranslateY(0);
        this.setFill(color);
        
        final String cssDefault = "-fx-border-color: black;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 2px;\n"
                + "-fx-border-style: solid;\n";
        
        this.setStyle(cssDefault);
        
       // System.out.println( this.board.getBoundsInParent().getWidth() );
	}

}
