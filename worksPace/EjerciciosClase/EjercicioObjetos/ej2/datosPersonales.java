package ej2;

public class datosPersonales {
	private String nombreAve, nombreDue�o;

	public datosPersonales(String nombreAve, String nombreDue�o) {
		super();
		this.nombreAve = nombreAve;
		this.nombreDue�o = nombreDue�o;
	}

	public String getNombreAve() {
		return nombreAve;
	}

	public void setNombreAve(String nombreAve) {
		this.nombreAve = nombreAve;
	}

	public String getNombreDue�o() {
		return nombreDue�o;
	}

	public void setNombreDue�o(String nombreDue�o) {
		this.nombreDue�o = nombreDue�o;
	}

	@Override
	public String toString() {
		return "datosPersonales [nombreAve=" + nombreAve + ", nombreDue�o=" + nombreDue�o + "]";
	}
	
	
}
