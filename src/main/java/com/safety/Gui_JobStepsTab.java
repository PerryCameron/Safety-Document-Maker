package com.safety;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Gui_JobStepsTab extends Tab { 

	Pane root = new Pane();
	Label jobStepsLabel = new Label("Job Step:");
	Label hazardsLabel = new Label("Hazards:");
	Label riskLabel = new Label("Risk:");
	Label controlMeasuresLabel = new Label("Control Measures:");
	Stage popUpStage = new Stage();

	Image pickSteps = new Image(getClass().getResourceAsStream(Document_Main.PICKSTEPS));
	Image saveOk = new Image(getClass().getResourceAsStream(Document_Main.SAVEOK));
	Image saveNok = new Image(getClass().getResourceAsStream(Document_Main.SAVENOK));
	Image editImage = new Image(getClass().getResourceAsStream(Document_Main.EDITICON));
	
	ImageView saveOkImage = new ImageView(saveOk);
	ImageView saveNOkImage = new ImageView(saveNok);

	TextField jobStepsTextField = new TextField();
	TextField hazardsTextField = new TextField();
	TextField controlMeasuresTextField = new TextField();

	ObservableList<String> riskInformation = 
			FXCollections.observableArrayList(
					"Low",
					"Medium",
					"High"
					);

	final ToggleGroup riskGroup = new ToggleGroup();
	RadioButton rb1 = new RadioButton("Low");
	RadioButton rb2 = new RadioButton("Medium");
	RadioButton rb3 = new RadioButton("High");

	Button addButton = new Button("Add Step");
	Button delButton = new Button("Delete");
	Button preDefinedButton = new Button("");
	Button generalDeleteButton = new Button("Delete");
	Button batteryDeleteButton = new Button("Delete");
	Button preventiveDeleteButton = new Button("Delete");
	Button correctiveDeleteButton = new Button("Delete");
	Button generalEditButton = new Button("Edit");
	Button batteryEditButton = new Button("Edit");
	Button preventiveEditButton = new Button("Edit");
	Button correctiveEditButton = new Button("Edit");

	HBox jobStepsHBox = new HBox(15);
	HBox hazardsHBox = new HBox(15);
	HBox riskHBox = new HBox(15);
	HBox controlMeasuresHBox = new HBox(15);
	HBox feedBackHBox = new HBox(15);
	HBox textPlusButtonHBox = new HBox();
	HBox preGeneralStepsTabHbox = new HBox(20);
	HBox preBatteryStepsTabHbox = new HBox(20);
	HBox prePreventiveStepsTabHbox = new HBox(20);
	HBox preCorrectiveStepsTabHbox = new HBox(20);
	HBox riskOutSideHBox = new HBox(140);  /// change number here

	VBox jobStepsVBox = new VBox(15);
	VBox mainVBox= new VBox(15);
	VBox generalJobStepsVBox = new VBox(15);
	VBox batteryJobStepsVBox = new VBox(15);
	VBox preventiveJobStepsVBox = new VBox(15);
	VBox correctiveJobStepsVBox = new VBox(15);
	VBox imageHoldingVBox = new VBox();

	TitledPane jobStepsTitledPane;
	TitledPane listedStepsTitledPane;

	TabPane stepsTabPane = new TabPane();

	Tab customTab = new Tab("Custom");
	Tab generalTab = new Tab("General");
	Tab batteryTab = new Tab("Battery");
	Tab pmaintenanceTab = new Tab("Preventive");
	Tab cmaintenanceTab = new Tab("Corrective");

	ListView<String> listView = new ListView<String>();
	ListView<String> listViewGeneral = new ListView<String>();
	ListView<String> listViewBattery = new ListView<String>();
	ListView<String> listViewCm = new ListView<String>();
	ListView<String> listViewPm = new ListView<String>();

	public Gui_JobStepsTab() {
		super();
		setText("Job Steps");

		rb1.setToggleGroup(riskGroup);
		rb2.setToggleGroup(riskGroup);
		rb3.setToggleGroup(riskGroup);
		rb1.setSelected(true);

		jobStepsVBox.setPadding(new Insets(15,15,15,15));
		preDefinedButton.setGraphic(new ImageView(pickSteps));
		jobStepsVBox.setPrefWidth(670);
		listView.setPrefWidth(550);
		listViewGeneral.setPrefWidth(550);
		listViewBattery.setPrefWidth(550);
		listViewPm.setPrefWidth(550);
		listViewCm.setPrefWidth(550);
		listView.setPrefHeight(200);
		mainVBox.setPadding(new Insets(15,15,15,15));

		jobStepsHBox.setAlignment(Pos.CENTER_RIGHT);
		jobStepsHBox.setSpacing(20);
		hazardsHBox.setSpacing(29);
		hazardsHBox.setAlignment(Pos.CENTER_RIGHT);
		feedBackHBox.setSpacing(20);
		controlMeasuresHBox.setAlignment(Pos.CENTER_RIGHT);
		riskHBox.setAlignment(Pos.CENTER);

		controlMeasuresHBox.setSpacing(20);
		jobStepsTextField.setPrefWidth(520);  // was 550 before adding button
		hazardsTextField.setPrefWidth(550);
		controlMeasuresTextField.setPrefWidth(500);

		textPlusButtonHBox.getChildren().addAll(jobStepsTextField,preDefinedButton);
		jobStepsHBox.getChildren().addAll(jobStepsLabel,textPlusButtonHBox);
		hazardsHBox.getChildren().addAll(hazardsLabel,hazardsTextField);


		riskHBox.getChildren().addAll(riskLabel,rb1,rb2,rb3);
		//riskHBox.setAlignment(Pos.CENTER);
		// the entire purpose of imageHoldingVBox is to hold one object so it can be cleard and a new one added later.
		imageHoldingVBox.getChildren().add(saveNOkImage);
		riskOutSideHBox.getChildren().addAll(imageHoldingVBox,riskHBox, addButton);
		riskOutSideHBox.setAlignment(Pos.CENTER);

		controlMeasuresHBox.getChildren().addAll(controlMeasuresLabel,controlMeasuresTextField);
		feedBackHBox.getChildren().addAll(listView,delButton);

		///////  Tab formatting here ////////////
		generalJobStepsVBox.getChildren().addAll(generalDeleteButton, generalEditButton);
		batteryJobStepsVBox.getChildren().addAll(batteryDeleteButton, batteryEditButton);
		preventiveJobStepsVBox.getChildren().addAll(preventiveDeleteButton, preventiveEditButton);
		correctiveJobStepsVBox.getChildren().addAll(correctiveDeleteButton, correctiveEditButton);

		preGeneralStepsTabHbox.getChildren().addAll(listViewGeneral,generalJobStepsVBox);
		preGeneralStepsTabHbox.setPadding(new Insets(10));
		preBatteryStepsTabHbox.getChildren().addAll(listViewBattery,batteryJobStepsVBox);
		preBatteryStepsTabHbox.setPadding(new Insets(10));
		prePreventiveStepsTabHbox.getChildren().addAll(listViewPm,preventiveJobStepsVBox);
		prePreventiveStepsTabHbox.setPadding(new Insets(10));
		preCorrectiveStepsTabHbox.getChildren().addAll(listViewCm,correctiveJobStepsVBox);
		preCorrectiveStepsTabHbox.setPadding(new Insets(10));

		generalTab.setContent(preGeneralStepsTabHbox);
		batteryTab.setContent(preBatteryStepsTabHbox);
		pmaintenanceTab.setContent(prePreventiveStepsTabHbox);
		cmaintenanceTab.setContent(preCorrectiveStepsTabHbox);

		stepsTabPane.setMaxHeight(215);

		/// fills all the list views up for pre-made steps
		refreshListViews();

		stepsTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		// currently what goes in the titled pane
		jobStepsVBox.getChildren().addAll(jobStepsHBox,hazardsHBox,controlMeasuresHBox,riskOutSideHBox);
		// we need to put a tabbed pane in here
		jobStepsTitledPane = new TitledPane("Job Steps", stepsTabPane);
		// put vbox in tab
		customTab.setContent(jobStepsVBox);
		// put tabs in pane
		stepsTabPane.getTabs().setAll(customTab, generalTab, batteryTab, cmaintenanceTab, pmaintenanceTab);

		listedStepsTitledPane = new TitledPane("Listed Steps", feedBackHBox);
		mainVBox.getChildren().addAll(jobStepsTitledPane,listedStepsTitledPane);
		root.getChildren().addAll(mainVBox); //feedBackHBox
		setContent(root);

		preDefinedButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				Gui_jobStepsPopUp.predefinedJobStepsMenu(popUpStage,saveOkImage, saveNOkImage);
			}
		});

		addButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				// we are permantly going to save this step
				if(Document_Main.saveStepToFile) {
					// StepsObject statically retrieves the info from the form on creation
					Document_Main.document.get(0).setStep(new StepsObject(Document_Main.stepCategory));
					// StepsObject statically retrieves the info from the form on creation
					Document_Main.setStep(new StepsObject(Document_Main.stepCategory));
					Document_Main.saveStepObjects();
					System.out.println("We are going to save it to catagory: " + Document_Main.stepCategory);
					System.out.println("The step list array is now: " + Document_Main.jobSteps.size());
					// this resets saving the step back to false (default)
					Document_Main.saveStepToFile = false;
					imageHoldingVBox.getChildren().clear();
					imageHoldingVBox.getChildren().add(saveNOkImage);
					clearListViews();
					refreshListViews();
					// we are not saving it
				} else {
					Document_Main.document.get(0).setStep(new StepsObject(0));
					System.out.println("We are not saving this step");
				}
				// clears the listview
				listView.getItems().clear();
				// prints the fields to the listview
				Document_Main.printListElements();
				// this clears the fields that you type info into after you add a step
				Document_Main.clearFields();

			}
		});  

		delButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listView.getItems().isEmpty()) {  // prevents exception if no elements to delete
					Document_Main.document.get(0).removeStep(listView.getSelectionModel().getSelectedIndex()); 
					listView.getItems().clear();
					Document_Main.printListElements();
				}
			}
		});

		generalDeleteButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewGeneral.getItems().isEmpty()) {  // prevents exception if no elements to delete
					if(confirmDelete(listViewGeneral.getSelectionModel().selectedItemProperty().getValue())) {
						System.out.println("Removed Step: " + listViewGeneral.getSelectionModel().selectedItemProperty().getValue());
						Document_Main.removeStep(getStepsElement(listViewGeneral.getSelectionModel().selectedItemProperty().getValue()));
						clearListViews();
						refreshListViews();
						Document_Main.saveStepObjects();
					}
				}
			}
		});

		batteryDeleteButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewBattery.getItems().isEmpty()) {  // prevents exception if no elements to delete
					if(confirmDelete(listViewBattery.getSelectionModel().selectedItemProperty().getValue())) {
						System.out.println("Removed Step: " + listViewBattery.getSelectionModel().selectedItemProperty().getValue());
						Document_Main.removeStep(getStepsElement(listViewBattery.getSelectionModel().selectedItemProperty().getValue()));
						clearListViews();
						refreshListViews();
						Document_Main.saveStepObjects();
					}
				}
			}
		});

		correctiveDeleteButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewCm.getItems().isEmpty()) {  // prevents exception if no elements to delete
					if(confirmDelete(listViewCm.getSelectionModel().selectedItemProperty().getValue())) {
						System.out.println("Removed Step: " + listViewCm.getSelectionModel().selectedItemProperty().getValue());
						Document_Main.removeStep(getStepsElement(listViewCm.getSelectionModel().selectedItemProperty().getValue()));
						clearListViews();
						refreshListViews();
						Document_Main.saveStepObjects();
					}
				}
			}
		});

		preventiveDeleteButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewPm.getItems().isEmpty()) {  // prevents exception if no elements to delete
					if(confirmDelete(listViewPm.getSelectionModel().selectedItemProperty().getValue())) {
						System.out.println("Removed Step: " + listViewPm.getSelectionModel().selectedItemProperty().getValue());
						Document_Main.removeStep(getStepsElement(listViewPm.getSelectionModel().selectedItemProperty().getValue()));
						clearListViews();
						refreshListViews();
						Document_Main.saveStepObjects();
					}
				}
			}
		});

		listViewGeneral.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
					if(mouseEvent.getClickCount() == 2){
						for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
							// compare string we clicked to string in jobStep variable
							if(listViewGeneral.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
								Document_Main.document.get(0).setStep(Document_Main.jobSteps.get(i));
								System.out.println("Element " + i + " contains the correct object ");
							}
						}
						// clears the listview
						listView.getItems().clear();
						Document_Main.printListElements();
					}
				}
			}
		});	

		listViewBattery.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
					if(mouseEvent.getClickCount() == 2){
						for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
							// compare string we clicked to string in jobStep variable
							if(listViewBattery.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
								Document_Main.document.get(0).setStep(Document_Main.jobSteps.get(i));
							}
						}
						// clears the listview
						listView.getItems().clear();
						// refreshes the listview
						Document_Main.printListElements();
					}
				}
			}
		});	

		listViewCm.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
					if(mouseEvent.getClickCount() == 2){
						for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
							// compare string we clicked to string in jobStep variable
							if(listViewCm.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
								Document_Main.document.get(0).setStep(Document_Main.jobSteps.get(i));
							}
						}
						// clears the listview
						listView.getItems().clear();
						// refreshes the listview
						Document_Main.printListElements();
					}
				}
			}
		});	

		listViewPm.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
					if(mouseEvent.getClickCount() == 2){
						for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
							// compare string we clicked to string in jobStep variable
							if(listViewPm.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
								Document_Main.document.get(0).setStep(Document_Main.jobSteps.get(i));
							}
						}
						// clears the listview
						listView.getItems().clear();
						// refreshes the listview
						Document_Main.printListElements();
					}
				}
			}
		});	

		generalEditButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewGeneral.getItems().isEmpty()) {  // prevents exception if no elements to edit
					int elementSelected = 0; // element in jobsteps we are interested in
					for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
						// compare string we clicked to string in jobStep variable
						if(listViewGeneral.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
							elementSelected = i;
						}
					}
					preGeneralStepsTabHbox.getChildren().clear();
					preGeneralStepsTabHbox.getChildren().add(Gui_EditSteps.makeStepEditGui(elementSelected, editImage));
				}
			}
		});
		
		batteryEditButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewBattery.getItems().isEmpty()) {  // prevents exception if no elements to edit
					int elementSelected = 0; // element in jobsteps we are interested in
					for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
						// compare string we clicked to string in jobStep variable
						if(listViewBattery.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
							elementSelected = i;
						}
					}
					preBatteryStepsTabHbox.getChildren().clear();
					preBatteryStepsTabHbox.getChildren().add(Gui_EditSteps.makeStepEditGui(elementSelected, editImage));
				}
			}
		});
		
		correctiveEditButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewCm.getItems().isEmpty()) {  // prevents exception if no elements to edit
					int elementSelected = 0; // element in jobsteps we are interested in
					for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
						// compare string we clicked to string in jobStep variable
						if(listViewCm.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
							elementSelected = i;
						}
					}
					preCorrectiveStepsTabHbox.getChildren().clear();
					preCorrectiveStepsTabHbox.getChildren().add(Gui_EditSteps.makeStepEditGui(elementSelected, editImage));
				}
			}
		});
		
		preventiveEditButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listViewPm.getItems().isEmpty()) {  // prevents exception if no elements to edit
					int elementSelected = 0; // element in jobsteps we are interested in
					for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
						// compare string we clicked to string in jobStep variable
						if(listViewPm.getSelectionModel().selectedItemProperty().getValue().equals(Document_Main.jobSteps.get(i).getJobSteps())) {
							elementSelected = i;
						}
					}
					prePreventiveStepsTabHbox.getChildren().clear();
					prePreventiveStepsTabHbox.getChildren().add(Gui_EditSteps.makeStepEditGui(elementSelected, editImage));
				}
			}
		});
		
	}

	public int getStepsElement(String compareString) {
		int finalValue = 0;
		for(int i = 0; i < Document_Main.jobSteps.size(); i++) { 
			if(Document_Main.jobSteps.get(i).getJobSteps().equals(compareString)) {
				finalValue = i;
				System.out.println("The match we found is Element " + i);
			}
		}
		return finalValue;
	}

	public void refreshListViews() {
		for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
			switch (Document_Main.jobSteps.get(i).getStepType()) {
			case 1:  listViewGeneral.getItems().add(Document_Main.jobSteps.get(i).getJobSteps());
			break;
			case 2:  listViewBattery.getItems().add(Document_Main.jobSteps.get(i).getJobSteps());
			break;
			case 3:  listViewCm.getItems().add(Document_Main.jobSteps.get(i).getJobSteps());
			break;
			case 4:  listViewPm.getItems().add(Document_Main.jobSteps.get(i).getJobSteps());
			break;
			}
		}
	}

	public void clearListViews() {
		listViewGeneral.getItems().clear();
		listViewBattery.getItems().clear();
		listViewCm.getItems().clear();
		listViewPm.getItems().clear();
	}

	public boolean confirmDelete(String objectToBeDeleted) {
		boolean answer = false;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Are you sure you want to delete this step?");
		alert.setContentText(objectToBeDeleted);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			answer = true;
		} else {
			answer = false;
		}
		return answer;
	}
}