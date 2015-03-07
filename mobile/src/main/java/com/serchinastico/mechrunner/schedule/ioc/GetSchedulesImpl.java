package com.serchinastico.mechrunner.schedule.ioc;

import android.content.Context;

import com.serchinastico.mechrunner.executor.ExecutorProvider;
import com.serchinastico.mechrunner.schedule.storage.ScheduleStorage;
import com.serchinastico.mechrunner.schedule.usecase.GetSchedules;

import java.util.concurrent.Executor;

public class GetSchedulesImpl implements GetSchedules, Runnable {

	private final Context context;
	private final Executor executor;
	private Callback callback;

	public GetSchedulesImpl(Context context) {
		this.context = context;
		this.executor = ExecutorProvider.getUseCaseExecutor();
	}

	@Override
	public void execute(Callback callback) {
		this.callback = callback;
		executor.execute(this);
	}

	@Override
	public void run() {
		ScheduleStorage storage = new ScheduleStorage(context);
		callback.onSuccess(storage.getAll());
	}
}
