package vue;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import model.Grille;

public class GrilleUI extends Canvas {

	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

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
	
	private int htOfRow;
	
	private int wdOfRow;
	
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
	
	
	
	
	public int getHtOfRow() {
		return htOfRow;
	}

	/*public void setHtOfRow(int htOfRow) {
		this.htOfRow = htOfRow;
	}*/

	public int getWdOfRow() {
		return wdOfRow;
	}

	/*public void setWdOfRow(int wdOfRow) {
		this.wdOfRow = wdOfRow;
	}*/




	/**
	 * Constructeur. Dessine la grille à partir d'un objet.
	 * @param grille La grille que l'on doit dessiner
	 */
	public GrilleUI(Grille grille)
	{
		this.setPreferredSize(new Dimension(300, 300));
		this.grille = grille;
		rows = grille.getNbLignes();
		cols = grille.getNbColonnes();
	}
	
	@Override
	/**
	 * Dessine la grille : les lignes puis les obstacles
	 */
	public void paint(Graphics g) {
		
		paintLines(g);
		fillObstacles(g);
	
	}
	
	/**
	 * Dessine les obstacles sur la grille
	 * @param g Objet graphique à utiliser pour le dessin
	 */
	private void fillObstacles(Graphics g)
	{
		int x,y;
		
		for(x=0; x<rows; x++)
		{
			for(y=0; y<cols; y++)
			{
				if(grille.getValue(x,y) >= 500)
				{
					// Signature : g.fillRect(x, y, width, height)
					g.fillRect(y * htOfRow, x * wdOfRow, wdOfRow, htOfRow);
				}
			}
		}
	}
	
	/**
	 * Dessine les lignes de la grille
	 * @param g Objet graphique à utiliser pour le dessin
	 */
	private void paintLines(Graphics g)
	{
		int k;
		width = this.getPreferredSize().width;
		height = this.getPreferredSize().height;
		
		htOfRow = height / (rows); // Définition de la hauteur d'une ligne
		
		for (k = 0; k <= rows; k++) {
		    g.drawLine(0, k * htOfRow, width, k * htOfRow);
		}
		
		wdOfRow = width / (cols); // Définition de la largeur d'une ligne
		
		for (k = 0; k <= cols; k++) {
			    g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
		}
	}
	
}
 