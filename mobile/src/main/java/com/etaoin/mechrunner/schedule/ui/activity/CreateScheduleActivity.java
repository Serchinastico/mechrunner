package com.etaoin.mechrunner.schedule.ui.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.etaoin.mechrunner.R;
import com.etaoin.mechrunner.schedule.ui.action.OpenCreateSchedule;
import com.etaoin.mechrunner.schedule.ui.action.OpenCreateStep;
import com.etaoin.mechrunner.schedule.ui.action.OpenEditSchedule;
import com.etaoin.mechrunner.schedule.ui.model.Schedule;
import com.etaoin.mechrunner.schedule.ui.model.Step;
import com.etaoin.mechrunner.schedule.ui.view.DividerDecoration;
import com.etaoin.mechrunner.schedule.ui.view.schedulelist.ScheduleListAdapter;
import com.etaoin.mechrunner.schedule.ui.view.steplist.StepListAdapter;
import com.melnykov.fab.FloatingActionButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CreateScheduleActivity extends ActionBarActivity {

	private static final int GET_NEW_STEP = 1;
	private static final String SCHEDULE_SAVED_INSTANCE = "schedule_saved_instance";

	@InjectView(R.id.steps_list)
	RecyclerView stepsList;

	@InjectView(R.id.add_step_button)
	FloatingActionButton addStepButton;

	@InjectView(R.id.create_schedule_toolbar)
	Toolbar toolbar;

	private RecyclerView.Adapter stepsListAdapter;
	private Schedule schedule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_schedule);
		ButterKnife.inject(this);

		loadSchedule(savedInstanceState);

		configureViews();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(SCHEDULE_SAVED_INSTANCE, schedule);

		super.onSaveInstanceState(outState);
	}

	private void configureViews() {
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		stepsList.setLayoutManager(layoutManager);
		stepsListAdapter = new StepListAdapter(schedule);
		stepsList.setAdapter(stepsListAdapter);
		stepsList.addItemDecoration(new DividerDecoration());

		addStepButton.attachToRecyclerView(stepsList);

		toolbar.setTitle(schedule.getName());
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@OnClick(R.id.add_step_button)
	void onAddStepButtonClick() {
		OpenCreateStep action = new OpenCreateStep(this, addStepButton, toolbar);
		action.execute(GET_NEW_STEP);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == GET_NEW_STEP) {
			if (resultCode == RESULT_OK) {
				Step newStep = (Step) data.getSerializableExtra(CreateStepActivity.NEW_STEP_EXTRA);
				schedule.addStep(newStep);
				stepsListAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_create_schedule, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				OpenEditSchedule action = new OpenEditSchedule(this, addStepButton, toolbar);
				action.execute();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void loadSchedule(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			schedule = (Schedule) savedInstanceState.getSerializable(SCHEDULE_SAVED_INSTANCE);
		}

		if (schedule == null) {
			schedule = new Schedule("Schedule 1");
		}
	}
}
