

import javafx.stage.Stage;

class gridgui extends Main{
	int m,n,Nplayer;
	gridgui(int m, int n,int noplayer){
		this.m=m;
		this.n=n;
		Nplayer=noplayer;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		menupaine(primaryStage);
		
	}
}