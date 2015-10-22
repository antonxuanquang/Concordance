package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Theme.ColorTheme;
import Theme.QButton;
import Theme.QLabel;
import Theme.QTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;

import Controller.MainController;
import Model.Concordance;

import javax.swing.UIManager;

import javax.swing.JPanel;

public class Lab2View extends JPanel {

	private MainController mainControl;
	public Concordance model;
	
	Color primary = new ColorTheme().primary;
	Color secondary = new ColorTheme().secondary;
	private JPanel panel2;
	private QTextField tf;
	public QButton btnLoadCommonWords,btnDisplayAll, btnSearch, 
		btnGetSmallest, btnBuildConcordance, btnGetBiggest;
	
	
	
	/**
	 * Create the panel.
	 */
	public Lab2View(Concordance model) {
		this.model = model;
		buildMainView();
		
		mainControl = new MainController(this);
	}
	
	void buildMainView() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MigLayout("", "[]", "[]"));
		
		// Row A
		panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(null, "Panel of Results", TitledBorder.LEADING, TitledBorder.TOP, null, primary));
		add(panel2, "span ,push ,grow");
		
		// Row B
		JPanel panel = new JPanel();
		add(panel, "span, pushx, growx");
		panel.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		// Row 1
		btnLoadCommonWords = new QButton("Load Common Words File", secondary, primary);
		panel.add(btnLoadCommonWords , "cell 0 0, sg leftButton");
		
		btnDisplayAll = new QButton("Display All", secondary, primary);
		btnDisplayAll.setEnabled(false);
		panel.add(btnDisplayAll, "cell 2 0, sg rightButton, wrap");
		
		
		// Row 2		
		QLabel lblSearch = new QLabel("Search: ", primary);
		panel.add(lblSearch, "split 3,pushx ,alignx center");
		
		tf = new QTextField(secondary, primary);
		panel.add(tf, "width 100:150:200");
		
		btnSearch = new QButton("Search", secondary, primary);
		btnSearch.setEnabled(false);
		panel.add(btnSearch);
		
		btnGetSmallest = new QButton("Get Smallest", secondary, primary);
		btnGetSmallest.setEnabled(false);
		panel.add(btnGetSmallest, "cell 2 1, sg rightButton, wrap");
		
		// Row 3
		btnBuildConcordance = new QButton("Load Text File", secondary, primary);
		btnBuildConcordance.setEnabled(false);
		panel.add(btnBuildConcordance, "cell 0 2, sg leftButton");
		
		btnGetBiggest = new QButton("Get Biggest", secondary, primary);
		btnGetBiggest.setEnabled(false);
		panel.add(btnGetBiggest, "cell 2 2, sg rightButton, wrap");
	}

}
