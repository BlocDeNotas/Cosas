package global.accesodatos;

import java.util.ArrayList;

import global.entidades.Libro;

public class LibrosDAOColeccion implements Crudable<Libro> {
	//Data
	//Access
	//Object
	
	// Inicio patrón Singleton
	private LibrosDAOColeccion() {
	}
	
	
	private static LibrosDAOColeccion instancia;
	
	public static LibrosDAOColeccion getInstance() {
		if(instancia == null) {
			instancia = new LibrosDAOColeccion();
		}
		
		return instancia;
	}
	// Fin patrón Singleton
	
	private ArrayList<Libro> libros = new ArrayList<Libro>();
	
	
	
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
	public boolean asignar(Iterable<Libro> lista) {
		libros = (ArrayList<Libro>) lista;
		return false;
	}

	@Override
	public Libro buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
