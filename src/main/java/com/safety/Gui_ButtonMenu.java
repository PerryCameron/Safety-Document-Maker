package com.safety;

//import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Gui_ButtonMenu {
		static FileChooser fileChooser = new FileChooser();
		//private static Desktop desktop = Desktop.getDesktop();
		
	public static HBox buttonMenu(final Stage primaryStage) throws FileNotFoundException {

		HBox topMenu = new HBox();
		Button save = new Button("");
		Button open = new Button("");
		Button neuw = new Button("");
		Button conf = new Button("");
		Button user1 = new Button("");
		Button exit = new Button("");

		Image save32 = new Image(new FileInputStream(Document_Main.SAVEICON));
		Image new32 = new Image(new FileInputStream(Document_Main.NEWICON));
		Image open32 = new Image(new FileInputStream(Document_Main.OPENICON));
		Image conf32 = new Image(new FileInputStream(Document_Main.CONFICON));
		Image user = new Image(new FileInputStream(Document_Main.USERICON));
		Image se = new Image(new FileInputStream(Document_Main.SCHNEIDER));
		Image exit32 = new Image(new FileInputStream(Document_Main.EXIT));
		
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

		topMenu.getStyleClass().add("topmenu");
		topMenu.setAlignment(Pos.BOTTOM_LEFT);  // aligns nodes to center in HBox
		topMenu.setSpacing(30);  // puts spacing between buttons
		topMenu.setPrefHeight(80);  // forces HBox to be a certain height
		topMenu.getChildren().addAll(save,open,neuw,conf,user1,exit,schneiderLogo);
		
		user1.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(Document_Main.users.isEmpty()) {  // does the main user exist?
							try {
								Gui_UserSetting.createUserMenu(primaryStage,false);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						} else {
							try {
								Gui_UserSetting.createUserMenu(primaryStage,true);
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
	        	  configureFileChooser(fileChooser);
	        	  File file = fileChooser.showSaveDialog(primaryStage);
                  if (file != null) {
                	  Document_Main.saveSafetyObjects(file);  ///
                  }
			}
		}); 
		
	      open.setOnAction(new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent e) {
	        	  configureFileChooser(fileChooser);
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
		
		return topMenu;
	}

	private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("Open Safety Assesment File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SAD", "*.sad")
            );
	}
}
