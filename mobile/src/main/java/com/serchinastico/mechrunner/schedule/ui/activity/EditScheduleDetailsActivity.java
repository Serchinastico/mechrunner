package com.serchinastico.mechrunner.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.serchinastico.mechrunner.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditScheduleDetailsActivity extends ActionBarActivity {

	public static final String SCHEDULE_NAME_EXTRA = "schedule_name";
	public static final String SCHEDULE_REPETITIONS_COUNT_EXTRA = "schedule_repetitions";

	@InjectView(R.id.schedule_name_edit_text)
	EditText scheduleNameEditText;

	@InjectView(R.id.schedule_repetitions_count_edit_text)
	EditText scheduleRepetitionsCountEditText;

	@InjectView(R.id.edit_schedule_toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_schedule);
		ButterKnife.inject(this);

		configureViews();
	}

	private void configureViews() {
		toolbar.setTitle(getResources().getString(R.string.edit_schedule_title));
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@OnClick(R.id.accept_schedule_config)
	void onCreateStepButtonClick() {
		if (validateScheduleData()) {
			Intent stepData = new Intent();
			stepData.putExtra(SCHEDULE_NAME_EXTRA, scheduleNameEditText.getText().toString());
			stepData.putExtra(SCHEDULE_REPETITIONS_COUNT_EXTRA,
					Integer.parseInt(scheduleRepetitionsCountEditText.getText().toString()));
			setResult(RESULT_OK, stepData);
			finish();
		}
	}

	private boolean validateScheduleData() {
		boolean isValid = true;

		if (scheduleNameEditText.getText().toString().isEmpty()) {
			scheduleNameEditText.setError(
					getResources().getString(R.string.error_invalid_schedule_name));
			isValid = false;
		}
		try {
			Integer.parseInt(scheduleRepetitionsCountEditText.getText().toString());
		} catch (NumberFormatException e) {
			scheduleRepetitionsCountEditText.setError(
					getResources().getString(R.string.error_invalid_repetitions));
			isValid = false;
		}

		return isValid;
	}

}
