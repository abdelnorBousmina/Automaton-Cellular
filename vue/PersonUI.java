package vue;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class PersonUI extends Canvas {

	private Color color;
	private Integer x;
	private Integer y;
	private GrilleUI gUi;
	
	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

	public PersonUI() {
		color = Color.RED;
	}
	
	public PersonUI(GrilleUI gUi) {
		color = Color.RED;
		this.gUi = gUi;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public GrilleUI getgUi() {
		return gUi;
	}

	public void setgUi(GrilleUI gUi) {
		this.gUi = gUi;
	}

	/**
	 * Transforme la position "matricielle" en position relative à la grille
	 * @param x Position "matricielle"
	 */
	public void setX(Integer x) {
		// Origine grille + taille ligne * x 
		this.x = gUi.getX() + gUi.getWdOfRow() * x;
	}

	/**
	 * Transforme la position "matricielle" en position relative à la grille
	 * @param y Position "matricielle"
	 */
	public void setY(Integer y) {
		this.y = gUi.getY() + gUi.getHtOfRow() * y;
	}

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
		g.setColor(color);
		System.out.println(" X draw : " + Math.round(x) + " - Y draw : " + Math.round(y));
		g.fillRect( Math.round(x), Math.round(y), 15, 15 );
	}
	
}
