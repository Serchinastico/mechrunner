package com.serchinastico.mechrunner.schedule.ui.presenter;

/*
 * Copyright (C) 2015 Sergio Gutiérrez Mota.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;

import com.serchinastico.mechrunner.schedule.domain.model.Schedule;
import com.serchinastico.mechrunner.schedule.domain.model.Step;
import com.serchinastico.mechrunner.schedule.ioc.UpdateScheduleImpl;
import com.serchinastico.mechrunner.schedule.usecase.UpdateSchedule;

/**
 * @author Sergio Gutiérrez Mota.
 */
public class EditSchedulePresenter extends BasePresenter {

	private final UpdateSchedule updateSchedule;
	private View view;
	private Schedule schedule;

	public EditSchedulePresenter(Context context) {
		this.updateSchedule = new UpdateScheduleImpl(context);
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public void create() {
		super.create();
	}

	public void addStep(Step step) {
		schedule.addStep(step);
		view.updateSteps();
		updateSchedule.execute(schedule);
	}

	public void setScheduleDetails(String name, int repetitionsCount) {
		schedule.setName(name);
		schedule.setRepetitionsCount(repetitionsCount);
		view.setTitle(schedule.getName() + " (x" + schedule.getRepetitionsCount() + ")");
		updateSchedule.execute(schedule);
	}

	public interface View {

		void updateSteps();

		void setTitle(String title);
	}
}
