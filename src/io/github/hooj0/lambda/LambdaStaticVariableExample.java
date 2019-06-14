package io.github.hooj0.lambda;

import io.github.hooj0.lambda.LambdaFunctionalInterfaces.Converter;

/**
 * Accessing fields and static variables
 * 访问全局静态变量
 * @author hoojo
 * @createDate 2019年6月13日 下午5:17:11
 * @file LambdaStaticVariableExample.java
 * @package io.github.hooj0.lambda
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LambdaStaticVariableExample {

	static int outerStaticNum;
	int outerNum;
	
	public void testScope() {
		Converter<Integer, String> convter = (from) -> {
			outerNum = 23;
			return String.valueOf(from);
		};
		System.out.println(convter.convert(11));
		
		Converter<Integer, String> convter2 = (from) -> {
			outerStaticNum = 78;
			return String.valueOf(from);
		};
		System.out.println(convter2.convert(22));
	}
	
	public static void main(String[] args) {

		LambdaStaticVariableExample example = new LambdaStaticVariableExample();
		example.testScope();
		
		// 成功访问外部变量并赋值
		System.out.println(example.outerNum); // 23
		System.out.println(outerStaticNum); // 78
	}

}
