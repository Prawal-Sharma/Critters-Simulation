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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.*;


public class Main  extends Application {
	private static Stage runStatsWindow=new Stage();
	 private static GridPane controlPane;
    private static Stage controlStage;
	protected static boolean runStatsCritterSelectedbool;
	private static ArrayList<TextFlow> textList = new ArrayList <TextFlow>();
	private static Stage critterMap = new Stage(); 
	private static ComboBox<String> runstatsmenu = new ComboBox<>();
	private static Text runstatsdisplaytext = new Text();
	public static void main(String[] args) {
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		CreatepaneStart();
		
	}

	 public void CreatepaneStart() {
	        // Make pane
	        controlPane = new GridPane();
	        controlPane.setHgap(25);
	        controlPane.setVgap(30);
	        controlPane.setPadding(new Insets(0, 0, 0, 20));
	        Stop[] stops = new Stop[] { new Stop(0, Color.rgb(0, 222, 255)), new Stop(1, Color.WHITE)};
	       // LinearGradient gradient = new LinearGradient(0,0, 0,1.5, true, CycleMethod.NO_CYCLE, stops);
	        controlPane.setBackground(new Background(new BackgroundFill(null, null, new Insets(-10))));
	        
	        // Make border pane
	        BorderPane bPane = new BorderPane();
	     
	        Text title = new Text("Critter Controller");
	        title.setFont(Font.font(null, FontWeight.BOLD, 65));
	        title.setFill(Color.WHITE);
	        VBox titleBox = new VBox();
	        titleBox.setPadding(new Insets(20,0,8,0));
	        titleBox.getChildren().add(title);
	        titleBox.setAlignment(Pos.CENTER);
	        bPane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 204, 255), null, new Insets(-10))));
	        bPane.setTop(titleBox);
	        bPane.setCenter(controlPane);
	        
	        // Make the stage
	        controlStage = new Stage();
	        controlStage.setScene(new Scene(bPane,700,700));
	        controlStage.setTitle("Controller Window");
	        controlStage.setResizable(false);
	        //controllerStage.initStyle(StageStyle.UTILITY);
	        
	        //  create the controller
	        createcontroller();
	        
	        // Set stage bounds
	        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	        controlStage.setX(primaryScreenBounds.getMinX() + 50);
	        controlStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 9);

	        controlStage.show();

	        // Window title
	        critterMap.setTitle("Critter World");
	        //critterMap.initStyle(StageStyle.UTILITY);
	       // runStatswindow.setTitle("Critter Stats Monitor");
	        //runStatsWindow.initStyle(StageStyle.UTILITY);

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
                
                //Update display when button is pressed  
                GridPane grid = new GridPane();     
            	Critter.displayWorld(grid);
            textDisplayWorld.setText("The world is displayed.");
            }
           
        });
        controlPane.add(makecritbutton, 0, 2);
        controlPane.add(makeInputBox, 1, 2);
        controlPane.add(makeCritterDropdown, 2, 2);

        
        
        //runstats button
        
        Button runStatsButton = new Button("Run Stats");
        runstatsmenu = new ComboBox<>();
        runstatsdisplaytext = new Text("Select Critter.");
        runstatsdisplaytext.setFont(Font.font(null,FontWeight.NORMAL,18));
        runstatsdisplaytext.setFill(Color.BLACK);
        runstatsmenu.getItems().addAll(classes); // set up w/ Critter
                                                     // implementing classes
        runStatsButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                Class<?> c = null;
                String selection = runstatsmenu.getValue();
                if (selection != null) 
                { 
                      runStatsCritterSelectedbool=true;
                      runstatsdisplaytext.setText("Ran stats on " + selection + ".");
                      displayRunStatsScene();
                } 
                else 
                {
                		runstatsdisplaytext.setText("Please select a critter.");
                		runStatsCritterSelectedbool=false;
                }
            }

			
        });
        controlPane.add(runStatsButton,0,3);
        controlPane.add(runstatsmenu,1,3);
        controlPane.add(runstatsdisplaytext,2,3);
        
        
        
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
                // Update Display when button is pressed 
                GridPane grid = new GridPane();     
                Critter.displayWorld(grid);
                textDisplayWorld.setText("The world is displayed.");
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
		
		
		ArrayList<String> returnlist= new ArrayList<>();
		for (String s : critters) {
			if (!returnlist.contains(s)) {
				returnlist.add(s);
			}
			
			
		}
		return returnlist.toArray(new String[returnlist.size()]);
	}
	
	
	private void  displayRunStatsScene()  
	{
				// TODO Auto-generated method stub
	
		
		Class<?> c =null;
		
		
		String critterstats="";
        
        String critchoice = runstatsmenu.getValue();
        if (critchoice!= null) 
        { 
                                 
            runStatsCritterSelectedbool =true;
            runstatsdisplaytext.setText("Run stats for "+critchoice+ ".");
            String classname =Critter.class.getPackage().toString().split(" ")[1]+ "."+critchoice;
            try 
            {
                List<Critter>list = Critter.getInstances(critchoice);
                c = Class.forName(classname);
                critterstats = (String)c.getMethod("runStats",List.class).invoke(null,list);
            } 
            catch (ClassNotFoundException e3) {} catch (InvalidCritterException e) {
				// TODO Auto-generated catch block
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				
			}
        } 
        else 
        {
        		runstatsdisplaytext.setText("Please select a critter.");
            runStatsCritterSelectedbool =false;
        }
        GridPane textGrid = new GridPane();
        ScrollPane root = new ScrollPane(textGrid);
        root.setVvalue(1.0);
        Scene run_stat_scene = new Scene(root,500, 250);
        TextFlow textHolder= new TextFlow();
        Text critterstattext =new Text(critterstats);                                      
        //textHolder.widthProperty().isEqualTo(run_stat_scene.widthProperty());
        critterstattext.setFill(Color.BLACK);
        textHolder.getChildren().add(critterstattext);
        textList.add(textHolder);
        for (int k=0; k < textList.size(); k++) 
        {
            textGrid.add(textList.get(k),0, k);
        }
        runStatsWindow.setScene(run_stat_scene);
        Rectangle2D primaryScreenBounds= Screen.getPrimary().getVisualBounds();
        runStatsWindow.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 525);
        runStatsWindow.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 3);
        runStatsWindow.show();
		
	}
}
