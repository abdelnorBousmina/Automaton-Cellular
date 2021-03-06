package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import model.Person;

public class PersonUI extends Canvas {

	/**
	 * La couleur
	 */
	private Color color;

	/**
	 * La partie graphique sur laquelle dessiner cette personne
	 */
	private GridUI gridUI;

	/**
	 * La personne liee a cette instance
	 */
	private Person person;

	/**
	 * La position en x
	 */
	private Integer x;

	/**
	 * La position en y
	 */
	private Integer y;

	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par defaut. Cree une couleur aleatoire pour cette personne
	 */
	public PersonUI() {

		// Random color
		Random randomGenerator = new Random();
		int red = randomGenerator.nextInt(255);
		int green = randomGenerator.nextInt(255);
		int blue = randomGenerator.nextInt(255);

		color = new Color(red,green,blue);

	}

	/**
	 * @return la couleur
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return la partie graphique liee a cette instance
	 */
	public GridUI getGridUI() {
		return gridUI;
	}

	/**
	 * @return la personne liee a cette instance
	 */
	public Person getPerson() {
		return person;
	}


	@Override
	public void paint(Graphics g) 
	{
		update(g);
	} 

	/**
	 * @param color la couleur de cette personne
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param gUi la partie graphique d'une grille a lier
	 */
	public void setGridUI(GridUI gUi) {
		this.gridUI = gUi;
	}

	/**
	 * @param person la personne a lier a cette instance
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Met a jour la position de cette instance par rapport a la personne a laquelle
	 * elle est liee.
	 */
	public void updatePosition()
	{
		this.x = gridUI.getX() + gridUI.getWdOfRow() * person.getColonne() + 8;
		this.y = gridUI.getY() + gridUI.getHtOfRow() * person.getLine() + 8;
	}

	/**
	 * Dessine cette personne.
	 * @see javax.swing.JFrame#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		g.setColor(color);
		g.fillRect( Math.round(x), Math.round(y), 15, 15 );
	}
}
