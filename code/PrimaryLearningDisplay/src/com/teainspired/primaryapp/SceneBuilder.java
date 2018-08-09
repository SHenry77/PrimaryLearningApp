package com.teainspired.primaryapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SceneBuilder {
    public static Scene createNoUserStartSceene(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //button to add user
        Button addUserBtn = new Button("Add User");
        grid.add(addUserBtn, 0, 0);
        addUserBtn.setDefaultButton(true);
        return new Scene(grid, 500, 275);
    }

    public Scene getCreateUserScene(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return null;
    }

}
