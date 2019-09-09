package test;

import java.util.ArrayList;
import java.util.List;

public class Test_1{
    public static void main(String[] args) throws CloneNotSupportedException {
//        t1();   // 浅克隆(clone方法)
        t2();   // 深克隆(clone方法修改)
    }

    private static void t2() throws CloneNotSupportedException {
        Student student = new Student("男");

        Person p1 = new Person(23, "张三");
        p1.setStudent(student);

        Person clone = (Person)p1.clone();
        clone.setAge(20);
        clone.getStudent().setSex("女");
        System.out.println(clone);

        System.out.println(p1);
    }

    private static void t1() throws CloneNotSupportedException {
        Student student = new Student("男");

        Person p1 = new Person(23, "张三");
        p1.setStudent(student);

        Person clone = (Person)p1.clone();
        clone.setAge(20);
        clone.getStudent().setSex("女");
        System.out.println(clone);

        System.out.println(p1);
    }
}

class Person implements Cloneable{
    private int age;
    private String name;

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", student_sex=" + (student == null ? null : student.getSex()) +
                '}';
    }

    // 深克隆
    @Override
    public Object clone() throws CloneNotSupportedException {
        Person clone = (Person) super.clone();
        clone.setStudent((Student) clone.getStudent().clone());
        return clone;
    }

    // 浅克隆
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
}

class Student implements Cloneable{
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Student(String sex) {
        this.sex = sex;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
