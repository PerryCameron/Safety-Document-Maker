package com.safety;

import java.io.FileInputStream;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui_Main extends Application {

	static Gui_InformationTab infoTab;
	static Gui_ChoicesTab choicesTab;
	static Gui_JobStepsTab stepsTab;
	static Gui_JobCompletionTab completionTab;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
			// GUI objects
		Document_Main.document.add(new SafetyObject());
		//Document_Main.users.add(new UserObject("newuser",""));
		Document_Main.openUserObjects();  // load main and other users into system
		Gui_Main.infoTab = new Gui_InformationTab((SafetyObject) Document_Main.document.get(Document_Main.document.size() -1));
		Gui_Main.choicesTab = new Gui_ChoicesTab((SafetyObject) Document_Main.document.get(Document_Main.document.size() -1));
		Gui_Main.stepsTab = new Gui_JobStepsTab((SafetyObject) Document_Main.document.get(Document_Main.document.size() -1));
		Gui_Main.completionTab = new Gui_JobCompletionTab((SafetyObject) Document_Main.document.get(Document_Main.document.size() -1), infoTab, choicesTab, stepsTab);
		
		final HashMap<String, Tab> tabsMap = createTabHashMap(infoTab, choicesTab, stepsTab, completionTab);
		
		Image image = new Image(new FileInputStream(Document_Main.SAFETY_IMG));
		Image image2 = new Image(new FileInputStream(Document_Main.DOCUMENT_ICON));

		ImageView imageView = new ImageView(image); 
	     
	     
	    MenuBar menuBar = Gui_FileMenu.buildMenuBarWithMenus(primaryStage.widthProperty(), primaryStage); 
	      //Setting the preserve ratio of the image view 
	    imageView.setPreserveRatio(true);
	    imageView.setFitHeight(140); 
	    imageView.setFitWidth(200);
	    
		Group root = new Group();
		VBox leftBox = new VBox();
		final Scene scene = new Scene(root, 950, 650, Color.WHITE);
		scene.getStylesheets().add(Document_Main.MAINCSS);
		primaryStage.getIcons().add(image2);
		StackPane welcomeTab_stack = new StackPane();
		BorderPane mainPane = new BorderPane();  // the main pain :)
		

		TabPane tabPane = new TabPane();


        // GUI Object Properties
		
        primaryStage.setTitle("Documents");

		// riskAssessment.setText("Information");
		
		welcomeTab_stack.setAlignment(Pos.CENTER);
		leftBox.getChildren().addAll(new Gui_TreeViewPane(tabPane,tabsMap),imageView);
		leftBox.setAlignment(Pos.CENTER);
		leftBox.setPadding(new Insets(0,0,10,0));
		mainPane.setLeft(leftBox);
		mainPane.setCenter(tabPane);
		mainPane.setTop(Gui_ButtonMenu.buttonMenu(primaryStage));
		mainPane.prefHeightProperty().bind(scene.heightProperty());
		mainPane.prefWidthProperty().bind(scene.widthProperty());
		
		mainPane.setCenter(tabPane);
		root.getChildren().addAll(mainPane, menuBar);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public HashMap <String, Tab>createTabHashMap(Tab infoTab, Tab choicesTab, Tab stepsTab, Tab completionTab) {
		HashMap<String, Tab> hashMap = new HashMap<String, Tab>();
		hashMap.put("Information", infoTab);
		hashMap.put("Safety Checkoff", choicesTab);
		hashMap.put("Job Steps", stepsTab);
		hashMap.put("Job Completion", completionTab);
		return hashMap;
	}
}
