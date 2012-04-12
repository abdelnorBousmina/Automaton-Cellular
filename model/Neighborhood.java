/**
 * 
 */
package model;

/**
 * @author albin
 *
 */
public class Neighborhood {
	
	public static final Integer NB_LIGNES = 3;
	public static final Integer NB_COLONNES = 3;
	
	/**
	 * Le voisinage
	 */
	private Float[][] voisinage;
	
	/**
	 * La personne dont on considère le voisinage
	 */
	private Person person;
	
	/**
	 * Constructeur par défaut. Ne fait rien de particulier
	 */
	public Neighborhood()
	{
		
	}
	
	/**
	 * Constructeur. Ce constructeur construit le voisinage en fonction de la grille
	 * et de la personne passés en paramètres.
	 * @param person La personne dont on cherche le voisinage
	 */
	public Neighborhood(Person person)
	{
		this.person = person;
		construireVoisinage(person.getGrille());
	}
	
	public Float[][] getVoisinage()
	{
		return voisinage;
	}
	
	public Person getPerson()
	{
		return person;
	}
	
	/**
	 * Affiche le voisinage dans la console
	 */
	public void afficherNeighborhood()
	{
		int ligne,colonne;
		
		for(ligne=0; ligne < NB_LIGNES; ligne++)
		{
			for(colonne=0; colonne < NB_COLONNES; colonne++)
			{
				System.out.print(voisinage[ligne][colonne]);
				System.out.print("     ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Construit le voisinage en fonction de l'attribut person
	 * @param grille La grille dans laquelle on recherche le voisinage
	 */
	public void construireVoisinage(Grille grille)
	{
		int ligne,colonne;
		voisinage = new Float[NB_LIGNES][NB_COLONNES];
		
		for(ligne=0; ligne < NB_LIGNES; ligne++)
		{
			for(colonne=0; colonne < NB_COLONNES; colonne++)
			{
				voisinage[ligne][colonne] = 
						grille.getValue(person.getX() + (ligne - 1), person.getY() + (colonne - 1));
				
			}
		}
	}
	
	/**
	 * Retourne la distance de la sortie de la case (x,y)
	 * @param x Coordonnée en x de la case
	 * @param y Coordonnée en y de la case
	 * @return La distance de la sortie par rapport à la case (x,y) ou -1 si la case n'existe pas
	 */
	public Float getValue(Integer x, Integer y)
	{
		Float retour = -1.0f;
		
		if(x <= NB_LIGNES && y <= NB_COLONNES)
		{
			retour = voisinage[x][y];
		}
		
		return retour;
		
	}

}
