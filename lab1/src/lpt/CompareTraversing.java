package lpt;

import java.util.*;

public class CompareTraversing {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Antal tal: ");
		int n = scan.nextInt();
		
		List<Integer> list = new LinkedList<Integer>();	
//		List<Integer> list = new ArrayList<Integer>();
		fillRandom(list, n);
	
		// Summera talen - iterator
		long t0 = System.nanoTime();
		int sum = 0;
		for (int nbr : list) {
			sum += nbr;
		}
		long t1 = System.nanoTime();
		System.out.println("Exekveringstid iterator: " +
		                    Math.round((t1 - t0) / 1000000.0) + " ms");

		// Summera talen - for-sats med get(i)
		t0 = System.nanoTime();
		sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		t1 = System.nanoTime();
		System.out.println("Exekveringstid for-sats med get(i): " +
                           Math.round((t1 - t0) / 1000000.0) + " ms");
	}
	
	public static void fillRandom(List<Integer> list, int n) {
		for (int i = 0; i < n; i++) {
			list.add((int) (Integer.MAX_VALUE * Math.random() + 1));
		}
	}
}