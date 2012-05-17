package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import model.Grille;
import model.Person;
import vue.Chart;
import vue.ChartLine;
import vue.DrawAreaUI;
import vue.GrilleUI;
import vue.PersonUI;

public class Controlleur {

	/**
	 * Probabilité de panique des personnes
	 */
	private static Float conflit = 0.5f;
	
	/**
	 * Aire de dessin
	 */
	private DrawAreaUI drawArea;
	
	/**
	 * Grille du système
	 */
	private Grille grille;
	
	/**
	 * Tableau des personnes actuellement dans le système
	 */
	private ArrayList<Person> personnes;
	
	/**
	 * Timer permettant le déplacement régulier
	 */
	private Timer timer;
	
	private ChartLine chartExit;
	private Chart chartDistance;
	
	private int nbIterations;
		
	/**
	 * Constructeur par défaut. Crée une nouvelle DrawAreaUI et une nouvelle Grille.
	 * Il initialise les propriétés privées et déclenche un timer qui se charge de
	 * redessiner la zone toute les secondes.
	 * @param tabX : abscisses des entrées
	 * @param tabY : ordonnées des entrées
	 */
	public Controlleur(int[] tabX, int[] tabY, int nbLigne, int nbCol)
	{
			
		/*
		 *  STEP 1 : Définition de la grille
		 */
		drawArea = new DrawAreaUI();
		grille = new Grille(nbLigne,nbCol,tabX, tabY);
		drawArea.setGrilleUi(new GrilleUI(grille));
		drawArea.paintGrille();
		
		/*
		 * STEP 2 : Définition des personnes
		 */
		personnes = new ArrayList<Person>();
		
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
		
		/*
		 * STEP 3 : Définition des charts 
		 */
		chartExit = new ChartLine("Sortie des personnes");
		nbIterations = 0;
		
		chartDistance = new Chart("Distance parcourue");
		
		/*
		 * STEP 4 : Définition du timer (toute les 1 secondes)
		 */
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
					
					// Dans le cas où la personne n'est plus dans le système
					if(!p.updatePosition()) 
					{
						it.remove();	
						it2.remove();
						
						chartExit.addPoint(nbIterations, "Dates de sortie", Integer.toString(p.getId()));
						System.out.println(p.getDistance());
						chartDistance.addPoint(p.getDistance(), "Distances", Integer.toString(p.getId()));
						
						drawArea.removePersonUi(p.getUi());					
					}
				}
				resolveConflicts(clonePersonnes);
				
				for(int i = 0; i < clonePersonnes.size(); i++)
				{
					personnes.get(i).update(clonePersonnes.get(i));
				}
				
				drawArea.updateItems();
				nbIterations++;
			}
		} );
		timer.setInitialDelay(1000);
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
	
	/**
	 * Retourne l'aire de dessin gérée par ce controleur
	 * @return l'aire de dessin gérée par ce controleur
	 */
	public DrawAreaUI getDrawArea() {
		return drawArea;
	}
	
	/**
	 * Retourne la grille gérée par ce controleur
	 * @return la grille gérée par ce controleur
	 */
	public Grille getGrille() {
		return grille;
	}
	
	/**
	 * Retourne la position d'une personne dans la liste des personnes
	 * @param p La personne dont on cherche la position
	 * @return La position de la personne dans la liste des personnes
	 */
	public int getId(Person p)
	{
		return personnes.indexOf(p);
	}
	
	/**
	 * Indique si une position est occupée ou non
	 * @param pos La position à vérifier
	 * @return True si la position est occupée, False sinon
	 */
	public Boolean isOccupied(Integer[] pos)
	{	
		for (Person p : personnes) {
			if(p.getLigne() == pos[0] && p.getColonne() == pos[1] && p.getId() != pos[2])
			{				
				return true; 
			}
		}
		return false;
	}
	
	/**
	 * Si 2 personnes parmis la nouvelle grille ont fait le même choix de case, une personne est tirée au sort
	 * pour rester sur sa case tandis que l'autre reprend sa position précédente
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
	
	/**
	 * Définit la zone de dessin à rattacher à ce controleur
	 * @param drawArea La zone de dessin à rattacher 
	 */
	public void setDrawArea(DrawAreaUI drawArea) {
		this.drawArea = drawArea;
	}
	
	/**
	 * Démarre la simulation
	 */
	public void startSimulation()
	{
		timer.start();
	}
	
	/**
	 * Arrête la simulation
	 */
	public void stopSimulation()
	{
		timer.stop();
	}
	
}
