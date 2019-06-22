package io.github.hooj0.built_in_functional;

import java.util.Optional;

/**
 * Built-in Functional Interfaces Optionals Example
 * 防止NullPointerException的漂亮实用程序。
 * 可选是值的简单容器，该值可以为空或非空。
 * 想想一个方法，它可能返回一个非空的结果，但有时什么也不返回。
 * 而不是返回NULL，在Java 8中返回一个可选的。
 * @author hoojo
 * @createDate 2019年6月22日 上午11:02:47
 * @file OptionalsExample.java
 * @package io.github.hooj0.built_in_functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class OptionalsExample {

	public static void main(String[] args) {

		Optional<Integer> optional = Optional.of(2);
		System.out.println(optional.isPresent()); // true
		System.out.println(optional.get());	// 2
		System.out.println(optional.orElse(2333));	// 2
		
		optional = Optional.ofNullable(999);
		System.out.println(optional.isPresent()); // true
		System.out.println(optional.get()); // 999
		System.out.println(optional.orElse(2333)); // 999
		
		optional = Optional.empty();
		System.out.println(optional.isPresent()); // false
		//System.out.println(optional.get()); // java.util.NoSuchElementException: No value present
		System.out.println(optional.orElse(2333)); // 2333
	}
}
