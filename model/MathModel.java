package model;


public class MathModel {

	/**
	 * Probabilite de panique des personnes dans le systeme
	 */
	private static Double panic = 0.05;

	/**
	 * Definit la prochaine position dans le voisinage de la case centrale du voisinage
	 * @param voisinage Le voisinage de la personne qui doit se deplacer
	 * @return Un tableau de la forme :
	 * 		tab[0] la valeur a ajouter a x pour atteindre la prochaine position
	 *		tab[1] la valeur a ajouter a y pour atteindre la prochaine position
	 */
	public Integer[] move(Neighborhood voisinage)
	{
		Integer[] retour = new Integer[2];
		int[] miniPos = new int[2];

		if(panic.compareTo(Math.random()) < 0)
		{		
			miniPos = voisinage.getMinPosition();
		}
		else
		{
			miniPos[0] = 1;
			miniPos[1] = 1;
			//System.out.println("panic :s :s :s :s");
		}

		retour[0] = miniPos[0] - 1;
		retour[1] = miniPos[1] - 1;

		return retour;

	}
}
