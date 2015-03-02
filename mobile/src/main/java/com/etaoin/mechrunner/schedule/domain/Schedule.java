package com.etaoin.mechrunner.schedule.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Schedule {

	private long id;
	private final List<Step> steps = new ArrayList<>();
	private int repetitionsCount;
	private String name;

	public Schedule(String name) {
		this.name = name;
	}

	public Schedule(int repetitionsCount, String name) {
		this.repetitionsCount = repetitionsCount;
		this.name = name;
	}

	public Schedule(long id, int repetitionsCount, String name) {
		this.id = id;
		this.repetitionsCount = repetitionsCount;
		this.name = name;
	}

	public boolean addStep(Step step) {
		return steps.add(step);
	}

	public Iterator<Step> iterator() {
		return steps.iterator();
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRepetitionsCount() {
		return repetitionsCount;
	}

	public String getName() {
		return name;
	}
}
