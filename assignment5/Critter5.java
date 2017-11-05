package assignment5;

import assignment5.Critter.CritterShape;

/**Critter5 by Prawal Sharma

// This Critter is the "Human" Inspired Critter. It has an age, and depending on it's age (determined by time step)
// It acts in different ways
// From age 0-3 it does nothing, then from 4-12 it talks in a straight line (it's a good boy). Starting from 13-17 
// the Critter will begin to rebel and walk around in random locations. Then from age 18-26 it will run in random directions
// as it has much more freedom. From age 27-34 it will run straight and have a baby every-year (time step). Then from 35-80, it begins to
// feel old and tired, and decides to walk in a straight line (random), lastly, from 81-infinity, it dies :( 

// It also fights depending on it's age
 * @author Prawal

// Look Implemented in doTimeStep

*/
public class Critter5 extends Critter {

	private int age = 0; 
	
	@Override
	public void doTimeStep() {
		// Look Implemented
		String a = look(0,false);
		
		int RandDir[] = {0, 2, 4, 6}; // Random Straight Directions
		if(age >= 0 && age <= 3) {
			// Do nothing but age
		}
		else if(age >= 4 && age <= 12) {
			walk(RandDir[getRandomInt(4)]); // Walk straight in some Direction
			
		}
		else if(age >= 13 && age <= 17) {
			walk(getRandomInt(8)); // Walks in random directions : Rebel Phase
		}
		else if(age >= 18 && age <= 26) {
			run(getRandomInt(8)); // Much More freedom, can run in random directions
		}
		else if(age >= 27 && age <= 34) {
			run(RandDir[getRandomInt(4)]);	// Run in a straight fashion, it has responsibilities now
			Critter5 myBaby = new Critter5(); 
			reproduce(myBaby, 0); 			// It's baby making time
			
		}
		else if(age >= 35 && age <= 80) {
			walk(RandDir[getRandomInt(4)]); // Walk straight in some Direction, old Critter
		}
		else if(age >= 81) {
			while(getEnergy() > 0) {
				run(0); 
			}
		}
		
		age++; 
	}
	
	@Override 
	public boolean fight(String opponent) {
		if(age >= 0 && age <= 3) {
			return false; 	// Too young to fight
		}
		else if(age >= 4 && age <= 12) {
			return false; 	// Too young to fight
			
		}
		else if(age >= 13 && age <= 17) {
			return true; 	// Rebel wants to fight
		}
		else if(age >= 18 && age <= 26) {
			return true; 	// Hot Blooded adult
		}
		else if(age >= 27 && age <= 34) {
			return false; 	// Family Man
			
		}
		else if(age >= 35 && age <= 80) {
			return false; 	// Getting old, don't want to hurt the joints
		}
		else if(age >= 81) {
			return false; 
		}
		return false; 
	}
	
	public String toString() {
		return "5"; 
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.STAR;
	}
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.RED; }
}
