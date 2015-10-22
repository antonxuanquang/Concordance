package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import Model.Concordance;
import View.Lab2View;

public class MainController implements ActionListener{
	
	private Lab2View view;
	private Concordance model;
	
	public MainController (Lab2View fromView) {
		view = fromView;
		model = view.model;
		
		setUpListeners();
	}
	
	
	//set up Listeners
	void setUpListeners () {
		view.btnDisplayAll.addActionListener(this);
		view.btnGetBiggest.addActionListener(this);
		view.btnGetSmallest.addActionListener(this);
		view.btnLoadCommonWords.addActionListener(this);
		view.btnBuildConcordance.addActionListener(this);
		view.btnSearch.addActionListener(this);
	}
	
	//invoke Action Listener 
	public void actionPerformed (ActionEvent e) {
		Object events = e.getSource();
		if (events.equals(view.btnLoadCommonWords)) loadCommonWords();
		else if (events.equals(view.btnBuildConcordance)) buildConcordance();
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
			//gui
			view.btnDisplayAll.setEnabled(true);
			view.btnGetBiggest.setEnabled(true);
			view.btnGetSmallest.setEnabled(true);
			view.btnSearch.setEnabled(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}


	
}
