package io.github.hooj0.streams.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
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

	private static List<Person> persons = Arrays.asList(
			new Person("Max", 18),
	        new Person("Peter", 23),
	        new Person("Pamela", 23),
	        new Person("David", 12));
	
	
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
		
		Integer ageSum = persons
			    .stream()
			    .reduce(0,
			        (sum, p) -> {
			            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
			            return sum += p.age;
			        },
			        (sum1, sum2) -> {
			            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
			            return sum1 + sum2;
			        });
		System.out.println(ageSum);
		
		// accumulator: sum=0; person=Max
		// accumulator: sum=18; person=Peter
		// accumulator: sum=41; person=Pamela
		// accumulator: sum=64; person=David
		
		ageSum = persons
			    .parallelStream()
			    .reduce(0,
			        (sum, p) -> {
			            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
			            return sum += p.age;
			        },
			        (sum1, sum2) -> {
			            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
			            return sum1 + sum2;
			        });
		System.out.println(ageSum);
		/*
		accumulator: sum=0; person=Pamela
		accumulator: sum=0; person=David
		accumulator: sum=0; person=Max
		accumulator: sum=0; person=Peter
		combiner: sum1=23; sum2=12
		combiner: sum1=18; sum2=23
		combiner: sum1=41; sum2=35
		*/
	}
	
	
	public static void testParallelStreams() {
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		// 底层线程池的大小最多使用五个线程，具体取决于可用物理CPU核心的数量。
		System.out.println(commonPool.getParallelism()); // 7
		
		// 在jvm中配置线程池数量
		// -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
		
		// 并行流使用公共ForkJoinPool中的所有可用线程来执行流操作。
		// 由于实际使用特定线程的行为是不确定的，因此输出可能在连续运行中有所不同。
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
				.parallelStream()
				.filter(s -> {
					System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
					return true;
				})
				.map(s -> {
					System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
					return s.toUpperCase();
				})
				.forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
		
		/*
		filter: a2 [ForkJoinPool.commonPool-worker-2]
		filter: c1 [ForkJoinPool.commonPool-worker-3]
		map: c1 [ForkJoinPool.commonPool-worker-3]
		filter: c2 [ForkJoinPool.commonPool-worker-4]
		map: c2 [ForkJoinPool.commonPool-worker-4]
		forEach: C2 [ForkJoinPool.commonPool-worker-4]
		filter: a1 [ForkJoinPool.commonPool-worker-1]
		filter: b1 [main]
		map: a1 [ForkJoinPool.commonPool-worker-1]
		forEach: C1 [ForkJoinPool.commonPool-worker-3]
		map: a2 [ForkJoinPool.commonPool-worker-2]
		forEach: A1 [ForkJoinPool.commonPool-worker-1]
		map: b1 [main]
		forEach: B1 [main]
		forEach: A2 [ForkJoinPool.commonPool-worker-2]
		*/
		
		System.out.println("-----------------------------------");
		
		// 似乎排序只在主线程上按顺序执行。实际上，并行流上的排序使用引擎盖下的新Java 8方法数组.RealSoReSt*()。
		// 如JavaDoc中所述，如果排序将按顺序或并行方式执行，则此方法将决定数组的长度：
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
		.parallelStream()
		.filter(s -> {
			System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		})
		.map(s -> {
			System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		})
		.sorted((s1, s2) -> {
	        System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
	        return s1.compareTo(s2);
	    })
		.forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
		
		/*
		filter: b1 [main]
		filter: a1 [ForkJoinPool.commonPool-worker-2]
		map: a1 [ForkJoinPool.commonPool-worker-2]
		filter: c1 [ForkJoinPool.commonPool-worker-1]
		filter: c2 [ForkJoinPool.commonPool-worker-4]
		filter: a2 [ForkJoinPool.commonPool-worker-3]
		map: a2 [ForkJoinPool.commonPool-worker-3]
		map: c2 [ForkJoinPool.commonPool-worker-4]
		map: c1 [ForkJoinPool.commonPool-worker-1]
		map: b1 [main]
		sort: A2 <> A1 [main]
		sort: B1 <> A2 [main]
		sort: C2 <> B1 [main]
		sort: C1 <> C2 [main]
		sort: C1 <> B1 [main]
		sort: C1 <> C2 [main]
		forEach: B1 [main]
		forEach: C1 [ForkJoinPool.commonPool-worker-4]
		forEach: A1 [ForkJoinPool.commonPool-worker-2]
		forEach: C2 [ForkJoinPool.commonPool-worker-1]
		forEach: A2 [ForkJoinPool.commonPool-worker-5]
		*/
						
		System.out.println("------------------------------------");
		
		persons.parallelStream()
	    .reduce(0,
	        (sum, p) -> {
	        	System.out.format("accumulator: sum=%s; person=%s [%s]\n", sum, p, Thread.currentThread().getName());
	            return sum += p.age;
	        },
	        (sum1, sum2) -> {
	        	System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2, Thread.currentThread().getName());
	            return sum1 + sum2;
	        });
		
		/*
		控制台输出显示累加器和合并器功能在所有可用线程上并行执行：
		一些并行流操作（如reduce和collect）需要额外的计算（合并操作），这在按顺序执行时是不需要的。
		
		accumulator: sum=0; person=Pamela [main]
		accumulator: sum=0; person=Max [ForkJoinPool.commonPool-worker-5]
		accumulator: sum=0; person=Peter [ForkJoinPool.commonPool-worker-1]
		accumulator: sum=0; person=David [ForkJoinPool.commonPool-worker-3]
		combiner: sum1=18; sum2=23 [ForkJoinPool.commonPool-worker-1]
		combiner: sum1=23; sum2=12 [ForkJoinPool.commonPool-worker-3]
		combiner: sum1=41; sum2=35 [ForkJoinPool.commonPool-worker-3]
		*/
	}
	
	public static void main(String[] args) {
		testFlatMap();
		
		testFlatMapCheckNull();
		
		testReduce();
		
		testParallelStreams();
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
