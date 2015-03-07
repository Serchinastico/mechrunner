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
import com.serchinastico.mechrunner.schedule.ioc.CreateScheduleImpl;
import com.serchinastico.mechrunner.schedule.ioc.GetSchedulesImpl;
import com.serchinastico.mechrunner.schedule.usecase.CreateSchedule;
import com.serchinastico.mechrunner.schedule.usecase.GetSchedules;

import java.util.List;

/**
 * @author Sergio Gutiérrez Mota.
 */
public class ScheduleListPresenter extends BasePresenter implements GetSchedules.Callback,
		CreateSchedule.Callback {

	private final GetSchedules getSchedules;
	private final CreateSchedule createSchedule;
	private View view;

	public ScheduleListPresenter(Context context) {
		this.getSchedules = new GetSchedulesImpl(context);
		this.createSchedule = new CreateScheduleImpl(context);
	}

	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void create() {
		super.create();
		getSchedules.execute(this);
	}

	@Override
	public void onSuccess(final List<Schedule> schedules) {
		view.setSchedules(schedules);
	}

	@Override
	public void onSuccess(Schedule schedule) {
		view.openEditSchedule(schedule);
	}

	public void createSchedule() {
		createSchedule.execute(this);
	}

	public interface View {

		void setSchedules(List<Schedule> schedules);

		void openEditSchedule(Schedule schedule);

	}

}
