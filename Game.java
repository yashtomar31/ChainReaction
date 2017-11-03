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

import javafx.scene.layout.GridPane;
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
	GridPane g;
	void setgridpane(GridPane g){
		this.g=g;
	}
	public Queue<Player> getPlayers() {
		return Players;
	}

	public Matrix getMatrix() {
		return matrix;
	}

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
	
	public static Game deserialize() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in=null;
		Game obj;
		try{
			in=new ObjectInputStream(new FileInputStream("out.txt"));
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
	
	public void addplayer(Color c){
		Players.add(new Player(c,matrix));
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
				System.out.print(matrix.getBoard()[j][k].getOwner()+"("+matrix.getBoard()[j][k].getOrbs()+")" +" ");
			}
			System.out.println();
		}
	}
	
	
	public void checkplayers() {
		Queue<Player> jugaad=new LinkedList<Player>();
		int loop=Players.size();
		for(int i=0;i<loop;i++){
			//System.out.println("size "+Players.size());
			Player temp=Players.remove();
			if(isActive(temp)){
				//System.out.println("in loop "+temp.getColor()+" "+i);
				jugaad.add(temp);
			}
		}
		Players=jugaad;
//		System.out.println("Start no. of colors");
//		for (Player element : jugaad) {
//			  System.out.println(element.getColor());
//			}
//		System.out.println("end no. of colors");
	}
	boolean isActive(Player temp){
		int points=0;
		for (int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				if(this.getMatrix().getBoard()[i][j].getOwner().equals(temp.getColor())){
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