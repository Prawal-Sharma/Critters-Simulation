Prawal Sharma ps27933 Eric Su es34826                 Critters - Project 5

Overview: Project 5 - Critters is a virtual simulation of a world of "Critters". This world contains movement, encounter, birthing, and dying of various types of "Critters" in a 2-D grid environment.

World Model: The model for the project is comprised of a RunStats window which shows the statistical components for each critter, the Controller Component which helps users interface with the Critter World and the View Component which displays the grid. 

Console/Controller: The world and it's inhabitant population is controlled by the user through various buttons.
1. Display Button - initiates the call to a window that shows the world grid.
2. Make Critter Button - Based on the number input into the selection bar, this button enables the creation of critters on the grid.
3. Time Step Button - initiates a change in the time step of the world and also updates the display.
4. Run Stats button - displays the statistics of each critter.
5. Set Seed Button - allows user to input an integer value for seed management. 
6. Quiz Button - User can exit the program through this button. 

View Component: 
Ths shows the current state of the 2D grid from the last project in a more dynamic and aesthetically pleasing way. The GUI depics each critter as a shape and defines the critters properties based on class functionalities. The Algae is represented as a defaulted green circle and Craigs with squares. Any critter in the directory can be displayed due to the functionality. 

crittermap - This class ensures that critters don't move more than once. The class stores information regarding critter position and move status. The method is simple, but leads to clarification of iterations of move call. Contains getter methods and xpos ypos variables.
