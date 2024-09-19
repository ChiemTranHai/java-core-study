package com.example.demo.study;

import com.example.demo.TestClass;
import org.openjdk.jol.vm.VM;

public class TestAddressMemory {
    public static long addr(Object obj) {
        return VM.current().addressOf(obj);
    }
    public static void testAddressMemory() {
        String a = "a";
        String b = "a";
        String c = new String("a");
        String d = new String("a");
        System.out.println(VM.current().addressOf(a));
        System.out.println(VM.current().addressOf(b));
        System.out.println(VM.current().addressOf(c));
        System.out.println(VM.current().addressOf(d));

        String test = new String("abc");
        String test1 = new String("abc");
        System.out.println(VM.current().addressOf(test));
        System.out.println(VM.current().addressOf(test.intern()));
        System.out.println(VM.current().addressOf(test1));
        System.out.println(VM.current().addressOf(test1.intern()));
        TestClass testClass = new TestClass();
        TestClass testClass1 = new TestClass();
        System.out.println(addr(testClass));
        System.out.println(addr(testClass1));
        System.out.println(addr(testClass.var1));
        System.out.println(addr(testClass.var2));
        System.out.println(addr(testClass.var3));
        System.out.println(addr(testClass1.var1));
        System.out.println(addr(testClass1.var2));
        System.out.println(addr(testClass1.var3));
    }
}
