package view;

import javafx.scene.Parent;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        this.setRadius( 30 * board.getWidth() / 800 );
        
        this.setTranslateX(0);//on positionne le groupe plutôt que le rectangle
        this.setTranslateY(0);
        this.setFill(color);
        
        if(color==Color.GRAY){
        	InnerShadow innerShadow = new InnerShadow();
			innerShadow.setOffsetX(3);
			innerShadow.setOffsetY(3);
			innerShadow.setColor(Color.BLACK);
			this.setEffect(innerShadow);
		}
        else{
        	final Light.Distant light = new Light.Distant();
            light.setAzimuth(-135.0);
            final Lighting lighting = new Lighting();
            lighting.setLight(light);
            lighting.setSurfaceScale(9.0);
            this.setEffect(lighting);
        }
        
        final String cssDefault = "-fx-border-color: black;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 2px;\n"
                + "-fx-border-style: solid;\n";
        
        this.setStyle(cssDefault);
        
       // System.out.println( this.board.getBoundsInParent().getWidth() );
	}

}
