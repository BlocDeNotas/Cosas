package ej2;

public class datosPersonales {
	private String nombreAve, nombreDueño;

	public datosPersonales(String nombreAve, String nombreDueño) {
		super();
		this.nombreAve = nombreAve;
		this.nombreDueño = nombreDueño;
	}

	public String getNombreAve() {
		return nombreAve;
	}

	public void setNombreAve(String nombreAve) {
		this.nombreAve = nombreAve;
	}

	public String getNombreDueño() {
		return nombreDueño;
	}

	public void setNombreDueño(String nombreDueño) {
		this.nombreDueño = nombreDueño;
	}

	@Override
	public String toString() {
		return "datosPersonales [nombreAve=" + nombreAve + ", nombreDueño=" + nombreDueño + "]";
	}
	
	
}
