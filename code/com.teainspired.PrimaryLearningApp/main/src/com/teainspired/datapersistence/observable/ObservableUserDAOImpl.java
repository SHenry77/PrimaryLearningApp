package com.teainspired.datapersistence.observable;

import com.teainspired.datapersistence.UserDAO;
import com.teainspired.model.Student;
import com.teainspired.model.Supervisor;
import com.teainspired.model.observable.FXStudent;
import com.teainspired.model.observable.FXSupervisor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ObservableUserDAOImpl implements ObservableUserDAO {

    private UserDAO userDAO;

    public ObservableUserDAOImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public ObservableList<FXStudent> getAllStudents() {
        List<Student> originalList = userDAO.getAllStudents();
        ObservableList<FXStudent> convertedList = FXCollections.observableArrayList();
        for (Student student: originalList) {
            convertedList.add(new FXStudent(student.getUsername(), student.getPrimary()));
        }
        return convertedList;
    }

    @Override
    public FXStudent getStudent(String username) {
        Student originalStudent = userDAO.getStudent(username);
        if(originalStudent == null) {
            return null;
        }
        return new FXStudent(originalStudent.getUsername(), originalStudent.getPrimary());
    }

    @Override
    public ObservableList<FXSupervisor> getAllSupervisors() {
        List<Supervisor> originalList = userDAO.getAllSupervisors();
        ObservableList<FXSupervisor> convertedList = FXCollections.observableArrayList();
        for (Supervisor sup : originalList) {
            convertedList.add(new FXSupervisor(sup.getUsername(), sup.getPassword()));
        }
        return convertedList;
    }

    @Override
    public FXSupervisor getSupervisor(String username) {
        Supervisor original = userDAO.getSupervisor(username);
        if( original == null) {
            return null;
        }
        return new FXSupervisor(original.getUsername(),original.getPassword());
    }


}
