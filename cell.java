import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.paint.Color;

/**
 * @author kshitiz
 *
 */
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

	public Queue<Player> getPlayers() {
		return Players;
	}

	public void setPlayers(Queue<Player> players) {
		Players = players;
	}

	public Cell(int cm,Queue<Player> Players){
		this.Players=Players;
		this.Criticalmass=cm;
		this.Owner=null;
	}

	public void addORB() throws WinnerException{
		this.orbs++;
//		System.out.println("Addinf orb: "+Players.size());
//		this.Displayplayers();
		if(this.isFull()){
			this.explode();
		}
	}

	public void explode() throws WinnerException {
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
		System.out.println("number of players : " + Players.size());
		this.checkplayers();
		System.out.println("Helloprefinal "+Players.size());
		if(Players.size()==0){
			System.out.println("Hellofinal");
			throw new WinnerException("Jeet gya koi");
		}
	}
//	
//	public void Displayplayers(){
//		int loop=Players.size();
//		for(int i=0;i<loop;i++){
//			Player temp=Players.remove();
//			System.out.println(temp.getColor());
//			Players.add(temp);
//		}
//	}
//	
	public void checkplayers() {
		Queue<Player> jugaad=new LinkedList<Player>();
		int loop=Players.size();
		System.out.println("size"+" "+loop);
		for(int i=0;i<loop;i++){
			//System.out.println("size "+Players.size());
			Player temp=Players.remove();
			System.out.println(i+" "+temp.getColor());
			System.out.println("yeh bachce hai "+i+" "+temp.getColor());
			if(temp.isActive()){
				System.out.println("yeh bach gaya "+i+" "+temp.getColor());
				jugaad.add(temp);
			}
		}
		loop=jugaad.size();
		for(int i=0;i<loop;i++){
			Player te=jugaad.remove();
			Players.add(te);
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
