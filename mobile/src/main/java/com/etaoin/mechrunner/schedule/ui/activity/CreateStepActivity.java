package com.etaoin.mechrunner.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.etaoin.mechrunner.R;
import com.etaoin.mechrunner.schedule.ui.model.Step;
import com.etaoin.mechrunner.schedule.ui.model.StepType;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CreateStepActivity extends ActionBarActivity {

	public static final String NEW_STEP_EXTRA = "new_step";

	@InjectView(R.id.duration_seconds_edit_text)
	EditText durationSecondsEditText;

	@InjectView(R.id.step_type_radio_group)
	RadioGroup stepTypeRadioGroup;

	@InjectView(R.id.create_step_toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_step);
		ButterKnife.inject(this);

		configureViews();
	}

	private void configureViews() {
		toolbar.setTitle(getResources().getString(R.string.create_step_title));
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@OnClick(R.id.add_step_button)
	void onCreateStepButtonClick() {
		if (checkUserData()) {
			Intent stepData = new Intent();
			stepData.putExtra(NEW_STEP_EXTRA, createStepFromUserData());
			setResult(RESULT_OK, stepData);
			finish();
		}
	}

	private boolean checkUserData() {
		boolean isValid = false;
		try {
			Integer.parseInt(durationSecondsEditText.getText().toString());
			isValid = true;
		} catch (NumberFormatException e) {
			durationSecondsEditText.setError(
					getResources().getString(R.string.error_invalid_duration));
		}
		return isValid;
	}

	private Step createStepFromUserData() {
		int durationSeconds = Integer.parseInt(durationSecondsEditText.getText().toString());
		return new Step(durationSeconds, getStepTypeFromSelection());
	}

	private StepType getStepTypeFromSelection() {
		StepType stepType;
		switch (stepTypeRadioGroup.getCheckedRadioButtonId()) {
			case R.id.step_type_rest_radio_button:
				stepType = StepType.REST;
				break;
			case R.id.step_type_exercise_radio_button:
			default:
				stepType = StepType.EXERCISE;
				break;
		}
		return stepType;
	}

}
