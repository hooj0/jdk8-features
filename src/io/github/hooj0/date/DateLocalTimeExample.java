package io.github.hooj0.date;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * date localtime example
 * @author hoojo
 * @createDate 2019年7月3日 上午11:33:00
 * @file DateLocalTimeExample.java
 * @package io.github.hooj0.date
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DateLocalTimeExample {

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
	}
}
