package com.teainspired.datapersistence;

import com.teainspired.model.Supervisor;
import com.teainspired.model.UsernameComparator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLDOMUserDaoImplSupervisorTest {
    private XMLDOMUserDaoImpl noUsersDao;
    private XMLDOMUserDaoImpl usersDao;
    private UsernameComparator usernameComparator = new UsernameComparator();
    private List<Supervisor> defaultSupervisorList;

    @BeforeEach
    void setUp() {
        String noUsersPath = XMLDOMUserDaoImplSupervisorTest.class.getResource("/xml/noUsers.xml").getPath();
        String usersPath = XMLDOMUserDaoImplSupervisorTest.class.getResource("/xml/users.xml").getPath();
        noUsersDao = new XMLDOMUserDaoImpl(noUsersPath);
        usersDao = new XMLDOMUserDaoImpl(usersPath);
        defaultSupervisorList = new ArrayList<>();
        defaultSupervisorList.add(new Supervisor("Jean-Luc", "engage"));
        defaultSupervisorList.add(new Supervisor("Worf", "behave"));
    }

    @AfterEach
    void tearDown() {
        noUsersDao = null;
        usersDao = null;
    }

    @Test
    void saveSupervisorException() {
        // throws Exception for a null Supervisor
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.saveSupervisor(null));

        //throws Exception for a Supervisor with no username
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.saveSupervisor(new Supervisor(null, "password")));

        //throws Exception for a Supervisor that has null password
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.saveSupervisor(new Supervisor("Maya", null)));

        //throws Exception for a Supervisor with too short a password
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.saveSupervisor(new Supervisor("Maya", "M1")));

    }

    @Test
    void saveSupervisorWhoAlreadyExists() {
        // expect that when you put a user with changed password they will be saved.
        Supervisor preUpdate = usersDao.getSupervisor("Jean-Luc");
        Supervisor update = usersDao.getSupervisor("Jean-Luc");
        update.setPassword("BORG!");
        //assert that a clone is passed back so that user cannot update objects in the map accidentally
        assertNotEquals(preUpdate, update);// this will only pass if a clone is passed back
        usersDao.saveSupervisor(update);
        Supervisor actual = usersDao.getSupervisor("Jean-Luc");
        assertEquals("Jean-Luc", actual.getUsername() );
        assertEquals("BORG!", actual.getPassword());
    }

    @Test
    void saveSupervisorNotPresent() {
        String username = "Bagpuss";
        assertNull(usersDao.getSupervisor(username));
        Supervisor addition = new Supervisor(username, "catpuss");
        usersDao.saveSupervisor(addition);
        assertEquals(addition, usersDao.getSupervisor(username));
    }

    @Test
    void getSupervisorExceptionCase() {
        // Exception thrown when username null
        assertThrows(IllegalArgumentException.class,
                () -> usersDao.getSupervisor(null));
    }

    @Test
    void getSupervisorSupervisorExists() {
        // supervisor exists
        Supervisor expected = new Supervisor("Jean-Luc", "engage");
        Supervisor actual = usersDao.getSupervisor("Jean-Luc");
        assertEquals(expected, actual);
    }

    @Test
    void getSupervisorDoesNotExist() {
        assertNull(usersDao.getSupervisor("Evangeline"));
        assertNull(noUsersDao.getSupervisor("Evangeline"));
    }


    @Test
    void getAllSupervisorsWithNoSupervisors() {
        List<Supervisor> actual = noUsersDao.getAllSupervisors();
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void getAllSupervisorsWithSupervisors() {
        List<Supervisor> actual = usersDao.getAllSupervisors();
        actual.sort(usernameComparator);
        defaultSupervisorList.sort(usernameComparator);
        assertEquals(defaultSupervisorList, actual);
        // do a check to make sure that list is not returning references to original values
        for(Supervisor s: actual) {
            s.setPassword("newPassord");//this change should not affect the original
            assertNotEquals(s, usersDao.getSupervisor(s.getUsername()));
        }
    }

    @Test
    void removeSupervisorExceptionCases(){
        // pass in null
        assertThrows(IllegalArgumentException.class,
                () -> usersDao.removeSupervisor(null));
        // pass in invalid user
        assertThrows(IllegalArgumentException.class,
                () -> noUsersDao.removeSupervisor(new Supervisor()));
    }

    @Test
    void removeSupervisorNonExistentUser(){
        // remove a non existent user
        assertTrue(noUsersDao.getAllSupervisors().isEmpty());
        noUsersDao.removeSupervisor(new Supervisor("dog", "woof"));
        assertTrue(noUsersDao.getAllSupervisors().isEmpty());

        //remove non eisten user f
        List actual = usersDao.getAllSupervisors();
        actual.sort(usernameComparator);
        defaultSupervisorList.sort(usernameComparator);
        assertEquals(defaultSupervisorList, actual);
        usersDao.removeSupervisor(new Supervisor("dog", "woof"));
        actual = usersDao.getAllSupervisors();
        actual.sort(usernameComparator);
        assertEquals(defaultSupervisorList, actual);
    }

    @Test
    void removeSupervisorExistingUser(){
        // remove an existing user
        Supervisor toRemove = usersDao.getSupervisor("Jean-Luc");
        assertNotNull(toRemove);
        usersDao.removeSupervisor(toRemove);
        assertNull(usersDao.getSupervisor("Jean-Luc"));
    }

}