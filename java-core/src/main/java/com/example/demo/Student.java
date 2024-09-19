package com.example.demo;

public class Student implements Comparable<Student> {

    private Integer age;

    private Long salary;

    private String lastname;

    private Integer experience;

    public Student(Integer age) {
        this.age = age;
    }

    public Student(String lastname, Integer age) {
        this.lastname = lastname;
        this.age = age;
    }

    public Student(Integer age, Long salary, String lastname, Integer experience) {
        this.age = age;
        this.salary = salary;
        this.lastname = lastname;
        this.experience = experience;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public int compareTo(Student o) {
        return this.age.compareTo(o.getAge());
    }

    @Override
    public String toString() {
        return "Student{" +
                "lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
