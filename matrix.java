package v1.oo;

import java.io.Serializable;
import java.util.ArrayList;

public class Matrix implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int counter;
	private ArrayList<ArrayList<Cell>> board;
	private int m,n;
	private int currentplayer;
	Matrix(int m,int n){
		this.m=m;
		this.n=n;
		board=new ArrayList<ArrayList<Cell>>(m);
		for(int i=0;i<n;i++){
			ArrayList<Cell> temp=new ArrayList<Cell(n);
			board.add(temp);
		}
		for(int i=1;i<m-1;i++){
			for(int j=1;j<n-1;j++){
				board.get(i).add(j,new Cell(4));
			}
		}
		for(int i=1;i<n-1;i++){
			board.get(0).add(i,new Cell(3));
			board.get(m-1).add(i,new Cell(3));
		}
		for(int i=1;i<m-1;i++){
			board.get(i).add(0,new Cell(3));
			board.get(i).add(n-1,new Cell(3));
		}
		board.get(0).add(0,new Cell(2));
		board.get(0).add(m-1,new Cell(4));
		board.get(n-1).add(0,new Cell(4));
		board.get(n-1).add(m-1,new Cell(4));
		
	}
	public int getCounter() {
		return counter;
	}

	

}
