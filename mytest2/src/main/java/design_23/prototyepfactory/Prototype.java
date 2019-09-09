package design_23.prototyepfactory;

import java.io.*;

/**
 * 原型模式-深、浅克隆
 */
public class Prototype implements Cloneable, Serializable {

    private static final long serialVersionUID = 8831579621083633580L;

    private String name;
    private Student student;    // 引用-测试深复制

    /* 浅复制 */
    public Object clone() throws CloneNotSupportedException {
        Prototype proto = (Prototype) super.clone();
        return proto;
    }

    /* 深复制 */
    public Object deepClone() throws IOException, ClassNotFoundException {

		/* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

		/* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        ois.close();
        bis.close();
        oos.close();
        bos.close();
        return ois.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    /*
    test
     */
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Student student = new Student();
        student.setAge(18);

        Prototype prototype = new Prototype();
        prototype.setName("张三");
        prototype.setStudent(student);

        System.out.println("未修改原数据：" + prototype);

        Prototype clone = (Prototype)prototype.clone();
        Prototype deepclone = (Prototype)prototype.deepClone();

        // 修改属性值(深、浅克隆，两个值都不一样)
        prototype.setName("李四");

        // 修改引用数据(浅克隆两个值一样，深克隆不一样)
        student.setAge(24);

        System.out.println("修改后原数据：" + prototype);

        // 浅克隆数据的引用和原来的还是指向的同一个对象
        System.out.println("浅克隆数据：" + clone);

        // 深克隆数据独一份(值应该和未修改的原数据一样)
        System.out.println("深克隆数据：" + deepclone);
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "name='" + name + '\'' +
                ", student.age=" + (student == null ? null : student.getAge()) +
                '}';
    }
}

class Student implements Serializable {

    private static final long serialVersionUID = 3333576662044481294L;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
