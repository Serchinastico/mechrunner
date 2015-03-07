package com.serchinastico.mechrunner.schedule.usecase;

import com.serchinastico.mechrunner.schedule.domain.model.Schedule;

import java.util.List;

public interface GetSchedules {

	public void execute(Callback callback);

	public static interface Callback {

		void onSuccess(List<Schedule> schedules);

	}

}
