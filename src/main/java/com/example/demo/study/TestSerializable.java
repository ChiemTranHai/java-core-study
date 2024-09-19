package com.example.demo.study;

import com.example.demo.SimpleClass;

import java.io.*;

public class TestSerializable {
    public static void serializeAndDeserialize() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test.txt"))) {
            SimpleClass simpleClass = new SimpleClass("test 1", "test 2");
            objectOutputStream.writeObject(simpleClass);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("test.txt"))) {
            SimpleClass simpleClass = (SimpleClass) objectInputStream.readObject();
            System.out.println(simpleClass);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
