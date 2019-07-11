package io.github.hooj0.streams;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream example
 * 
 * @author hoojo
 * @createDate 2019年7月12日 上午10:34:46
 * @file StreamsExample.java
 * @package io.github.hooj0.streams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsExample {

	public static void main(String[] args) {

		// list
		Arrays.asList(1, 2, 3).stream().findFirst().ifPresent(System.out::println); // 1
		
		// stream
		Stream.of(1, 2, 3).findFirst().ifPresent(System.out::println); // 1
		
		// IntStream, LongStream and DoubleStream.
		IntStream.range(1, 5).forEach(System.out::print); // 1234
		
	}
}
