package view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Grid;
import model.Neighborhood;

public class DrawAreaUI extends JPanel {

	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * La grille contenue dans cette zone de dessin
	 */
	private Grid grid;

	/**
	 * La partie graphique de la grille contenue dans cette zone de dessin
	 */
	private GridUI gridUi;

	/**
	 * Liste des parties graphiques des personnes a dessiner
	 */
	private ArrayList<PersonUI> personsUi;

	/**
	 * Un voisinage
	 */
	private Neighborhood nbh;

	/**
	 * Constructeur par defaut. Instancie une nouvelle liste de personnes
	 * a dessiner.
	 */
	public DrawAreaUI() 
	{
		personsUi = new ArrayList<PersonUI>();
	}


	/**
	 * Ajoute la partie graphique d'une personne a dessiner dans liste
	 * @param pUi La partie graphique de la personne a ajouter
	 */
	public void addPersonUi(PersonUI pUi)
	{
		pUi.setVisible(true);
		personsUi.add(pUi);
	}

	/**
	 * @return la grille
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @return la partie graphique grille
	 */
	public GridUI getGridUi() {
		return gridUi;
	}

	/**
	 * @return le voisinage
	 */
	public Neighborhood getNbh() {
		return nbh;
	}

	/**
	 * @return la liste des personnes a dessiner
	 */
	public ArrayList<PersonUI> getPersonsUi() {
		return personsUi;
	}

	/**
	 * Custom painting. Repaint la grille, puis mets a jour les positions
	 * des parties graphiques des personnes a dessiner, puis les dessine.
	 */
	@Override
	public void paintComponent(Graphics g) {
		gridUi.paint(g);

		for (PersonUI p : personsUi) {
			p.updatePosition();
			p.paint(g);
		}
	}

	/**
	 * Affiche la grille
	 */
	public void paintGrid()
	{
		gridUi.setVisible(true);
	}

	/**
	 * Retire la partie graphique d'une personne a dessiner dans liste
	 * @param pUi La partie graphique de la personne a retirer
	 */
	public void removePersonUi(PersonUI pUi)
	{
		pUi.setVisible(false);
		personsUi.remove(pUi);
	}

	/**
	 * @param grille la grille
	 */
	public void setGrid(Grid grille) {
		this.grid = grille;
	}

	/**
	 * @param grilleUi la partie graphique grille a utiliser
	 */
	public void setGridUi(GridUI grilleUi) {
		this.gridUi = grilleUi;
	}

	/**
	 * @param nbh le voisinage a utiliser
	 */
	public void setNbh(Neighborhood nbh) {
		this.nbh = nbh;
	}

	/**
	 * @param personsUi la liste des personnes a dessiner a utiliser
	 */
	public void setPersonsUi(ArrayList<PersonUI> personsUi) {
		this.personsUi = personsUi;
	}

	/**
	 * Redessine toutes les personnes a dessiner
	 */
	public void updateItems()
	{
		this.repaint();
	}	
}
