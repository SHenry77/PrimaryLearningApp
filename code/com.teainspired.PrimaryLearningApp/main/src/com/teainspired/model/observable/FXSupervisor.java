package com.teainspired.model.observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class FXSupervisor {
    @FXML
    private StringProperty username;

    @FXML
    private StringProperty password;

    public FXSupervisor(){
        this(null, null);
    }

    public FXSupervisor(String username, String password){
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FXSupervisor that = (FXSupervisor) o;

        if (!username.getValue().equals(that.username.getValue())) return false;
        return password.getValue().equals(that.password.getValue());
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
