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

public class Lab2 extends JFrame {

	Color primary = new ColorTheme().primary;
	Color secondary = new ColorTheme().secondary;
	
	
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
		JPanel panel = new JPanel();
		contentPane.add(panel, "span, pushx, growx");
		panel.setLayout(new MigLayout("", "[][][][]", "[][][][]"));
		
		// Row 1
		QButton btnSelectFilterFile = new QButton("Select Filter File", secondary, primary);
		panel.add(btnSelectFilterFile, "cell 2 0,pushx ,width 130!,alignx right,height 20!");
		
		QButton btnSelectTextFile = new QButton("Select Text File", secondary, primary);
		panel.add(btnSelectTextFile, "cell 3 0 2 1,pushx ,width 130!,alignx left,height 20!");
		
		
		//Row 2
		QLabel lblTextfieldHereTo = new QLabel("TextField here to show file name", primary);
		panel.add(lblTextfieldHereTo, "cell 1 1 4 1,alignx center,aligny center");
		
		
		// Row 3
		QLabel label = new QLabel("TextField here to show file name", primary);
		panel.add(label, "cell 1 2 4 1,alignx center,aligny center");
		
		
		// Row 4
		QLabel lblSearch = new QLabel("Search: ", primary);
		panel.add(lblSearch, "cell 1 3,width 50!,height 20!");
		
		QTextField tf = new QTextField(secondary, primary);
		panel.add(tf, "cell 2 3,width 130!,alignx left,height 20!");
		
		QButton btnNewButton = new QButton("New button", secondary, primary);
		panel.add(btnNewButton, "cell 3 3,alignx right");
		
		QButton btnNewButton_1 = new QButton("New button", secondary, primary);
		panel.add(btnNewButton_1, "cell 4 3,alignx left, wrap");
		
		// Row B
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, "span ,push ,grow");
		
		
		
		
		
		
		
		
		
		
	}

}
