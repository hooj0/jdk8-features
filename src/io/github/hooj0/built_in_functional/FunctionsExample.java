package io.github.hooj0.built_in_functional;

import java.util.function.Function;

/**
 * Built-in Functional Interfaces Functions Example
 * 函数接受一个参数并产生一个结果。默认方法可用于将多个函数链接在一起（compose和then）。
 * @author hoojo
 * @createDate 2019年6月17日 上午11:33:16
 * @file FunctionsExample.java
 * @package io.github.hooj0.built_in_functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class FunctionsExample {

	public static void main(String[] args) {

		Function<String, Integer> convter = Integer::valueOf;
		System.out.println(convter.apply("123") + 2); // 125
		
		// andThen 在 convter 的函数执行后再执行
		Function<String, String> rollback = convter.andThen(String::valueOf);
		System.out.println(rollback.apply("123")); // 123
		
		// compose 在apply方法之前完成一些业务处理
		System.out.println(convter.compose((v) -> {
			return v.toString() + "2";
		}).apply(223));
	}
}
