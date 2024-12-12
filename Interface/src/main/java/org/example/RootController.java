package org.example;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.model.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class RootController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private Label ranking;
    @FXML
    private Button save;
    @FXML
    private Slider slider;
    @FXML
    private ListView<Libro> tablaLibros;

    public RootController() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeListView();
        addSliderListener();
        addListViewListener();
    }

    private void initializeListView() {
        tablaLibros.getItems().add(new Libro());
    }

    private void addSliderListener() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateRankingLabel();
        });
    }

    private void addListViewListener() {
        tablaLibros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateRankingLabel();
        });
    }

    private void updateRankingLabel() {
        Libro selectedLibro = tablaLibros.getSelectionModel().getSelectedItem();
        if (selectedLibro != null) {
            ranking.setText("Rank: " + slider.getValue());
        }
    }

    @FXML
    void onSaveAction(ActionEvent event) {
        // Lógica para el botón de guardar
    }

    // Getters y setters
    public Label getRanking() {
        return ranking;
    }

    public void setRanking(Label ranking) {
        this.ranking = ranking;
    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public ListView<Libro> getTablaLibros() {
        return tablaLibros;
    }

    public void setTablaLibros(ListView<Libro> tablaLibros) {
        this.tablaLibros = tablaLibros;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
