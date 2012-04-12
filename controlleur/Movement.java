package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import vue.DrawAreaUI;
import vue.GrilleUI;

import model.Grille;
import model.MathModel;
import model.Neighborhood;
import model.Person;

public class Movement {
	
	private DrawAreaUI drawArea;
	
	private MathModel model;
	private Grille gr;
	private GrilleUI gUi;
	private Neighborhood nbh;
	private Person person1;
	

	public Movement()
	{
		/*model = new MathModel();
		Integer[] mvt;
		
		person1 = new Person();
		person1.setX(6);
		person1.setY(4);
		
		gUi = new GrilleUI(gr);
		
		person1.getUi().setgUi(gUi);
		person1.getUi().repaint();
		
		Timer timer;
		timer = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				person1.updatePosition();
				drawArea.updateItems();
			}
		} );
		timer.setInitialDelay(0);
		timer.start();*/
	}
	
}
