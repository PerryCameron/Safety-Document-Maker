package com.safety;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Gui_ButtonMenu {
		static FileChooser fileChooser = new FileChooser();
		
		//private static Desktop desktop = Desktop.getDesktop();
		
	public static BorderPane buttonMenu(final Stage primaryStage
			,Image save32
			,Image new32
			,Image open32
			,Image conf32
			,Image user
			,Image se
			,Image exit32
			,final Image signature
			,final Image initials
			,final Image cornerImage) throws FileNotFoundException {
		
		BorderPane menuBorderPane = new BorderPane();
		HBox topMenu = new HBox();
		HBox schneiderHBox = new HBox();
		Button save = new Button("");
		save.getStyleClass().add("save");
		Button open = new Button("");
		open.getStyleClass().add("open");
		Button neuw = new Button("");
		neuw.getStyleClass().add("new");
		Button conf = new Button("");
		conf.getStyleClass().add("conf");
		Button user1 = new Button("");
		user1.getStyleClass().add("user");
		Button exit = new Button("");
		exit.getStyleClass().add("exit");

		save.setGraphic(new ImageView(save32));
		neuw.setGraphic(new ImageView(new32));
		open.setGraphic(new ImageView(open32));
		conf.setGraphic(new ImageView(conf32));
		user1.setGraphic(new ImageView(user));
		exit.setGraphic(new ImageView(exit32));
		ImageView schneiderLogo = new ImageView(se);
		schneiderLogo.setPreserveRatio(true);
		schneiderLogo.setFitHeight(50); 
		

		//schneiderLogo.setFitWidth(100);

		menuBorderPane.getStyleClass().add("topmenu");
		topMenu.setAlignment(Pos.BOTTOM_LEFT);  // aligns nodes to center in HBox
		topMenu.setSpacing(30);  // puts spacing between buttons
		topMenu.setPrefHeight(80);  // forces HBox to be a certain height
		topMenu.getChildren().addAll(save,open,neuw,conf,user1,exit);
		schneiderHBox.getChildren().add(schneiderLogo);
		schneiderHBox.setAlignment(Pos.BOTTOM_LEFT); 
		menuBorderPane.setCenter(topMenu); 
		menuBorderPane.setRight(schneiderHBox);
		
		user1.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(Document_Main.users.isEmpty()) {  // does the main user exist?
							try {
								Gui_UserSettingPopUp.createUserMenu(primaryStage,false,signature,initials,cornerImage);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						} else {
							try {
								Gui_UserSettingPopUp.createUserMenu(primaryStage,true,signature,initials,cornerImage);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				});
		
		save.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
	    		  Document_Main.updateVariables();  /// updates the sa object with the gui fields
	        	  configureFileChooser(fileChooser, false);
	        	  File file = fileChooser.showSaveDialog(primaryStage);
                  if (file != null) {
                	  Document_Main.saveSafetyObjects(file);  ///
                  }
			}
		}); 
		
		neuw.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Form Clear");
				alert.setContentText("Would you like to clear current document?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					Document_Main.clearSafetyObject();
				} else {
				    // ... user chose CANCEL or closed the dialog
				}
	    		  
			}
		});
		
		conf.setOnAction(new EventHandler<ActionEvent>() {   /// event on button
			public void handle(ActionEvent e) {
				for(int i = 0; i < Document_Main.jobSteps.size(); i++) {
					System.out.println(Document_Main.jobSteps.get(i).toString());
				}
			//	System.out.println("configuration");
			//	Alert alert = new Alert(AlertType.INFORMATION);
			//	alert.setTitle("Settings");
			//	alert.setHeaderText("Settings");
			//	alert.setContentText("This feature not yet implemented");
			//	alert.showAndWait();
			}
		}); 
		
	      open.setOnAction(new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent e) {
	        	  configureFileChooser(fileChooser, true);
	        	  File file = fileChooser.showOpenDialog(primaryStage);
                  if (file != null) {
                	  Document_Main.openSaftyObjects(file);
                  }
				Document_Main.updateGui();
				//Gui_Main.infoTab.updateFields();
	          }
	      });
	      
	      exit.setOnAction(new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent e) {
	        	  System.exit(0);
	          }
	      });	
		return menuBorderPane;
	}

	private static void configureFileChooser(final FileChooser fileChooser, boolean isOpen){ 
		if (isOpen) {
        fileChooser.setTitle("Open Safety Assesment File");
		} else {
        fileChooser.setTitle("Save Safety Assesment File");
		}
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SAD", "*.sad")
            );
	}
}

