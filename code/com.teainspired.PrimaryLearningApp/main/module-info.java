module com.teainspired.PrimaryLearningApp{
    requires java.base;
    requires java.xml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.teainspired.model;
    exports com.teainspired.datapersistence;
    exports com.teainspired.main;
}