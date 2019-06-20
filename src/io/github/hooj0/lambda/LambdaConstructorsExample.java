package io.github.hooj0.lambda;

/**
 * lambda constructor expressions example
 * 
 * @author hoojo
 * @createDate 2019年6月12日 下午5:50:32
 * @file LambdaConstructorsExample.java
 * @package io.github.hooj0.lambda
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class LambdaConstructorsExample {

	public static class User {
		public String name;
		public int age;
		
		public User() {}
		
		public User(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}
	
	interface UserFactory<U extends User> {
	    U create(String name, int age);
	}
	
	public static void main(String[] args) {

		// 利用Lambda表达式 new 直接实现UserFactory接口的create方法
		UserFactory<User> factory = User::new;
		User user = factory.create("joe", 29);
		
		System.out.println(user.name); 	// joe
		System.out.println(user.age);	// 29
	}
}
