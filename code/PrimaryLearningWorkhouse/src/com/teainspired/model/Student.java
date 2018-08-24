package com.teainspired.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

public class Student implements User, Cloneable {

    private String username;
    private int primary;

    public Student(){}

    public Student(String username, int primary){
        this.username = username;
        this.primary = primary;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public void setUsername(String username){
        this.username = username;
    }

    public int getPrimary(){
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return primary == student.primary &&
                Objects.equals(username, student.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, primary);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Student -");
        builder.append("Username: ").append(username);
        builder.append(" Primary: ").append(primary);
        return builder.toString();
    }

    @Override
    public Student clone() {
        return new Student(username, primary);
    }
}