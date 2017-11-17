//package v1.oo;
//import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.StringTokenizer;

//import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * @author kshitiz
 *
 */
public class Game implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private	Queue<Player> Players;
	private Matrix matrix;
	private ArrayList<String> arcolor=new ArrayList<String>();
	private int noofplayer;

	public static void serialize(Game obj) throws FileNotFoundException, IOException{
		ObjectOutputStream out=null;
		try{
			out=new ObjectOutputStream(new FileOutputStream("out.ser"));
			out.writeObject(obj);
		}
		finally{
			out.close();
		}
	}

	public static Game deserialize() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in=null;
		Game obj;
		try{
			in=new ObjectInputStream(new FileInputStream("out.ser"));
			obj= (Game) in.readObject();
		}
		finally{
			in.close();
		}
		return obj;
	}

	public static void serialize2(Game obj) throws FileNotFoundException, IOException{
		ObjectOutputStream out=null;
		try{
			out=new ObjectOutputStream(new FileOutputStream("out2.ser"));
			out.writeObject(obj);
		}
		finally{
			out.close();
		}
	}

	public static Game deserialize2() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in=null;
		Game obj;
		try{
			in=new ObjectInputStream(new FileInputStream("out2.ser"));
			obj= (Game) in.readObject();
		}
		finally{
			in.close();
		}
		return obj;
	}

	int x,y;
	
	/**
	 * Construct Game object and initiaties a Game with board dimensions mXn and players k
	 * @param m
	 * @param n
	 * @param k
	 */
	public Game(int m,int n,int k) {
		Players=new LinkedList<Player>();
		matrix=new Matrix(m,n,Players);
		matrix.setCounter(k);
		x=m;
		y=n;
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				this.matrix.board[i][j].setOwner(Color.BLACK);
			}
		}
	}
//		for(int i=0;i<k;i++){
//			Players.add(i, new Player("A"+i,matrix));
//		}

	
	/**
	 * This method is used after serializing the Game object, this is because the COLOR of javafx is non-serializable and
	 * hence cannot be saved. 
	 * @author kshitiz
	 */
	public void comeback(){
		for(int i=0;i<this.matrix.getM();i++){
			for(int j=0;j<this.matrix.getN();j++){
				this.matrix.getBoard()[i][j].setOwner(Color.web(this.matrix.getBoard()[i][j].getOwnstr()));
			}
		}
	}

	/**
	 * add player in queue
	 * @param c
	 * @param n
	 * @author yash
	 *
	 */
	public void addplayer(Color c,int n){
		Players.add(new Player(c,matrix,n));
		System.out.println("Gamelist: "+Players.size());
		System.out.println("Celllist: " +this.matrix.board[5][5].getPlayers().size());
	}
	
	/**
	 * This method return true if no. of players left in game is 1, and hence used for declaring winner in game
	 * @return
	 * @author kshitiz
	 */
	public boolean isWinner(){
		if (Players.size()==1){
			return true;
		}
		return false;
	}
	
	/**
	 * Displays the current matrix in console, used for debugging 
	 * @author kshitiz
	 */
	public void show(){
		for(int j=0;j<matrix.getM();j++){
			for(int k=0;k<matrix.getN();k++){
				System.out.print(matrix.getBoard()[j][k].getOwnstr()+"("+matrix.getBoard()[j][k].getOrbs()+")" +" ");
			}
			System.out.println();
		}
	}

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
			if(isActive(temp)){
//				System.out.println(i+" "+temp.getColor());
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
	 * This method is used after serializing the Game object, this is because the COLOR of javafx is non-serializable and
	 * hence cannot be saved. This method helps Players to regain their color which is saved in their String attribute. 
	 * @author kshitiz
	 */
	public void comeback2(){
		Queue<Player> jugaad=new LinkedList<Player>();
		int loop=Players.size();
		System.out.println("size"+" "+loop);
		for(int i=0;i<loop;i++){
			//System.out.println("size "+Players.size());
			Player temp=Players.remove();
//			System.out.println(i+" "+temp.getColor());
			temp.setColor(Color.web(temp.getColstr()));
			jugaad.add(temp);
		}
		loop=jugaad.size();
		for(int i=0;i<loop;i++){
			//System.out.println("size "+Players.size());
			Player temp=jugaad.remove();
			Players.add(temp);
		}
	}

	/**
	 * Checks it temp player is active or not, it iterates over all the cells and checks each cell is it owned by the player or not
	 * and returns true if yes and false otherwise
	 * @param temp
	 * @return
	 * @author kshitiz
	 */
	boolean isActive(Player temp){
		int points=0;
		for (int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				if(this.getMatrix().getBoard()[i][j].getOwner().equals(temp.getColor()) || temp.getTurns()==0){
					points++;
				}
			}
		}
		if(points>0){
			return true;
		}
		else{
			return false;
		}

	}
	

	/**
	 * setter and getter of Game
	 * @return
	 */

	public void setPlayers(Queue<Player> players) {
		Players = players;
	}
	public Queue<Player> getPlayers(Queue<Player> players) {
		return this.Players;
	}

	/**
	 * To get arraylist of color present initially
	 * @return
	 * @author yash
	 */
	public ArrayList<Paint> getBc() {
		ArrayList<Paint> a=new ArrayList<Paint>();
		for(int i=0;i<arcolor.size();i++){
			a.add(Paint.valueOf(arcolor.get(i)));
		}
		return a;
	}

	/**
	 *
	 * @param bc
	 * set color string in string arraylist
	 * it is used so that when new game is called so i have arraylist of color present initially
	 * @author yash
	 */
	public void setBc(ArrayList<Paint> bc) {
		for(int i=0;i<bc.size();i++){
			this.arcolor.add(bc.get(i).toString());
		}
	}

	/**
	 * to get no. of players in game
	 * @return
	 * @author yash
	 */
	public int getNoofplayer() {
		return noofplayer;
	}

	/**
	 * to set no. of players in game
	 * @param noofplayer
	 * @author yash
	 */
	public void setNoofplayer(int noofplayer) {
		this.noofplayer = noofplayer;
	}
	
	public Queue<Player> getPlayers() {
		return Players;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	
}

class WinnerException extends Exception{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public WinnerException(String s){
		super(s);
	}
}

