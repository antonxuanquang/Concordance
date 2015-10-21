import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Concordance;
import View.Lab2View;

public class Lab2 extends JFrame {
	public Lab2View view;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Lab2();
	}

	/**
	 * Create the frame.
	 */
	public Lab2() {
		Concordance model = new Concordance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 581);
		view = new Lab2View(model);
		setContentPane(view);
	}

}
