package Cosas;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class utiles {
	static Scanner pato = new Scanner(System.in);
	
	public static int LeerInt() {
		System.out.println("Dame un número int: ");
		return pato.nextInt();
	}
	
	public static String LeerString() {
		System.out.println("Dame una String: ");
		return pato.next();
	}
	
	public char LeerChar() {
		System.out.println("Dame un Char: ");
		return pato.next().charAt(0);
	}
	
	public static boolean EsBisiesto(int anio) {
		GregorianCalendar calendar = new GregorianCalendar();
		if (calendar.isLeapYear(anio)) {return true;}
		else {return false;}
	}
	
	public static String Separar(String delim, int num, String fecha) {
		String[] palabrasSeparadas = fecha.split(delim);
		return palabrasSeparadas[num];
	}
	
	public static long EntreFechas(String fecha1, String fecha2) {
		long acum = 0;
		int[] anio = {Integer.parseInt(Separar("/", 2, fecha1)), Integer.parseInt(Separar("/", 2, fecha2)) }; //Parse al string que devuelve Separar
		int[] mes = {Integer.parseInt(Separar("/", 1, fecha1)), Integer.parseInt(Separar("/", 1, fecha2))};
		int[] dia = {Integer.parseInt(Separar("/", 0, fecha1)), Integer.parseInt(Separar("/", 0, fecha2))};
		int mayorint1 = (FechaMayor(anio[0], mes[0], dia[0], anio[1], mes[1], dia[1])) ? 1 : 0; // int Intpato = (booleano) ? 1 : 0; <- le da valor a la int 1 o 0 según el booleano
		int menorint = 1;
		if (mayorint1 == 1) { 
			menorint = 0;
		}
			
		int[] diasmes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31,30,31};
		
		if (EsBisiesto(mes[mayorint1])) {
			diasmes[2] = 29;
		}
		
		while (anio[mayorint1] > anio[menorint] || mes[mayorint1] > mes[menorint] || dia[mayorint1] > dia[menorint]) {
			dia[menorint]+=1;
			acum+=1;
			//System.out.println("Dia: "+dia[menorint]+" mes: "+mes[menorint]+" año: "+anio[menorint]);
			
			if ((diasmes[mes[menorint]]) < dia[menorint]) {
				dia[menorint] = 1;
				mes[menorint] += 1;
				
				if (mes[menorint] >= 12) { 
					mes[menorint] = 1;
					anio[menorint] += 1;
					
					if (EsBisiesto(mes[menorint])) {
						diasmes[2] = 29;
					} else {
						diasmes[2] = 28;
					}
				}
				
			}

		}
		return acum;
	}
	
	public static boolean EsNumero(String cosa1) {
		boolean dev = false;
		if (cosa1.matches("[0-9]*")) {
			dev = true;
		}
		return dev;
	}
	
	public static boolean FechaMayor(int anio1, int mes1, int dia1, int anio2, int mes2, int dia2) {
		if (anio2 >= anio1) {
			javac
			if (mes2 >= mes1) {
				if (dia2 > dia1) {
					return true;
				} else {
					if (dia2 == dia1) {
						new FileWriter(new OutputStreamWriter(null));
						System.out.println("Las fechas son iguales.");
						return false;
					} else {
						return false;							
					}
				}
			} else {
				return false;				
			}
		} else {
			return false;
		}
		
	}
}
