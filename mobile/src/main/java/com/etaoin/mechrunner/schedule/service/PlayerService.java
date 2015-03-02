package com.etaoin.mechrunner.schedule.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.etaoin.mechrunner.schedule.domain.Schedule;
import com.etaoin.mechrunner.schedule.domain.Step;
import com.etaoin.mechrunner.schedule.domain.StepType;

public class PlayerService extends Service {

	public static final String ACTION_EXTRA = "action";
	public static final String ACTION_START = "start";
	public static final String ACTION_STOP = "stop";

	private Looper looper;
	private PlayerServiceHandler handler;
	private Schedule schedule;
	private ScheduleTimer timer;

	@Override
	public void onCreate() {
		super.onCreate();
		HandlerThread thread = new HandlerThread("ServiceStartArguments", 1);
		thread.start();

		looper = thread.getLooper();
		handler = new PlayerServiceHandler(looper);
		schedule = new Schedule("Schedule");
		schedule.addStep(new Step(2, StepType.EXERCISE));
		schedule.addStep(new Step(3, StepType.REST));
		schedule.addStep(new Step(4, StepType.EXERCISE));
		timer = new ScheduleTimer(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Message msg = handler.obtainMessage();
		msg.obj = intent.getStringExtra(ACTION_EXTRA);
		handler.sendMessage(msg);

		return START_STICKY;
	}

	private final class PlayerServiceHandler extends Handler {

		public PlayerServiceHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String action = (String) msg.obj;

			switch (action) {
				case ACTION_START:
					timer.start(schedule);
					break;
				case ACTION_STOP:
				default:
					timer.stop();
					break;
			}
		}
	}
}
