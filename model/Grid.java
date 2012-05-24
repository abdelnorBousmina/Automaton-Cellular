package model;

import java.util.List;

public class Grid {

	/**
	 * La grille
	 */
	private float[][] grid;

	/**
	 * Le nombre de lignes de la grille
	 */
	private int nbLines;

	/**
	 * Le nombre de colonnes de la grille
	 */
	private int nbColumns;

	/**
	 * @return the nbLignes
	 */
	public int getNbLines() {
		return nbLines;
	}

	/**
	 * @return the nbColonnes
	 */
	public int getNbColumns() {
		return nbColumns;
	}

	private int tabxExit[];

	private int nbExit;
	/**
	 * @return the nbExit
	 */
	public int getNbExit() {
		return nbExit;
	}

	private int tabyExit[];
	private float lambda = 1.5f;
	private final int wall = 500;
	private final int exit = 1;

	/**
	 * Cree un tableau de float a 2 dimensions representant une salle
	 * @param lignes nombre de ligne
	 * @param colonnes nombre de colonne
	 * @param letabxExit les numeros de ligne des sorties
	 * @param letabyExit les numeros de colonne des sorties
	 * @param lambda la valeur de lambda
	 * @param obstacles la liste des obstacles
	 */
	public Grid(int lignes,int colonnes,int letabxExit[],int letabyExit[], float lambda, List<Obstacle> obstacles)
	{
		this.lambda = lambda;
		this.nbLines = lignes;
		this.nbColumns = colonnes;
		this.tabxExit = letabxExit;
		this.tabyExit = letabyExit;		
		this.nbExit = letabxExit.length;

		grid = new float[nbLines][nbColumns];

		// Init murs verticaux
		for(int i = 0; i < nbLines; i++)
		{
			grid[i][0] = wall;
			grid[i][nbColumns-1] = wall;
		}

		// Init murs horizontaux
		for(int j = 1; j < nbColumns-1; j++)
		{
			grid[0][j] = wall;
			grid[nbLines-1][j] = wall;
		}

		// Init reste de la grille
		for(int i = 1; i < nbLines-1;i++)
		{
			for(int j = 1; j < nbColumns-1 ; j++ )
				grid[i][j] = 400;
		}

		// Init sortie
		for(int i = 0; i < nbExit; i++)
			grid[tabxExit[i]][tabyExit[i]] = exit;

		// Init obstacles
		for(Obstacle o:obstacles)
		{
			int xD = o.getxStart();
			int xF = o.getxEnd();
			int yD = o.getyStart();
			int yF = o.getyEnd();
			for(int i = xD; i <= xF;i++)
				for(int j = yD; j <= yF;j++)
					grid[i][j] = wall;
		}
		for(int i = 0; i < tabxExit.length ; i++)
		{		
			if(tabyExit[i] == 0)
			{
				initGridTopRight(tabxExit[i], tabyExit[i]);
				initGridBottomRight(tabxExit[i], tabyExit[i]);			
			}
			else
			{	
				if(tabyExit[i] == nbColumns - 1)
				{
					initGrilleTopLeft(tabxExit[i], tabyExit[i]);
					initGridBottomLeft(tabxExit[i],tabyExit[i]);
				}
				else if(tabxExit[i] == 0)
				{
					initGridBottomLeft(tabxExit[i],tabyExit[i]);
					initGridBottomRight(tabxExit[i], tabyExit[i]);
				}
				else
				{
					initGrilleTopLeft(tabxExit[i], tabyExit[i]);
					initGridTopRight(tabxExit[i], tabyExit[i]);
				}
			}
		}
	}

	/**
	 * @return the grid
	 */
	public float[][] getGrid() {
		return grid;
	}

	public float getValue(int x, int y)
	{
		if(x < 0 || y < 0 || x >= nbLines || y >= nbColumns)
		{
			return 0;
		}
		return grid[x][y];
	}

	/**
	 * Calcul les distances des cases positionnees au dessus et/ou a droite de la case actuelle
	 * @param x numero de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGridTopRight(int x,int y)
	{
		if(x > 0)
		{
			if(grid[x - 1][y] != wall && grid[x - 1][y] != exit)
			{
				if(grid[x - 1][y] > grid[x][y] + 1)
				{
					grid[x - 1][y] = grid[x][y] + 1;						
				}
				initGridTopRight(x-1,y);				
			}			
			if(y < nbColumns)
			{

				if(grid[x][y + 1] != wall && grid[x][y + 1] != exit)
				{		
					if(grid[x][y + 1] > grid[x][y] + 1)
					{
						grid[x][y + 1] = grid[x][y] + 1;
					}	
					initGridTopRight(x,y+1);
				}
				if(grid[x - 1][y + 1] != wall && grid[x - 1][y + 1] != exit)
				{					
					if(grid[x - 1][y + 1] > grid[x][y] + lambda)
					{
						grid[x - 1][y + 1] = grid[x][y] + lambda;
					}
					initGridTopRight(x-1,y+1);
				}
				if(x < nbLines - 1)
				{
					if(grid[x + 1][y + 1] != wall && grid[x + 1][y + 1] != exit)
					{
						if(grid[x + 1][y + 1] > grid[x][y] + lambda)
						{
							grid[x + 1][y + 1] = grid[x][y] + lambda;
						}
						initGridTopRight(x+1,y+1);					
					}
				}
			}
		}
	}

	/**
	 * Calcul les distances des cases positionnees en dessous et/ou a droite de la case actuelle
	 * @param x numero de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGridBottomRight(int x, int y)
	{

		if(x < nbLines)
		{
			if(grid[x + 1][y] != wall && grid[x + 1][y] != exit)
			{	
				if(grid[x + 1][y] > grid[x][y] + 1)
				{
					grid[x + 1][y] = grid[x][y] + 1;
				}
				initGridBottomRight(x+1,y);

			}
			if(y < nbColumns)
			{
				if(grid[x + 1][y + 1] != wall && grid[x + 1][y + 1] != exit)
				{
					if(grid[x + 1][y + 1] > grid[x][y] + lambda)
					{
						grid[x + 1][y + 1] = grid[x][y] + lambda;
					}
					initGridBottomRight(x+1,y+1);					
				}
				if(grid[x][y + 1] != wall && grid[x][y + 1] != exit)
				{		
					if(grid[x][y + 1] > grid[x][y] + 1)
					{
						grid[x][y + 1] = grid[x][y] + 1;
					}	
					initGridBottomRight(x,y+1);
				}
				if( x > 0)
				{
					if(grid[x - 1][y + 1] != wall && grid[x - 1][y + 1] != exit)
					{					
						if(grid[x - 1][y + 1] > grid[x][y] + lambda)
						{
							grid[x - 1][y + 1] = grid[x][y] + lambda;
						}
						initGridBottomRight(x-1,y+1);
					}
				}
			}		

		}
	}

	/**
	 * Calcul les distances des cases positionnees au dessus et/ou a gauche de la case actuelle
	 * @param x numero de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGrilleTopLeft(int x,int y)
	{
		if(x > 0)
		{
			if(grid[x - 1][y] != wall && grid[x - 1][y] != exit)
			{
				if(grid[x - 1][y] > grid[x][y] + 1)
				{
					grid[x - 1][y] = grid[x][y] + 1;						
				}
				initGrilleTopLeft(x-1,y);				
			}			
			if(y > 0)
			{

				if(grid[x][y - 1] != wall && grid[x][y - 1] != exit)
				{		
					if(grid[x][y - 1] > grid[x][y] + 1)
					{
						grid[x][y - 1] = grid[x][y] + 1;
					}	
					initGrilleTopLeft(x,y-1);
				}
				if(grid[x - 1][y - 1] != wall && grid[x - 1][y - 1] != exit)
				{					
					if(grid[x - 1][y - 1] > grid[x][y] + lambda)
					{
						grid[x - 1][y - 1] = grid[x][y] + lambda;
					}
					initGrilleTopLeft(x-1,y-1);
				}
				if(y < nbColumns - 1)
				{
					if(grid[x - 1][y + 1] != wall && grid[x - 1][y + 1] != exit)
					{					
						if(grid[x - 1][y + 1] > grid[x][y] + lambda)
						{
							grid[x - 1][y + 1] = grid[x][y] + lambda;
						}
						initGrilleTopLeft(x-1,y+1);
					}
				}
			}
		}
	}

	/**
	 * Calcul les distances des cases positionnees en dessous et/ou a gauche de la case actuelle
	 * @param x numero de ligne de la case actuelle
	 * @param y indice de colonne de la actuelle
	 */
	private void initGridBottomLeft(int x, int y)
	{

		if(x < nbLines)
		{
			if(grid[x + 1][y] != wall && grid[x+1][y] != exit )
			{	
				if(grid[x + 1][y] > grid[x][y] + 1)
				{
					grid[x + 1][y] = grid[x][y] + 1;
				}
				initGridBottomLeft(x+1,y);

			}
			if(y > 0)
			{
				if(grid[x + 1][y - 1] != wall && grid[x + 1][y - 1] != exit)
				{
					if(grid[x + 1][y - 1] > grid[x][y] + lambda)
					{
						grid[x + 1][y - 1] = grid[x][y] + lambda;
					}
					initGridBottomLeft(x+1,y-1);					
				}
				if(grid[x][y - 1] != wall && grid[x][y - 1] != exit)
				{		
					if(grid[x][y - 1] > grid[x][y] + 1)
					{
						grid[x][y - 1] = grid[x][y] + 1;
					}	
					initGridBottomLeft(x,y-1);
				}
				if( y < nbColumns - 1)
				{
					if(grid[x+1][y + 1] != wall && grid[x+1][y + 1] != exit)
					{
						if(grid[x + 1][y + 1] > grid[x][y] + 1)
						{
							grid[x + 1][y + 1] = grid[x][y] + 1;
						}	
						initGridBottomLeft(x+1,y+1);
					}
				}
			}		

		}
	}


	// Test
	public void afficherGrille()
	{
		for(int i = 0; i < nbLines;i++)
		{
			for(int j = 0; j < nbColumns; j++ )
			{
				System.out.print(grid[i][j]);
				System.out.print("     ");
			}	
			System.out.println();
		}
	}
}
