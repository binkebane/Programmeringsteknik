package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> ccomparator;
  private boolean addReturn;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root=null;
		size=0;
		ccomparator=(e1,e2)->((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root=null;
		size=0;
		ccomparator=comparator;
	}
	
	

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		
		root = add(root, x);
	    return addReturn;
	}
	private BinaryNode<E> add(BinaryNode<E> n, E x) {
		
		if (n==null) {
			size++;
			addReturn=true;
			return new BinaryNode<E>(x);
		}
		int compRes = ccomparator.compare(x, n.element);
		if (compRes==0) {
			addReturn=false;
			return n;
		}
		else if (compRes < 0) {
			n.left = add(n.left, x);
			return n;
		}
		else {
			n.right = add(n.right, x);
			return n;
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	private int height(BinaryNode<E> n) {
		if (n==null) {
			return 0;
		}
		int lHeight = height(n.left); 
        int rHeight = height(n.right);
		
        if (lHeight > rHeight) {
            return(lHeight + 1); 
        }
        else {
        	return(rHeight + 1); 
        }
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		size=0;
		root=null;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sortedlist = new ArrayList<>();
		toArray(root, sortedlist);
		root = buildTree(sortedlist, 0, sortedlist.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first>last) {
			return null;
		}
		int mid = first + ((last - first) / 2);
		BinaryNode<E> root = new BinaryNode(sorted.get(mid));
		root.left=buildTree(sorted, first, mid-1);
		root.right=buildTree(sorted, mid+1, last);
		return root;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
	public static void main(String args[]) {
		BSTVisualizer v = new BSTVisualizer("BinarySearchTree", 600, 600);
		BinarySearchTree<Integer> bstint = new BinarySearchTree<Integer>();
		BinarySearchTree<String> bststr = new BinarySearchTree<String>((e1, e2)->e1.compareTo(e2));
		bstint.add(1);
		bstint.add(3);
		bstint.add(5);
		bstint.add(7);
		bstint.add(9);
		bstint.add(11);
		bstint.add(13);

		
		bststr.add("F");
		bststr.add("D");
		bststr.add("I");
		bststr.add("K");
		bststr.add("J");
		bststr.add("C");
		bststr.add("A");
		bststr.add("E");
		bststr.add("B");
		bststr.add("L");
		bststr.add("M");
		bststr.add("N");
		bststr.add("O");
		bststr.add("P");
		bststr.add("Q");
		bststr.add("R");
		bststr.add("S");
		bststr.add("T");
		bststr.printTree();
		bststr.rebuild();
		v.drawTree(bststr);
		System.out.print(bststr.height());
		
		
	}
	
}
