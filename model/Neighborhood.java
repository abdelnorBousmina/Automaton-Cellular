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
	private Float[][] voisinage;
	
	
	// Constructeurs
	public Neighborhood(Grille grille, Person person)
	{
		int ligne,colonne;
		voisinage = new Float[nbLignes][nbColonnes];
		
		for(ligne=0; ligne < nbLignes; ligne++)
		{
			for(colonne=0; colonne < nbColonnes; colonne++)
			{
				voisinage[ligne][colonne] = 
						grille.getValue(person.getX() + (colonne - 1), person.getY() + (ligne - 1));
				
			}
		}
	}
	
	// Getters & setters
	public Float[][] getVoisinage()
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
	public Float getValue(Integer x, Integer y)
	{
		Float retour = -1.0f;
		
		if(x <= nbLignes && y <= nbColonnes)
		{
			retour = voisinage[x][y];
		}
		
		return retour;
		
	}
	
	public void afficherNeighborhood()
	{
		int ligne,colonne;
		
		for(ligne=0; ligne < nbLignes; ligne++)
		{
			for(colonne=0; colonne < nbColonnes; colonne++)
			{
				System.out.print(voisinage[ligne][colonne]);
				System.out.print("     ");
			}
			System.out.println();
		}
	}

}
