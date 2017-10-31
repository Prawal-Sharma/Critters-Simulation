package assignment5;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

import java.io.File;
import java.util.ArrayList;

import javafx.animation.*;


public class Main  extends Application {

	 private static GridPane controlPane;
     private static Stage controlStage;
	protected static boolean runStatsCritterSelectedbool;
	private static Stage critterMap = new Stage(); 
	
	
	public static void main(String[] args) {
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		createcontroller();
		
}

	private void createcontroller() {
		// TODO Auto-generated method stub
		
		// display world button
        Text textDisplayWorld = new Text("The world is not displayed.");
        Button displayworldButton = new Button("Display World");
        displayworldButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
            GridPane grid = new GridPane();     
            	Critter.displayWorld(grid);
            textDisplayWorld.setText("The world is displayed.");
            }
        });
        textDisplayWorld.setFill(Color.BLACK);
        textDisplayWorld.setStyle(" -fx-font-size: 18");
        controlPane.add(displayworldButton,0,1);
        controlPane.add(textDisplayWorld,1,1);
        
        
        //make critter button
        Button makecritbutton = new Button("Make Critter");
        ComboBox<String> makeCritterDropdown = new ComboBox<>();
        String[] classes = this.getClasses();
        makeCritterDropdown.getItems().addAll(classes); 
                                                       
        TextField makeInputBox = new TextField("1"); 
        makecritbutton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                try 
                {
                    String choice = makeCritterDropdown.getValue();
                    if (choice!=null) 
                    {
                        String input = makeInputBox.getText();
                        if (input!=null) 
                        {
                            int n = Integer.parseInt(input);
                            for (int i = 0; i < n; i++) 
                            {
                                Critter.makeCritter(choice);
                            }
                        }
                    }
                } 
                catch (InvalidCritterException e) {} 
                catch (NumberFormatException e) {}
            }
        });
        controlPane.add(makecritbutton, 0, 2);
        controlPane.add(makeInputBox, 1, 2);
        controlPane.add(makeCritterDropdown, 2, 2);

        
        
        //runstats button
        
        Button runStatsButton = new Button("Run Stats");
        ComboBox<String> runStatsdropdown = new ComboBox<>();
        Text runStatsText = new Text("Select Critter.");
        runStatsText.setFont(Font.font(null,FontWeight.NORMAL,18));
        runStatsText.setFill(Color.BLACK);
        runStatsdropdown.getItems().addAll(classes); // set up w/ Critter
                                                     // implementing classes
        runStatsButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                Class<?> c = null;
                String selection = runStatsdropdown.getValue();
                if (selection != null) 
                { 
                    runStatsCritterSelectedbool = true;
                		runStatsText.setText("Ran stats on " + selection + ".");
                     displayRunStatsScene();
                } 
                else 
                {
                		runStatsText.setText("Please select a critter.");
                		runStatsCritterSelectedbool = false;
                }
            }

			
        });
        controlPane.add(runStatsButton, 0, 3);
        controlPane.add(runStatsdropdown, 1, 3);
        controlPane.add(runStatsText, 2, 3);
        
        
        
     // make  time step button 
        Button stepButton = new Button("Perform Time Step");
        TextField stepInputBox = new TextField("1"); // default 1 step
        Text textStep = new Text("");
   //     textStep.setFill(Color.WHITE);
   //     textStep.setStyle();
        

        stepButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {

                String input = stepInputBox.getText();
                textDisplayWorld.setText("The world was displayed.");

                if (input.equals("") || input.equals("1")) 
                {
                    Critter.worldTimeStep();
                    textStep.setText("");
                } 
                else 
                {
                    textStep.setText("");
                    try 
                    {
                        int n = Integer.parseInt(input);
                        for (int i = 0; i < n; i++) 
                        {
                            Critter.worldTimeStep();
                        }
                    } 
                    catch (Exception e) 
                    {
                        textStep.setText("Enter an integer.");
                    }
                }
            }
        });
        controlPane.add(stepButton, 0, 4);
        controlPane.add(stepInputBox, 1, 4);
        controlPane.add(textStep, 2, 4);

     // seed button
        Text textSeed =new Text("");
        TextField seedInputBox = new TextField();
        seedInputBox.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                try 
                {
                    int i = Integer.parseInt(seedInputBox.getText());
                    Critter.setSeed(i);
                    textSeed.setText("Seed set to "+i);
                } 
                catch (Exception e) 
                {
                    textSeed.setText("Enter an integer.");
                }
            }
        });
        Button seedButton = new Button("Set Seed");
        seedButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent arg0) 
            {
                try 
                {
                    int i = Integer.parseInt(seedInputBox.getText());
                    Critter.setSeed(i);
                    textSeed.setText("Seed set to " + i);
                } 
                catch (Exception e) 
                {
                    textSeed.setText("Enter an integer.");
                }
            }
        });
        textSeed.setFill(Color.WHITE);
        //textSeed.setStyle("-fx-font-size: 20");
        controlPane.add(seedButton,0,7);
        controlPane.add(seedInputBox,1,7);
        controlPane.add(textSeed,2,7);

        
        
        
        //  quit button
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        controlPane.add(quitButton, 0, 10);

        
        
        
        //  reset button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Critter.clearWorld();
            }
        });
        controlPane.add(resetButton, 1, 10);

        
        
	}
	
	public static void setWorldMap(Scene scene) {
		if(critterMap.getScene() == null) {
			critterMap.setScene(scene);
			Rectangle2D primBound = Screen.getPrimary().getVisualBounds(); 
			critterMap.setX(primBound.getMinX() + 700);
			critterMap.setY(primBound.getMinY() + primBound.getHeight() / 8 + 50);
			critterMap.show();
		}
		else {
			double changedWidth = critterMap.getWidth(); 
			double changedHeight = critterMap.getHeight(); 
			critterMap.setScene(scene);
			critterMap.setWidth(changedWidth);
			critterMap.setHeight(changedHeight);
			critterMap.show();
		}
	}

	private String[] getClasses() {
		// TODO Auto-generated method stub
		
		ArrayList<String> critters = new ArrayList<>();
		File directory = new File(Critter.class.getPackage().toString().split(" ")[1]);
		String[] filelist= new String[1];
		if (directory.exists()) {filelist=directory.list();}
		for (int i=0;i<filelist.length;i++)
		{
			filelist[i] = filelist[i].split("^*{0,}\\.")[0];
		}
		for (String crit:filelist)
		{
			String className = Critter.class.getPackage().toString().split(" ")[1] +"." +crit;
            Class<?> c = null;
            try 
            {
                c = Class.forName(className);
            } 
            catch (ClassNotFoundException ce) {}
            if (c !=null && Critter.class.isAssignableFrom(c)) 
            {
                try 
                {
                    @SuppressWarnings("unused")
                    Critter o = (Critter) c.newInstance();
                    critters.add(crit);
                } 
                catch (InstantiationException e) {} 
                catch (IllegalAccessException e) {}
            }
		}
		
		return critters.toArray(new String[critters.size()]);
	}
	
	
	private void  displayRunStatsScene() {
				// TODO Auto-generated method stub
				
	}
}
