package sudokusolver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class SudokuWindow {
	MatrixButton[][] buttonMatrix;

	public SudokuWindow(SudokuEngine e) {
		buttonMatrix = new MatrixButton[9][9];
		SwingUtilities.invokeLater(() -> createWindow(e, "SudokuSolver", 100, 300));
	}

	private void createWindow(SudokuEngine e, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(430, 250);

		Container pane = frame.getContentPane();
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return ".txt Documents (*.txt)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(".txt");
				}
			}
		});

		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		JPanel gamepanel = new JPanel();
		gamepanel.setLayout(new GridLayout(9, 9));
		gamepanel.setBorder(BorderFactory.createMatteBorder(3, 3, 1, 1, Color.BLACK));
		ButtonMatrix bmat=new ButtonMatrix();
		for (int r = 1; r < 10; r++) {
			for (int c = 1; c < 10; c++) {
				MatrixButton btn = new MatrixButton("", r-1, c-1);
				bmat.insert(btn, r-1, c-1);
				btn.setFont(new Font("Arial", Font.PLAIN, 35));
				btn.setPreferredSize(new Dimension(40, 40));
				btn.setBackground(Color.white);
				btn.setOpaque(true);
				btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				if (r % 3 == 0) {
					btn.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
				}

				if (c % 3 == 0) {
					btn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
				}

				if (c % 3 == 0 && r % 3 == 0) {
					btn.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));

				}
				
				btn.addActionListener(event -> {
					for (int row=0;row<9;row++) {
						for (int col=0;col<9;col++) {
							if (btn.getRow()==row) {
								bmat.get(row, col).setBackground(new Color(205, 242, 255));
							}
							if (btn.getCol()==col) {
								bmat.get(row, col).setBackground(new Color(205, 242, 255));
							}
							pane.repaint();
						}
					}
					
					btn.setBackground(new Color(100,210,255));
					JButton[] buttonlist = new JButton[9];
					JDialog window = new JDialog();
					
					window.setVisible(true);
					window.setBounds(frame.getX()+430, frame.getY()+130, 250, 227);
					JPanel optionpanel = new JPanel(new BorderLayout());
					JPanel numberpanel = new JPanel(new GridLayout(3, 3));
					for (int j=0;j<gamepanel.getComponents().length;j++) {
						gamepanel.getComponent(j).setEnabled(false);
					}
					
					
					
					for (int i = 1;i<10;i++) {
						
						JButton nbr = new JButton(String.valueOf(i));
						buttonlist[i-1]=nbr;
						nbr.setPreferredSize(new Dimension(40, 40));
						nbr.addActionListener(event2 -> {
							String str=nbr.getText();
							
							try {
								
								if (str.equals("0")) {
									btn.setText("");
									e.clearNumber(btn.getRow(), btn.getCol());
								}

								else if (e.isValid(btn.getRow(), btn.getCol(), Integer.parseInt(str))) {
									btn.setText(str);
									e.setNumber(btn.getRow(), btn.getCol(), Integer.parseInt(str));
									for (int j=0;j<gamepanel.getComponents().length;j++) {
										gamepanel.getComponent(j).setEnabled(true);
									}
									for (int row=0;row<9;row++) {
										for (int col=0;col<9;col++) {
											bmat.get(row, col).setBackground(Color.white);
											pane.repaint();
										}
										}
									
									window.dispose();
									
								}

								else if (e.isValid(btn.getRow(), btn.getCol(), Integer.parseInt(str)) == false) {
									JOptionPane.showMessageDialog(optionpanel, "Invalid Input!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
								}
							} catch (IllegalArgumentException exeption) {
								JOptionPane.showMessageDialog(optionpanel, "Invalid Input!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
							}
							
							
	
						});
						
						numberpanel.add(nbr);
					}
					
					JButton cleartile = new JButton("Clear Tile");
					cleartile.setPreferredSize(new Dimension(40, 40));
					cleartile.addActionListener(event2 -> {
						btn.setText("");
						e.clearNumber(btn.getRow(), btn.getCol());
						for (int j=0;j<gamepanel.getComponents().length;j++) {
							gamepanel.getComponent(j).setEnabled(true);
						}
						for (int row=0;row<9;row++) {
							for (int col=0;col<9;col++) {
								bmat.get(row, col).setBackground(Color.white);
								pane.repaint();
							}
							}
						window.dispose();
					});	
					
					JButton cancelbtn = new JButton("Cancel");
					cancelbtn.setPreferredSize(new Dimension(40, 40));
					cancelbtn.addActionListener(event2 -> {
						for (int j=0;j<gamepanel.getComponents().length;j++) {
							gamepanel.getComponent(j).setEnabled(true);
						}
						for (int row=0;row<9;row++) {
							for (int col=0;col<9;col++) {
								bmat.get(row, col).setBackground(Color.white);
								pane.repaint();
							}
							}
						window.dispose();
					});	
					
					optionpanel.add(numberpanel,BorderLayout.NORTH);
					optionpanel.add(cleartile,BorderLayout.CENTER);
					optionpanel.add(cancelbtn,BorderLayout.SOUTH);
					window.add(optionpanel,BorderLayout.NORTH);

				});
				

				
				
				
				buttonMatrix[r-1][c-1] = btn;
				String str = String.valueOf(e.getMatrix()[r-1][c-1]);
				if (e.getMatrix()[r-1][c-1] != 0) {
					buttonMatrix[r-1][c-1].setText(str);
				}
				gamepanel.add(btn);
				
				

			}
		}

		pane.add(gamepanel, BorderLayout.CENTER);


		JButton cbtn = new JButton("Clear");
		cbtn.setPreferredSize(new Dimension(80, 20));
		cbtn.addActionListener(event -> {
			e.clear();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					buttonMatrix[i][j].setText("");
				}
			}
			pane.repaint();
		});

		JButton solbtn = new JButton("Solve");
		solbtn.setPreferredSize(new Dimension(80, 20));
		solbtn.addActionListener(event -> {
			if (e.solve() == true) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						String str = String.valueOf(e.getMatrix()[i][j]);
						buttonMatrix[i][j].setText(str);

					}
				}
				pane.repaint();
				JOptionPane.showMessageDialog(gamepanel, "Solution found!","",JOptionPane.INFORMATION_MESSAGE);
			} else if (e.solve() == false && e.isAllValid()==false){
				JOptionPane.showMessageDialog(gamepanel, "Sudoku not solvable, illegal entries found!","",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(gamepanel, "Sudoku not solvable!","",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton setbtn = new JButton("Open");
		setbtn.setPreferredSize(new Dimension(80, 20));
		setbtn.addActionListener(event -> {
			int result = fileChooser.showOpenDialog(pane);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				try {
					Scanner scan = new Scanner(new File(selectedFile.getName()));
					scan.useDelimiter("\\D");
					outerloop: for (int r = 0; r < 9; r++) {
						for (int c = 0; c < 9; c++) {
							String nextNumber = scan.next();
							try {
								e.setNumber(r, c, Integer.parseInt(nextNumber));
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(gamepanel, "Wrong format.","",JOptionPane.ERROR_MESSAGE);
								break outerloop;

							}

						}
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					String str = String.valueOf(e.getMatrix()[i][j]);
					if (str.equals("0")) {
						buttonMatrix[i][j].setText("");
					} else {
						buttonMatrix[i][j].setText(str);
					}

				}
			}

		});

		JButton valbtn = new JButton("Validate");
		valbtn.setPreferredSize(new Dimension(80, 20));
		valbtn.addActionListener(event -> {
			if (e.isAllValid()) {
				JOptionPane.showMessageDialog(gamepanel, "All entries are valid.");
			}

			else {
				JOptionPane.showMessageDialog(gamepanel, "Illegal entries found.");
			}
		});

		JButton savebtn = new JButton("Save");
		savebtn.setPreferredSize(new Dimension(80, 20));
		savebtn.addActionListener(event -> {
			StringBuilder file = new StringBuilder();
			String str = JOptionPane.showInputDialog("Choose filename.");
			if (str == null) {
			} else {
				Path path = Paths.get(str + ".txt");
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						int dsd = e.getMatrix()[i][j];
						file.append(dsd + ",");
					}
				}

				try {
					Files.writeString(path, file.toString(), StandardCharsets.UTF_8);
					System.out.println("Saved file: "  + path.toAbsolutePath());
				} catch (IOException ex) {
					
				}
			}

		});
		JPanel panel = new JPanel();

		panel.add(cbtn);
		panel.add(solbtn);
		panel.add(setbtn);
		panel.add(valbtn);
		panel.add(savebtn);
		pane.add(panel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

}

class MatrixButton extends JButton {
	private static final long serialVersionUID = -7859776160737374423L;
	private final int row;
	private final int col;

	public MatrixButton(String t, int row, int col) {
		super(t);
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}

class ButtonMatrix {
	private MatrixButton[][] m;

	public ButtonMatrix() {
		m =new MatrixButton[10][10];
	}
	
	public void insert(MatrixButton b, int row, int col) {
		m[row][col]=b;
	}
	
	public JButton get(int row, int col) {
		return m[row][col];
	}

}
