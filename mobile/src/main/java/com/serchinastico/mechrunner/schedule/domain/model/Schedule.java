package com.serchinastico.mechrunner.schedule.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 4440550559130047199L;

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

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setRepetitionsCount(int repetitionsCount) {
		this.repetitionsCount = repetitionsCount;
	}

	public int getRepetitionsCount() {
		return repetitionsCount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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

	public Iterator<Step> iterator() {
		return steps.iterator();
	}
}
