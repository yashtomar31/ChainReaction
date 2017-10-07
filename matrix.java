package v1.oo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Matrix implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int counter;
	Cell[][] board;
	private int m,n;
	private int currentplayer;
	Matrix(int m,int n){
		this.m=m;
		this.n=n;
		board=new Cell[m][n];
		for(int i=1;i<m-1;i++){
			for(int j=1;j<n-1;j++){
				board[i][j]=new Cell(4);
			}
		}
		for(int i=1;i<n-1;i++){
			board[0][i]=new Cell(3);
			board[m-1][i]=new Cell(3);
		}
		for(int i=1;i<n-1;i++){
			board[i][0]=new Cell(3);
			board[i][n-1]=new Cell(3);
		}
		board[0][0]=new Cell(2);
		board[0][n-1]=new Cell(2);
		board[m-1][0]=new Cell(2);
		board[m-1][n-1]=new Cell(2);
		
	}
	public int getCounter() {
		return counter;
	}
	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in);
		int m=scan.nextInt();
		int n=scan.nextInt();
		Matrix a =new Matrix(m,n);
	}

	

}
