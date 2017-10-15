import java.io.Serializable;

public class Cell implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cell link1,link2,link3,link4;
	private int Criticalmass;
	private String color;		
	private int orbs;
	
	
	public int getOrbs() {
		return orbs;
	}

	public void setOrbs(int orbs) {
		this.orbs = orbs;
	}

	public Cell(int cm){
		this.Criticalmass=cm;
		this.color="Black";
	}
	
	public void addORB(){
		this.orbs++;
		if(this.isFull()){
			this.explode();
		}
	}
	
	public void explode() {
		// TODO Auto-generated method stub
		if (this.Criticalmass==2){
			this.orbs=0;
			String temp=this.color;
			this.color="Black";
			this.link1.setColor(temp);
			this.link1.addORB();
			this.link2.setColor(temp);
			this.link2.addORB();
		}
		else if(this.Criticalmass==3){
			this.orbs=0;
			String temp=this.color;
			this.color="Black";
			this.link1.setColor(temp);
			this.link1.addORB();
			this.link2.setColor(temp);
			this.link2.addORB();
			this.link3.setColor(temp);
			this.link3.addORB();
		}
		else if(this.Criticalmass==4){
			this.orbs=0;
			String temp=this.color;
			this.color="Black";
			this.link1.setColor(temp);
			this.link1.addORB();
			this.link2.setColor(temp);
			this.link2.addORB();
			this.link3.setColor(temp);
			this.link3.addORB();
			this.link4.setColor(temp);
			this.link4.addORB();
		}
		else{
			System.out.println("Code Gdbd hai");
		}
	}
	
	public boolean isOwnedBy(Player p){
		if( p.getColor().equals(this.color)){
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
	public int getCriticalmass() {
		return Criticalmass;
	}
}