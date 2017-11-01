package v1.oo;

//import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.collections.ObservableList;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.Label;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class temp extends Application {
	public static void serialize(Game obj) throws FileNotFoundException, IOException{
		ObjectOutputStream out=null;
		try{
			out=new ObjectOutputStream(new FileOutputStream("out.txt"));
			out.writeObject(obj);
		}
		finally{
			out.close();
		}
	}
	
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
		 g=new Game(m,n,noofplayer);
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
	 private static void game(){
			Pane root=new Pane();
			 GridPane gp=new GridPane();
			 gp.setMinSize(m*50,(n+1)*50);
			 gp.setAlignment(Pos.CENTER);
			 for(int i=0;i<m;i++){
				 for(int j=0;j<n;j++){
					 tile a=new tile(g.getMatrix().board[i][j],g,g.getMatrix().board[i][j].getCriticalmass());
					 g.getMatrix().board[i][j].setColor(Color.BLACK);
					 root.getChildren().add(a);
					 gp.add(a,j,i);
					 
				 }	
			 }
			 
			 g.addplayer(Color.RED);
			 g.addplayer(Color.BLUE);
			 ChoiceBox<String> ccb = new ChoiceBox<String>(); 
		      ccb.getItems().addAll ("Start game", "Exit");
		      gp.add(ccb,n+1,0);
			 Scene scgame = new Scene(gp);
			 thestage.setScene(scgame);
			 thestage.show();
		 
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
	// private int clicks=0
	 

}


