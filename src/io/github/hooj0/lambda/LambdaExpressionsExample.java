package io.github.hooj0.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lambda expressions
 * @author hoojo
 * @createDate 2019年6月5日 上午11:18:58
 * @file LambdaExpressionsExample.java
 * @package io.github.hooj0.lambda
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LambdaExpressionsExample {

	public static void main(String[] args) {

		List<String> strings = Arrays.asList("java", "spring", "hibernate", "mybatis");
		
		Collections.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		// lambda 1：
		Collections.sort(strings, (String o1, String o2) -> {
			return o1.compareTo(o2);
		});
		
		// lambda 2：
		Collections.sort(strings, (o1, o2) -> {
			return o1.compareTo(o2);
		});
		
		// lambda 3:
		Collections.sort(strings, (a, b) -> b.compareTo(a));
		
		// 可以去掉大括号和return关键字
		strings.sort((a, b) -> b.compareTo(a));
	}
}
