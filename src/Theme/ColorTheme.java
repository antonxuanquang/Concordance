package Theme;

import java.awt.Color;

public class ColorTheme {
	static Color primary = new Color(125, 130, 176);
	static Color secondary = new Color(50, 56, 117);
	
	public ColorTheme() {}
	
	public static Color getPrimaryColor() {
		return primary;
	}
	
	public static Color getSecondaryColor() {
		return secondary;
	}
}
