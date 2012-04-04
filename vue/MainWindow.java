/**
 * 
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
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

	private JPanel backgroundPanel;
	private JFrame frame;
	
	public MainWindow()
	{
		frame = new JFrame("Automate cellulaire");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setResizable(false);
		
		backgroundPanel = new JPanel();	
		backgroundPanel.setLayout(new GridLayout(3,2));
		backgroundPanel.setBorder(new EmptyBorder(30,30,30,30));
		//backgroundPanel.setBackground(Color.BLACK);
		backgroundPanel.setSize(100,100); // Does not work !
		
		JLabel label = new JLabel("automate celullaire");
		backgroundPanel.add(label);
		
		GrilleUI gUi = new GrilleUI(300, 300, 10, 10);
		frame.add(gUi);
		
		//frame.add(backgroundPanel,"Center");
		
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
		
		//g.afficherGrille();
		
		Person person = new Person();
		person.setX(1);
		person.setY(1);
		
		//System.out.println(g.getValue(person.getX(), person.getY()));
		
		Neighborhood nbh = new Neighborhood(g, person);
		
		//nbh.afficherNeighborhood();
		
		MathModel model = new MathModel();
		Integer[] mvt = model.bouger(nbh);
		
		person.updatePosition(mvt);
		
		//System.out.println("NEW PERSON'S POS : (" + person.getX() + " , " + person.getY() + ")");

	}

}
