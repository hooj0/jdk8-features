package io.github.hooj0.streams.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * stream complex samples
 * 
 * @author hoojo
 * @createDate 2019年8月15日 下午4:01:52
 * @file StreamsComplexSample.java
 * @package io.github.hooj0.streams.samples
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsComplexSample {

	// 将一个对象转换成多个其他对象
	public static void testFlatMap() {
		List<Foo> list = new ArrayList<>();
		
		// create foos
		IntStream.range(1, 4).forEach(i -> list.add(new Foo("foo-" + i)));
		
		// create bars
		list.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar(f.name + "-bar" + i))));
		
		// views
		list.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));
		
		/*
		  	foo-1-bar1
			foo-1-bar2
			foo-1-bar3
			foo-2-bar1
			foo-2-bar2
			foo-2-bar3
			foo-3-bar1
			foo-3-bar2
			foo-3-bar3
		 */
		
		System.out.println("------------简化------------");
		IntStream.range(1, 4)
				.mapToObj(i -> new Foo("foo" + i))
				.peek(f -> IntStream.range(1, 4).mapToObj(i -> new Bar(f.name + "-bar" + i)).forEach(f.bars::add))
				.flatMap(f -> f.bars.stream())
				.forEach(b -> System.out.println(b.name));
	}
	
	// 为了解析外部实例的内部字符串foo，必须添加多个空检查以防止可能的NullPointerExceptions:
	public static void testFlatMapCheckNull() {
		
		// 检查
		Outer outer = new Outer();
		if (outer != null && outer.nested != null && outer.nested.inner != null) {
		    System.out.println(outer.nested.inner.foo);
		}
		
		// 流检查
		Optional.of(new Outer())
				.flatMap(o -> Optional.ofNullable(o.nested))
				.flatMap(n -> Optional.ofNullable(n.inner))
				.flatMap(i -> Optional.ofNullable(i.foo))
				.ifPresent(System.out::println);
	}
	
	
	// 还原操作将流的所有元素组合为一个结果
	public static void testReduce() {
		
		List<Person> persons = Arrays.asList(
				new Person("Max", 18),
		        new Person("Peter", 23),
		        new Person("Pamela", 23),
		        new Person("David", 12));
		
		// 返回年龄最大的人
		persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println);
		
		// reduce方法接受binaryOperator累加器函数
		Person person = persons.stream().reduce(new Person("", 0), (p1, p2) -> {
			p1.age += p2.age;
			p1.name += p2.name;
			return p1;
		});
		System.out.println(person.age); // 76
		
		// sum
		int age = persons.stream().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
		System.out.println(age); // 76
	}
	
	public static void main(String[] args) {
		testFlatMap();
		
		testFlatMapCheckNull();
		
		testReduce();
	}
	
	static class Outer {
	    Nested nested;
	}

	class Nested {
	    Inner inner;
	}

	class Inner {
	    String foo;
	}
	
	
	
	static class Foo {
	    String name;
	    List<Bar> bars = new ArrayList<>();

	    Foo(String name) {
	        this.name = name;
	    }
	}

	static class Bar {
	    String name;

	    Bar(String name) {
	        this.name = name;
	    }
	}
	
	
	static class Person {
	    String name;
	    int age;

	    Person(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }

	    @Override
	    public String toString() {
	        return name;
	    }
	}
}
