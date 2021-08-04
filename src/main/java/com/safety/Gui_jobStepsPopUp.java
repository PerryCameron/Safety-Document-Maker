package com.safety;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui_jobStepsPopUp {

	public static void predefinedJobStepsMenu(Stage primaryStage, final ImageView saveOkImage, final ImageView saveNOkImage) {
		
		 ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "General",
			        "Battery",
			        "Corrective Maintenance",
			        "Preventive Maintenance"
			    );
		
		final ComboBox<String> selectionComboBox = new ComboBox<String>(options);
		final Stage popUpStage = new Stage();
		final ToggleGroup radioButtonGroup = new ToggleGroup();
		
		VBox mainVBox = new VBox(20);
		VBox vBox1 = new VBox(20);
		HBox choiceHbox = new HBox(20);
		HBox selectionHBox = new HBox(20);
		HBox buttonHBox = new HBox();
		Label choiceLabel = new Label("Would you like to permantly save this step?");
		Label selectionLabel = new Label("What group would you like to save your step in?");
		//Pane mainVBox = new Pane();
		final RadioButton rb1 = new RadioButton("Yes");
		RadioButton rb2 = new RadioButton("No");
		Button chooseButton = new Button("close");
		Pane popUpPane = new Pane();
		TitledPane generalStepsTitledPane = new TitledPane("Step saving dialog", popUpPane);
		
		choiceHbox.setAlignment(Pos.CENTER);
		selectionHBox.setAlignment(Pos.CENTER);
		buttonHBox.setAlignment(Pos.BASELINE_RIGHT);
		
		vBox1.setPadding(new Insets(15,15,15,15));
		
		rb1.setToggleGroup(radioButtonGroup);
		if(Document_Main.saveStepToFile) {
		rb1.setSelected(true);
		} else {
		rb2.setSelected(true);
		}
		
		rb2.setToggleGroup(radioButtonGroup);
		choiceHbox.getChildren().addAll(rb1,rb2);
		selectionHBox.getChildren().addAll(selectionComboBox);
		buttonHBox.getChildren().addAll(chooseButton);
		vBox1.getChildren().addAll(choiceLabel, choiceHbox, selectionLabel, selectionHBox);
		popUpPane.getChildren().addAll(vBox1);
		selectionComboBox.getSelectionModel().select(Document_Main.stepCategory - 1);
		
		mainVBox.setPadding(new Insets(15,15,15,15));
		mainVBox.getChildren().addAll(generalStepsTitledPane,buttonHBox);
		
		Scene dialogScene = new Scene(mainVBox, 340, 300);
		popUpStage.setScene(dialogScene);
		popUpStage.show();
		
		chooseButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(rb1.isSelected()) {
					if(selectionComboBox.getValue().equals("General")) {
						Document_Main.stepCategory = 1;	
					}
					else if(selectionComboBox.getValue().equals("Battery")) {
						Document_Main.stepCategory = 2;	
					}
					else if(selectionComboBox.getValue().equals("Corrective Maintenance")) {
						Document_Main.stepCategory = 3;	
					}
					else if(selectionComboBox.getValue().equals("Preventive Maintenance")) {
						Document_Main.stepCategory = 4;	
					} else {
						Document_Main.stepCategory = 1;
					}
					Document_Main.saveStepToFile = true;
					Gui_Main.stepsTab.imageHoldingVBox.getChildren().clear();
					Gui_Main.stepsTab.imageHoldingVBox.getChildren().add(saveOkImage);
				} else {
					Gui_Main.stepsTab.imageHoldingVBox.getChildren().clear();
					Gui_Main.stepsTab.imageHoldingVBox.getChildren().add(saveNOkImage);
					Document_Main.saveStepToFile = false;
					Document_Main.stepCategory = 0;
				}
				System.out.println("save step to file = " + Document_Main.saveStepToFile);
				System.out.println("step catagory = " + Document_Main.stepCategory);
				popUpStage.close();
			}
		}); 
	}
	

}
