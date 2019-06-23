package io.github.hooj0.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * Streams filter example
 * 筛选器接受谓词以筛选流的所有元素
 * 
 * @author hoojo
 * @createDate 2019年6月23日 上午11:42:53
 * @file StreamsFilterExample.java
 * @package io.github.hooj0.built_in_functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsFilterExample {

	public static void main(String[] args) {

		// Java 8中的集合被扩展，因此可以简单地通过调用集合（流）和集合（并行流）来创建流。以下部分将解释最常见的流操作。
		List<String> list = new ArrayList<>();
		list.add("ddd2");
		list.add("aaa2");
		list.add("bbb1");
		list.add("aaa1");
		list.add("bbb3");
		list.add("ccc");
		list.add("bbb2");
		list.add("ddd1");
		
		// 过滤
		list.stream()
			.filter((s) -> s.startsWith("a"))
			.forEach(System.out::println); // aaa2 aaa1
	}
}
