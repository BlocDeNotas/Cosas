package estrellasTv;

import aves.pajaros.Canario;
import aves.tropicales.Loro;
import ej2.datosPersonales;

public class Piolin extends Canario{
	private int numPeliculas;
	
	
	
	public Piolin(char sexo, int edad, int numPeliculas, int tamano) {
		super(sexo, numPeliculas, tamano);
		this.numPeliculas = numPeliculas;
	}

	public static void main(String[] args) {
		//Ej2
		//====================================
		Piolin p = new Piolin('m', 30, 1, 13);
		Loro l = new Loro('f', 10, 'n');
		l.deDondeEres();
		p.altura();
		p.setTamano(60);
		p.altura();
		
		//Ej3
		//====================================
		l.cantar();
		Piolin p1 = new Piolin('m', 30, 1, 13);
		Piolin p2 = new Piolin('f', 15, 10, 17);
		Piolin p3 = new Piolin('m', 5, 91, 60);
		Piolin[] piolines = {p1,p2,p3};
		piolines[0].setNombres(new datosPersonales("piolin1", "duenoPiolin1"));
		System.out.println("Datos del primer piolin: "+piolines[0].getNombres().toString());
	}


	public int getNumPeliculas() {
		return numPeliculas;
	}

	public void setNumPeliculas(int numPeliculas) {
		this.numPeliculas = numPeliculas;
	}
	
	@Override
	public void cantar() {
		System.out.println("Pio pio soy un piolin");
	}
	
}
