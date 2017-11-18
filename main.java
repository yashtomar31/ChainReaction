
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Queue;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
//import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * @author kshitiz and yash
 *
 */
public class Main extends Application {


	private static Game g;
	private static Stage thestage;
	private static int noofplayer;
	private static ChoiceBox<String> ncb;
	private static int m,n;
	private static boolean settingFlag;
	private static boolean playerFlag;
	private static ArrayList<Paint> colorpicker=new ArrayList<Paint>();
//	private static boolean submitflag;
//	private static String submitval;

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		menupaine(primaryStage);

	}

	/**
	 *
	 * @param e Action event is passed
	 * Method called when button is setting button is clicked and this takes us to new setting page
	 * @author yash
	 */
	public static void ButtonClicked(javafx.event.ActionEvent e){
		noofplayer=Integer.parseInt((ncb.getValue().toString()));
		settings();
	}

	/**
	 * Method called when a new game starts and it initializes the game
	 * @author yash
	 */
	public static void ngame(){
		noofplayer=Integer.parseInt((ncb.getValue().toString()));
		g=new Game(m,n,noofplayer);
		g.setNoofplayer(noofplayer);
		for(int i=0;i<noofplayer;i++){
//			System.out.println(i);
			g.addplayer((Color)colorpicker.get(i),i+1);
//			System.out.println((Color)colorpicker.get(i));
		}
		g.setBc(colorpicker);
		game();
	}


	/**
	 * Method is used resume the previous saved game, then initializes the game.
	 * @author kshitiz
	 */
	public static void rgame(javafx.event.ActionEvent e){
		Game temp=null;
		try {
			temp=Game.deserialize2();
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
//		System.out.println(temp.x+" "+temp.y+" "+temp.getPlayers().size());
		noofplayer=temp.getPlayers().size();
		m=temp.x;n=temp.y;
		g=new Game(temp.x,temp.y,temp.getPlayers().size());
		g.setPlayers(temp.getPlayers());
		flag2=true;
		game();
	}

	/**
	 *
	 * @param primaryStage Stage for which has to be drawn
	 *Method used to make menu page
	 * here we can select to start game with selction of new game and have to select grid size and no. of player
	 * Default no. of player as two default size of grid is 9x16
	 * we can go to settings page to select color of players
	 * @author yash
	 */
	public static void menupaine(Stage primaryStage){
		if(!settingFlag){
			colorpicker.add(Paint.valueOf("#CD5C5C"));
			colorpicker.add(Paint.valueOf("#C39BD3"));
			colorpicker.add(Paint.valueOf("#45B39D"));
			colorpicker.add(Paint.valueOf("#F1C40F"));
			colorpicker.add(Paint.valueOf("#DC7633"));
			colorpicker.add(Paint.valueOf("#2E86C1"));
			colorpicker.add(Paint.valueOf("#ECF0F1"));
			colorpicker.add(Paint.valueOf("#F80D0D"));
		}
		thestage=primaryStage;
		AnchorPane bc=new AnchorPane();
		RadioButton b=new RadioButton();
		b.setText("9x6");
		RadioButton c=new RadioButton();
		c.setText("15x10");
		final ToggleGroup group = new ToggleGroup();
		b.setToggleGroup(group);
		c.setToggleGroup(group);
		b.setUserData("9x6");
		c.setUserData("15x10");
		b.setSelected(true);
		b.requestFocus();
		b.setAlignment(Pos.CENTER);
		b.setTextFill(Color.WHITE);
		c.setTextFill(Color.WHITE);
		m=9;
		n=6;
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
								Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() != null) {

					if(group.getSelectedToggle().getUserData().toString().equals("15x10")){
						m=15;
						n=10;
					}
					else{
						m=9;
						n=6;
					}
					System.out.print(m+ " "+ n);
				}
			}
		});
		bc.setStyle("\"-fx-background-color: Beige;\"");
		Label ch=new Label(" CHAIN REACTION");
		ch.setTextAlignment(TextAlignment.CENTER);
		ch.setFont(Font.font("Georgia",40));
		ch.setTextFill(Paint.valueOf("#ebe2e2"));
		bc.getChildren().add(ch);
		ch.setLayoutX(135);
		ch.setLayoutY(63);
		GridPane gridPane=new GridPane();
		ncb = new ChoiceBox<String>();
		gridPane.add(ch,0,7,4,1);
		ncb.getItems().addAll ("2","3" ,"4", "5", "6", "7","8");
		if(playerFlag){
			ncb.setValue(String.valueOf(noofplayer));
		}
		else {
			ncb.setValue("2");
		}
		Text namegameplayer=new Text("No. of players  ");
		Text gridsize=new Text("Grid size");
		gridsize.setFill(Color.WHITE);
		namegameplayer.setFill(Color.WHITE);
		Button Ngame = new Button("Start Game");
		Ngame.setAlignment(Pos.CENTER);
		Button Rgame = new Button("Resume Game");
		Button Settings = new Button("Settings");
		gridPane.setMinSize(500, 500);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(namegameplayer,1,24);
		gridPane.add(ncb,2,24);
		gridPane.add(gridsize,1,34);
		gridPane.add(b,2,34);
		gridPane.add(c,3,34);
		gridPane.add(Ngame,0,48);
		gridPane.add(Rgame,2,48);
		gridPane.add(Settings,4,48);
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setStyle("-fx-background-color: darkslategray;");
		Scene SceneMenu = new Scene(gridPane);
		primaryStage.setScene(SceneMenu	);
		primaryStage.show();
		Settings.setOnAction(e->ButtonClicked(e));
		Ngame.setOnAction(e->ngame());
		Rgame.setOnAction(e->rgame(e));



	}

	/**
	 * Method used to make setting page
	 * with colorpicker used so that we can select color and only returns when color are different for different player
	 *and update static variable colorpicker
	 * @author yash
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void settings(){
		GridPane gridPane=new GridPane();
		gridPane.setStyle("-fx-background-color: darkslategray;");
		int nplayer=noofplayer;
		gridPane.setMinSize(500,500);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);
		//ArrayList<ColorPicker>
		for(int i=0;i<nplayer;i++){
			Text pc=new Text("Choose colour for " +(i+1)+" player");
			pc.setFill(Color.WHITE);
			orb akla=new orb((Color)colorpicker.get(i));
			gridPane.add(akla.getS(),3,i);
			ColorPicker ccb=new ColorPicker();
			ccb.setOnAction(new EventHandler() {
				@SuppressWarnings("static-access")
				public void handle(Event t) {
					orb bk=new orb(ccb.getValue());
					gridPane.add(bk.getS(),3,gridPane.getRowIndex(ccb));
					colorpicker.set(gridPane.getRowIndex(ccb),ccb.getValue());
				}
			});
			ccb.setValue((Color) colorpicker.get(i));
			gridPane.add(ccb,1,i);
			gridPane.add(pc,0,i);
		}
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		Button a=new Button();
		a.setText("Submit");
		gridPane.add(a,1,noofplayer+10);
		a.setOnMouseClicked(e->{
			if(!checkrepeat()){
				settingFlag=true;
				playerFlag=true;
				menupaine(thestage);
			}

		});
		Scene SceneSettings = new Scene(gridPane);
		thestage.setScene(SceneSettings);
		thestage.show();

	}

	/**
	 * to check if color in arraylist are same or not
	 * @return True if color repeats
	 * true if color in arraylist are same
	 * @author yash
	 */

	static boolean checkrepeat(){
		for(int i=0;i<noofplayer;i++){
			for(int j=i+1;j<noofplayer;j++){
				if(colorpicker.get(i).equals(colorpicker.get(j))){
					return true;
				}
			}
		}
		return false;
	}

	static int grid_tile_row,grid_tile_coloumn;
	static GridPane gp;
	private static boolean flag=false;
	private static boolean flag2=false;

	private static boolean disable=true;

	/**
	 * This initializes grid
	 * Have buttons like undo to undo move and drop down for new game and exit game
	 * Here we make our grid with tiles as its element in grid and they are clickable and calls other method on click
	 * Undo: If undo is clicked then its flag is enabled and a new game(GUI) is initialized and then previous game(Game object) is deserialized
	 * and then game now continues from one turn before the undo is clicked.Methods like comeback and comeback2 helps to deseiralize the non-desiralizable 
	 * attributes of game. 
	 * Flag2 is enabled if the Resume button is clicked,a new game(GUI) is initialized and then previous game(Game object) is deserialized
	 * and then game now continues from the point which the previous game was left.  
	 * @author kshitiz,yash
	 */
	private static void game(){
		gp=new GridPane();
		gp.setMinSize(m*50,(n+1)*50);
		gp.setAlignment(Pos.CENTER);
		Button undo=new Button("UNDO");
		undo.setAlignment(Pos.CENTER);
		gp.add(undo, n+1, 1);
		undo.setOnMouseClicked(e -> {
					flag=true;
					if(disable){
						undo.disabledProperty();
					}
					game();
				}
		);
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				g.getMatrix().board[i][j].setOwner(Color.BLACK);
				tile a=new tile(g.getMatrix().board[i][j],g,g.getMatrix().board[i][j].getCriticalmass());
				a.setOwner(Color.BLACK);
				a.border.setStroke(g.getPlayers().peek().getColor());
				a.setOnMouseClicked(e-> {
					try {
						Buttonclick(e, a);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				});
//					 root.getChildren().add(a);
				gp.add(a,j,i);

			}
		}
		setlinks(gp);
		ChoiceBox<String> ccb = new ChoiceBox<String>();
		if(flag2){
			flag2=false;
			Game previous=null;
			try {
				previous = Game.deserialize2();
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
			g.comeback();
			g.comeback2();
			Main temp=new Main();
			changegridcolour(g.getPlayers().peek().getColor(),gp);
			for(int i=0;i<previous.x;i++){
				for(int j=0;j<previous.y;j++){
					int orbs= previous.getMatrix().getBoard()[i][j].getOrbs();
					String owner= previous.getMatrix().getBoard()[i][j].getOwnstr();
					owner=owner.substring(0,8);
					tile pointer=(tile) temp.getNode(i, j, gp);
					pointer.setOwner(Color.web(owner));
					for(int k=0;k<orbs;k++){
						pointer.addORB();
					}
					pointer.setOnMouseClicked(e->{
						try {
//							System.out.println("No. of Players : "+g.getPlayers().size());
							Buttonclick(e,pointer);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
				}
			}

		}
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
			g.comeback();
			g.comeback2();
			Main temp=new Main();
			changegridcolour(g.getPlayers().peek().getColor(),gp);
			for(int i=0;i<previous.x;i++){
				for(int j=0;j<previous.y;j++){
					int orbs= previous.getMatrix().getBoard()[i][j].getOrbs();
					String owner= previous.getMatrix().getBoard()[i][j].getOwnstr();
					owner=owner.substring(0,8);
					tile pointer=(tile) temp.getNode(i, j, gp);
					pointer.setOwner(Color.web(owner));
					for(int k=0;k<orbs;k++){
						pointer.addORB();
					}
					pointer.setOnMouseClicked(e->{
						try {
//							System.out.println("No. of Players : "+g.getPlayers().size());
							Buttonclick(e,pointer);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
				}
			}
		}
		ccb.getItems().addAll ("Select Further option","New game", "Exit");
		ccb.setValue("Select Further option");
		ccb.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {

						if(new_value.toString().equals("2")){
							thestage.close();
						}
						else{
							colorpicker=g.getBc();
							flag=false;
							ncb.setValue(String .valueOf(g.getNoofplayer()));
							System.out.println("aadfadadadadadfadfadfda "+noofplayer);
							flag2=false;
							nm=0;
							ngame();
							undo.setDisable(true);
						}
					}
				});

		gp.add(ccb,n+1,0);
		Scene scgame = new Scene(gp);

		thestage.setScene(scgame);
//		System.out.println("Hello");
		thestage.show();
	}

//	static void deactivatecell(){
//		Main akla=new Main();
//		for(int i=0;i<m;i++){
//			for(int j=0;j<n;j++){
//				akla.getNode(i,j,gp).setDisable(true);
//			}
//		}
//	}
//	static void activatecell(){
//		Main akla=new Main();
//		for(int i=0;i<m;i++){
//			for(int j=0;j<n;j++){
//				akla.getNode(i,j,gp).setDisable(false);
//			}
//		}
//	}


	private static int nm=0;

	/**
	 *This function is called when a player clicks on tile
	 * And when tile owner is equal to player or there is no owner then we call add orb method to add orb in it
	 * After every 
	 * After every time player takes turn it waits if winner exception is throw or not, if yes new dialogue box is 
	 * called which displays the winner and game is ended from that point.
	 * @param e Action event
	 * @param a Tile on which it is clicked
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author yash kshitiz
	 */
	

	public static boolean winnerflag=false;
	
	private static void Buttonclick(MouseEvent e,tile a) throws FileNotFoundException, IOException{
		disable=false;
//		System.out.println("hello3");
		grid_tile_coloumn=GridPane.getColumnIndex(a);
		grid_tile_row=GridPane.getRowIndex(a);
		System.out.println("coordinate " + grid_tile_coloumn+ " "+grid_tile_row);
//		System.out.println(grid_tile_coloumn+" "+grid_tile_row);
		if(g.getPlayers().peek().getColor().equals(a.getOwner())||a.getOwner().equals(Color.BLACK)){
			Game.serialize(g);
			//System.out.println("he");
			Player temp=g.getPlayers().remove();
			a.setOwner(temp.getColor());
			try {
				//System.out.println("Turn Tooked");
				temp.takeTurn(grid_tile_row, grid_tile_coloumn);
				//System.out.println("Hello4");
			}
			catch (WinnerException e1){
				System.out.println(e1.getMessage());
			}
			catch (Exception e1) {
				//deactivatecell();
				System.out.println("taketurn error");
			}
			g.getPlayers().add(temp);
			a.addORB();
			if(nm>noofplayer){//add no.ofplayers-1
				g.checkplayers();
			}
			//g.show();
			nm++;
			changegridcolour(g.getPlayers().peek().getColor(),gp);
		}
		Game.serialize2(g);
		if(winnerflag){
			Display("Player : "+g.getPlayers().peek().getNo()+" wins");
//			reset();
			winnerflag=false;
		}
	}

	/**
	 * @param msg Message to be displayed when dialogue box appears
	 * @author kshitiz
	 */
	public static void Display(String msg){
		Stage Stage = new Stage();
		Stage.setTitle("ERROR");
		Scene New = new Scene(new Group(), 300, 200, Color.GRAY);
		Button ok=new Button("OK");
		Button NewGame=new Button("New Game");
		ok.setOnMouseClicked(e ->{
			thestage.close();
			Stage.close();

		});
		ok.setLayoutX(80);
		ok.setLayoutY(90);
		NewGame.setLayoutX(140);
		NewGame.setLayoutY(90);
		ok.setAlignment(Pos.CENTER_LEFT);
		NewGame.setAlignment(Pos.CENTER_RIGHT);
		Pane grid = new Pane();
		Text t = new Text(100, 75,"");
		t.setText(msg);
		t.setFill(Color.WHITE);
		grid.getChildren().add(ok);
		grid.getChildren().add(t);
		grid.getChildren().add(NewGame);
		NewGame.setOnMouseClicked(e->{
			colorpicker=g.getBc();
			flag=false;
			ncb.setValue(String .valueOf(g.getNoofplayer()));
			System.out.println("aadfadadadadadfadfadfda "+noofplayer);
			flag2=false;
			nm=0;
			ngame();

		});
		Group ro = (Group)New.getRoot();
		ro.getChildren().add(grid);
		Stage.setScene(New);
		Stage.show();
	}

	/**
	 * @param gp gridpane which has to be linked
	 * used to set link between neighbours of cells
	 * So that each cell knows about its neighbour
	 * @author yash
	 */

	static void setlinks(GridPane gp){
		Main akla=new Main();
		for(int i=1;i<m-1;i++){
			for(int j=1;j<n-1;j++){//i,j
				((tile) akla.getNode(i,j, gp)).setLink1(akla.getNode(i,j-1,gp));
				((tile) akla.getNode(i,j, gp)).setLink2(akla.getNode(i+1,j,gp));//i+1,j
				((tile) akla.getNode(i,j, gp)).setLink3(akla.getNode(i,j+1,gp));//i,j+1
				((tile) akla.getNode(i,j, gp)).setLink4(akla.getNode(i-1,j,gp));//i-1,j
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
			((tile) akla.getNode(0,i,gp)).setLink1(akla.getNode(0,i-1,gp));//0,i-1
			((tile) akla.getNode(0,i,gp)).setLink2(akla.getNode(1,i,gp));//1,i
			((tile) akla.getNode(0,i,gp)).setLink3(akla.getNode(0,i+1,gp));//0,i+1
			//m-1,i
			((tile) akla.getNode(m-1,i,gp)).setLink1(akla.getNode(m-1,i-1,gp));//m-1,i-1
			((tile) akla.getNode(m-1,i,gp)).setLink2(akla.getNode(m-2,i,gp));//m-1,i+1
			((tile) akla.getNode(m-1,i,gp)).setLink3(akla.getNode(m-1,i+1,gp));//m-1,i
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

	/**
	 *
	 * @param row row number
	 * @param column column number
	 * @param gridPane gridpane from which node has to be found
	 * it is used to return a node from gridpane with given coordinate
	 * @return 	Node of given row and column no.
	 * @author yash
	 */
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

	/**
	 * it used to change gridcolor according to player turn
	 * it turns grid color with same as present player color
	 * @param g Color to which it has to be changed
	 * @param gp grid pane to be changed 
	 * @author yash
	 */
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





