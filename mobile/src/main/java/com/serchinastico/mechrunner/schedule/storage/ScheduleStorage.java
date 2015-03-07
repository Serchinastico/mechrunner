package com.serchinastico.mechrunner.schedule.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.serchinastico.mechrunner.schedule.domain.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleStorage extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Schedule.db";

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + ScheduleContract.ScheduleEntry.TABLE_NAME + " (" +
					ScheduleContract.ScheduleEntry._ID + " INTEGER PRIMARY KEY," +
					ScheduleContract.ScheduleEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
					ScheduleContract.ScheduleEntry.COLUMN_NAME_REPETITIONS_COUNT + TEXT_TYPE +
					" )";

	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + ScheduleContract.ScheduleEntry.TABLE_NAME;

	public ScheduleStorage(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

	public void insert(Schedule schedule) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_NAME, schedule.getName());
		values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_REPETITIONS_COUNT,
				schedule.getRepetitionsCount());

		long newRowId = db.insert(
				ScheduleContract.ScheduleEntry.TABLE_NAME,
				null,
				values);

		schedule.setId(newRowId);
	}

	public List<Schedule> getAll() {
		List<Schedule> schedules = new ArrayList<>();
		SQLiteDatabase db = getReadableDatabase();

		String[] projection = {
				ScheduleContract.ScheduleEntry._ID,
				ScheduleContract.ScheduleEntry.COLUMN_NAME_NAME,
				ScheduleContract.ScheduleEntry.COLUMN_NAME_REPETITIONS_COUNT
		};

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				ScheduleContract.ScheduleEntry._ID + " DESC";

		Cursor c = db.query(
				ScheduleContract.ScheduleEntry.TABLE_NAME,
				projection,
				null,
				null,
				null,
				null,
				sortOrder
		);

		c.moveToFirst();
		while (!c.isAfterLast()) {
			schedules.add(new Schedule(
					c.getLong(c.getColumnIndexOrThrow(
							ScheduleContract.ScheduleEntry._ID)),
					c.getInt(c.getColumnIndexOrThrow(
							ScheduleContract.ScheduleEntry.COLUMN_NAME_REPETITIONS_COUNT)),
					c.getString(c.getColumnIndexOrThrow(
							ScheduleContract.ScheduleEntry.COLUMN_NAME_NAME))));
			c.moveToNext();
		}

		return schedules;
	}

	public void update(Schedule schedule) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_NAME, schedule.getName());
		values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_REPETITIONS_COUNT,
				schedule.getRepetitionsCount());

		db.update(
				ScheduleContract.ScheduleEntry.TABLE_NAME,
				values,
				ScheduleContract.ScheduleEntry._ID + "=" + schedule.getId(),
				null);
	}

}
