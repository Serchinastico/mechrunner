package com.etaoin.mechrunner.schedule.ui.action;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.View;

import com.etaoin.mechrunner.schedule.ui.activity.CreateScheduleActivity;

public class OpenCreateSchedule {

	private Activity activity;
	private View commonFab;
	private View commonToolbar;

	public OpenCreateSchedule(Activity activity, View commonFab, View commonToolbar) {
		this.activity = activity;
		this.commonFab = commonFab;
		this.commonToolbar = commonToolbar;
	}

	public void execute() {
		Intent intent = new Intent(activity, CreateScheduleActivity.class);
		activity.startActivity(intent,
				ActivityOptions.makeSceneTransitionAnimation(activity,
						Pair.create(commonFab, "fab"),
						Pair.create(commonToolbar, "toolbar")).toBundle());
	}

}
