/**
 * 
 */
package model;

/**
 * @author albin
 *
 */
public class Neighborhood {
	
	private Integer nbLignes = 3;
	private Integer nbColonnes = 3;
	private Integer[][] voisinage;
	
	
	// Constructeurs
	public Neighborhood(Grille grille, Person person)
	{
		int ligne,colonne;
		voisinage = new Integer[nbLignes][nbColonnes];
		
		for(ligne=0; ligne < nbLignes; ligne++)
		{
			for(colonne=0; colonne < nbColonnes; colonne++)
			{
				/*voisinage[ligne][colonne] = 
						grille.getValue(person.getX() - (ligne - 1), person.getY() - (colonne - 1));*/
			}
		}
	}
	
	// Getters & setters
	public Integer[][] getVoisinage()
	{
		return voisinage;
	}
	
	public Integer getNbLignes()
	{
		return nbLignes;
	}
	
	public Integer getNbColonnes()
	{
		return nbColonnes;
	}
	
	// Méthodes
	
	/**
	 * Retourne la distance de la sortie de la case (x,y)
	 * @param x Coordonnée en x de la case
	 * @param y Coordonnée en y de la case
	 * @return La distance de la sortie par rapport à la case (x,y) ou -1 si la case n'existe pas
	 */
	public Integer getValue(Integer x, Integer y)
	{
		Integer retour = -1;
		
		if(x >= nbLignes && y >= nbColonnes)
		{
			retour = voisinage[x][y];
		}
		
		return retour;
		
	}

}
