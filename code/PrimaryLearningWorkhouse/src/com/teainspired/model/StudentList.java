package com.teainspired.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(namespace="com.teainspired.model")
public class StudentList {

    @XmlElementWrapper(name="studentList")
    @XmlElement(name="student")
    private ArrayList<Student> studentList;

    public ArrayList<Student> getStudentList(){
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
}
