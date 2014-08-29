package io.chronize.adsb.clock;

import org.json.*;

public class ClockTester {
	public static void main(String[] args) {

		Clock clock1 = new Clock();
		Clock clock2 = new Clock(3, 0);
		Clock clock3 = new Clock(11, 59);

		Tester.test("(Clock1)", clock1.toString(), "12:00");
		Tester.test("3:00", clock2.toString(), "3:00");
		Tester.test("11:59", clock3.toString(), "11:59");

		clock1.addMinute();
		clock2.addMinute();
		clock3.addMinute();

		Tester.test("(Clock1)+1", clock1.toString(), "12:01");
		Tester.test("3:00+1", clock2.toString(), "3:01");
		Tester.test("11:59+1", clock3.toString(), "12:00");

		AlarmClock alarmClock1 = new AlarmClock();
		AlarmClock alarmClock2 = new AlarmClock(1, 30, 8, 0);

		Tester.test("(AlarmClock1)", alarmClock1.toString(), "Time=12:00, Alarm=12:00");
		Tester.test("(AlarmClock2)", alarmClock2.toString(), "Time=1:30, Alarm=8:00");

		Tester.test("(Clock1 makenoise)", clock1.makeNoise(), "Tick tock tick tock");
		Tester.test("(Clock2 makenoise)", clock2.makeNoise(), "Tick tock tick tock");
		Tester.test("(Clock3 makenoise)", clock3.makeNoise(), "Tick tock tick tock");
		Tester.test("(AlarmClock1 makenoise)", alarmClock1.makeNoise(), "Briiiiiiiiiiiiiiing");
		Tester.test("(AlarmClock2 makenoise)", alarmClock2.makeNoise(), "Tick tock tick tock");

		clock1.setTime(10, 47);

		Tester.test("(Clock1)+1", clock1.toString(), "10:47");

		alarmClock1.setTime(3, 0);
		alarmClock1.setAlarmTime(3, 5);

		while (!alarmClock1.makeNoise().equals(AlarmClock.ALARM_SOUND)) {
			alarmClock1.addMinute();
			System.err.println(alarmClock1.makeNoise());
		}

		Tester.test("(AlarmClock1)", alarmClock1.toString(), "Time=3:05, Alarm=3:05");
		Tester.test("(Clock1 equals Clock2)", clock1.equals(clock2), false);
		Tester.test("(new AlarmClock equals AlarmClock1)", alarmClock1.equals(new AlarmClock(3,5,3,5)), true);

		{
			WeekClock weekClock = new WeekClock(4, 17, 15);
			System.out.println(weekClock);
			weekClock.commitJson("{" +
				"	hour: 5," +
				"	minute: 19," +
				"	day: 1" +
				"}");
			System.out.println(weekClock);
			weekClock.addDay();
			weekClock.addHour();
			System.out.println(weekClock);
		}
	}
}
