/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.Grille;
import controlleur.Controlleur;
import model.Obstacle;

/**
 * @author bous
 *
 */
public class MainWindow {
	
	private JFrame frame;
	private JPanel uiPanel;
	private JPanel backgroundPanel;
	private JPanel buttonsPanel;
	private JPanel sizePanel;
	private JPanel nbEntryPanel;
	private JPanel posEntryPanel;
	private JPanel obsAndPersPanel;
	private Controlleur controlleur;
	private JButton start; 
	private JButton stop;
	private JButton restart;
	private JButton confirm;
	private JFormattedTextField fieldLambda;
	private JLabel lblLambda;
	private JTextField fieldNbEntries;
	private JLabel lblNbEntries;
	private JTextField fieldObstacles;
	private JLabel lblObstacles;
	private JTextField fieldPersonnes;
	private JLabel lblPersonnes;
	private JCheckBox newFigure;
	private JLabel lblnewFigure;
	private int nbEntries;
	private int tabX[]; 
	private int tabY[];
	private int nbLigneGrille = 11;
	private int nbColGrille = 11;
	private final int SIZE_MAX = 15;
	private JComboBox cbNbLigne;
	private JComboBox cbNbCol;
	private JLabel lblSize;
	private ArrayList<JComboBox> tabCombobox ;
	private float lambda = 1.5f;
	private ChartLine chartline;
	private List<Integer[]> personnes;
	private List<Obstacle> obstacles; 
	
	private final String patternObstacle = "([0-9]+),([0-9]+);([0-9]+),([0-9]+)";
	private final String patternPersonne = "([0-9]+),([0-9]+)";
	private final String patternEntries = "([0-9]+),([0-9]+)";
	
	public MainWindow()
	{
		/**
		 * Initialisation des elements graphiques
		 */

		// Définition de la frame
		frame = new JFrame("Automate cellulaire");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1100, 600));
		frame.setMinimumSize(new Dimension(1100, 600));
		frame.setResizable(false);

		// Création du panel contenant la grille
		backgroundPanel = new JPanel();	
		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.setBorder(new EmptyBorder(30,30,30,30));
		backgroundPanel.setPreferredSize(new Dimension(500,500));

		// Panel user interface
		uiPanel = new JPanel();
		uiPanel.setLayout(new GridLayout(5,1,5,5));
		uiPanel.setBorder(new EmptyBorder(30,5,30,5));
		uiPanel.setPreferredSize(new Dimension(500, 600));
		uiPanel.setMinimumSize(new Dimension(500, 600));
		
		// Panel choix taille grille
		sizePanel = new JPanel();
		
		// Panel choix obstacles et personnes
		obsAndPersPanel = new JPanel();
		obsAndPersPanel.setLayout(new GridLayout(3,2));
	//	obsAndPersPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		// Panel choix nombre d'entrée
		nbEntryPanel = new JPanel();
		nbEntryPanel.setLayout(new GridLayout(3,2));
	//	nbEntryPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		// Panel choix du positionnement des entrées
		posEntryPanel = new JPanel();
		posEntryPanel.setLayout(new GridLayout(0,4));
	//	posEntryPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		// Panel boutons
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3,3,5,5));
	//	buttonsPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		// Boutons
		start = new JButton("start");
		stop = new JButton("stop");
		restart = new JButton("restart");	
		confirm = new JButton("confirm");
		
		start.setEnabled(false);
		restart.setEnabled(false);
		stop.setEnabled(false);
				
		buttonsPanel.add(confirm);
		buttonsPanel.add(start);
		buttonsPanel.add(stop);
		buttonsPanel.add(restart);

		fieldNbEntries = new JTextField();
		fieldNbEntries.setText("[1,0]");
		lblNbEntries = new JLabel("Nombre d'entrées: ");
		lblNbEntries.setLabelFor(fieldNbEntries);
		
		NumberFormat percentFormat = NumberFormat.getNumberInstance();
        percentFormat.setMinimumFractionDigits(2);
		fieldLambda = new JFormattedTextField(percentFormat);
		fieldLambda.setValue(lambda);
		lblLambda = new JLabel("Coût déplacement diagonal: ");
		lblLambda.setLabelFor(lblLambda);
				
		newFigure = new JCheckBox();
		newFigure.setSelected(true);
		newFigure.setEnabled(false);
		lblnewFigure = new JLabel("Ouvrir un nouveau graphique");
		lblnewFigure.setLabelFor(newFigure);
		
		nbEntryPanel.add(lblLambda);
		nbEntryPanel.add(fieldLambda);
		nbEntryPanel.add(lblNbEntries);
		nbEntryPanel.add(fieldNbEntries);
		nbEntryPanel.add(lblnewFigure);
		nbEntryPanel.add(newFigure);
		
		// taille de la grille 
		Integer[] size = new Integer[SIZE_MAX];
		
		for(int i = 1; i <= SIZE_MAX ; i++) size[i - 1] = i;
		
		cbNbLigne = new JComboBox(size);
		cbNbCol = new JComboBox(size);
		lblSize = new JLabel("Taille de grille");
		cbNbLigne.setSelectedIndex(nbLigneGrille-1);
		cbNbCol.setSelectedIndex(nbColGrille-1);
		
		JLabel lblligne = new JLabel("lignes");
		JLabel lblcol = new JLabel("colonnes");
		
		sizePanel.add(lblSize);	
		sizePanel.add(cbNbLigne);
		sizePanel.add(lblligne);
		sizePanel.add(cbNbCol);
		sizePanel.add(lblcol);
		
		// Obstacles et Personnes
		lblObstacles = new JLabel("Obstacles ([xDebut,yDebut;xFin,yFin]");
		lblPersonnes = new JLabel("Personnes ([x,y])");
		fieldObstacles = new JTextField();
		
		fieldObstacles.setText("[2,2;4,2][7,2;8,2][2,4;4,4][7,4;8,4][2,6;4,6][7,6;8,6][5,8;6,8]");	
		fieldObstacles.setMaximumSize(new Dimension(50, 30));
		fieldPersonnes = new JTextField();
		fieldPersonnes.setText("[2,1][3,1][4,1][7,1][8,1][2,3][3,3][4,3][7,3][8,3][2,5][3,5][4,5][7,5][8,5][6,9]");
		lblObstacles.setLabelFor(fieldObstacles);
		lblPersonnes.setLabelFor(fieldPersonnes);
		
		obsAndPersPanel.add(lblObstacles);
		obsAndPersPanel.add(fieldObstacles);
		obsAndPersPanel.add(lblPersonnes);
		obsAndPersPanel.add(fieldPersonnes);
		
		uiPanel.add(sizePanel);
		uiPanel.add(obsAndPersPanel);
		uiPanel.add(nbEntryPanel);
		uiPanel.add(posEntryPanel);
		uiPanel.add(buttonsPanel);
		
		// Ajout du panel à la frame + dessin + affichage
		frame.add("Center",backgroundPanel);
		frame.add("East",uiPanel);
		
		frame.pack();
		frame.setVisible(true);

		/**
		 * Definition des actions liées aux boutons
		 */
				
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {						
				
				nbLigneGrille = cbNbLigne.getSelectedIndex() + 1;
				nbColGrille = (Integer) cbNbCol.getItemAt(cbNbCol.getSelectedIndex());
				
				setEntries();
				setPersonnes();
				setObstacles();
				if(areValidObstacles())
				{
					if(areValidPersonnes())
					{
						if(isValidEntry())
						{					
							posEntryPanel.removeAll();
							if(controlleur != null){
								backgroundPanel.remove(controlleur.getDrawArea());
							}
							
							lambda = Float.parseFloat(fieldLambda.getText().replace(",","."));
							
							if(newFigure.isSelected())
							{
								chartline = new ChartLine("Sortie des personnes");
							}
							Grille grille = new Grille(nbLigneGrille, nbColGrille, tabX, tabY, lambda, obstacles);
							controlleur = new Controlleur(grille, personnes ,chartline);	
							backgroundPanel.add("Center",controlleur.getDrawArea());
							frame.repaint();
							frame.pack();					
							start.setEnabled(true);
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Entrée(s) invalide(s)");
						}
					}else{
						JOptionPane.showMessageDialog(frame, "Personne(s) invalide(s)");
					}
				}else
				{
					JOptionPane.showMessageDialog(frame, "Obstacle(s) invalide(s)");					
				}
			}

		});
		
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				controlleur.startSimulation();
				stop.setEnabled(true);
				start.setEnabled(false);
				restart.setEnabled(true);
				confirm.setEnabled(false);
				newFigure.setEnabled(true);
			}

		});

		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlleur.stopSimulation();
				start.setEnabled(true);
				stop.setEnabled(false);
				confirm.setEnabled(true);
			}

		});

		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {			
				stop.setEnabled(false);				

				backgroundPanel.remove(controlleur.getDrawArea());
				if(newFigure.isSelected())
				{
					chartline = new ChartLine("Sortie des personnes");
				}
				Grille grille = new Grille(nbLigneGrille, nbColGrille, tabX, tabY, lambda, obstacles);
				controlleur = new Controlleur(grille,personnes,chartline);
				backgroundPanel.add("Center",controlleur.getDrawArea());

				frame.repaint();
				frame.pack();
				start.setEnabled(true);
				confirm.setEnabled(true);
			}

		});

	}
	private boolean areValidObstacles() {
		boolean answer = true;
		for(Obstacle o : obstacles)
		{
			if(o.getxDebut() <= 0 || o.getxDebut() >= nbLigneGrille -1)
			{
				answer = false;
			}
			if(o.getyDebut() <= 0 || o.getyDebut() >= nbColGrille - 1)
			{
				answer = false;
			}
			if(o.getxFin() <= 0 || o.getxFin() >= nbLigneGrille -1)
			{
				answer = false;
			}
			if(o.getyFin() <= 0 || o.getyFin() >= nbColGrille - 1)
			{
				answer = false;
			}
			
		}
		return answer;
	}

	private boolean areValidPersonnes() {
		boolean answer = true;
		int size = personnes.size();
		for(int i = 0; i < size ; i++)
		{
			if(personnes.get(i)[0] < 1 || personnes.get(i)[0] >= nbLigneGrille - 1)
				answer = false;
			if(personnes.get(i)[1] < 1 || personnes.get(i)[1] >= nbColGrille - 1)
				answer = false;
			for(Obstacle o : obstacles)
			{
				//TODO obstacle != personne
				if((personnes.get(i)[0] == o.getxDebut() || personnes.get(i)[0] == o.getxFin() ) && personnes.get(i)[1] >= o.getyDebut() && personnes.get(i)[1] <= o.getyFin())
				{
					answer = false;
				}
				if((personnes.get(i)[1] == o.getyDebut() || personnes.get(i)[1] == o.getyFin() ) && personnes.get(i)[0] >= o.getxDebut() && personnes.get(i)[0] <= o.getxFin())
				{
					answer = false;
				}
			}
			for(int j = 0 ; j < size ; j++)
			{
				if( i != j)
				{
					if(personnes.get(i)[0] == personnes.get(j)[0] && personnes.get(i)[1] == personnes.get(j)[1] )
						answer = false;
				}
			}
		}
		
		return answer;
	}
	
	private void setObstacles() {		
		List<Obstacle> obstacles = new ArrayList<Obstacle>();
		
		String obstacle = fieldObstacles.getText();
		
		Pattern p = Pattern.compile(patternObstacle);
		Matcher m = p.matcher(obstacle);
		while (m.find())
		{
			obstacles.add(new Obstacle(Integer.parseInt(m.group(1)),
					Integer.parseInt(m.group(2)),
					Integer.parseInt(m.group(3)),
					Integer.parseInt(m.group(4))));
			/*System.out.println("["+Integer.parseInt(m.group(1)) +"," +
					Integer.parseInt(m.group(2)) + ";" +
					Integer.parseInt(m.group(3)) + "," +
					Integer.parseInt(m.group(4))+ "]");*/
			
		}		
		this.obstacles = obstacles;
	}

	private void setPersonnes() {
		
		List<Integer[]> listPersonnes = new ArrayList<Integer[]>();
		
		String personne = fieldPersonnes.getText();
		
		Pattern p = Pattern.compile(patternPersonne);
		Matcher m = p.matcher(personne);
		while (m.find())
		{
			Integer[] person = new Integer[2];			
			person[0] = Integer.parseInt(m.group(1));
			person[1] = Integer.parseInt(m.group(2));
			listPersonnes.add(person);
		}	
		
		this.personnes = listPersonnes;
	}
	
	private void setEntries() {
		
		List<Integer[]> listEntries = new ArrayList<Integer[]>();
		
		String entries = fieldNbEntries.getText();
		
		Pattern p = Pattern.compile(patternEntries);
		Matcher m = p.matcher(entries);
		while (m.find())
		{
			Integer[] entry = new Integer[2];			
			entry[0] = Integer.parseInt(m.group(1));
			entry[1] = Integer.parseInt(m.group(2));
			listEntries.add(entry);
		}	
		
		tabX = new int[listEntries.size()];
		tabY = new int[listEntries.size()];
		int i = 0;
		for(Integer[] entry : listEntries)
		{
			tabX[i] = entry[0];
			tabY[i] = entry[1];
			i++;
		}
		
	}

	/**
	 * Permet de s'assurer que les entrées se situent à des endroits cohérents
	 * @return true si les entrées sont cohérentes
	 */
	private boolean isValidEntry()
	{
		boolean answer = true;
		for(int i = 0; i < tabX.length; i++)
		{
			// coin haut gauche
			if(tabX[i] == 0 && tabY[i] == 0)
			{
				answer = false;
			}
			// coin haut droit
			if(tabX[i] == 0 && tabY[i] == (nbColGrille - 1))
			{
				answer = false;
			}
			// coin bas droit
			if(tabX[i] == (nbLigneGrille-1) && tabY[i] == nbColGrille - 1)
			{
				answer = false;
			}
			// coin bas gauche
			if(tabX[i] == (nbLigneGrille-1) && tabY[i] == 0)
			{
				answer = false;
			}
			
			// centre
			if(tabX[i] != 0 && tabX[i] != nbLigneGrille-1 && tabY[i] != 0 && tabY[i] != nbColGrille-1) 
			{
				answer = false;
			}
		}
		return answer;
	}
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainWindow mw = new MainWindow();
	}

}
