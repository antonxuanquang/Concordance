import java.io.FileNotFoundException;

import javax.swing.JFrame;

import Controller.MainController;
import Model.Concordance;

public class Lab2Test extends JFrame{
	
	
	public static void main (String [] args) {
		new Lab2Test();
	}
	
	public Lab2Test() {
		Lab2 lab2 = new Lab2();
		MainController control = new MainController(lab2.getView());
		control.loadCommonWords();
		control.buildConcordance();
	}
}
