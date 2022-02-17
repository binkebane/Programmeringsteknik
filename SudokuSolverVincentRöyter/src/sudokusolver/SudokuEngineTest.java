package sudokusolver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuEngineTest {
	SudokuEngine s;

	@BeforeEach
	void setUp() throws Exception {
		s = new SudokuEngine();
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}

	@Test
	void testSudokuEngine() {
		int[][] m = new int[9][9];
		Assert.assertArrayEquals(m, s.getMatrix());
	}

	@Test
	void testSetNumber() {
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, 2, 10));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, 2, -1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(-1, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, -1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(9, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, 9, 1));
		
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				s.setNumber(row, col, 1);
				assertEquals(1, s.getMatrix()[row][col]);
				s.clearNumber(row, col);
			}
		}
	}

	@Test
	void testGetNumber() {
		assertThrows(IllegalArgumentException.class, () -> s.getNumber(1, -1));
		assertThrows(IllegalArgumentException.class, () -> s.getNumber(-1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.getNumber(9, 1));
		assertThrows(IllegalArgumentException.class, () -> s.getNumber(1, 9));

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				s.setNumber(r, c, c);
				assertEquals(c, s.getNumber(r, c));
				s.setNumber(r, c, 0);
			}
		}
	}

	@Test
	void testClearNumber() {
		assertThrows(IllegalArgumentException.class, () -> s.clearNumber(1, -1));
		assertThrows(IllegalArgumentException.class, () -> s.clearNumber(-1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.clearNumber(9, 0));
		assertThrows(IllegalArgumentException.class, () -> s.clearNumber(0, 9));

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				s.setNumber(r, c, c);
				s.clearNumber(r, c);
				assertEquals(0, s.getNumber(r, c));
			}
		}
	}

	@Test
	void testIsValid() {
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, 2, 10));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, 2, -1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(-1, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, -1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(9, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> s.setNumber(1, 9, 1));

		int[][] mat = { { 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		s.setMatrix(mat);
		Assert.assertFalse(s.isValid(5, 0, 1));
		Assert.assertFalse(s.isValid(0, 5, 1));
		Assert.assertFalse(s.isValid(2, 2, 1));

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (r != 0 && c != 0 && (r + c) > 5) {
					Assert.assertTrue(s.isValid(r, c, 1));
				}
			}
		}
	}

	@Test
	void testIsAllValid() {
		Assert.assertTrue(s.isAllValid());

		int[][] mat = { { 1, 0, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 5, 0, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 7, 0, 0, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		s.setMatrix(mat);
		Assert.assertTrue(s.isAllValid());

		s.setNumber(1, 0, 1);
		Assert.assertFalse(s.isAllValid());
		s.setNumber(1, 0, 0);
		s.setNumber(0, 1, 1);
		Assert.assertFalse(s.isAllValid());
		s.setNumber(0, 1, 0);
		s.setNumber(1, 1, 1);
		Assert.assertFalse(s.isAllValid());
	}

	@Test
	void testSolve() {
		int[][] empty = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		s.setMatrix(empty);

		Assert.assertTrue(s.solve());
		
		int[][] solvable = { { 1, 0, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 4, 0, 0, 5, 0, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 7, 0, 0, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		s.setMatrix(solvable);

		Assert.assertTrue(s.solve());

		int[][] unsolvable = { { 2, 0, 0, 9, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 6, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 5, 0, 2, 6, 0, 0, 4, 0, 7 }, { 0, 0, 0, 0, 0, 4, 1, 0, 0 },
				{ 0, 0, 0, 0, 9, 8, 0, 2, 3 }, { 0, 0, 0, 0, 0, 3, 0, 8, 0 }, { 0, 0, 5, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 7, 0, 0, 0, 0, 0, 0 } };
		s.setMatrix(unsolvable);
		Assert.assertFalse(s.solve());

		int[][] illegalmatrix = { { 2, 2, 0, 9, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 6, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 5, 0, 2, 6, 0, 0, 4, 0, 7 }, { 0, 0, 0, 0, 0, 4, 1, 0, 0 },
				{ 0, 0, 0, 0, 9, 8, 0, 2, 3 }, { 0, 0, 0, 0, 0, 3, 0, 8, 0 }, { 0, 0, 5, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 7, 0, 0, 0, 0, 0, 0 } };
		s.setMatrix(illegalmatrix);
		Assert.assertFalse(s.solve());
	}

	@Test
	void testClear() {

		int[][] matrix = { { 1, 0, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 5, 0, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 7, 0, 0, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		s.setMatrix(matrix);
		s.clear();

		int[][] m = new int[9][9];
		Assert.assertArrayEquals(m, s.getMatrix());
	}

	@Test
	void testGetMatrix() {
		int[][] matrix = { { 1, 0, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 5, 0, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 7, 0, 0, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		s.setMatrix(matrix);

		Assert.assertArrayEquals(s.getMatrix(), matrix);
	}

	@Test
	void testSetMatrix() {
		int[][] matrix = { { 1, 0, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 5, 0, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 7, 0, 0, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		s.setMatrix(matrix);

		Assert.assertArrayEquals(s.getMatrix(), matrix);
		
		int[][] matrix10 = { { 1, 10, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 5, 0, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 7, 0, 0, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		int[][] matrixminus = { { 1, -1, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 5, 0, 0, 6, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 7, 0, 0, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		int[][] matrixwrongsize = new int[10][10];
		
		assertThrows(IllegalArgumentException.class, () -> s.setMatrix(matrix10));
		assertThrows(IllegalArgumentException.class, () -> s.setMatrix(matrixminus));
		assertThrows(IllegalArgumentException.class, () -> s.setMatrix(matrixwrongsize));
	}
}