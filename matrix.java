import java.io.Serializable;

public class Matrix implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int counter;
	public Cell[][] board;
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Cell[][] getBoard() {
		return board;
	}
	private int m,n;
	
	public int getM() {
		return m;
	}
	public int getN() {
		return n;
	}
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
		for(int i=1;i<m-1;i++){
			board[i][0]=new Cell(3);
			board[i][n-1]=new Cell(3);
		}
		board[0][0]=new Cell(2);
		board[0][n-1]=new Cell(2);
		board[m-1][0]=new Cell(2);
		board[m-1][n-1]=new Cell(2);
		
		//linking of Cells
		for(int i=1;i<m-1;i++){
			for(int j=1;j<n-1;j++){
				board[i][j].setLink1(board[i][j-1]);
				board[i][j].setLink2(board[i+1][j]);
				board[i][j].setLink3(board[i][j+1]);
				board[i][j].setLink4(board[i-1][j]);
			}
		}
		
		for(int i=1;i<m-1;i++){
			board[i][0].setLink1(board[i+1][0]);
			board[i][0].setLink2(board[i][1]);
			board[i][0].setLink3(board[i-1][0]);
			
			board[i][n-1].setLink1(board[i-1][n-1]);
			board[i][n-1].setLink2(board[i+1][n-1]);
			board[i][n-1].setLink3(board[i][n-2]);
		}
		
		for(int i=1;i<n-1;i++){
			board[0][i].setLink1(board[0][i-1]);
			board[0][i].setLink2(board[1][i]);
			board[0][i].setLink3(board[0][i+1]);
			
			board[m-1][i].setLink1(board[m-1][i-1]);
			board[m-1][i].setLink2(board[m-1][i+1]);
			board[m-1][i].setLink3(board[m-2][i]);
		}
		
		board[0][0].setLink1(board[1][0]);
		board[0][0].setLink2(board[0][1]);
		
		board[m-1][0].setLink1(board[m-1][1]);
		board[m-1][0].setLink2(board[m-2][0]);
		
		board[0][n-1].setLink1(board[0][n-2]);
		board[0][n-1].setLink2(board[1][n-1]);
		
		board[m-1][n-1].setLink1(board[m-1][n-2]);
		board[m-1][n-1].setLink2(board[m-2][n-1]);
		
	}
	public int getCounter() {
		return counter;
	}
//	public static void main(String[] args) throws IOException{
//			//Scanner scan = new Scanner(System.in);
//		//int m=scan.nextInt();
//		//int n=scan.nextInt();
//		//Matrix a =new Matrix(m,n);
//	}

	

}