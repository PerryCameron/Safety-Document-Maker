package com.safety;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Document_Main {
	//static SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
	public static final String VERSION = "0.8";
	// this variable determines where a custom step is saved permanently
	public static boolean saveStepToFile = false;
	// this variable determines how a saved custom step should be classified
	public static int stepCategory = 0;
	// this variable determines whether Signature.png should be applied to PDF
	public static boolean signatureEnable = true;
	public static File recordsDir;
	public static String saveDirectory;
	public static String saFileName = "SE001B Point of Work Risk Assessment.pdf";
	public static boolean openDocumentUponCreation = false;  // will we open the PDF after creating it
	
	static Color lightGreenColor = new DeviceCmyk(0.78f, 0, 0.81f, 0.21f);
	static Color yellowColor = new DeviceCmyk(0, 0, 0.76f, 0.01f);
	static Color redColor = new DeviceCmyk(0, 0.76f, 0.86f, 0.01f);
	static Color blueColor = new DeviceCmyk(0.28f, 0.11f, 0, 0);
	static Color lightGrayColor = new DeviceCmyk(0.43f, 0.41f, 0.32f, 0.11f);
	// static Color lightGreenColor = new DeviceCmyk(0.64f, 0.25f, 0.63f, 0.25f);
	
	public static final String ROOTDIR = "src/main/resources/";
	public static final String USERFILETEMPLATE = "/data/users.udf";
	public static final String STEPSFILETEMPLATE = "/data/steps.udf";
	public static final String SAFETY_IMG = "/img/Safety.png";
	public static final String PROGRAM_ICON = "/img/Document.ico";
	public static final String TOPFOLDER =  "/img/folder_classic_add.png";
	public static final String DOCUMENT = "/img/document_a4_blank.png";
	public static final String DOCUMENT_ICON = "/img/Document.png";
	public static final String MAINCSS = "css/stylesheet.css";
	public static final String SIGNATURE = System.getProperty("user.home") + "/.document/img/Signature.png";
	public static final String INITIALS = System.getProperty("user.home") + "/.document/img/Initials.png";
	public static final String USERFILE = System.getProperty("user.home") + "/.document/users.udf";
	public static final String STEPFILE = System.getProperty("user.home") + "/.document/steps.udf";
	
	public static final String SAVEICON = "/img/Savex32.png";
	public static final String NEWICON = "/img/Newx32.png";
	public static final String OPENICON = "/img/Openx32.png";
	public static final String CONFICON = "/img/Pinionx32.png";
	public static final String USERICON = "/img/Userx32.png";
	public static final String SCHNEIDER = "/img/Schneider.png";
	public static final String EXIT = "/img/exit_32.png";
	public static final String PICKSTEPS = "/img/pickstep.png";
	public static final String SAVEOK = "/img/save_OK.png";
	public static final String SAVENOK = "/img/save_NOK.png";
	public static final String EDITICON = "/img/edit.png";
	
	private final static String[] MONTHS = {  /// headings for each group
			"January" ,"February","March","April","May","June","July"
			,"August","September","October","November","December"
	};
	
	/// this is the list of safety assessment objects that have been created
	static List<SafetyObject> document = new ArrayList<SafetyObject>();
	static List<UserObject> users = new ArrayList<UserObject>();
	static List<StepsObject> jobSteps = new ArrayList<StepsObject>();
	
	public static StepsObject getSteps(int index) {
		return jobSteps.get(index);
	}

	public static void setStep(StepsObject thisStep) {
		jobSteps.add(thisStep);
	}
	
	public static void removeStep(int element) {
		jobSteps.remove(element);
	}
	
	public static Paragraph setCheckMark(int fontSize) throws IOException {
	    Paragraph checkMark = new Paragraph("");
	    checkMark.setFontColor(lightGreenColor);
	    PdfFont zapfdingbats = PdfFontFactory.createFont(FontConstants.ZAPFDINGBATS);
	    Text chunk = new Text("4").setFont(zapfdingbats).setFontSize(fontSize);
	    checkMark.add(chunk);
	    return checkMark;
	}
	
	public static void clearSafetyObject() {		
		/// Information Tab
		Gui_Main.infoTab.workOrderTextField.setText("");
		Gui_Main.infoTab.scopeComboBox.setValue("Preventive Maintenance");
		Gui_Main.infoTab.nameTextField.setText("");
		Gui_Main.infoTab.addressTextField.setText("");
		Gui_Main.infoTab.cityTextField.setText("");
		Gui_Main.infoTab.stateComboBox.setValue("");
		Gui_Main.infoTab.zipTextField.setText("");
		Gui_Main.infoTab.customerTextField.setText("");
		Gui_Main.infoTab.subcontractorTextField.setText("none");
		
		for(int p = 0; p < document.get(0).getSafetyCheckarraysRows(); p++) {  /// for each row
			for(int q = 0; q < document.get(0).getSafetyCheckNumberOfElements(p); q++) {  /// for each element of that row
					Gui_Main.choicesTab.checkBoxHashMap.get(document.get(0).getSafetyCheckarrays(p,q)).setSelected(false);
			}
		}
		
		document.get(0).setWorkOrder("");
		document.get(0).setName("");
		document.get(0).setStreetAddress("");
		document.get(0).setCity("");
		document.get(0).setState("");
		document.get(0).setZipcode("");
		document.get(0).setCustomer("");
		document.get(0).setSubContractor("none");
		// job steps tab
		Document_Main.document.get(0).clearAllSteps();
		Gui_Main.stepsTab.listView.getItems().clear();
	}
	
	public static void saveStepObjects() {  // saves user file to disk
		File g = new File(STEPFILE);
		System.out.println("saving " + STEPFILE);
		try	{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(g));
			out.writeObject(jobSteps); 
			out.close();
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void openStepObjects() {
		System.out.println();
		File g = new File(STEPFILE);
		if (g.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(g));
				jobSteps = (List<StepsObject>) in.readObject();
				in.close();
			} catch (Exception e) {
				System.out.println("Error occurred during reading the file");
				System.out.println( e.getMessage() );
				e.printStackTrace();
			}			  
		} else {
			System.out.println("There is no file " + STEPFILE);
		}
	}
	
	public static void saveUserObjects() {  // saves user file to disk
		File g = new File(USERFILE);
		System.out.println("saving " + USERFILE);
		try	{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(g));
			out.writeObject(users); 
			out.close();
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
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void openSaftyObjects(File g) {
		clearSafetyObject();  // clears out old data before loading new data
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
	
	public static Image openSignature(File g) {
		Image thisImage = null;
		if (g.exists()) {
			try {
				BufferedImage bufferedImage = ImageIO.read(g);
                thisImage = SwingFXUtils.toFXImage(bufferedImage, null);
			} catch (Exception e) {
				//Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("Error occurred during reading the file");
				System.out.println( e.getMessage() );
				e.printStackTrace();
			}			  
		} else {
			System.out.println("There is no file ");
		}
		return thisImage;
	}
	
	public static void setUpForFirstTime() throws Exception {
		recordsDir = new File(System.getProperty("user.home"), ".document/img");
		if (!recordsDir.exists()) {
			System.out.println("Creating dir: " + System.getProperty("user.home") + "/.document"); // USERFILETEMPLATE
			System.out.println("Creating dir: " + System.getProperty("user.home") + "/.document/img");
		    recordsDir.mkdirs();
		    System.out.println("Installing: " + ResourceManager.extract(Document_Main.SIGNATURE, (System.getProperty("user.home") + "\\.document\\img\\")));
		    System.out.println("Installing: " + ResourceManager.extract(Document_Main.INITIALS, (System.getProperty("user.home") + "\\.document\\img\\")));
		    System.out.println("Installing: " + ResourceManager.extract(Document_Main.PROGRAM_ICON, (System.getProperty("user.home") + "\\.document\\")));
		    System.out.println("Installing: " + ResourceManager.extract(Document_Main.USERFILETEMPLATE, (System.getProperty("user.home") + "\\.document\\")));
		}
	}

	public static void updateVariables() {  /// saves value of fields to SA object
		// grap date in separate integer values
		int month = Gui_Main.infoTab.checkInDatePicker.getValue().getMonthValue();
		int day = Gui_Main.infoTab.checkInDatePicker.getValue().getDayOfMonth();
		int year = Gui_Main.infoTab.checkInDatePicker.getValue().getYear();
		// change format of the date to look prettier for PDF
		String printedDate = (MONTHS[month-1] + " " + day + ", " + year);
		// saves the value of CheckBoxes to sA object
		Gui_Main.choicesTab.saveHashtoObject();
		document.get(0).setWorkOrder(Gui_Main.infoTab.workOrderTextField.getText());
		document.get(0).setName(Gui_Main.infoTab.nameTextField.getText());
		document.get(0).setDate(printedDate);
		document.get(0).setStreetAddress(Gui_Main.infoTab.addressTextField.getText());
		document.get(0).setCity(Gui_Main.infoTab.cityTextField.getText());
		document.get(0).setState(Gui_Main.infoTab.stateComboBox.getValue());
		document.get(0).setZipcode(Gui_Main.infoTab.zipTextField.getText());
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
		Gui_Main.infoTab.checkInDatePicker.setPromptText(document.get(0).getDate());
		Gui_Main.infoTab.workOrderTextField.setText(document.get(0).getWorkOrder());
		Gui_Main.infoTab.addressTextField.setText(document.get(0).getStreetAddress());
		Gui_Main.infoTab.cityTextField.setText(document.get(0).getCity());
		Gui_Main.infoTab.stateComboBox.setValue(document.get(0).getState());
		Gui_Main.infoTab.zipTextField.setText(document.get(0).getZipcode());
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
	
	public static void printSafetyAssesmentData() {   /// this can go soon was for testing
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
	
	public static void permantlySaveListElements() {  /// prints job steps on job step tab
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
	
	public static String getCustomerDirectory() {  // will give user selected customer directory if set
		String directory;							// or home directory if not set
		if(users.get(0).getCustomerDirectory() == null) {
			directory = System.getProperty("user.home");
		} else {
			directory = users.get(0).getCustomerDirectory();
		}
		return directory;
	}
	
	
	public static void clearFields() {
		Gui_Main.stepsTab.jobStepsTextField.clear();
		Gui_Main.stepsTab.hazardsTextField.clear();
		Gui_Main.stepsTab.controlMeasuresTextField.clear();
		Gui_Main.stepsTab.rb1.setSelected(true);
	}
	
	public static int getDocumentArraySize() {
		return document.size();
	}
	
	   public static byte[] toByteArray(InputStream in) throws IOException {  // for taking inputStream and returning byte array
		      //InputStream is = new BufferedInputStream(System.in);
		      ByteArrayOutputStream os = new ByteArrayOutputStream();
		      byte [] buffer = new byte[1024];
		      int len;
		      // read bytes from the input stream and store them in buffer
				while ((len = in.read(buffer)) != -1) {
					// write bytes from the buffer into output stream
					os.write(buffer, 0, len);
				}
				return os.toByteArray();
		}
}
