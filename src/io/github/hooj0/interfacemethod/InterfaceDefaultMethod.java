package io.github.hooj0.interfacemethod;

/**
 * Java Interface default method support
 * Java 8使我们能够通过利用default关键字向接口添加非抽象方法实现。此功能也称为虚拟扩展方法。
 * @author hoojo
 * @createDate 2019年7月16日 上午10:48:03
 * @file InterfaceDefaultMethod.java
 * @package io.github.hooj0.interfacemethod
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class InterfaceDefaultMethod {

	interface Formula {
		double calc(int foo);
		
		// 提供默认的实现
		default double sqrt(int bar) {
			
			return Math.sqrt(bar);
		}
	}
	
	public static void main(String[] args) {

		Formula formula = new Formula() {
			
			@Override
			public double calc(int foo) {
				
				// 可以直接使用默认的sqrt方法，不需要实现
				return this.sqrt(foo * 100);
			}
		};
		
		System.out.println(formula.calc(100)); 	// 100
		System.out.println(formula.sqrt(16));	// 4.0
	}

}
