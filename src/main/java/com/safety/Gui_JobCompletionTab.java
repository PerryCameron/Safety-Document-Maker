package com.safety;



import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Gui_JobCompletionTab extends Tab {

	Label incidentInjuryLabel = new Label("Were there any incident/injuries?");
	Label hazardsLabel = new Label("Are there Hazards remaining?");
	Label areaCleanedLabel = new Label("Was the area cleaned up at the end of job/shift?");
	
	final Separator separator1 = new Separator();
	final Separator separator2 = new Separator();

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
	TextField savePDFToTextField = new TextField();

	//CheckBox addSignatureCheckBox = new CheckBox("Add Signature");
	CheckBox openOnCreationCheckBox = new CheckBox("Open PDF on creation");
    CheckBox attachSignatureCheckBox = new CheckBox("Attach Signature");
    
	Button createPdfButton = new Button("Create PDF");
	Button savePDFToButton = new Button("Save To...");
	/// layout objects


	Pane topPane = new Pane();

	HBox incidentInjuryHbox = new HBox(15);
	HBox incidentInjuryStringHbox = new HBox(15);
	HBox hazardsHbox = new HBox(15);
	HBox hazardsStringHbox = new HBox(15);
	HBox areaCleanedHbox = new HBox(15);
	HBox areaCleanedStringHbox = new HBox(15);
	HBox setSaveDirectoryHBox = new HBox(15);
	HBox createPDFHBox = new HBox(15);
	GridPane topGridPane = new GridPane();

	TitledPane jobCompletionTitledPane;
	TitledPane finalTitledPane;
	
	VBox mainVBox = new VBox(15);
	VBox finalVBox = new VBox(15);
	
	DirectoryChooser directoryChooser = new DirectoryChooser();

	public Gui_JobCompletionTab() {
		super();

		//this.stepsTab = stepsTab;
		setText("Job Completion");
		jobCompletionTitledPane = new TitledPane("Job Completion", topGridPane);
		finalTitledPane = new TitledPane("Final Steps", finalVBox);
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
		savePDFToTextField.setPrefWidth(500);
		savePDFToTextField.setText(Document_Main.getCustomerDirectory());
		attachSignatureCheckBox.setSelected(true);
		
		topGridPane.getStyleClass().add("JobCompGridPane");
		
		Label ifYes1 = new Label("If Yes, explain:");
		ifYes1.setPadding(new Insets(0,15,0,0));
		Label ifYes2 = new Label("If Yes, explain:");
		ifYes2.setPadding(new Insets(0,15,0,0));
		Label ifNo = new Label("If No, explain:");
		ifNo.setPadding(new Insets(0,20,0,0));
		
		createPdfButton.setStyle("-fx-background-color: #48DE48; ");
		incidentInjuryHbox.getChildren().addAll(rb1,rb2);
		incidentInjuryStringHbox.getChildren().addAll(ifYes1, IncidentInjuryTextField);
		hazardsHbox.getChildren().addAll(rb3,rb4);
		hazardsStringHbox.getChildren().addAll(ifYes2,hazardsTextField);
		areaCleanedHbox.getChildren().addAll(rb5,rb6,rb7);
		areaCleanedStringHbox.getChildren().addAll(ifNo,areaCleanedTextField);
		setSaveDirectoryHBox.getChildren().addAll(savePDFToTextField,savePDFToButton);
		createPDFHBox.getChildren().addAll(openOnCreationCheckBox,attachSignatureCheckBox,createPdfButton);
		finalVBox.getChildren().addAll(setSaveDirectoryHBox,createPDFHBox);
		
		incidentInjuryHbox.setAlignment(Pos.CENTER_LEFT);
		incidentInjuryStringHbox.setAlignment(Pos.CENTER_LEFT);
		hazardsHbox.setAlignment(Pos.CENTER_LEFT);
		hazardsStringHbox.setAlignment(Pos.CENTER_LEFT);
		areaCleanedHbox.setAlignment(Pos.CENTER_LEFT);
		areaCleanedStringHbox.setAlignment(Pos.CENTER_LEFT);
		createPDFHBox.setAlignment(Pos.CENTER_RIGHT);
		setSaveDirectoryHBox.setAlignment(Pos.CENTER_LEFT);
		
		separator1.getStyleClass().add("separator1");
		separator2.getStyleClass().add("separator2");
		
		topGridPane.add(incidentInjuryLabel, 0, 0);
		topGridPane.add(incidentInjuryHbox, 1, 0);
		topGridPane.add(incidentInjuryStringHbox, 0, 1);
		GridPane.setColumnSpan(incidentInjuryStringHbox, 2);
		
		topGridPane.add(separator1, 0, 2);
		GridPane.setColumnSpan(separator1, 2);
		
		topGridPane.add(hazardsLabel, 0, 3);
		topGridPane.add(hazardsHbox, 1, 3);
		topGridPane.add(hazardsStringHbox, 0, 4);
		GridPane.setColumnSpan(hazardsStringHbox, 2);
		
		topGridPane.add(separator2, 0, 5);
		GridPane.setColumnSpan(separator2, 2);
		
		topGridPane.add(areaCleanedLabel, 0, 6);
		topGridPane.add(areaCleanedHbox, 1, 6);
		topGridPane.add(areaCleanedStringHbox, 0, 7);
		GridPane.setColumnSpan(areaCleanedStringHbox, 2);

		topGridPane.setPadding(new Insets(15));
		topGridPane.setVgap(10);
		topGridPane.setHgap(20);
		
		mainVBox.getChildren().addAll(jobCompletionTitledPane,finalTitledPane);
		mainVBox.setPadding(new Insets(15, 15, 15, 15));
		setContent(mainVBox);

		createPdfButton.setOnAction(new EventHandler<ActionEvent>() {   /// creates the PDF
			public void handle(ActionEvent e) {
				Document_Main.updateVariables();
				//File file = new File(System.getProperty("user.home"));
				//file.getParentFile().mkdirs();
				try {  // saves PDF to specified directory
					new Pdf_Main().createPdf(Document_Main.saveDirectory + "\\" + Document_Main.saFileName);
				} catch (IOException e1) {
					e1.printStackTrace(); 
				}
				Document_Main.saveSafetyObjects(new File(Document_Main.saveDirectory 
						+ "\\" 
						+ Document_Main.document.get(0).getWorkOrder() 
						+ "_"
						+ Document_Main.document.get(0).getCustomer()
						+ ".sad"));
			}
		});  // inner class close
		
		savePDFToButton.setOnAction(new EventHandler<ActionEvent>() {   /// puts where we save PDF to
			public void handle(ActionEvent e) {
				File file = new File(Document_Main.getCustomerDirectory());
				directoryChooser.setInitialDirectory(file);
				Stage thisStage = new Stage();
				File selectedDirectory = directoryChooser.showDialog(thisStage);
				if(selectedDirectory == null){
				     //No Directory selected
				}else{
					savePDFToTextField.setText(selectedDirectory.getAbsolutePath());
					Document_Main.saveDirectory = selectedDirectory.getAbsolutePath();
				}
			}
		});  // inner class close
		
		openOnCreationCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            // TODO Auto-generated method stub
	            if(newValue){
	            	// Check Box is checked
	              Document_Main.openDocumentUponCreation = true;
	            } else {
	            	// Check Box is unchecked
	            	Document_Main.openDocumentUponCreation = false;
	            }
	        }
	    });
		
		attachSignatureCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            // TODO Auto-generated method stub
	            if(newValue){
	            	// Check Box is checked
	              Document_Main.signatureEnable = true;
	            } else {
	            	// Check Box is unchecked
	            	Document_Main.signatureEnable = false;
	            }
	        }
	    });
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
}