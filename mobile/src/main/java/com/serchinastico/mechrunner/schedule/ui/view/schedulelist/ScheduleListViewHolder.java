package com.serchinastico.mechrunner.schedule.ui.view.schedulelist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.serchinastico.mechrunner.schedule.service.PlayerService;

public class ScheduleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private final Context context;
	private final TextView textView;

	public ScheduleListViewHolder(Context context, TextView view) {
		super(view);
		this.context = context;
		textView = view;
		textView.setOnClickListener(this);
	}

	public void setText(String text) {
		textView.setText(text);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, PlayerService.class);
		intent.putExtra(PlayerService.ACTION_EXTRA, PlayerService.ACTION_START);
		context.startService(intent);
	}
}