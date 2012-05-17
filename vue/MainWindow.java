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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import controlleur.Controlleur;


/**
 * @author bous
 *
 */
public class MainWindow {
	
	private JFrame frame;
	private JPanel uiPanel;
	private JPanel backgroundPanel;
	private JPanel buttonsPanel;
	private JPanel nbEntryPanel;
	private JPanel posEntryPanel;
	private Controlleur controlleur;
	private JButton start; 
	private JButton stop;
	private JButton restart;
	private JButton confirm;
	private JFormattedTextField fieldLambda;
	private JLabel lblLambda;
	private JFormattedTextField fieldNbEntries;
	private JLabel lblNbEntries;
	private JCheckBox newFigure;
	private JLabel lblnewFigure;
	private int nbEntries;
	private int tabX[]; 
	private int tabY[];
	private int nbLigneGrille = 11;
	private int nbColGrille = 11;
	private ArrayList<JComboBox> tabCombobox ;
	private float lambda = 1.5f;
	private ChartLine chartline;
	
	
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

		// Panel boutons
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3,3,5,5));
		buttonsPanel.setBorder(new EmptyBorder(30,30,30,30));

		// Panel choix nombre d'entrée
		nbEntryPanel = new JPanel();
		nbEntryPanel.setLayout(new GridLayout(3,2,5,5));
		nbEntryPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		// Panel choix du positionnement des entrées
		posEntryPanel = new JPanel();
		posEntryPanel.setLayout(new GridLayout(0,4,5,5));
		posEntryPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		// Panel user interface
		uiPanel = new JPanel();
		uiPanel.setLayout(new BorderLayout());
		uiPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		// Boutons
		start = new JButton("start");
		stop = new JButton("stop");
		restart = new JButton("restart");	
		confirm = new JButton("confirm");
		
		confirm.setEnabled(false);
		start.setEnabled(false);
		restart.setEnabled(false);
		stop.setEnabled(false);
				
		buttonsPanel.add(confirm);
		buttonsPanel.add(start);
		buttonsPanel.add(stop);
		buttonsPanel.add(restart);

		fieldNbEntries = new JFormattedTextField(createFormatter("#"));
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
		
		uiPanel.add("North",nbEntryPanel);
		uiPanel.add("Center",posEntryPanel);
		uiPanel.add("South",buttonsPanel);
		
		// Ajout du panel à la frame + dessin + affichage
		frame.add("Center",backgroundPanel);
		frame.add("East",uiPanel);
		
		frame.pack();
		frame.setVisible(true);

		/**
		 * Definition des actions liées aux boutons
		 */
		
		fieldNbEntries.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				nbEntries = Integer.parseInt(fieldNbEntries.getText());
				//System.out.print(nbEntries);
				ArrayList<JLabel> tabLabel = new ArrayList<JLabel>();
				
				tabCombobox = new ArrayList<JComboBox>();
				
				Integer[] choixLigne = new Integer[nbLigneGrille];
				Integer[] choixColonne = new Integer[nbColGrille];
				
				for(int i = 0; i < nbLigneGrille ; i++) choixLigne[i] = i + 1;
				for(int i = 0; i < nbColGrille ; i++) choixColonne[i] = i + 1;
				
				for(int i = 0; i < nbEntries;i++)
				{
					JLabel lblLigne = new JLabel("n° ligne entrée " + (i+1) + ": ");
					JComboBox choixNumLigne = new JComboBox(choixLigne);
					choixNumLigne.setPreferredSize(new Dimension(20,5));
					JLabel lnlColonne = new JLabel("n° colonne entrée " + (i+1) +": ");
					JComboBox choixNumColonne = new JComboBox(choixColonne);
					choixNumColonne.setPreferredSize(new Dimension(20,5));
					
					tabLabel.add(lblLigne);
					tabLabel.add(lnlColonne);
					tabCombobox.add(choixNumLigne);
					tabCombobox.add(choixNumColonne);
				}
				
				posEntryPanel.removeAll();
				
				Iterator<JLabel> it = tabLabel.iterator();
				Iterator<JComboBox> it1 = tabCombobox.iterator();
				JLabel lbl;
				JComboBox cb;
				while(it.hasNext())
				{
					lbl = it.next();
					cb = it1.next();
					posEntryPanel.add(lbl);
					posEntryPanel.add(cb);
					
				}			
				confirm.setEnabled(true);
				frame.pack();
				frame.repaint();
			}
		});
		
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {						
				
				tabX = new int[nbEntries];
				tabY = new int[nbEntries];
				
				Iterator<JComboBox> it = tabCombobox.iterator();
				JComboBox cb;
				int i = 0;
				while(it.hasNext())
				{
					cb = it.next();
					tabX[i] = cb.getSelectedIndex();				
					cb = it.next();
					tabY[i] = cb.getSelectedIndex();
					
					i++;
				}
				
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
					controlleur = new Controlleur(tabX, tabY,nbLigneGrille,nbColGrille, lambda,chartline);	
					backgroundPanel.add("Center",controlleur.getDrawArea());
					frame.repaint();
					frame.pack();					
					start.setEnabled(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Entrée(s) invalide(s)");
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
				controlleur = new Controlleur(tabX, tabY, nbLigneGrille, nbColGrille, lambda,chartline);
				backgroundPanel.add("Center",controlleur.getDrawArea());

				frame.repaint();
				frame.pack();
				start.setEnabled(true);
				confirm.setEnabled(true);
			}

		});

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
