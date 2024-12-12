package org.example.Main;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import org.example.*;

public class InterfaceApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        RootController rootController = new RootController();
        Stage stage = new Stage();
        stage.setTitle("Ranking book");
        stage.setScene(new Scene(rootController.getRoot()));
        stage.show();
    }
}
