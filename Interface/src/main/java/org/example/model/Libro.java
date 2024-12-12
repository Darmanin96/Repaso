package org.example.model;

import javafx.beans.property.*;

public class Libro {

    private StringProperty nombre;


    public Libro(){
        nombre = new SimpleStringProperty(this,"nombre");

    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
}
