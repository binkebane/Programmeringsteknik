package mountain;

import java.util.HashMap;

import fractal.*;

public class ImprovedMountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	HashMap<Side, Point> map;

	public ImprovedMountain(Point a, Point b, Point c, double dev) {
		this.a=a;
		this.b=b;
		this.c=c;

		this.dev = dev;
		map = new HashMap<Side, Point>();

	}

	@Override
	public String getTitle() {
		return "ImprovedMountain";
	}

	@Override
	public void draw(TurtleGraphics t) {
		fractalLine(t, order,a,b,c,dev);
		

	}
	private void fractalLine(TurtleGraphics t, int order, Point p1, Point p2, Point p3, double dev) {


		if (order==0) {

			t.moveTo(p1.getX(), p1.getY());
			t.penDown();
			t.forwardTo(p2.getX(), p2.getY());
			t.forwardTo(p3.getX(), p3.getY());
			t.forwardTo(p1.getX(), p1.getY());

			


		}
		else {
			double devi=dev/2;
			System.out.println(devi);
			Side s1=new Side(p1,p2);
			Side s2=new Side(p2,p3);
			Side s3=new Side(p3,p1);
			
			Point a=p1;
			Point b=p2;
			Point ab=s1.getMidPoint();
			Point c=p3;
			Point bc=s2.getMidPoint();
			Point ca=s3.getMidPoint();

			
			if (map.containsKey(s1)==false) {
				System.out.println("AB");
				ab = new Point((p1.getX()+p2.getX())/2,(int) Math.round((p1.getY()+p2.getY())/2+randFunc(devi)));
				map.put(s1, ab);
			}
			else {
				ab=map.remove(s1);
			}

			if (map.containsKey(s2)==false) {
				System.out.println("BC");
				bc = new Point((p2.getX()+p3.getX())/2,(int) Math.round((p2.getY()+p3.getY())/2+randFunc(devi)));
				map.put(s2, bc);
			}
			else {
				bc=map.remove(s2);
			}

			if (map.containsKey(s3)==false) {
				System.out.println("CA");
				ca = new Point((p3.getX()+p1.getX())/2,(int) Math.round((p3.getY()+p1.getY())/2+randFunc(devi)));
				map.put(s3, ca);
			}
			else {
				ca=map.remove(s3);
			}

			
			fractalLine(t, order-1,a,ab,ca,devi);
			fractalLine(t, order-1,ab,b,bc,devi);
			fractalLine(t, order-1,ca,bc,c,devi);
			fractalLine(t, order-1,ab,bc,ca,devi);
			


			
		}
	}
	

	private static double randFunc(double dev) {
		double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
		if (Math.random() < 0.5) {
			t = -t;
		}
		return t;
	}

	
	

}
