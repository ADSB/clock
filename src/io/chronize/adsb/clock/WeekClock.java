package io.chronize.adsb.clock;

import org.json.JSONObject;
import org.json.JSONTokener;

public class WeekClock extends Clock {

	public static final String[] WEEKDAY_HASH = {
		"Sunday",
		"Monday",
		"Tuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday"
	};

	public static final int MAX_DAYS = 7;

	protected int _days;

	public WeekClock() {
		super();
		_days = 0;
	}

	public WeekClock(int days) {
		super();
		_days = 0;
	}

	public WeekClock(int days, int hours, int minutes) {
		super(hours, minutes);
		_days = days;
	}

	/**
	 * Get clock day.
	 *
	 * @return clock day
	 */
	public int getDays() {
		return _days;
	}

	/**
	 * Set clock day.
	 *
	 * days clock day
	 */
	public void setDays(int days) {
		_days = days;
	}

	/**
	 * Add an hour, adding a day if necessary.
	 */
	@Override
	public void addHour() {
		if (_hours == MAX_HOURS - 1) {
			setHours(0);
			addDay();
		}
		else
			setHours(_hours + 1);
	}

	/**
	 * Add a day.
	 */
	public void addDay() {
		if (_days == MAX_DAYS - 1) {
			setDays(0);
		}
		else
			setDays(_days + 1);
	}

	/**
	 * Set properties with JSON.
	 *
	 * json JSON for property transaction
	 */
	@Override
	public void commitJson(String json) {
		super.commitJson(json);
		JSONObject transaction = (JSONObject)(new JSONTokener(json).nextValue());
		if (!transaction.isNull("day"))
			setDays(transaction.getInt("day"));
	}

	@Override
	public String toString() {
		return String.format("%s, %s", WEEKDAY_HASH[_days], super.toString());
	}
}
