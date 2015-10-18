package programacionDinamica;

import java.io.FileNotFoundException;
import java.util.Vector;


import lecturasFichero.FicheroAMultidimensional;
import main.SolucionViajante;

/**
 * Practicas Algoritmia Basica - Programacion dinamica
 * El problema del viajante de comercio.
 * 
 * Clase SolucionDinamica. La clase crea un objeto SolucionDinamica
 * que se encarga de solucionar el problema del viajante de comercio
 * con programacion dinamica.
 * 
 * @author Fernando Aliaga
 *
 */
public class SolucionDinamica implements SolucionViajante{

	/**
	 * Tabla multidimensional que representa las aristas del grafo
	 */
	static int[][] grafo;
	/**
	 * Vector que almacena las distancias ya conocidas
	 */
	static Vector<NodoGtab> gtab;


	/**
	 * Metodo constructor de objetos 'SolucionDinamica'.
	 * @param rutaF ruta del fichero de texto que desbribe el grafo
	 * @throws FileNotFoundException
	 */
	public SolucionDinamica(String rutaF) throws FileNotFoundException{
		grafo = new FicheroAMultidimensional(rutaF).leerFichero();
		gtab = new Vector<NodoGtab>();
	}


	/**
	 * Metodo recursivo que devuelve la longitud del camino minimo 
	 * desde 'i' hasta el nodo 0 que pasa por cada vertice del 
	 * ConjuntoS 's'.
	 * @param i nodo 'i'
	 * @param s ConjuntoS 's'
	 * @return la longitud del camino minimo desde 'i' hasta el 
	 * nodo 0 que pasa por cada vertice del ConjuntoS 's'.
	 */
	public int g(int i, ConjuntoS s){
		int masCorto;
		int distancia;
		int j;
		int indice;
		/*
		 * Iniciamos el algoritmo recursivo
		 */
		if (s.esVacio()){
			/*
			 * Añadimos el un nuevo nodoGtab a la tabla gtab para poder realizar
			 * la demostracion completa del algoritmo
			 */
			indice = gtab.indexOf(new NodoGtab(i,s,-1));
			if(indice==-1){
				gtab.add(new NodoGtab(i,s,grafo[i][0]));
			}
			return grafo[i][0];
		}
		else {
			/*
			 * Buscamos la combinacion [i,S] en gtab.
			 */
			indice = gtab.indexOf(new NodoGtab(i,s,-1));
			if(indice!=-1){
				return gtab.get(indice).getDistancia();
			}
			else {
				/*
				 * Representamos el valor infinito con '-1'
				 * ya que todas las distancias entre nodos son 
				 * enteras positivas.
				 */
				masCorto = -1;
				for (j=0; j<=s.numNodos()-1; j++){
					int nodoJ = s.getNodo(j);
					ConjuntoS aux = new ConjuntoS(s.getV());
					aux.eliminarNodo(nodoJ);
					distancia = grafo[i][nodoJ] + g(nodoJ,aux);
					if(distancia<masCorto || masCorto==-1){
						masCorto = distancia;
					}
				}
				gtab.add(new NodoGtab(i,s,masCorto));
				return masCorto;
			}
		}
	}

	/**
	 * Imprime por pantalla la tabla de distancias.
	 */
	public static void imprimirGtab(){
		System.out.println("Caminos minimos entre nodos 'i' y conjuntos 'S':");
		for (int i=0; i<grafo.length; i++){
			for (int j=0; j<gtab.size(); j++){
				if(gtab.get(j).getS().numNodos()==i){
					System.out.println(gtab.get(j).toString());
				}
			}
		}
		System.out.println();
	}

	/**
	 * Imprime por pantalla el grafo.
	 */
	public static void imprimirGrafo(){
		System.out.println("Grafo:");
		for (int i=0; i<grafo.length; i++){
			System.out.printf("|");
			for (int j=0; j<grafo.length; j++){
				System.out.printf("%3d ",grafo[i][j]);
			}
			System.out.printf("|\n");
		}
		System.out.println();
	}

	/**
	 * Implementacion del metodo 'resolverViajante' del interfaz 
	 * 'SolucionViajante'. Resuelve el problema mediante programacion
	 * dinamica. Imprime los resultados por pantalla.
	 */
	public void resolverViajante(){
		/*
		 * Calculamos los tiempos de ejcucion.
		 */
		long tiempo_inicio, tiempo_final;
		tiempo_inicio = System.currentTimeMillis();
		/*
		 * Inicio algoritmo de busqueda
		 */
		ConjuntoS s = new ConjuntoS();
		for(int i=1; i<=grafo[0].length-1; i++){
			s.anadirNodo(i);
		}
		int distanciaMenor = g(0,s);
		/*
		 * Fin algoritmo de busqueda
		 */
		tiempo_final = System.currentTimeMillis();
		System.out.println("Demostracion solucion dinamica");
		System.out.println("------------------------------");
		imprimirGrafo();
		imprimirGtab();
		System.out.println("Menor distancia encontrada = " + distanciaMenor);
		System.out.println("Tiempo de ejecucion = " + ( tiempo_final - tiempo_inicio ) + " milisegundos");
	}


}
