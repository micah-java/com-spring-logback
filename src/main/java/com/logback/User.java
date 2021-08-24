package com.logback;

import java.io.Serializable;

public class User implements Serializable {

    private String mobileNo;
    private int age;

    public User() {
    }

    public User(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public User(String mobileNo, int age) {
        this.mobileNo = mobileNo;
        this.age = age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("mobileNo='").append(mobileNo).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
