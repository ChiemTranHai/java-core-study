package com.example.demo.study;

import com.example.demo.NumberComparator;
import com.example.demo.SimpleClass;
import com.example.demo.Student;

import java.util.Comparator;

public class TestComparator {
    public static void comparator() {
        SimpleClass class1 = new SimpleClass();
        SimpleClass class2 = new SimpleClass();
        class1.setNumber(40);
        class2.setNumber(30);
        System.out.println(class1.compareTo(class2));
        NumberComparator numberComparator = new NumberComparator();
        System.out.println(numberComparator.compare(class1.getNumber(), class2.getNumber()));
        Comparator<SimpleClass> numberComparator1 = Comparator.comparing(SimpleClass::getNumber);
        System.out.println(numberComparator1.compare(class1, class2));
        Student student1 = new Student(null, 1500L, "jack", null);
        Student student2 = new Student(28, 1500L, "JACK", null);
        Comparator<Student> ageComparator = Comparator.comparing(Student::getAge, Comparator.nullsFirst(Comparator.naturalOrder()));
        Comparator<Student> chainComparator = Comparator
                .comparing(Student::getLastname, String::compareToIgnoreCase)
                .thenComparing(Student::getSalary, Comparator.nullsFirst(Comparator.naturalOrder()))
                .thenComparing(Student::getExperience, Comparator.nullsFirst(Comparator.naturalOrder()))
                .thenComparing(Student::getAge, Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(ageComparator.compare(student1, student2));
        System.out.println(chainComparator.compare(student1, student2));
    }
}
