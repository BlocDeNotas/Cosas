package global.entidades;

public class Libro {
	private long id,ISBN;
	private String titulo, autor, descripcion, genero, edicion,descripcion2;
	private boolean isBorrado;
	private String fechaImpresion;
	private Editorial editorial;

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + "]";
	}

	public Libro(long id, long iSBN, String titulo, String autor, String descripcion, String genero, String edicion,
			Editorial editorial, boolean isBorrado, String fechaImpresion) {
		super();
		this.id = id;
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		this.genero = genero;
		this.edicion = edicion;
		this.editorial = editorial;
		this.isBorrado = isBorrado;
		this.fechaImpresion = fechaImpresion;
	}
	
	

	public Libro(long id, long iSBN, String titulo, String autor, String descripcion, String genero, String edicion,
			Editorial editorial, String descripcion2, boolean isBorrado, String fechaImpresion) {
		super();
		this.id = id;
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		this.genero = genero;
		this.edicion = edicion;
		this.editorial = editorial;
		this.descripcion2 = descripcion2;
		this.isBorrado = isBorrado;
		this.fechaImpresion = fechaImpresion;
	}

	public Libro() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public boolean isBorrado() {
		return isBorrado;
	}

	public void setBorrado(boolean isBorrado) {
		this.isBorrado = isBorrado;
	}

	public String getFechaImpresion() {
		return fechaImpresion;
	}

	public void setFechaImpresion(String fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public String getDescripcion2() {
		return descripcion2;
	}

	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}
	
	
	
}
