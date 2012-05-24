package view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import model.Grid;

public class GridUI extends Canvas {

	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nombre de colonnes
	 */
	private int cols;

	/**
	 * La grille liee a cette UI
	 */
	private Grid grid;

	/**
	 * Hauteur de la grille
	 */
	private int height;

	/**
	 * Hauteur d'une case
	 */
	private int htOfRow;

	/**
	 * Nombre de lignes
	 */
	private int rows;

	/**
	 * Largeur d'une case
	 */
	private int wdOfRow;

	/**
	 * Largeur de la grille
	 */
	private int width;

	/**
	 * Constructeur. Definit la largeur, la hauteur, le nombre de lignes et de colonnes.
	 * @param w Largeur souhaitee
	 * @param h Hauteur souhaitee
	 * @param r Nombre de lignes souhaite
	 * @param c Nombre de colonnes souhaite
	 */
	public GridUI(int w, int h, int r, int c)
	{
		this.setPreferredSize(new Dimension(w, h));
		width = w;
		height = h;
		rows = r;
		cols = c;
	}

	/**
	 * Constructeur. Dessine la grille a partir d'un objet.
	 * @param grille La grille que l'on doit dessiner
	 */
	public GridUI(Grid grille)
	{
		this.setPreferredSize(new Dimension(400, 400));
		this.grid = grille;
		rows = grille.getNbLines();
		cols = grille.getNbColumns();
	}

	/**
	 * Dessine les obstacles sur la grille
	 * @param g Objet graphique a utiliser pour le dessin
	 */
	private void fillObstacles(Graphics g)
	{
		int x,y;

		for(x=0; x<rows; x++)
		{
			for(y=0; y<cols; y++)
			{
				if(grid.getValue(x,y) >= 500)
				{
					// Signature : g.fillRect(x, y, width, height)
					g.fillRect(y * htOfRow, x * wdOfRow, wdOfRow, htOfRow);
				}
			}
		}
	}

	/**
	 * @return la hauteur d'une ligne
	 */
	public int getHtOfRow() {
		return htOfRow;
	}

	/**
	 * @return la largeur d'une ligne
	 */
	public int getWdOfRow() {
		return wdOfRow;
	}

	@Override
	/**
	 * Dessine la grille : les lignes puis les obstacles
	 */
	public void paint(Graphics g) {

		g.clearRect(this.getX(), this.getY(), width, height);

		paintLines(g);
		fillObstacles(g);

	}

	/**
	 * Dessine les lignes de la grille
	 * @param g Objet graphique a utiliser pour le dessin
	 */
	private void paintLines(Graphics g)
	{
		int k;
		width = this.getPreferredSize().width;
		height = this.getPreferredSize().height;

		htOfRow = height / (rows); // Definition de la hauteur d'une ligne

		for (k = 0; k <= rows; k++) {
			g.drawLine(0, k * htOfRow, width, k * htOfRow);
		}

		wdOfRow = width / (cols); // Definition de la largeur d'une ligne

		for (k = 0; k <= cols; k++) {
			g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
		}
	}

}
