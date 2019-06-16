package io.github.hooj0.built_in_functional;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Built-in Functional Interfaces Predicate Example
 * 内置函数接口
 * @author hoojo
 * @createDate 2019年6月16日 上午11:17:18
 * @file PredicateExample.java
 * @package io.github.hooj0.built_in_functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class PredicateExample {

	public static void main(String[] args) {
		
		Predicate<String> predicate = (str) -> str.length() > 0;
		
		// 判断是否满足
		System.out.println(predicate.test("abc")); // true
		// 取反
		System.out.println(predicate.negate().test("abc"));	// false
		
		Predicate<Boolean> notNull = Objects::nonNull;
		System.out.println(notNull.test(false)); // true
		System.out.println(notNull.test(null));	// false
		
		Predicate<Boolean> isNull = Objects::isNull;
		System.out.println(isNull.test(false)); // false
		System.out.println(isNull.test(null));	// true
		
		Predicate<String> isEmpty = String::isEmpty;
		System.out.println(isEmpty.test(""));	// true
		System.out.println(isEmpty.test("abc")); // false
		
		// 用上面的表达式，取反
		Predicate<String> isNotEmpty = isEmpty.negate();
		System.out.println(isNotEmpty.test(""));	// false
	}
}
