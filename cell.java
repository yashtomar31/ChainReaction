package v1.oo;

public class Cell {
	private Cell link1,link2,link3,link4;
	private int Criticalmass;
	private String color;
	private int Owner;
	private int orbs;
	
	public int getOrbs() {
		return orbs;
	}

	public void setOrbs(int orbs) {
		this.orbs = orbs;
	}

	public Cell(int cm){
		this.Criticalmass=cm;
	}
	
	public void addORB(){
		this.orbs++;
		if(this.isFull()){
			this.explode();
			this.orbs=0;
		}
	}
	
	public void explode() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isOwnedBy(int i){
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getOwner() {
		return Owner;
	}
	public void setOwner(int owner) {
		Owner = owner;
	}
	public int getCriticalmass() {
		return Criticalmass;
	}
}
