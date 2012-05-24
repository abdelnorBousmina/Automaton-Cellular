/**
 * 
 */
package model;

import controller.Controller;
import view.PersonUI;

/**
 * @author albin
 *
 */
public class Person implements Cloneable {

	/**
	 * Colonne
	 */
	private Integer colonne;

	/**
	 * Le Controlleur qui gere cette instance
	 */
	private Controller controlleur;

	/**
	 * Distance a rajouter lors du parcours d'une diagonale
	 */
	private static int diagonalLength = 0;

	/**
	 * La distance parcourue par cette personne
	 */
	private int distance;

	/**
	 * Id
	 */
	private int id;

	/**
	 * Ligne
	 */
	private Integer line;

	/**
	 * Modele mathematique
	 */
	private MathModel model;

	/**
	 * Position precedente
	 */
	private Integer[] posPrec;

	/**
	 * Dessin
	 */
	private PersonUI ui;

	/**
	 * Constructeur par defaut. Instancie la position precedente et le modele 
	 * mathematique. L'id est mis a -1.
	 */
	public Person() {
		//ui = new PersonUI();
		posPrec = new Integer[2];
		model = new MathModel();
		id = -1;
		distance = 0;
	}

	/**
	 * Constructeur surcharge. Definit l'id, la position et le controlleur selon les
	 * parametres. Instancie la position precedente et le modele mathematique.
	 * @param id L'id a definir
	 * @param x La position en x
	 * @param y La position en y
	 * @param c Le controlleur
	 */
	public Person(int id, int x, int y, Controller c) {
		this.id = id;
		line = x;
		colonne = y;
		posPrec = new Integer[2];
		posPrec[0] = x;
		posPrec[1] = y;
		model = new MathModel();
		controlleur = c;
		distance = 0;
	}

	/**
	 * @return un clone de cette instance 
	 */
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {			
			cnse.printStackTrace(System.err);
		}
		return o;
	}

	/**
	 * @return le y de cette personne
	 */
	public Integer getColonne() {
		return colonne;
	}

	/**
	 * @return le controlleur de cette personne
	 */
	public Controller getControlleur() {
		return controlleur;
	}

	/**
	 * @return la distance parcourue
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return la grille ou se trouve cette personne
	 */
	public Grid getGrid() {
		return controlleur.getGrid();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return le x de cette personne
	 */
	public Integer getLine() {
		return line;
	}

	/**
	 * @return Le model de cette personne
	 */
	public MathModel getModel() {
		return model;
	}

	/**
	 * @return la position precedente de cette personne
	 */
	public Integer[] getPosPrec() {
		return posPrec;
	}

	/**
	 * @return le ui de cette personne
	 */
	public PersonUI getUi() {
		return ui;
	}

	/**
	 * @param y le y a definir
	 */
	public void setColumn(Integer y) {
		this.colonne = y;
	}

	/**
	 * @param controlleur le controlleur a definir
	 */
	public void setController(Controller controlleur) {
		this.controlleur = controlleur;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param x le x a definir
	 */
	public void setLine(Integer x) {
		this.line = x;
	}

	/**
	 * @param model le model a definir
	 */
	public void setModel(MathModel model) {
		this.model = model;
	}

	/**
	 * @param posPrec la position precedente a definir
	 */
	public void setPosPrec(Integer[] posPrec) {
		this.posPrec = posPrec;
	}

	/**
	 * @param ui le ui a definir
	 */
	public void setUi(PersonUI ui) {
		this.ui = ui;
	}

	/**
	 * Mets a jour les coordoonees de cette instance
	 * par rapport a celle donnee en parametre
	 * @param p L'instance dont on souhaite copier les coordonnees
	 */
	public void update(Person p)
	{
		this.line = p.line;
		this.colonne = p.colonne;
		this.distance = p.distance;
	}

	/**
	 * Mets a jour la position de la personne
	 * @return True si la personne est encore dans le grille, False sinon
	 */
	public Boolean updatePosition()
	{
		Boolean retour = true;

		// Choix de la nouvelle position
		Integer[] mvt = model.move(new Neighborhood(this));

		// Definition des variables pour la nouvelle position
		Integer[] nextPos = new Integer[3];
		nextPos[0] = line + mvt[0];
		nextPos[1] = colonne + mvt[1];
		nextPos[2] = id;

		// On verifie si la case est libre
		if(!controlleur.isOccupied(nextPos))
		{
			line += mvt[0];
			colonne += mvt[1];

			// Si la prochaine case n'est pas dans le systeme
			if( line < 0 || line > getGrid().getNbLines() - 1 || 
					colonne < 0 || colonne > getGrid().getNbColumns() - 1 )
			{
				retour = false;
			}
			else
			{
				// Si la personne ne s'est pas deplacee en diagonale, on augmente la distance
				if(Math.abs(mvt[0] + mvt[1]) == 1)
				{					
					//System.out.println("Depla. ligne - " + distance);
					distance = distance + 1;
				}
				// Si elle deplacee en diagonale, on change l'operation
				else if(mvt[0] != 0 && mvt[1] != 0)
				{
					//System.out.println("Depla. diag - " + distance);
					distance = distance + 1 + diagonalLength;
				}
			}
		}

		return retour;

	}
}
