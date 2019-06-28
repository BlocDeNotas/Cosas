package Sockets;

import java.util.ArrayList;

import FisicasComunes.PlayerComun;

public class Usuario {
	private String nombre, apellido, edad, contrasena, nUsuario;
	private long id = (long) (Math.random() * 1000);
	private PlayerComun p;

	public PlayerComun getP() {
		return p;
	}

	public void setP(PlayerComun p) {
		this.p = p;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", contrasena=" + contrasena
				+ ", nUsuario=" + nUsuario + "]";
	}

	public Usuario(ArrayList<ArrayList<String>> arrayList) {
		super();
		this.nombre = arrayList.get(0).get(0);
		this.apellido = arrayList.get(0).get(1);
		System.out.println("Usuario " + nombre + " logeado");
		/*
		 * this.edad = temp[2]; this.contrasena = temp[3]; this.nUsuario = temp[4];
		 */
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getnUsuario() {
		return nUsuario;
	}

	public void setnUsuario(String nUsuario) {
		this.nUsuario = nUsuario;
	}
}
