package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import model.Grille;
import model.Person;
import vue.DrawAreaUI;
import vue.GrilleUI;
import vue.PersonUI;

public class Controlleur {

	private DrawAreaUI drawArea;
	
	public DrawAreaUI getDrawArea() {
		return drawArea;
	}

	public void setDrawArea(DrawAreaUI drawArea) {
		this.drawArea = drawArea;
	}

	private Grille grille;
	private ArrayList<Person> personnes;
	
	public Controlleur()
	{
		int tabX[] = new int[2];
		int tabY[] = new int[2];
		
		tabX[0] = 5;
		tabX[1] = 6;
		
		tabY[0] = 0;
		tabY[1] = 0;
		
		// Grille
		drawArea = new DrawAreaUI();
		
		grille = new Grille(10,10,tabX, tabY);
		drawArea.setGrilleUi(new GrilleUI(grille));
		drawArea.paintGrille();
		
		// Person
		personnes = new ArrayList<Person>();
		personnes.add(new Person());
		personnes.get(0).setX(6);
		personnes.get(0).setY(4);
		personnes.get(0).setGrille(grille);
		PersonUI pUi = new PersonUI();
		pUi.setPerson(personnes.get(0));
		pUi.setGrilleUI(drawArea.getGrilleUi());
		drawArea.addPersonUi(pUi);
		
		Timer timer;
		timer = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				for (Person p : personnes) {
					p.updatePosition();
				}

				drawArea.updateItems();

			}
		} );
		timer.setInitialDelay(0);
		timer.start();
		
	}
	
}