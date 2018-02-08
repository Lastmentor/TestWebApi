package com.example.rza.denemewebapi;

public class Students {
    private String firstName;
    private String lastName;
    private String department;

    public Students(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }
}
