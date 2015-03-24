package edu.nyu.main;

import java.util.ArrayList;

public class Node {

  private int utilVal;
  private int[][] node;
  private String currentPlayer;
  int treeDepth;
  int moves;
  int previousPaikaMoves=0;
  public String getCurrentPlayer() {
	return currentPlayer;
  }

  public void setCurrentPlayer(String currentPlayer) {
	this.currentPlayer = currentPlayer;
	}

  private ArrayList<Node> subNodes = new ArrayList<Node>();
  
  public Node(int[][] node){
    this.node = node;
  }
  
  public int[][] getBoard(){
	  return this.node;
  }
  
  public ArrayList<Node> getSubNodes(){
    return this.subNodes;
  }
  
  public int getUtilVar(){
    return this.utilVal;
  }
  
  public void setAlpha(int alpha){
    this.utilVal = alpha;
  }
  
  public void addSubNode(Node newNode){
    this.subNodes.add(newNode);
  }
  
  
  //Method to count of no of generated nodes
  
  public int getNoOfGeneratedNode(){
	  int totalCnt=0;
	  
	  if(this.getSubNodes()!=null){
		  totalCnt+=this.getSubNodes().size();
		  for (Node n:this.getSubNodes()){
			totalCnt+=n.getNoOfGeneratedNode();
		
		  }
		
	  }
	  else{
		  return 0;
	  }
	  
	  return totalCnt;
  }
  
  
  // Method to get the depth. ****Incomplete
  
/*  public int getTreeDepth(){
	  int depth=0;
	  if(this.getSubNodes()!=null){
		  depth+=1;
		  for (Node n:this.getSubNodes()){
				depth+=n.getTreeDepth();
			
			  }
	  }
	  else{return 0;}
	  
	  
	  return depth;
  }*/
}