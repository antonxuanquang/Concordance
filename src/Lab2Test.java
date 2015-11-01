import java.io.FileNotFoundException;

import javax.swing.JFrame;

import Controller.MainController;
import Interface.WordNode;
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
//		control.displayAllConcordance();
//		WordNode tree = control.getTree();
//		System.out.println(tree.tInOrder());
		
//		long start = System.nanoTime();
//		long stopTime = System.nanoTime();
//		long elapsed = stopTime - start;
//		System.out.println("Building time: " + elapsed/1.0e9);
	}
	
	
}
