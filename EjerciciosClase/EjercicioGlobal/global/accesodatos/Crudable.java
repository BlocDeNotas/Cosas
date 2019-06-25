package global.accesodatos;

public interface Crudable<T> {
	// Create
	// Retrieve
	// Update
	// Delete
	
	public Iterable<T> obtenerTodos();
	public T obtenerPorId(long id);
	public boolean insertar(T objeto);
	public boolean modificar(T objeto, long id);
	public boolean borrar(long id);
	public T buscarPorNombre(String nombre);
	public boolean asignar(Iterable<T> lista);
	//public Editorial getEditorialPorNombre(String text);
}
