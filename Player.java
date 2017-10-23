import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String color;
	private Matrix curr;
	private int count=0;
	
	Player(String color,Matrix c){
		this.color=color;
		this.curr=c;
	}

	public void takeTurn() throws IOException{
		boolean flag=true;
		while(flag){
			int x= Reader.nextInt();
			int y= Reader.nextInt();
			if(this.curr.board[x][y].getColor().equals(color) || this.curr.board[x][y].getColor().equals("Black")){
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
