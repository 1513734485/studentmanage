package com.example.xinji2023student.entity;

public class Userer {
    private String firstName;
    private String lastName;

    // 必须提供 getter 和 setter
    public String getFirstName() {
        System.out.println(firstName);
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        System.out.println(lastName);
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
