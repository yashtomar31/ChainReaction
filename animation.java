package v1.oo;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

class animation{
	Group a;
	RotateTransition rt;

	animation(Color c,int no){
		this.a=new Group();
		for(int i=0;i<no;i++){
			orb o=new orb(c);
			a.getChildren().add(o.getS());
		}
		this.rt=new RotateTransition(Duration.millis(2000), a);
	}
	void setcoordinates(){
		int size=a.getChildren().size();
		if(size==2){
			a.getChildren().get(1).setTranslateX(10);
			a.getChildren().get(1).setTranslateY(10);
			this.rt=new RotateTransition(Duration.millis(2000), a);
		}
		if(size==3){
			a.getChildren().get(0).setTranslateX(0);
			a.getChildren().get(0).setTranslateY(8);
			a.getChildren().get(1).setTranslateX(+6.666);
			a.getChildren().get(1).setTranslateY(-3.3333);
			a.getChildren().get(2).setTranslateX(-6.666);
			a.getChildren().get(2).setTranslateY(-3.3333);

		}
	}
	void addanimation(){
		 rt.setFromAngle(0);
		 rt.setCycleCount(RotateTransition.INDEFINITE);
		 rt.setInterpolator(Interpolator.LINEAR);
		 rt.setByAngle(360);
	     rt.play();
	 }



}
class orb{

	private Sphere s;

	orb(Color c){
		final PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseColor(c);
		 setS(new Sphere());
		 getS().setRadius(10);
		 getS().setMaterial(mat);
	}

	public Sphere getS() {
		return s;
	}
	public void setS(Sphere s) {
		this.s = s;
	}
}