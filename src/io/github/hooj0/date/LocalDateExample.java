package io.github.hooj0.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * date api local date example
 * @author hoojo
 * @createDate 2019年7月5日 下午2:41:05
 * @file LocalDateExample.java
 * @package io.github.hooj0.date
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LocalDateExample {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		
		System.out.println(today);	// 2019-07-05
		System.out.println(tomorrow); // 2019-07-06
		System.out.println(yesterday); // 2019-07-04
		
		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek week = independenceDay.getDayOfWeek();
		
		System.out.println(independenceDay); // 2014-07-04
		System.out.println(week); // FRIDAY
		
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
		LocalDate date = LocalDate.parse("24.12.2014", formatter);
		System.out.println(date); // 2014-12-24
	}
}
