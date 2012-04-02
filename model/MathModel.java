package model;


public class MathModel {
	
	/**
	 * Probabilité de panique des personnes dans le système
	 */
	private static Float panic = 0.05f;

	/**
	 * Définit la prochaine position dans le voisinage de la case centrale du voisinage
	 * @param voisinage Le voisinage de la personne qui doit se déplacer
	 * @return Un tableau de la forme :
	 * 		tab[0] la valeur à ajouter à x pour atteindre la prochaine position
	 *		tab[1] la valeur à ajouter à y pour atteindre la prochaine position
	 */
	public Integer[] bouger(Neighborhood voisinage)
	{
		Integer nextX = 0, nextY = 0, ligne, colonne;
		Integer[] retour = new Integer[2];
		
		if(Math.random() >= panic)
		{
			// Parcours du voisinage
			for (ligne = 0; ligne < voisinage.getNbLignes(); ligne++) 
			{
				
				for (colonne = 0; colonne < voisinage.getNbColonnes(); colonne++) 
				{
					// Si la case évaluée est plus proche de la sortie que la destination
					// déjà choisie, la prochaine case est la case évaluée
					// En cas d'égalité, un choix aléatoire est effectué
					
					System.out.print("(" + voisinage.getValue(ligne, colonne) + ") ");
					
					if(voisinage.getValue(ligne, colonne) < voisinage.getValue(nextX, nextY))
					{
						nextX = ligne;
						nextY = colonne;
					}
					else if(voisinage.getValue(ligne, colonne) == voisinage.getValue(nextX, nextY))
					{
						// 1 chance sur 2 d'aller finalement en (ligne, colonne)
						if(Math.random() <= 0.50f)
						{
							nextX = ligne;
							nextY = colonne;
						}
					} // Fin détermination next	
				
				}// Fin parcours des colonnes
				
			} // Fin du parcours des lignes
		
		}
		else
		{
			// TODO Implémenter le comportement de panique 
		}
		
		retour[0] = nextX - 1;
		retour[1] = nextY - 1;
		
		System.out.println();
		
		return retour;
		
	}
}
