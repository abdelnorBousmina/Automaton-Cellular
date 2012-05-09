package model;

import java.util.ArrayList;
import java.util.List;

public class Grille {

	private float[][] grille;
	private int nbLignes;
	private int nbColonnes;
	/**
	 * @return the nbLignes
	 */
	public int getNbLignes() {
		return nbLignes;
	}

	/**
	 * @return the nbColonnes
	 */
	public int getNbColonnes() {
		return nbColonnes;
	}


	private int tabxExit[];
	private int tabyExit[];
	private final float lambda = 1.5f;
	private final int mur = 500;

	/**
	 * 
	 * @param lignes nombre de ligne
	 * @param colonnes nombre de colonne
	 * @param xExit numéro de ligne de la sortie
	 * @param yExit numéro de colonne de la sortie
	 */
	public Grille(int lignes,int colonnes,int letabxExit[],int letabyExit[])
	{
		nbLignes = lignes;
		nbColonnes = colonnes;
		this.tabxExit = letabxExit;
		this.tabyExit = letabyExit;		

		grille = new float[nbLignes][nbColonnes];

		List<Obstacle> obstacles = new ArrayList<Obstacle>();

		obstacles.add(new Obstacle(2, 2, 4, 2));
		obstacles.add(new Obstacle(7, 2, 8, 2));
		obstacles.add(new Obstacle(2, 4, 4, 4));
		obstacles.add(new Obstacle(7, 4, 8, 4));
		obstacles.add(new Obstacle(2, 6, 4, 6));
		obstacles.add(new Obstacle(7, 6, 8, 6));
		obstacles.add(new Obstacle(5, 8, 6, 8));

		// Init murs verticaux
		for(int i = 0; i < nbLignes; i++)
		{
			grille[i][0] = mur;
			grille[i][nbColonnes-1] = mur;
		}

		// Init murs horizontaux
		for(int j = 1; j < nbColonnes-1; j++)
		{
			grille[0][j] = mur;
			grille[nbLignes-1][j] = mur;
		}

		// Init reste de la grille
		for(int i = 1; i < nbLignes-1;i++)
		{
			for(int j = 1; j < nbColonnes-1 ; j++ )
				grille[i][j] = 400;
		}

		// Init sortie
		for(int i = 0; i < tabxExit.length; i++)
			grille[tabxExit[i]][tabyExit[i]] = 1;

		// Init obstacles
		for(Obstacle o:obstacles)
		{
			int xD = o.getxDebut();
			int xF = o.getxFin();
			int yD = o.getyDebut();
			int yF = o.getyFin();
			for(int i = xD; i <= xF;i++)
				for(int j = yD; j <= yF;j++)
					grille[i][j] = mur;
		}
		//TODO gérer les obstacles 
		for(int i = 0; i < tabxExit.length ; i++)
		{		
			if(tabyExit[i] == 0)
			{
				initGrilleHautDroite(tabxExit[i], tabyExit[i]);
				initGrilleBasDroite(tabxExit[i], tabyExit[i]);			
			}
			else
			{	
				if(tabyExit[i] == nbColonnes - 1)
				{
					initGrilleHautGauche(tabxExit[i], tabyExit[i]);
					initGrilleBasGauche(tabxExit[i],tabyExit[i]);
				}
				else if(tabxExit[i] == 0)
				{
					initGrilleBasGauche(tabxExit[i],tabyExit[i]);
					initGrilleBasDroite(tabxExit[i], tabyExit[i]);
				}
				else
				{
					initGrilleHautGauche(tabxExit[i], tabyExit[i]);
					initGrilleHautDroite(tabxExit[i], tabyExit[i]);
				}
			}
		}
	}

	/**
	 * @return the grille
	 */
	public float[][] getGrille() {
		return grille;
	}

	public float getValue(int x, int y)
	{
		if(x < 0 || y < 0 || x >= nbLignes || y >= nbColonnes)
		{
			return 500;
		}
		return grille[x][y];
	}

	/**
	 * Calcul les distances des cases positionnées au dessus et/ou à droite de la case actuelle
	 * @param x numéro de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGrilleHautDroite(int x,int y)
	{
		if(x > 0)
		{
			if(grille[x - 1][y] != mur)
			{
				if(grille[x - 1][y] > grille[x][y] + 1)
				{
					grille[x - 1][y] = grille[x][y] + 1;						
				}
				initGrilleHautDroite(x-1,y);				
			}			
			if(y < nbColonnes)
			{

				if(grille[x][y + 1] != mur)
				{		
					if(grille[x][y + 1] > grille[x][y] + 1)
					{
						grille[x][y + 1] = grille[x][y] + 1;
					}	
					initGrilleHautDroite(x,y+1);
				}
				if(grille[x - 1][y + 1] != mur)
				{					
					if(grille[x - 1][y + 1] > grille[x][y] + lambda)
					{
						grille[x - 1][y + 1] = grille[x][y] + lambda;
					}
					initGrilleHautDroite(x-1,y+1);
				}
				if(x < nbLignes - 1)
				{
					if(grille[x + 1][y + 1] != mur)
					{
						if(grille[x + 1][y + 1] > grille[x][y] + lambda)
						{
							grille[x + 1][y + 1] = grille[x][y] + lambda;
						}
						initGrilleHautDroite(x+1,y+1);					
					}
				}
			}
		}
	}

	/**
	 * Calcul les distances des cases positionnées en dessous et/ou à droite de la case actuelle
	 * @param x numéro de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGrilleBasDroite(int x, int y)
	{

		if(x < nbLignes)
		{
			if(grille[x + 1][y] != mur)
			{	
				if(grille[x + 1][y] > grille[x][y] + 1)
				{
					grille[x + 1][y] = grille[x][y] + 1;
				}
				initGrilleBasDroite(x+1,y);

			}
			if(y < nbColonnes)
			{
				if(grille[x + 1][y + 1] != mur)
				{
					if(grille[x + 1][y + 1] > grille[x][y] + lambda)
					{
						grille[x + 1][y + 1] = grille[x][y] + lambda;
					}
					initGrilleBasDroite(x+1,y+1);					
				}
				if(grille[x][y + 1] != mur)
				{		
					if(grille[x][y + 1] > grille[x][y] + 1)
					{
						grille[x][y + 1] = grille[x][y] + 1;
					}	
					initGrilleBasDroite(x,y+1);
				}
				if( x > 0)
				{
					if(grille[x - 1][y + 1] != mur)
					{					
						if(grille[x - 1][y + 1] > grille[x][y] + lambda)
						{
							grille[x - 1][y + 1] = grille[x][y] + lambda;
						}
						initGrilleBasDroite(x-1,y+1);
					}
				}
			}		

		}
	}

	/**
	 * Calcul les distances des cases positionnées au dessus et/ou à gauche de la case actuelle
	 * @param x numéro de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGrilleHautGauche(int x,int y)
	{
		if(x > 0)
		{
			if(grille[x - 1][y] != mur)
			{
				if(grille[x - 1][y] > grille[x][y] + 1)
				{
					grille[x - 1][y] = grille[x][y] + 1;						
				}
				initGrilleHautGauche(x-1,y);				
			}			
			if(y > 0)
			{

				if(grille[x][y - 1] != mur)
				{		
					if(grille[x][y - 1] > grille[x][y] + 1)
					{
						grille[x][y - 1] = grille[x][y] + 1;
					}	
					initGrilleHautGauche(x,y-1);
				}
				if(grille[x - 1][y - 1] != mur)
				{					
					if(grille[x - 1][y - 1] > grille[x][y] + lambda)
					{
						grille[x - 1][y - 1] = grille[x][y] + lambda;
					}
					initGrilleHautGauche(x-1,y-1);
				}
				if(y < nbColonnes - 1)
				{
					if(grille[x - 1][y + 1] != mur)
					{					
						if(grille[x - 1][y + 1] > grille[x][y] + lambda)
						{
							grille[x - 1][y + 1] = grille[x][y] + lambda;
						}
						initGrilleHautGauche(x-1,y+1);
					}
				}
			}
		}
	}

	/**
	 * Calcul les distances des cases positionnées en dessous et/ou à gauche de la case actuelle
	 * @param x numéro de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGrilleBasGauche(int x, int y)
	{

		if(x < nbLignes)
		{
			if(grille[x + 1][y] != mur)
			{	
				if(grille[x + 1][y] > grille[x][y] + 1)
				{
					grille[x + 1][y] = grille[x][y] + 1;
				}
				initGrilleBasGauche(x+1,y);

			}
			if(y > 0)
			{
				if(grille[x + 1][y - 1] != mur)
				{
					if(grille[x + 1][y - 1] > grille[x][y] + lambda)
					{
						grille[x + 1][y - 1] = grille[x][y] + lambda;
					}
					initGrilleBasGauche(x+1,y-1);					
				}
				if(grille[x][y - 1] != mur)
				{		
					if(grille[x][y - 1] > grille[x][y] + 1)
					{
						grille[x][y - 1] = grille[x][y] + 1;
					}	
					initGrilleBasGauche(x,y-1);
				}
				if( y < nbColonnes - 1)
				{
					if(grille[x+1][y + 1] != mur)
					{
						if(grille[x + 1][y + 1] > grille[x][y] + 1)
						{
							grille[x + 1][y + 1] = grille[x][y] + 1;
						}	
						initGrilleBasGauche(x+1,y+1);
					}
				}
			}		

		}
	}


	// Test
	public void afficherGrille()
	{
		for(int i = 0; i < nbLignes;i++)
		{
			for(int j = 0; j < nbColonnes; j++ )
			{
				System.out.print(grille[i][j]);
				System.out.print("     ");
			}	
			System.out.println();
		}
	}
}
