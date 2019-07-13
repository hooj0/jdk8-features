package io.github.hooj0.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream example
 * 
 * @author hoojo
 * @createDate 2019年7月12日 上午10:34:46
 * @file StreamsExample.java
 * @package io.github.hooj0.streams
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class StreamsExample {

	public static void main(String[] args) {

		// 操作方式
		// ----------------------------------------------------------
		// list
		Arrays.asList(1, 2, 3)
				.stream()
				.findFirst()
				.ifPresent(System.out::println); // 1
		
		// stream
		Stream.of(1, 2, 3)
			.findFirst()
			.ifPresent(System.out::println); // 1
		
		
		// 不同种类的流
		// ----------------------------------------------------------
		// IntStream, LongStream and DoubleStream.
		IntStream.range(1, 5).forEach(System.out::print); // 1234
		
		// 平均值
		Arrays.stream(new int[] { 1, 3, 5 })
				.map(n -> 2 * n + 1)
				.average() // 平均值函数
				.ifPresent(System.out::println); // 12347.0
		
		// 最大值
		Stream.of("a1", "b2", "c3")
			.map(s -> s.substring(1)) // 处理截取数据
			.mapToInt(Integer::parseInt)	// 转换参数
			.max()	// 最大值
			.ifPresent(System.out::println); // 3
		
		// 转对象
		IntStream.range(1, 4)
				.mapToObj(n -> n + "1,") // 转对象
				.forEach(System.out::print); // 11,21,31,
		
		// stream
		Stream.of(1.0d, 2.0d, 3.0d)
			.mapToInt(Double::intValue) // 转int
			.mapToObj(n -> "/a" + n) // 转对象
			.forEach(System.out::print); // /a1/a2/a3
		
		
		// 处理数据
		// ----------------------------------------------------------
		Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> {
					System.out.println("filter: " + s);
					return true;
				}).forEach(System.out::println);
		
		Stream.of("d2", "a2", "b1", "b3", "c")
			.filter(s -> {
				return s.length() < 2;
			}).forEach(System.out::println); // c
		
		
		Stream.of("d2", "a2", "b1", "b3", "c")
			.map(s -> {
				return s.toUpperCase();
			}).anyMatch(s -> {
				return s.startsWith("B");
			});
		
		
		Stream.of("d2", "a2", "b1", "b3", "c")
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("a");
		    })
		    .sorted((s1, s2) -> {
		        System.out.printf("sort: %s; %s\n", s1, s2);
		        return s1.compareTo(s2);
		    })
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
		
		
		Stream<String> stream = Stream.of("d2", "a2", "b1", "b3").filter(s -> s.startsWith("b"));
		
		System.out.println(stream.anyMatch(s -> true)); // true
		// System.out.println(stream.noneMatch(s -> true)); // Exception, 为了避免异常使用下面的方法
		
		Supplier<Stream<String>> supplier = () -> Stream.of("d2", "a2", "b1", "b3").filter(s -> s.startsWith("b"));
		System.out.println(supplier.get().anyMatch(s -> true)); // true
		System.out.println(supplier.get().noneMatch(s -> true)); // false
		
		// 高级操作
		// ----------------------------------------------------------
		class Person {
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

		
		List<Person> persons = Arrays.asList(
			new Person("Max", 18),
	        new Person("Peter", 23),
	        new Person("Pamela", 23),
	        new Person("David", 12));
		
		// 过滤一部分组合成新的集合
		List<Person> filtered = persons.stream()
				.filter(p -> p.name.startsWith("P"))
				.collect(Collectors.toList()); // Collectors.toMap / toSet
		System.out.println(filtered); // [Peter, Pamela]
		
		// 根据年龄进行分组
		Map<Integer, List<Person>> map = persons.stream().collect(Collectors.groupingBy(p -> p.age));
		System.out.println(map); // {18=[Max], 23=[Peter, Pamela], 12=[David]}
		
		// 求平均年龄
		Double averageAge = persons.stream().collect(Collectors.averagingDouble(p -> p.age));
		System.out.println(averageAge); // 19.0
		
		// 更多统计  min, max, average, sum and count.
		IntSummaryStatistics statistics = persons.stream().collect(Collectors.summarizingInt(p -> p.age));
		System.out.println(statistics); // IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
		
		// 拼接字符串
		String names = persons.stream().filter(p -> p.age > 18).map(p -> p.name).collect(Collectors.joining(" 和 ", "人员 ", " 符合年龄要求."));
		System.out.println(names); // 人员 Peter 和 Pamela 符合年龄要求.
		
		// 转换map，不唯一的key将引发 IllegalStateException: Duplicate key Peter 
		// Map<Integer, String> personMap = persons.stream().collect(Collectors.toMap(p -> p.age, p -> p.name));
		Map<Integer, String> personMap = persons.stream().collect(Collectors.toMap(p -> p.age, p -> p.name, (v1, v2) -> v1 + "/" + v2));
		System.out.println(personMap); // {18=Max, 23=Peter/Pamela, 12=David}
		
		// 构建自定义 Collector
		Collector<Person, StringJoiner, String> collector = Collector.of(
				() -> new StringJoiner(" | "), // supplier
				(j, p) -> j.add(p.name.toUpperCase()), // accumulator 
				(j1, j2) -> j1.merge(j2), // combiner
				StringJoiner::toString // characteristics
				);
		
		names = persons.stream().collect(collector);
		System.out.println(names); // MAX | PETER | PAMELA | DAVID
		
		
	}
}
