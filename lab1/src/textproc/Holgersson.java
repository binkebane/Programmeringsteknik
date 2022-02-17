package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		List<TextProcessor> list = new ArrayList<TextProcessor>();
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
		

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for (TextProcessor n : list) {
				n.process(word);
			}
			
		}

		
		for (TextProcessor n : list) {
		n.report();
		}
		
		MultiWordCounter m = new MultiWordCounter(REGIONS);
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
				m.process(word);
		}

		
			m.report();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		
		scan.close();
		GeneralWordCounter g = new GeneralWordCounter(stopwords);
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			g.process(word);
		}
		s.close();
		g.report();
		
		long t1 = System.nanoTime();
		
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}