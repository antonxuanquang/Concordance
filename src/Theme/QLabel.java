package Theme;

import java.awt.Color;
import javax.swing.JLabel;

public class QLabel extends JLabel{
	
	Color mainColor = ColorTheme.getSecondaryColor();
	
	public QLabel (String text) {
		this.setText(text);
		this.setForeground(mainColor);
	}
}
