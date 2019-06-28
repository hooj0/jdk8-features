package io.github.hooj0.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * stream reduce example
 * 使用给定的函数对流的元素执行缩减。结果是可以选择保留减少的值。
 * 
 * @author hoojo
 * @createDate 2019年6月28日 上午11:01:03
 * @file StreamsReduceExample.java
 * @package io.github.hooj0.streams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsReduceExample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("ddd2");
		list.add("aaa2");
		list.add("bbb1");
		list.add("aaa1");
		list.add("bbb3");
		list.add("ccc");
		list.add("bbb2");
		list.add("ddd1");
		
		Optional<String> reduced = list.stream()
						.sorted()
						.reduce((s1, s2) -> s1 + "-" + s2); // 相当于拼接元素 join 方法
		
		System.out.println(reduced.get()); // aaa1-aaa2-bbb1-bbb2-bbb3-ccc-ddd1-ddd2
	}
}
