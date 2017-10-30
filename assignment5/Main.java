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
import javafx.animation.*;


public class Main  extends Application {

	 private static GridPane controlPane;
     private static Stage controlStage;
	
	
	
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
        displayworldButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	Critter.displayWorld(   );
            textDisplayWorld.setText("The world is displayed.");
            }
        });
        textDisplayWorld.setFill(Color.BLACK);
        textDisplayWorld.setStyle(" -fx-font-size: 18");
        controlPane.add(displayworldButton,0,1);
        controlPane.add(textDisplayWorld,1,1);
        
        
        
        Button makecritbutton = new Button("Make Critter");
        ComboBox<String> makeCritterDropdown = new ComboBox<>();
        String[] classes = this.getClasses();
        makeCritterDropdown.getItems().addAll(classes); // set up w/ Critter
                                                        // implementing classes
        TextField makeInputBox = new TextField("1"); // default to 1 critter
        makecritbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        
	}

	private String[] getClasses() {
		// TODO Auto-generated method stub
		
		
		
		
		
		return null;
	}
	
	
	
}
