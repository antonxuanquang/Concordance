package Theme;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class QButton extends JButton implements MouseListener{
	
	Color hoverColor = ColorTheme.getPrimaryColor();
	Color mainColor = ColorTheme.getSecondaryColor();
	
	public QButton (String name) {
		addMouseListener(this);
		setBackground(mainColor);
		setForeground(Color.WHITE);
		setText(name);
	}
	
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		Object event = e.getSource();
		if (event.equals(this)) {
			setBackground(hoverColor);
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	public void mouseExited(MouseEvent e) {
		Object event = e.getSource();
		if (event.equals(this)) {
			setBackground(mainColor);
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}
