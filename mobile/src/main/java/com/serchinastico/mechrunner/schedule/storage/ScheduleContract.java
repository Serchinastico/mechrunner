package com.serchinastico.mechrunner.schedule.storage;

import android.provider.BaseColumns;

public class ScheduleContract {

	public static abstract class ScheduleEntry implements BaseColumns {
		public static final String TABLE_NAME = "schedules";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_REPETITIONS_COUNT = "repetitions_count";
	}

}
