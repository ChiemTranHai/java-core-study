package com.example.demo.study;

import com.example.demo.CustomUncheckedException;
import com.example.demo.DemoApplication;
import com.example.demo.TestAutoCloseClass;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestException {
    private final static Logger LOGGER = Logger.getLogger(TestException.class.getName());

    public static void highLevel(List<String> list) {
        list.add("z");
        middleLevel(list);
        System.out.println("high level");
    }

    public static void middleLevel(List<String> list) {
        System.out.println("middle level");
        String str = list.stream().filter("c"::equals).findFirst().orElse(null);
        lowLevel(str);
    }

    public static void lowLevel(String str) {
        try {
            String tmp = str.toUpperCase();
            System.out.println("low level " + tmp);
        } catch (Exception ex) {
            throw new CustomUncheckedException("something error", ex);
        }
    }

    public static void exception() {
        try {
            List<String> a = new ArrayList<>();
            try {
                highLevel(a);
            } catch (NullPointerException e) {
                LOGGER.log(Level.WARNING, "null ne ", e);
            } finally {
                System.out.println("close");
            }
            System.out.println("continue ne");
            String tmp = null;
            System.out.println(tmp.toLowerCase());
        } catch (CustomUncheckedException exception) {
            System.out.println(exception);
            exception.printStackTrace();
            LOGGER.log(Level.WARNING, "warning ne {0}.Exception: {1}", new Object[]{"test", exception});
            System.out.println("-----------------------------------------------------------------------");
            LOGGER.log(Level.WARNING, "warning ne ", exception);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "error ne ", exception);
        } finally {
            System.out.println("finally ne");
        }

        // try-catch-finally
        TestAutoCloseClass cached1 = new TestAutoCloseClass();
        try {
            cached1.add("test1", "value1");
            System.out.println(cached1.get("te").toLowerCase());
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            System.out.println("after");
//            System.out.println(Arrays.toString(exception.getSuppressed()));
//            LOGGER.log(Level.WARNING,"something error",exception);
        } finally {
            System.out.println("before");
            cached1.close();
        }

        System.out.println("---------------------------------------------------");

        // try-with-resource
        try (TestAutoCloseClass cached = new TestAutoCloseClass()) {
            cached.add("test1", "value1");
            System.out.println(cached.get("te").toLowerCase());
        } catch (NullPointerException exception) {
            LOGGER.log(Level.WARNING, "something error", exception);
        }
    }
}
