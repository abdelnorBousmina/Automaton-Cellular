package vue;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import model.Person;

public class PersonUI extends Canvas {

	private Color color;
	private Integer x;
	private Integer y;
	private GrilleUI grilleUI;
	private Person person;
	
	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

	public PersonUI() {
		color = Color.RED;
	}
	
	public PersonUI(GrilleUI gUi) {
		color = Color.RED;
		this.grilleUI = gUi;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public GrilleUI getGrilleUI() {
		return grilleUI;
	}

	public void setGrilleUI(GrilleUI gUi) {
		this.grilleUI = gUi;
	}
	
	public void updatePosition()
	{
		this.x = grilleUI.getX() + grilleUI.getWdOfRow() * person.getX();
		this.y = grilleUI.getY() + grilleUI.getHtOfRow() * person.getY();
	}

	/**
	 * Transforme la position "matricielle" en position relative à la grille
	 * @param x Position "matricielle"
	 */
	/*public void setX(Integer x) {
		// Origine grille + taille ligne * x 
		this.x = gUi.getX() + gUi.getWdOfRow() * x;
	}*/

	/**
	 * Transforme la position "matricielle" en position relative à la grille
	 * @param y Position "matricielle"
	 */
	/*public void setY(Integer y) {
		this.y = gUi.getY() + gUi.getHtOfRow() * y;
	}*/

	@Override
	public void paint(Graphics g) {
		update(g);
	} 
	
	/**
	 * Draw the car
	 * @see javax.swing.JFrame#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		System.out.println("pUi : update");
		g.setColor(color);
		System.out.println(" X draw : " + Math.round(x) + " - Y draw : " + Math.round(y));
		g.fillRect( Math.round(x), Math.round(y), 15, 15 );
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
