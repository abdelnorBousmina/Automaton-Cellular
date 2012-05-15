/**
 * 
 */
package model;

import controlleur.Controlleur;
import vue.PersonUI;

/**
 * @author albin
 *
 */
public class Person implements Cloneable{
	
	/**
	 * Colonne
	 */
	private Integer colonne;
	
	/**
	 * Le Controlleur qui gère cette instance
	 */
	private Controlleur controlleur;
	
	/**
	 * Id
	 */
	private int id;
	
	/**
	 * Ligne
	 */
	private Integer ligne;
	
	/**
	 * Modèle mathématique
	 */
	private MathModel model;
	
	/**
	 * Position précédente
	 */
	private Integer[] posPrec;
	
	/**
	 * Dessin
	 */
	private PersonUI ui;

	/**
	 * Constructeur par défaut. Instancie la position précédente et le modèle 
	 * mathématique. L'id est mis à -1.
	 */
	public Person() {
		//ui = new PersonUI();
		posPrec = new Integer[2];
		model = new MathModel();
		id = -1;
	}
	
	/**
	 * Constructeur surchargé. Définit l'id, la position et le controlleur selon les
	 * paramètres. Instancie la position précédente et le modèle mathématique.
	 * @param id L'id à définir
	 * @param x La position en x
	 * @param y La position en y
	 * @param c Le controlleur
	 */
	public Person(int id, int x, int y, Controlleur c) {
		this.id = id;
		ligne = x;
		colonne = y;
		posPrec = new Integer[2];
		posPrec[0] = x;
		posPrec[1] = y;
		model = new MathModel();
		controlleur = c;
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
	public Controlleur getControlleur() {
		return controlleur;
	}
	
	/**
	 * @return la grille où se trouve cette personne
	 */
	public Grille getGrille() {
		return controlleur.getGrille();
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
	public Integer getLigne() {
		return ligne;
	}
	
	/**
	 * @return Le model de cette personne
	 */
	public MathModel getModel() {
		return model;
	}
	
	/**
	 * @return la position précédente de cette personne
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
	 * @param y le y à définir
	 */
	public void setColonne(Integer y) {
		this.colonne = y;
	}
	
	/**
	 * @param controlleur le controlleur à définir
	 */
	public void setControlleur(Controlleur controlleur) {
		this.controlleur = controlleur;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param x le x à définir
	 */
	public void setLigne(Integer x) {
		this.ligne = x;
	}
	
	/**
	 * @param model le model à définir
	 */
	public void setModel(MathModel model) {
		this.model = model;
	}

	/**
	 * @param posPrec la position précédente à définir
	 */
	public void setPosPrec(Integer[] posPrec) {
		this.posPrec = posPrec;
	}
	
	/**
	 * @param ui le ui à définir
	 */
	public void setUi(PersonUI ui) {
		this.ui = ui;
	}
	
	/**
	 * Mets à jour les coordoonées de cette instance
	 * par rapport à celle donnée en paramètre
	 * @param p L'instance dont on souhaite copier les coordonnées
	 */
	public void update(Person p)
	{
		this.ligne = p.ligne;
		this.colonne = p.colonne;
	}

	/**
	 * Mets à jour la position de la personne
	 * @param movement Tableau renvoyée par MathModel.bouger()
	 * @return True si la personne est encore dans le grille, False sinon
	 */
	//public void updatePosition(Integer[] movement)
	public Boolean updatePosition()
	{
		Boolean retour = true;

		// Choix de la nouvelle position
		Integer[] mvt = model.bouger(new Neighborhood(this));
		
		// Définition des variables pour la nouvelle position
		Integer nextligne = ligne + mvt[0];
		Integer nextcolonne = colonne + mvt[1];
		Integer[] nextPos = new Integer[3];
		nextPos[0] = nextligne;
		nextPos[1] = nextcolonne;
		nextPos[2] = id;
		
		// On vérifie si la case est libre
		if(!controlleur.isOccupied(nextPos))
		{
			ligne += mvt[0];
			colonne += mvt[1];
			
			// Si la prochaine case n'est pas dans le système
			if( ligne < 0 || ligne > getGrille().getNbLignes() - 1 || 
					colonne < 0 || colonne > getGrille().getNbColonnes() - 1 )
			{
				retour = false;
			}
		}
		
		return retour;

	}
}
