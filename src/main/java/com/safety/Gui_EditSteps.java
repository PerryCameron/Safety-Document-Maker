package com.safety;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
////////  this class creates some GUI componets for the JobStepsTab class
public class Gui_EditSteps {

	public static VBox makeStepEditGui(final int element, Image editImage) {
		final ToggleGroup riskGroup = new ToggleGroup();
		ImageView editIcon = new ImageView(editImage);
		final RadioButton rb1 = new RadioButton("Low");
		final RadioButton rb2 = new RadioButton("Medium");
		final RadioButton rb3 = new RadioButton("High");

		Button saveButton = new Button("Save");

		final TextField jobStepsTextField = new TextField();
		final TextField hazardsTextField = new TextField();
		final TextField controlMeasuresTextField = new TextField(); 
		Label controlMeasuresLabel = new Label("Control Measures:");

		Label jobStepsLabel = new Label("Job Step:");
		Label hazardsLabel = new Label("Hazards:");
		Label riskLabel = new Label("Risk:");

		VBox mainVBox = new VBox(20);
		HBox jobStepsHBox = new HBox(15);
		HBox hazardsHBox = new HBox(15);
		HBox riskHBox = new HBox(15);
		HBox controlMeasuresHBox = new HBox(15);
		HBox riskExtendedHBox = new HBox(120);

		jobStepsHBox.setAlignment(Pos.CENTER_RIGHT);
		jobStepsHBox.setSpacing(25);
		hazardsHBox.setSpacing(29);
		hazardsHBox.setAlignment(Pos.CENTER_RIGHT);
		controlMeasuresHBox.setAlignment(Pos.CENTER_RIGHT);
		riskExtendedHBox.setAlignment(Pos.CENTER);

		jobStepsTextField.setPrefWidth(545);  // was 550 before adding button
		hazardsTextField.setPrefWidth(545);
		controlMeasuresTextField.setPrefWidth(500);

		jobStepsTextField.setText(Document_Main.jobSteps.get(element).getJobSteps());
		hazardsTextField.setText(Document_Main.jobSteps.get(element).getHazards());
		controlMeasuresTextField.setText(Document_Main.jobSteps.get(element).getControls());
		System.out.println("Risk set to " + Document_Main.jobSteps.get(element).getRisk());
		switch (Document_Main.jobSteps.get(element).getRisk()) {
			case "Low" :  rb1.setSelected(true);
			break;
			case "Medium":  rb2.setSelected(true);
			break;
			case "High":  rb3.setSelected(true);
			break;
		}
		
		riskHBox.getChildren().addAll(riskLabel,rb1,rb2,rb3);
		riskExtendedHBox.getChildren().addAll(editIcon,riskHBox,saveButton);
		jobStepsHBox.getChildren().addAll(jobStepsLabel,jobStepsTextField);
		controlMeasuresHBox.getChildren().addAll(controlMeasuresLabel,controlMeasuresTextField);
		hazardsHBox.getChildren().addAll(hazardsLabel,hazardsTextField);
		

		rb1.setToggleGroup(riskGroup);
		rb2.setToggleGroup(riskGroup);
		rb3.setToggleGroup(riskGroup);
		// rb1.setSelected(true);
		mainVBox.getChildren().addAll(jobStepsHBox,hazardsHBox, controlMeasuresHBox, riskExtendedHBox);
		
		
		saveButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				Document_Main.jobSteps.get(element).setJobSteps(jobStepsTextField.getText());
				Document_Main.jobSteps.get(element).setHazards(hazardsTextField.getText());
				Document_Main.jobSteps.get(element).setControls(controlMeasuresTextField.getText());
				if(rb1.isSelected()) {
					Document_Main.jobSteps.get(element).setRisk("Low");
					
				} else if(rb2.isSelected()) {
					Document_Main.jobSteps.get(element).setRisk("Medium");
				} else {
					Document_Main.jobSteps.get(element).setRisk("High");
				}
				Document_Main.saveStepObjects();
				/// clears display, finds element, redisplays
				clearTab(Document_Main.jobSteps.get(element).getStepType()); 
				Gui_Main.stepsTab.clearListViews();
				Gui_Main.stepsTab.refreshListViews();
			}
		});
		
		return mainVBox;
	}
	
	public static void clearTab(int tabToClear) {
		System.out.println("tabToClear = " + tabToClear);
		if(tabToClear == 1) {
			Gui_Main.stepsTab.preGeneralStepsTabHbox.getChildren().clear(); 
			Gui_Main.stepsTab.preGeneralStepsTabHbox.getChildren().addAll(Gui_Main.stepsTab.listViewGeneral,Gui_Main.stepsTab.generalJobStepsVBox);
		} else if(tabToClear == 2) {
			Gui_Main.stepsTab.preBatteryStepsTabHbox.getChildren().clear(); 
			Gui_Main.stepsTab.preBatteryStepsTabHbox.getChildren().addAll(Gui_Main.stepsTab.listViewBattery,Gui_Main.stepsTab.batteryJobStepsVBox);
		} else if(tabToClear == 3) {
			Gui_Main.stepsTab.preCorrectiveStepsTabHbox.getChildren().clear(); 
			Gui_Main.stepsTab.preCorrectiveStepsTabHbox.getChildren().addAll(Gui_Main.stepsTab.listViewCm,Gui_Main.stepsTab.correctiveJobStepsVBox);
		} else if(tabToClear == 4) {
			Gui_Main.stepsTab.prePreventiveStepsTabHbox.getChildren().clear(); 
			Gui_Main.stepsTab.prePreventiveStepsTabHbox.getChildren().addAll(Gui_Main.stepsTab.listViewPm,Gui_Main.stepsTab.preventiveJobStepsVBox);
		} else {
			System.out.println("Error: no such tab to clear");
		}
	}
}
