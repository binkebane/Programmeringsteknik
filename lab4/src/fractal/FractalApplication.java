package fractal;

import koch.Koch;
import mountain.ImprovedMountain;
import mountain.MountainFractal;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[3];
		fractals[2] = new Koch(300);
		fractals[1] = new MountainFractal(new Point(30,570),new Point(340,20),new Point(570,440),20 );
		fractals[0] = new ImprovedMountain(new Point(30,570),new Point(340,20),new Point(570,440),50 );
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
