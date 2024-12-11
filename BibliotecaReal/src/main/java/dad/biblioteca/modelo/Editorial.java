package dad.biblioteca.modelo;


import jakarta.xml.bind.annotation.*;
import javafx.beans.property.*;

@XmlType
public class Editorial {
	private SimpleStringProperty nombre = new SimpleStringProperty(this, "nombre");


	public Editorial(SimpleStringProperty nombre) {
		this.nombre = nombre;
	}

	public Editorial(){

	}

	@XmlElement

	public String getNombre() {
		return nombre.get();
	}

	public SimpleStringProperty nombreProperty() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
}
