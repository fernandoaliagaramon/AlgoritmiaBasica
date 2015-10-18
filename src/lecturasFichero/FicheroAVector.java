package lecturasFichero;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import algoritmoVoraz.Arista;




public class FicheroAVector {
	
	private String ruta;
	private int numNodos;
	
	public FicheroAVector(String ruta){
		this.ruta = ruta;
		numNodos = 0;
	}
	
	public ArrayList<Arista> leerFichero() throws FileNotFoundException{
		Scanner sc = new Scanner(new File(this.ruta));
		ArrayList<Arista> grafo = new ArrayList<Arista>();
		String linea = "";
		Scanner scLinea=null;
		int i=1;
		int j=1;
		/*
		 * Calculamos las dimensiones del array.
		 */
		while(sc.hasNextLine()) {
			linea = sc.nextLine();
			scLinea = new Scanner (linea);
			while (scLinea.hasNextInt()){
				int peso = scLinea.nextInt();
				if(peso>0){
					grafo.add(new Arista(i,j,peso));
				}
				j++;
			}
			j=1;
			i++;
		}
		/*
		 * Definimos el numero de nodos.
		 */
		this.numNodos=i-1;
		/*
		 * Cerramos los objetos de tipo Scanner.
		 */
		sc.close();
		scLinea.close();
		return grafo;
	}
	
	public int numNodos(){
		return this.numNodos;
	}

}
