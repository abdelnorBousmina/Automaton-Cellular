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
	 * Liste des parties graphiques des personnes à dessiner
	 */
	private ArrayList<PersonUI> personsUi;
	
	/**
	 * Un voisinage
	 */
	private Neighborhood nbh;

	/**
	 * Constructeur par défaut. Instancie une nouvelle liste de personnes
	 * à dessiner.
	 */
	public DrawAreaUI() 
	{
		personsUi = new ArrayList<PersonUI>();
	}
	

	/**
	 * Ajoute la partie graphique d'une personne à dessiner dans liste
	 * @param pUi La partie graphique de la personne à ajouter
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
	 * @return la liste des personnes à dessiner
	 */
	public ArrayList<PersonUI> getPersonsUi() {
		return personsUi;
	}
	
	/**
	 * Custom painting. Repaint la grille, puis mets à jour les positions
	 * des parties graphiques des personnes à dessiner, puis les dessine.
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
	 * Retire la partie graphique d'une personne à dessiner dans liste
	 * @param pUi La partie graphique de la personne à retirer
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
	 * @param grilleUi la partie graphique grille à utiliser
	 */
	public void setGridUi(GridUI grilleUi) {
		this.gridUi = grilleUi;
	}

	/**
	 * @param nbh le voisinage à utiliser
	 */
	public void setNbh(Neighborhood nbh) {
		this.nbh = nbh;
	}
	
	/**
	 * @param personsUi la liste des personnes à dessiner à utiliser
	 */
	public void setPersonsUi(ArrayList<PersonUI> personsUi) {
		this.personsUi = personsUi;
	}
	
	/**
	 * Redessine toutes les personnes à dessiner
	 */
	public void updateItems()
	{
		this.repaint();
	}	
}
