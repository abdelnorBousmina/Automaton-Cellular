package vue;

import java.awt.Graphics;

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
	
	private MathModel model;
	private Grille gr;
	private GrilleUI gUi;
	private Neighborhood nbh;
	private Person person1;
	
	public MathModel getModel() {
		return model;
	}

	public void setModel(MathModel model) {
		this.model = model;
	}

	public Grille getGr() {
		return gr;
	}

	public void setGr(Grille gr) {
		this.gr = gr;
	}

	public GrilleUI getgUi() {
		return gUi;
	}

	public void setgUi(GrilleUI gUi) {
		this.gUi = gUi;
	}

	public Neighborhood getNbh() {
		return nbh;
	}

	public void setNbh(Neighborhood nbh) {
		this.nbh = nbh;
	}

	public DrawAreaUI() {
		person1 = new Person();
		person1.setX(6);
		person1.setY(4);
		
		gUi = new GrilleUI(gr);
		gUi.paint(this.getGraphics());
		
		/*person1.getUi().setgUi(gUi);
		person1.getUi().update(this.getGraphics());*/
	}
	
	public void updateItems()
	{
		this.repaint();
	}
	
	/**
	 * Custom painting
	 */
	@Override
	public void paintComponent(Graphics g) {
		person1.getUi().paint(g);
		/*for (Car car : road.getCarList()) {
			car.getImage().paint(g);
		}*/
	}

}
