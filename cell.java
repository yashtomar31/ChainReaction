package v1.oo;
import javafx.scene.paint.Color;

public class Cell{
	private Cell link1,link2,link3,link4;
	private int Criticalmass;
	private Color color;		//extra hai
	private Color Owner; 			
	private int orbs;
	
	
	public int getOrbs() {
		return orbs;
	}

	public void setOrbs(int orbs) {
		this.orbs = orbs;
	}

	public Cell(int cm){
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
		if (this.Criticalmass==2){
			this.orbs=0;
			Color temp=this.color;
			this.link1.setOwner(temp);
			this.link1.addORB();
			this.link2.setOwner(temp);
			this.link2.addORB();
		}
		else if(this.Criticalmass==3){
			this.orbs=0;
			Color temp=this.color;
			this.link1.setOwner(temp);
			this.link1.addORB();
			System.out.println("orbs no."+this.orbs);
			this.link2.setOwner(temp);
			this.link2.addORB();
			this.link3.setOwner(temp);
			this.link3.addORB();
		}
		else if(this.Criticalmass==4){
			this.orbs=0;
			Color temp=this.color;
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color2) {
		this.color = color2;
	}
	public Color getOwner() {
		return Owner;
	}
	public void setOwner(Color temp) {
		Owner = temp;
	}
	public int getCriticalmass() {
		return Criticalmass;
	}
}