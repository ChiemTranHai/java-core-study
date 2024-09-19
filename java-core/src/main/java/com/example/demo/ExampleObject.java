package com.example.demo;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ExampleObject implements Cloneable, Serializable {

    private int id;
    private String name;

    private ExampleGroup group;

    private int[] arr;

    private List<String> listStr;

    public ExampleObject() {
    }

    @Override
    public ExampleObject clone() throws CloneNotSupportedException {
//        return (ExampleObject) super.clone();
        return (ExampleObject) SerializationUtils.clone(this);
    }

    public ExampleObject(int id, String name, ExampleGroup group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public ExampleObject(int id, String name, ExampleGroup group, int[] arr) {
        this(id,name,group);
        this.arr = arr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExampleGroup getGroup() {
        return group;
    }

    public void setGroup(ExampleGroup group) {
        this.group = group;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public List<String> getListStr() {
        return listStr;
    }

    public void setListStr(List<String> listStr) {
        this.listStr = listStr;
    }

    @Override
    public String toString() {
        return "ExampleObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group=" + group +
                ", arr=" + Arrays.toString(arr) +
                ", listStr=" + listStr +
                '}';
    }
}
