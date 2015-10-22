import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Concordance;
import View.Lab2View;

public class Lab2 extends JFrame {
	private Lab2View view;
	private Concordance model;
	
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
		model = new Concordance();
		view = new Lab2View(model);
		createFrame();
	}

	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 581);
		setContentPane(view);
		setVisible(true);
	}
	
	public Concordance getModel() {
		return model;
	}
	
	public Lab2View getView() {
		return view;
	}
}
