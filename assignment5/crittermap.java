package assignment5;

/**class that stores information of critter position and critter move status
 * useful to ensure critters dont move twice
 * @author ericsu
 *
 */

public class crittermap{
	private int xpos;
	private int ypos;
	private boolean moved;
	
	/**
	 * Constructor function
	 * 
	 * @author ericsu
	 * @param x location x
	 * @param y location y
	 */
	public crittermap(int x,int y) {
		this.moved=false;
		this.xpos=x;
		this.ypos=y;
	}
	/**
	 * Getter function
	 * @return ypos
	 */
	public int y() {return ypos;}
	
	/**
	 * Getter function
	 * @return xpos
	 */
	public int x() {return xpos;}
	
	/**
	 * Return whether critter has moved
	 * @return boolean for if has moved
	 */
	public boolean moved() {return moved;}
	
	/**
	 * move critter to desired position and say the critter has moved
	 * @param x move x
	 * @param y move y
	 */
	public void move(int x, int y){
		this.xpos=x;
		this.ypos=y;
		this.moved=true;
	}
}
