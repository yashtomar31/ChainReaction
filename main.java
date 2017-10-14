package v1.oo;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {
	private Matrix a;
	static Stage thestage;
	 public static void main(String[] args) {
	        launch(args);
	    }
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Scanner scan = new Scanner(System.in);
//		int m=scan.nextInt();
//		int n=scan.nextInt();
//		a =new Matrix(m,n);
//		for(int i=0;i<m;i++){
//			for(int j=0;j<m;j++){
//				pane.add(a.board[i][j],j,i);
//				
//			}
//		}
//		BorderPane borderpane=new BorderPane();
//		borderpane.setCenter(pane);
//		Scene scene=new Scene(borderpane,450,300); 
//		primaryStage.setTitle("init");
		menupaine(primaryStage);
		
	}
	 public static void ButtonClicked(javafx.event.ActionEvent e){
		 settings();
	 }
	 public static void menupaine(Stage primaryStage){
		 thestage=primaryStage;
		 GridPane gridPane=new GridPane();
		 ChoiceBox ncb = new ChoiceBox(); 
	      ncb.getItems().addAll ("2", "4", "5", "6", "7","8");
	    Text namegameplayer=new Text("No. of players");
	    Text gridsize=new Text("Choose grid size");
	    ChoiceBox gridbox=new ChoiceBox();
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
		Settings.setOnAction(e-> ButtonClicked(e));
		
	 }
	 public static void settings(){
		 GridPane gridPane=new GridPane();
		 int nplayer=6;
		  gridPane.setMinSize(500, 500);
		 gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		 gridPane.setAlignment(Pos.CENTER);
		 for(int i=0;i<nplayer;i++){
			 Text pc=new Text("Choose colour for " +i);
		 ChoiceBox ncb = new ChoiceBox(); 
	      ncb.getItems().addAll ("red", "blue", "green", "orange", "yellow","black");
	      gridPane.add(ncb,1,i);
	      gridPane.add(pc,0,i);
	      
	      }
		 gridPane.setVgap(5);
		 gridPane.setHgap(5);
		 gridPane.setStyle("-fx-background-color: BEIGE;");
		 Scene SceneSettings = new Scene(gridPane);
		 thestage.setScene(SceneSettings);
		 thestage.show();
	 }
	 

}
class switchpane extends Application{
	 Button btnscene1, btnscene2;
	    Label lblscene1, lblscene2;
	    FlowPane pane1, pane2;
	    Scene scene1, scene2;
	    Stage thestage;
	 
	    public static void main(String[] args) {
	        launch(args);
	    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		 thestage=primaryStage;
	        //can now use the stage in other methods
	       
	        //make things to put on panes
	        btnscene1=new Button("Click to go to Other Scene");
	        btnscene2=new Button("Click to go back to First Scene");
	        btnscene1.setOnAction(e-> ButtonClicked(e));
	        btnscene2.setOnAction(e-> ButtonClicked(e));
	        lblscene1=new Label();
	        lblscene2=new Label();
	        //make 2 Panes
	        pane1=new FlowPane();
	        pane2=new FlowPane();
	        pane1.setVgap(10);
	        pane2.setVgap(10);
	        //set background color of each Pane
	        pane1.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
	        pane2.setStyle("-fx-background-color: red;-fx-padding: 10px;");
	           
	        //add everything to panes
	        pane1.getChildren().addAll(lblscene1, btnscene1);
	        pane2.getChildren().addAll(lblscene2, btnscene2);
	        scene1 = new Scene(pane1, 200, 100);
	        scene2 = new Scene(pane2, 200, 100);
	        
	        primaryStage.setTitle("Hello World!");
	        primaryStage.setScene(scene1);
	        primaryStage.show();
		
	}
	 public void ButtonClicked(javafx.event.ActionEvent e)
	    {
	        if (e.getSource()==btnscene1)
	            thestage.setScene(scene2);
	        else
	            thestage.setScene(scene1);
	    }

}
