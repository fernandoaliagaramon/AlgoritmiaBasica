package main;

import java.io.FileNotFoundException;
import programacionDinamica.SolucionDinamica;
import algoritmoVoraz.SolucionVoraz;
import fuerzaBruta.SolucionFuerzaBruta;


public class Main {
	static SolucionViajante s;
	public static void main (String [] args) {
		try{
			if(args[0].equals("fb")){
				s = new SolucionFuerzaBruta(args[1]);
				System.out.println("Calculando solucion mediante " +
						"algoritmo de fuerza bruta...");
			}
			else if(args[0].equals("av")){
				s = new SolucionVoraz(args[1]);
				System.out.println("Calculando solucion mediante " +
						"algoritmo voraz...");
			}
			else if(args[0].equals("pd")){
				s = new SolucionDinamica(args[1]);
				System.out.println("Calculando solucion mediante " +
						"algoritmo de programacion dinamica...");
			}
			else if(args[0].equals("rp")){
				s = new SolucionFuerzaBruta(args[1]);
				System.out.println("Calculando solucion mediante " +
						"algoritmo de ramificacion y poda...");
			}
			s.resolverViajante();
		}
		catch(FileNotFoundException ex){
			System.out.println("Error en el fichero de texto.");
			System.out.println("Uso: tsp -opt <nombre de fichero>");
		}
		catch(Exception ex2){
			System.out.println("Uso: tsp -opt <nombre de fichero>");
		}
	}
}
