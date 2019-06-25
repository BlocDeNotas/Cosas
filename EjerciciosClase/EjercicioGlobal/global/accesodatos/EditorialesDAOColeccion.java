package global.accesodatos;

import java.util.ArrayList;

import global.entidades.Editorial;

public class EditorialesDAOColeccion implements Crudable<Editorial>{
	
	private static EditorialesDAOColeccion instancia;
	private ArrayList<Editorial> editoriales = new ArrayList<Editorial>();
	
	private EditorialesDAOColeccion() {
		editoriales.add(new Editorial("O´RLY", 0));
		editoriales.add(new Editorial("Ye boi", 1));
	}
	
	public static EditorialesDAOColeccion getInstance() {
		if(instancia == null) {
			instancia = new EditorialesDAOColeccion();
		}
		
		return instancia;
	}
	
	public Editorial buscarPorNombre(String text) {
		for (Editorial editorial : editoriales) {
			if(editorial.getNombre().equals(text)) return editorial;
		}
		return null;
	}

	@Override
	public Iterable<Editorial> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Editorial obtenerPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Editorial objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Editorial objeto, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrar(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean asignar(Iterable<Editorial> lista) {
		// TODO Auto-generated method stub
		return false;
	}
}
