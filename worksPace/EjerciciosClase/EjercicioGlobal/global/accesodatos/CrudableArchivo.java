package global.accesodatos;

public interface CrudableArchivo<T> {
	public boolean cargar();
	public boolean guardar();
}
