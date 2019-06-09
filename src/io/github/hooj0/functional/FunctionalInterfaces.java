package io.github.hooj0.functional;

/**
 * 函数式接口编程
 * @author hoojo
 * @createDate 2019年6月8日 上午10:52:52
 * @file FunctionalInterfaces.java
 * @package io.github.hooj0.functional
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class FunctionalInterfaces {

	// @FunctionalInterface 省略也是有效的
	@FunctionalInterface
	interface Converter<F, T> {
		T convert(F from);
	}
	
	public static void main(String[] args) {

		// lambda 实现 converter接口
		Converter<String, Integer> convter = (from) -> Integer.parseInt(from);
		Integer num = convter.convert("123");
		System.out.println(num);
	}

}
