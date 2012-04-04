package vue;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import model.Grille;

public class GrilleUI extends Canvas {

	/**
	 * Hauteur de la grille
	 */
	private int height;
	
	/**
	 * Largeur de la grille
	 */
	private int width;
	
	/**
	 * Nombre de lignes
	 */
	private int rows;
	
	/**
	 * Nombre de colonnes
	 */
	private int cols;
	
	private Grille grille;

	/**
	 * Constructeur
	 * @param w Largeur souhaitée
	 * @param h Hauteur souhaitée
	 * @param r Nombre de lignes souhaité
	 * @param c Nombre de colonnes souhaité
	 */
	public GrilleUI(int w, int h, int r, int c)
	{
		//this.setSize(w, h);
		this.setPreferredSize(new Dimension(w, h));
		System.out.println("w : " + w + " || h : " + h);
        width = w;
        height = h;
		rows = r;
        cols = c;
        
        System.out.println("width : " + width + " || height : " + height);
	}
	
	/**
	 * Constructeur. Dessine la grille à partir d'un objet.
	 * @param grille La grille que l'on doit dessiner
	 */
	public GrilleUI(Grille grille)
	{
		// TODO Récupérer les valeurs de la grilles.
	}
	
	@Override
	/**
	 * Dessine la grille.
	 */
	public void paint(Graphics g) {
		
		int k;
		width = this.getPreferredSize().width;
		height = this.getPreferredSize().height;
		
		System.out.println("width2 : " + width + " || height2 : " + height);
		
		int htOfRow = height / (rows); // Définition de la hauteur d'une ligne
		System.out.println("height : " + htOfRow);
		
		for (k = 0; k <= rows; k++) {
		    g.drawLine(0, k * htOfRow, width, k * htOfRow);
		}
		
		int wdOfRow = width / (cols); // Définition de la largeur d'une ligne
		System.out.println("width : " + wdOfRow);
		
		for (k = 0; k <= cols; k++) {
			    g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
		}
	
	}
	
}
 