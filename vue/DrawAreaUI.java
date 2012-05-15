package vue;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Grille;
import model.Neighborhood;

public class DrawAreaUI extends JPanel {

	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * La grille contenue dans cette zone de dessin
	 */
	private Grille grille;
	
	/**
	 * La partie graphique de la grille contenue dans cette zone de dessin
	 */
	private GrilleUI grilleUi;
	
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
	public Grille getGrille() {
		return grille;
	}
	
	/**
	 * @return la partie graphique grille
	 */
	public GrilleUI getGrilleUi() {
		return grilleUi;
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
		grilleUi.paint(g);
		
		for (PersonUI p : personsUi) {
			p.updatePosition();
			p.paint(g);
		}
	}
	
	/**
	 * Affiche la grille
	 */
	public void paintGrille()
	{
		grilleUi.setVisible(true);
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
	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	/**
	 * @param grilleUi la partie graphique grille à utiliser
	 */
	public void setGrilleUi(GrilleUI grilleUi) {
		this.grilleUi = grilleUi;
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
