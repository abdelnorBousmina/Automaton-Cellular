/**
 * 
 */
package model;

/**
 * @author bous
 *
 */
public class Obstacle {

	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	
	/**
	 * @param xStart
	 * @param yStart
	 * @param xEnd
	 * @param yEnd
	 */
	public Obstacle(int xStart, int yStart, int xEnd, int yEnd) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	/**
	 * @return the xStart
	 */
	public int getxStart() {
		return xStart;
	}
	/**
	 * @param xStart the xStart to set
	 */
	public void setxStart(int xStart) {
		this.xStart = xStart;
	}
	/**
	 * @return the yStart
	 */
	public int getyStart() {
		return yStart;
	}
	/**
	 * @param yStart the yDebut to set
	 */
	public void setyStart(int yStart) {
		this.yStart = yStart;
	}
	/**
	 * @return the xEnd
	 */
	public int getxEnd() {
		return xEnd;
	}
	/**
	 * @param xEnd the xEnd to set
	 */
	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}
	/**
	 * @return the yEnd
	 */
	public int getyEnd() {
		return yEnd;
	}
	/**
	 * @param yEnd the yEnd to set
	 */
	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}
	
}
