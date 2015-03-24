package edu.nyu.main;

import java.util.ArrayList;
import java.util.HashSet;

public class AI {
	boolean cuttoff=false;
	int maxPrune=0;
	int minPrune=0;
	int consecutivePaikaMove=0;
	
	// Method to generate the tree from the given root node
	
	public void buildTree(Node currentState,int depth){
		if(depth==0 ||  currentState.moves==40 || currentState.previousPaikaMoves >= 3){
			this.cuttoff=true;
			return;
		}
		Board b=new Board();
		b.boardCol=currentState.getBoard().length;
		b.boardRow=currentState.getBoard().length;
		b.board=currentState.getBoard();
		if(b.checkGoalState()==2 || b.checkGoalState()==1 ||b.checkGoalState()==3){
			return;
		}
		ArrayList<Position> p=b.getPossiblePlaces(currentState.getCurrentPlayer());
		ArrayList<Position> j=new ArrayList<Position>();
		ArrayList<Position> k=new ArrayList<Position>();
		ArrayList<Integer> l=new ArrayList<Integer>();
		for(Position p1:p){
			ArrayList<Position> possibleMoves = b.getNeighbours(p1);
			for (Position nextPos : possibleMoves){
				int i=b.isMoveAbleToCapture(p1, nextPos);
				if (i !=0){
					j.add(p1);
					l.add(i);
					k.add(nextPos);
				}
		        else{ 
		        	if(b.isPaikaAllowed(currentState.getCurrentPlayer())){
		        		j.add(p1);
		        		l.add(i);
		        		k.add(nextPos);
		        	}
		        }
			}
		}
	
		for(int i=0;i<l.size();i++){
			Position p1=j.get(i);
			Position p2=k.get(i);
			int move=l.get(i);
			Board b2=new Board();
			b2.board=b.copy(b.board);
			b2.boardCol=currentState.getBoard().length;
			b2.boardRow=currentState.getBoard().length;
			Node newNode=new Node(b2.board);
			
			if(currentState.getCurrentPlayer().equals("black")){
				newNode.setCurrentPlayer("white");
			}
			else{
				newNode.setCurrentPlayer("black");
			}
			if(move==1){
				b2.captureApproach(p1,p2);
			}
			else if(move==2){
				b2.captureWithdraw(p1, p2);
			}
			else{	
				if(currentState.getCurrentPlayer().equals("black")){
					b2.board[p2.getRow()][p2.getCol()]=1;
					b2.board[p1.getRow()][p1.getCol()]=0;
					
				}
				else{
					b2.board[p2.getRow()][p2.getCol()]=2;
					b2.board[p1.getRow()][p1.getCol()]=0;
				}
				
				newNode.previousPaikaMoves=currentState.previousPaikaMoves+1;;
				newNode.setAlpha(this.utility_opponentCount(b2.board,currentState.getCurrentPlayer()));
				currentState.addSubNode(newNode);
				continue;
			}
			if(b2.previousPositions!=null)
				b2.previousPositions.add(p1);
			else
			{	
				b2.previousPositions=new HashSet<Position>();
				b2.previousPositions.add(p1);
			}
			if(b2.previousDirections!=null)
				b2.previousDirections.add(b2.getDirection(p1, p2));
			else
			{	
				b2.previousDirections= new HashSet<String>();
				b2.previousDirections.add(b2.getDirection(p1, p2));
			}
			

			b2.generateMultipleMoves(b2.board, p2);
			b2.MOVES--;
			//b2.printBoard();
			newNode.moves=currentState.moves+1;
			//newNode.setAlpha(this.utility_opponentCount(b2.board,currentState.getCurrentPlayer()));
			//if(newNode.getCurrentPlayer().equals("white"))
			b2.printBoard();
			//System.out.println(""+this.utility_difference(b2.board));
			if(newNode.getCurrentPlayer().equals("black"))
				newNode.setAlpha(this.utility_difference(b2.board));
			else
				newNode.setAlpha(Math.abs(this.utility_difference(b2.board)));
			//System.out.println(""+newNode.getUtilVar());
			currentState.addSubNode(newNode);
		}
		
		for(Node n:currentState.getSubNodes()){
			this.buildTree(n, depth-1);
		}
		
	}

	
	//Alpha beta algorithm
	
	public int alphaBeta(Node node, int depth, int alpha, int beta, String player){
		if ((node.getSubNodes().size() == 0) || (depth == 0)) {
	      return node.getUtilVar();
	    }
	    ArrayList<Node> subNodes = node.getSubNodes();
	    if (player.equals("black")){
	    	for (Node n : subNodes){
	    		alpha = Math.min(alpha, alphaBeta(n, depth - 1, alpha, beta, "white"));
	    		n.setAlpha(alpha);
	    		if (alpha <= beta) {
	    			//System.out.println("Pruning Min");
	    			minPrune++;
	    			break;
	    		}
	    	}
	    	return alpha;
	    }
	    for (Node n : subNodes){
	    		beta = Math.max(beta, alphaBeta(n, depth - 1, alpha, beta, "black"));
	    		n.setAlpha(beta);
	    		if (alpha <= beta) {
	    			//System.out.println("Prunning Max");
	    			maxPrune++;
	    			break;
	    		}
	    	}
	    	return beta;
	    
	}
	

	
	
	// Method to get the best possible move from alpha beta values
	
	public int[][] getBestMove(Node rootNode){
		int utilVal = 0;
	    Board b=new Board();
	    ArrayList<Node> subNodes = rootNode.getSubNodes();
	    if (rootNode.getCurrentPlayer().equals("black")) {
	    	for (int i = 0; i < subNodes.size(); i++) {
	    		if (((Node)subNodes.get(i)).getUtilVar() < utilVal){
	    			utilVal = ((Node)subNodes.get(i)).getUtilVar();
	    			b.board=(subNodes.get(i)).getBoard();
	    		}
	    	}
	    } 
	    else {
	    	for (int i = 0; i < subNodes.size(); i++) {
	    		if (((Node)subNodes.get(i)).getUtilVar() > utilVal){
	    			utilVal = ((Node)subNodes.get(i)).getUtilVar();
	    			b.board=((Node)subNodes.get(i)).getBoard();
	    		}
	    	}
	    }
	   return b.board;
	}
	
	
	//Utility method which returns the difference between the player stone and computer stone as a utility value
	
	public int utility_difference(int[][] board){
	    int utility = 0;
	    int white = 0;
	    int black = 0;
	    for (int row=0; row<board.length;row++){
			for (int column=0;column < board[row].length;column++){
				if(board[row][column]==1){
					black++;
				}
				else if(board[row][column]==2){
					white++;
				}
			}
		}
	   
	    utility = white - black;
	    return utility;
	}
	
	
	//Utility method which returns the opponent count as a utility value
	
	public int utility_opponentCount(int[][] board,String color){
		int count = 0;
	    int check;
	    if (color.equals("white")){
	    	check=2;
	    }
	    else{
	    	check=1;
	    }
	    for (int row=0; row<board.length;row++){
			for (int column=0;column < board[row].length;column++){
				if(check==board[row][column]){
					count++;
				}
			}
	     }
	  
	    return count;
	  }
}




//  Comments ///

// System.out.println("result:"+resultIdx);
// return (Move)this.rootNode.getBoard().getAllPossibleMoves().get(resultIdx);


//System.out.println("COLOR:"+currentState.getCurrentPlayer()+":::"+newNode.getCurrentPlayer());

//System.out.println("able to capture from "+"("+p1.getRow()+","+p1.getCol()+")"+" to ("+p2.getRow()+","+p2.getCol()+")");


//System.out.println("COLOR:"+currentState.getCurrentPlayer()+":::"+newNode.getCurrentPlayer());


//  System.out.println("able to capture from "+"("+p1.getRow()+","+p1.getCol()+")"+" to ("+nextPos.getRow()+","+nextPos.getCol()+")");

//System.out.println("depth:"+depth);
// System.out.println("++"+count);