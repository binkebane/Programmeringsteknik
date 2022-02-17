package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode(e);
		if (size==0) {
			last = n;
			last.next=n;
		}
		if (size!=0) {
		n.next=last.next;
		last.next=n;
		last=n;
		}
		size=size+1;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size==0) {
			return null;
		}
		else {
			return last.next.element;	
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size==0) {
			last=null;
			return null;
		}
		else if (last.next==last) {
			QueueNode<E> n = last;
			last=null;
			size=size-1;
			return n.element;
		}
		else {
			QueueNode<E> first = last.next;
			last.next=first.next;
			size=size-1;
			return first.element;
		}

	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if (this==q) {
			throw new IllegalArgumentException();
		}
		else if (q.size()==0) {
			
		}
		else if (this.size()==0) {
			QueueNode<E> n=q.last;
			last=n;
			size=q.size;
			q.last=null;
			q.size=0;
		}
			
		else {
		QueueNode<E> q2first=q.last.next;
		QueueNode<E> q2last=q.last;
		QueueNode<E> q1first = last.next;
		QueueNode<E> q1last = last;
		last.next=q2first;
		q2last.next=q1first;
		last=q2last;
		size=size+q.size;
		q.size=0;
		q.last=null;
		
		
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator () {
		return new QueueIterator();
		}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private QueueNode<E> first;
		private int i=0;
		
		private QueueIterator() {
			if (size==0) {
				pos=null;
			}
			else {
				pos=last;
				first=last.next;
				
				
			}
			
		}
		public boolean hasNext() {
			
			if (pos==null) {
				return false;
			}
			else if (i>1 && pos==last) {
				return false;
			}
			else {
				return true;
			}
			
		}
		public E next() {
			
			i++;
			QueueNode<E> nextpos;
			try {
			nextpos=pos.next;
			}
			catch (NullPointerException e) {
				nextpos=null;
			}

			if (nextpos==first && i>1) {
				throw new NoSuchElementException();
			}
			else if (nextpos==null) {
				throw new NoSuchElementException();
			}
			else {
				if (nextpos==first) {
					pos=nextpos;
					return first.element;
					
				}

				else {
					pos=nextpos;

					return nextpos.element;
				}
				
			}
			}
		
		}

}
