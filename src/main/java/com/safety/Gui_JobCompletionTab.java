package com.safety;



import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Gui_JobCompletionTab extends Tab {

	private SafetyObject sA;
	//private Gui_JobStepsTab stepsTab;
	
	Label incidentInjuryLabel = new Label("Were there any incident/injuries?");
	Label hazardsLabel = new Label("Are there Hazards remaining?");
	Label areaCleanedLabel = new Label("Was the area cleaned up at the end of job/shift?");

	final ToggleGroup incidentInjuryGroup = new ToggleGroup();
	final ToggleGroup hazardsGroup = new ToggleGroup();
	final ToggleGroup areaCleanedGroup = new ToggleGroup();

	RadioButton rb1 = new RadioButton("Yes");
	RadioButton rb2 = new RadioButton("No");
	RadioButton rb3 = new RadioButton("Yes");
	RadioButton rb4 = new RadioButton("No");
	RadioButton rb5 = new RadioButton("Yes");
	RadioButton rb6 = new RadioButton("No");
	RadioButton rb7 = new RadioButton("N/A");

	TextField IncidentInjuryTextField = new TextField();
	TextField hazardsTextField = new TextField();
	TextField areaCleanedTextField = new TextField();

	CheckBox addSignature = new CheckBox("Add Signature");

	Button createPdfButton = new Button("Create PDF");

	/// layout objects

	FlowPane root = new FlowPane();
	Pane topPane = new Pane();

	HBox incidentInjuryHbox = new HBox();
	HBox incidentInjuryStringHbox = new HBox();
	HBox hazardsHbox = new HBox();
	HBox hazardsStringHbox = new HBox();
	HBox areaCleanedHbox = new HBox();
	HBox areaCleanedStringHbox = new HBox();
	GridPane topGridPane = new GridPane();


	VBox topVbox = new VBox();

	public Gui_JobCompletionTab(final SafetyObject safeA
			, Gui_InformationTab infoTab
			, Gui_ChoicesTab choicesTab
			,Gui_JobStepsTab stepsTab) {
		super();
		this.sA = safeA;

		//this.stepsTab = stepsTab;
		setText("Job Completion");

		rb1.setToggleGroup(incidentInjuryGroup);
		rb2.setSelected(true);
		rb2.setToggleGroup(incidentInjuryGroup);
		rb3.setToggleGroup(hazardsGroup);
		rb4.setSelected(true);
		rb4.setToggleGroup(hazardsGroup);
		rb5.setToggleGroup(areaCleanedGroup);
		rb5.setSelected(true);
		rb6.setToggleGroup(areaCleanedGroup);
		rb7.setToggleGroup(areaCleanedGroup);
		
		IncidentInjuryTextField.setPrefWidth(450);
		hazardsTextField.setPrefWidth(450);
		areaCleanedTextField.setPrefWidth(450);
		
		//topGridPane.getStylesheets().add(Def.MAINCSS);
		topGridPane.getStyleClass().add("JobCompGridPane");
		
		Label ifYes1 = new Label("If Yes, explain:");
		ifYes1.setPadding(new Insets(0,15,0,0));
		Label ifYes2 = new Label("If Yes, explain:");
		ifYes2.setPadding(new Insets(0,15,0,0));
		Label ifNo = new Label("If No, explain:");
		ifNo.setPadding(new Insets(0,20,0,0));
		

		incidentInjuryHbox.getChildren().addAll(rb1,rb2);
		incidentInjuryStringHbox.getChildren().addAll(ifYes1, IncidentInjuryTextField);
		hazardsHbox.getChildren().addAll(rb3,rb4);
		hazardsStringHbox.getChildren().addAll(ifYes2,hazardsTextField);
		areaCleanedHbox.getChildren().addAll(rb5,rb6,rb7);
		areaCleanedStringHbox.getChildren().addAll(ifNo,areaCleanedTextField);
		incidentInjuryHbox.setSpacing(15);
		hazardsHbox.setSpacing(15);
		areaCleanedHbox.setSpacing(15);
		
		topGridPane.add(incidentInjuryLabel, 0, 0);
		topGridPane.add(incidentInjuryHbox, 1, 0);
		topGridPane.add(incidentInjuryStringHbox, 0, 1);
		GridPane.setColumnSpan(incidentInjuryStringHbox, 2);
		topGridPane.add(hazardsLabel, 0, 2);
		topGridPane.add(hazardsHbox, 1, 2);
		topGridPane.add(hazardsStringHbox, 0, 3);
		GridPane.setColumnSpan(hazardsStringHbox, 2);
		topGridPane.add(areaCleanedLabel, 0, 4);
		topGridPane.add(areaCleanedHbox, 1, 4);
		topGridPane.add(areaCleanedStringHbox, 0, 5);
		GridPane.setColumnSpan(areaCleanedStringHbox, 2);

		topGridPane.setPadding(new Insets(10));
		topGridPane.setVgap(10);
		topGridPane.setHgap(20);
		
		topVbox.getChildren().addAll(topGridPane,createPdfButton);
		topVbox.setSpacing(15);
		topVbox.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().addAll(topVbox);
		setContent(root);

		createPdfButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				Document_Main.updateVariables();
				File file = new File(Document_Main.DEST);
				file.getParentFile().mkdirs();
				try {
					new Pdf_Main().createPdf(Document_Main.DEST, sA);
				} catch (IOException e1) {
					e1.printStackTrace(); 
				}
			}
		});  // inner class close
	}

	public int getRadioButtonState (RadioButton rb1, RadioButton rb2) {
		int selected = 2;  /// this one is for N/A
		if(rb1.isSelected()) {
			selected = 1;
		}
		if(rb2.isSelected()) {
			selected = 0;
		}
		return selected;
	}
	
//	public void writeJobDataToObject() {
//		sA.setHazardsString(hazardsTextField.getText());
//		sA.setIncidentString(IncidentInjuryTextField.getText());
//		sA.setAreaCleanedString(areaCleanedTextField.getText());
//		sA.setIncidentInjuries(getRadioButtonState(rb1,rb2));
//		sA.setHazardsRemain(getRadioButtonState(rb3,rb4));
//		sA.setAreaCleanedUP(getRadioButtonState(rb5,rb6));
//	}

}