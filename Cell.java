import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.paint.Color;

/**
 * @author kshitiz
 *
 */
public class Cell implements Serializable{
	
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

	
	/**
	 * This constructs the new cell setting the Players playing in the game, sets the critical mass of the respective cell
	 * @param cm
	 * @param Players
	 * @author kshitiz
	 */
	public Cell(int cm,Queue<Player> Players){
		this.Players=Players;
		this.Criticalmass=cm;
		this.Owner=null;
	}

	/**
	 * This method increases a orb in the respective cell, also checks if this the number of orbs is equals or greater than critical mass
	 * and if yes calls the method explodes
	 * 
	 * @throws WinnerException
	 * @author kshitiz
	 */
	
	public void addORB() throws WinnerException{
		this.orbs++;
//		System.out.println("Addinf orb: "+Players.size());
//		this.Displayplayers();
		if(this.isFull()){
			this.explode();
		}
	}

	/**
	 * This method is called when no. of obrs is equal or greater than the critical mass of the respective orb
	 * .It first stores the Owner of the cell in temp variable. It first check that the no. of orbs are greater 
	 * or equal to the critical mass of the cell. If it is greater than the critical mass this that no of orbs are 
	 * removed and those orbs are distributed across its neighbors.If no. of orbs is exactly equals to the critical mass
	 * cell is declared to be none by setting the color= BLACK. For distributing the orbs to the neighbors first the owner is 
	 * set and then one orb is added to the respective neighbor. At the end it checks if the players in the game are 1 (O is checked
	 * because the current player taking the turn is not included in the ongoing players list, and if no of players left is 1 then winner exception 
	 * thrown to the parent method and winner is declared.
	 * 
	 * @throws WinnerException
	 * @author kshitiz
	 */
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
	
	
	/**
	 * This method checks if the players in the list are active of not(through is active method), and updates the
	 * list accordingly
	 * 
	 * @author kshitiz
	 */
	public void checkplayers() {
		Queue<Player> jugaad=new LinkedList<Player>();
		int loop=Players.size();
//		System.out.println("size"+" "+loop);
		for(int i=0;i<loop;i++){
			//System.out.println("size "+Players.size());
			Player temp=Players.remove();
//			System.out.println(i+" "+temp.getColor());
//			System.out.println("yeh bachce hai "+i+" "+temp.getColor());
			if(temp.isActive()){
//				System.out.println("yeh bach gaya "+i+" "+temp.getColor());
				jugaad.add(temp);
			}
		}
		loop=jugaad.size();
		for(int i=0;i<loop;i++){
			Player te=jugaad.remove();
			Players.add(te);
		}
	}

	
	/**
	 * Check if this cell is owned by the player of given color i or not
	 * @param i
	 * @return
	 * @author kshitiz
	 */
	public boolean isOwnedBy(Color i){
		if( i==this.Owner){
			return true;
		}
		return false;
	}
	

	/**
	 * checks if the no. of orbs in cell is equal to cell's critical mass or not.
	 * @author kshitiz
	 * @return
	 */
	public boolean isFull(){
		if (this.Criticalmass==this.orbs){
			return true;
		}
		return false;
	}

	/**
	 * setter and getter of Cell
	 * @return
	 */
		
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
