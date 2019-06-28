package com.mygdx.game.desktop;

public class Libro {
	// TODO: ISBN,
	private long id;
	private String titulo;

	public Libro(Long id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + "]";
	}

}
