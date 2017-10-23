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
import java.util.ArrayList;
import java.util.StringTokenizer;

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
	//private static Game obj;
	
	private static final long serialVersionUID = 1L;
	private	ArrayList<Player> Players;
	private Matrix matrix;
	
	public ArrayList<Player> getPlayers() {
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
	
	public Game(int m,int n,int k) {
		matrix=new Matrix(m,n);
		System.out.println("Select no. of players");
		matrix.setCounter(k);
		Players=new ArrayList<Player>(k);
		System.out.println("Enter Color of Players");
		for(int i=0;i<k;i++){
			Players.add(i, new Player("A"+i,matrix));
		}	
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader.init(System.in);
		System.out.println("Select grid");
		int x=Reader.nextInt();
		int y=Reader.nextInt();
		int play=Reader.nextInt();
		Game obj=new Game(x,y,play);
		while(!obj.isWinner()){
			for(int i=0;i<obj.Players.size();i++){
				serialize(obj);
				obj.Players.get(i).takeTurn();
				obj.checkplayers();
				obj.show();
				if(obj.Players.size()==1){
					break;
				}
			}
		}
		System.out.println(obj.Players.get(0).getColor());
	}
	
	public boolean isWinner(){
		if (Players.size()==1){
			return true;
		}
		return false;
	}

	public void show(){
		for(int j=0;j<matrix.getM();j++){
			for(int k=0;k<matrix.getN();k++){
				System.out.print(matrix.getBoard()[j][k].getColor().charAt(0)+"("+matrix.getBoard()[j][k].getOrbs()+")" +" ");
			}
			System.out.println();
		}
	}
	
	
	public void checkplayers() {
		// TODO Auto-generated method stub
		ArrayList<Player> jugaad=new ArrayList<Player>();
		for(int i=0;i<Players.size();i++){
			if(Players.get(i).isActive()){
				jugaad.add(Players.get(i));
			}
		}
		Players=jugaad;
	}

}