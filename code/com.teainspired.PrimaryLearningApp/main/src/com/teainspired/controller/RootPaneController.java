package com.teainspired.controller;

import com.teainspired.main.PrimaryLearningApp;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class RootPaneController {

    private System.Logger logger;
    private PrimaryLearningApp primaryLearningApp;

    public RootPaneController(){
        logger = System.getLogger(this.getClass().getName());
    }

    @FXML
    private void handleShowStudents(){
       primaryLearningApp.showStudentsView();
    }

    @FXML
    private void handleExit(){
        logger.log(System.Logger.Level.INFO, "handling exit press");
        Platform.exit();
    }

    public void setPrimaryLearningApp(PrimaryLearningApp primaryLearningApp) {
        this.primaryLearningApp = primaryLearningApp;
    }
}
