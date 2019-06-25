package aves.pajaros;

import aves.Ave;

public class Canario extends Ave{
	private int tamano;
	
	public Canario(char sexo, int edad, int tamano) {
		super(sexo, tamano);
		this.edad = edad;
	}
	
	public void altura() {
		if(this.tamano>30) {
			System.out.println("Alto");
		} else if(this.tamano >=30) {
			System.out.println("Mediano");
		} else {
			System.out.println("Pequeno");
		}
	}


	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	@Override
	public void cantar() {
		System.out.println("Pio-pio soy un Canario");
		
	}
	
}
