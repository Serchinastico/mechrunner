package com.etaoin.mechrunner.schedule.ioc;

import android.content.Context;

import com.etaoin.mechrunner.schedule.storage.ScheduleDatabaseHelper;
import com.etaoin.mechrunner.schedule.usecase.GetSchedules;

import java.util.concurrent.Executor;

public class GetSchedulesImpl implements GetSchedules, Runnable {

	private final Context context;
	private final Executor executor;
	private Listener listener;

	public GetSchedulesImpl(Context context, Executor executor) {
		this.context = context;
		this.executor = executor;
	}

	@Override
	public void execute(Listener listener) {
		this.listener = listener;
		executor.execute(this);
	}

	@Override
	public void run() {
		ScheduleDatabaseHelper dbHelper = new ScheduleDatabaseHelper(context);
		listener.onSuccess(dbHelper.getAll());
	}
}
