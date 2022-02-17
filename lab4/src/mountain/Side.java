package mountain;

public class Side {
	private Point p1;
	private Point p2;

	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point getFirstPoint() {
		return p1;
	}

	public Point getSecondPoint() {
		return p2;
	}

	public Point getMidPoint() {
		Point pm = new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
		return pm;
	}
	

	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	
	@Override
	public boolean equals(Object s) {
		Side a=(Side) s;
		if (a.getFirstPoint().equals(p1) && a.getSecondPoint().equals(p2)) {
			return true;
		}
		else if (a.getSecondPoint().equals(p1) && a.getFirstPoint().equals(p2)) {
			return true;
		}
		else {
			return false;
		}


	}
	

}
