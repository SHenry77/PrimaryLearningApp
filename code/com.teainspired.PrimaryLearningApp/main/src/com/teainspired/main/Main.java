package com.teainspired.main;


import com.teainspired.datapersistence.UserDAO;
import com.teainspired.datapersistence.XMLDOMUserDaoImpl;
import com.teainspired.model.Student;
import com.teainspired.model.Supervisor;

import java.util.List;

public class Main {
    public static void main(String[] args){
        System.Logger logger = System.getLogger(Main.class.getName());
        logger.log(System.Logger.Level.INFO, "Starting App");
        String userXML = Main.class.getResource("/xml/Users.xml").getFile();
        logger.log(System.Logger.Level.DEBUG, userXML);
        UserDAO userDAO = new XMLDOMUserDaoImpl(userXML);
        List<Student> students = userDAO.getAllStudents();
        for (Student s: students) {
            System.out.println(s.toString());
        }
        List<Supervisor> supervisors = userDAO.getAllSupervisors();
        for(Supervisor supervisor: supervisors) {
            System.out.println(supervisor.toString());
        }

    }
}
