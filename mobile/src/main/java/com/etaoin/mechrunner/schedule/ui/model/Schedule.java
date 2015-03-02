package com.etaoin.mechrunner.schedule.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 7005757609587499277L;

	private final List<Step> steps = new ArrayList<>();
	private int repetitionsCount;
	private String name;

	public Schedule(String name) {
		this.name = name;
	}

	public boolean addStep(Step step) {
		return steps.add(step);
	}

	public Step getStep(int index) {
		return steps.get(index);
	}

	public int size() {
		return steps.size();
	}

	public String getName() {
		return name;
	}
}
