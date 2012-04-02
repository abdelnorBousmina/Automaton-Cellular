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
import model.MathModel;
import model.Neighborhood;
import model.Person;

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
		@SuppressWarnings("unused")
		MainWindow mw = new MainWindow();
		Grille g = new Grille(7, 7, 5, 6);
		g.afficherGrille();
		
		Person person = new Person();
		person.setX(4);
		person.setY(2);
		
		System.out.println(g.getValue(person.getX(), person.getY()));
		
		Neighborhood nbh = new Neighborhood(g, person);
		
		nbh.afficherNeighborhood();
		
		MathModel model = new MathModel();
		Integer[] mvt = model.bouger(nbh);
		
		person.updatePosition(mvt);
		
		System.out.println("NEW PERSON'S POS : (" + person.getX() + " , " + person.getY() + ")");

	}

}
