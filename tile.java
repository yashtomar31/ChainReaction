
import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

class tile extends StackPane{

	Rectangle border;
	Game g;
	private Node link1;
	private Node link2;
	private Node link3;
	private Node link4;
	private final int Criticalmass;
	transient private Color Owner;
	private int orbs;

	/**
	 * Constructor for tile
	 * With initialization of owner as black means no owner
	 * made stackpane as clickable object
	 * owner is null
	 * and each tile is of size 50x50
	 * @param k
	 * @param g
	 * @param cmass
	 * @author yash
	 */
	public tile(Cell k,Game g,int cmass){
		this.Owner=Color.BLACK;
		this.g=g;
		border=new Rectangle(50,50);
		border.setFill(Color.BLACK);
		this.Owner=null;
		this.Criticalmass=cmass;
		getChildren().addAll(border);
	}

	/**
	 * This is the method is called when no. of orbs in a cell is equal to critical mass of the cell
	 * So when critical mass is same as present orbs in cell so according to critical mass i recursively call add orbs on neighbours
	 *Make present orbs in that cell 0 and add 1 orb to each of the neighbour
	 * Make this owner as null and set owner of neighbouring orbs as this cell
	 * @author yash
	 */

	public void explode() {
		// TODO Auto-generated method stub
		try{
			getChildren().remove(1,this.getChildren().size());
		}
		catch(Exception e){

		}
		Color temp=this.Owner;
		if (this.Criticalmass==2){
			if(this.orbs>2){
				this.orbs=this.orbs-2;
				animation addorb=new animation(this.Owner,this.orbs);addorb.setcoordinates();
				addorb.addanimation();
				this.getChildren().add(addorb.a);
			}
			else{
				this.orbs=0;
				this.Owner=Color.BLACK;
			}
			((tile) this.link1).setOwner(temp);
			((tile) this.link1).addORB();
			((tile) this.link2).setOwner(temp);
			((tile) this.link2).addORB();
		}
		else if(this.Criticalmass==3){
			if(this.orbs>3){
				this.orbs=this.orbs-3;
				animation addorb=new animation(this.Owner,this.orbs);
				addorb.setcoordinates();
				addorb.addanimation();
				this.getChildren().add(addorb.a);
			}
			else{
				this.orbs=0;
				this.Owner=Color.BLACK;
			}
			((tile) this.link1).setOwner(temp);
			((tile) this.link1).addORB();
			((tile) this.link2).setOwner(temp);
			((tile) this.link2).addORB();
			((tile) this.link3).setOwner(temp);
			((tile) this.link3).addORB();
		}
		else if(this.Criticalmass==4){
			if(this.orbs>4){
				this.orbs=this.orbs-4;
				animation addorb=new animation(this.Owner,this.orbs);
				addorb.setcoordinates();
				addorb.addanimation();
				this.getChildren().add(addorb.a);
			}
			else{
				this.orbs=0;
				this.Owner=Color.BLACK;
			}
			((tile) this.link1).setOwner(temp);
			((tile) this.link1).addORB();
			((tile) this.link2).setOwner(temp);
			((tile) this.link2).addORB();
			((tile) this.link3).setOwner(temp);
			((tile) this.link3).addORB();
			((tile) this.link4).setOwner(temp);
			((tile) this.link4).addORB();
		}
		else{
			System.out.println("Code Gdbd hai");
		}
		
	}

	/**
	 * So when we click on tile this method is called on
	 *First of all simply increase the present no. of balls
	 * Then if there is no group present create a group and add 1 orb to it if already there add orb to it and set its coordinate and rotation
	 * If tile orbs is equal to critical mass basically remove group create new group and add orbs and set them on transition
	 * When critical mass is equal to present orbs get array of neighbour get its coordinate and then create an orb and move this orb in direction of coordinate by subtracting this cell coordinate from neighbouring coordinate
	 * store this transition in parallel transition
	 * And after adding all transition start the transition
	 * And after animation is finished  call the explode function to add orbs in neighbour
	 * If this orbs is greater than critical mass just do animation in one direction in which animation is overlapping
	 * @author yash
	 */
	public void addORB(){
		animation addorb;
		this.orbs++;
		try{
			getChildren().remove(1);
			int temp=this.orbs%this.Criticalmass;
			addorb=new animation(this.Owner,temp);
			addorb.setcoordinates();
			addorb.addanimation();
		}
		catch(IndexOutOfBoundsException e){
			addorb=new animation(this.Owner,this.orbs);
		}
		getChildren().add(addorb.a);
		ParallelTransition mainTransition = new ParallelTransition();
		ParallelTransition mainTransition2 = new ParallelTransition();
		if(this.isFull()){
			ArrayList<tile> neighbouringCells=this.getnbrs();
			if(this.orbs==this.Criticalmass){
				getChildren().remove(1,this.getChildren().size());
			}
			for (int i=0;i<this.Criticalmass;i++)
			{
				orb cur=new orb(this.Owner);
				tile neighbour = neighbouringCells.get(i);
				TranslateTransition move = new TranslateTransition();
				move.setDuration(Duration.seconds(1));
				move.setNode(cur.getS());
				int moveX = GridPane.getRowIndex(neighbour)- GridPane.getRowIndex(this);
				int moveY = GridPane.getColumnIndex(neighbour)- GridPane.getColumnIndex(this);
				move.setToX(moveY*50);
				move.setToY(moveX*50);
				mainTransition.getChildren().add(move);
				neighbour.toBack();
				this.getChildren().add(cur.getS());
				if(this.Criticalmass==2){
					System.out.println(this.orbs);
				}

			}
			mainTransition.play();
			mainTransition.setOnFinished(e->{
				this.getChildren().remove(1, this.getChildren().size());;
				this.explode();
			});

		}
		if(this.isgreater()){
			ArrayList<tile> neighbouringCells=this.getnbrs();
			if(this.orbs==this.Criticalmass){
				getChildren().remove(1,this.getChildren().size());
			}
			for (int i=0;i<this.orbs%this.getCriticalmass();i++)
			{
				orb cur=new orb(this.Owner);
				tile neighbour = neighbouringCells.get(i);
				TranslateTransition move = new TranslateTransition();
				move.setDuration(Duration.seconds(1));
				move.setNode(cur.getS());
				int moveX = GridPane.getRowIndex(neighbour)- GridPane.getRowIndex(this);
				int moveY = GridPane.getColumnIndex(neighbour)- GridPane.getColumnIndex(this);
				move.setToX(moveY*50);
				move.setToY(moveX*50);
				mainTransition2.getChildren().add(move);
				neighbour.toBack();
				this.getChildren().add(cur.getS());

			}
			mainTransition2.play();
			mainTransition.setOnFinished(e->{
			});
		}

	}

	/**
	 * arraylist to get neighbours of cell
	 * @return
	 * @author yash
	 */
	ArrayList<tile> getnbrs(){
		ArrayList<tile> a =new ArrayList<tile>();
		if(this.link1!=null){
			a.add((tile) link1);
		}
		if(this.link2!=null){
			a.add((tile) link2);
		}
		if(this.link3!=null){
			a.add((tile) link3);
		}
		if(this.link4!=null){
			a.add((tile) link4);
		}
		return a;

	}

	/**
	 * to check if cell owner is same as color passed
	 * @param i
	 * @return
	 * @author yash
	 */
	public boolean isOwnedBy(Color i){
		if( i==this.Owner){
			return true;
		}
		return false;
	}

	/**
	 * true if critical mass is equal to this orbs
	 * @return
	 * @author yash
	 */
	public boolean isFull(){
		if (this.Criticalmass==this.orbs){
			return true;
		}
		return false;
	}

	/**
	 * true if this orbs is greater than critical mass
	 * @return
	 * @author yash
	 */
	public boolean isgreater(){
		if(this.Criticalmass<this.orbs){
			return true;
		}
		return false;
	}

	/**
	 * setters and getters of parameters
	 * @return
	 * @author yash
	 */
	public Node getLink1() {
		return link1;
	}
	public void setLink1(Node node) {
		this.link1 = node;
	}
	public Node getLink2() {
		return link2;
	}
	public void setLink2(Node node) {
		this.link2 = node;
	}
	public Node getLink3() {
		return link3;
	}
	public void setLink3(Node node) {
		this.link3 = node;
	}
	public Node getLink4() {
		return link4;
	}
	public void setLink4(Node node) {
		this.link4 = node;
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

