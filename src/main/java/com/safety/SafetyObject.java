package com.safety;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class SafetyObject extends Doc_ObjectSE001B implements Serializable {
	private static final long serialVersionUID = 8535677913873057274L;
	private String incidentString;
	private String hazardsString;
	private String areaCleanedString;
	private String workOrder;
	private String customer;
	private String subContractor;
	private String scopeOfWork;
	private String streetAddress;
	private String date;
	private String name;

	private boolean energizedWork = false;
	private int incidentInjuries = 0;  // could be boolean but areaCleand up has 3 options
	private int hazardsRemain = 0;  // could be boolean but areaCleand up has 3 options
	private int areaCleanedUP = 0;
	
	// Aggregation relationship
	private List<StepsObject> steps;

	// For anonymous object Reference 
	HashMap<String, Boolean> safetyStepsMap;

	// pre-defined strings
	private final String[] HEADERS = {  /// headings for each group
			"Pre-job" 
			,"Electrical PPE Required"
			,"Fit For work"
			,"Electrical Hazards"
			,"Ergonomic Hazards"
			,"Access/Egress Hazards"
			,"Working at Heights"
			,"Environmental Hazards"
			,"Other Hazards"
			,"Close out"
	};

	private final String[] PREJOB = {
			"Site Orientation"
			, "Job scope / Procedures understood"
			, "Toolbox meeting held"
			, "Training adequate for tasks"
			, "PPE/Equipment available & Inspected"
			, "All isolation points locked / tagged"
			, "Oxygen/LEL tests required"
			, "Warning tape needed"
			, "Client contact notified"
			, "Emergency response plan reviewed"
			, "Inspection of tools/equipment/vehicle "
			, "Permits required"
	};

	private final String[]  ELECTRICALPPEREQUIRED = {
			"safety boots"
			,"Safety glasses, safety gloves"
			,"FR rated clothing "
			,"Arc Flash protective equipment"
			,"Shock protection rated gloves"
			,"FR Hi-Vis Vest / Coverall"	
	};

	private final String[] FITFORWORK = {
			"I am fit to do my job"		
	};

	private final String[] ELECTRICALHAZARDS = {
			"Work near energized equipment"
			,"Electrical Shock"
			,"Arc Flash (Arc Flash Calculator)"
			,"Backfeed/Induction (Grounds Installed)"
			,"Electrical cords & GFCI Test"
			,"Portable Grounds Installed (>600V)"
			,"Inadvertent energization"
			,"Emergency power"
	};

	private final String[] ERGONOMICHAZARDS = {
			"Awkward body position"
			,"Over extension"
			,"Twisting/repetitive/bending motion"
			,"Working in tight area"
			," above your head"
			,"Lift too Workingheavy"
			,"Hands not in line of sight"
			,"Lifting/pushing/pulling materials"
			,"Struck by / Caught between"
			,"Line of fire"
			,"Fatigue"
			,"Poor lighting / Ventilation"
	};

	private final String[] ACCESSEGRESSHAZARDS = {
			"Aerial lift inspected & tagged"
			,"Scaffold inspected & tagged"
			,"Ladders inspected & secured"
	};

	private final String[] WORKINGATHEIGHTS = {
			"Work overhead"
			,"Elevated work"
			,"Overhead lines"
			,"Harness / Lanyards inspected"
			,"100% Tie-off with harness "
			,"Falling objects"
	};

	private final String[] ENVIRONMENTALHAZARDS = {
			"Spill"
			,"Dust / Mist / Fumes"
			,"Weather Conditions (Rain, Wind, Snow)"
			,"Noise"
			,"Waste Management"
			,"Housekeeping"
			,"MSDS available & valid"
			,"Material Storage"
			,"Asbestos or other hazardous materials"
			,"PCB"
	};

	private final String[] OTHERHAZARDS = {
			"Procedure not available for task"
			,"Confusing instructions"
			,"No training for task or tools to be used"
			,"First time performing the task"
			,"Working alone"
			,"Fire / Cutting / Grinding"
			,"Moving Equipment / Vehicles"
			,"Cut sharp edges"
			,"Power hand tools"
			,"Pinch points"
			,"Security"
			,"Excavation / Utilities underground"
			,"Confined space"
			,"Severe Weather (Lightning, Tornadoes)"
	};

	private final String[] CLOSEOUT = {
			"Work area cleaned-up"
			,"All tools / equipment removed"
			,"Job completed"
			,"Personal locks removed"
			,"System verification"
			,"Permits close out"
	};

	private final String[][] safetyCheckarrays = { PREJOB, ELECTRICALPPEREQUIRED, FITFORWORK
			, ELECTRICALHAZARDS, ERGONOMICHAZARDS, ACCESSEGRESSHAZARDS, WORKINGATHEIGHTS
			, ENVIRONMENTALHAZARDS, OTHERHAZARDS, CLOSEOUT};

	/// default constructor
	public SafetyObject() {
		super();
		this.safetyStepsMap = createHashMap();
		this.steps = new ArrayList<StepsObject>();
	}

	public HashMap<String, Boolean> createHashMap() {
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();  // creates hashmap putting string with boolean
		for(int j = 0; j < getSafetyCheckarraysRows(); j++) {
			for(int i = 0; i < getSafetyCheckNumberOfElements(j); i++) {
				hashMap.put(getSafetyCheckarrays(j,i), new Boolean(false));  // match strings to their boolean// checkedBoxes[j][i]
			}
		}
		return hashMap;
	}

	/// getter and setters
	
	
	
	
	public boolean getSafetyStepsMapValue(String key) {
		return safetyStepsMap.get(key);
	}
	

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public StepsObject getSteps(int index) {
		return steps.get(index);
	}

	public void setStep(StepsObject thisStep) {
		this.steps.add(thisStep);
	}
	
	public void removeStep(int thisStep) {
		this.steps.remove(thisStep);
	}
	
	public int getStepSize() {
		return steps.size();
	}

	public void setSafetyBooleanValue (boolean value, String key) {
		safetyStepsMap.put(key, value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/// separate from  date in parent
	public void setDate(String dateCreated) {
		this.date = dateCreated;
	}

	/// separate from date in parent
	public String getDate() {
		return this.date;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getScopeOfWork() {
		return scopeOfWork;
	}


	public void setScopeOfWork(String scopeOfWork) {
		this.scopeOfWork = scopeOfWork;
	}


	public String getIncidentString() {
		return incidentString;
	}

	public void setIncidentString(String incidentString) {
		this.incidentString = incidentString;
	}

	public String getHazardsString() {
		return hazardsString;
	}

	public void setHazardsString(String hazardsString) {
		this.hazardsString = hazardsString;
	}

	public String getAreaCleanedString() {
		return areaCleanedString;
	}

	public void setAreaCleanedString(String areaCleanedString) {
		this.areaCleanedString = areaCleanedString;
	}

	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	public String getSubContractor() {
		return subContractor;
	}

	public void setSubContractor(String contractor) {
		this.subContractor = contractor;
	}

	public boolean getEnergizedWork() {
		return energizedWork;
	}

	public void setEnergizedWork(boolean energizedWork) {
		this.energizedWork = energizedWork;
	}

	public int getIncidentInjuries() {
		return incidentInjuries;
	}

	public void setIncidentInjuries(int incidentInjuries) {
		this.incidentInjuries = incidentInjuries;
	}

	public int getHazardsRemain() {
		return hazardsRemain;
	}

	public void setHazardsRemain(int hazardsRemain) {
		this.hazardsRemain = hazardsRemain;
	}

	public int getAreaCleanedUP() {
		return areaCleanedUP;
	}

	public void setAreaCleanedUP(int areaCleanedUP) {
		this.areaCleanedUP = areaCleanedUP;
	}

	////////////////  STRINGS getters and setters
	public String[] getHEADERS() {
		return HEADERS;
	}

	public String getHEADERS(int index) {
		return HEADERS[index];
	}

	public String[][] getSafetyCheckarrays() {
		return safetyCheckarrays;
	}

	public String getSafetyCheckarrays(int row, int column) {
		return safetyCheckarrays[row][column];
	}

	public int getSafetyCheckNumberOfElements(int index) {  // returns length of each row
		return safetyCheckarrays[index].length;
	}

	public int getSafetyCheckarraysRows() { // retruns how many rows there are
		return safetyCheckarrays.length;
	}

	@Override
	public String toString() {
		return "SafetyAssesment [incidentString=" + incidentString + ", hazardsString=" + hazardsString
				+ ", areaCleanedString=" + areaCleanedString + ", workOrder=" + workOrder + ", subContractor="
				+ subContractor + ", scopeOfWork=" + scopeOfWork + ", streetAddress=" + streetAddress + ", date=" + date
				+ ", name=" + name + ", energizedWork=" + energizedWork + ", incidentInjuries=" + incidentInjuries
				+ ", hazardsRemain=" + hazardsRemain + ", areaCleanedUP=" + areaCleanedUP + ", steps=" + steps
				+ ", safetyStepsMap=" + safetyStepsMap + ", HEADERS=" + Arrays.toString(HEADERS) + ", PREJOB="
				+ Arrays.toString(PREJOB) + ", ELECTRICALPPEREQUIRED=" + Arrays.toString(ELECTRICALPPEREQUIRED)
				+ ", FITFORWORK=" + Arrays.toString(FITFORWORK) + ", ELECTRICALHAZARDS="
				+ Arrays.toString(ELECTRICALHAZARDS) + ", ERGONOMICHAZARDS=" + Arrays.toString(ERGONOMICHAZARDS)
				+ ", ACCESSEGRESSHAZARDS=" + Arrays.toString(ACCESSEGRESSHAZARDS) + ", WORKINGATHEIGHTS="
				+ Arrays.toString(WORKINGATHEIGHTS) + ", ENVIRONMENTALHAZARDS=" + Arrays.toString(ENVIRONMENTALHAZARDS)
				+ ", OTHERHAZARDS=" + Arrays.toString(OTHERHAZARDS) + ", CLOSEOUT=" + Arrays.toString(CLOSEOUT)
				+ ", safetyCheckarrays=" + Arrays.toString(safetyCheckarrays) + "]";
	}


}



