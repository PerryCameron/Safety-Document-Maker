package com.safety;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Gui_UserSettingPopUp {

	static FileChooser fileChooser = new FileChooser();
	static Label firstNameLabel = new Label("First Name: ");
	static Label lastNameLabel = new Label("Last Name: ");
	static Label signatureLabel = new Label("Signature: ");
	static Label initialsLabel = new Label("Initials: ");

	static TextField firstNameTextField = new TextField();
	static TextField lastNameTextField = new TextField();

	static Button save = new Button("Save");
	static Button close = new Button("Close");
	static Button custDir = new Button("Set Customer Directory...");
	static Image cornerImage;
	static ImageView signatureImageView; 
	static ImageView initialsImageView;
	static Text customerRootDir;
	//static Double totalWidth = 10.0;

	static TitledPane initialsTitledPane;
	static TitledPane customerDirTitledPane;
	//Stage primaryStage;

	public static void createUserMenu(Stage primaryStage, boolean userExists, Image signature, Image initials,Image cornerImage) throws FileNotFoundException {
		//cornerImage = new Image(ff(Document_Main.DOCUMENT_ICON));
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);

		customerRootDir = new Text(defaultStringDirectory());
		VBox dialogVbox = new VBox(20);
		HBox sHBox = new HBox(10);
		HBox buttonHBox = new HBox(10);
		HBox firstHBox = new HBox(10);
		HBox lastHBox = new HBox(10);
		dialogVbox.setPadding(new Insets(10));
		firstHBox.getChildren().addAll(firstNameLabel,firstNameTextField);
		lastHBox.getChildren().addAll(lastNameLabel,lastNameTextField);
		dialogVbox.getChildren().addAll(firstHBox,lastHBox);

		if(userExists) {
			firstNameTextField.setText(Document_Main.users.get(0).getFirstName());
			lastNameTextField.setText(Document_Main.users.get(0).getLastName());
			signatureImageView = new ImageView(signature);
			signatureImageView.setPreserveRatio(true);
			signatureImageView.setFitHeight(50); 
			TitledPane signatureTitledPane = new TitledPane("Signature", signatureImageView);
			customerDirTitledPane = new TitledPane("Customer root directory", customerRootDir);
			signatureTitledPane.setCollapsible(false);
			signatureTitledPane.setMaxWidth(signatureImageView.getFitWidth());
			sHBox.getChildren().add(signatureTitledPane);
			initialsImageView = new ImageView(initials);
			initialsImageView.setPreserveRatio(true);
			initialsImageView.setFitHeight(50);
			initialsTitledPane = new TitledPane("Initials", initialsImageView);
			initialsTitledPane.setCollapsible(false);
			initialsTitledPane.setMaxWidth(initialsImageView.getFitWidth());
			sHBox.getChildren().add(initialsTitledPane);

			save.setOnAction( // this will update an already created user.
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Document_Main.users.get(0).setFirstName(firstNameTextField.getText());
							Document_Main.users.get(0).setLastName(lastNameTextField.getText());
							Document_Main.saveUserObjects();
							dialog.close();
						}
					}); 

			custDir.setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Stage thisStage = new Stage();
							File file = defaultDirectory();
							directoryChooser.setInitialDirectory(file);
							File selectedDirectory = directoryChooser.showDialog(thisStage);

							if(selectedDirectory == null){
								//No Directory selected
							}else{
								customerRootDir.setText(selectedDirectory.getAbsolutePath());
								Document_Main.users.get(0).setCustomerDirectory(selectedDirectory.getAbsolutePath());
								System.out.println(Document_Main.users.get(0).getCustomerDirectory());
							}
						}
					}); 

		} else {  /// we have yet to create the main user this will do that
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

		close.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						dialog.close();
					}
				}); 

		buttonHBox.getChildren().addAll(custDir,save,close);
		buttonHBox.setAlignment(Pos.CENTER_RIGHT);
		dialogVbox.getChildren().addAll(sHBox,customerDirTitledPane,buttonHBox);
		Scene dialogScene = new Scene(dialogVbox, 340, 300);
		dialog.getIcons().add(cornerImage);
		dialog.setScene(dialogScene);
		dialog.show();
	}

	public static File defaultDirectory() {
		File file;
		if(Document_Main.users.get(0).getCustomerDirectory() == null) {
			file = new File(System.getProperty("user.home"));
		} else {
			file = new File(Document_Main.users.get(0).getCustomerDirectory());
		}
		return file;
	}

	public static String defaultStringDirectory() {
		String directory;
		if(Document_Main.users.get(0).getCustomerDirectory() == null) {
			directory = System.getProperty("user.home");
		} else {
			directory = Document_Main.users.get(0).getCustomerDirectory();
		}
		return directory;
	}
}
