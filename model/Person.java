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
public class Person implements Cloneable {
	
	/**
	 * Colonne
	 */
	private Integer colonne;
	
	/**
	 * Le Controlleur qui gère cette instance
	 */
	private Controlleur controlleur;
	
	/**
	 * Distance à rajouter lors du parcours d'une diagonale
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
		distance = 0;
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
	public Controlleur getControlleur() {
		return controlleur;
	}
	
	/**
	 * @return la distance parcourue
	 */
	public int getDistance() {
		System.out.println("dist __ " + distance);
		return distance;
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
	
	public void setDistance(int d) {
		distance = d;
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
		this.distance = p.distance;
	}

	/**
	 * Mets à jour la position de la personne
	 * @param movement Tableau renvoyée par MathModel.bouger()
	 * @return True si la personne est encore dans le grille, False sinon
	 */
	public Boolean updatePosition()
	{
		Boolean retour = true;

		// Choix de la nouvelle position
		Integer[] mvt = model.bouger(new Neighborhood(this));
		
		// Définition des variables pour la nouvelle position
		Integer[] nextPos = new Integer[3];
		nextPos[0] = ligne + mvt[0];
		nextPos[1] = colonne + mvt[1];
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
			else
			{
				// Si la personne ne s'est pas déplacée en diagonale, on augmente la distance
				if(Math.abs(mvt[0] + mvt[1]) == 1)
				{					
					//System.out.println("Depla. ligne - " + distance);
					distance = distance + 1;
				}
				// Si elle déplacée en diagonale, on change l'opération
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
