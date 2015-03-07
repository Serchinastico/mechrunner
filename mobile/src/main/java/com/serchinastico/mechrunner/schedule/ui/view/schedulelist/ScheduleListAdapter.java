package com.serchinastico.mechrunner.schedule.ui.view.schedulelist;

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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serchinastico.mechrunner.R;
import com.serchinastico.mechrunner.schedule.domain.model.Schedule;

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
		Schedule schedule = schedules.get(position);
		holder.setText(schedule.getName() + " (x" + schedule.getRepetitionsCount() + ")");
	}

	@Override
	public int getItemCount() {
		return schedules == null ? 0 : schedules.size();
	}
}
