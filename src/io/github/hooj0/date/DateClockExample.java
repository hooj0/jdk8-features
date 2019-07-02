package io.github.hooj0.date;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * date api clock example
 * 
 * @author hoojo
 * @createDate 2019年7月2日 上午9:45:36
 * @file DateClockExample.java
 * @package io.github.hooj0.date
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DateClockExample {

	public static void main(String[] args) {

		Clock clock = Clock.systemDefaultZone();
		long m = clock.millis();
		System.out.println(m); // 1562032987192
		System.out.println(clock.getZone()); // Asia/Shanghai
		System.out.println(Clock.systemUTC());
		
		Instant instant = clock.instant();
		Date date = Date.from(instant);
		System.out.println(date); // Tue Jul 02 10:03:07 CST 2019
		
		System.out.println(instant.getEpochSecond()); // 1562035187
		System.out.println(instant.getNano()); // 101000000
		System.out.println(instant.toEpochMilli()); // 1562035187101
		System.out.println(Instant.now());	// 2019-07-02T02:39:47.110Z
	}
}
