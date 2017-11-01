package v1.oo;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class tile extends StackPane{
	 Rectangle border;Game g;
	 int clicks=0;
	 Node link1;
	Node link2;
	Node link3;
	private Node link4;
		private int Criticalmass;
		private Color color;		//extra hai
		private Color Owner; 			
		private int orbs;
		
	 public tile(Cell k,Game g,int cmass){
		 this.g=g;
		 border=new Rectangle(50,50);
		 border.setFill(Color.BLACK);
		 border.setStroke(Color.RED);
		 this.Owner=null;
		 this.Criticalmass=cmass;
		 getChildren().addAll(border);
		 setOnMouseClicked(event->{
			 int x=GridPane.getColumnIndex(this);
			 int y=GridPane.getRowIndex(this);
			 System.out.println(x+" "+y);
			 try {
					g.getPlayers().get(clicks%g.getPlayers().size()).takeTurn(y,x);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
				 g.checkplayers();
				 g.show();
			 int porbs=g.getMatrix().board[y][x].getOrbs();
			 System.out.println("orbs no."+porbs);
			 if(porbs==1){
				 animation a=new animation(Color.BROWN,1);
				 getChildren().add(a.a);
			 }
			 else{
				 //System.out.println(getChildren().size());
				 getChildren().remove(1);
				 animation a=new animation(Color.BISQUE,porbs);
				 a.setcoordinates();
				 a.addanimation();
				 getChildren().addAll(a.a);
			 }
		 });
	 }
		public void explode() {
			// TODO Auto-generated method stub
			if (this.Criticalmass==2){
				this.orbs=0;
				Color temp=this.color;
				((tile) this.link1).setOwner(temp);
				((tile) this.link1).addORB();
				((tile) this.link2).setOwner(temp);
				((tile) this.link2).addORB();
			}
			else if(this.Criticalmass==3){
				this.orbs=0;
				Color temp=this.color;
				((tile) this.link1).setOwner(temp);
				((tile) this.link1).addORB();
				System.out.println("orbs no."+this.orbs);
				((tile) this.link2).setOwner(temp);
				((tile) this.link2).addORB();
				((tile) this.link3).setOwner(temp);
				((tile) this.link3).addORB();
			}
			else if(this.Criticalmass==4){
				this.orbs=0;
				Color temp=this.color;
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
			System.out.println(GridPane.getColumnIndex(this));
			
			this.orbs++;
			if(this.isFull()){
				this.explode();
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
