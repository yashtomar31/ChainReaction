package v1.oo;
import java.util.ArrayList;
import java.util.ArrayList;

>>>>>>> f5587501c1dde75848ad30310ae140931737a179
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

class tile extends StackPane{

	Rectangle border;
	Game g;
	private Node link1;
	private Node link2;
	private Node link3;
	private Node link4;
	private int Criticalmass;
	transient private Color Owner;
	private int orbs;


	 public tile(Cell k,Game g,int cmass){
		 this.Owner=Color.BLACK;
		 this.g=g;
		 border=new Rectangle(50,50);
		 border.setFill(Color.BLACK);
		 border.setStroke(Color.RED);
		 this.Owner=null;
		 this.Criticalmass=cmass;
		 getChildren().addAll(border);
	 }

	public void explode() {
			// TODO Auto-generated method stub
			try{
				getChildren().remove(1);
				}
			catch(Exception e){

			}
			Color temp=this.Owner;
			this.Owner=Color.BLACK;
			if (this.Criticalmass==2){
				((tile) this.link1).setOwner(temp);
				((tile) this.link1).addORB();
				((tile) this.link2).setOwner(temp);
				((tile) this.link2).addORB();
			}
			else if(this.Criticalmass==3){
				((tile) this.link1).setOwner(temp);
				((tile) this.link1).addORB();
				//System.out.println("orbs no."+this.orbs);
				((tile) this.link2).setOwner(temp);
				((tile) this.link2).addORB();
				((tile) this.link3).setOwner(temp);
				((tile) this.link3).addORB();
			}
			else if(this.Criticalmass==4){
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
		 ArrayList<orb> createorblist(){
			ArrayList<orb> a=new ArrayList<orb>();
			for(int i=0;i<4;i++){
				a.add(new orb(getOwner()));
			}
			return a;
		}
		public void addORB(){
			animation addorb;
			this.orbs++;
			try{
			getChildren().remove(1);
			addorb=new animation(this.Owner,this.orbs);
			addorb.setcoordinates();
			addorb.addanimation();
			}
			catch(Exception e){
			 addorb=new animation(this.Owner,this.orbs);

			// System.out.println(this.orbs +" present orbs");
			}
			getChildren().add(addorb.a);
//			orb b=new orb(Color.RED);
//			TranslateTransition m1 = new TranslateTransition();
//			b.s.toFront();
//			m1.setDuration(Duration.seconds(1));
//            m1.setNode(b.s);
//            m1.setToX(60);
//            m1.setToY(0);
//            m1.play();
//            m1.setOnFinished(e -> {
//            	System.out.println("done");
//            });

			if(this.isFull()){
				ArrayList<tile> neighbouringCells=this.getnbrs();
				ArrayList<orb> allSpheres =this.createorblist();
				this.orbs=0;
				ParallelTransition mainTransition = new ParallelTransition();
				getChildren().remove(1);
				for (int i=0;i<neighbouringCells.size();i++)
	            {
					orb cur=allSpheres.get(i);
	                tile neighbour = neighbouringCells.get(i);
	                TranslateTransition move = new TranslateTransition();
	                move.setDuration(Duration.seconds(0.25));
         					move.setNode(cur.s);
	                int moveX = GridPane.getRowIndex(neighbour)- GridPane.getRowIndex(this);
	                int moveY = GridPane.getColumnIndex(neighbour)- GridPane.getColumnIndex(this);
	                System.out.println("moveX "+moveX);
	                System.out.println("moveY "+moveY);
	                move.setToX(moveY*50);
	                move.setToY(moveX*50);
	                mainTransition.getChildren().add(move);
	                neighbour.toBack();
	                this.getChildren().add(cur.s);
	            }
	            mainTransition.play();
	            mainTransition.setOnFinished(e->{
	            	this.getChildren().remove(1, this.getChildren().size());;
	            	this.explode();});
			}
		}
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
