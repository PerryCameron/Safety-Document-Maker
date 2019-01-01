package com.safety;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Gui_InformationTab extends Tab {

	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
	SafetyObject aD;
	
	Button chooseAccount = new Button("Choose Account");
	
	GridPane root = new GridPane();

	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Preventive Maintenance",
		        "Corrective Maintenance",
		        "Battery Install",
		        "Start Up Service",
		        "Stand By Service",
		        "Assembly Service"
		    );
	
	TextField dateTextField = new TextField();
	TextField subcontractorTextField = new TextField();
	TextField customerTextField = new TextField();
	TextField locationTextField = new TextField();
	TextField nameTextField = new TextField();
	TextField workOrderTextField = new TextField();
	Label workOrderLabel = new Label("Work Order: ");
	Label nameLabel = new Label("Name: ");
	Label locationLabel = new Label("Location: ");
	Label customerLabel = new Label("Customer: ");
	Label subcontractorLabel = new Label("Subcontractor: ");
	Label dateLabel = new Label("Date: ");
	Label scopeLabel = new Label("Scope of Work: ");
	
	final ComboBox<String> scopeComboBox = new ComboBox<String>(options);
	
	
	Label energizedLabel = new Label("Energized Work?: ");
	HBox radioButtonHBox = new HBox();
	final ToggleGroup group = new ToggleGroup(); // for radio buttons
	RadioButton rb1 = new RadioButton("Yes");
	RadioButton rb2 = new RadioButton("No");

	
	
	// default constructor
	public Gui_InformationTab(SafetyObject aD) { // 
		super();
		this.aD = aD;
		setText("Information");
		//Document_Main.updateGui();
		// properties
		scopeComboBox.getSelectionModel().select(0);
		locationTextField.setMinWidth(30);
		// if user has set first name add it to tab
		if(!Document_Main.users.isEmpty()) {
			String myName = Document_Main.users.get(0).getFirstName() + " " + Document_Main.users.get(0).getLastName();
			nameTextField.setText(myName);
		}
		String strDate = formatter.format(aD.getDateCreated());
		dateTextField.setText(strDate);
		rb1.setToggleGroup(group);
		rb2.setSelected(true);
		rb2.setToggleGroup(group);
		radioButtonHBox.getChildren().addAll(rb1,rb2);
		radioButtonHBox.setSpacing(5);
		root.add(nameLabel, 0, 0);
		root.add(nameTextField, 0, 1);
		root.add(workOrderLabel, 1, 0);
		root.add(workOrderTextField, 1, 1);
		root.add(customerLabel, 2, 0);
		root.add(customerTextField, 2, 1);
		root.add(subcontractorLabel, 3, 0);
		root.add(subcontractorTextField, 3, 1);
		root.add(locationLabel, 0, 3);
		root.add(locationTextField, 0, 4);
		root.add(dateLabel, 1, 3);
		root.add(dateTextField, 1, 4);
		root.add(scopeLabel, 2, 3);
		root.add(scopeComboBox, 2, 4);
		root.add(energizedLabel, 3, 3);
		root.add(radioButtonHBox,3,4);
		
		setContent(root);
	}
}
