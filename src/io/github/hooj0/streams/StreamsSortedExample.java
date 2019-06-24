package io.github.hooj0.streams;

import java.util.ArrayList;
import java.util.List;

/**
 *  Streams sorted example
 * @author hoojo
 * @createDate 2019年6月23日 下午4:09:17
 * @file StreamsSortedExample.java
 * @package io.github.hooj0.streams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsSortedExample {

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
		
		// 排序
		list.stream()
			.sorted()
			.forEach(System.out::println); 
		/*
		aaa1
		aaa2
		bbb1
		bbb2
		bbb3
		ccc
		ddd1
		ddd2
		*/
		
		System.out.println("-------------");
		// 排序、过滤
		list.stream()
			.sorted()
			.filter((s) -> s.startsWith("b"))
			.forEach(System.out::println); 
		/*
			bbb1
			bbb2
			bbb3
		 */
	}
}
