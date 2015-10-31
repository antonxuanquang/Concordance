package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	
	public JPanel resultPanel;
	private QTextField tfSearch;
	public QButton btnLoadCommonWords, btnDisplayAll, btnSearch, 
		btnGetSmallest, btnBuildConcordance, btnGetBiggest, btnFirst, btnLast,
		btnPrevious, btnNext;
	public JComboBox cbFrequency;
	public DefaultListModel listOfWords;
	
	
	
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
		setLayout(new MigLayout("", "", ""));
		
		
		
		// Column A
		JPanel panel = new JPanel();
		add(panel, "width ::500, growy");
		panel.setLayout(new MigLayout("", "", ""));
		
		
		btnLoadCommonWords = new QButton("Load Common Words File");
		panel.add(btnLoadCommonWords , "alignx center, aligny center, sg middleButton, wrap");
		
		btnBuildConcordance = new QButton("Load Text File");
		btnBuildConcordance.setEnabled(false);
		panel.add(btnBuildConcordance, "alignx center, aligny center, sg middleButton, wrap");
		
		btnDisplayAll = new QButton("Display All");
		btnDisplayAll.setEnabled(false);
		panel.add(btnDisplayAll, "alignx center, aligny center, sg middleButton, wrap");
		
		
		listOfWords = new DefaultListModel();
	    JList list = new JList(listOfWords);
	    JScrollPane pane = new JScrollPane(list);
	    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(pane, "height 200:300:, growy, pushy, growx, wrap");
		
		btnFirst = new QButton("First");
		panel.add(btnFirst, "split 4");
		
		btnPrevious = new QButton("<");
		panel.add(btnPrevious, "");
		
		btnNext = new QButton(">");
		panel.add(btnNext, "");
		
		btnLast = new QButton("Last");
		panel.add(btnLast, "wrap");
		
		
		QLabel lblSearch = new QLabel("Search: ");
		panel.add(lblSearch, "split 2, sg leftLabel");
		
		tfSearch = new QTextField();
		panel.add(tfSearch, "growx, wrap");
		
		btnGetSmallest = new QButton("Get Smallest");
		btnGetSmallest.setEnabled(false);
		panel.add(btnGetSmallest, "split 2, alignx center");
		
		btnGetBiggest = new QButton("Get Biggest");
		btnGetBiggest.setEnabled(false);
		panel.add(btnGetBiggest, "wrap");
		
		QLabel lblFrequency = new QLabel("Frequency: ");
		panel.add(lblFrequency, "split 2, sg leftLabel");
		
		cbFrequency = new JComboBox();
		panel.add(cbFrequency, "growx, wrap");
		
		
		// Column B
		resultPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new TitledBorder(null, "Panel of Results", TitledBorder.LEADING, TitledBorder.TOP, null, ColorTheme.getPrimaryColor()));
		scrollPane.add(resultPanel);
		add(scrollPane, "span ,push ,grow");
		
	}

}
