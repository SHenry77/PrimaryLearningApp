package com.teainspired.datapersistence.observable;

import com.teainspired.datapersistence.UserDAO;
import com.teainspired.model.Student;
import com.teainspired.model.Supervisor;

import java.util.Collections;
import java.util.List;

public class TestUserDaoImpl implements UserDAO {
    private List<Student> studentList;
    private List<Supervisor> supervisorList;

    TestUserDaoImpl(){
        studentList = Collections.emptyList();
        supervisorList = Collections.emptyList();
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public Student getStudent(String username) {
        Student studentToReturn = null;
        for (Student s: studentList) {
            if(s.getUsername().equals(username)){
                studentToReturn = s;
            }
        }
        return studentToReturn;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }

    @Override
    public void removeStudent(Student student) {

    }

    @Override
    public void saveSupervisor(Supervisor supervisor) {

    }

    @Override
    public Supervisor getSupervisor(String username) {
        for (Supervisor s: supervisorList) {
            if(s.getUsername().equals(username)){
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Supervisor> getAllSupervisors() {
        return supervisorList;
    }

    @Override
    public void removeSupervisor(Supervisor supervisor) {

    }

    void setStudents(List<Student> students){
        studentList = students;
    }

    void setSupervisors(List<Supervisor> supervisors){
        supervisorList = supervisors;
    }
}
