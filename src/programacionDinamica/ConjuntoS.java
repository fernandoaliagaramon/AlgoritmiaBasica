package programacionDinamica;

import java.util.Vector;

/**
 * Practicas Algoritmia Basica - Programacion dinamica
 * El problema del viajante de comercio.
 * 
 * Clase ConjuntoS. La clase crea objetos 'ConjuntoS', los cuales estan
 * definidos como un vector de identificadores de nodos del grafo.
 * 
 * @author Fernando Aliaga
 *
 */
public class ConjuntoS {

	/**
	 * Vector que almacena los nodos del conjunto
	 */
	private Vector<Integer> v;

	/**
	 * Metodo constructor de objetos ConjuntoS
	 */
	public ConjuntoS(){
		v = new Vector<Integer>();
	}

	/**
	 * Metodo constructor de objetos ConjuntoS.
	 * @param vector Vector de nodos
	 */
	public ConjuntoS(Vector<Integer> vector){
		v = new Vector<Integer>();
		for (int i=0; i<vector.size(); i++){
			v.add(vector.elementAt(i).intValue());
		}
	}

	/**
	 * Devuelve el nodo almacenado en el indice dado
	 * @param indice indice del nodo
	 * @return nodo almacenado en el indice dado
	 */
	public int getNodo(int indice){
		return v.get(indice);
	}

	/**
	 * Añade un nodo al ConjuntoS
	 * @param nodo Nodo a añadir en el ConjuntoS
	 */
	public void anadirNodo(int nodo){
		v.add(nodo);
	}

	/**
	 * Devuelve el vector de identificadores de nodos
	 * @return vector de identificadores de nodos
	 */
	public Vector<Integer> getV (){
		return this.v;
	}

	/**
	 * Elimina el nodo con identificador 'nodo'
	 * @param nodo identificador del nodo a eliminar
	 */
	public void eliminarNodo(int nodo){
		v.remove(v.indexOf(nodo));
	}

	/**
	 * Devuelve la pertenencia o no de un nodo al ConjuntoS
	 * @param nodo Nodo a buscar
	 * @return true si pertenece al conjunto, false si no pertenece
	 */
	public boolean estaNodo(int nodo){
		return v.contains(nodo);
	}

	/**
	 * Devuelve el numero de nodos del ConjuntoS
	 * @return numero de nodos del COnjuntoS
	 */
	public int numNodos(){
		return v.size();
	}

	/**
	 * Devuelve una cadena de caracteres que describe al ConjuntoS
	 */
	public String toString(){
		return v.toString();
	}

	/**
	 * Indica si el ConjuntoS es vacio
	 * @return true si pertenece el conjunto es vacio, false si no lo es
	 */
	public boolean esVacio(){
		return (v.size()==0);
	}

	/**
	 * El metodo redefine la implementacion del metodo de su mismo nombre.
	 * Dos objetos 'ConjuntoS' los consideramos iguales si tienen los mismos
	 * identificadores de nodos a lo largo de sus vectores.
	 * Este metodo nos ayudara a realizar comparaciones en los objetos 'NodoGtab'
	 */
	public boolean equals(Object a){
		ConjuntoS s = (ConjuntoS)a;
		/*
		 * Comprobamos que los dos conjuntos son de la misma
		 * longitud.
		 */
		if(!(this.numNodos()==s.numNodos())){
			return false;
		}
		int nodo;
		/*
		 * Comprobamos que los conjuntos tienen los mismos elementos.
		 * Para realizar dicha comprobación recorremos un conjunto y
		 * verificamos que todos sus nodos estan contenidos en el otro
		 * conjunto.
		 */
		for(int i=0; i<this.numNodos(); i++){
			nodo = s.getNodo(i);
			if(!v.contains(nodo)){
				return false;
			}
		}
		return true;
	}

}
