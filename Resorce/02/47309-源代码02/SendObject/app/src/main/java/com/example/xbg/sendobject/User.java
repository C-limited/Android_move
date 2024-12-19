package com.example.xbg.sendobject;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name;
    private int age;
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //将对象数据成员写入dest
        dest.writeString(getName());
        dest.writeInt(getAge());
    }
    public static  Creator<User> CREATOR=new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source.readString(),source.readInt());
        }
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
/***实现Serializable接口时的类代码
 import java.io.Serializable;
 public class User implements Serializable {
 private String name;
 private int age;
 public User(String name, int age) {
 this.name = name;
 this.age = age;
 }
 public String getName() {
 return name;
 }
 public void setName(String name) {
 this.name = name;
 }
 public int getAge() {
 return age;
 }
 public void setAge(int age) {
 this.age = age;
 }
 }



* */