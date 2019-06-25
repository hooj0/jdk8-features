package io.github.hooj0.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * streams map filter example
 * 
 * @author hoojo
 * @createDate 2019年6月24日 下午4:43:38
 * @file StreamsMapExample.java
 * @package io.github.hooj0.streams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsMapExample {

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
		
		// 排序、过滤
		list.stream()
			.sorted()
			.filter((s) -> s.startsWith("b"))
			.map(String::toUpperCase)
			.forEach(System.out::println); 
		
		/*
		BBB1
		BBB2
		BBB3
		*/
	}

}
