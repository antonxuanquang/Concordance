import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Concordance;
import View.Lab2View;

/**
 * 
 * Name: Concordance Builder
 * Purpose: This application reads a text file and builds a Concordance basing on all the words
 * Data Structure: Double-threaded Binary Search Tree
 * Limitation: When trying to display all the words or words with small frequency in large data set, 
 * 			there would be a 5-10 second lag for the program to update the GUI. 
 * 
 * @author Quang Nguyen
 *
 */

public class Lab2 extends JFrame {
	private Lab2View view;
	private Concordance model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Lab2();
	}

	
	public Lab2() {
		model = new Concordance();
		view = new Lab2View(model);
		createFrame();
	}

	/**
	 * Create the frame.
	 */
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 581);
		setContentPane(view);
		setVisible(true);
	}
	
	/**
	 * @return model
	 */
	public Concordance getModel() {
		return model;
	}
	
	/**
	 * @return view
	 */
	public Lab2View getView() {
		return view;
	}
}
