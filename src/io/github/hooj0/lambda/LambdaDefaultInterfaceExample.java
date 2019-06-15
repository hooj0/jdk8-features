package io.github.hooj0.lambda;

/**
 * <b>function:</b>
 * @author hoojo
 * @createDate 2019年6月15日 上午10:38:52
 * @file LambdaDefaultInterfaceExample.java
 * @package io.github.hooj0.lambda
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LambdaDefaultInterfaceExample {

	static int num;
	
	interface Formula {
		double calc(int foo);
		
		// 提供默认的实现
		default double sqrt(int bar) {
			
			num = 11;
			return Math.sqrt(bar);
		}
	}
	
	public static void main(String[] args) {

		Formula formula = (a) -> Math.sqrt(a * 100);

		System.out.println(formula.sqrt(16));	// 4.0
		System.out.println(num);	// 11
	}
}
