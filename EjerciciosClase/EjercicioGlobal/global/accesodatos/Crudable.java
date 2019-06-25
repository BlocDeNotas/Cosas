package global.accesodatos;

import global.entidades.Editorial;

public interface Crudable<L,E> {
	// Create
	// Retrieve
	// Update
	// Delete
	
	public Iterable<L> obtenerTodos();
	public boolean cargarLibros();
	public boolean guardarLibros();
	public boolean cargarEditoriales();
	public boolean guardarEditoriales();
	public boolean insertarEditorial();
	public boolean borrarEditorial();
	public L obtenerPorId(long id);
	public boolean insertar(L objeto);
	public boolean modificar(L objeto, long id);
	public boolean borrar(long id);
	public Editorial getEditorialPorNombre(String text);
}
