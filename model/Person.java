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
public class Person {
	
	/**
	 * Dessin
	 */
	private PersonUI ui;
	
	/**
	 * Ligne
	 */
	private Integer ligne;
	
	/**
	 * Colonne
	 */
	private Integer colonne;
	
	/**
	 * Position précédente
	 */
	private Integer[] posPrec;
	
	/**
	 * Modèle mathématique
	 */
	private MathModel model;
	
	/**
	 * Le Controlleur qui gère cette instance
	 */
	private Controlleur controlleur;

	public Person() {
		//ui = new PersonUI();
		posPrec = new Integer[2];
		model = new MathModel();
	}
	
	public Person(int x, int y, Controlleur c) {
		//ui = new PersonUI();
		ligne = x;
		colonne = y;
		posPrec = new Integer[2];
		posPrec[0] = x;
		posPrec[1] = y;
		model = new MathModel();
		controlleur = c;
	}
	
	public Integer[] getPosPrec() {
		return posPrec;
	}

	public void setPosPrec(Integer[] posPrec) {
		this.posPrec = posPrec;
	}

	public Controlleur getControlleur() {
		return controlleur;
	}

	public void setControlleur(Controlleur controlleur) {
		this.controlleur = controlleur;
	}
	
	
	/**
	 * @return the ui
	 */
	public PersonUI getUi() {
		return ui;
	}
	/**
	 * @param ui the ui to set
	 */
	public void setUi(PersonUI ui) {
		this.ui = ui;
	}
	/**
	 * @return the x
	 */
	public Integer getLigne() {
		return ligne;
	}
	/**
	 * @param x the x to set
	 */
	public void setLigne(Integer x) {
		this.ligne = x;
	}
	/**
	 * @return the y
	 */
	public Integer getColonne() {
		return colonne;
	}
	/**
	 * @param y the y to set
	 */
	public void setColonne(Integer y) {
		this.colonne = y;
	}
	
	public MathModel getModel() {
		return model;
	}

	public void setModel(MathModel model) {
		this.model = model;
	}
	
	public Grille getGrille()
	{
		return controlleur.getGrille();
	}

	/**
	 * Mets à jour la position de la personne
	 * @param movement Tableau renvoyée par MathModel.bouger()
	 * @return True si encore dans la grille, Faux sinon
	 */
	//public void updatePosition(Integer[] movement)
	public Boolean updatePosition()
	{
		System.out.println("ID PERSON : " + controlleur.getId(this));
		Integer[] mvt = model.bouger(new Neighborhood(this));
		Boolean retour = true;
		
		if(!controlleur.isOccupied(mvt))
		{
			System.out.println("==> Mouvement");
			System.out.println("");
			ligne += mvt[0];
			colonne += mvt[1];
			
			if( ligne <= 0 || ligne >= getGrille().getNbLignes() - 1|| 
					colonne <= 0 || colonne >= getGrille().getNbColonnes() - 1 )
			{
				retour = false;
			}
		}
		else
		{
			System.out.println("==> Rien");
			System.out.println("");
		}
		
		return retour;
		
		//System.out.println("-------- PERSON : x = " + mvt[0] + " - y = " + mvt[1]);
		//ui.setX(x);
		//ui.setY(y);
	}

}
