package sudokusolver;

public class SearchLinkedList<E> {
	private ListNode<E> first;

	public SearchLinkedList() {
		first = null;
	}

	public E find(E x) {
		if (first==null) {
			return null;
		}
		ListNode<E> n;
		ListNode<E> temp = first;
		if (temp.data.equals(x)) {
			++temp.count;
			return x;
		}
		while (temp.next != null) {
			if (temp.next.data.equals(x)) {
				++temp.next.count;
				if (temp.count<=temp.next.count) {
					n=temp.next;
					temp.next=n.next;
					moveNode(n);
					return x;
				}
				else {
					return x;
				}
			}
			temp=temp.next;
			
		}
		return null;
	}
	
	private void moveNode(ListNode<E> n) {
		ListNode<E> nod;
		ListNode<E> temp = first;
        if (temp.count <= n.count){
            n.next=temp;
            first=n;
            return;
        }
		while (temp.next != null && temp.next.count > n.count) {
			temp=temp.next;
		}
		nod=temp.next;
		temp.next=n;
		n.next=nod;
	}

	/* Nästlad klass. Representerar en nod som innehåller ett element av typen E. */
	private static class ListNode<E> {
		private E data;
		private int count;
		private ListNode<E> next;

		private ListNode(E e) {
			data = e;
			count = 0;
			next = null;
		}
	}
	public void add(E e) {
		ListNode<E> n = new ListNode<E>(e);
		if (first == null) {
			first = n;
		} else {
			ListNode<E> p = first;
			while (p.next != null) {
				p = p.next;
			}
			p.next = n;
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		ListNode<E> n = first;
		while (n != null) {
			s.append(n.data.toString());
			s.append(" (" + n.count + ")");
			if (n.next != null) {
				s.append(", ");
			}
			n = n.next;
		}
		s.append("]");
		return s.toString();		
	}
	
	public static void main(String args[]) {
		SearchLinkedList<Integer> list = new SearchLinkedList<Integer>();
		list.add(1000); list.add(2000); list.add(3000); list.add(4000); list.add(5000);
		list.find(4000);
		list.find(5000);
		list.find(5000);
		list.find(3000);
		list.find(1000);
		System.out.println(list);
	}
}
