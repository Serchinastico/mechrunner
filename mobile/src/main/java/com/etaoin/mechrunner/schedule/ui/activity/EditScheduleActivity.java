package com.etaoin.mechrunner.schedule.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.etaoin.mechrunner.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditScheduleActivity extends ActionBarActivity {

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

	@OnClick(R.id.add_step_button)
	void onCreateStepButtonClick() {
		// TODO Store new data in schedule domain object
		finish();
	}


}
