package bst;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BSTtest {
	BinarySearchTree<Integer> bstint;
	BinarySearchTree<String> bststr;

	@BeforeEach
	void setUp() throws Exception {
		bstint = new BinarySearchTree<Integer>();
		bststr = new BinarySearchTree<String>((e1, e2)->e1.compareTo(e2));
	}

	@AfterEach
	void tearDown() throws Exception {
		bstint.clear();
		bststr.clear();
	}

	@Test
	void testAdd() {
		bstint.add(10);
		bstint.add(5);
		bstint.add(12);
		bstint.add(13);
		bstint.add(11);
		bstint.add(4);
		bstint.add(6);
		assertEquals(7, bstint.size());
		assertEquals(3, bstint.height());
		
		bststr.add("F");
		bststr.add("D");
		bststr.add("I");
		bststr.add("K");
		bststr.add("J");
		bststr.add("C");
		bststr.add("A");
		bststr.add("E");
		assertEquals(8, bststr.size());
		assertEquals(4, bststr.height());
		
		Assert.assertTrue(bstint.add(7));
		Assert.assertFalse(bstint.add(7));
		assertEquals(8, bstint.size());
		assertEquals(4, bstint.height());

		Assert.assertTrue(bststr.add("B"));
		Assert.assertFalse(bststr.add("B"));
		assertEquals(9, bststr.size());
		assertEquals(5, bststr.height());
		
	}
	
	@Test
	void testHeight() {
		assertEquals(0, bstint.height());
		assertEquals(0, bststr.height());
	}
	
	@Test
	void testSize() {
		assertEquals(0, bstint.size());
		assertEquals(0, bststr.size());
	}
	
	@Test
	void testClear() {
		bstint.add(10);
		bstint.add(5);
		bstint.add(12);
		bstint.add(13);
		bstint.add(11);
		bstint.add(4);
		bstint.add(6);
		
		bststr.add("F");
		bststr.add("D");
		bststr.add("I");
		bststr.add("K");
		bststr.add("J");
		bststr.add("C");
		bststr.add("A");
		bststr.add("E");
		
		bstint.clear();
		bststr.clear();
		
		assertEquals(0, bstint.size());
		assertEquals(0, bststr.size());
		
		assertEquals(0, bstint.height());
		assertEquals(0, bststr.height());
		
	}
}
