package io.chronize.adsb.clock;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Clock {

	public static final int MAX_HOURS = 24;
	public static final int MAX_MINUTES = 60;
	public static final String CLOCK_SOUND = "Tick tock tick tock";
	public static final String TIME_FORMAT = "%d:%02d";

	protected int _hours;
	protected int _minutes;

	public Clock() {
		this(12, 0);
	}

	public Clock(int hours, int minutes) {
		_hours = hours;
		_minutes = minutes;
	}

	/**
	 * Get clock hour.
	 *
	 * @return clock hour
	 */
	public int getHours() {
		return _hours;
	}

	/**
	 * Get clock minute.
	 *
	 * @return clock minute
	 */
	public int getMinutes() {
		return _minutes;
	}

	/**
	 * Set clock hour.
	 *
	 * hours clock hour
	 */
	public void setHours(int hours) {
		_hours = hours;
	}

	/**
	 * Set clock minute.
	 *
	 * @param minutes clock minute
	 */
	public void setMinutes(int minutes) {
		_minutes = minutes;
	}

	/**
	 * Set clock time.
	 *
	 * @param hours clock hour
	 * @param minutes clock minute
	 */
	public void setTime(int hours, int minutes) {
		_hours = hours;
		_minutes = minutes;
	}

	/**
	 * Add a minute, adding an hour if necessary.
	 */
	public void addMinute() {
		if (_minutes == MAX_MINUTES - 1) {
			setMinutes(0);
			addHour();
		}
		else
			setMinutes(_minutes + 1);
	}

	/**
	 * Add an hour.
	 */
	public void addHour() {
		if (_hours == MAX_HOURS - 1) {
			setHours(0);
		}
		else
			setHours(_hours + 1);
	}

	/**
	 * Set properties with JSON.
	 *
	 * json JSON for property transaction
	 */
	public void commitJson(String json) {
		JSONObject transaction = (JSONObject)(new JSONTokener(json).nextValue());
		if (!transaction.isNull("hour"))
			setHours(transaction.getInt("hour"));
		if (!transaction.isNull("minute"))
			setMinutes(transaction.getInt("minute"));
	}

	/**
	 * Make default clock noise.
	 *
	 * @return default clock noise
	 */
	public String makeNoise() {
		return CLOCK_SOUND;
	}

	@Override
	public String toString() {
		return String.format(TIME_FORMAT, _hours, _minutes);
	}

	@Override
	public boolean equals(Object o) {
		Clock other = (Clock)(o);
		return (other.getHours() == _hours) && (other.getMinutes() == _minutes);
	}

}
