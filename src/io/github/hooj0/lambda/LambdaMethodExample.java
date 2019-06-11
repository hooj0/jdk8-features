package io.github.hooj0.lambda;

/**
 * lambda method expression example
 * @author hoojo
 * @createDate 2019年6月9日 下午5:17:49
 * @file LambdaMethodExample.java
 * @package io.github.hooj0.lambda
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LambdaMethodExample {

	@FunctionalInterface
	interface Converter<F, T> {
	    T convert(F from);
	}
	
	static class StringUtils {
		String startWith(String str) {
			return String.valueOf(str.charAt(0));
		}
	}
	
	public static void main(String[] args) {

		// lambda 实现 converter接口，由于T convert(F from);只有一个接口参数和返回值，可以直接使用 Lambda 表达式
		Converter<String, Integer> convter = Integer::parseInt;
		Integer num = convter.convert("123");
		System.out.println(num); // 123

		// lambda 调用方法
		StringUtils util = new StringUtils();
		Converter<String, String> convert2 = util::startWith;
		String data = convert2.convert("Java");
		System.out.println(data); // J
	}
}
