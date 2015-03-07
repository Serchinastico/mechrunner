package com.serchinastico.mechrunner.schedule.ioc;

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

import com.serchinastico.mechrunner.executor.ExecutorProvider;
import com.serchinastico.mechrunner.schedule.domain.model.Schedule;
import com.serchinastico.mechrunner.schedule.storage.ScheduleStorage;
import com.serchinastico.mechrunner.schedule.usecase.CreateSchedule;

import java.util.concurrent.Executor;

public class CreateScheduleImpl implements CreateSchedule, Runnable {

	private static final int DEFAULT_REPETITIONS_COUNT = 4;
	private static final String DEFAULT_SCHEDULE_NAME = "New schedule";

	private final Context context;
	private final Executor executor;
	private Callback callback;

	public CreateScheduleImpl(Context context) {
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

		Schedule schedule = new Schedule(DEFAULT_REPETITIONS_COUNT, DEFAULT_SCHEDULE_NAME);
		storage.insert(schedule);

		callback.onSuccess(schedule);
	}
}
