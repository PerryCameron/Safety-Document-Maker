package com.safety;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Gui_JobStepsTab extends Tab { 
	SafetyObject sA;
	Pane root = new Pane();
	Label jobStepsLabel = new Label("Job Steps:");
	Label hazardsLabel = new Label("Hazards:");
	Label riskLabel = new Label("Risk:");
	Label controlMeasuresLabel = new Label("Control Measures:");
	
	static TextField jobStepsTextField = new TextField();
	static TextField hazardsTextField = new TextField();
	static TextField controlMeasuresTextField = new TextField();
	
	ObservableList<String> riskInformation = 
		    FXCollections.observableArrayList(
		        "Low",
		        "Medium",
		        "High"
		    );
	
	final ToggleGroup riskGroup = new ToggleGroup();
	static RadioButton rb1 = new RadioButton("Low");
	static RadioButton rb2 = new RadioButton("Medium");
	RadioButton rb3 = new RadioButton("High");
	
	Button addButton = new Button("Add Step");
	Button delButton = new Button("Delete");
	
	HBox jobStepsHBox = new HBox();
	HBox hazardsHBox = new HBox();
	HBox riskHBox = new HBox();
	HBox controlMeasuresHBox = new HBox();
	HBox feedBackHBox = new HBox();
	
	VBox mainVBox = new VBox();

	ListView<String> listView = new ListView<String>();
	
	public Gui_JobStepsTab(final SafetyObject sA) {
		super();
		this.sA = sA;
		setText("Job Steps");
		
		rb1.setToggleGroup(riskGroup);
		rb2.setToggleGroup(riskGroup);
		rb3.setToggleGroup(riskGroup);
		rb1.setSelected(true);
		
		riskHBox.setSpacing(15);
		mainVBox.setPadding(new Insets(15,0,0,15));
		mainVBox.setSpacing(15);
		mainVBox.setPrefWidth(670);
		listView.setPrefWidth(550);
		

		jobStepsHBox.setAlignment(Pos.CENTER_RIGHT);
		jobStepsHBox.setSpacing(20);
		hazardsHBox.setSpacing(29);
		hazardsHBox.setAlignment(Pos.CENTER_RIGHT);
		feedBackHBox.setSpacing(20);
		controlMeasuresHBox.setAlignment(Pos.CENTER_RIGHT);
		riskHBox.setAlignment(Pos.CENTER);
		controlMeasuresHBox.setSpacing(20);
		jobStepsTextField.setPrefWidth(560);
		hazardsTextField.setPrefWidth(560);
		controlMeasuresTextField.setPrefWidth(510);
		
		jobStepsHBox.getChildren().addAll(jobStepsLabel,jobStepsTextField);
		hazardsHBox.getChildren().addAll(hazardsLabel,hazardsTextField);
		riskHBox.getChildren().addAll(riskLabel,rb1,rb2,rb3,addButton);
		controlMeasuresHBox.getChildren().addAll(controlMeasuresLabel,controlMeasuresTextField);
		feedBackHBox.getChildren().addAll(listView,delButton);
		mainVBox.getChildren().addAll(jobStepsHBox,hazardsHBox,controlMeasuresHBox,riskHBox,feedBackHBox);
		root.getChildren().addAll(mainVBox);
		setContent(root);
		
		addButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				sA.setStep(new StepsObject());
		        listView.getItems().clear(); // clear listview
		        Document_Main.printListElements();
		        Document_Main.clearFields();
			}
		});  

		delButton.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				if(!listView.getItems().isEmpty()) {  // prevents exception if no elements to delete
					sA.removeStep(listView.getSelectionModel().getSelectedIndex()); // removes selected item
					listView.getItems().clear();
					Document_Main.printListElements();
				}
			}
		}); 
	}

/*	public void initialize() {
        listView.setCellFactory(new Callback<ListView<ColoredText>, ListCell<ColoredText>>() {
			@Override
			public ListCell<ColoredText> call(ListView<ColoredText> lv) {
				return new ListCell<ColoredText>() {
				    @Override
				    protected void updateItem(ColoredText item, boolean empty) {
				        super.updateItem(item, empty);
				        if (item == null) {
				            setText(null);
				            setTextFill(null);
				            System.out.println("is null");
				        } else {
				        	setTextFill(Color.RED);
				        	System.out.println(item.getText());
				        	System.out.println(item.getColor());
				            setText(item.getText());
				            setTextFill(item.getColor());
				        }
				    }
				};
			}
		});
    }
	*/
//	public void printListElements() {
//		int listViewElement = 1;
//		for(int i = 0; i < sA.getStepSize();i++) {
//			
//			listView.getItems().add(listViewElement + ") " 
//					+ sA.getSteps(i).jobSteps
//					+ " / "
//					+ sA.getSteps(i).hazards
//					+ " / "
//					+ sA.getSteps(i).controls);
//
//        	listViewElement++;
///       }
//	}
	
//	public void clearFields() {
//		jobStepsTextField.clear();
//		hazardsTextField.clear();
//		controlMeasuresTextField.clear();
//		rb1.setSelected(true);
//	}
/*	
	public class ColoredText {
	    private final String text ;
	    private final Color color ;

	    public ColoredText(String text, Color color) {
	        this.text = text ;
	        this.color = color ;
	    }

	    public String getText() {
	        return text ;
	    }

	    public Color getColor() {
	        return color ;
	    }
	}
*/
}
