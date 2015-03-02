package com.etaoin.mechrunner.schedule.service;

import android.content.Context;
import android.media.MediaPlayer;

import com.etaoin.mechrunner.R;
import com.etaoin.mechrunner.schedule.domain.Schedule;
import com.etaoin.mechrunner.schedule.domain.Step;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleTimer {

	private final Context context;
	private Iterator<Step> stepsIterator;
	private Timer timer;
	private MediaPlayer player;

	public ScheduleTimer(Context context) {
		this.context = context;
	}

	public synchronized void start(Schedule schedule) {
		stepsIterator = schedule.iterator();
		timer = new Timer();
		scheduleNextStep();
	}

	public synchronized void stop() {
		releasePlayer();

		if (timer != null) {
			timer.cancel();
			timer.purge();
			timer = null;
		}
	}

	private synchronized void scheduleNextStep() {
		if (stepsIterator.hasNext()) {
			Step step = stepsIterator.next();
			timer.schedule(createTimerTask(), step.getDurationSeconds() * 1000);
		}
	}

	private TimerTask createTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				releasePlayer();
				player = MediaPlayer.create(context, R.raw.beep);
				player.start();
				scheduleNextStep();
			}
		};
	}

	private void releasePlayer() {
		if (player != null) {
			player.stop();
			player.release();
			player = null;
		}
	}

}
