package com.etaoin.mechrunner.schedule.ui.mapper;

import com.etaoin.mechrunner.schedule.ui.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {

	public List<Schedule> transformDomainToUi(
			List<com.etaoin.mechrunner.schedule.domain.Schedule> schedules) {
		List<Schedule> uiSchedules = new ArrayList<>();

		for (com.etaoin.mechrunner.schedule.domain.Schedule schedule : schedules) {
			uiSchedules.add(new Schedule(schedule.getName()));
		}

		return uiSchedules;
	}
}
