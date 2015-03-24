package edu.nyu.main;

public class Position {
	
	int row;
	int col;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public Position(int row, int col){
		this.row=row;
		this.col=col;
	}
	public Position(){
		
	}
	public boolean equals(Object obj){
		if ((obj instanceof Position)){
	      Position pos = (Position)obj;
	      return (this.row == pos.row) && (this.col == pos.col);
	    }
	    return false;
	  }	

}
