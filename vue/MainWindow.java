/**
 * 
 */
package vue;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import model.Grille;

/**
 * @author bous
 *
 */
public class MainWindow {

	private JPanel panel;
	private JFrame frame;
	
	public MainWindow()
	{
		frame = new JFrame("Automate cellulaire");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		panel = new JPanel();		
		panel.setLayout(new GridLayout(3,2,10,10));
		panel.setBorder(new EmptyBorder(30,30,30,30));
		panel.setSize(640, 480);
		JLabel label = new JLabel("automate celullaire");
		panel.add(label);
		frame.add(panel,"Center");
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
		Grille g = new Grille(7, 7, 3, 0);
		g.afficherGrille();
	}

}
