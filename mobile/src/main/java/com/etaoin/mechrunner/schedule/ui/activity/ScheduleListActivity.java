package com.etaoin.mechrunner.schedule.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;

import com.etaoin.mechrunner.R;
import com.etaoin.mechrunner.schedule.domain.Schedule;
import com.etaoin.mechrunner.schedule.ioc.GetSchedulesImpl;
import com.etaoin.mechrunner.schedule.ui.action.OpenCreateSchedule;
import com.etaoin.mechrunner.schedule.ui.mapper.ScheduleMapper;
import com.etaoin.mechrunner.schedule.ui.view.DividerDecoration;
import com.etaoin.mechrunner.schedule.ui.view.schedulelist.ScheduleListAdapter;
import com.etaoin.mechrunner.schedule.usecase.AddSchedule;
import com.etaoin.mechrunner.schedule.usecase.GetSchedules;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ScheduleListActivity extends ActionBarActivity implements GetSchedules.Listener {

	@InjectView(R.id.schedules_list)
	RecyclerView scheduleList;

	@InjectView(R.id.add_schedule_button)
	FloatingActionButton addScheduleButton;

	@InjectView(R.id.schedule_list_toolbar)
	Toolbar toolbar;

	private GetSchedules getSchedules;
	private AddSchedule addSchedule;
	private ScheduleListAdapter scheduleListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_list);
		ButterKnife.inject(this);

		configureViews();
		getSchedules = new GetSchedulesImpl(this, Executors.newFixedThreadPool(4));
		getSchedules.execute(this);
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
		OpenCreateSchedule action = new OpenCreateSchedule(this, addScheduleButton, toolbar);
		action.execute();
	}

	@Override
	public void onSuccess(final List<Schedule> schedules) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ScheduleMapper mapper = new ScheduleMapper();
				scheduleListAdapter.setSchedules(mapper.transformDomainToUi(schedules));
				scheduleListAdapter.notifyDataSetChanged();
			}
		});
	}
}
