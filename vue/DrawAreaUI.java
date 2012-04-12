package vue;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Grille;
import model.MathModel;
import model.Neighborhood;
import model.Person;

public class DrawAreaUI extends JPanel {

	/**
	 * Automatically added
	 */
	private static final long serialVersionUID = 1L;
	
	private Grille grille;
	private GrilleUI grilleUi;
	private ArrayList<PersonUI> personsUi;
	
	private Neighborhood nbh;

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille gr) {
		this.grille = gr;
	}

	public GrilleUI getGrilleUi() {
		return grilleUi;
	}

	public void setGrilleUi(GrilleUI grilleUi) {
		this.grilleUi = grilleUi;
	}

	public Neighborhood getNbh() {
		return nbh;
	}

	public void setNbh(Neighborhood nbh) {
		this.nbh = nbh;
	}
	
	public ArrayList<PersonUI> getPersonsUi() {
		return personsUi;
	}

	public void setPersonsUi(ArrayList<PersonUI> personsUi) {
		this.personsUi = personsUi;
	}

	public DrawAreaUI() 
	{
		personsUi = new ArrayList<PersonUI>();
	}
	
	public void updateItems()
	{
		this.repaint();
	}
	
	public void paintGrille()
	{
		grilleUi.setVisible(true);
	}
	
	/**
	 * Custom painting
	 */
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("dau : paintComponent");
		grilleUi.paint(g);
		int i = 0;
		for (PersonUI p : personsUi) {
			System.out.println("\t i : " + i++);
			p.updatePosition();
			p.paint(g);
		}
	}
	
	public void addPersonUi(PersonUI pUi)
	{
		pUi.setVisible(true);
		personsUi.add(pUi);
	}

}
