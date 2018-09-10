package com.teainspired.datapersistence.observable;

import com.teainspired.model.Supervisor;
import com.teainspired.model.observable.FXSupervisor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObservableUserDAOImplSupervisorTest {

    private TestUserDaoImpl userDao;
    private ObservableUserDAO observableUserDAO;

    @BeforeEach
    void setUp() {
        userDao = new TestUserDaoImpl();
        observableUserDAO = new ObservableUserDAOImpl(userDao);
    }

    @AfterEach
    void tearDown() {
        userDao = null;
        observableUserDAO = null;
    }

    @Test
    void getAllSupervisorsWithEmptyList() {
        // this is correct by default
        assertTrue(userDao.getAllSupervisors().isEmpty());
        assertTrue(observableUserDAO.getAllSupervisors().isEmpty());
    }

    @Test
    void getAllSupervisorsWithNonEmptyList() {
        //setup
        userDao.setSupervisors(generateSupervisorList(7));
        ObservableList<FXSupervisor> expected = generateFXSupervisorList(7);

        ObservableList<FXSupervisor> actual = observableUserDAO.getAllSupervisors();
        assertEquals(expected, actual);
    }

    @Test
    void getSupervisorWithEmptyList() {
        assertNull(observableUserDAO.getSupervisor("user202"));
    }

    @Test
    void getSupervisorWhereUserNotPresentInList() {
        userDao.setSupervisors(generateSupervisorList(8));
        assertNull(observableUserDAO.getSupervisor("user202"));
    }

    @Test
    void getSupervisorWhereUserPresent() {
        userDao.setSupervisors(generateSupervisorList(8));
        FXSupervisor expected = new FXSupervisor("user4", "password4");
        assertEquals(expected, observableUserDAO.getSupervisor("user4"));
    }

    private List<Supervisor> generateSupervisorList(int numUsers){
        List<Supervisor> supervisors = new ArrayList<>(numUsers);
        for(int i=0; i<numUsers; i++){
            String username = "user" + i;
            String password = "password" + i;
            supervisors.add(new Supervisor(username, password));
        }
        return supervisors;
    }

    private ObservableList<FXSupervisor> generateFXSupervisorList(int numUsers){
        ObservableList<FXSupervisor> supervisors = FXCollections.observableArrayList();
        for(int i=0; i<numUsers; i++){
            String username = "user" + i;
            String password = "password" + i;
            supervisors.add(new FXSupervisor(username, password));
        }
        return supervisors;
    }
}