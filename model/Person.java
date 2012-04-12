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
	private Integer x;
	
	/**
	 * Colonne
	 */
	private Integer y;
	
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
	public Integer getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(Integer x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public Integer getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(Integer y) {
		this.y = y;
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
		x += mvt[0];
		y += mvt[1];
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
