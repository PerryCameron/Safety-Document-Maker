package com.safety;

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
	
    private TreeView<String> treeview; 
    HashMap<String, Tab> tabsMap;
    
	@SuppressWarnings("unchecked")
	public Gui_TreeViewPane(final TabPane tabPane, final HashMap<String, Tab> tabsMap) throws FileNotFoundException {
		super();
		this.treeview = new TreeView<>();
		this.tabsMap = tabsMap;
		Document_Main.document.add(new SafetyObject()); // had to create this here
		setHeight(650);
		setPrefHeight(650);
		
		Image image1 = new Image(getClass().getResourceAsStream(Document_Main.TOPFOLDER));
		Image image2 = new Image(getClass().getResourceAsStream(Document_Main.DOCUMENT));
		
		TreeItem<String> root = new TreeItem<>("Safety Assesment", new ImageView(image1));

		//root.setExpanded(true);
		
		TreeItem<String> item1 = new TreeItem<>("Information",new ImageView(image2));
		TreeItem<String> item2 = new TreeItem<>("Safety Checkoff",new ImageView(image2));
		TreeItem<String> item3 = new TreeItem<>("Job Steps",new ImageView(image2));
		TreeItem<String> item4 = new TreeItem<>("Job Completion",new ImageView(image2));
		
		treeview.getStyleClass().add("tree");
		treeview.setRoot(root);
		root.getChildren().addAll(item1,item2,item3,item4);
		getChildren().add(treeview);
		
	
		/// this opens tabs for safety assesment document
		treeview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
					if(mouseEvent.getClickCount() == 1){
						System.out.print("treeview clicked: ");
						// this prevents nullpointer exception on first click
						if(treeview.getSelectionModel().getSelectedItem() == null) {
							System.out.println("null sent");
						} else {
							TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();
							// System.out.print("You clicked: " + treeview.getSelectionModel().getSelectedItem());
							if(item.getValue().equals("Safety Assesment")) {
								addAllTabs(tabPane);
								System.out.println("Safety Assesment");
							}
							if(item.getValue().equals("Information")) {
								// if the tabpane does not contain this tab already then open it
								if(checkTabPaneForSameObject(tabsMap.get("Information"),tabPane))
									addTab("Information", tabPane);	
								System.out.println("Information");
							}
							if(item.getValue().equals("Safety Checkoff")) {
								// if the tabpane does not contain this tab already then open it
								if(checkTabPaneForSameObject(tabsMap.get("Safety Checkoff"),tabPane))
									addTab("Safety Checkoff", tabPane);
							    System.out.println("Safety Checkoff");
							}
							if(item.getValue().equals("Job Steps")) {
								// if the tabpane does not contain this tab already then open it
								if(checkTabPaneForSameObject(tabsMap.get("Job Steps"),tabPane))
									addTab("Job Steps", tabPane);
								System.out.println("Job Steps");
							}
							if(item.getValue().equals("Job Completion")) {
								// if the tabpane does not contain this tab already then open it
								if(checkTabPaneForSameObject(tabsMap.get("Job Completion"),tabPane))
									addTab("Job Completion", tabPane);
								 System.out.println("Job Completion");
							}
						 } // else not null
					}
				}
			}
		});	
	}

	public void addAllTabs(TabPane tabPane) {
		//System.out.println("1");
		if(tabPane.getTabs().size() != 4) {
			//System.out.println("2");
			tabPane.getTabs().setAll(
					 tabsMap.get("Information")
					,tabsMap.get("Safety Checkoff")
					,tabsMap.get("Job Steps")
					,tabsMap.get("Job Completion"));
			//System.out.println("3");
		} else	{
			System.out.println("We already have a full tabPane, there are " + tabPane.getTabs().size());
		}
	}

	public void addTab(String tab, TabPane tabPane) {
		if(tabPane.getTabs().size() < 4) {  // if the 4 tab slots are not filled
			if(tabPane.getTabs().size() > 0) {
				if (tabPane.getTabs().size() == 3) {
					tabPane.getTabs().setAll(tabsMap.get(tab),tabPane.getTabs().get(0)
							,tabPane.getTabs().get(1),tabPane.getTabs().get(2));
					//System.out.println("addTab() -> triggered size 3");
				}
				if (tabPane.getTabs().size() == 2) {
					tabPane.getTabs().setAll(tabsMap.get(tab),tabPane.getTabs().get(0)
							,tabPane.getTabs().get(1));
					//System.out.println("addTab() -> triggered size 2");
				}				
				if(tabPane.getTabs().size() == 1) {
					tabPane.getTabs().setAll(tabsMap.get(tab),tabPane.getTabs().get(0));
					//System.out.println("addTab() -> triggered size 1");
				} 
			} else {
				tabPane.getTabs().setAll(tabsMap.get(tab));
				//System.out.println("addTab() -> triggered all");
			}
		} else {
			System.out.println("addTab() -> We alread have a full tabPane");
		}
		//for(int i = 0; i < tabPane.getTabs().size(); i++) {
		//	System.out.print(i + ") addTab() -> ");
		//	System.out.println(tabPane.getTabs().get(i));
		//}
	}
	
	public boolean checkTabPaneForSameObject(Tab tabToCheck, TabPane tabPane) {
		boolean defaultValue = true;
		for(int i = 0; i < tabPane.getTabs().size(); i++) {
			//System.out.println("Checking if " + tabToCheck + " matches " + tabPane.getTabs().get(i) + " in tab pane");
			if(tabToCheck == tabPane.getTabs().get(i)) {
				System.out.println("duplicate tabs prevented ");
				defaultValue = false;
			}
		}
		return defaultValue;
	}
}

/// C:\Users\sesa91827\eclipse-workspace\Document\src\main\resources\img
/// C:/Users/sesa91827/eclipse-workspace/Document/target/classes/css/img/folder_classic_add.png
///  -fx-background-image: url("../../../src/main/resources/img/folder_classic_add.png");

/*
.tree-cell .tree-disclosure-node .arrow {
    -fx-shape: null;
    -fx-background-color: null;
    -fx-background-image: url("plus-arrow.png");
}
.tree-cell:expanded .tree-disclosure-node .arrow {
    -fx-shape: null;
    -fx-background-color: null;
    -fx-background-image: url("minus-arrow.png");
}
*/