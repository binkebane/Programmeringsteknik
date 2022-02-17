package testqueue;
import queue_singlelinkedlist.FifoQueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	private static int nbr = 5;

	@BeforeEach
	void setUp() {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() {
		q1=null;
		q2=null;
	}

	@Test
	void testAppendTwoEmptyQueues() {
		q1.append(q2);
		assertTrue(q1.isEmpty(), "Wrong result from appending two empty queues.");
	}
	@Test
	void testAppendAnEmptyQueue() {
		for (int i=1;i<=nbr;i++) {
			q1.offer(i);
		}
		int size=q1.size();
		q1.append(q2);
		assertEquals(size, q1.size(), "Wrong size after appending an empty queue");
		for (int i=1;i<=nbr;i++) {
		assertEquals(i, q1.poll(), "Wrong order after appending an empty queue");
		}
	}
	@Test
	void testAppendToAnEmptyQueue() {
		for (int i=1;i<=nbr;i++) {
			q2.offer(i);
		}
		int size=q2.size();
		q1.append(q2);
		assertEquals(0, q2.size(), " Appended queue wrong size after appending to an empty queue");
		assertEquals(size, q1.size(), "Wrong size after appending to an empty queue");
		for (int i=1;i<=nbr;i++) {
		assertEquals(i, q1.poll(), "Wrong order after appending to an empty queue");
		}
	}
	
	@Test
	void testAppendTwoQueues() {
		for (int i=1;i<=nbr;i++) {
			q1.offer(i);
			q2.offer(nbr+i);
		}
		int size=q1.size()+q2.size();
		q1.append(q2);
		assertEquals(0, q2.size(), " Appended queue wrong size after appending to an empty queue");
		assertEquals(size, q1.size(), "Wrong size after appending to an empty queue");
		for (int i=1;i<=2*nbr;i++) {
		assertEquals(i, q1.poll(), "Wrong order after appending to an empty queue");
		}
	}
	
	@Test
	void testAppendQueueToItself() {
		for (int i=1;i<=nbr;i++) {
			q1.offer(i);
	}
		assertThrows(IllegalArgumentException.class, () -> q1.append(q1));
}
}
