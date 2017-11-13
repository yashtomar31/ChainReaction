package v1.oo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

class Reader {			//reader class for to take input in faster manner
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for of if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}


public class Game implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private	Queue<Player> Players;
	private Matrix matrix;
//	private GridPane g;
//
//	public void setgridpane(GridPane g){
//		this.g=g;
//	}
//
//	public GridPane getGridPane(){
//		return g;
//	}
//
	public Queue<Player> getPlayers() {
		return Players;
	}

	public Matrix getMatrix() {
		return matrix;
	}

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
	public Game(int m,int n,int k) {
		matrix=new Matrix(m,n);
		matrix.setCounter(k);
		Players=new LinkedList<Player>();
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

	public void comeback(){
		for(int i=0;i<this.matrix.getM();i++){
			for(int j=0;j<this.matrix.getN();j++){
				this.matrix.getBoard()[i][j].setOwner(Color.web(this.matrix.getBoard()[i][j].getOwnstr()));
			}
		}
	}
	
	public void addplayer(Color c){
		Players.add(new Player(c,matrix));
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		Game obj=new Game(9,6,2);
		serialize(obj);
		obj.addplayer(Color.BLUE);
		obj.addplayer(Color.RED);
		obj.getPlayers().peek().takeTurn(1, 2);
		serialize(obj);
		Game dese=deserialize();
		if( dese.getMatrix().getM()==obj.getMatrix().getM()){
			System.out.println("yo");
		}
	}

//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		Reader.init(System.in);
//		System.out.println("Select grid");
//		int x=Reader.nextInt();
//		int y=Reader.nextInt();
//		int play=Reader.nextInt();
//		Game obj=new Game(x,y,play);
//		for(int i=0;i<x;i++){
//		obj.addplayer(Color.RED);
//		obj.addplayer(Color.BLUE);
//		while(!obj.isWinner()){
//			for(int i=0;i<obj.Players.size();i++){
//				//serialize(obj);
//				boolean flag=true;
//				while(flag){
//				int a=Reader.nextInt();
//				int b=Reader.nextInt();
//				flag=obj.Players.get(i).takeTurn(a,b);
//				}
//				obj.checkplayers();
//				obj.show();
//				if(obj.Players.size()==1){
//					break;
//				}
//			}
//		}
//		System.out.println(obj.Players.get(0).getColor());
//	}
//
	public boolean isWinner(){
		if (Players.size()==1){
			return true;
		}
		return false;
	}

	public void show(){
		for(int j=0;j<matrix.getM();j++){
			for(int k=0;k<matrix.getN();k++){
				System.out.print(matrix.getBoard()[j][k].getOwnstr()+"("+matrix.getBoard()[j][k].getOrbs()+")" +" ");
			}
			System.out.println();
		}
	}


	public void checkplayers() {
		Queue<Player> jugaad=new LinkedList<Player>();
		int loop=Players.size();
		System.out.println("size"+" "+loop);
		for(int i=0;i<loop;i++){
			//System.out.println("size "+Players.size());
			Player temp=Players.remove();
			System.out.println(i+" "+temp.getColor());
			if(isActive(temp)){
				System.out.println(i+" "+temp.getColor());
				jugaad.add(temp);
			}
		}
		Players=jugaad;
	}
	
	public void comeback2(){
		Queue<Player> jugaad=new LinkedList<Player>();
		int loop=Players.size();
		System.out.println("size"+" "+loop);
		for(int i=0;i<loop;i++){
			//System.out.println("size "+Players.size());
			Player temp=Players.remove();
			System.out.println(i+" "+temp.getColor());
			temp.setColor(Color.web(temp.getColstr()));
			jugaad.add(temp);
		}
		Players=jugaad;
	}
	
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

}
