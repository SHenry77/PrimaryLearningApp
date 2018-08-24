package com.teainspired.main;

import com.teainspired.datapersistence.UserDAO;
import com.teainspired.datapersistence.XMLDOMUserDaoImpl;
import com.teainspired.model.Student;

import java.util.List;

public class Main {
    public static void main(String[] args){
        System.out.println("Starting App");
        String userXML = "C:/Users/Douglas/IdeaProjects/PrimaryLearningApp/code/PrimaryLearningWorkhouse/resources/xml/Users.xml";
        try {
            UserDAO userDAO = new XMLDOMUserDaoImpl(userXML);
            List<Student> students = userDAO.getAllStudents();
            for (Student s: students) {
                System.out.println(s.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
