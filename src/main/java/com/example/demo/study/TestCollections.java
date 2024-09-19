package com.example.demo.study;

import com.example.demo.Student;
import com.example.demo.TestClass;

import java.util.*;

public class TestCollections {
    public static void list() {
        List<Integer> arr1 = new LinkedList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> arr2 = new LinkedList<>(List.of(1, 3, 5, 6, 7, 8));
        List<Integer> insertNew = new ArrayList<>(arr1);
        List<Integer> deleteOld = new ArrayList<>(arr2);
        insertNew.removeAll(arr2);
        deleteOld.removeAll(arr1);
        System.out.println(insertNew);
        System.out.println(arr1 + "------" + arr2);
        System.out.println(deleteOld);
        arr1.retainAll(arr2);
        System.out.println(arr1 + "------" + arr2);
        System.out.println(arr1.removeIf(filter -> filter == 3));
        System.out.println(arr1);
    }

    public static void map() {
        Map<String, String> map = new HashMap<>();
        map.put(null, null);
        map.put("key1", "value2");
        map.put("key2", null);
        map.put("key3", null);
        Set<String> keySets = map.keySet();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (String key : keySets) {
            System.out.println(key + " = " + map.get(key));
        }
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println(map.containsKey("key1"));
        System.out.println(map.containsValue("value2"));
        map.compute("key1", (k, v) -> v.concat(" test"));
        map.compute("key6", (k, v) -> " test6");
        map.computeIfPresent("key4", (k, v) -> v.concat("test4"));
        map.computeIfAbsent("key4", k -> k.concat("test4"));
        map.compute("key1", (k, v) -> null);
        map.computeIfPresent("key4", (k, v) -> "test5");
        map.computeIfAbsent("key4", k -> k.concat("test4"));
        map.putIfAbsent("key3", "test3");
        System.out.println(map);
        Set<String> values = new HashSet<>(map.values());
        System.out.println(values);
        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }

    public static void set() {
        TestClass testClass1 = new TestClass("testclass1");
        TestClass testClass2 = new TestClass("testclass2");
        TestClass testClass3 = new TestClass("testclass3");
        Comparator<TestClass> comparator = Comparator.comparing(TestClass::getVar1);
        Set<TestClass> classSet = Set.of(testClass1, testClass2, testClass3);
        System.out.println(classSet);
        SortedSet<TestClass> set = new TreeSet<>(comparator);
        set.addAll(classSet);
        System.out.println(set.first() + "---" + set.last());
//        Iterator<TestClass> iterator = set.iterator();
//        if (iterator.hasNext()) {
//            iterator.next();
//            iterator.remove();
//
//        }
        System.out.println(set);

        List<TestClass> arrayList = new ArrayList<>(List.of(testClass1, testClass2, testClass3));
        Iterator<TestClass> iterator1 = arrayList.iterator();
        if (iterator1.hasNext()) {
            iterator1.next();
            iterator1.remove();


        }
        System.out.println(arrayList);
    }

    public static void navigableSet() {
        Student student1 = new Student("student1", 25);
        Student student2 = new Student("student2", 26);
        Student student3 = new Student("student3", 27);
        Student student4 = new Student("student4", 20);
        Student student5 = new Student("student5", 29);
        Student student6 = new Student("student6", 19);
        Student student7 = new Student("student7", 21);
        NavigableSet<Student> navigableSet = new TreeSet<>(Comparator.comparing(Student::getAge, Comparator.nullsFirst(Comparator.naturalOrder())));
        navigableSet.addAll(Arrays.asList(student1, student2, student3, student4, student5, student6));
        System.out.println(navigableSet);
        System.out.println(navigableSet.floor(new Student("student", 20)));
        System.out.println(navigableSet.lower(new Student("student", 21)));
        System.out.println(navigableSet.ceiling(new Student("student", 25)));
        System.out.println(navigableSet.higher(new Student("student", 21)));
        navigableSet.add(student7);
        System.out.println(navigableSet.headSet(new Student("student", 21), true));
        System.out.println(navigableSet.headSet(new Student("student", 21), false));
        System.out.println(navigableSet.headSet(new Student("student", 21)));
        System.out.println(navigableSet.tailSet(new Student("student", 21), true));
        System.out.println(navigableSet.tailSet(new Student("student", 21), false));
        System.out.println(navigableSet.tailSet(new Student("student", 21)));
        System.out.println(navigableSet.pollFirst());
        System.out.println(navigableSet.pollLast());
        System.out.println(navigableSet.first());
        System.out.println(navigableSet.last());
        System.out.println(navigableSet);
    }

    public static void queue() {
        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");
        queue.add("f");
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.element());
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.remove("c"));
        System.out.println(queue.removeIf("e"::equals));
        System.out.println(queue);
        System.out.println(queue.offer("z"));
        System.out.println(queue.add("y"));
        System.out.println(queue);
    }

    public static void arrayDeque() {
        Deque<String> deque = new ArrayDeque<>();
        deque.add("f");
        deque.add("c");
        deque.add("a");
        deque.add("b");
        deque.add("d");
        deque.add("e");
        System.out.println(deque);
        System.out.println(deque.removeFirstOccurrence("c"));
        System.out.println(deque.pollFirst());
        System.out.println(deque.peekFirst());
        System.out.println(deque.getFirst());
        System.out.println(deque.offerLast("z"));
        System.out.println(deque);
        Deque<String> stack = new ArrayDeque<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.offerFirst("z"));
        System.out.println(stack);
    }

    public static void collections() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "z", "g", "b", "o", "k"));
        Collections.sort(list);
        System.out.println(Collections.binarySearch(list, "z"));
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
        System.out.println(Collections.indexOfSubList(list, List.of("b", "g")));
        list.sort(Collections.reverseOrder());
        System.out.println(list);
        List<String> clone = new ArrayList<>(Collections.nCopies(list.size(), null));
        System.out.println(clone.size());
        Collections.copy(clone, list);
        System.out.println(list);
        System.out.println(clone);
        clone.sort(Collections.reverseOrder());
        System.out.println(list);
        System.out.println(clone);

        Map<String, Boolean> mapWeak = new WeakHashMap<>();
        Map<String, Boolean> map = Map.ofEntries(
                Map.entry("key1", Boolean.FALSE),
                Map.entry("key2", Boolean.TRUE),
                Map.entry("key3", Boolean.FALSE)
        );

        Set<String> set = Collections.newSetFromMap(mapWeak);
        mapWeak.putAll(map);
        System.out.println(mapWeak.keySet());
        System.out.println(set);
    }

    public static void listIterator() {
        List<String> list = new ArrayList<>(List.of("a", "b", "d", "z"));
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if ("b".equals(listIterator.next())) {
                listIterator.set("test");
                listIterator.add("c");
                System.out.println(listIterator.nextIndex());
            }
        }
        System.out.println(list);
    }
}
