package ihm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SelectColumn extends Button{
	
	Board board;
	
	SelectColumn(Board b, int id, String str){
		super(str);
		this.board = b;
		this.setId(String.valueOf(id));
		
		
		
		this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                board._wg.selectColumnAction(Integer.valueOf(getId()));
            }
        });
		
	}

}
