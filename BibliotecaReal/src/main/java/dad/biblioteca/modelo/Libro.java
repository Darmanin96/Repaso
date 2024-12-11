package dad.biblioteca.modelo;

import jakarta.xml.bind.annotation.*;
import javafx.beans.property.*;

import java.util.Date;

@XmlType
public class Libro {

	private SimpleStringProperty titulo = new SimpleStringProperty(this, "titulo");
	private SimpleStringProperty autor = new SimpleStringProperty(this, "autor");
	private SimpleStringProperty isbn = new SimpleStringProperty(this, "isbn");
	private SimpleObjectProperty<Date> fechaPublicacion = new SimpleObjectProperty<>(this, "fechaPublicacion");
	private SimpleObjectProperty<Editorial> editorial = new SimpleObjectProperty<>(this, "editorial");

	@XmlElement
	public String getTitulo() {
		return titulo.get();
	}

	public SimpleStringProperty tituloProperty() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo.set(titulo);
	}

	@XmlElement
	public String getAutor() {
		return autor.get();
	}

	public SimpleStringProperty autorProperty() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor.set(autor);
	}

	@XmlElement
	public String getIsbn() {
		return isbn.get();
	}

	public SimpleStringProperty isbnProperty() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}

	@XmlElement
	public Date getFechaPublicacion() {
		return fechaPublicacion.get();
	}

	public SimpleObjectProperty<Date> fechaPublicacionProperty() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion.set(fechaPublicacion);
	}

	@XmlElement
	public Editorial getEditorial() {
		return editorial.get();
	}

	public SimpleObjectProperty<Editorial> editorialProperty() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial.set(editorial);
	}
}
