package io.github.hooj0.date;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * date localtime example
 * @author hoojo
 * @createDate 2019年7月3日 上午11:33:00
 * @file LocalTimeExample.java
 * @package io.github.hooj0.date
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LocalTimeExample {

	public static void main(String[] args) {

		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		
		LocalTime localTime1 = LocalTime.now(zone1);
		LocalTime localTime2 = LocalTime.now(zone2);
		
		System.out.println(localTime1.isBefore(localTime2)); // false
		
		System.out.println(localTime1.getHour()); // 5
		System.out.println(localTime1.getSecond()); // 51
		System.out.println(localTime1.getMinute());
		System.out.println(localTime1.getNano()); // 673000000
		
		System.out.println(localTime1.toSecondOfDay());
		
		System.out.println(ChronoUnit.HOURS.between(localTime1, localTime2)); // -4 
		System.out.println(ChronoUnit.MINUTES.between(localTime1, localTime2)); // -299
		
		LocalTime time = LocalTime.of(23, 59, 59);
		System.out.println(time); // 23:59:59
		
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
		LocalTime chainTime = LocalTime.parse("15:33", formatter);
		System.out.println(chainTime); // 15:33
	}
}
