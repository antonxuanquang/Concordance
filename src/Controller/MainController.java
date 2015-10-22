package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Lab2View;

public class MainController implements ActionListener{
	
	private Lab2View view;
	
	public MainController (Lab2View fromView) {
		view = fromView;
	}
	
	
	//set up Listeners
	void setUpListeners () {
		view.btnDisplayAll.addActionListener(this);
		view.btnGetBiggest.addActionListener(this);
		view.btnGetSmallest.addActionListener(this);
		view.btnLoadCommonWords.addActionListener(this);
		view.btnLoadConcordance.addActionListener(this);
		view.btnRemoveCommonWords.addActionListener(this);
		view.btnSearch.addActionListener(this);
	}
	
	//invoke Action Listener 
	public void actionPerformed (ActionEvent e) {
		
	}
	
}
