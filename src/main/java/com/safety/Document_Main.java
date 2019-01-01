package com.safety;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

public class Document_Main {
	static Color lightGreenColor = new DeviceCmyk(0.78f, 0, 0.81f, 0.21f);
	static Color yellowColor = new DeviceCmyk(0, 0, 0.76f, 0.01f);
	static Color redColor = new DeviceCmyk(0, 0.76f, 0.86f, 0.01f);
	static Color blueColor = new DeviceCmyk(0.28f, 0.11f, 0, 0);
	static Color lightGrayColor = new DeviceCmyk(0.43f, 0.41f, 0.32f, 0.11f);
	// static Color lightGreenColor = new DeviceCmyk(0.64f, 0.25f, 0.63f, 0.25f);
	
	public static final String SAFETY_IMG = "src/main/resources/img/Safety.png";
	public static final String DEST = "temp/SE001B Point of Work Risk Assessment.pdf";
	public static final String TOPFOLDER = "icons/folder_classic_add.png";
	public static final String DOCUMENT = "icons/document_a4_blank.png";
	public static final String DOCUMENT_ICON = "src/main/resources/img/Document.png";
	public static final String MAINCSS = "css/stylesheet.css";
	public static final String SIGNATURE = "src/main/resources/img/Signature.png";
	public static final String USERFILE = "src/main/resources/data/users.udf";
	
	public static final String SAVEICON = "src/main/resources/img/Savex32.png";
	public static final String NEWICON = "src/main/resources/img/Newx32.png";
	public static final String OPENICON = "src/main/resources/img/Openx32.png";
	public static final String CONFICON = "src/main/resources/img/Pinionx32.png";
	public static final String USERICON = "src/main/resources/img/Userx32.png";
	public static final String SCHNEIDER = "src/main/resources/img/Schneider.png";
	public static final String EXIT = "src/main/resources/img/exit_32.png";
	
	/// this is the list of safety assessment objects that have been created
	static List<SafetyObject> document = new ArrayList<SafetyObject>();
	static List<UserObject> users = new ArrayList<UserObject>();
	
	public static Paragraph setCheckMark(int fontSize) throws IOException {
	    Paragraph checkMark = new Paragraph("");
	    checkMark.setFontColor(lightGreenColor);
	    PdfFont zapfdingbats = PdfFontFactory.createFont(FontConstants.ZAPFDINGBATS);
	    Text chunk = new Text("4").setFont(zapfdingbats).setFontSize(fontSize);
	    checkMark.add(chunk);
	    return checkMark;
	}
	
	public static void saveUserObjects() {  // saves SA object to file
		File g = new File(USERFILE);
		System.out.println("saving " + USERFILE);
		try	{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(g));
			out.writeObject(users); 
			out.close();
			System.out.println(USERFILE + "Saved Sucessful!");
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void openUserObjects() {
		System.out.println();
		File g = new File(USERFILE);
		if (g.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(g));
				users = (ArrayList<UserObject>) in.readObject();
				in.close();
			} catch (Exception e) {
				System.out.println("Error occurred during reading the file");
				System.out.println( e.getMessage() );
				e.printStackTrace();
			}			  
		} else {
			System.out.println("There is no file " + USERFILE);
		}
		
	}
	
	public static void saveSafetyObjects(File g) {  // saves SA object to file
		try	{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(g));
			out.writeObject(document); 
			out.close();
			System.out.println("Safetyfile Saved Sucessfully!");
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void openSaftyObjects(File g) {
		System.out.println();
		if (g.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(g));
				document = (ArrayList<SafetyObject>) in.readObject();
				in.close();
			} catch (Exception e) {
				System.out.println("Error occurred during reading the file");
				System.out.println( e.getMessage() );
				e.printStackTrace();
			}			  
		} else {
			System.out.println("There is no file ");
		}
		
	}
	
	public static void updateVariables() {  /// saves value of fields to SA object
		// Gui_Main.completionTab.writeJobDataToObject();
		Gui_Main.choicesTab.saveHashtoObject(); // saves the value of CheckBoxes to sA object
		document.get(0).setWorkOrder(Gui_Main.infoTab.workOrderTextField.getText());
		document.get(0).setName(Gui_Main.infoTab.nameTextField.getText());
		document.get(0).setDate(Gui_Main.infoTab.dateTextField.getText());
		document.get(0).setStreetAddress(Gui_Main.infoTab.locationTextField.getText());
		document.get(0).setCustomer(Gui_Main.infoTab.customerTextField.getText());
		document.get(0).setSubContractor(Gui_Main.infoTab.subcontractorTextField.getText());
		document.get(0).setScopeOfWork((String) Gui_Main.infoTab.scopeComboBox.getValue());
		document.get(0).setName(Gui_Main.infoTab.nameTextField.getText());
		document.get(0).setCustomer(Gui_Main.infoTab.customerTextField.getText());
		if (Gui_Main.infoTab.rb2.isSelected()) {
			document.get(0).setEnergizedWork(false);
		} else {
			document.get(0).setEnergizedWork(true);
		}
		///////////// below saves the checkboxes
		for(int p = 0; p < document.get(0).getSafetyCheckarraysRows(); p++) {  /// for each row
			for(int q = 0; q < document.get(0).getSafetyCheckNumberOfElements(p); q++) {  /// for each element of that row
					/// checks to see if checkboxes in GUI are checked
				if(Gui_Main.choicesTab.checkBoxHashMap.get(document.get(0).getSafetyCheckarrays(p,q)).isSelected()) {
					/// if checked then updateds the sa object
					document.get(0).setSafetyBooleanValue(true, document.get(0).getSafetyCheckarrays(p,q));
				} else {
					document.get(0).setSafetyBooleanValue(false, document.get(0).getSafetyCheckarrays(p,q));
				}			
			}
		}
		//////////// below are things on completion tab
		document.get(0).setHazardsString(Gui_Main.completionTab.hazardsTextField.getText());
		document.get(0).setIncidentString(Gui_Main.completionTab.IncidentInjuryTextField.getText());
		document.get(0).setAreaCleanedString(Gui_Main.completionTab.areaCleanedTextField.getText());
		document.get(0).setIncidentInjuries(Gui_Main.completionTab.getRadioButtonState(Gui_Main.completionTab.rb1,Gui_Main.completionTab.rb2));
		document.get(0).setHazardsRemain(Gui_Main.completionTab.getRadioButtonState(Gui_Main.completionTab.rb3,Gui_Main.completionTab.rb4));
		document.get(0).setAreaCleanedUP(Gui_Main.completionTab.getRadioButtonState(Gui_Main.completionTab.rb5,Gui_Main.completionTab.rb6));
	}
	
	public static void updateGui() {
		Gui_Main.infoTab.nameTextField.setText(document.get(0).getName());
		Gui_Main.infoTab.dateTextField.setText(document.get(0).getDate());
		Gui_Main.infoTab.workOrderTextField.setText(document.get(0).getWorkOrder());
		Gui_Main.infoTab.locationTextField.setText(document.get(0).getStreetAddress());
		Gui_Main.infoTab.customerTextField.setText(document.get(0).getCustomer());
		Gui_Main.infoTab.subcontractorTextField.setText(document.get(0).getSubContractor());
		Gui_Main.infoTab.scopeComboBox.setValue(document.get(0).getScopeOfWork());
		if(document.get(0).getEnergizedWork()) {
			Gui_Main.infoTab.rb1.setSelected(document.get(0).getEnergizedWork());
		} else {
			Gui_Main.infoTab.rb2.setSelected(document.get(0).getEnergizedWork());
		}
		for(int p = 0; p < document.get(0).getSafetyCheckarraysRows(); p++) {  /// for each row
			for(int q = 0; q < document.get(0).getSafetyCheckNumberOfElements(p); q++) {  /// for each element of that row
				/// if this item in the SA object is true then check the gui box that corresponds to it
				if(document.get(0).getSafetyStepsMapValue(document.get(0).getSafetyCheckarrays(p,q))) {  // get the value of each
					Gui_Main.choicesTab.checkBoxHashMap.get(document.get(0).getSafetyCheckarrays(p,q)).setSelected(true);
				} else {
					Gui_Main.choicesTab.checkBoxHashMap.get(document.get(0).getSafetyCheckarrays(p,q)).setSelected(false);
				}
			}
		}
		Gui_Main.completionTab.hazardsTextField.setText(document.get(0).getHazardsString());
		Gui_Main.completionTab.IncidentInjuryTextField.setText(document.get(0).getIncidentString());
		Gui_Main.completionTab.areaCleanedTextField.setText(document.get(0).getAreaCleanedString());
		//// Incident Injuries ///////
		if(document.get(0).getIncidentInjuries() == 0) {
			Gui_Main.completionTab.rb2.setSelected(true);
		} else {
			Gui_Main.completionTab.rb1.setSelected(true);
		}
		//// Hazards Remain //////
		if(document.get(0).getHazardsRemain() == 0){
			Gui_Main.completionTab.rb4.setSelected(true);
		} else {
			Gui_Main.completionTab.rb3.setSelected(true);
		}
		//// Area Cleaned Up ////
		if(document.get(0).getAreaCleanedUP() == 0) {
			Gui_Main.completionTab.rb6.setSelected(true);
		} else if (document.get(0).getAreaCleanedUP() == 1) {
			Gui_Main.completionTab.rb5.setSelected(true);
		} else {
			Gui_Main.completionTab.rb7.setSelected(true);
		}
		clearFields();
		printListElements(); /// prints job steps on jobStep Tab
	}
	
	public static void printSafetyAssesmentData() {
		System.out.println("Name= " + document.get(0).getName());
		System.out.println("Date= " + document.get(0).getDate());
		System.out.println("Location= " + document.get(0).getStreetAddress());
		System.out.println("WO= " + document.get(0).getWorkOrder());
		System.out.println("Scope= " + document.get(0).getScopeOfWork());
		System.out.println("Contractor= " + document.get(0).getSubContractor());
		System.out.println("Customer= " + document.get(0).getCustomer());
		System.out.println("Energized?= " + document.get(0).getEnergizedWork());
		
		for(int p = 0; p < document.get(0).getSafetyCheckarraysRows(); p++) {  /// for each row
			for(int q = 0; q < document.get(0).getSafetyCheckNumberOfElements(p); q++) {  /// for each element of that row
				/// gives me text attached to each checkbox
				/// System.out.print(Gui_Main.choicesTab.checkBoxHashMap.get(document.get(0).getSafetyCheckarrays(p,q)).getText() + " = ");
				/// gives me text before attached to each checkbox(in an array)
				System.out.print(document.get(0).getSafetyCheckarrays(p,q) + " = ");
				/// gives me boolean value for each value in the SA object
				System.out.println(document.get(0).getSafetyStepsMapValue(document.get(0).getSafetyCheckarrays(p,q)));
				/// tells me if each check box is selected in GUI. returns boolean
				//System.out.println(Gui_Main.choicesTab.checkBoxHashMap.get(document.get(0).getSafetyCheckarrays(p,q)).isSelected());
			}
		}
		System.out.println("Injuries = " + Gui_Main.completionTab.getRadioButtonState(Gui_Main.completionTab.rb1,Gui_Main.completionTab.rb2));
		System.out.println("Remaining Hazards = " + Gui_Main.completionTab.getRadioButtonState(Gui_Main.completionTab.rb3,Gui_Main.completionTab.rb4));
		System.out.println("Area Cleaned = " + Gui_Main.completionTab.getRadioButtonState(Gui_Main.completionTab.rb5,Gui_Main.completionTab.rb6));
		System.out.println(document.get(0).getStepSize());
		
	}
	
	public static void printListElements() {  /// prints job steps on job step tab
		int listViewElement = 1;
		for(int i = 0; i < document.get(0).getStepSize();i++) {
			
			Gui_Main.stepsTab.listView.getItems().add(listViewElement + ") " 
					+ document.get(0).getSteps(i).jobSteps
					+ " / "
					+ document.get(0).getSteps(i).hazards
					+ " / "
					+ document.get(0).getSteps(i).controls);
        	listViewElement++;
        }
	}
	
	public static void clearFields() {
		Gui_JobStepsTab.jobStepsTextField.clear();
		Gui_JobStepsTab.hazardsTextField.clear();
		Gui_JobStepsTab.controlMeasuresTextField.clear();
		Gui_JobStepsTab.rb1.setSelected(true);
	}
	
	public static int getDocumentArraySize() {
		return document.size();
	}
}
