package com.teainspired.datapersistence;

import com.teainspired.model.Student;
import com.teainspired.model.Supervisor;

import java.util.List;

public interface UserDAO {
    /*
     * Method to persist a Student object
     * @param Student - the Student object to save - must not be null
     */
    public void saveStudent(Student student);

    /*
     * Method to retrieve the Student with the given username
     * @param username - the username of the student to retrieve
     * @return returns the student with the given name or throws a NotFoundException
     */
    public Student getStudent(String username);

    /*
     * Method to retrieve a list of all the Students
     * @return A list of all the students saved or an Empty list if no students were found.
     * @throws exception
     */
    public List<Student> getAllStudents();

    /*
     * Method to remove a saved student will ignore if the student is not present
     * @param student - the student to remove
     */
    public void removeStudent(Student student);

    /*
     * Method to persist the Supervisor object provided
     * @param supervisor - the supervisor to save - must not be null
     */
    public void saveSupervisor(Supervisor supervisor);

    /*
     * Method to retrieve the supervisor with the username provided.
     * @return the supervisor provided or null if none found
     */
    public Supervisor getSupervisor(String username);
    /*
     * Method to return a list of all the Supervisors.
     * @returns a list of supervisors or an emptyList if none found
     */
    public List<Supervisor> getAllSupervisors();
    /*
     * Method to remove the provided supervisor from the saved list
     * @param supervisor - must not be null.
     */
    public void removeSupervisor(Supervisor supervisor);

}
