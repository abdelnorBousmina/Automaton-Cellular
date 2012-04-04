/**
 * 
 */
package model;

/**
 * @author bous
 *
 */
public class Obstacle {

	private int xDebut;
	private int yDebut;
	private int xFin;
	private int yFin;
	
	/**
	 * @param xDebut
	 * @param yDebut
	 * @param xFin
	 * @param yFin
	 */
	public Obstacle(int xDebut, int yDebut, int xFin, int yFin) {
		this.xDebut = xDebut;
		this.yDebut = yDebut;
		this.xFin = xFin;
		this.yFin = yFin;
	}
	/**
	 * @return the xDebut
	 */
	public int getxDebut() {
		return xDebut;
	}
	/**
	 * @param xDebut the xDebut to set
	 */
	public void setxDebut(int xDebut) {
		this.xDebut = xDebut;
	}
	/**
	 * @return the yDebut
	 */
	public int getyDebut() {
		return yDebut;
	}
	/**
	 * @param yDebut the yDebut to set
	 */
	public void setyDebut(int yDebut) {
		this.yDebut = yDebut;
	}
	/**
	 * @return the xFin
	 */
	public int getxFin() {
		return xFin;
	}
	/**
	 * @param xFin the xFin to set
	 */
	public void setxFin(int xFin) {
		this.xFin = xFin;
	}
	/**
	 * @return the yFin
	 */
	public int getyFin() {
		return yFin;
	}
	/**
	 * @param yFin the yFin to set
	 */
	public void setyFin(int yFin) {
		this.yFin = yFin;
	}
	
}
