package dad.biblioteca.Main;

import dad.biblioteca.controller.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class BibliotecaApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        RootController rootController = new RootController();
        Stage stage1 = new Stage();
        stage1.setTitle("Biblioteca");
        stage1.setScene(new Scene(rootController.getRoot()));
        stage1.show();
    }
}
