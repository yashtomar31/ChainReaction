//package v1.oo;
import java.util.ArrayList;

//import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
//import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
//import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
//import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Sphere;
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
	
	 public tile(Cell k,Game g,int cmass){
		 this.Owner=Color.BLACK;
		 this.g=g;
		 border=new Rectangle(50,50);
		 border.setFill(Color.BLACK);
		 this.Owner=null;
		 this.Criticalmass=cmass;
		 getChildren().addAll(border);
	 }

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
	                move.setDuration(Duration.seconds(0.5));
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
				for (int i=0;i<1;i++)
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
	                if(this.Criticalmass==2){
	                	System.out.println(this.orbs);
	                }
	   
	            }
	            mainTransition2.play();
	            mainTransition.setOnFinished(e->{
	            	});
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
		public boolean isgreater(){
			if(this.Criticalmass<this.orbs){
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
//class animation{
//	Group a;
//	RotateTransition rt;
//
//	animation(Color c,int no){
//		this.a=new Group();
//		for(int i=0;i<no;i++){
//			orb o=new orb(c);
//			a.getChildren().add(o.getS());
//		}
//		this.rt=new RotateTransition(Duration.millis(2000), a);
//	}
//	void setcoordinates(){
//		int size=a.getChildren().size();
//		System.out.println(size);
//		if(size==2){
//			a.getChildren().get(1).setTranslateX(10);
//			a.getChildren().get(1).setTranslateY(10);
//			this.rt=new RotateTransition(Duration.millis(2000), a);
//		}
//		if(size==3){
//			a.getChildren().get(0).setTranslateX(0);
//			a.getChildren().get(0).setTranslateY(8);
//			a.getChildren().get(1).setTranslateX(+6.666);
//			a.getChildren().get(1).setTranslateY(-3.3333);
//			a.getChildren().get(2).setTranslateX(-6.666);
//			a.getChildren().get(2).setTranslateY(-3.3333);
//
//		}
//	}
//	void addanimation(){
//		 rt.setFromAngle(0);
//		 rt.setCycleCount(RotateTransition.INDEFINITE);
//		 rt.setInterpolator(Interpolator.LINEAR);
//		 rt.setByAngle(360);
//	     rt.play();
//	 }
//
//
//
//}
//class orb{
//
//	private Sphere s;
//
//	orb(Color c){
//		final PhongMaterial mat = new PhongMaterial();
//        mat.setDiffuseColor(c);
//		 setS(new Sphere());
//		 getS().setRadius(10);
//		 getS().setMaterial(mat);
//	}
//
//	public Sphere getS() {
//		return s;
//	}
//	public void setS(Sphere s) {
//		this.s = s;
//	}
//}
//