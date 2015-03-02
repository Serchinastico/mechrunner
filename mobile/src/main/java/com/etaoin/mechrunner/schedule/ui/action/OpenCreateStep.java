package com.etaoin.mechrunner.schedule.ui.action;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.View;

import com.etaoin.mechrunner.schedule.ui.activity.CreateStepActivity;

public class OpenCreateStep {

	private Activity activity;
	private View commonFab;
	private View commonToolbar;

	public OpenCreateStep(Activity activity, View commonFab, View commonToolbar) {
		this.activity = activity;
		this.commonFab = commonFab;
		this.commonToolbar = commonToolbar;
	}

	public void execute(int requestCode) {
		Intent intent = new Intent(activity, CreateStepActivity.class);
		activity.startActivityForResult(intent,
				requestCode,
				ActivityOptions.makeSceneTransitionAnimation(activity,
						Pair.create(commonFab, "fab"),
						Pair.create(commonToolbar, "toolbar"))
						.toBundle());
	}

}
