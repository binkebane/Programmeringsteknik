package mountain;

import java.util.HashMap;

public class testa {
	
	public static void main(String[] args) {
		HashMap<Side, Point> map = new HashMap<Side, Point>();
		
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,2);
		Side s1=new Side(p1,p2);
		Side s2=new Side(p2,p1);
		map.put(s1, s1.getMidPoint());
		System.out.print(map.containsKey(new Side(p1,p2)));
		
	}

}
