 package textproc;
 import java.util.*;
import java.util.Map.Entry;
 
public class WordCountComparator implements Comparator<Map.Entry<String,Integer>>{

	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		int compval=o2.getValue()-o1.getValue();
		int compkey=o1.getKey().compareTo(o2.getKey());
		if(compval==0) {
			return compval+compkey;
		}
		return compval;
	}
        	  
          }
