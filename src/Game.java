import java.util.Scanner;

public class Game {
	Board board;
	int currentPlayer;
	int winner = 0;
	
	
	public static void main(String[] args){
		Game game = new Game();
		
		
	}
	
	
	public Game(){
		this.board = new Board();
		this.currentPlayer=1;
		
		Scanner sc = new Scanner(System.in);
		while(true){
			

			System.out.println("Veuillez saisir une colonne (j"+currentPlayer+") :");
			String str = sc.nextLine();
			if(str.equals("quit") || str.equals(""))
				return;
			System.out.println(str);
			this.place(Integer.parseInt(str));
			
			this.board.showDebug();
			
			currentPlayer = (currentPlayer == 1) ? 2 : 1;
		}
		
		
	}
	
	public void place(int column){
		if(!this.board.isColumnFull(column))
			this.board.add(column, new Pion(this.currentPlayer));
		
		// Vérification si pions alignés
		int winner;
		if((winner = this.board.existsAlignment()) != 0)
			this.winner=winner;
			
	}
	
	
}
