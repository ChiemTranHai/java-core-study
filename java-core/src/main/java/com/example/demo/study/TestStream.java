package com.example.demo.study;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Supplier;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void streamString() {
        List<String> list = List.of("100", "200", "300", "400", "100", "5", "1", "250");
        Supplier<Stream<String>> streamSupplier = () -> list.stream().filter(item -> Integer.parseInt(item) >= 50);
        System.out.println(streamSupplier.get().collect(Collectors.toList()));
        System.out.println(streamSupplier.get().distinct().collect(Collectors.toList()));
        System.out.println(streamSupplier.get().skip(2).limit(3).collect(Collectors.toList()));
        System.out.println(streamSupplier.get().max(Comparator.naturalOrder()).orElse(StringUtils.EMPTY));
        System.out.println(streamSupplier.get().min(Comparator.naturalOrder()).orElse(StringUtils.EMPTY));
        System.out.println(streamSupplier.get().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        System.out.println(streamSupplier.get().anyMatch(item -> Integer.parseInt(item) % 2 == 0));
        System.out.println(streamSupplier.get().noneMatch(item -> Integer.parseInt(item) % 2 == 0));
        System.out.println(streamSupplier.get().allMatch(item -> Integer.parseInt(item) % 2 == 0));
        System.out.println(streamSupplier.get().count());
        System.out.println(streamSupplier.get().map(Integer::parseInt).reduce(0, Integer::sum));
        streamSupplier.get().forEach(System.out::print);


    }

    public static void streamAPI9() {
        System.out.println(Stream.of("one", "two", "three", "three", "four", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .distinct()
                .peek(e -> System.out.println("Distinct value: " + e))
                .collect(Collectors.toList()));

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println(list.stream().takeWhile(x -> x < 4).toList());
        System.out.println(list.stream().dropWhile(x -> x < 4).toList());

        Collections.shuffle(list, new Random());
        System.out.println(list);
        System.out.println(list.stream().takeWhile(x -> x > 4).toList());
        Collections.shuffle(list, new Random());
        System.out.println(list);
        System.out.println(list.stream().dropWhile(x -> x < 4).toList());
        System.out.println(Stream.iterate(0, x -> x < 20, x -> x + 1).toList());
        Map<String, String> map = new HashMap<>(Map.ofEntries(
                Map.entry("key1", "value1"),
                Map.entry("key3", "value3")
        ));
        map.put("key2", null);
        map.put(null, "value4");
        List<List<List<String>>> lists = List.of(Arrays.asList(List.of("a", "b"), List.of("c", "d"), List.of("e", "f")), Arrays.asList(List.of("a", "b"), List.of("c", "d"), List.of("e", "f")));
        System.out.println(lists.stream().flatMap(Collection::stream).flatMap(Collection::stream).toList());
        List<String> listKey = List.of("key1", "key2", "key3");
        Stream<String> stringStream = listKey.stream().flatMap(key -> Stream.ofNullable(map.get(key)));
        System.out.println(stringStream.map(StringUtils::upperCase).toList());
    }
}
