package com.etaoin.mechrunner.schedule.ioc;

import android.content.Context;

import com.etaoin.mechrunner.schedule.domain.Schedule;
import com.etaoin.mechrunner.schedule.storage.ScheduleDatabaseHelper;
import com.etaoin.mechrunner.schedule.usecase.AddSchedule;

import java.util.concurrent.Executor;

public class AddScheduleImpl implements AddSchedule, Runnable {

	private final Context context;
	private final Executor executor;
	private Schedule schedule;

	public AddScheduleImpl(Context context, Executor executor) {
		this.context = context;
		this.executor = executor;
	}

	@Override
	public void execute(Schedule schedule) {
		executor.execute(this);
		this.schedule = schedule;
	}

	@Override
	public void run() {
		ScheduleDatabaseHelper dbHelper = new ScheduleDatabaseHelper(context);
		dbHelper.insert(schedule);
	}
}
