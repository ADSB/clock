package io.chronize.adsb.clock;

import org.json.JSONObject;
import org.json.JSONTokener;

public class AlarmClock extends Clock {

	public static final String ALARM_SOUND = "Briiiiiiiiiiiiiiing";

	protected int _wakeUpHours;
	protected int _wakeUpMinutes;

	public AlarmClock() {
		super();
		_wakeUpHours = 12;
		_wakeUpMinutes = 0;
	}

	public AlarmClock(int hours, int minutes) {
		this(hours, minutes, 12, 0);
	}

	public AlarmClock(int hours, int minutes, int wakeUpHours, int wakeUpMinutes) {
		super(hours, minutes);
		_wakeUpHours = wakeUpHours;
		_wakeUpMinutes = wakeUpMinutes;
	}

	/**
	 * Get alarm hour.
	 *
	 * @return alarm hour
	 */
	public int getWakeUpHours() {
		return _wakeUpHours;
	}

	/**
	 * Get alarm minute.
	 *
	 * @return alarm minute
	 */
	public int getWakeUpMinutes() {
		return _wakeUpMinutes;
	}

	/**
	 * Set alarm hour.
	 *
	 * hours alarm hour
	 */
	public void setWakeUpHours(int hours) {
		_wakeUpHours = hours;
	}

	/**
	 * Set alarm minute.
	 *
	 * minutes alarm minute
	 */
	public void setWakeUpMinutes(int minutes) {
		_wakeUpMinutes = minutes;
	}

	/**
	 * Set alarm time.
	 *
	 * hours alarm hour
	 * minutes alarm minute
	 */
	public void setAlarmTime(int hours, int minutes) {
		_wakeUpHours = hours;
		_wakeUpMinutes = minutes;
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
		if (!transaction.isNull("alarmHour"))
			setWakeUpHours(transaction.getInt("alarmHour"));
		if (!transaction.isNull("alarmMinute"))
			setWakeUpMinutes(transaction.getInt("alarmMinute"));
	}

	/**
	 * Make alarm io.chronize.adsb.clock noise if the io.chronize.adsb.clock time matches the alarm time.
	 *
	 * @return alarm io.chronize.adsb.clock noise
	 */
	@Override
	public String makeNoise() {
		return ((_wakeUpHours == _hours) && (_wakeUpMinutes == _minutes)) ? ALARM_SOUND : CLOCK_SOUND;
	}

	@Override
	public String toString() {
		return String.format("Time=%s, Alarm=" + TIME_FORMAT, super.toString(), _wakeUpHours, _wakeUpMinutes);
	}

	@Override
	public boolean equals(Object o) {
		AlarmClock other = (AlarmClock)(o);
		return super.equals(o) && other.getWakeUpHours() == _wakeUpHours && other.getMinutes() == _wakeUpMinutes;
	}


}
