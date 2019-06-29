package io.github.hooj0.parallelstreams;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Parallel Streams example
 * 流可以是顺序的，也可以是并行的。顺序流上的操作在单个线程上执行，而并行流上的操作在多个线程上并发执行。
 * 
 * @author hoojo
 * @createDate 2019年6月28日 上午11:10:05
 * @file ParallelStreamsExample.java
 * @package io.github.hooj0.parallelstreams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class ParallelStreamsExample {

	public static void main(String[] args) {
		// 下面的示例演示了使用并行流提高性能是多么容易。

		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
		    UUID uuid = UUID.randomUUID();
		    values.add(uuid.toString());
		}
		
		// Sequential Sort
		long start = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println(count); // 1000000
		
		long stop = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(stop - start);
		System.out.println("sequential sort use time: " + millis); // use time: 1343
		
		// clear resources
		System.gc();
		
		// Parallel Sort
		start = System.nanoTime();
		count = values.parallelStream().sorted().count();
		System.out.println(count); // 1000000
		
		stop = System.nanoTime();
		millis = TimeUnit.NANOSECONDS.toMillis(stop - start);
		
		// 并行排序大约快50%，只需将stream()更改为parallelstream()
		System.out.println("parallel sort use time: " + millis); // 499
	}
}
