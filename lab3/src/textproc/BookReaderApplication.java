package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JList;

public class BookReaderApplication {
	
	public static void main(String args[]) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); 
		
		GeneralWordCounter g = new GeneralWordCounter(stopwords);
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			g.process(word);
		}
		s.close();
		scan.close();
		
		BookReaderController c = new BookReaderController(g);
		
		
		
		
	}

}
