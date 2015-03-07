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

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;

import com.melnykov.fab.FloatingActionButton;
import com.serchinastico.mechrunner.R;
import com.serchinastico.mechrunner.schedule.domain.model.Schedule;
import com.serchinastico.mechrunner.schedule.ui.action.OpenEditSchedule;
import com.serchinastico.mechrunner.schedule.ui.action.ViewsList;
import com.serchinastico.mechrunner.schedule.ui.presenter.ScheduleListPresenter;
import com.serchinastico.mechrunner.schedule.ui.view.DividerDecoration;
import com.serchinastico.mechrunner.schedule.ui.view.schedulelist.ScheduleListAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ScheduleListActivity extends ActionBarActivity implements ScheduleListPresenter.View {

	@InjectView(R.id.schedules_list)
	RecyclerView scheduleList;

	@InjectView(R.id.add_schedule_button)
	FloatingActionButton addScheduleButton;

	@InjectView(R.id.schedule_list_toolbar)
	Toolbar toolbar;

	private ScheduleListPresenter presenter;

	private ScheduleListAdapter scheduleListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_list);
		ButterKnife.inject(this);

		presenter = new ScheduleListPresenter(this);
		presenter.setView(this);
		presenter.create();

		configureViews();
	}

	private void configureViews() {
		LayoutManager layoutManager = new LinearLayoutManager(this);
		scheduleList.setLayoutManager(layoutManager);
		scheduleListAdapter = new ScheduleListAdapter(this);
		scheduleList.setAdapter(scheduleListAdapter);
		scheduleList.addItemDecoration(new DividerDecoration());

		addScheduleButton.attachToRecyclerView(scheduleList);
		setSupportActionBar(toolbar);
	}

	@OnClick(R.id.add_schedule_button)
	void onAddScheduleButtonClick() {
		presenter.createSchedule();
	}

	@Override
	public void setSchedules(List<Schedule> schedules) {
		scheduleListAdapter.setSchedules(schedules);
		scheduleListAdapter.notifyDataSetChanged();
	}

	@Override
	public void openEditSchedule(Schedule schedule) {
		OpenEditSchedule action = new OpenEditSchedule(this, getCommonViews());
		action.execute(schedule);
	}

	private ViewsList getCommonViews() {
		ViewsList commonViews = new ViewsList();
		commonViews.add(addScheduleButton);
		commonViews.add(toolbar);
		return commonViews;
	}

}
