package com.example.demo.study;

import com.example.demo.ExampleGroup;
import com.example.demo.ExampleObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCloning {
    public static void testCloning() throws CloneNotSupportedException {
        ExampleGroup group = new ExampleGroup(1, "group");
        int[] arr = new int[]{1, 2};
        List<String> listStr = new ArrayList<>(List.of("test 1", "test 2"));
        ExampleObject object1 = new ExampleObject(1, "obj1", group.clone(), arr);
        ExampleObject object2 = new ExampleObject(2, "obj2", group.clone());
        object1.setListStr(listStr);
        ExampleObject object3 = object1.clone();
        object1.getGroup().setGroupName("group change 1");
        object2.getGroup().setGroupName("group change 2");
        int[] tmp = object1.getArr();
        tmp[0] = 10;
        object1.setArr(tmp);
        List<String> listStrObj1 = object1.getListStr();
        listStrObj1.add("test object 1");
        object1.setListStr(listStrObj1);
        List<String> listStr2 = listStr;
        listStr2.add("test 3");
        object3.setId(3);
        object3.setName("object 3");
//        object3.setListStr(listStr2);
        System.out.println(Arrays.toString(arr));
        System.out.println(listStr);
        System.out.println(object1);
        System.out.println(object2);
        System.out.println(object3);
        System.out.println(group.hashCode() + "------------" + object1.getGroup().hashCode() + "--------" + object2.getGroup().hashCode() +
                "-----" + object3.getGroup().hashCode());
    }
}
