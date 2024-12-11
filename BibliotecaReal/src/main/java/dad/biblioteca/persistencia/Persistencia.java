package dad.biblioteca.persistencia;

import java.io.File;



import dad.biblioteca.modelo.Biblioteca;
import jakarta.xml.bind.*;

public class Persistencia {

	public static void guardar(Biblioteca biblio, File destino) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(biblio, destino);
	}
	
	public static Biblioteca leer(File origen) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Biblioteca) unmarshaller.unmarshal(origen);
	}
	
}
