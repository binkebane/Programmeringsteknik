package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor{
	Set<String> stopwords;
	Map<String,Integer> counter;
	
	public GeneralWordCounter(Set<String> stopwords) {
		this.stopwords=stopwords;
		counter = new TreeMap<String,Integer>();
	}


	public void process(String w) {
		int value=0;
		if(stopwords.contains(w)==false) {
			try{
				value=counter.get(w);
			}
			catch(java.lang.NullPointerException e) {

			}
			counter.put(w, value+1);
		}
		
	}


	public void report() {
//		for(String n : counter.keySet()) {
//			if(counter.get(n)>=200) {
//			System.out.println(n + ": " + counter.get(n));
//			}
//		}
		Set<Map.Entry<String, Integer>> wordSet = counter.entrySet();
	    List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
	    
	    wordList.sort(new WordCountComparator());
	    
	    for(int i=0;i<50;i++) {
	    System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
	    }
		
	}
	
	public List<Map.Entry<String,Integer>> getWordList() {
		Set<Map.Entry<String, Integer>> wordSet = counter.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		return wordList;
		}

}
