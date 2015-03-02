package com.etaoin.mechrunner.schedule.domain;

public class Step {

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
