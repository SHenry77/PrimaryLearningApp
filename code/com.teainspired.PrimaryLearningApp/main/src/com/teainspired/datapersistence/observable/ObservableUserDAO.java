package com.teainspired.datapersistence.observable;

import com.teainspired.model.observable.FXStudent;
import com.teainspired.model.observable.FXSupervisor;
import javafx.collections.ObservableList;

public interface ObservableUserDAO {
    /*
     * a method to return a list of all students in an observable list
     * @returns List<FXStudent> an empty List if no students
     */
    ObservableList<FXStudent> getAllStudents();
    
    /*
     * a method to return an individual student from their username
     * @param username - the username of the student to be fetch
     * @returns the FXStudent with the given username or null if not found
     */
    FXStudent getStudent(String username);

    /*
     * a method to return a observable list of all supervisors
     * @returns List<FXSupervisor> an empty list is returned if there are no supervisors
     */
    ObservableList<FXSupervisor> getAllSupervisors();

    /*
     * a method to return a single supervisor with the given username
     * @param username - the username of the supervisor to fetch
     * @return the FXSupervisor with the provided username or null if not found
     */
    FXSupervisor getSupervisor(String username);
}
