package com.etaoin.mechrunner.schedule.ui.model;

import java.io.Serializable;

public class Step implements Serializable {

	private static final long serialVersionUID = 1114793090530542564L;

	private int durationSeconds;
	private StepType type;

	public Step(int durationSeconds, StepType type) {
		this.durationSeconds = durationSeconds;
		this.type = type;
	}

	public int getDurationSeconds() {
		return durationSeconds;
	}

	public StepType getType() {
		return type;
	}
}
