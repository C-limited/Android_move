package com.example.administrator.classschedule;
/**
 * MyClass类用于封装每节课的信息，包括：节次（no）、上课时间（time）和课程名称（name）
 */

public class MyClass {
    private String no,time,name;
    public MyClass(String no, String time, String name) {
        this.no = no;
        this.time = time;
        this.name = name;
    }
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
