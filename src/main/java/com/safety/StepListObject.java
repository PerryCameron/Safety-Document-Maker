package com.safety;

import java.util.List;

public class StepListObject {

	// Aggregation relationship
	private List<StepsObject> stepList;

	public StepListObject(List<StepsObject> stepList) {
		super();
		this.stepList = stepList;
	}

	public StepsObject getSteps(int index) {
		return stepList.get(index);
	}

	public void setStep(StepsObject thisStep) {
		this.stepList.add(thisStep);
	}
}
