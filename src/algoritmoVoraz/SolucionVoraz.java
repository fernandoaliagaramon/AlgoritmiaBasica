package algoritmoVoraz;

import java.io.FileNotFoundException;
import java.util.*;


import lecturasFichero.FicheroAVector;
import main.SolucionViajante;



/**
 * Practicas Algoritmia Basica - Algoritmo voraz
 * El problema del viajante de comercio.
 * 
 * Clase Voraz. La clase Voraz nos permite ejecutar la solución
 * al problema del viajante de comercio mediante un algoritmo 
 * voraz. 
 * 
 * @author Fernando Aliaga Ramon
 */
public class SolucionVoraz implements SolucionViajante{

	/**
	 * Numero de nodos del Grafo 
	 */
	static int n_nodos;
	/**
	 * Array que contiene las aristas del grafo y sus longitudes
	 */
	private static ArrayList<Arista> grafo;
	/**
	 * Vector que almacena las aristas que componen la solucion.
	 */
	private static Vector<Arista> recorrido;
	/**
	 * Tabla de booleanos que almacena los nodos salida de las
	 * aristas que componen la solucion.
	 */
	private static boolean[] nodosSalida;
	/**
	 * Tabla de booleanos que almacena los nodos llegada de las
	 * aristas que componen la solucion.
	 */
	private static boolean[] nodosLlegada;
	/**
	 * Valor entero que acumula la distancia de las aristas que
	 * componen la solucion.
	 */
	private static int distancia= 0;
	/**
	 * Lista de identificadores de nodos que forman el ciclo 
	 * solucion. 
	 */
	private static Vector<Integer> ciclo;   

	/**
	 * Constructor de objetos Voraz. Inicializa los objetos
	 * utilizados en la heuristica.
	 * @param rutaFichero ruta del fichero donde tenemos la 
	 * matriz de aristas.
	 * @throws FileNotFoundException 
	 */
	public SolucionVoraz(String rutaFichero) throws FileNotFoundException{
		FicheroAVector fich = new FicheroAVector(rutaFichero);
		ArrayList<Arista> g = fich.leerFichero();
		n_nodos = fich.numNodos();
		nodosSalida = new boolean[n_nodos];
		nodosLlegada = new boolean[n_nodos];
		ciclo = new Vector<Integer>(n_nodos);   
		for (int i=0;i<n_nodos+1;i++){
			ciclo.addElement(0);
		}
		recorrido = new Vector<Arista>();
		grafo = new ArrayList<Arista>();  
		for (Iterator<Arista> x=g.iterator(); x.hasNext();){
			grafo.add(x.next());
		}
	}


	/**
	 * Imprime por pantalla una línea con los parametros
	 * de la arista.               
	 * @param c
	 */
	public static void print(Collection<Arista> c){
		System.out.println("Listado de aristas (orden creciente):");
		for (Iterator<Arista> x=c.iterator(); x.hasNext();) {    
			Arista a = x.next();
			a.print();
		}
		System.out.println();
	}  

	/**
	 * Metodo que devuelve un String con el ciclo solucion y
	 * la distancia recorrida
	 * @return String con el ciclo solucion y la distancia recorrida
	 */
	private static String printCiclo() {    
		String resultado;
		resultado = "Recorrido solucion: ";
		resultado = resultado + "(";
		for (Iterator<Integer> x=ciclo.iterator(); x.hasNext();) 
			resultado = resultado + x.next()+" ";       
		resultado = resultado +")\n";
		resultado = resultado + "Menor distancia encontrada = "+distancia;              
		return resultado;
	} 


	/**
	 * Metodo que resuelve la primera parte del problema del viajante de
	 * comercio. Crea un recorrido de aristas, el cual tendrá que ser 
	 * reordenado mas tarde por la funcion 'reconstruirCiclo'.
	 */
	private static void heuristica_voraz() {
		/*
		 * Ordenamos el vetor de las aristas en orden creciente
		 * en función del peso de cada arista
		 */
		Collections.sort(grafo, new ComparaAristas());
		/*
		 * Objeto de la clase 'RelacionEquivalencia' que nos permite
		 * controlar los posibles ciclo que se formen entre nodos.
		 */
		RelacionEquivalencia re = new RelacionEquivalencia(n_nodos);
		/*
		 * Entero que acumula la distancia de la solucion,
		 */
		distancia = 0;
		/*
		 * Iterador que recorre las aristas, las cuales deben de estar
		 * ordenadas de menor a mayor.      
		 */
		ListIterator<Arista>  lit = grafo.listIterator();    
		/*
		 * Inicializamos las tablas de booleanos 'nodosSalida'
		 * y 'nodosLlegada' con valor 'false' en todos sus
		 * registros
		 */
		Arrays.fill(nodosSalida,false);
		Arrays.fill(nodosLlegada,false);

		/*
		 * Numero de nodos visitados.
		 */
		int nodosVisitados = 0;
		Arista a;
		int u;
		int v;
		/*
		 * Repetir hasta que se hayan visitado todos los datos
		 * con las aristas solucion.
		 */
		while (nodosVisitados<n_nodos) {
			a = lit.next();
			u = a.i_arista();
			v = a.f_arista();

			/*
			 * Comprobar que los nodos salida y llegada no hayan sido
			 * añadidos a la solucion que dicho rol.
			 */
			if (!nodosSalida[u-1] && !nodosLlegada[v-1]) {
				/*
				 * Comprobar que no forman ciclo o que la arista es 
				 * la que cierra el recorrido solucion 
				 * (conecta nodo_salida con nodo_llegada).
				 */
				if(!re.formanCiclo(u, v) || nodosVisitados==(n_nodos-1)){
					/*
					 * Añadimos los nodos a la solucion.
					 */
					nodosSalida[u-1]=true;
					nodosLlegada[v-1]=true;
					nodosVisitados++;
					/*
					 * Añadimos la arista a la solucion.
					 */
					recorrido.addElement(a);
					distancia += a.distancia();
					/*
					 * Actualizamos las clase de equivalencia. 
					 */
					re.fusiona(u, v);
				}
			}
		}    
	} 

	/**
	 * Metodo que reconstruye la aristas encontradas mediante
	 * hueristica voraz. Rellena el vector 'ciclo' con los 
	 * identificadores de los nodos en orden.
	 */
	private static void reconstruirCiclo() {
		/*
		 * Inicializamos las variables a usar.
		 */
		int i=0;
		int u = recorrido.elementAt(0).i_arista();
		int v = recorrido.elementAt(0).f_arista();
		/*
		 * Añadimos los nodos de la primera arista de la solucion
		 */
		ciclo.setElementAt(u,i++);
		ciclo.setElementAt(v,i++); 
		/*
		 * Mientras que queden aristas en la solucion añadimos
		 * los nodos al ciclo solucion.
		 */
		while (i < recorrido.size()+1) {  
			int sig = buscar_siguiente(v);       
			/*
			 * Comprobamos si nuestro algoritmo encuentra la solucion
			 * Si ha fallado la heuristica aqui avisaremos del error.
			 */
			if (sig > 0) {
				/*
				 * Añadimos el nodo siguiente al ciclo solucion.
				 */
				ciclo.setElementAt(sig,i++);
				u = v;
				v = sig;
			} else {
				System.out.println("Error : no existe " +
						"el siguiente de "+v);         
				System.exit( 0 );
			}    
		} 
	}

	/**
	 * Metodo que busca en la tabla recorrido el siguiente nodo siguiendo
	 * @param a nodo de partida de la arista a buscar
	 * @return nodo llegada de la nueva arista del ciclo
	 */
	private static int buscar_siguiente(int a) {
		for (int i=0; i<recorrido.size();i++) {
			int u = recorrido.elementAt(i).i_arista();
			int v = recorrido.elementAt(i).f_arista();
			/*
			 * Si el nodo a es igual al nodo salida de la arista
			 * reconstruimos el ciclo con dicha arista (nodo llegada)
			 */
			if(a==u){
				return v;
			}
		}
		return (-1);
	}


	/**
	 * Metodo que se encarga de resolver el problema del viajante
	 * de comercio. Imprime por pantalla los resultados obtenidos.
	 */
	public void resolverViajante(){
		/*
		 * Calculamos los tiempos de ejcucion.
		 */
		long tiempo_inicio, tiempo_final;
		tiempo_inicio = System.currentTimeMillis();	
		/*
		 * Inicio algoritmo de busqueda 
		 * Aplicamos la heuristica voraz.
		 * Rellenamos la tabla de aristas solucion.
		 */
		heuristica_voraz();
		/*
		 * Reconstruimos el ciclo con las aristas solucion.
		 */
		reconstruirCiclo();
		/*
		 * Fin algoritmo de busqueda
		 */
		tiempo_final = System.currentTimeMillis();
		System.out.println("Demostracion solucion voraz");
		System.out.println("---------------------------");
		print(grafo);
		System.out.println("Aristas que componen la solucion: ");    
		for (Iterator<Arista> x=recorrido.iterator(); x.hasNext();){
			x.next().print();
		}
		System.out.println();
		System.out.println(printCiclo());
		System.out.println("Tiempo de ejecucion = " + ( tiempo_final - tiempo_inicio ) + " milisegundos");
	}

}