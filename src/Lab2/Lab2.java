package Lab2;
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
import javax.swing.UIManager;

public class Lab2 extends JFrame {

	Color primary = new ColorTheme().primary;
	Color secondary = new ColorTheme().secondary;
	private JPanel panel2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab2 frame = new Lab2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Lab2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 581);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[97.00][]"));
		
		// Row A
		panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(null, "Panel of Results", TitledBorder.LEADING, TitledBorder.TOP, null, primary));
		contentPane.add(panel2, "span ,push ,grow");
		
		// Row B
		JPanel panel = new JPanel();
		contentPane.add(panel, "span, pushx, growx");
		panel.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		// Row 1
		QButton btnLoadCommonWords = new QButton("Load Common Words", secondary, primary);
		panel.add(btnLoadCommonWords , "cell 0 0, sg leftButton");
		
		QButton btnDisplayAll = new QButton("Display All", secondary, primary);
		panel.add(btnDisplayAll, "cell 2 0, sg rightButton, wrap");
		
		
		// Row 2
		QButton btnRemoveCommonWords = new QButton("RemoveCommonWords", secondary, primary);
		panel.add(btnRemoveCommonWords, "cell 0 1, sg leftButton");
		
		QLabel lblSearch = new QLabel("Search: ", primary);
		panel.add(lblSearch, "split 3,pushx ,alignx center");
		
		QTextField tf = new QTextField(secondary, primary);
		panel.add(tf, "width 100:150:200");
		
		QButton btnSearch = new QButton("Search", secondary, primary);
		panel.add(btnSearch);
		
		QButton btnGetSmallest = new QButton("Get Smallest", secondary, primary);
		panel.add(btnGetSmallest, "cell 2 1, sg rightButton, wrap");
		
		// Row 3
		QButton btnLoadConcordance = new QButton("Load Concordance", secondary, primary);
		panel.add(btnLoadConcordance, "cell 0 2, sg leftButton");
		
		QButton btnGetBiggest = new QButton("Get Biggest", secondary, primary);
		panel.add(btnGetBiggest, "cell 2 2, sg rightButton, wrap");
		
		
		
		
		
		
	}

}
