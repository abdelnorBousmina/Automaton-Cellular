package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import model.Grille;
import model.Person;
import vue.DrawAreaUI;
import vue.GrilleUI;
import vue.PersonUI;

public class Controlleur {

	private static Float conflit = 0.5f;
	
	private DrawAreaUI drawArea;
	
	public DrawAreaUI getDrawArea() {
		return drawArea;
	}

	public void setDrawArea(DrawAreaUI drawArea) {
		this.drawArea = drawArea;
	}

	private Grille grille;
	public Grille getGrille() {
		return grille;
	}

	private ArrayList<Person> personnes;
	
	private Timer timer;
	
	/**
	 * Constructeur par défaut. Crée une nouvelle DrawAreaUI et une nouvelle Grille.
	 * Il initialise les propriétés privées et déclenche un timer qui se charge de
	 * redessiner la zone toute les secondes.
	 */
	public Controlleur()
	{
		int tabX[] = new int[2];
		int tabY[] = new int[2];
		
		// 1st entry
		tabX[0] = 10;
		tabY[0] = 1;
		
		// 2nd entry
		tabX[1] = 10;
		tabY[1] = 2;
		
		// Grille
		drawArea = new DrawAreaUI();
		
		grille = new Grille(11,11,tabX, tabY);
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
		/*		
		addPerson(new Person(7,1,3, this));
		addPerson(new Person(6,2,3, this));
		addPerson(new Person(5,3,3, this));
		addPerson(new Person(4,4,3, this));
		addPerson(new Person(3,5,3, this));
		*/
		addPerson(new Person(1,2,5, this));
		addPerson(new Person(2,3,5, this));
		addPerson(new Person(3,4,5, this));
		addPerson(new Person(4,7,5, this));
		addPerson(new Person(5,8,5, this));
		
		addPerson(new Person(6,2,3, this));
		addPerson(new Person(7,3,3, this));
		addPerson(new Person(8,4,3, this));
		addPerson(new Person(9,7,3, this));
		addPerson(new Person(10,8,3, this));
		
		addPerson(new Person(11,2,1, this));
		addPerson(new Person(12,3,1, this));
		addPerson(new Person(13,4,1, this));
		addPerson(new Person(14,7,1, this));
		addPerson(new Person(15,8,1, this));
		
		addPerson(new Person(16,5,9, this));
				
		//System.out.println("Lig : " + per.getLigne() + " | Col : " + per.getColonne());
		//nbh.afficherNeighborhood();
		
		timer = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Person> clonePersonnes = new ArrayList<Person>();
				
				for (Person p : personnes )
				{
					clonePersonnes.add((Person) p.clone());
				}				
				
				Iterator<Person> it = clonePersonnes.iterator();
				Iterator<Person> it2 = personnes.iterator();
				Person p;
				while(it.hasNext())
				{
					p = it.next();
					it2.next();
					if(!p.updatePosition()) 
					{
						it.remove();	
						it2.remove();
						drawArea.removePersonUi(p.getUi());						
					}
				}
				resolveConflicts(clonePersonnes);
				//System.out.println("maj personnes");
				
				for(int i = 0; i < clonePersonnes.size(); i++)
				{
					personnes.get(i).update(clonePersonnes.get(i));
				}
				
				drawArea.updateItems();

			}
		} );
		timer.setInitialDelay(1000);
		
		//timer.start();
		
	}
	
	/**
	 * Ajoute une Person à la Grille gérée par ce Controlleur
	 * @param person La personne à ajouter
	 */
	public void addPerson(Person person)
	{
		//person.setGrille(grille);
		PersonUI pUi = new PersonUI();
		pUi.setPerson(person);
		pUi.setGrilleUI(drawArea.getGrilleUi());
		person.setUi(pUi);
		
		personnes.add(person);
		drawArea.addPersonUi(pUi);	
	}
	
	public Boolean isOccupied(Integer[] pos)
	{
		//System.out.println("new: " + pos[2]+ ": " +pos[0] + " " + pos[1]);
		//System.out.println("old: " + personnes.get(0).getId() + ": " +personnes.get(0).getLigne()  + " " + personnes.get(0).getColonne() );
		
		for (Person p : personnes) {
			if(p.getLigne() == pos[0] && p.getColonne() == pos[1] && p.getId() != pos[2])
			{				
				//System.out.println(p.getId() + " Occupé");
				return true; 
			}
		}
		return false;
	}
	
	/**
	 * Si 2 personnes parmis la nouvelle grille on fait le même choix de case, une personne est tirée au sort
	 * pour rester sur sa case tandis que l'autre reprand sa position précédente
	 * @param nextPersonnes
	 */
	private void resolveConflicts(ArrayList<Person> nextPersonnes)
	{
		int i = 0;
		
		for (Person p : nextPersonnes)
		{
			int j = 0;
			for (Person p2 : nextPersonnes)
			{
				if(p.getLigne() == p2.getLigne()  && p.getColonne() == p2.getColonne() && p.getId() != p2.getId())
				{
					//System.out.println("conflit");					
					if( Math.random() >= conflit )
					{
						p.setLigne(personnes.get(i).getLigne());
						p.setColonne(personnes.get(i).getColonne());
					}
					else
					{
						p2.setLigne(personnes.get(j).getLigne());
						p2.setColonne(personnes.get(j).getColonne());
					}
				}
				j++;
			}
			i++;
		}
	
	}
	
	public int getId(Person p)
	{
		return personnes.indexOf(p);
	}
	
	public void startSimulation()
	{
		timer.start();
	}
	
	public void stopSimulation()
	{
		timer.stop();
	}
	
}
