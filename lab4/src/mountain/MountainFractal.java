package mountain;
import java.util.HashMap;

import fractal.*;

public class MountainFractal extends Fractal{
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	HashMap<Side, Point> map;
	
	public MountainFractal(Point a, Point b, Point c, double dev) {
		this.a=a;
		this.b=b;
		this.c=c;
		this.dev=dev;
		map=new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		return "Mountainfractal";
	}

	@Override
	public void draw(TurtleGraphics t) {
		fractalLine(t, order,a,b,c,dev);
	}
	private void fractalLine(TurtleGraphics t, int order, Point p1, Point p2, Point p3, double dev) {
		if (order==0) {
			t.moveTo(p1.getX(), p1.getY());
			t.forwardTo(p2.getX(), p2.getY());
			t.forwardTo(p3.getX(), p3.getY());
			t.forwardTo(p1.getX(), p1.getY());
		}
		else {
			Point midab = new Point((p1.getX()+(p2.getX()-p1.getX())/2),(int) ((p1.getY()+(p2.getY()-p1.getY())/2)+randFunc(dev/2)));
			Point midbc = new Point((p2.getX()+(p3.getX()-p2.getX())/2),(int) ((p2.getY()+(p3.getY()-p2.getY())/2)+randFunc(dev/2)));
			Point midca = new Point((p3.getX()+(p1.getX()-p3.getX())/2),(int) ((p3.getY()+(p1.getY()-p3.getY())/2)+randFunc(dev/2)));
			fractalLine(t, order-1,midab,midbc,midca,dev/2);
			fractalLine(t, order-1,p1,midab,midca,dev/2);
			fractalLine(t, order-1,midab,p2,midbc,dev/2);
			fractalLine(t, order-1,midca,midbc,p3,dev/2);
			
			
		}
	}
	
	public static double randFunc(double dev) {
		double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
		if (Math.random() < 0.5) {
		t = -t;
		}
		return t;
		}

}
