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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.melnykov.fab.FloatingActionButton;
import com.serchinastico.mechrunner.R;
import com.serchinastico.mechrunner.schedule.domain.model.Schedule;
import com.serchinastico.mechrunner.schedule.domain.model.Step;
import com.serchinastico.mechrunner.schedule.ui.action.OpenCreateStep;
import com.serchinastico.mechrunner.schedule.ui.action.OpenEditScheduleDetails;
import com.serchinastico.mechrunner.schedule.ui.action.ViewsList;
import com.serchinastico.mechrunner.schedule.ui.presenter.EditSchedulePresenter;
import com.serchinastico.mechrunner.schedule.ui.view.DividerDecoration;
import com.serchinastico.mechrunner.schedule.ui.view.steplist.StepListAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditScheduleActivity extends ActionBarActivity implements EditSchedulePresenter.View {

	public static final String SCHEDULE_EXTRA = "schedule";
	private static final int EDIT_SCHEDULE = 1;
	private static final int GET_NEW_STEP = 2;
	private static final int DEFAULT_REPETITIONS_COUNT = -1;

	@InjectView(R.id.steps_list)
	RecyclerView stepsList;

	@InjectView(R.id.add_step_button)
	FloatingActionButton addStepButton;

	@InjectView(R.id.create_schedule_toolbar)
	Toolbar toolbar;

	private EditSchedulePresenter presenter;
	private RecyclerView.Adapter stepsListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_schedule);
		ButterKnife.inject(this);

		presenter = new EditSchedulePresenter(this);
		presenter.setView(this);
		presenter.create();

		Schedule schedule = loadSchedule();
		presenter.setSchedule(schedule);

		configureViews(schedule);
	}

	private void configureViews(Schedule schedule) {
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
		OpenCreateStep action = new OpenCreateStep(this, getCommonViews());
		action.execute(GET_NEW_STEP);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case EDIT_SCHEDULE:
				handleEditScheduleResult(resultCode, data);
				break;
			case GET_NEW_STEP:
				handleGetNewStepResult(resultCode, data);
				break;
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
				OpenEditScheduleDetails action = new OpenEditScheduleDetails(this, getCommonViews());
				action.execute(EDIT_SCHEDULE);
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void updateSteps() {
		stepsListAdapter.notifyDataSetChanged();
	}

	@Override
	public void setTitle(final String title) {
		toolbar.setTitle(title);
	}

	private void handleEditScheduleResult(int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			String name = data.getStringExtra(
					EditScheduleDetailsActivity.SCHEDULE_NAME_EXTRA);
			int repetitionsCount = data.getIntExtra(
					EditScheduleDetailsActivity.SCHEDULE_REPETITIONS_COUNT_EXTRA,
					DEFAULT_REPETITIONS_COUNT);

			presenter.setScheduleDetails(name, repetitionsCount);
		}
	}

	private void handleGetNewStepResult(int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Step newStep = (Step) data.getSerializableExtra(CreateStepActivity.NEW_STEP_EXTRA);
			presenter.addStep(newStep);
		}
	}

	private Schedule loadSchedule() {
		return (Schedule) getIntent().getSerializableExtra(SCHEDULE_EXTRA);
	}

	private ViewsList getCommonViews() {
		ViewsList commonViews = new ViewsList();
		commonViews.add(addStepButton);
		commonViews.add(toolbar);
		return commonViews;
	}

}
