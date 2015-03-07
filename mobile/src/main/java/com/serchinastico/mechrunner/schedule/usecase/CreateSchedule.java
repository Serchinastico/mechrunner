package com.serchinastico.mechrunner.schedule.usecase;

import com.serchinastico.mechrunner.schedule.domain.model.Schedule;

public interface CreateSchedule {

	public void execute(Callback callback);

	public static interface Callback {

		void onSuccess(Schedule schedule);

	}

}
