package io.github.hooj0.built_in_functional;

import java.util.function.Supplier;

import io.github.hooj0.lambda.LambdaConstructorsExample.User;

/**
 * Built-in Functional Interfaces Suppliers Example
 * 供应商生成给定泛型类型的结果。与函数不同，供应商不接受参数。
 * @author hoojo
 * @createDate 2019年6月18日 下午4:58:22
 * @file SuppliersExample.java
 * @package io.github.hooj0.built_in_functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class SuppliersExample {
	
	public static void main(String[] args) {

		// 生成指定泛型类型的对象，但不接受参数
		Supplier<Double> supplier = Math::random;
		System.out.println(supplier.get());
		
		Supplier<User> supplier2 = User::new;
		System.out.println(supplier2.get().age);
	}
}
