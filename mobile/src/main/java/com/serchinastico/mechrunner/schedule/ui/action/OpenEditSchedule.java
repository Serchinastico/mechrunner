package com.serchinastico.mechrunner.schedule.ui.action;

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

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;

import com.serchinastico.mechrunner.schedule.domain.model.Schedule;
import com.serchinastico.mechrunner.schedule.ui.activity.EditScheduleActivity;

public class OpenEditSchedule {

	private Activity activity;
	private ViewsList commonViews;

	public OpenEditSchedule(Activity activity, ViewsList commonViews) {
		this.activity = activity;
		this.commonViews = commonViews;
	}

	public void execute(final Schedule schedule) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(activity, EditScheduleActivity.class);

				intent.putExtra(EditScheduleActivity.SCHEDULE_EXTRA, schedule);

				activity.startActivity(intent,
						ActivityOptions.makeSceneTransitionAnimation(activity,
								commonViews.toPairsArray()).toBundle());
			}
		});
	}

}
