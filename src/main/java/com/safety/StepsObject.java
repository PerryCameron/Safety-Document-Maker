package com.safety;

import java.io.Serializable;

import javafx.scene.control.RadioButton;

public class StepsObject implements Serializable {

	private static final long serialVersionUID = 1657949566190018324L;
	/// class inside of a class so I could use local variables
	String jobSteps;
	String hazards;
	String controls;
	String risk;
	int stepType;
	
	// stepType key
	// 0 = custom
	// 1 = general
	// 2 = battery
	// 3 = corrective maintenance
	// 4 = preventive maintenance

	public StepsObject(int stepType) {
		super();
		this.jobSteps = Gui_Main.stepsTab.jobStepsTextField.getText();
		this.hazards = Gui_Main.stepsTab.hazardsTextField.getText();
		this.controls = Gui_Main.stepsTab.controlMeasuresTextField.getText();
		this.risk = getRadioButtonState(Gui_Main.stepsTab.rb1, Gui_Main.stepsTab.rb2);
		this.stepType = stepType;
	}

	public String getRadioButtonState (RadioButton rb1, RadioButton rb2) {
		String selected = "High";  /// this one is for N/A
		if(rb1.isSelected()) {
			selected = "Low";
		}
		if(rb2.isSelected()) {
			selected = "Medium";
		}
		return selected;
	}

	public String getJobSteps() {
		return jobSteps;
	}
	
	public void setJobSteps(String step) {
		this.jobSteps = step;
	}
	

	public String getHazards() {
		return hazards;
	}
	
	public void setHazards(String hazard) {
		this.hazards = hazard;
	}

	public String getControls() {
		return controls;
	}
	
	public void setControls(String control) {
		this.controls = control;
	}

	public String getRisk() {
		return risk;
	}
	
	public void setRisk(String risk) {
		this.risk = risk;
	}

	public int getStepType() {
		return stepType;
	}

	public void setStepType(int stepType) {
		this.stepType = stepType;
	}

	@Override
	public String toString() {
		return "StepsObject [jobSteps=" + jobSteps + ", hazards=" + hazards + ", controls=" + controls + ", risk="
				+ risk + ", stepType=" + stepType + "]";
	}
}