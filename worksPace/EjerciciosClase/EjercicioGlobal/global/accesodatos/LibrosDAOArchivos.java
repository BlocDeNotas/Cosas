package global.accesodatos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import global.entidades.Libro;

public class LibrosDAOArchivos implements CrudableArchivo<Libro> {
	private ObjectMapper mapper = new ObjectMapper();
	private static Crudable<Libro> daoLibros = LibrosDAOColeccion.getInstance();
	
	
	private LibrosDAOArchivos() {
	}
	
	
	private static LibrosDAOArchivos instancia;
	
	public static LibrosDAOArchivos getInstance() {
		if(instancia == null) {
			instancia = new LibrosDAOArchivos();
		}
		
		return instancia;
	}
	
	
	@Override
	public boolean cargar() {
		boolean r = false;
		try {
			File file = new File(".\\libros.json");
			ArrayList<Libro> libros = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Libro.class));
			daoLibros.asignar(libros);
			r = true;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	@Override
	public boolean guardar() {
		boolean r = true;
		File file = new File(".\\libros.json");
		if(file.exists()){
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        try {
            mapper.writeValue(file, (ArrayList<Libro>)daoLibros.obtenerTodos());
        } catch (IOException e) {
        	r = false;
            e.printStackTrace();
        }
        return r;
	}
}
