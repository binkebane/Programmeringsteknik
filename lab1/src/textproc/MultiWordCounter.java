package textproc;
import java.util.*;


public class MultiWordCounter implements TextProcessor{
	Map<String,Integer> counter;
	
	public MultiWordCounter(String[] landskap) {
		counter = new TreeMap<String,Integer>();
		for (String n : landskap) {
			counter.put(n, 0);
		}
		
	}

	public void process(String w) {
		for(String n : counter.keySet()) {
			if(n.equals(w)) {
				int value=counter.get(n);
				counter.put(n, value+1);
			}
		}
		
	}

	
	public void report() {
		for(String n : counter.keySet()) {
			System.out.println(n + ": " + counter.get(n));
		}
		
		
	}

}
