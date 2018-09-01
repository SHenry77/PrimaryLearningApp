package com.teainspired.datapersistence;

import com.teainspired.model.Student;
import com.teainspired.model.UsernameComparator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLDOMUserDaoImplStudentTest {
    private XMLDOMUserDaoImpl noUsersDao;
    private XMLDOMUserDaoImpl usersDao;
    private UsernameComparator usernameComparator = new UsernameComparator();
    private List<Student> defaultStudentList;

    @BeforeEach
    void setUp() {
        String path = "C:/Users/Douglas/IdeaProjects/PrimaryLearningApp/code/PrimaryLearningWorkhouse/test-resources/xml/";
        noUsersDao = new XMLDOMUserDaoImpl(path + "noUsers.xml");
        usersDao = new XMLDOMUserDaoImpl(path + "users.xml");
        defaultStudentList = new ArrayList<>();
        defaultStudentList.add(new Student("Wesley", 1));
        defaultStudentList.add(new Student("Will", 1));
        defaultStudentList.add(new Student("Geordie", 2));
        defaultStudentList.add(new Student("Data", 7));
    }

    @AfterEach
    void tearDown() {
        noUsersDao = null;
        usersDao = null;
    }

    @Test
    void saveStudentExceptionCases() {
        //assert Exception thrown for null Student
        assertThrows( IllegalArgumentException.class,
                () -> noUsersDao.saveStudent(null),"student must not be null.");
        //assert Exception thrown for null username
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.saveStudent(new Student()));
        //assert Exception thrown for invalid primaryYear
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.saveStudent(new Student("Billy", 8)));
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.saveStudent(new Student("Bertie", 0)));
    }

    @Test
    void saveStudent() {
        //assert correctly saves student when there are none.
        assertTrue(noUsersDao.getAllStudents().isEmpty());
        Student jayne = new Student("Jayne", 4);
        noUsersDao.saveStudent(jayne);
        assertEquals(jayne, noUsersDao.getStudent("Jayne") );
        List<Student> studentsAfterSave = noUsersDao.getAllStudents();
        assertTrue(studentsAfterSave.contains(jayne));
        assertEquals(1, studentsAfterSave.size());

        //assert correctly saves student when there are some.
        List<Student> studentsBefore = noUsersDao.getAllStudents();
        assertFalse(noUsersDao.getAllStudents().isEmpty());
        Student mal = new Student("Mal", 5);
        noUsersDao.saveStudent(mal);
        assertEquals(mal, noUsersDao.getStudent("Mal") );
        List<Student> studentsAfterSave2 = noUsersDao.getAllStudents();
        assertTrue(studentsAfterSave2.contains(mal));
        assertEquals(studentsBefore.size()+1, studentsAfterSave2.size());
    }

    @Test
    void getStudentExceptionCases() {
        // assert throws Exception for Null Student
        assertThrows(IllegalArgumentException.class,
                ()-> noUsersDao.getStudent(null));
    }

    @Test
    void getStudentStudentDoesNotExist() {
        // assert null returned for non-existing student (empty map)
        assertNull(noUsersDao.getStudent("Mary"));
        // assert null return for non-existing student (non-empty map)
        assertNull(usersDao.getStudent("Mary"));
    }

    @Test
    void getStudentStudentExists() {
        // assert correct student returned
        Student geordie = new Student("Geordie", 2);
        assertEquals(geordie, usersDao.getStudent("Geordie"));
        //check the returned student is a clone by changing values.
        Student toChange = usersDao.getStudent("Geordie");
        toChange.setPrimary(5);
        assertNotEquals(toChange, usersDao.getStudent("Geordie"));
    }

    @Test
    void getAllStudents() {
        // assert Empty List returned where no students exist;
        assertTrue(noUsersDao.getAllStudents().isEmpty());
        // assert correct List when students Exist;
        List<Student> actual = usersDao.getAllStudents();
        defaultStudentList.sort(usernameComparator);
        actual.sort(usernameComparator);
        assertEquals(defaultStudentList, actual);
        //check each of the students is a clone not a refernence to actual student
        for (Student student:actual) {
            student.setPrimary(8);
            assertNotEquals(student, usersDao.getStudent(student.getUsername()));
        }
    }

    @Test
    void removeStudentExceptionCases() {
        // assert Exception thrown for null student
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.removeStudent(null));
    }

    @Test
    void removeStudentStudentIsPresentInOriginalList() {
        // assert student list is as expected beforehand
        defaultStudentList.sort(usernameComparator);
        List<Student> before = usersDao.getAllStudents();
        before.sort(usernameComparator);
        assertEquals(defaultStudentList, before);

        // remove student
        Student data = usersDao.getStudent("Data");
        usersDao.removeStudent(data);

        // check student is missing
        List<Student> actual = usersDao.getAllStudents();
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Wesley", 1));
        expected.add(new Student("Will", 1));
        expected.add(new Student("Geordie", 2));
        expected.sort(usernameComparator);
        actual.sort(usernameComparator);
        assertEquals(expected, actual);
    }

    @Test
    void removeStudentWhenStudentNotPresentInOriginalList() {
        // assert list is unchanged if student not present
        Student absent = new Student("Absent", 3);
        assertTrue(noUsersDao.getAllStudents().isEmpty());
        noUsersDao.removeStudent(absent);
        assertTrue(noUsersDao.getAllStudents().isEmpty());

        // check list is default before removing astudent
        List<Student> before = usersDao.getAllStudents();
        defaultStudentList.sort(usernameComparator);
        before.sort(usernameComparator);
        assertEquals(defaultStudentList, before);

        usersDao.removeStudent(absent);
        List<Student> after = usersDao.getAllStudents();
        after.sort(usernameComparator);
        assertEquals(defaultStudentList, after);

    }


}