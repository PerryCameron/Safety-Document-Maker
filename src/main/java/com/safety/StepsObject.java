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

	public StepsObject() {
		super();
		this.jobSteps = Gui_JobStepsTab.jobStepsTextField.getText();
		this.hazards = Gui_JobStepsTab.hazardsTextField.getText();
		this.controls = Gui_JobStepsTab.controlMeasuresTextField.getText();
		this.risk = getRadioButtonState(Gui_JobStepsTab.rb1, Gui_JobStepsTab.rb2);
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

	public String getHazards() {
		return hazards;
	}

	public String getControls() {
		return controls;
	}

	public String getRisk() {
		return risk;
	}

	@Override
	public String toString() {
		return "[jobSteps=" + jobSteps + ", hazards=" + hazards + ", controls=" + controls + ", risk="
				+ risk;
	}


}