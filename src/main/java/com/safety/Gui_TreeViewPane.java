package com.safety;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Gui_TreeViewPane extends Pane {
	//private final Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream(Definitions.TOPFOLDER)));
	
    private TreeView<String> treeview; 
    SafetyObject sA;
    HashMap<String, Tab> tabsMap;
    
	@SuppressWarnings("unchecked")
	public Gui_TreeViewPane(final TabPane tabPane, HashMap<String, Tab> tabsMap) throws FileNotFoundException {
		super();
		this.treeview = new TreeView<String>();
		this.tabsMap = tabsMap;
		Document_Main.document.add(new SafetyObject()); // had to create this here
		setHeight(650);
		setPrefHeight(650);
		FileInputStream input1 = new FileInputStream(Document_Main.TOPFOLDER);
		FileInputStream input2 = new FileInputStream(Document_Main.DOCUMENT);
		Image image1 = new Image(input1);
		Image image2 = new Image(input2);
		
		TreeItem<String> root = new TreeItem<String>("Safety Assesment", new ImageView(image1));

		//root.setExpanded(true);
		
		TreeItem<String> item1 = new TreeItem<String>("Information",new ImageView(image2));
		TreeItem<String> item2 = new TreeItem<String>("Safety Checkoff",new ImageView(image2));
		TreeItem<String> item3 = new TreeItem<String>("Job Steps",new ImageView(image2));
		TreeItem<String> item4 = new TreeItem<String>("Job Completion",new ImageView(image2));
		
		treeview.getStyleClass().add("tree");
		treeview.setRoot(root);
		root.getChildren().addAll(item1,item2,item3,item4);
		getChildren().add(treeview);
	
		treeview.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 1){
		            	TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();

		                if(item.getValue().equals("Safety Assesment")) {
		                	addAllTabs(tabPane);
		                }
		                if(item.getValue().equals("Information")) {
		                	addTab("Information", tabPane);
		                }
		                if(item.getValue().equals("Safety Checkoff")) {
		                	addTab("Safety Checkoff", tabPane);
		                }
		                if(item.getValue().equals("Job Steps")) {
		                	addTab("Job Steps", tabPane);
		                }
		                if(item.getValue().equals("Job Completion")) {
		                	addTab("Job Completion", tabPane);
		                }
		            }
		        }
		    }
		});	
	}
	
	public void addAllTabs(TabPane tabPane) {
		if(tabPane.getTabs().size() != 4) {
        	tabPane.getTabs().setAll(
       			 tabsMap.get("Information")
       			,tabsMap.get("Safety Checkoff")
       			,tabsMap.get("Job Steps")
       			,tabsMap.get("Job Completion"));
		} else	{
			System.out.println("We alread have a full tabPane");
		}
	}

	public void addTab(String tab, TabPane tabPane) {
		if(tabPane.getTabs().size() < 4) {
			if(tabPane.getTabs().size() > 0) {
				if(tabPane.getTabs().size() == 1) {
					tabPane.getTabs().setAll(tabsMap.get(tab),tabPane.getTabs().get(0));
				} 
				if (tabPane.getTabs().size() == 2) {
					tabPane.getTabs().setAll(tabsMap.get(tab),tabPane.getTabs().get(0)
							,tabPane.getTabs().get(1));
				}
				if (tabPane.getTabs().size() == 3) {
					tabPane.getTabs().setAll(tabsMap.get(tab),tabPane.getTabs().get(0)
							,tabPane.getTabs().get(1),tabPane.getTabs().get(2));
				}
			} else {
				tabPane.getTabs().setAll(tabsMap.get(tab));
			}
		} else {
			System.out.println("We alread have a full tabPane");
		}
	}
}


