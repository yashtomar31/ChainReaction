
//import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
//import javafx.scene.control.Label;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {


	static Game g;
	static Stage thestage;
	static int noofplayer;
	static ChoiceBox<String> ncb;
	static ChoiceBox<String> gridbox;
	static String grid;
	static int m,n;
	 public static void main(String[] args) {
	        launch(args);
	    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		menupaine(primaryStage);

	}
	 public static void ButtonClicked(javafx.event.ActionEvent e){
		 noofplayer=Integer.parseInt((ncb.getValue().toString()));
		 grid=gridbox.getValue().toString();
		 if(grid.equals("15x10")){
			 m=15;
			 n=10;
		 }
		 else{
			 m=9;
			 n=6;
		 }

		 settings();
	 }
	 public static void ngame(javafx.event.ActionEvent e){
		 noofplayer=Integer.parseInt((ncb.getValue().toString()));
		 grid=gridbox.getValue().toString();
		 if(grid.equals("15x10")){
			 m=15;
			 n=10;
		 }
		 else{
			 m=9;
			 n=6;
		 }
		 game();
	 }
	 public static void menupaine(Stage primaryStage){
		 thestage=primaryStage;
		 GridPane gridPane=new GridPane();
		ncb = new ChoiceBox<String>();
	      ncb.getItems().addAll ("2","3" ,"4", "5", "6", "7","8");
	    Text namegameplayer=new Text("No. of players");
	    Text gridsize=new Text("Choose grid size");
	    gridbox=new ChoiceBox<String>();
	    gridbox.getItems().addAll("9x6","15x10");
	    Button Ngame = new Button("Start Game");
	    Button Rgame = new Button("Resume Game");
	    Button Settings = new Button("Settings");
	    gridPane.setMinSize(500, 500);
	    gridPane.setPadding(new Insets(10, 10, 10, 10));
	    gridPane.setAlignment(Pos.CENTER);
	    gridPane.add(namegameplayer,0,0);
	    gridPane.add(ncb,1,0);
	    gridPane.add(gridsize,0,1);
	    gridPane.add(gridbox,1,1);
	    gridPane.add(Ngame,0,2);
	    gridPane.add(Rgame,1,2);
	    gridPane.add(Settings,2,2);
	      gridPane.setVgap(5);
	      gridPane.setHgap(5);
	      gridPane.setStyle("-fx-background-color: BEIGE;");
	    Scene SceneMenu = new Scene(gridPane);
		primaryStage.setScene(SceneMenu	);
		primaryStage.show();
		Settings.setOnAction(e->ButtonClicked(e));
		Ngame.setOnAction(e->ngame(e));



	 }
	 public static void settings(){
		 GridPane gridPane=new GridPane();
		 int nplayer=noofplayer;
		  gridPane.setMinSize(500, 500);
		 gridPane.setPadding(new Insets(10, 10, 10, 10));
		 gridPane.setAlignment(Pos.CENTER);
		 for(int i=0;i<nplayer;i++){
			 Text pc=new Text("Choose colour for " +i);
		 ChoiceBox<String> ccb = new ChoiceBox<String>();
	      ccb.getItems().addAll ("red", "blue", "green", "orange", "yellow","black");
	      gridPane.add(ccb,1,i);
	      gridPane.add(pc,0,i);

	      }
		 gridPane.setVgap(5);
		 gridPane.setHgap(5);
		 gridPane.setStyle("-fx-background-color: BEIGE;");
		 Scene SceneSettings = new Scene(gridPane);
		 thestage.setScene(SceneSettings);
		 thestage.show();
	 }

	 static int grid_tile_row,grid_tile_coloumn;
	 static GridPane gp;
	 private static boolean flag=false;


	 private static void game(){
//			Pane root=new Pane();
		 	g=new Game(m,n,noofplayer);
		 	 gp=new GridPane();
			 gp.setMinSize(m*50,(n+1)*50);
			 gp.setAlignment(Pos.CENTER);
			 Button undo=new Button("UNDO");
			 gp.add(undo, n+1, 1);
			 undo.setOnMouseClicked(e -> {
				 nm=0;
				 flag=true;
				 game();
			 }
			 );
			 for(int i=0;i<m;i++){
				 for(int j=0;j<n;j++){
					 g.getMatrix().board[i][j].setOwner(Color.BLACK);
					 tile a=new tile(g.getMatrix().board[i][j],g,g.getMatrix().board[i][j].getCriticalmass());
					 a.setOwner(Color.BLACK);

					 a.setOnMouseClicked(e->{
						try {
							Buttonclick(e,a);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
//					 root.getChildren().add(a);
					 gp.add(a,j,i);

				 }
			 }
			 setlinks(gp);
			 g.addplayer(Color.BLUE);
			 g.addplayer(Color.RED);
			 ChoiceBox<String> ccb = new ChoiceBox<String>();
			 if(flag){
					Game previous=null;
					try {
						previous = Game.deserialize();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					g=previous;
//					Main temp=new Main();
					for(int i=0;i<previous.x;i++){
						for(int j=0;j<previous.y;j++){
							int orbs= previous.getMatrix().getBoard()[i][j].getOrbs();
							String owner= previous.getMatrix().getBoard()[i][j].getOwnstr();
							tile pointer=(tile) Main.getNode(i, j, gp);
							pointer.setOwner(Color.web(owner));
//							pointer.getChildren().remove(1);
							for(int k=0;k<orbs;k++){
								pointer.addORB();
							}
						}
					}
			 }
		     ccb.getItems().addAll ("Start game", "Exit");
		     gp.add(ccb,n+1,0);
			 Scene scgame = new Scene(gp);
			 thestage.setScene(scgame);
			 thestage.show();
	 }

	 private static Object Undo() throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		Game previous=Game.deserialize();
		previous.show();
		return null;
	}

	static int nm=0;
	 static void Buttonclick(MouseEvent e,tile a) throws FileNotFoundException, IOException{
		 Game.serialize(g);
		 grid_tile_coloumn=GridPane.getColumnIndex(a);
		 grid_tile_row=GridPane.getRowIndex(a);
		// System.out.println(grid_tile_coloumn+" "+grid_tile_row);
		// System.out.println(a.getOwner()+" "+Color.BLACK);
		 if(g.getPlayers().peek().getColor()==a.getOwner()||a.getOwner()==Color.BLACK){
		 Player temp=g.getPlayers().remove();
		 a.setOwner(temp.getColor());
		 try {
			temp.takeTurn(grid_tile_row, grid_tile_coloumn);
		} catch (IOException e1) {
			System.out.println("taketurn error");
		}
		 g.getPlayers().add(temp);
		 System.out.println("No. of players "+g.getPlayers().size());
		 a.addORB();
		 if(nm>1){//add no.ofplayers-1
			 g.checkplayers();
			 }
			//g.show();
		 nm++;
		 changegridcolour(g.getPlayers().peek().getColor(),gp);
		 }
	 }
	 static void setlinks(GridPane gp){
//			Main akla=new Main();
			for(int i=1;i<m-1;i++){
				for(int j=1;j<n-1;j++){//i,j
					((tile) Main.getNode(i,j, gp)).setLink1(Main.getNode(i,j-1,gp));
					((tile) Main.getNode(i,j, gp)).setLink2(Main.getNode(i+1,j,gp));//i+1,j
					((tile) Main.getNode(i,j, gp)).setLink3(Main.getNode(i,j+1,gp));//i,j+1
					((tile) Main.getNode(i,j, gp)).setLink4(Main.getNode(i-1,j,gp));//i-1,j
				}
			}

			for(int i=1;i<m-1;i++){//i,0
				((tile) akla.getNode(i,0,gp)).setLink1(akla.getNode(i+1,0,gp));//i+1,0,1
				((tile) akla.getNode(i,0,gp)).setLink2(akla.getNode(i,1,gp));//i,1,2
				((tile) akla.getNode(i,0,gp)).setLink3(akla.getNode(i-1,0,gp));//i-1,0,3
				//i,n-1
				((tile) akla.getNode(i,n-1,gp)).setLink1(akla.getNode(i-1,n-1,gp));//i-1,n-1
				((tile) akla.getNode(i,n-1,gp)).setLink2(akla.getNode(i,n-2,gp));//i+1,n-1
				((tile) akla.getNode(i,n-1,gp)).setLink3(akla.getNode(i+1,n-1,gp));//i,n-2
			}

			for(int i=1;i<n-1;i++){//0,i
				((tile) Main.getNode(0,i,gp)).setLink1(Main.getNode(0,i-1,gp));//0,i-1
				((tile) Main.getNode(0,i,gp)).setLink2(Main.getNode(1,i,gp));//1,i
				((tile) Main.getNode(0,i,gp)).setLink3(Main.getNode(0,i+1,gp));//0,i+1
				//m-1,i
				((tile) Main.getNode(m-1,i,gp)).setLink1(Main.getNode(m-1,i-1,gp));//m-1,i-1
				((tile) Main.getNode(m-1,i,gp)).setLink2(Main.getNode(m-2,i,gp));//m-1,i+1
				((tile) Main.getNode(m-1,i,gp)).setLink3(Main.getNode(m-1,i+1,gp));//m-1,i
			}

			((tile) akla.getNode(0,0,gp)).setLink1(akla.getNode(1,0,gp));//
			((tile) akla.getNode(0,0,gp)).setLink2(akla.getNode(0,1,gp));

			((tile) akla.getNode(m-1,0,gp)).setLink1(akla.getNode(m-1,1,gp));
			((tile) akla.getNode(m-1,0,gp)).setLink2(akla.getNode(m-2,0,gp));

			((tile) akla.getNode(0,n-1,gp)).setLink1(akla.getNode(0,n-2,gp));
			((tile) akla.getNode(0,n-1,gp)).setLink2(akla.getNode(1,n-1,gp));

			((tile) akla.getNode(m-1,n-1,gp))	.setLink1(akla.getNode(m-1,n-2,gp));
			((tile) akla.getNode(m-1,n-1,gp)).setLink2(akla.getNode(m-2,n-1,gp));
	 }
	 Node getNode (final int row, final int column, GridPane gridPane) {
		    Node result = null;
		    ObservableList<Node> childrens = gridPane.getChildren();

		    for (Node node : childrens) {
		        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
		            result = node;
		            break;
		        }
		    }

		    return result;
		}

	  static void changegridcolour(Color g,GridPane gp){
		 ObservableList<Node> children = gp.getChildren();
		 for(Node node:children){
			 try{
				 tile a=(tile)node;
				 a.border.setStroke(g);
			}
			 catch(Exception e){
			 }
		 }
	 }
}

class animation{
	Group a;
	RotateTransition rt;

	animation(Color c,int no){
		this.a=new Group();
		for(int i=0;i<no;i++){
			orb o=new orb(c);
			a.getChildren().add(o.getS());
		}
		this.rt=new RotateTransition(Duration.millis(2000), a);
	}
	void setcoordinates(){
		int size=a.getChildren().size();
		System.out.println(size);
		if(size==2){
			a.getChildren().get(1).setTranslateX(10);
			a.getChildren().get(1).setTranslateY(10);
			this.rt=new RotateTransition(Duration.millis(2000), a);
		}
		if(size==3){
			a.getChildren().get(0).setTranslateX(0);
			a.getChildren().get(0).setTranslateY(8);
			a.getChildren().get(1).setTranslateX(+6.666);
			a.getChildren().get(1).setTranslateY(-3.3333);
			a.getChildren().get(2).setTranslateX(-6.666);
			a.getChildren().get(2).setTranslateY(-3.3333);

		}
	}
	void addanimation(){
		 rt.setFromAngle(0);
		 rt.setCycleCount(RotateTransition.INDEFINITE);
		 rt.setInterpolator(Interpolator.LINEAR);
		 rt.setByAngle(360);
	     rt.play();
	 }



}
class orb{

	private Sphere s;

	orb(Color c){
		final PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseColor(c);
		 setS(new Sphere());
		 getS().setRadius(10);
		 getS().setMaterial(mat);
	}

	public Sphere getS() {
		return s;
	}
	public void setS(Sphere s) {
		this.s = s;
	}
}
