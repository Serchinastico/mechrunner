package com.etaoin.mechrunner.schedule.ui.view.schedulelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etaoin.mechrunner.R;
import com.etaoin.mechrunner.schedule.ui.model.Schedule;

import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListViewHolder> {

	private final Context context;
	private List<Schedule> schedules;

	public ScheduleListAdapter(Context context) {
		this.context = context;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@Override
	public ScheduleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		TextView view = (TextView) LayoutInflater.from(parent.getContext())
				.inflate(R.layout.schedule_list_item, parent, false);
		return new ScheduleListViewHolder(context, view);
	}

	@Override
	public void onBindViewHolder(ScheduleListViewHolder holder, int position) {
		holder.setText(schedules.get(position).getName());
	}

	@Override
	public int getItemCount() {
		return schedules == null ? 0 : schedules.size();
	}
}
