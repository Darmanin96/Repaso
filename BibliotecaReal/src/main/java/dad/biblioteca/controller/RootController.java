package dad.biblioteca.controller;

import javafx.fxml.*;
import javafx.scene.control.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class RootController implements Initializable {

    @FXML
    private Tab Editorial;

    @FXML
    private Tab Libros;

    @FXML
    private TabPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public RootController(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Tab getEditorial() {
        return Editorial;
    }

    public void setEditorial(Tab editorial) {
        Editorial = editorial;
    }

    public Tab getLibros() {
        return Libros;
    }

    public void setLibros(Tab libros) {
        Libros = libros;
    }

    public TabPane getRoot() {
        return root;
    }

    public void setRoot(TabPane root) {
        this.root = root;
    }
}
