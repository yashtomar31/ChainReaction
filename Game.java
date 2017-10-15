import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


public class Game {
	
	private	ArrayList<Player> Players;
	private Matrix matrix;
	
	public Game() throws IOException{
		System.out.println("Select grid");
		int m=Reader.nextInt();
		int n=Reader.nextInt();
		matrix=new Matrix(m,n);
		System.out.println("Select no. of players");
		int k=Reader.nextInt();
		matrix.setCounter(k);
		Players=new ArrayList<Player>(k);
		System.out.println("Enter Color of Players");
		for(int i=0;i<k;i++){
			Players.add(i, new Player(Reader.next(),matrix));
		}	
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader.init(System.in);
		Game obj=new Game();
		obj.show();
		obj.start();
	}
	
	private boolean isWinner(){
		if (Players.size()==1){
			return true;
		}
		return false;
	}

	private void show(){
		for(int j=0;j<matrix.getM();j++){
			for(int k=0;k<matrix.getN();k++){
				System.out.print(matrix.getBoard()[j][k].getColor().charAt(0) +" ");
			}
			System.out.println();
		}
	}
	
	private void chance(){
		for(int i=0;i<Players.size();i++){
			Players.get(i).takeTurn();
			checkplayers();
			show();
		}
	}
	
	private void checkplayers() {
		// TODO Auto-generated method stub
		ArrayList<Player> jugaad=new ArrayList<Player>();
		for(int i=0;i<Players.size();i++){
			if(Players.get(i).isActive()){
				jugaad.add(Players.get(i));
			}
		}
		Players=jugaad;
	}

	private void start() {
		// TODO Auto-generated method stub
		while(!isWinner()){
			chance();
		}
		
	}

}