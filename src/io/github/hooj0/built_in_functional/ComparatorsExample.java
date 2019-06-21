package io.github.hooj0.built_in_functional;

import java.util.Comparator;

import io.github.hooj0.lambda.LambdaConstructorsExample.User;

/**
 * Built-in Functional Interfaces Comparators Example
 * 比较器从旧版本的Java中是众所周知的，Java 8向接口添加了各种默认方法。
 * 
 * @author hoojo
 * @createDate 2019年6月21日 下午5:19:36
 * @file ComparatorsExample.java
 * @package io.github.hooj0.built_in_functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class ComparatorsExample {

	public static void main(String[] args) {

		Comparator<User> comparator = (u1, u2) -> u1.name.compareTo(u2.name);
		
		/*Comparator<User> comparator = (u1, u2) -> {
			if (u1.age > u2.age) return -1;
			else return 1;
		};*/
		
		User u1 = new User("joe", 218);
		User u2 = new User("tom", 123);
		
		System.out.println(comparator.compare(u1, u2)); // < 0
		System.out.println(comparator.reversed().compare(u1, u2)); // > 0
	}
}
