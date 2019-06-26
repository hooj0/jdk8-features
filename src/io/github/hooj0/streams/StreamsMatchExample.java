package io.github.hooj0.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * streams match example
 * 可以使用各种匹配操作来检查某个谓词是否与流匹配。所有这些操作都是终端操作，并返回布尔结果。
 * @author hoojo
 * @createDate 2019年6月25日 下午5:33:08
 * @file StreamsMatchExample.java
 * @package io.github.hooj0.streams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsMatchExample {

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
		
		boolean flag = list.stream().anyMatch((s) -> s.startsWith("b")); // 判断是否包含b开头的
		System.out.println(flag); // true
		
		flag = list.stream().allMatch((s) -> s.startsWith("b")); // 判断是否全部是b开头的
		System.out.println(flag); // false
		
		flag = list.stream().noneMatch((s) -> s.startsWith("z")); // 判断是否不存在b开头的
		System.out.println(flag); // true
		
	}
}
