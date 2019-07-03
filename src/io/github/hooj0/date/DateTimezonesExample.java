package io.github.hooj0.date;

import java.time.ZoneId;

/**
 * date api Timezones example
 * @author hoojo
 * @createDate 2019年7月3日 上午10:41:02
 * @file DateTimezonesExample.java
 * @package io.github.hooj0.date
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DateTimezonesExample {

	public static void main(String[] args) {

		System.out.println(ZoneId.systemDefault()); // Asia/Shanghai
		System.out.println(ZoneId.getAvailableZoneIds());
		
		System.out.println(ZoneId.of("Europe/Berlin").getRules()); // ZoneRules[currentStandardOffset=+01:00]
		
	}
}
