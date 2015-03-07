package com.serchinastico.mechrunner.schedule.ui.view.steplist;

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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.serchinastico.mechrunner.R;
import com.serchinastico.mechrunner.schedule.domain.model.Schedule;

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
