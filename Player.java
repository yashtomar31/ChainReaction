package v1.oo;
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
	
	Player(Color c2,Matrix c){
		this.color=c2;
		this.curr=c;
	}

	public boolean takeTurn(int x,int y) throws IOException{
			if(this.curr.board[x][y].getOwner().equals(Color.BLACK)||this.curr.board[x][y].getOwner().equals(color) ){
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
}
