package v1.oo;
import java.io.IOException;
import java.io.Serializable;

import javafx.scene.paint.Color;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private Matrix curr;
	private int count=0;
	
	Player(Color c2,Matrix c){
		this.color=c2;
		this.curr=c;
	}

	public void takeTurn(int x,int y) throws IOException{
		boolean flag=true;
		while(flag){
			if(this.curr.board[x][y].getColor().equals(color) || this.curr.board[x][y].getColor().equals(Color.BLACK)){
				this.curr.board[x][y].setColor(color);
				this.curr.board[x][y].addORB();
				flag=false;
			}
		}
		count++;
	}
	
	public boolean isActive() {
		int points=0;
		for (int i=0;i<curr.getM();i++){
			for(int j=0;j<curr.getN();j++){
				if(curr.getBoard()[i][j].getColor().equals(color)){
					points++;
				}
			}
		}
		if(points>0 || count==0){
			return true;
		}
		else{
			return false;
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
