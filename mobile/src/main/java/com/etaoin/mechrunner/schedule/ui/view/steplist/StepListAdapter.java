package com.etaoin.mechrunner.schedule.ui.view.steplist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.etaoin.mechrunner.R;
import com.etaoin.mechrunner.schedule.ui.model.Schedule;

public class StepListAdapter extends RecyclerView.Adapter<StepListViewHolder> {

	private final Schedule schedule;

	public StepListAdapter(Schedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public StepListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext())
				.inflate(R.layout.step_list_item, parent, false);
		return new StepListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(StepListViewHolder holder, int position) {
		holder.setStep(schedule.getStep(position), position);
	}

	@Override
	public int getItemCount() {
		return schedule.size();
	}
}
