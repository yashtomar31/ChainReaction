package v1.oo;
import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isActive;
	private int number;
	private String color;
	private Matrix curr;
	
	Player(String color, int num,Matrix c){
		this.color=color;
		this.isActive=true;
		this.number=num;
		this.curr=c;
	}

	public void takeTurn(){
		boolean flag=true;
		while(flag){
			@SuppressWarnings("resource")
			Scanner s=new Scanner(System.in);
			int x=s.nextInt();
			int y=s.nextInt();
			if(this.curr.board[x][y].isOwnedBy(number) || this.curr.board[x][y].getOwner()==0){
				this.curr.board[x][y].setColor(color);
				this.curr.board[x][y].setOwner(number);
				this.curr.board[x][y].addORB();
				this.curr.board[x][y].addORB();
			}
		}
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumber() {
		return number;
	}

}
