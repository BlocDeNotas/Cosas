package global.accesodatos;

import java.util.ArrayList;

import global.entidades.Editorial;
import global.presentacionconsola.ExcepcionCustom;
import global.presentacionconsola.MantenimientoLibros;

public class EditorialesDAOColeccion implements Crudable<Editorial> {

	private static EditorialesDAOColeccion instancia;
	private ArrayList<Editorial> editoriales = new ArrayList<Editorial>();

	private EditorialesDAOColeccion() {
		editoriales.add(new Editorial("O´RLY", 0));
		editoriales.add(new Editorial("Ye boi", 1));
	}

	public static EditorialesDAOColeccion getInstance() {
		if (instancia == null) {
			instancia = new EditorialesDAOColeccion();
		}
		return instancia;
	}

	public Editorial buscarPorNombre(String text) {
		for (Editorial editorial : editoriales) {
			if (editorial.getNombre().equals(text))
				return editorial;
		}
		return null;
	}

	@Override
	public Iterable<Editorial> obtenerTodos() {
		// TODO Auto-generated method stub
		try {
			throw new ExcepcionCustom("Método no implementado", MantenimientoLibros.gettPaneConsola());
		} catch (ExcepcionCustom e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Editorial obtenerPorId(long id) {
		// TODO Auto-generated method stub
		try {
			throw new ExcepcionCustom("Método no implementado", MantenimientoLibros.gettPaneConsola());
		} catch (ExcepcionCustom e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertar(Editorial objeto) {
		// TODO Auto-generated method stub
		try {
			throw new ExcepcionCustom("Método no implementado", MantenimientoLibros.gettPaneConsola());
		} catch (ExcepcionCustom e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modificar(Editorial objeto, long id) {
		// TODO Auto-generated method stub
		try {
			throw new ExcepcionCustom("Método no implementado", MantenimientoLibros.gettPaneConsola());
		} catch (ExcepcionCustom e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean borrar(long id) {
		// TODO Auto-generated method stub
		try {
			throw new ExcepcionCustom("Método no implementado", MantenimientoLibros.gettPaneConsola());
		} catch (ExcepcionCustom e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean asignar(Iterable<Editorial> lista) {
		// TODO Auto-generated method stub
		try {
			throw new ExcepcionCustom("Método no implementado", MantenimientoLibros.gettPaneConsola());
		} catch (ExcepcionCustom e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
