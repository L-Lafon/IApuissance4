

public class Board {
	
	final static int NB_LINES = 6;
	final static int NB_COLUMNS = 7;
	
	Pion[][] pions;
	int nbLines,nbColumns;
	
	
	/** Initialisation du plateau
	 * pion[i][j] = null (aucun pion dans la case)
	 */
	
	public Board(){
		// valeurs par défaut utilisées si pas précisés
		this(Board.NB_LINES,Board.NB_COLUMNS);
		
	}
	public Board(int row, int col){
		
		this.nbLines = row;
		this.nbColumns = col;		
		
		pions = new Pion[nbLines][nbColumns];
		
		int i,j;
		for(i=0; i<this.nbLines;i++)
			for(j=0;j<this.nbColumns; j++)
				this.pions[i][j]=null;
	}
	
	// Retour l'id de la ligne où le jeton a été déposé
	public int add(int c, Pion p){
		int l;
		for(l=0; l<this.nbLines && !isCaseFree(l,c); l++);
		this.add(l,c, p);
		return l;
	}
	
	
	
	// Pour l'ia plus tard
	public void add(int l, int c, Pion p){
		this.pions[l][c] = p;
	}
	public void remove(int l, int c){
		this.pions[l][c] = null;
	}
	
	public boolean isColumnFull(int col){
		return this.pions[this.pions.length -1][col] != null;
	}
	
	public boolean isCaseFree(int l, int c){
		//System.out.println("L="+l+"C="+c);
		if(l>= 0 && l < nbLines && c >= 0 && c < nbColumns)
			return this.pions[l][c] == null;
		return false;
	}
	
	public boolean existsAlignment(Position p){
		
		int pos_row = p.getRow();
		int pos_col = p.getCol();
		int currPlayer = this.pions[pos_row][pos_col].getPlayer();
		
		int aligned;
		
		
		
		// Détection verticale
		
		aligned = 1;
		if(p.getRow() >= 4){			
			
			for(int i=1; i<=3;i++){
				if(this.pions[pos_row-i][pos_col].getPlayer() == currPlayer)
					aligned++;					
				else
					break;
			}
			
			if(aligned == 4)
				return true;			
			
		}	
		
		// Détection horizontale
		aligned = 1;
		
		for(int i=1; i<=3;i++){
			if(pos_col-i >= 0 && !this.isCaseFree(pos_row, pos_col-i)  && this.pions[pos_row][pos_col-i].getPlayer() == currPlayer)
				aligned++;
			else
				break;
		}
		
		for(int i=1; i<=3;i++){
			if(pos_col+i < nbColumns && !this.isCaseFree(pos_row, pos_col+i) && this.pions[pos_row][pos_col+i].getPlayer() == currPlayer)
				aligned++;
			else
				break;
		}
		
		if(aligned == 4)
			return true;	
		
		// Détection diagonale bas gauche
		
		aligned = 1;
		
		for(int i=1; i<=3;i++){
			if(pos_row-i >= 0 && pos_col-i >= 0 && !this.isCaseFree(pos_row-i, pos_col-i) && this.pions[pos_row-i][pos_col-i].getPlayer() == currPlayer)
				aligned++;
			else
				break;
		}
		
		// Détection diagonale haut droite
		
		for(int i=1; i<=3;i++){
			if(pos_row+i < nbLines && pos_col+i < nbColumns && !this.isCaseFree(pos_row+i, pos_col+i) && this.pions[pos_row+i][pos_col+i].getPlayer() == currPlayer)
				aligned++;
			else
				break;
		}
		
		if(aligned == 4)
			return true;		
		
		
		return false;
	}
	
	
	public void showDebug(){
		
		int i,j;
		String player="";
		System.out.printf("Plateau :\n");
		for(i=nbLines-1; i>=0; i--){
			for(j=0; j<nbColumns; j++){
				if(!this.isCaseFree(i, j)){
					if(this.pions[i][j].getPlayer() == 1)
						player = "X";
					else if(this.pions[i][j].getPlayer() == 2)
						player = "O";
				}
				else
					player = "-";
					
					System.out.printf("%3s ", player);
			}
			
			System.out.printf("\n");
		}
		/*String col_sep = "|";
		String row_sep = "\n%s+\n"%("+-----"*this.pions[0].length);
		String s= row_sep;
        for i in range(len(mat)):
            s+=col_sep
            for j in range(len(mat[0])):
                s+='%5s%s'%(mat[i][j],col_sep)
            s+=row_sep
        return print(s)*/
	}
}
