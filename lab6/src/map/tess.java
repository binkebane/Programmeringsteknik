package map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

public class tess {
	
	public static void main(String args[]) {
		SimpleHashMap<Integer, Integer> m16 = new SimpleHashMap<Integer, Integer>();
		java.util.Random random = new java.util.Random(123456);
		HashSet<Integer> randNbrs = new HashSet<Integer>();
		for (int i = 0; i < 20; i++) {
			int r = random.nextInt(100);
			m16.put(r, r);
			randNbrs.add(r);
		}
		for (int i : randNbrs) {
			if (m16.get(i).equals(i)) {
				System.out.println("Ja");
			}
			else {
				System.out.println("Nej");
			}
		}
		
		
		
		m16.show();
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE+3);
		
	}
	

}
