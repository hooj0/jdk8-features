package io.github.hooj0.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps Utils example
 * 
 * @author hoojo
 * @createDate 2019年6月29日 上午11:22:27
 * @file MapsExample.java
 * @package io.github.hooj0.maps
 * @project jdk8-features
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class MapsExample {

	public static void main(String[] args) {

		// create Map collection
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			// 如果为空，putIfAbsent 会阻止我们编写额外的if-null检查
		    map.putIfAbsent(i, "val" + i);
		}
		// for each接受一个消费者为映射的每个值执行操作
		map.forEach((id, val) -> System.out.println(val));
		
		// 如果存在就执行remappingFunction，并填充修改后的数据
		map.computeIfPresent(3, (num, val) -> num + val);
		System.out.println(map.get(3)); // 3val3
		
		// 333 不存在，故为 null
		map.computeIfPresent(333, (num, val) -> num + val);
		System.out.println(map.get(333)); // null
		
		// 设为空值，相当于删除
		System.out.println(map.containsKey(9));     // true
		map.computeIfPresent(9, (num, val) -> null);
		System.out.println(map.containsKey(9));     // false
		
		// 如果不存在就执行添加
		map.computeIfAbsent(233, num -> "val" + num);
		System.out.println(map.containsKey(233));    // true
		System.out.println(map.get(233));			// val233

		// 如果存在就用之前的数据
		System.out.println(map.containsKey(233));    // true
		map.computeIfAbsent(233, num -> "666"); 	// 666 没有被添加进去
		System.out.println(map.get(233));			// val233
	}
}