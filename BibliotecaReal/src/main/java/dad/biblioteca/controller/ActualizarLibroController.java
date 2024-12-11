package dad.biblioteca.controller;

import dad.biblioteca.modelo.*;
import dad.biblioteca.persistencia.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class ActualizarLibroController implements Initializable {


    @FXML
    private Button Cancelar;

    @FXML
    private Button Guardar;

    @FXML
    private TextField autor;

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

    private Libro libro;

    @FXML
    void onCancelarAction(ActionEvent event) {

    }

    @FXML
    void onGuardarAction(ActionEvent event) {
        try {
            libro.setTitulo(titulo.getText());
            libro.setAutor(autor.getText());
            libro.setIsbn(isbn.getText());
            String fechaTexto = fechaPublicacion.getText();
            if (!fechaTexto.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);
                Date fecha = dateFormat.parse(fechaTexto);
                libro.setFechaPublicacion(fecha);
            } else {
                libro.setFechaPublicacion(null);
            }
            libro.setEditorial(editorial.getValue());
            File file = new File("biblioteca.xml");
            Biblioteca biblioteca = Persistencia.leer(file);
            Persistencia.guardar(biblioteca, file);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } catch (ParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("La fecha ingresada no tiene un formato válido (dd/MM/yyyy).");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No se pudo guardar el libro.");
            alert.showAndWait();
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLibro(Libro libro) {
        this.libro = libro;
        titulo.setText(libro.getTitulo());
        autor.setText(libro.getAutor());
        isbn.setText(libro.getIsbn());
        if (libro.getFechaPublicacion() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaTexto = dateFormat.format(libro.getFechaPublicacion());
            fechaPublicacion.setText(fechaTexto);
        } else {
            fechaPublicacion.setText("");
        }
        if (libro.getEditorial() != null) {
            editorial.setValue(libro.getEditorial());
        }
    }



    public Button getCancelar() {
        return Cancelar;
    }

    public void setCancelar(Button cancelar) {
        Cancelar = cancelar;
    }

    public Button getGuardar() {
        return Guardar;
    }

    public void setGuardar(Button guardar) {
        Guardar = guardar;
    }

    public TextField getAutor() {
        return autor;
    }

    public void setAutor(TextField autor) {
        this.autor = autor;
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
