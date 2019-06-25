package aves.tropicales;

import aves.Ave;

public class Loro extends Ave{
	private char region;

	public Loro(char sexo, int edad, char region) {
		super(region, edad);
		this.region = region;
	}
	
	public void deDondeEres() {
		switch ((""+region).toLowerCase()) {
		case "n":
			System.out.println("Norte");
			break;
		case "s":
			System.out.println("sur");
			break;
		case "e":
			System.out.println("este");
			break;
		case "o":
			System.out.println("oeste");
			break;

		default:
			break;
		}
	}

	public char getRegion() {
		return region;
	}

	public void setRegion(char region) {
		this.region = region;
	}

	@Override
	public void cantar() {
		System.out.println("Pio pio loro bonito.");
	}

	
	
	
}
