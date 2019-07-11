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
		Arrays.asList(1, 2, 3)
				.stream()
				.findFirst()
				.ifPresent(System.out::println); // 1
		
		// stream
		Stream.of(1, 2, 3)
			.findFirst()
			.ifPresent(System.out::println); // 1
		
		// IntStream, LongStream and DoubleStream.
		IntStream.range(1, 5).forEach(System.out::print); // 1234
		
		// 平均值
		Arrays.stream(new int[] { 1, 3, 5 })
				.map(n -> 2 * n + 1)
				.average() // 平均值函数
				.ifPresent(System.out::println); // 12347.0
		
		// 最大值
		Stream.of("a1", "b2", "c3")
			.map(s -> s.substring(1)) // 处理截取数据
			.mapToInt(Integer::parseInt)	// 转换参数
			.max()	// 最大值
			.ifPresent(System.out::println); // 3
		
		// 转对象
		IntStream.range(1, 4)
				.mapToObj(n -> n + "1,") // 转对象
				.forEach(System.out::print); // 11,21,31,
		
		//
	}
}
