/**
 * 
 */
package model;

import vue.MainWindow;
import vue.PersonUI;

/**
 * @author albin
 *
 */
public class Person {
	
	/**
	 * Dessin
	 */
	//private PersonUI ui;
	
	/**
	 * Ligne
	 */
	private Integer ligne;
	
	/**
	 * Colonne
	 */
	private Integer colonne;
	
	/**
	 * Modèle mathématique
	 */
	private MathModel model;
	
	private Grille grille;
	
	
	public Person() {
		//ui = new PersonUI();
		model = new MathModel();
	}
	
	/**
	 * @return the ui
	 */
	/*public PersonUI getUi() {
		return ui;
	}*/
	/**
	 * @param ui the ui to set
	 */
	/*public void setUi(PersonUI ui) {
		this.ui = ui;
	}*/
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

	/**
	 * Mets à jour la position de la personne
	 * @param movement Tableau renvoyée par MathModel.bouger()
	 */
	//public void updatePosition(Integer[] movement)
	public void updatePosition()
	{
		Integer[] mvt = model.bouger(new Neighborhood(this));
		ligne += mvt[0];
		colonne += mvt[1];
		
		System.out.println("-------- PERSON : x = " + mvt[0] + " - y = " + mvt[1]);
		//ui.setX(x);
		//ui.setY(y);
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

}
