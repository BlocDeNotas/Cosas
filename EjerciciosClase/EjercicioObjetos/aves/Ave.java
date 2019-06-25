package aves;

import ej2.datosPersonales;

public abstract class Ave {
	protected char sexo;
	protected int edad, tamano;
	private datosPersonales nombres; 
	public Ave(char sexo, int edad) {
		super();
		this.sexo = sexo;
		this.edad = edad;
	}
	
	public void quienSoy() {
		System.out.println("Sexo: "+sexo+" edad: "+edad);
	}
	
	public abstract void cantar();

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public datosPersonales getNombres() {
		return nombres;
	}

	public void setNombres(datosPersonales nombres) {
		this.nombres = nombres;
	}
	
}