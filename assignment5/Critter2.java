package assignment5;
/* CRITTERS MyCritter2.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Prawal Sharma>
 * <ps27933>
 * <16280>
 * <Eric Su>
 * <es34826>
 * <16275>
 * Slip days used: <0>
 * Fall 2017
 */




/**MyCritter2 by Eric Su

//Critter 2 is called the coin flip critter. in normal timestep, theres a 50% chance that he walk in a random direction
// or a 50% chance he does nothing. When he has encounters, he fights half the time and the other half of the time, he might
 *  fight if he happens to sees non critter 2 nearby. 
 *   This unpredictability will make this critter behave sporatically and perhaps help it stay 
//alive for a longer time.

	@author ericsu
*/

import java.util.*;

public class Critter2 extends Critter {

	@Override
	public void doTimeStep() {
		if (getRandomInt(8)>3) 		//50% chance to walk
		{
			walk(getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
		if (getRandomInt(8)>3 ) {return true;}//50% chance to fight
		else {
			int rand=getRandomInt(8);
			String a =look(rand,false);			//50% chance to look in random direction
			if (!opponent.equals(a)) {		//fight if its not a friendly
				return true;
			}
					
			return false;
		}
	}
	
	public String toString() {
		return "2";
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return  CritterShape.DIAMOND;
	}
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.YELLOW; }
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BLACK; }
	
}
