package com.teainspired.main;


import com.teainspired.controller.RootPaneController;
import com.teainspired.controller.ShowStudentsController;
import com.teainspired.datapersistence.UserDAO;
import com.teainspired.datapersistence.XMLDOMUserDaoImpl;
import com.teainspired.datapersistence.observable.ObservableUserDAO;
import com.teainspired.datapersistence.observable.ObservableUserDAOImpl;
import com.teainspired.model.observable.FXStudent;
import com.teainspired.model.observable.FXSupervisor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.System.Logger;

import static java.lang.System.Logger.Level;

public class PrimaryLearningApp extends Application {

    private BorderPane rootPane;
    private UserDAO userDAO;
    private ObservableUserDAO observableUserDAO;
    private Logger logger;
    private FXSupervisor loggedInSupervisor;
    private FXStudent loggedInStudent;

    public PrimaryLearningApp(){
        logger = System.getLogger(PrimaryLearningApp.class.getName());
        String userXML = PrimaryLearningApp.class.getResource("/xml/Users.xml").getFile();
        logger.log(Level.DEBUG, userXML);
        userDAO = new XMLDOMUserDaoImpl(userXML);
        observableUserDAO = new ObservableUserDAOImpl(userDAO);
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        // setup loader
        logger.log(Level.INFO, "Starting application");
        //fetch root pane
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PrimaryLearningApp.class.getResource("/fxml/RootPane.fxml"));
        try {
            rootPane = loader.load();
        } catch (IOException e) {
            handleLoadException(e, "RootPane");
        }
        RootPaneController controller = loader.getController();
        controller.setPrimaryLearningApp(this);
        showStudentsView();
        primaryStage.setScene(new Scene(rootPane));
        primaryStage.show();

    }

    // all view methods
    public void showStudentsView() {
        //fetch the pane
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PrimaryLearningApp.class.getResource("/fxml/ShowStudents.fxml"));
        Node showUsers = null;
        try {
            showUsers = loader.load();
        } catch (IOException e) {
            handleLoadException(e, "ShowStudents");
        }
        rootPane.setCenter(showUsers);

        // fetch controller and pass in required variables
        ShowStudentsController controller = loader.getController();
        controller.setPrimaryLearningApp(this);
        controller.setObservableUserDAO(observableUserDAO);
        controller.setStudents();
    }

    public void showGamesView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PrimaryLearningApp.class.getResource("/fxml/GamesView.fxml"));
        Node gamesView = null;
        try {
            gamesView = loader.load();
        } catch (IOException e) {
            handleLoadException(e, "GamesView");
        }
        rootPane.setCenter(gamesView);
    }


    public void setManageWordView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PrimaryLearningApp.class.getResource("/fxml/ManageWordsView.fxml"));
        Node manageWords = null;
        try {
            manageWords = loader.load();
        } catch (IOException e) {
            handleLoadException(e, "ManageWordsView");
        }
        rootPane.setCenter(manageWords);
    }

    // non-view related methods


    public void setLoggedInStudent(FXStudent student){
        loggedInStudent = student;
    }

    private void handleLoadException(Exception e, String view){
        logger.log(Level.ERROR, String.format("Error loading view: %s", view));
        logger.log(Level.ERROR, e.toString());
        Platform.exit();
    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @throws Exception if something goes wrong
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        logger.log(Level.INFO, "exiting app");
        System.exit(0);
    }
}
