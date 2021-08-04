package com.safety;

import java.io.File;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Gui_FileMenu {
	static FileChooser fileChooser = new FileChooser();
	
	static MenuBar buildMenuBarWithMenus(final ReadOnlyDoubleProperty menuWidthProperty, final Stage primaryStage) {
		
		final MenuBar menuBar = new MenuBar();
		final Menu fileMenu = new Menu("File");
		//final Menu testMenu = new Menu("Test");
		//final Menu helpMenu = new Menu("Help");
	    //final MenuItem aboutMenuItem = new MenuItem("Testing dirs and stuff");
	    final MenuItem exit = new MenuItem("Exit");
	    final MenuItem open = new MenuItem("Open");
	   // final MenuItem newData = new MenuItem("Count User Accounts");
	    final MenuItem save = new MenuItem("Save");
	    //final MenuItem accountTestPrint = new MenuItem("Print SaftyAssesment Data to Console");
	    //final MenuItem customerTestPrint = new MenuItem("Print save directory");
	    //final MenuItem programInfo = new MenuItem("test extract file from jar");
	    
		fileMenu.getItems().addAll(open,save);
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().add(exit);
		//testMenu.getItems().addAll(newData,accountTestPrint,customerTestPrint,programInfo);
		//helpMenu.getItems().add(aboutMenuItem);
		
		menuBar.getMenus().add(fileMenu);
		//menuBar.getMenus().add(testMenu);
		//menuBar.getMenus().add(helpMenu);
			
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
	      
	  /*    newData.setOnAction(new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent e) {
				System.out.println(Document_Main.users.size());
	          }
	      }); */
	      
	      save.setOnAction(new EventHandler<ActionEvent>() {
	    	  @Override public void handle(ActionEvent e) {
	    		  Document_Main.updateVariables();  /// updates the sa object with the gui fields
	        	  configureFileChooser(fileChooser, false);
	        	  File file = fileChooser.showSaveDialog(primaryStage);
                  if (file != null) {
                	  Document_Main.saveSafetyObjects(file);  ///
                  }
	    	  }
	      });
	/*      
	      accountTestPrint.setOnAction(new EventHandler<ActionEvent>() {
	    	  @Override public void handle(ActionEvent e) {
				Document_Main.printSafetyAssesmentData();
	    	  }
	      });
	      
	      customerTestPrint.setOnAction(new EventHandler<ActionEvent>() {
	    	  @Override public void handle(ActionEvent e) {
	    		  System.out.println(System.getProperty("user.home") + "\\" + Document_Main.saFileName);
	    	  }
	      });
	      
	      programInfo.setOnAction(new EventHandler<ActionEvent>() {
	    	  @Override public void handle(ActionEvent e) {
	    		  System.out.println(ResourceManager.extract(Document_Main.SIGNATURE, (System.getProperty("user.home") + "\\.document\\img\\")));
	    	  }
	      });
	      
	     aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent e) {
	        	  Alert alert = new Alert(AlertType.INFORMATION);
	        	  alert.setTitle("Information Dialog");
	        	  alert.setHeaderText("Your directory is");
	        	  alert.setContentText(
	        			  //System.getProperty("user.dir") + Document_Main.WINSIGNATURE


	        			  Document_Main.SIGNATUREFILE
	        			  
	        			  );

	        	  alert.showAndWait();
	          }
	      });
	     */
	      exit.setOnAction(new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent e) {
				System.exit(0);
	          }
	      });
	      
        // bind width of menu bar to width of associated stage
		menuBar.prefWidthProperty().bind(menuWidthProperty);
		return menuBar;
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