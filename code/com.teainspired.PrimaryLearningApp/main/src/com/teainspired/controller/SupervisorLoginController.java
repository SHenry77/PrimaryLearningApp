package com.teainspired.controller;

import com.teainspired.datapersistence.observable.ObservableUserDAO;
import com.teainspired.main.PrimaryLearningApp;
import com.teainspired.model.observable.FXSupervisor;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SupervisorLoginController {

    @FXML private Label errorLabel;

    @FXML private TextField username;
    @FXML private PasswordField password;
    private ObservableUserDAO userDAO;

    private PrimaryLearningApp primaryLearningApp;

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }
    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }


    public void setUserDAO(ObservableUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @FXML
    private void handleLogin(){
        // clear errors
        PseudoClass error = PseudoClass.getPseudoClass("error");
        username.pseudoClassStateChanged(error, false);
        password.pseudoClassStateChanged(error, false);
        errorLabel.textProperty().setValue("");
        boolean hasErrors = false;
        if(username.textProperty().getValue().isEmpty()){
            // error
            username.pseudoClassStateChanged(error, true);
            addErrorToLabel("Username is required.");
            hasErrors = true;
        }
        if(password.textProperty().getValue().isEmpty()){
            //
            password.pseudoClassStateChanged(error, true);
            addErrorToLabel("Password is required");
            hasErrors = true;
        }
        if(!hasErrors ){
            FXSupervisor supervisor = userDAO.getSupervisor(username.getText());
            if(supervisor == null){
                addErrorToLabel(String.format("No username with username:%s", username.textProperty().getValue()));
                username.pseudoClassStateChanged(error, true);
                hasErrors = true;
            } else {
                if(supervisor.getPassword().equals(password.getText())) {
                    // success
                    primaryLearningApp.setLoggedInSupervisor(supervisor);
                    primaryLearningApp.setManageWordView();
                } else {
                    password.pseudoClassStateChanged(error, true);
                    addErrorToLabel("Password incorrect.");
                }
            }
        }
    }

    public void setPrimaryLearningApp(PrimaryLearningApp primaryLearningApp) {
        this.primaryLearningApp = primaryLearningApp;
    }

    private void addErrorToLabel(String error){
        String errorText = errorLabel.textProperty().getValue();
        if(errorText.isEmpty()){
            errorText = error;
        } else {
            errorText += System.getProperty("line.separator") + error;
        }
        errorLabel.textProperty().setValue(errorText);
    }
}
