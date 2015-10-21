package Theme;

import java.awt.Color;
import javax.swing.JLabel;

public class QLabel extends JLabel{
	
	public QLabel (String text, Color mainColor) {
		this.setText(text);
		this.setForeground(mainColor);
	}
}
