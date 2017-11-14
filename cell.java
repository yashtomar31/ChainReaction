import java.io.Serializable;
import java.util.Queue;

import javafx.scene.paint.Color;

public class Cell implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Cell link1,link2,link3,link4;
	private int Criticalmass;
	transient private Color Owner=Color.BLACK;
	private String Ownstr="Black";
	private int orbs;
	private Queue<Player> Players;


	public int getOrbs() {
		return orbs;
	}

	public void setOrbs(int orbs) {
		this.orbs = orbs;
	}

	public Cell(int cm,Queue<Player> Players){
		this.Players=Players;
		this.Criticalmass=cm;
		this.Owner=null;
	}

	public void addORB(){
		this.orbs++;
		if(this.isFull()){
			this.explode();
		}
	}

	public void explode() {
		// TODO Auto-generated method stub
		Color temp=this.Owner;
		if (this.Criticalmass==2){
			if(this.orbs>2){
				this.orbs=this.orbs-2;
			}
			else{
				this.Owner=Color.BLACK;
				this.orbs=0;
				
			}
			this.link1.setOwner(temp);
			this.link1.addORB();
			this.link2.setOwner(temp);
			this.link2.addORB();
		}
		else if(this.Criticalmass==3){
			if(this.orbs>2){
				this.orbs=this.orbs-3;
			}
			else{
				this.Owner=Color.BLACK;
				this.orbs=0;
				
			}
			this.link1.setOwner(temp);
			this.link1.addORB();
			this.link2.setOwner(temp);
			this.link2.addORB();
			this.link3.setOwner(temp);
			this.link3.addORB();
		}
		else if(this.Criticalmass==4){
			if(this.orbs>2){
				this.orbs=this.orbs-4;
			}
			else{
				this.Owner=Color.BLACK;
				this.orbs=0;
				
			}
			this.link1.setOwner(temp);
			this.link1.addORB();
			this.link2.setOwner(temp);
			this.link2.addORB();
			this.link3.setOwner(temp);
			this.link3.addORB();
			this.link4.setOwner(temp);
			this.link4.addORB();
		}
		else{
			System.out.println("Code Gdbd hai");
		}
		
//		if(Players.size()==1){
//			throw new WinnerException("Jeet gya koi");
//		}
	}

	public boolean isOwnedBy(Color i){
		if( i==this.Owner){
			return true;
		}
		return false;
	}

	public boolean isFull(){
		if (this.Criticalmass==this.orbs){
			return true;
		}
		return false;
	}

	public Cell getLink1() {
		return link1;
	}
	public void setLink1(Cell link1) {
		this.link1 = link1;
	}
	public Cell getLink2() {
		return link2;
	}
	public void setLink2(Cell link2) {
		this.link2 = link2;
	}
	public Cell getLink3() {
		return link3;
	}
	public void setLink3(Cell link3) {
		this.link3 = link3;
	}
	public Cell getLink4() {
		return link4;
	}
	public void setLink4(Cell link4) {
		this.link4 = link4;
	}
	public Color getOwner() {
		return Owner;
	}
	public void setOwner(Color temp) {
		Ownstr=temp.toString();
		Owner = temp;
	}
	public int getCriticalmass() {
		return Criticalmass;
	}

	public String getOwnstr() {
		return Ownstr;
	}

	public void setOwnstr(String ownstr) {
		Ownstr = ownstr;
	}
}
