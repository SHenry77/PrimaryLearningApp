package com.teainspired.datapersistence.observable;

import com.teainspired.model.Student;
import com.teainspired.model.observable.FXStudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObservableUserDAOImplStudentTest {

    private TestUserDaoImpl userDAO;
    private ObservableUserDAO observableUserDAO;

    @BeforeEach
    void setUp() {
       userDAO = new TestUserDaoImpl();
       observableUserDAO = new ObservableUserDAOImpl(userDAO);
    }

    @AfterEach
    void tearDown() {
        userDAO = new TestUserDaoImpl();
        observableUserDAO = null;
    }

    @Test
    void getAllStudentsTestWithEmptyList() {
        //userDAO has an empty list by default
        assertTrue(userDAO.getAllStudents().isEmpty());
        ObservableList<FXStudent> actual = observableUserDAO.getAllStudents();
        assertNotNull(actual, "null was unexpectedly returned");
        assertTrue(actual.isEmpty());
    }

    @Test
    void getAllStudentsWithList() {
        //setup
        List<Student> studentListInput = generateStudentList(3);
        userDAO.setStudents(studentListInput);
        ObservableList<FXStudent> expected = generateFXStudentList(3);
        ObservableList<FXStudent> actual = observableUserDAO.getAllStudents();
        assertEquals(expected, actual);

    }

    @Test
    void getStudentWhereNoneExist(){
        assertNull(observableUserDAO.getStudent("Student"));
    }

    @Test
    void getStudentWhenNotInList(){
        userDAO.setStudents(generateStudentList(5));
        assertNull(observableUserDAO.getStudent("Mary"));
    }

    @Test
    void getStudentWhoIsInList(){
        userDAO.setStudents(generateStudentList(2));
        FXStudent expected = new FXStudent("user2", 2);
        assertEquals(expected, observableUserDAO.getStudent("user2"));
    }

    private List<Student> generateStudentList(int numStudents){
        List<Student> studentList = new ArrayList<>();
        for(int i=1,primary=1; i<=numStudents; i++, primary++){
            String username = "user" + i;
            if( primary > 7){
                primary = primary - 7;
            }
            studentList.add(new Student(username, primary));
        }
        return studentList;
    }

    private ObservableList<FXStudent> generateFXStudentList(int numStudents){
        ObservableList<FXStudent> students = FXCollections.observableArrayList();
        for(int i=1,primary=1; i<=numStudents; i++, primary++){
            String username = "user" + i;
            if( primary > 7) {
                primary = primary - 7;
            }
            students.add(new FXStudent(username, primary));
        }
        return students;
    }
}