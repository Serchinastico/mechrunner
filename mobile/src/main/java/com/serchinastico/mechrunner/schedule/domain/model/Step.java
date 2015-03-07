package com.serchinastico.mechrunner.schedule.domain.model;

import java.io.Serializable;

public class Step implements Serializable {

	private static final long serialVersionUID = 357274565245129713L;

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
