//package v1.oo;
import java.io.IOException;
import java.io.Serializable;

import javafx.scene.paint.Color;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient private Color color;
	private String colstr;
	private Matrix curr;
	private int turns=0;
	private int no;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	Player(Color c2,Matrix c,int n){
		this.color=c2;
		this.colstr=color.toString();
		this.curr=c;
		no=n;
	}

	public boolean takeTurn(int x,int y) throws IOException, WinnerException{
			if(this.curr.board[x][y].getOwner().equals(Color.BLACK)||this.curr.board[x][y].getOwner().equals(color) ){
				turns++;
				this.curr.board[x][y].setOwner(color);
				this.curr.board[x][y].addORB();
				return false;
			}
			return true;
	}

	
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		colstr=color.toString();
		this.color = color;
	}

	public String getColstr() {
		return colstr;
	}

	public void setColstr(String colstr) {
		this.colstr = colstr;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	boolean isActive(){
		int points=0;
		for (int i=0;i<curr.getM();i++){
			for(int j=0;j<curr.getN();j++){
				if(this.curr.getBoard()[i][j].getOwner().equals(this.getColor()) || this.getTurns()==0){
					points++;
				}
			}
		}
		if(points>0){
			return true;
		}
		else{
			return false;
		}

	}
}
