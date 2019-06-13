package io.github.hooj0.lambda;

import io.github.hooj0.lambda.LambdaFunctionalInterfaces.Converter;

/**
 * Accessing local variables
 * 访问本地变量
 * 
 * @author hoojo
 * @createDate 2019年7月30日 下午5:06:15
 * @file LambdaLocalVariableExample.java
 * @package io.github.hooj0.lambda
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LambdaLocalVariableExample {

	public static void main(String[] args) {
		
		// 访问局部最终变量
		final int num = 1;
		Converter<Integer, String> stringConverter =(from) -> String.valueOf(from + num);

		System.out.println(stringConverter.convert(2));     // 3
		
		
		// 访问局部变量
		int num2 = 1;
		Converter<Integer, String> stringConverter2 = (from) -> String.valueOf(from + num2);

		System.out.println(stringConverter2.convert(2));     // 3
		
		
		int num3 = 1;
		Converter<Integer, String> stringConverter3 = (from) -> String.valueOf(from + num3);
		// 由于 num 被重新赋值，不是隐形的最终变量，故编译失败；
		// 在 Lambda表达式内部也禁止赋值写入操作
		//num3 = 3;
	}
}
