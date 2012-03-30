package model;

public class Grille {

<<<<<<< HEAD
	private float[][] grille;
	private int nbLignes;
	private int nbColonnes;
	private int xExit;
	private int yExit;
	private static float lambda = 1.5f;
	
	public Grille(int lignes,int colonnes,int xExit,int yExit)
	{
		nbLignes = lignes;
		nbColonnes = colonnes;
		this.xExit = xExit;
		this.yExit = yExit;
		grille = new float[nbLignes][nbColonnes];
		
		// Init murs verticaux
		for(int i = 0; i < nbLignes; i++)
		{
			grille[i][0] = 500;
			grille[i][nbColonnes-1] = 500;
		}
		
		// Init murs horizontaux
		for(int j = 1; j < nbColonnes-1; j++)
		{
			grille[0][j] = 500;
			grille[nbLignes-1][j] = 500;
		}
		
		// Init reste de la grille
		for(int i = 1; i < nbLignes-1;i++)
		{
			for(int j = 1; j < nbColonnes-1 ; j++ )
				grille[i][j] = 400;
		}
		// Init sortie
		grille[this.xExit][this.yExit] = 1;
		
		initGrilleHautDroite(xExit, yExit);
		initGrilleBasDroite(xExit, yExit);
	}
	
	/**
	 * @return the grille
	 */
	public float[][] getGrille() {
		return grille;
	}

	public float getValue(int x, int y)
	{
		return grille[x][y];
	}
	
	/**
	 * Permet de calculer les distances à la sortie de chaque cellules
	 * @param x la ligne
	 * @param y la colonne
	 */
	private void initGrille(int x,int y)
	{
		if(x > 0)
		{
			if(grille[x - 1][y] != 500)
			{
				
				if(grille[x - 1][y] == 400)
				{
					grille[x - 1][y] = grille[x][y] + 1;
					initGrille(x-1,y);
				}
				else if(grille[x - 1][y] > grille[x][y] + 1)
					{
						grille[x - 1][y] = grille[x][y] + 1;						
					}
				
			}
			if(y > 0)
			{				
				if(grille[x][y - 1] != 500)
				{
					if(grille[x - 1][y - 1] == 400)
					{
						grille[x][y - 1] = grille[x][y] + 1;
						initGrille(x,y-1);
					}
					else if(grille[x][y - 1] > grille[x][y] + 1)
						{
							grille[x][y - 1] = grille[x][y] + 1;
						}				
					
				}
				if(grille[x - 1][y - 1] != 500)
				{
					if(grille[x - 1][y - 1] == 400)
					{
						grille[x - 1][y - 1] = grille[x][y] + lambda;
						initGrille(x-1,y-1);
					}
					else if(grille[x - 1][y - 1] > grille[x][y] + lambda)
					{
						grille[x - 1][y - 1] = grille[x][y] + lambda;
					}					
				}
			}
			if(y < nbColonnes)
			{
				
				if(grille[x][y + 1] != 500)
				{
					if(grille[x][y + 1] == 400)
					{
						grille[x][y + 1] = grille[x][y] + 1;
						initGrille(x,y+1);
					}
					else if(grille[x][y + 1] > grille[x][y] + 1)
					{
						grille[x][y + 1] = grille[x][y] + 1;
					}					
				}
				if(grille[x - 1][y + 1] != 500)
				{
					if(grille[x - 1][y + 1] == 400)
					{
						grille[x - 1][y + 1] = grille[x][y] + lambda;
						initGrille(x-1,y+1);
					}
					else if(grille[x - 1][y + 1] > grille[x][y] + lambda)
						{
							grille[x - 1][y + 1] = grille[x][y] + lambda;
						}
					
				}
			}
		}
		if(x < nbLignes)
		{
			if(grille[x + 1][y] != 500)
			{	
				if(grille[x + 1][y] == 400)
				{
					grille[x + 1][y] = grille[x][y] + 1;
					initGrille(x+1,y);
				}
				else if(grille[x + 1][y] > grille[x][y] + 1)
				{
					grille[x + 1][y] = grille[x][y] + 1;
				}	
				
			}
			if(y < nbColonnes)
			{
				if(grille[x + 1][y + 1] != 500)
				{
					if(grille[x + 1][y + 1] == 400)
					{
						grille[x + 1][y + 1] = grille[x][y] + lambda;
						initGrille(x+1,y+1);
					}
					else if(grille[x + 1][y + 1] > grille[x][y] + lambda)
					{
						grille[x + 1][y + 1] = grille[x][y] + lambda;
					}
					
				}
			}
			if(y > 0)
			{
				if(grille[x + 1][y - 1] != 500)
				{
					if(grille[x + 1][y - 1] == 400)
					{
						grille[x + 1][y - 1] = grille[x][y] + lambda;
						initGrille(x+1,y-1);
					}
					if(grille[x + 1][y - 1] > grille[x][y] + lambda)
					{
						grille[x + 1][y - 1] = grille[x][y] + lambda;
					}
					
				}
			}	
			
		}
	
	}
	private void initGrilleHautDroite(int x,int y)
	{
		if(x > 0)
		{
			if(grille[x - 1][y] != 500)
			{
				if(grille[x - 1][y] > grille[x][y] + 1)
				{
					grille[x - 1][y] = grille[x][y] + 1;						
				}
				initGrilleHautDroite(x-1,y);				
			}			
			if(y < nbColonnes)
			{
				
				if(grille[x][y + 1] != 500)
				{		
					if(grille[x][y + 1] > grille[x][y] + 1)
					{
						grille[x][y + 1] = grille[x][y] + 1;
					}	
					initGrilleHautDroite(x,y+1);
				}
				if(grille[x - 1][y + 1] != 500)
				{					
					if(grille[x - 1][y + 1] > grille[x][y] + lambda)
					{
						grille[x - 1][y + 1] = grille[x][y] + lambda;
					}
					initGrilleHautDroite(x-1,y+1);
				}
			}
		}
	}
	private void initGrilleBasDroite(int x, int y)
	{
		
		if(x < nbLignes)
		{
			if(grille[x + 1][y] != 500)
			{	
				if(grille[x + 1][y] > grille[x][y] + 1)
				{
					grille[x + 1][y] = grille[x][y] + 1;
				}
				initGrilleBasDroite(x+1,y);
				
			}
			if(y < nbColonnes)
			{
				if(grille[x + 1][y + 1] != 500)
				{
					if(grille[x + 1][y + 1] > grille[x][y] + lambda)
					{
						grille[x + 1][y + 1] = grille[x][y] + lambda;
					}
					initGrilleBasDroite(x+1,y+1);					
				}
				if(grille[x][y + 1] != 500)
				{		
					if(grille[x][y + 1] > grille[x][y] + 1)
					{
						grille[x][y + 1] = grille[x][y] + 1;
					}	
					initGrilleHautDroite(x,y+1);
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
=======
>>>>>>> f4bfaf0344aea8d63e2cec8b6d42fbf683de9f8f
}
