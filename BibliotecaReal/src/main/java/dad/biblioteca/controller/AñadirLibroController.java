package dad.biblioteca.controller;

import dad.biblioteca.modelo.*;
import dad.biblioteca.persistencia.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class AñadirLibroController implements Initializable {

    @FXML
    private TextField autor;

    @FXML
    private Button cancelar;

    @FXML
    private Button crear;

    @FXML
    private ChoiceBox<Editorial> editorial;

    @FXML
    private TextField fechaPublicacion;

    @FXML
    private TextField isbn;

    @FXML
    private BorderPane root;

    @FXML
    private TextField titulo;

    private LibroController libreController;

    private ObservableList<Libro> libroAñadir = FXCollections.observableArrayList();

    @FXML
    void onCancelarAction(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onCrearAction(ActionEvent event) {
            String fechaTexto = fechaPublicacion.getText();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.setLenient(false);
            try {
                 Date fecha = simpleDateFormat.parse(fechaTexto);
                 String TituloNuevo = titulo.getText();
                 String AutorNuevo = autor.getText();
                 String ISBN = isbn.getText();
                 Editorial EditorialNueva = editorial.getValue();
                 Libro libro = new Libro();
                 libro.setTitulo(TituloNuevo);
                 libro.setAutor(AutorNuevo);
                 libro.setIsbn(ISBN);
                 libro.setEditorial(EditorialNueva);
                 File file = new File("biblioteca.xml");
                 Biblioteca biblioteca = Persistencia.leer(file);
                 biblioteca.getLibros().add(libro);
                 Persistencia.guardar(biblioteca,file);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            } catch (ParseException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje");
                alert.setContentText("El formato de la fecha es incorrecta");
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            File file = new File("biblioteca.xml");
            Biblioteca biblioteca = Persistencia.leer(file);
            if (biblioteca.getEditoriales() != null){
                editorial.getItems().addAll(biblioteca.getEditoriales());
                editorial.setValue(biblioteca.getEditoriales().get(0));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AñadirLibroController(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AñadirLibroView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TextField getAutor() {
        return autor;
    }

    public void setAutor(TextField autor) {
        this.autor = autor;
    }

    public Button getCancelar() {
        return cancelar;
    }

    public void setCancelar(Button cancelar) {
        this.cancelar = cancelar;
    }

    public Button getCrear() {
        return crear;
    }

    public void setCrear(Button crear) {
        this.crear = crear;
    }

    public ChoiceBox<Editorial> getEditorial() {
        return editorial;
    }

    public void setEditorial(ChoiceBox<Editorial> editorial) {
        this.editorial = editorial;
    }

    public TextField getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(TextField fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public TextField getIsbn() {
        return isbn;
    }

    public void setIsbn(TextField isbn) {
        this.isbn = isbn;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TextField getTitulo() {
        return titulo;
    }

    public void setTitulo(TextField titulo) {
        this.titulo = titulo;
    }
}
