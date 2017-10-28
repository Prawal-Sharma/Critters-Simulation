package assignment5;



/**
 * 2-D Matrix For the Map of the Critter World
 * */ 
/**
 * Class that contains the world map. Includes functions to print the world and get the world as a function
 * @author Prawal
 *
 */
public class WorldMap {
	
	private static Critter[][] critterWorld = new Critter[Params.world_width][Params.world_height]; 
	
	
/**
 *  Getter Function
 * @return world
 */
public static Critter[][] getWorld() {
	return critterWorld; 
}

/** World Print Function 
 * 
 * 
 */

public static void printWorld() {
	
	// Print the first +-------+ portion
	System.out.print('+');
    for (int i = 0; i < Params.world_width; i++) {
        System.out.print('-');
    }
    System.out.println('+');
    
    // Print the rest of the spaces and or critters
    for (int y = 0; y < Params.world_height; y++) {
        System.out.print('|');
        for (int x = 0; x < Params.world_width; x++) {
            if ((critterWorld[x][y] == null)) {
                System.out.print(' ');
            } else {
                System.out.print(critterWorld[x][y]);
            }
        }
        System.out.print('|');
        System.out.println();
    }
    
 // Print the second +-------+ portion
 	System.out.print('+');
     for (int i = 0; i < Params.world_width; i++) {
         System.out.print('-');
     }
     System.out.println('+');   
}

/**
 *  Clear the world
 */
public static void clearWorld() {
	critterWorld = new Critter[Params.world_width][Params.world_height];
}

/**
 *  Remove the critter from Matrix by marking space as null
 * @param x location 
 * @param y location
 */
public static void removeCritter(int x, int y) {
	critterWorld[x][y] = null; 
}

/**
 *  Add Critter to position x,y 
 * @param x added x spot
 * @param y added y spot
 * @param crit critter to add
 */
public static void addCritterToWorld(int x, int y, Critter crit) {
	critterWorld[x][y] = crit; 
}

}
