package com.safety;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Gui_InformationTab extends Tab {

	Button chooseAccount = new Button("Choose Account");
	
	VBox root = new VBox(15);

	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Preventive Maintenance",
		        "Corrective Maintenance",
		        "Battery Install",
		        "Start Up Service",
		        "Stand By Service",
		        "Assembly Service",
		        "Pre-Contract Audit"
		    );
	
	ObservableList<String> states = 
		    FXCollections.observableArrayList(
		    		"AL","AK","AZ","AR","CA","CO","CT","DE","DC","FL","GA","HI","ID","IL","IN","IA","KS","KY"
		    		,"LA","ME","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","MD","MA","MI"
		    		,"MN","MS","MO","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"
		    );
	
	TextField subcontractorTextField = new TextField();
	TextField customerTextField = new TextField();
	
	TextField addressTextField = new TextField();
	TextField cityTextField = new TextField();
	TextField zipTextField = new TextField();
	
	TextField nameTextField = new TextField();
	TextField workOrderTextField = new TextField();
	Label workOrderLabel = new Label("Work Order: ");
	Label nameLabel = new Label("Name: ");
	
	Label addressLabel = new Label("Address: ");
	Label cityLabel = new Label("City: ");
	Label stateLabel = new Label("State: ");
	Label zipLabel = new Label("Zip: ");
	
	Label customerLabel = new Label("Customer: ");
	Label subcontractorLabel = new Label("Subcontractor: ");
	Label dateLabel = new Label("Date: ");
	Label scopeLabel = new Label("Scope of Work: ");
	
	HBox addressHBox = new HBox(10);
	HBox cityHBox = new HBox(10);
	VBox locationVBox = new VBox(10);
	HBox scopeHBox = new HBox(10);
	HBox energizedHBox = new HBox(10);
	DatePicker checkInDatePicker;
	
	GridPane detailsGridPane = new GridPane();

	HBox dateHBox = new HBox(10);
	static TitledPane detailsTitledPane;
	static TitledPane addressTitledPane;
	static TitledPane dateTitledPane;
	static TitledPane workTitledPane;
	
	final ComboBox<String> scopeComboBox = new ComboBox<String>(options);
	final ComboBox<String> stateComboBox = new ComboBox<String>(states);
	
	Label energizedLabel = new Label("Energized Work?: ");
	HBox radioButtonHBox = new HBox();
	final ToggleGroup group = new ToggleGroup(); // for radio buttons
	RadioButton rb1 = new RadioButton("Yes");
	RadioButton rb2 = new RadioButton("No");

	// default constructor
	public Gui_InformationTab() { // 
		super();
		setText("Information");
		// properties
		checkInDatePicker = new DatePicker();
		scopeComboBox.getSelectionModel().select(0);
		addressTextField.setMinWidth(30);
		// if user has set name add it to tab
		if(!Document_Main.users.isEmpty()) {
			String myName = Document_Main.users.get(0).getFirstName() + " " + Document_Main.users.get(0).getLastName();
			nameTextField.setText(myName);
		}
		subcontractorTextField.setText("none");  // default value of none to start
		rb1.setToggleGroup(group);
		rb2.setSelected(true);
		rb2.setToggleGroup(group);
		
		// attributes
		stateComboBox.getSelectionModel().select(14);  // sets default state
		addressTextField.setPrefWidth(400);
		zipTextField.setPrefWidth(70);	
		radioButtonHBox.setSpacing(5);
		checkInDatePicker.setValue(LocalDate.now());
		addressHBox.setAlignment(Pos.CENTER_LEFT);
		cityHBox.setAlignment(Pos.CENTER_LEFT);
		dateHBox.setAlignment(Pos.CENTER_LEFT);
		scopeHBox.setAlignment(Pos.CENTER_LEFT);
		radioButtonHBox.setAlignment(Pos.CENTER_LEFT);
		root.setPadding(new Insets(15,15,0,15));
		
		// additions
		addressHBox.getChildren().addAll(addressLabel,addressTextField);
		cityHBox.getChildren().addAll(cityLabel,cityTextField,stateLabel,stateComboBox,zipLabel,zipTextField);
		locationVBox.getChildren().addAll(addressHBox,cityHBox);
		dateHBox.getChildren().addAll(dateLabel,checkInDatePicker);
		scopeHBox.getChildren().addAll(scopeLabel,scopeComboBox);
		radioButtonHBox.getChildren().addAll(energizedLabel,rb1,rb2);
		detailsGridPane.setHgap(10);
		detailsGridPane.setVgap(10);
		detailsGridPane.add(nameLabel, 0, 0);
		detailsGridPane.add(nameTextField, 1, 0);
		detailsGridPane.add(scopeHBox, 2, 0);
		detailsGridPane.add(workOrderLabel, 0, 1);
		detailsGridPane.add(workOrderTextField, 1, 1);
		detailsGridPane.add(customerLabel, 0, 2);
		detailsGridPane.add(customerTextField, 1, 2);
		detailsGridPane.add(radioButtonHBox, 2, 2);
		detailsGridPane.add(subcontractorLabel, 0, 3);
		detailsGridPane.add(subcontractorTextField, 1, 3);
		detailsTitledPane = new TitledPane("Details", detailsGridPane);
		addressTitledPane = new TitledPane("Location", locationVBox);
		dateTitledPane = new TitledPane("Date",dateHBox);
		root.getChildren().addAll(detailsTitledPane,dateTitledPane,addressTitledPane);

		setContent(root);
	}
}
