package global.accesodatos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import global.entidades.Editorial;
import global.entidades.Libro;

public class LibrosDAOColeccion implements Crudable<Libro,Editorial> {
	//Data
	//Access
	//Object
	
	// Inicio patrón Singleton
	private LibrosDAOColeccion() {
		editoriales.add(new Editorial("O´RLY", 0));
		editoriales.add(new Editorial("Ye boi", 1));
		
	}
	private ObjectMapper mapper = new ObjectMapper();
	private static LibrosDAOColeccion instancia;
	
	public static LibrosDAOColeccion getInstance() {
		if(instancia == null) {
			instancia = new LibrosDAOColeccion();
		}
		
		return instancia;
	}
	// Fin patrón Singleton
	
	private ArrayList<Libro> libros = new ArrayList<Libro>();
	private ArrayList<Editorial> editoriales = new ArrayList<Editorial>();
	
	@Override
	public boolean cargarLibros() {
		boolean r = false;
		try {
			File file = new File(".\\libros.json");
			libros = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Libro.class));
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
	public boolean guardarLibros() {
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
            mapper.writeValue(file, libros);
        } catch (IOException e) {
        	r = false;
            e.printStackTrace();
        }
        return r;
	}
	
	@Override
	public Iterable<Libro> obtenerTodos() {
		return libros;
	}

	@Override
	public Libro obtenerPorId(long id) {
		Libro l = null;
		for (Libro libro : libros) {
			if(libro.getId()==id) {
				l = libro;
			}
		}
		return l;
	}

	@Override
	public boolean insertar(Libro libro) {
		libros.add(libro);
		return true;
	}

	@Override
	public boolean modificar(Libro libro, long id) {
		boolean r = false;
		if(libros.indexOf(obtenerPorId(id))!=-1){
			libros.set(libros.indexOf(obtenerPorId(id)),libro);
			r = true;
		} 
		return r;
	}
	
	@Override
	public boolean borrar(long id) {
		boolean r = false;
		ArrayList<Integer> borrarbles = new ArrayList<Integer>();
		for (Libro libro : this.libros) {
			if(libro.getId()==id) {
				borrarbles.add(this.libros.indexOf(libro));
			}
		}
		for (Integer num : borrarbles) {
			this.libros.remove((int)num);
			r = true;
		}
		return r;
	}

	@Override
	public Editorial getEditorialPorNombre(String text) {
		for (Editorial editorial : editoriales) {
			if(editorial.getNombre().equals(text)) return editorial;
		}
		return null;
	}

	@Override
	public boolean cargarEditoriales() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean guardarEditoriales() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertarEditorial() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarEditorial() {
		// TODO Auto-generated method stub
		return false;
	}

}
