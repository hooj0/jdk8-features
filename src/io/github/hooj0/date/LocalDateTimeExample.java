package io.github.hooj0.date;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * date api local date time example
 * @author hoojo
 * @createDate 2019年7月7日 下午4:39:09
 * @file LocalDateTimeExample.java
 * @package io.github.hooj0.date
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LocalDateTimeExample {

	public static void main(String[] args) {
		LocalDateTime sylvester = LocalDateTime.of(2019, Month.DECEMBER, 31, 23, 59, 59);
		
		DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek); // TUESDAY
		
		Month month = sylvester.getMonth();
		System.out.println(month); // DECEMBER
		
		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay); // 1439
		
		Instant instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		System.out.println(date); // Tue Dec 31 23:59:59 CST 2019
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
		System.out.println(formatter.format(dateTime));
	}
}
