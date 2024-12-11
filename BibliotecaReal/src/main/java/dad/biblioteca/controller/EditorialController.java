package dad.biblioteca.controller;

import dad.biblioteca.modelo.*;
import dad.biblioteca.persistencia.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class EditorialController implements Initializable {

    @FXML
    private Button Añadir;

    @FXML
    private Button Eliminar;

    @FXML
    private TableColumn<Editorial, String> nombreTable;

    @FXML
    private BorderPane root;

    @FXML
    private TextField textEditorial;

    @FXML
    private TableView<Editorial> tableNombreReal;

    private Biblioteca bibliotecaReal;

    private ObservableList<Editorial> editorialList = FXCollections.observableArrayList();

    @FXML
    void onAñadirAction(ActionEvent event) {
        String editorialNueva = textEditorial.getText().trim();
        if (editorialNueva.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Añadir Editorial");
            alert.setHeaderText("Debe especificar un nombre para la editorial");
            alert.showAndWait();
        } else {
            Editorial nuevaEditorial = new Editorial(new SimpleStringProperty(editorialNueva));
            editorialList.add(nuevaEditorial);
            File file = new File("biblioteca.xml");
            try {
                Biblioteca biblioteca = Persistencia.leer(file);
                if (biblioteca != null) {
                    biblioteca.getEditoriales().add(nuevaEditorial);
                    Persistencia.guardar(biblioteca, file);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
        Editorial selected = tableNombreReal.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (bibliotecaReal != null) {
                try {
                    editorialList.remove(selected);
                    bibliotecaReal.getEditoriales().remove(selected);
                    Persistencia.guardar(bibliotecaReal, new File("biblioteca.xml"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No se ha cargado la biblioteca.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eliminar Editorial");
            alert.setContentText("Debe seleccionar una editorial");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombreTable.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tableNombreReal.setItems(editorialList);

        File file = new File("biblioteca.xml");
        try {
            bibliotecaReal = Persistencia.leer(file);
            if (bibliotecaReal != null) {
                editorialList.addAll(bibliotecaReal.getEditoriales());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Button getAñadir() {
        return Añadir;
    }

    public void setAñadir(Button añadir) {
        Añadir = añadir;
    }

    public Button getEliminar() {
        return Eliminar;
    }

    public void setEliminar(Button eliminar) {
        Eliminar = eliminar;
    }

    public TableColumn<Editorial, String> getNombreTable() {
        return nombreTable;
    }

    public void setNombreTable(TableColumn<Editorial, String> nombreTable) {
        this.nombreTable = nombreTable;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TextField getTextEditorial() {
        return textEditorial;
    }

    public void setTextEditorial(TextField textEditorial) {
        this.textEditorial = textEditorial;
    }

    public TableView<Editorial> getTableNombreReal() {
        return tableNombreReal;
    }

    public void setTableNombreReal(TableView<Editorial> tableNombreReal) {
        this.tableNombreReal = tableNombreReal;
    }

    public ObservableList<Editorial> getEditorialList() {
        return editorialList;
    }

    public void setEditorialList(ObservableList<Editorial> editorialList) {
        this.editorialList = editorialList;
    }
}
