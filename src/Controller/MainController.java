package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Interface.WordNode;
import Model.Concordance;
import View.ContextViewBuilder;
import View.Lab2View;

public class MainController implements ActionListener{
	
	private Lab2View view;
	private Concordance model;
	
	public MainController (Lab2View fromView) {
		view = fromView;
		model = view.model;
		
		setUpListeners();
	}
	
	
	public void loadCommonWords() {
		try {
			// model
			model.buildCommonWordsHash();
			//gui
			view.btnLoadCommonWords.setEnabled(false);
			view.btnBuildConcordance.setEnabled(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void buildConcordance() {
		try {
			// model
			model.buildConcordance();
			// gui
			updateListOfWordsAndCBOfFrequency(model.getTree());
			view.btnDisplayAll.setEnabled(true);
			view.btnGetBiggest.setEnabled(true);
			view.btnGetSmallest.setEnabled(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void displayAllConcordance() {
		view.resultPanel.add(ContextViewBuilder.buildContextView(model.getTree()));
		view.validate();
		view.repaint();		
	}

	private void updateListOfWordsAndCBOfFrequency(WordNode tree) {		
		WordNode temp = tree;
		ArrayList<Integer> frequencyArray = new ArrayList<Integer>();
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				updateListOfWords(temp);
				frequencyArray = updateArrayOfFrequency(temp, frequencyArray);
			}
		} while (temp != tree);
		updateCBOfFrequency(frequencyArray);		
	}

	private void updateListOfWords(WordNode temp) {
		view.listOfWords.addElement(temp.getWord());
	}


	private ArrayList<Integer> updateArrayOfFrequency(WordNode temp, ArrayList<Integer> array) {
		int count = temp.getCount();
		int index = Collections.binarySearch(array, count);
		if (index < 0) {
			array.add(-(index+1), count);
		}
		return array;
	}

	private void updateCBOfFrequency(ArrayList<Integer> frequencyArray) {
		view.cbFrequency.removeAllItems();
		view.cbFrequency.addItem("Choose a Frequency");
		for (int item: frequencyArray) {
			view.cbFrequency.addItem(item);
		}
	}

	//set up Listeners
	void setUpListeners () {
		view.btnDisplayAll.addActionListener(this);
		view.btnGetBiggest.addActionListener(this);
		view.btnGetSmallest.addActionListener(this);
		view.btnLoadCommonWords.addActionListener(this);
		view.btnBuildConcordance.addActionListener(this);
	}
	
	//invoke Action Listener 
	public void actionPerformed (ActionEvent e) {
		Object events = e.getSource();
		if (events.equals(view.btnLoadCommonWords)) loadCommonWords();
		else if (events.equals(view.btnBuildConcordance)) buildConcordance();
		else if (events.equals(view.btnDisplayAll)) displayAllConcordance();
	}

	//getters and setters
	public Lab2View getView() {
		return view;
	}
	
	public Concordance getModel() {
		return model;
	}
	
	
}
