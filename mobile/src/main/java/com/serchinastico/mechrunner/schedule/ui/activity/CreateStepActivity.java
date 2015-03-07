package com.serchinastico.mechrunner.schedule.ui.activity;

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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.serchinastico.mechrunner.R;
import com.serchinastico.mechrunner.schedule.domain.model.Step;
import com.serchinastico.mechrunner.schedule.domain.model.StepType;
import com.serchinastico.mechrunner.schedule.ui.presenter.CreateStepPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CreateStepActivity extends ActionBarActivity implements CreateStepPresenter.View {

	public static final String NEW_STEP_EXTRA = "new_step";

	@InjectView(R.id.duration_seconds_edit_text)
	EditText durationSecondsEditText;

	@InjectView(R.id.step_type_radio_group)
	RadioGroup stepTypeRadioGroup;

	@InjectView(R.id.create_step_toolbar)
	Toolbar toolbar;

	private CreateStepPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_step);
		ButterKnife.inject(this);

		presenter = new CreateStepPresenter();
		presenter.setView(this);

		configureViews();
	}

	private void configureViews() {
		toolbar.setTitle(getResources().getString(R.string.create_step_title));
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@OnClick(R.id.add_step_button)
	void onCreateStepButtonClick() {
		try {
			presenter.onCreateStep(Integer.parseInt(durationSecondsEditText.getText().toString()),
					getStepTypeFromSelection());
		} catch (IllegalArgumentException e) {
			durationSecondsEditText.setError(
					getResources().getString(R.string.error_invalid_duration));
		}
	}

	@Override
	public void returnStep(Step step) {
		Intent stepData = new Intent();
		stepData.putExtra(NEW_STEP_EXTRA, step);
		setResult(RESULT_OK, stepData);
		finish();
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
