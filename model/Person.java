/**
 * 
 */
package model;

import vue.PersonUI;

/**
 * @author albin
 *
 */
public class Person {
	
	private PersonUI ui;
	private Integer x;
	private Integer y;
	
	
	public Person()
	{
		// TODO Coder le constructeur
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
	
	/**
	 * Mets à jour la position de la personne
	 * @param movement Tableau renvoyée par MathModel.bouger()
	 */
	public void updatePosition(Integer[] movement)
	{
		x += movement[0];
		y += movement[1];
	}

}
