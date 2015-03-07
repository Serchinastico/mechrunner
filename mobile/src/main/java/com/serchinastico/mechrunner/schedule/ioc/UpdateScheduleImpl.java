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
import com.serchinastico.mechrunner.schedule.usecase.UpdateSchedule;

import java.util.concurrent.Executor;

/**
 * @author Sergio Gutiérrez Mota.
 */
public class UpdateScheduleImpl implements UpdateSchedule, Runnable {

	private final Context context;
	private final Executor executor;
	private Schedule schedule;

	public UpdateScheduleImpl(Context context) {
		this.context = context;
		this.executor = ExecutorProvider.getUseCaseExecutor();
	}

	@Override
	public void execute(Schedule schedule) {
		this.schedule = schedule;
		executor.execute(this);
	}

	@Override
	public void run() {
		ScheduleStorage storage = new ScheduleStorage(context);
		storage.update(schedule);
	}
}
