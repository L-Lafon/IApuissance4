package ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Chip;
import model.Grid;
import model.Player;
import model.Position;
import model.QualityMove;

public class IAMinMax extends IA{
	
	boolean elagage = false;
	
	public IAMinMax(int typeIA,Grid grid, Player pIA, Player pOpp){
		super(typeIA,grid,pIA,pOpp);
	}
	
	public Position play(){
		
		int depth;
		
		switch(typeIA){
			case IA.IA_MINMAX_H1_1 : depth = 4; break;
			case IA.IA_MINMAX_H1_2 : depth = 6; break;
			case IA.IA_MINMAX_H2_1 : depth = 4; break;
			case IA.IA_MINMAX_H2_2 : depth = 6; break;
			default: depth=4; break;
		}
		
		Integer alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;
		
		int val_max = Integer.MIN_VALUE;
		int cols_score[]= new int[grid.nbColumns];
		int val=Integer.MIN_VALUE;;
		
		
		for(int i=0; i<cols_score.length;i++)
			cols_score[i]=Integer.MIN_VALUE;
		
		int line;
		
		for(int i : getPossibleColumns()){
			
			// Simulation du coup				
			line = grid.add(i, new Chip(this.playerIA));				
			
							
			val = this.min(new Position(line, i),grid,depth,alpha,beta);	
			
			
			
			if(val > val_max){				
				val_max = val;			
			}
			
			cols_score[i] = val;;
			
			// Annulation du coup joué
			grid.remove(line, i);
				
					
		}	
		
		int col_max=0;
		ArrayList<Integer> bestColumns = new ArrayList<Integer>();
		for(int i=0; i<cols_score.length;i++){
			if(cols_score[i] == val_max)
				bestColumns.add(i);
		}
		
		
		
		
		return new Position(-1, getRandomInPossible(bestColumns));
	}
	
	public int min(Position posPlayed,Grid currentGrid, int depth, int alpha, int beta){
		
		if(depth == 0 || this.gameOver())
			return this.eval(posPlayed,currentGrid);
		
		int val_min = Integer.MAX_VALUE;
		int val;
		
		int line;
		
		for(int i : getPossibleColumns()){
			
			// Simulation du coup
			line = currentGrid.add(i, new Chip(playerOpponent));
			val = this.max(new Position(line,i),currentGrid,depth-1,alpha,beta);
			
			if(val <= val_min){
				val_min = val;					
			}		
			
			
			// Annulation du coup joué
			currentGrid.remove(line, i);
			
			if(elagage && alpha >= val) // élagage
				return val;
			
			beta = Math.min(beta,val); 
				
						
		}
		
		return val_min;
		
		
	}
	
	public int max(Position posPlayed, Grid currentGrid, int depth, int alpha, int beta){
		if(depth == 0 || this.gameOver()) 
			return this.eval(posPlayed, currentGrid);
		
		int val_max = Integer.MIN_VALUE;
		int val;
		
		int line;
		
		for(int i : getPossibleColumns()){
			
			
			// Simulation du coup
			line = currentGrid.add(i, new Chip(this.playerIA));
			val = this.min(new Position(line,i),currentGrid,depth-1,alpha,beta);
			
			if(val >= val_max){
				val_max = val;					
			}			
			
			
							
			// Annulation du coup joué
			currentGrid.remove(line, i);
			
			if(elagage && val >= beta)  // élagage 
				return val;
			
			alpha = Math.max(alpha,val);
				
						
		}
		
		return val_max;
		
		
	}
	
	public int eval(Position posPlayed, Grid currentGrid){
		if(typeIA == IA.IA_MINMAX_H2_1 || typeIA == IA.IA_MINMAX_H2_2)
			return evalH2(posPlayed,currentGrid);
		
		else
			return evalH1(posPlayed, currentGrid);
	}
	
	
	
	public int evalH2(Position posPlayed,Grid currentGrid){		
		
	
		int scoreIA = 0;
		int scoreOpp = 0;
		
		char pIASym=playerIA.getSymbol().charAt(0);
		char pOppSym=playerOpponent.getSymbol().charAt(0);
		
		Matcher matcher;
		Pattern pattern;
		
		// Attaques
		HashMap<String, Integer> weight = new HashMap<String,Integer>();
		weight.put("1111", 1000-currentGrid.getnbChips()*5);
		weight.put("_111_", 50);
		weight.put("111_", 20);
		weight.put("_111", 20);
		weight.put("11_1", 20);
		weight.put("1_11", 20);
		weight.put("1__1", 10);
		weight.put("11__", 10);
		weight.put("__11", 10);	
		weight.put("_1_1", 10);
		weight.put("1_1_", 10);
		weight.put("1___", 3);
		weight.put("_1__", 3);
		weight.put("__1_", 3);
		weight.put("___1", 3);
		weight.put("____", 1);
	
		// Défenses
		/*weight.put("2212", 50);
		weight.put("2122", 50);
		weight.put("2221", 50);
		weight.put("1222", 50);*/
		
		String currentPattern="";
		String pIAPattern="";
		String pOppPattern="";
		int currentWeight=0;
		
		String fullyState = currentGrid.getStateLines();
		
		Iterator<String> itePattern = weight.keySet().iterator();
		
		
		
		/*
		 * On passe en revue les différentes expressions (attaques/défenses) pour chacun des 2 joueurs. 
		 * Le score du joueur augmente au fur et à mesure que l'on trouve une configuration existantes dans les expressions régulières
		 */
		while (itePattern.hasNext())
		{
			
			currentPattern = (String)itePattern.next();
			currentWeight = (int)weight.get(currentPattern);
			
			pIAPattern = currentPattern.replace('1', pIASym).replace('2', pOppSym);
			pOppPattern = currentPattern.replace('1', pOppSym).replace('2', pIASym);				
		    
		    
		    pattern = Pattern.compile(pIAPattern);
		    matcher = pattern.matcher(fullyState);
		    while(matcher.find()) {		    	
		    	scoreIA+= currentWeight;
		    }
		    
		    pattern = Pattern.compile(pOppPattern);
		    matcher = pattern.matcher(fullyState);
		    
		    while(matcher.find()) {		    	
		    	scoreOpp+= currentWeight;
		    }		    
		   
		}	
		
		
		
		
		return( scoreIA - scoreOpp );		
		
	}
	
	
	public int evalH1(Position posPlayed,Grid currentGrid){
		
		Pattern pattern;
		Matcher matcher;
		
		String sym;
		int scoreIA=0, scoreOpp=0,scoreTmp=0;		
		String stateGrid = currentGrid.getStateLines();
		
		for(Player player : new Player[]{playerIA, playerOpponent}){
			sym = player.getSymbol();
			pattern = Pattern.compile("["+sym+"]{4,}");
			matcher = pattern.matcher(stateGrid);
			
			while(matcher.find()) {		
				
				scoreTmp += 1000 - currentGrid.getnbChips();
				scoreTmp += matcher.group().length()*10;
				
				if(player == playerIA)
					scoreIA += scoreTmp;				
				else
					scoreOpp += scoreTmp;				
			}
		}		
	
		return scoreIA - scoreOpp;	
	
	}
	
	
	
	
	
	

}
