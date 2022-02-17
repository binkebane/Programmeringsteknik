package sudokusolver;

import sudokusolver.SudokuEngine;
import sudokusolver.SudokuWindow;

public class test {
	public static void main(String[] args) {
		for (int i = 0; i < 17; i++) {
			emilAxel(i);
		}
	}
	
	public static void emilAxel(int n) {
		if (n % 5 == 0 && n % 3 == 0) {
			System.out.println(n + "EmilAxel");
			return;
		}
		
		if (n % 5 == 0) {
			System.out.println(n + "Emil");
			return;
		}
		
		if (n % 3 == 0) {
			System.out.println(n + "Axel");
			return;
		}
			
	 
	}
}
