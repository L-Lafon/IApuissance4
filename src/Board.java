

public class Board {
	
	Pion[][] pions;
	int nbColumns = 7;
	int nbLines = 6;;
	
	/** Initialisation du plateau
	 * pion[i][j] = null (aucun pion dans la case)
	 */
	public Board(){
		
		pions = new Pion[nbLines][nbColumns];
		
		int i,j;
		for(i=0; i<this.nbLines;i++)
			for(j=0;j<this.nbColumns; j++)
				this.pions[i][j]=null;
	}
	
	public void add(int c, Pion p){
		int l;
		for(l=0; l<this.nbLines && !isCaseFree(l,c); l++);
		this.add(l,c, p);
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
	
	public int existsAlignment(){
		
		return 0;
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
