package com.teainspired.datapersistence;

import com.teainspired.model.Student;
import com.teainspired.model.Supervisor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.teainspired.datapersistence.DOMHelper.readDocument;

public class XMLDOMUserDaoImpl implements UserDAO {

    private Map<String, Student> studentMap;
    private Map<String, Supervisor> supervisorMap;

    public XMLDOMUserDaoImpl(String xmlFilePath) {
        studentMap = new HashMap<>();
        supervisorMap = new HashMap<String, Supervisor>();
        readFile(xmlFilePath);
    }

    @Override
    public void saveStudent(Student student)  {
        validateStudent(student);
        studentMap.put(student.getUsername(), student);
        //ToDo persist change to document
    }

    @Override
    public Student getStudent(String username) {
        if(username == null){
            throw new IllegalArgumentException("username must not be null.");
        }
        Student original = studentMap.get(username);
        return original == null ? original : original.clone();
    }

    @Override
    public List<Student> getAllStudents()  {
        ArrayList<Student> studentList = new ArrayList<>();
        for (Student student:studentMap.values()) {
            studentList.add(student.clone());
        }
        return studentList;
    }

    @Override
    public void removeStudent(Student student) {
        if(student == null){
            throw new IllegalArgumentException("Student must not be null");
        }
        studentMap.remove(student.getUsername());
        //TODO persist change in file
    }

    @Override
    public void saveSupervisor(Supervisor supervisor) {
        validateSupervisor(supervisor);
        supervisorMap.put(supervisor.getUsername(), supervisor);
    }

    @Override
    public Supervisor getSupervisor(String username) {
        if(username == null){
            throw new IllegalArgumentException("username must not be null.");
        }
        Supervisor original = supervisorMap.get(username);
        return original == null ? original : original.clone();
    }

    @Override
    public List<Supervisor> getAllSupervisors() {
        List<Supervisor> supervisors = new ArrayList<Supervisor>();
        for(Supervisor s: supervisorMap.values()) {
            supervisors.add(s.clone());
        }
        return supervisors;
    }

    @Override
    public void removeSupervisor(Supervisor supervisor) {
        validateSupervisor(supervisor);
        supervisorMap.remove(supervisor.getUsername());
    }

    /*
     * reads the students and supervisors from the xml file if present
     */

    private void readFile(String xmlFilePath) {
        File xmlFile = new File(xmlFilePath);
        if(xmlFile.exists()) {
            Document document = null;
            try {
                document = readDocument(xmlFile);
            } catch (IOException e) {
                System.err.println("IO exception reading User XML file:");
                e.printStackTrace(System.err);
                System.exit(1);
            } catch (SAXException e) {
                System.err.println("Error reading User XML File: ");
                e.printStackTrace(System.err);
                System.exit(1);
            } catch (ParserConfigurationException e) {
                System.err.println("Error parsing User XML File");
                e.printStackTrace(System.err);
                System.exit(1);
            }
            getStudentsFromDocument(document);
            getSupervisorsFromDocument(document);
        }
    }
    /*
     * gets the student elements from the document then
     * puts them in the student map
     */

    private void getStudentsFromDocument(Document doc){
        NodeList nodeList = doc.getElementsByTagName("student");
        for (int i=0; i < nodeList.getLength(); i++) {

            Element element = (Element) nodeList.item(i);
            String username = DOMHelper.getTagValue("username", element);
            int primary = DOMHelper.getTagValueAsInt("primaryYear", element);
            studentMap.put(username, new Student(username, primary));
        }
    }
    /*
     * gets the supervisor elemtns from the document then
     * puts them in the supervisor map
     */

    private void getSupervisorsFromDocument(Document document){
        NodeList nodeList = document.getElementsByTagName("supervisor");
        for (int i=0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String username = DOMHelper.getTagValue("username", element);
            String password = DOMHelper.getTagValue("password", element);
            supervisorMap.put(username, new Supervisor(username, password));
        }
    }
    private void validateStudent(Student student) throws IllegalArgumentException {
        if(student == null){
            throw new IllegalArgumentException("student must not be null.");
        }else if ( student.getUsername() == null){
            throw new IllegalArgumentException("student username must not be null.");
        }else if ( student.getPrimary() < 1 || student.getPrimary() > 7 ){
            throw new IllegalArgumentException("student primary must be between 1 and 7 inclusive.");
        }
    }

    // validates the supervisor
    private void validateSupervisor(Supervisor supervisor) throws IllegalArgumentException {
        if(supervisor == null){
            throw new IllegalArgumentException("supervisor must not be null");
        } else if( supervisor.getUsername() == null ){
            throw new IllegalArgumentException("supervisor username must not be null");
        } else if ( supervisor.getPassword() == null ){
            throw new IllegalArgumentException("supervisor password must not be null");
        } else if ( supervisor.getPassword().length() < 3 ){
            throw new IllegalArgumentException("supervisor password must be at least 3 characters long");
        }

    }
}
