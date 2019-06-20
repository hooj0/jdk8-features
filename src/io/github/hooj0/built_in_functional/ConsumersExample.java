package io.github.hooj0.built_in_functional;

import java.util.function.Consumer;

import io.github.hooj0.lambda.LambdaConstructorsExample.User;

/**
 * Built-in Functional Interfaces Consumers Example
 * 使用者表示要在单个输入参数上执行的操作。
 * @author hoojo
 * @createDate 2019年6月20日 下午5:11:15
 * @file ConsumersExample.java
 * @package io.github.hooj0.built_in_functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class ConsumersExample {

	public static void main(String[] args) {
		
		// 在参数输入后执行此业务
		Consumer<User> consumer = (u) -> System.out.println("name -> " + u.name);
		// 输入参数
		consumer.accept(new User("joe", 2456));
	}
}
