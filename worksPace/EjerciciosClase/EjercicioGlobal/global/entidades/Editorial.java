package global.entidades;

public class Editorial {
	private String nombre;
	private long id;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Editorial(String nombre, long id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	public Editorial() {
		super();
	}
}
