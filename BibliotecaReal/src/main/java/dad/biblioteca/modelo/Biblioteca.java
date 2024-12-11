package dad.biblioteca.modelo;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@XmlRootElement
@XmlType
public class Biblioteca {
	private List<Libro> libros;
	private List<Editorial> editoriales;

	public Biblioteca() {
		libros = new ArrayList<Libro>();
		editoriales = new ArrayList<Editorial>();
	}
	
	@XmlElement
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@XmlElement
	public List<Editorial> getEditoriales() {
		return editoriales;
	}

	public void setEditoriales(List<Editorial> editoriales) {
		this.editoriales = editoriales;
	}

}
