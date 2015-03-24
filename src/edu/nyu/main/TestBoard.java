package edu.nyu.main;

import java.util.HashSet;



public class TestBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
/*		
		Board b=new Board();
		b.board = new int[3][3];
		b.boardCol=3;
		b.boardRow=3;
		b.board[0][0]=0;
		b.board[0][1]=1;
		b.board[0][2]=0;
		b.board[1][0]=0;
		b.board[1][1]=0;
		b.board[1][2]=2;
		b.board[2][0]=0;
		b.board[2][1]=0;
		b.board[2][2]=0;
		Node n=new Node(b.board);
		n.setCurrentPlayer("black");
		AI a=new AI();
		a.buildTree(n, 1);
		a.alphaBeta(n, 1, -99999, 999999, "black");
		b.board=a.getBestMove(n);
		System.out.println("generated nodes:"+n.getNoOfGeneratedNode()+"   ");
		b.printBoard();
*/
		Board b=new Board();
		b.board = new int[][]{{1,0,0,0,0},
				              {1,0,0,0,0},
				              {1,0,0,1,2},
				              {0,0,1,0,2},
				              {0,0,0,0,0}};
		b.boardCol=5;
		b.boardRow=5;
		/*b.MOVES=2;
		b.printBoard();
		b.setPlayerColor("white");
		b.previousPositions=new HashSet<Position>();
		b.previousDirections=new HashSet<String>();
		//System.out.println(""+b.isWithdrawMove(new Position(3,1), new Position(3,0)));
		int a=b.makePlayerMove(new Position(3,1), new Position(3,0));
		System.out.println("a:"+a);
		a=b.makePlayerMove(new Position(3,0), new Position(2,0));
		System.out.println("a:"+a);
		a=b.makePlayerMove(new Position(3,0), new Position(2,0));
		System.out.println("a:"+a);
		b.MOVES--;
		b.setAiColor("black");
		b.makeAIMove();
		//b.MOVES=1;
		b.MOVES--;
		
		int gameFlag=b.checkGoalState();
		System.out.println(""+gameFlag);*/
		//		Position p1= new Position(3,1);
//		Position p2= new Position(3,2);
//		b.setPlayerColor("white");
//		b.makePlayerMove(p1, p2);
////		b.captureWithdraw(p1, p2);
//		b.printBoard();
		Node n=new Node(b.board);
		n.setCurrentPlayer("black");
	//	b.MOVES=39;
	//	n.moves=39;
		AI a=new AI();
		a.buildTree(n, 1);
		a.alphaBeta(n, 1, -99999, 999999, "black");
		b.board=a.getBestMove(n);
		b.printBoard();
		System.out.println(""+n.getUtilVar());
		System.out.println("generated nodes:"+n.getNoOfGeneratedNode()+"   ");
		//b.printBoard();
		
//		
//		Position p1= new Position(2,2);
//		Position p2= new Position(2,1);
//		
//		b.captureApproach(p1, p2);
//	b.printBoard();
//		if(b.previousPositions!=null)
//			b.previousPositions.add(p1);
//		else
//		{	b.previousPositions=new HashSet<Position>();
//		b.previousPositions.add(p1);
//		}
//		if(b.previousDirections!=null)
//			b.previousDirections.add(b.getDirection(p1, p2));
//		else
//		{	b.previousDirections= new HashSet<String>();
//		b.previousDirections.add(b.getDirection(p1, p2));
//		}
//		//b2.previousDirections.add(b2.getDirection(p1, p2));
//		
//		
//		b.generateMultipleMoves(b.board, p2);
//		b.printBoard();
//	//	b.printBoard();
//		System.out.println(""+b.checkGoalState());
//		System.out.println("generated nodes:"+n.getNoOfGeneratedNode());
//		b.board[0][0]=0;b.board[0][1]=1;b.board[0][2]=1;b.board[0][3]=1;b.board[0][4]=1;
//		b.board[1][0]=1;
//		b.board[1][1]=2;
//		b.board[1][2]=2;
//		b.board[0][3]=1;
//		b.board[0][4]=1;
//		b.board[2][0]=2;
//		b.board[2][1]=2;
//		b.board[2][2]=0;
//		b.board[0][3]=1;
//		b.board[0][4]=1;
//		b.board[2][0]=2;
//		b.board[2][1]=2;
//		b.board[2][2]=0;
//		b.board[0][3]=1;
//		b.board[0][4]=1;
//		b.board[2][0]=2;
//		b.board[2][1]=2;
//		b.board[2][2]=0;
//		b.board[0][3]=1;
//		b.board[0][4]=1;
//		System.out.println();
//		System.out.println(""+b.isAbleToCapture(new Position(1,1)));
//		
//		
		
	}

}
