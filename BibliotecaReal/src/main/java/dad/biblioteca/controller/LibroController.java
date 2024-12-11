package dad.biblioteca.controller;

import dad.biblioteca.modelo.*;
import dad.biblioteca.persistencia.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class LibroController implements Initializable {

    @FXML
    private TableColumn<Libro, String> Autor;

    @FXML
    private Button Editar;

    @FXML
    private Button Eliminar;

    @FXML
    private Button Nuevo;

    @FXML
    private TableColumn<Libro, String> Titulo;

    @FXML
    private BorderPane root;

    @FXML
    private TableView<Libro> tableLibro;

    private Biblioteca bibliotecaReal;

    private AñadirLibroController añadirLibroController;

    private ObservableList<Libro> libroList = FXCollections.observableArrayList();

    @FXML
    void onEditarAction(ActionEvent event) {
        Libro selected = tableLibro.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ActualizarLibroView.fxml"));
                BorderPane actualizarRoot = loader.load();
                ActualizarLibroController actualizarController = loader.getController();
                actualizarController.setLibro(selected);
                Stage stage = new Stage();
                stage.setTitle("Actualizar Libro");
                stage.setScene(new Scene(actualizarRoot));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOnHidden(e -> tableLibro.refresh());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Editar libro");
            alert.setContentText("Debe seleccionar un libro.");
            alert.showAndWait();
        }
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
        Libro selected = tableLibro.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (bibliotecaReal == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No se ha cargado la biblioteca.");
                alert.showAndWait();
                return;
            }
            try {
                libroList.remove(selected);
                bibliotecaReal.getLibros().remove(selected);
                Persistencia.guardar(bibliotecaReal, new File("biblioteca.xml"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eliminar libro");
            alert.setContentText("Debe seleccionar un libro.");
            alert.showAndWait();
        }
    }

    @FXML
    void onNuevoAction(ActionEvent event) {
        Stage stage = new Stage();
        AñadirLibroController añadirLibroController = new AñadirLibroController();
        stage.setTitle("Crear libro");
        stage.setScene(new Scene(añadirLibroController.getRoot()));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Autor.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
        Titulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        tableLibro.setItems(libroList);

        File file = new File("biblioteca.xml");
        try {
            bibliotecaReal = Persistencia.leer(file);  // Asignando bibliotecaReal aquí
            if (bibliotecaReal != null) {
                libroList.addAll(bibliotecaReal.getLibros());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Button getEditar() {
        return Editar;
    }

    public void setEditar(Button editar) {
        Editar = editar;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public Button getNuevo() {
        return Nuevo;
    }

    public void setNuevo(Button nuevo) {
        Nuevo = nuevo;
    }

    public TableColumn<Libro, String> getAutor() {
        return Autor;
    }

    public void setAutor(TableColumn<Libro, String> autor) {
        Autor = autor;
    }

    public TableColumn<Libro, String> getTitulo() {
        return Titulo;
    }

    public void setTitulo(TableColumn<Libro, String> titulo) {
        Titulo = titulo;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TableView<Libro> getTableLibro() {
        return tableLibro;
    }

    public void setTableLibro(TableView<Libro> tableLibro) {
        this.tableLibro = tableLibro;
    }
}
