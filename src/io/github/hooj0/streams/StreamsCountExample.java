package io.github.hooj0.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * streams count example
 * Count是一个终端操作，返回流中元素的长度。
 * 
 * @author hoojo
 * @createDate 2019年6月26日 下午5:54:33
 * @file StreamsCountExample.java
 * @package io.github.hooj0.streams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsCountExample {

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
		
		long count = list.stream().filter((s) -> s.startsWith("b")).count(); // 获取数量/长度
		System.out.println(count); // 3
		
		count = list.stream().filter((s) -> s.startsWith("c")).count(); // 获取数量/长度
		System.out.println(count); // 1
	}
}
