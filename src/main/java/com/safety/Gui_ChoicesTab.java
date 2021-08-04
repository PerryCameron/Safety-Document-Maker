package com.safety;

import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Gui_ChoicesTab extends Tab {
	public HashMap<String, CheckBox> checkBoxHashMap;
	private TitledPane topicsTitledPane;
	private VBox choicesBox = new VBox(10); // holds individual selections
	
	public Gui_ChoicesTab() {
		super();
		this.checkBoxHashMap = createHashMap();
		setText("Safety Checkoff");
		HBox choicesPane = new HBox(); // main pane
		
		choicesPane.setPadding(new Insets(15,15,15,15));
		choicesBox.setPadding(new Insets(30,10,10,30));
		topicsTitledPane = new TitledPane("Risk Assessment", createListView());
		topicsTitledPane.setPrefHeight(295);
		topicsTitledPane.getStyleClass().add("titledpane-topic");
		choicesPane.getChildren().addAll(topicsTitledPane, choicesBox);
		setContent(choicesPane);

	}
	
	public int getHashLength() {
		return checkBoxHashMap.size();
	}

	public HashMap<String, CheckBox> createHashMap() {
		HashMap<String, CheckBox> hashMap = new HashMap<String, CheckBox>();  // creates hashmap putting string with boolean
		for(int p = 0; p < Document_Main.document.get(0).getSafetyCheckarraysRows(); p++) {  /// for each row
			for(int q = 0; q < Document_Main.document.get(0).getSafetyCheckNumberOfElements(p); q++) {  /// for each element of that row
				hashMap.put(Document_Main.document.get(0).getSafetyCheckarrays(p,q), new CheckBox(Document_Main.document.get(0).getSafetyCheckarrays(p,q)));// match strings to their boolean
				/// key = text out of 2D array, match = checkbox
			}
		}
		return hashMap;
	}
	
	public void saveHashtoObject() {  /// writes all values from checkboxs to sA object
		for(int p = 0; p < Document_Main.document.get(0).getSafetyCheckarraysRows(); p++) {
			for(int q = 0; q < Document_Main.document.get(0).getSafetyCheckNumberOfElements(p); q++) {
				Document_Main.document.get(0).setSafetyBooleanValue(checkBoxHashMap.get(Document_Main.document.get(0).getSafetyCheckarrays(p,q)).isSelected(), Document_Main.document.get(0).getSafetyCheckarrays(p,q)); 
			}
		}
	}

	public HBox createListView() {

		HBox listViewPanel = new HBox();
		
		ListView<String> listView;
		listView = new ListView<String>(FXCollections.observableArrayList(
				"Pre-job","Electrical PPE Required"
				,"Fit For work", "Electrical Hazards"
				, "Ergonomic Hazards", "Access/Egress Hazards"
				, "Working at Heights", "Environmental Hazards"
				, "Other Hazards", "Close out"));

		listView.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<String>() {
			public void changed(
					ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
					choicesBox.getChildren().clear();
					if(newValue.equals("Pre-job")) {
						createChoiceRow(0);
					}
					if(newValue.equals("Electrical PPE Required")) {
						createChoiceRow(1);
					}
					if(newValue.equals("Fit For work")) {
						createChoiceRow(2);
					}
					if(newValue.equals("Electrical Hazards")) {
						createChoiceRow(3);
					}
					if(newValue.equals("Ergonomic Hazards")) {
						createChoiceRow(4);
					}
					if(newValue.equals("Access/Egress Hazards")) {
						createChoiceRow(5);
					}
					if(newValue.equals("Working at Heights")) {
						createChoiceRow(6);
					}					
					if(newValue.equals("Environmental Hazards")) {
						createChoiceRow(7);
					}
					if(newValue.equals("Other Hazards")) {
						createChoiceRow(8);
					}
					if(newValue.equals("Close out")) {
						createChoiceRow(9);
					}
			}
		});
		listView.getSelectionModel().select(0);
		listViewPanel.getChildren().addAll(listView);
		return listViewPanel;
	}

	public void createChoiceRow(int selection) {
		for(int i = 0; i < Document_Main.document.get(0).getSafetyCheckNumberOfElements(selection); i++) {
			// gets correct CheckBoxes and adds them to panel
			choicesBox.getChildren().addAll(checkBoxHashMap.get(Document_Main.document.get(0).getSafetyCheckarrays(selection,i)));
			// checks to see if the checkbox should be checked
			if(Document_Main.document.get(0).getSafetyStepsMapValue(Document_Main.document.get(0).getSafetyCheckarrays(selection,i))) {
				// checks Checkbox if the hashmapped boolean value is true
				checkBoxHashMap.get(Document_Main.document.get(0).getSafetyCheckarrays(selection,i)).setSelected(true);
			}
		}
	}
}
