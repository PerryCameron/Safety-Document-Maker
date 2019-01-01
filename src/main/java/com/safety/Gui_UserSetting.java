package com.safety;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Gui_UserSetting {
	static Label firstNameLabel = new Label("First Name: ");
	static Label lastNameLabel = new Label("Last Name: ");
	static Label signatureLabel = new Label("Signature: ");
	static Label initialsLabel = new Label("Initials: ");

	static TextField firstNameTextField = new TextField();
	static TextField lastNameTextField = new TextField();

	static Button save = new Button("save");
	static Image cornerImage;

	public static void createUserMenu(Stage primaryStage, boolean userExists) throws FileNotFoundException {
		cornerImage = new Image(new FileInputStream(Document_Main.DOCUMENT_ICON));	
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);
		VBox dialogVbox = new VBox(20);

		if(userExists) {
			//System.out.println(Document_Main.users.size());
			//System.out.println(Document_Main.users.get(Document_Main.users.size()-1).getFirstName());
			String firstName = Document_Main.users.get(0).getFirstName();
			String lastName = Document_Main.users.get(0).getLastName();
			dialogVbox.getChildren().addAll(new Text("Name: \n" + firstName + " \n" + lastName));
		} else {
			dialogVbox.getChildren().addAll(firstNameLabel
					,firstNameTextField,lastNameLabel,lastNameTextField,save);
			save.setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Document_Main.users.add(new UserObject(firstNameTextField.getText(), lastNameTextField.getText()));
							Document_Main.saveUserObjects();
							dialog.close();
						}
					}); 
		}
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.getIcons().add(cornerImage);
		dialog.setScene(dialogScene);
		dialog.show();
	}
}
