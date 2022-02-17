package queue_singlelinkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.Queue;


public class testa {

	public static void main(String args[]) {
		FifoQueue<Integer> q1 = new FifoQueue<Integer>();

		

		FifoQueue<Integer> q2 = new FifoQueue<Integer>();
		int nbr = 5;

		
		for (int i=1;i<=nbr;i++) {
			q1.offer(i);
			q2.offer(nbr+i);
		}
		
		int size=q1.size()+q2.size();
		q1.append(q2);
		System.out.println("Storlekinnan:" + size + "Storlekefter:" + q1.size());
		for (int i=1;i<=2*nbr;i++) {
			System.out.println(i==q1.poll());
		}
		




		

	}
}
