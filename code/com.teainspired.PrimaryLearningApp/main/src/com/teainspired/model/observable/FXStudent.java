package com.teainspired.model.observable;

import com.teainspired.model.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXStudent implements User {

    private final StringProperty username;
    private final IntegerProperty primary;

    public FXStudent(){
        this(null, 1);
    }

    public FXStudent(String username, int primary){
        this.username = new SimpleStringProperty(username);
        this.primary = new SimpleIntegerProperty(primary);
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

    public int getPrimary() {
        return primary.get();
    }

    public IntegerProperty primaryProperty() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary.set(primary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FXStudent fxStudent = (FXStudent) o;

        if (!username.getValue().equals(fxStudent.username.getValue())) return false;
        return primary.getValue().equals(fxStudent.primary.getValue());
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + primary.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FXStudent{");
        sb.append("username=").append(username);
        sb.append(", primary=").append(primary);
        sb.append('}');
        return sb.toString();
    }
}
