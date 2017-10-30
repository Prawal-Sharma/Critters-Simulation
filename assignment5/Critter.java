package assignment5;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.List;
import java.util.HashSet;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static Map <Critter,crittermap> initialposition =new HashMap<>();
	private static int time=0;
	private static boolean timestepflag;
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	
	// STILL NEED TO TEST 
	protected final String look(int direction, boolean steps) {
		this.energy -= Params.look_energy_cost; 
		int temp_x = this.x_coord; 
		int temp_y = this.y_coord; 
		
		if(!timestepflag) {
			// if you haven't done timestep, get old coord
			this.x_coord =  initialposition.get(this).x();
			this.y_coord =  initialposition.get(this).y();
		}
		
		if(steps) {
			this.run(direction);
		}
		else {
			this.walk(direction); 
		}
		
		if(timestepflag) {
			for(Critter c : population) {
				if(c.isAlive() && c != this) {
					if((this.x_coord == c.x_coord) && (this.y_coord == c.y_coord)) {
						this.x_coord = temp_x; 
						this.y_coord = temp_y; 
						return c.toString(); 
					} 
				}
			}
		}
		else {
			for(Entry<Critter, crittermap> entry : initialposition.entrySet()) {
				Critter c = entry.getKey(); 
				crittermap cm = entry.getValue(); 
				if((c != this) && (this.x_coord == cm.x()) && (this.y_coord == cm.y())) {
					this.x_coord = temp_x; 
					this.y_coord = temp_y; 
					return entry.getKey().toString(); 
				}
			}
		}
		
		// Revert values back 
		this.x_coord = temp_x; 
		this.y_coord = temp_y; 
		return null;
		
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
	direction = direction%8;		//only allow 8 possible direction
		
		this.energy=this.energy-Params.walk_energy_cost;
		switch (direction) 		//cases for each direction where direction 0 is going up. and each successive direction going around clockwise
		{
		case 6:
			if(this.y_coord==(Params.world_height-1)) {this.y_coord=0;}		//wraparound when reach top
            else {this.y_coord++;}
		    break;
		case 7:
			if((this.x_coord==(Params.world_width-1)) && (this.y_coord==(Params.world_height-1)))		//double wraparound
            {
                this.x_coord = 0;
                this.y_coord = 0;
            }
            else if(this.y_coord == (Params.world_height-1)) {this.x_coord++; this.y_coord=0;}
            else if(this.x_coord == (Params.world_width-1)) {this.y_coord++; this.x_coord=0;}
            else {this.x_coord++; this.y_coord++; }
			break;
		case 0:
			if (this.x_coord==(Params.world_width-1)) {this.x_coord=0;}
			else{this.x_coord++;}
			break;
		case 1:
			if((this.x_coord==(Params.world_width-1)) && (this.y_coord==0))		//double wraparound
            {
                this.x_coord = 0;
                this.y_coord = Params.world_height-1;
            }
            else if(this.y_coord ==0) {this.x_coord++; this.y_coord=Params.world_height-1;}
            else if(this.x_coord == (Params.world_width-1)) {this.y_coord++; this.x_coord=0;}
            else {this.x_coord++; this.y_coord--; }
			break;
		case 2:
			if (this.y_coord==0) {this.y_coord=Params.world_height-1;}
			else {this.y_coord--;}
			break;
		case 3:
			if((this.x_coord==0) && (this.y_coord==0))		//double wraparound
            {
                this.x_coord = Params.world_width-1;
                this.y_coord = Params.world_height-1;
            }
            else if(this.y_coord ==0) {this.x_coord--; this.y_coord=Params.world_height-1;}
            else if(this.x_coord == 0) {this.y_coord--; this.x_coord=Params.world_width-1;}
            else {this.x_coord--; this.y_coord--; }
			break;
		case 4:
			if (this.x_coord==0) {this.x_coord=Params.world_width-1;}
			else {this.x_coord--;}
			break;
		case 5:
			if((this.x_coord==0) && (this.y_coord==Params.world_height-1))		//double wraparound
            {
                this.x_coord = Params.world_width-1;
                this.y_coord = 0;
            }
            else if(this.y_coord ==Params.world_height-1) {this.x_coord--; this.y_coord=0;}
            else if(this.x_coord == 0) {this.y_coord++; this.x_coord=Params.world_width-1;}
            else {this.x_coord--; this.y_coord++; }
			break;
		}

	}
	
	protected final void run(int direction) {
		this.energy=this.energy+(2*Params.walk_energy_cost);		//add back two walk energies to cancel out the deduction from function
		walk(direction);
		walk(direction);
		
		this.energy=this.energy-Params.run_energy_cost;		//take out run cost
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if (this.getEnergy()>=Params.min_reproduce_energy) {
			babies.add(offspring);
			offspring.energy= this.energy/2;
			this.energy=this.energy/2 +this.energy%2;
			offspring.y_coord=this.y_coord;
			offspring.x_coord=this.x_coord;
			offspring.walk(direction);
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	public static void worldTimeStep() {
		time++;//increment the time
		
		timestepflag = false; 


		//move critters by doing timestep
		movecritters();
		timestepflag = true; 
		
		//have all critters on same tile fight
		fightallcritters();
		
		
		//create list of dead critters to be deleted
		List<Critter> deadcritters= new java.util.ArrayList<Critter>();
		for (Critter Crit:population) {
			Crit.energy-=Params.rest_energy_cost;
			if (Crit.energy<=0) {
				deadcritters.add(Crit);
			}
		}
		population.removeAll(deadcritters);
		
		//put in algae
		for (int i=0;i<Params.refresh_algae_count;i++)
		{
			try 
			{
				makeCritter("Algae");
			}catch (InvalidCritterException e) {}
			
		}
		//add in babies
		population.addAll(babies);
		babies.clear();
	}
	
	public static void displayWorld(Object pane) {
		GridPane grid = (GridPane) pane; 
		Scene map = new Scene(grid, 400, 400);
		grid.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		int i = 0; 
		
		while(i != Params.world_width) {
			ColumnConstraints colcon = new ColumnConstraints(); 
			colcon.setPercentWidth(Params.world_width);
			grid.getColumnConstraints().add(colcon); 
			i++; 
		}
		i = 0; // reset value of iteration
		
		while(i != Params.world_height) {
			RowConstraints rowcon = new RowConstraints(); 
			rowcon.setPercentHeight(Params.world_height);
			grid.getRowConstraints().add(rowcon); 
			i++; 
		}
		
		if(population.isEmpty()) {
			grid.add(new Rectangle(), 0, 0);
		}
		else {
			for(Critter crit : population) {
				if(crit.isAlive()) {
					if(crit.viewShape() == CritterShape.SQUARE) {
						Rectangle rec = new Rectangle(); 
						rec.widthProperty().bind(map.widthProperty().divide(Params.world_width));
						rec.heightProperty().bind(map.heightProperty().divide(Params.world_height));								
						rec.setStroke(crit.viewOutlineColor()); 
						rec.setFill(crit.viewFillColor()); 	
						rec.setStrokeWidth(3);
						grid.add(rec, crit.x_coord, crit.y_coord);
						
					}
					else if(crit.viewShape() == CritterShape.CIRCLE) {
						Ellipse ell = new Ellipse();
						ell.setStroke(crit.viewOutlineColor()); 
						ell.setStrokeWidth(3);
						ell.setFill(crit.viewFillColor()); 
						ell.radiusXProperty().bind(map.widthProperty().divide(Params.world_width * 2));
						ell.radiusYProperty().bind(map.heightProperty().divide(Params.world_height * 2));
						grid.add(ell, crit.x_coord, crit.y_coord);
					}
					else if(crit.viewShape() == CritterShape.TRIANGLE) {
						Triangle tri = new Triangle(map.getWidth() / Params.world_width,
								map.getHeight() / Params.world_height);
						tri.getTrianglePolygon().setFill(crit.viewFillColor());
						tri.getTrianglePolygon().setStroke(crit.viewOutlineColor());
						tri.getTrianglePolygon().setStrokeWidth(3);
						tri.getPropertyBase().bind(map.widthProperty().divide(Params.world_width));
						tri.getPropertyHeight().bind(map.heightProperty().divide(Params.world_height));
						grid.add(tri.getTrianglePolygon(), crit.x_coord, crit.y_coord);
					}
					else if(crit.viewShape() == CritterShape.DIAMOND) {
						Diamond diamond = new Diamond(map.getWidth() / Params.world_width,
								map.getHeight() / Params.world_height);
						diamond.getTrianglePolygon().setFill(crit.viewFillColor());
						diamond.getTrianglePolygon().setStroke(crit.viewOutlineColor());
						diamond.getTrianglePolygon().setStrokeWidth(2);
						diamond.getPropertyWidth().bind(map.widthProperty().divide(Params.world_width));
						diamond.getPropertyHeight().bind(map.heightProperty().divide(Params.world_height));
						grid.add(diamond.getTrianglePolygon(), crit.x_coord, crit.y_coord);
					}
					else if(crit.viewShape() == CritterShape.STAR) {
						Star star = new Star(map.getWidth() / Params.world_width, map.getHeight() / Params.world_height);
						 star.getStarPolygon().setFill(crit.viewFillColor());
						 star.getStarPolygon().setStroke(crit.viewOutlineColor());
						star.getStarPolygon().setStrokeWidth(3);
						star.getXFactorProperty().bind(map.widthProperty().divide(Params.world_width));
                        star.getYFactorProperty().bind(map.heightProperty().divide(Params.world_height));
					    grid.add(star.getStarPolygon(), crit.x_coord, crit.y_coord);
						
					}
				}
				
			}
		}
		grid.setGridLinesVisible(true);
		Main.setWorldMap(map);
		
	} 
	/* Alternate displayWorld, where you use Main.<pane> to reach into your
	   display component.
	   // public static void displayWorld() {}
	*/
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		String cn = myPackage + "." + critter_class_name; // cn is class name
		Class<?> classC = null; 
		try {
			classC = Class.forName(cn); 
		}
		catch(ClassNotFoundException ce) {
			throw new InvalidCritterException(critter_class_name); 
		}
		try {
			if(Critter.class.isAssignableFrom(classC)) {
				Critter newCrit = (Critter) classC.newInstance(); 
				newCrit.energy = Params.start_energy; 
				newCrit.x_coord = getRandomInt(Params.world_width); 
				newCrit.y_coord = getRandomInt(Params.world_height); 
				WorldMap.addCritterToWorld(newCrit.x_coord, newCrit.y_coord, newCrit); 
				population.add(newCrit); 
			}
		}
		catch(InstantiationException | IllegalAccessException e) {
			throw new InvalidCritterException(critter_class_name); 
		}
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		String className = myPackage+"."+critter_class_name;
        Class<?> c = null;
        try{
            c = Class.forName(className);
        } 
        catch(ClassNotFoundException ce){
            throw new InvalidCritterException(critter_class_name);
        }
        
        List<Critter> world = population;
        for (Critter crit : world) {
            
            if (c.isInstance(crit)) {
                result.add(crit);
            }
            
        }
		return result;
	}
	
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		Critter.population.clear();
		Critter.babies.clear();
		WorldMap.clearWorld();
	}
	
	private boolean isAlive() {
		return(this.getEnergy() > 0);
	}
	
	private static void fightallcritters() 
	{
		
		for(Critter crit1: population) {
			for(Critter crit2: population) {
				if(crit1 == crit2) {
					continue; // don't fight yourself
				}
				if((crit1.x_coord == crit2.x_coord) && (crit1.y_coord == crit2.y_coord)){
					// check to see if both are alive
					if(crit1.isAlive() && crit2.isAlive()) {
						// Both on same location and are alive, must resolve conflict 
						int cord_x = crit1.x_coord; 
						int cord_y = crit1.y_coord; 
						
						boolean crit1_fight = crit1.fight(crit2.toString()); 
						if(spotTaken(crit1)) {
							// Can't move crit1, put crit1 back
							crit1.x_coord = cord_x; 
							crit1.y_coord = cord_y; 
						}
						
						// check critter 2
						cord_x = crit2.x_coord; 
						cord_y = crit2.y_coord; 
						
						boolean crit2_fight = crit2.fight(crit1.toString()); 
						if(spotTaken(crit2)) {
							crit2.x_coord = cord_x; 
							crit2.y_coord = cord_y; 
						}
						
						// Must check if after the fights, they are still in same position
						// In this case must roll and decide winner
						if((crit1.x_coord == crit2.x_coord) && (crit1.y_coord == crit2.y_coord)) {
							if(crit1.isAlive() && crit2.isAlive()) {
								// Two must roll at this point
								
								int crit1_roll; 
								int crit2_roll; 
								
								if(crit1_fight) {
									crit1_roll = Critter.getRandomInt(crit1.energy); 
								}
								else {
									crit1_roll = 0; 
								}
								if(crit2_fight) {
									crit2_roll = Critter.getRandomInt(crit2.energy); 
								}
								else {
									crit2_roll = 0; 
								}
								
								if(crit1_roll > crit2_roll) {
									// Crit1 wins fight
									crit1.energy = crit1.energy + (crit2.energy)/2; 
									crit2.energy = 0; 
								}
								else if(crit2_roll > crit1_roll) {
									// Crit2 wins fight
									crit2.energy = crit2.energy + (crit1.energy)/2; 
									crit1.energy = 0; 
								}
								
								// If both rolls are same, just say crit1 wins, he got lucky 
								else {
									// Crit1 wins fight
									crit1.energy = crit1.energy + (crit2.energy)/2; 
									crit2.energy = 0; 
								}
								
							}
						}
					}
				}
			}
		}
		
		
	}
	
	private static boolean spotTaken(Critter c) {
		for(Critter a: population) {
			if(a == c ) {
				continue; 
			}
			if((a.x_coord == c.x_coord) && (a.y_coord == c.y_coord)) {
				return true; 
			}
		}
		return false; 
	}
	
	private static void movecritters() 
	{
		//create hashmap that tells te initial posistion of each critter in world andthe status ofif it moved
		initialposition =new HashMap<>();
		for (Critter crit:population) 		//itterate through all critters
		{
			initialposition.put(crit, new crittermap(crit.x_coord,crit.y_coord));
			//now that we saved the old critter image we can do timestep
			crit.doTimeStep();
			//check to see if the critter moved
			if ((initialposition.get(crit).x()!=crit.x_coord)||(initialposition.get(crit).y()!=crit.y_coord))
			{
				//if position change then update position and say it moved
				initialposition.get(crit).move(crit.x_coord, crit.y_coord);
			}
		}
	}
	
	
	
	
}
