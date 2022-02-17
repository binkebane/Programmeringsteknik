package textproc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
		}
	
	private void createWindow(GeneralWordCounter counter, String title,
			int width, int height) {
			JFrame frame = new JFrame(title);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setPreferredSize(new Dimension(500, 300));
			
			Container pane = frame.getContentPane();
			SortedListModel<Map.Entry<String,Integer>> s = new SortedListModel(counter.getWordList());
			
			JList<Map.Entry<String, Integer>> listView = new JList<Map.Entry<String, Integer>>(s);
			listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane(listView);
			pane.add(scrollPane, BorderLayout.CENTER);
			
			JPanel panel1 = new JPanel();
			ButtonGroup bg = new ButtonGroup();
			JRadioButton b1 = new JRadioButton("Alphabetic", true);
			JRadioButton b2 = new JRadioButton("Frequency");
			bg.add(b1);
			bg.add(b2);
			panel1.add(b1);
			b1.addActionListener(event -> {
				s.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));
			});
			
			panel1.add(b2);
			
			b2.addActionListener(event -> {
				s.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
			});
			pane.add(panel1, BorderLayout.SOUTH);
			
			
			JPanel panel2 = new JPanel();
			JTextField f1=new JTextField(8);
			panel2.add(f1);
			JButton b3 = new JButton("Find");
			b3.addActionListener(event -> {
				String input = f1.getText().trim().toLowerCase();
				if (counter.counter.containsKey(input)) {
					for (int i = 0; i<s.getSize();i++) {
						if (input.equals(s.getElementAt(i).getKey())) {
							listView.ensureIndexIsVisible(i);
							listView.setSelectedIndex(i);
							break;
						}
					}
				}
				
				else {
					JOptionPane.showMessageDialog(panel2, "Word not found!");	
				}
			});

			panel2.add(b3, BorderLayout.CENTER);
			pane.add(panel2, BorderLayout.EAST);
			

			// pane är en behållarkomponent till vilken de övriga komponenterna
			//(listvy, knappar etc.) ska läggas till.
			
			frame.pack();
			frame.setVisible(true);
	}
	
	
}
