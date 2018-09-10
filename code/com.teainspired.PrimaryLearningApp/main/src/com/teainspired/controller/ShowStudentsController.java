package com.teainspired.controller;

import com.teainspired.datapersistence.observable.ObservableUserDAO;
import com.teainspired.main.PrimaryLearningApp;
import com.teainspired.model.observable.FXStudent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ShowStudentsController {

    // student table view
    @FXML private TableView<FXStudent> studentTable;
    @FXML private TableColumn<FXStudent, String> usernameColumn;
    @FXML private TableColumn<FXStudent, Number> primaryColumn;
    @FXML private Button loginButton;

    // dao for fetching data
    private ObservableUserDAO userDAO;
    private PrimaryLearningApp primaryLearningApp;
    private System.Logger logger;

    public ShowStudentsController(){
        logger = System.getLogger(ShowStudentsController.class.getName());
    }

    @FXML
    private void initialize(){
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        primaryColumn.setCellValueFactory(cellData -> cellData.getValue().primaryProperty());
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> enableLoginButton(newValue))
        );
    }

    private void enableLoginButton(FXStudent student){
        if(student != null){
            loginButton.setDisable(false);
        } else {
            loginButton.setDisable(true);
        }
    }

    @FXML
    private void handleLogin(){
        FXStudent student = studentTable.getSelectionModel().selectedItemProperty().get();
        if( student != null ) {
            primaryLearningApp.setLoggedInStudent(student);
            primaryLearningApp.showGamesView();
            logger.log(System.Logger.Level.INFO, String.format("Student with username:%s logged in.", student.getUsername()));
        }
    }
    /*
     * sets the observableUserDao
     * @param userDAO the userDao to reference
     */
    public void setObservableUserDAO(ObservableUserDAO userDAO){
        this.userDAO = userDAO;
    }

    /*
     * sets the students in the studentTable
     */
    public void setStudents(){
        studentTable.setItems(userDAO.getAllStudents());
    }

    /*
     * sets a reference to the main app
     * @param PrimaryLearningApp the primaryLearningApp to reference
     */
    public void setPrimaryLearningApp(PrimaryLearningApp primaryLearningApp) {
        this.primaryLearningApp = primaryLearningApp;
    }
}
