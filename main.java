package v1.oo;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {
	private Matrix a;
	static Stage thestage;
	static int noofplayer;
	static ChoiceBox<String> ncb;
	static ChoiceBox<String> gridbox;
	static String grid;
	static int m,n;
	static GridPane gp;
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
	 private static void game(){
			Pane root=new Pane();
			 gp=new GridPane();
			 main akla=new main();
			 gp.setMinSize(m*50,(n+1)*50);
			 gp.setAlignment(Pos.CENTER);
			 for(int i=0;i<m;i++){
				 for(int j=0;j<n;j++){
					 tile a=akla.new tile();
					 root.getChildren().add(a);
					 gp.add(a,j,i);
				 }	
			 }
			 ChoiceBox<String> ccb = new ChoiceBox<String>(); 
		      ccb.getItems().addAll ("Start game", "Exit");
		      gp.add(ccb,n+1,0);
			 Scene scgame = new Scene(gp);
			 thestage.setScene(scgame);
			 thestage.show();
		 
	 }
	 class tile extends StackPane{
		 public tile(){
			 final PhongMaterial redMaterial = new PhongMaterial();
		        redMaterial.setDiffuseColor(Color.DARKRED);
		        redMaterial.setSpecularColor(Color.RED);
			 Rectangle border=new Rectangle(50,50);
			 border.setFill(null);
			 border.setStroke(Color.BLACK);
			 setAlignment(Pos.CENTER);
			 getChildren().addAll(border);
			 setOnMouseClicked(event->{
				 Sphere sphere = new Sphere();
				 sphere.setRadius(10);
				 sphere.setMaterial(redMaterial);
				 getChildren().addAll(sphere);
				 int x=gp.getColumnIndex(this);
				 int y=gp.getRowIndex(this);
				 System.out.println(x+" "+y);
			 });
		 }
	 }
	 

}
//class switchpane extends Application{
//	 Button btnscene1, btnscene2;
//	    Label lblscene1, lblscene2;
//	    FlowPane pane1, pane2;
//	    Scene scene1, scene2;
//	    Stage thestage;
//	 
//	    public static void main(String[] args) {
//	        launch(args);
//	    }
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		 thestage=primaryStage;
//	        //can now use the stage in other methods
//	       
//	        //make things to put on panes
//	        btnscene1=new Button("Click to go to Other Scene");
//	        btnscene2=new Button("Click to go back to First Scene");
//	        btnscene1.setOnAction(e-> ButtonClicked(e));
//	        btnscene2.setOnAction(e-> ButtonClicked(e));
//	        lblscene1=new Label();
//	        lblscene2=new Label();
//	        //make 2 Panes
//	        pane1=new FlowPane();
//	        pane2=new FlowPane();
//	        pane1.setVgap(10);
//	        pane2.setVgap(10);
//	        //set background color of each Pane
//	        pane1.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
//	        pane2.setStyle("-fx-background-color: red;-fx-padding: 10px;");
//	           
//	        //add everything to panes
//	        pane1.getChildren().addAll(lblscene1, btnscene1);
//	        pane2.getChildren().addAll(lblscene2, btnscene2);
//	        scene1 = new Scene(pane1, 200, 100);
//	        scene2 = new Scene(pane2, 200, 100);
//	        
//	        primaryStage.setTitle("Hello World!");
//	        primaryStage.setScene(scene1);
//	        primaryStage.show();
//		
//	}
//	 public void ButtonClicked(javafx.event.ActionEvent e)
//	    {
//	        if (e.getSource()==btnscene1)
//	            thestage.setScene(scene2);
//	        else
//	            thestage.setScene(scene1);
//	    }
//
//}
