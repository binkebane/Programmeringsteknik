package sudokusolver;

public class SudokuEngine implements SudokuSolver {
	private int[][] sudokuboard;

	public SudokuEngine() {
		sudokuboard = new int[9][9];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		if (r < 0 || r > 8 || c < 0 || c > 8 || nbr < 0 || nbr > 9) {
			throw new IllegalArgumentException(
					"r, c och nbr måste vara heltal mellan 0 och 8, nbr måste vara ett heltal mellan 0 och 9.");
		}
			sudokuboard[r][c] = nbr;
	}

	@Override
	public int getNumber(int r, int c) {
		if (r < 0 || r > 8 || c < 0 || c > 8) {
			throw new IllegalArgumentException("r och c måste vara heltal mellan 0 och 8.");
		} else {
			return sudokuboard[r][c];
		}
	}

	@Override
	public void clearNumber(int r, int c) {
		if (r < 0 || r > 8 || c < 0 || c > 8) {
			throw new IllegalArgumentException("r och c måste vara heltal mellan 0 och 8.");
		} else {
			sudokuboard[r][c] = 0;
		}

	}

	@Override
	public boolean isValid(int r, int c, int nbr) {
		if (r < 0 || r > 8 || c < 0 || c > 8 || nbr < 0 || nbr > 9) {
			throw new IllegalArgumentException(
					"r, c och nbr måste vara heltal mellan 0 och 8, nbr måste vara ett heltal mellan 0 och 9.");
		}
		//Söker i 3x3-submatrisen som cellen r,c befinner sig i och kollar om nbr redan finns.
		//Returnerar false om nbr redan finns.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (r < 3 && c < 3) {
					if (sudokuboard[i][j] == nbr && nbr != 0 && r != i && c != j) {
						return false;
					}
				}

				if (r < 3 && c > 2 && c < 6) {
					if (sudokuboard[i][3 + j] == nbr && nbr != 0 && r != i && c !=3 + j) {
						return false;
					}
				}

				if (r < 3 && c > 5) {
					if (sudokuboard[i][6 + j] == nbr && nbr != 0 && r != i && c !=6 + j) {
						return false;
					}
				}

				if (r > 2 && r < 6 && c < 3) {
					if (sudokuboard[3 + i][j] == nbr && nbr != 0 && r != i+3 && c != j) {
						return false;
					}
				}

				if (r > 2 && r < 6 && c > 2 && c < 6) {
					if (sudokuboard[3 + i][3 + j] == nbr && nbr != 0 && r != i+3 && c != j+3) {
						return false;
					}
				}

				if (r > 2 && r < 6 && c > 5) {
					if (sudokuboard[3 + i][6 + j] == nbr && nbr != 0 && r != i+3 && c != j+6) {
						return false;
					}
				}
				if (r > 5 && c < 3) {
					if (sudokuboard[6 + i][j] == nbr && nbr != 0 && r != i+6 && c != j) {
						return false;
					}
				}
				if (r > 5 && c > 2 && c < 6) {
					if (sudokuboard[6 + i][3 + j] == nbr && nbr != 0 && r != i+6 && c != j+3) {
						return false;
					}
				}
				if (r > 5 && c > 5) {
					if (sudokuboard[6 + i][6 + j] == nbr && nbr != 0 && r != i+6 && c != j+6) {
						return false;
					}
				}

			}
		}
		//Kollar om nbr redan finns på samma rad eller i samma kolonn.
		for (int i = 0; i < 9; i++) {
			if (sudokuboard[i][c] == nbr && nbr != 0 && i !=r) {
				return false;
			} else if (sudokuboard[r][i] == nbr && nbr != 0 && i != c) {
				return false;
			}

		}
		return true;
	}

	@Override
	public boolean isAllValid() {
		//Går igenom hela matrisen och kollar om varje insättning är korrekt enl. reglerna.
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int n = sudokuboard[i][j];
				sudokuboard[i][j] = 0;
				if (isValid(i, j, n) == false) {
					sudokuboard[i][j] = n;
					return false;
				} else {
					sudokuboard[i][j] = n;
				}
			}
		}
		return true;
	}

	@Override
	//publik metod för att initiera lösning av sudokut om alla insättningar är korrekta.
	public boolean solve() {
		if (isAllValid()) {
			return solve(0, 0);
		}
		else {
			return false;
		}
		
	}

	//Privat hjälpmetod som solve() kallar på för att börja från första cellen
	//där r=0 och c=0.
	private boolean solve(int r, int c) {
		//När r=8 och c=9 har metoden rekursivt gått igenom alla celler.
		//Om alla insättnigarna är korrekta är sudokut löst och true returneras.
		if (r == 8 && c == 9 && isAllValid()) {
			return true;
		}
		//Om c=9 har alla celler på en given rad fyllts i och i så fall
		//går man man till nästa rad och börjar på första cellen på raden.
		if (c == 9) {
			r++;
			c = 0;
		}
		//Om cellen redan har ett värde som är giltligt går man till nästa cell.
		if (sudokuboard[r][c] > 0 && isValid(r,c,sudokuboard[r][c])) {
			return solve(r, c + 1);
		}
		//Testar stoppa ett nummer från 1 till 9 i cellen.
		for (int n = 1; n <= 9; n++) {
			//Om numret n är en korrekt insättning stoppas det in och sedan sker ett rekursivt anrop
			//för att kolla om följande celler är lösbara med den insättningen.
			if (isValid(r, c, n)) {
				sudokuboard[r][c] = n;
				//Är följande celler lösbara med insättningen n, så returneras true.
				if (solve(r, c + 1)) {
					return true;
					//Annars sätter man cellen till 0 (tom cell).
				} else {
					sudokuboard[r][c] = 0;
				}
			}
			//Nästa nummer n i for-loopen kommer att testas.
		}
		//Om det inte går att hitta korrekta insättningar i det givna sudokut
		//så är det inte lösbart och false returneras.
		return false;

	}

	@Override
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudokuboard[i][j] = 0;
			}
		}
	}

	@Override
	public int[][] getMatrix() {
		int[][] nbrs = new int[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				nbrs[r][c] = sudokuboard[r][c];
			}
		}
		return nbrs;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		for (int r = 0; r < nbrs.length; r++) {
			for (int c = 0; c < nbrs.length; c++) {
				if (r < 0 || r > 8 || c < 0 || c > 8 || nbrs[r][c] < 0 || nbrs[r][c] > 9) {
					throw new IllegalArgumentException(
							"r, c och nbr måste vara heltal mellan 0 och 8, nbr måste vara ett heltal mellan 0 och 9.");
				}
				sudokuboard[r][c] = nbrs[r][c];
			}
		}

	}
		

}
