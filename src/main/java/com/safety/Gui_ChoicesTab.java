package com.safety;

import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Gui_ChoicesTab extends Tab {
	private SafetyObject sA;
	private VBox choicesBox = new VBox();
	public HashMap<String, CheckBox> checkBoxHashMap;

	public Gui_ChoicesTab(SafetyObject sA) {
		super();
		this.sA = sA;
		this.checkBoxHashMap = createHashMap();
		setText("Safety Checkoff");
		HBox choicesPane = new HBox();
		choicesBox.setSpacing(10);
		choicesBox.setPadding(new Insets(30,10,10,30));
		choicesPane.getChildren().addAll(createListView(), choicesBox);
		setContent(choicesPane);

	}
	
	public int getHashLength() {
		return checkBoxHashMap.size();
	}

	public HashMap<String, CheckBox> createHashMap() {
		HashMap<String, CheckBox> hashMap = new HashMap<String, CheckBox>();  // creates hashmap putting string with boolean
		for(int p = 0; p < sA.getSafetyCheckarraysRows(); p++) {  /// for each row
			for(int q = 0; q < sA.getSafetyCheckNumberOfElements(p); q++) {  /// for each element of that row
				hashMap.put(sA.getSafetyCheckarrays(p,q), new CheckBox(sA.getSafetyCheckarrays(p,q)));// match strings to their boolean
				/// key = text out of 2D array, match = checkbox
			}
		}
		return hashMap;
	}
	
	public void saveHashtoObject() {  /// writes all values from checkboxs to sA object
		for(int p = 0; p < sA.getSafetyCheckarraysRows(); p++) {
			for(int q = 0; q < sA.getSafetyCheckNumberOfElements(p); q++) {
				sA.setSafetyBooleanValue(checkBoxHashMap.get(sA.getSafetyCheckarrays(p,q)).isSelected(), sA.getSafetyCheckarrays(p,q)); 
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
		for(int i = 0; i < sA.getSafetyCheckNumberOfElements(selection); i++) {
			// gets correct CheckBoxes and adds them to panel
			choicesBox.getChildren().addAll(checkBoxHashMap.get(sA.getSafetyCheckarrays(selection,i)));
			// checks to see if the checkbox should be checked
			if(sA.getSafetyStepsMapValue(sA.getSafetyCheckarrays(selection,i))) {
				// checks Checkbox if the hashmapped boolean value is true
				checkBoxHashMap.get(sA.getSafetyCheckarrays(selection,i)).setSelected(true);
			}
		}
	}
}
