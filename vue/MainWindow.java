/**
 * 
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
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
		// ********************
		// SOME TESTS INIT
		// ********************
		int tabX[] = new int[2];
		int tabY[] = new int[2];
		
		tabX[0] = 5;
		tabX[1] = 6;
		
		tabY[0] = 0;
		tabY[1] = 0;
		
		Grille g = new Grille(10, 10, tabX, tabY);
		
		g.afficherGrille();
		
		GrilleUI gUi = new GrilleUI(g);
		// ********************
		// END OF TESTS
		// ********************
		
		
		// Définition de la frame
		frame = new JFrame("Automate cellulaire");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setResizable(false);
		
		// Création du panel
		backgroundPanel = new JPanel();	
		backgroundPanel.setLayout(new GridLayout(1,1));
		backgroundPanel.setBorder(new EmptyBorder(30,30,30,30));
		backgroundPanel.setPreferredSize(new Dimension(500,500));

		// Ajout de la GrilleUi
		backgroundPanel.add(gUi);
		
		// Ajout du panel à la frame + dessin + affichage
		frame.add(backgroundPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainWindow mw = new MainWindow();
		
		/*Person person = new Person();
		person.setX(1);
		person.setY(1);
		
		//System.out.println(g.getValue(person.getX(), person.getY()));
		
		Neighborhood nbh = new Neighborhood(g, person);
		
		//nbh.afficherNeighborhood();
		
		MathModel model = new MathModel();
		Integer[] mvt = model.bouger(nbh);
		
		person.updatePosition(mvt);
		
		//System.out.println("NEW PERSON'S POS : (" + person.getX() + " , " + person.getY() + ")");*/

	}

}
