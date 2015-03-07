package com.serchinastico.mechrunner.schedule.ui.view.steplist;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.serchinastico.mechrunner.R;
import com.serchinastico.mechrunner.schedule.domain.model.Step;

public class StepListViewHolder extends RecyclerView.ViewHolder {

	private TextView title;
	private TextView description;

	public StepListViewHolder(LinearLayout view) {
		super(view);

		title = (TextView) view.findViewById(R.id.step_title);
		description = (TextView) view.findViewById(R.id.step_description);
	}

	public void setStep(Step step, int position) {
		title.setText("Step " + (position + 1));
		description.setText(getStepDescription(step));
	}

	private String getStepDescription(Step step) {
		String description;
		// TODO translatable
		switch (step.getType()) {
			case REST:
				description = step.getDurationSeconds() + " seconds of rest";
				break;
			case EXERCISE:
			default:
				description = step.getDurationSeconds() + " seconds of exercise";
				break;
		}

		return description;
	}
}
