import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

//public class Test extends JFrame {
//	public static void main(String[] args) throws Exception {
//		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		Test test = new Test();
//		test.setVisible(true);
//	}
//
//	public Test() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JPanel panel = new JPanel(new MigLayout("fillx, pack pref pref, debug, insets 0, gap 0"));
//		setContentPane(panel);
//		JButton button = new JButton("pack");
//		button.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				pack();
//			}
//
//		});
//		panel.add(button);
//		JLabel text = new JLabel(
//				"<Html>Quite long text, that <B>will wrap</B> for a few lines.<Div>Miglayout should calculate height of a container that uses miglayout as layout manager - height is calculated, but its wrong. If you will call getPrefferedSize() before pack and after pack() you will see that height has changed.</Html>");
//		panel.add(text, "newline, growx, width 0:300");
//
//		setLocationRelativeTo(null);
//	}
//}

public class Test extends JFrame
{
   public static void main(String[] args) throws Exception
   {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      Test test = new Test();
      test.setVisible(true);
   }

   public Test()
   {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel = new JPanel(new MigLayout("fillx, debug, insets 0, gap 0"));
      setContentPane(panel);
      JButton button = new JButton("pack");
      button.addActionListener(new ActionListener()
      {

         public void actionPerformed(ActionEvent e)
         {
            pack();
         }

      });
      panel.add(button);
      JLabel text = new JLabel("<Html>Quite long text, that <B>will wrap</B> for a few lines.<Div>Miglayout should calculate height of a container that uses miglayout as layout manager - height is calculated, but its wrong. If you will call getPrefferedSize() before pack and after pack() you will see that height has changed.</Html>");
      panel.add(text, "newline, growx, width 0:300");

      setLocationRelativeTo(null);
      pack();
      pack(); // Sometimes, and maybe on some platforms, this second pack needs to be in an invokelater clause...
   }
}