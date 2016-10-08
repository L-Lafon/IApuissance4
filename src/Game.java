
import java.util.Scanner;

public class Game {
	Board board;
	int nbPionToWin=4;
	int currentPlayer;
	int winner = 0;
	
	
	public static void main(String[] args){
		new Game();
		
		
	}
	
	
	public Game(){
		this.board = new Board();
		this.currentPlayer=1;
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(this.winner == 0){
			

			System.out.println("Veuillez saisir une colonne (j"+currentPlayer+") :");
			String str = sc.nextLine();
			if(str.equals("quit") || str.equals(""))
				return;
			System.out.println(str);
			this.place(Integer.parseInt(str));
			
			this.board.showDebug();
			
			currentPlayer = (currentPlayer == 1) ? 2 : 1;
		}
		
		sc.close();
		
		
		
	}
	
	public void place(int column){
		int line;
		if(!this.board.isColumnFull(column)){
			line = this.board.add(column, new Pion(this.currentPlayer));
		
			// Vérification si pions alignés par rapport au dernier pion déposé
			//int winner;
			if((this.board.existsAlignment(new Position(line,column))) != false){
				//this.winner=this.pions[line][column].getPlayer();
				System.out.println("Victoire de "+this.winner);
			}
		
		}
			
	}
	
	
}
