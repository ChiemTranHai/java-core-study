package com.example.demo;

import java.io.Serializable;

public class ExampleGroup implements Cloneable, Serializable {
    private int groupId;
    private String groupName;

    public ExampleGroup() {

    }

    public ExampleGroup(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "ExampleGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public ExampleGroup clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (ExampleGroup) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
