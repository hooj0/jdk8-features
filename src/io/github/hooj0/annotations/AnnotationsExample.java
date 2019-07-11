package io.github.hooj0.annotations;

import java.lang.annotation.Repeatable;

/**
 * annotation example
 * 
 * @author hoojo
 * @createDate 2019年7月9日 下午3:05:01
 * @file AnnotationsExample.java
 * @package io.github.hooj0.annotations
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class AnnotationsExample {

	@interface Hints {
		Hint[] value();
	}
	
	// Java 8允许我们通过声明注释@可重复使用同一类型的多个注释。
	@Repeatable(Hints.class)
	@interface Hint {
		String value();
	}
	
	// 老版本中的注解
	@Hints({ @Hint("hint1"), @Hint("hint2") })
	class Person {
	}
	
	// 新版本注解
	@Hint("hint_a")
	@Hint("hint_b")
	class User {
	}
	
	public static void main(String[] args) {

		Hint hint = Person.class.getAnnotation(Hint.class);
		System.out.println(hint); // null
		
		Hints hints = Person.class.getAnnotation(Hints.class);
		System.out.println(hints);
		//System.out.println(hints.value());
		
		Hint[] hints2 = User.class.getAnnotationsByType(Hint.class);
		System.out.println(hints2.length);          // 2
	}
}
