package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import model.Grille;
import model.Neighborhood;
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
		//personnes.add(new Person());
		//personnes.add(new Person());
		
		/*personnes.get(0).setLigne(6);
		personnes.get(0).setColonne(4);*/
		
		/*personnes.get(0).setLigne(8);
		personnes.get(0).setColonne(5);*/
		
		/*personnes.get(0).setGrille(grille);
		
		PersonUI pUi = new PersonUI();
		pUi.setPerson(personnes.get(0));
		pUi.setGrilleUI(drawArea.getGrilleUi());
		
		personnes.get(0).setUi(pUi);
		drawArea.addPersonUi(pUi);*/
		
		addPerson(new Person(6,4));
		addPerson(new Person(8,5));
		
		Person per = personnes.get(0);
		
		Neighborhood nbh = new Neighborhood(per);
		
		//System.out.println("Lig : " + per.getLigne() + " | Col : " + per.getColonne());
		//nbh.afficherNeighborhood();
		
		Timer timer;
		timer = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Iterator<Person> it = personnes.iterator();
				Person p;
				//for (Person p : personnes)
				while(it.hasNext())
				{
					p = it.next();
					if(!p.updatePosition()) 
					{
						it.remove();
						//personnes.remove(personnes.indexOf(p));
						drawArea.removePersonUi(p.getUi());
					}
				}

				drawArea.updateItems();

			}
		} );
		timer.setInitialDelay(1000);
		
		timer.start();
		
	}
	
	public void addPerson(Person person)
	{
		
		person.setGrille(grille);
		PersonUI pUi = new PersonUI();
		pUi.setPerson(person);
		pUi.setGrilleUI(drawArea.getGrilleUi());
		person.setUi(pUi);
		
		personnes.add(person);
		drawArea.addPersonUi(pUi);
		
	}
	
}
