package com.etaoin.mechrunner.schedule.usecase;

import com.etaoin.mechrunner.schedule.domain.Schedule;

import java.util.List;

public interface GetSchedules {

	public void execute(Listener listener);

	public static interface Listener {

		void onSuccess(List<Schedule> schedules);

	}

}
