package programacionDinamica;


/**
 * Practicas Algoritmia Basica - Programacion dinamica
 * El problema del viajante de comercio.
 * 
 * Clase NodoGtab. La clase crea objetos 'NodoGtab', los cuales estan
 * definidos como un entero 'i', un objeto ConjuntoS y una distancia.
 * En resumen, un NodoGtab es la longitud del camino minimo desde 'i'
 * hasta el nodo 0 que pasa por cada vertice del ConjuntoS.
 * 
 * @author Fernando Aliaga Ramon
 */
public class NodoGtab {
	/**
	 * Nodo 'i'
	 */
	private int i;
	/**
	 * Conjunto de nodos por los que debe pasar el camino minimo
	 */
	private ConjuntoS S;
	/**
	 * Distancia del camino minimo
	 */
	private int distancia;

	/**
	 * Metodo constructor de objetos 'NodoGtab'
	 * @param i Nodo 'i'
	 * @param S Conjunto de nodos por los que debe pasar el camino minimo
	 * @param distancia Distancia del camino minimo
	 */
	public NodoGtab(int i, ConjuntoS S, int distancia){
		this.i=i;
		this.S=S;
		this.distancia=distancia;
	}

	/**
	 * Define la distancia del objeto 'NodoGtab' 
	 * @param distancia Distancia del camino minimo
	 */
	public void setDistancia(int distancia){
		this.distancia = distancia;
	}

	/**
	 * Devuelve la distancia del objeto 'NodoGtab' 
	 * @return Distancia del camino minimo
	 */
	public int getDistancia(){
		return this.distancia;
	}

	/**
	 * Devuelve el valor del nodo 'i'
	 * @return nodo 'i'
	 */
	public int getI(){
		return this.i;
	}

	/**
	 * Devuelve el ConjuntoS del objeto 'NodoGtab'
	 * @return ConjuntoS
	 */
	public ConjuntoS getS(){
		return this.S;
	}

	/**
	 * Devuelve una cadena de caracteres que describe el 'NodoGtab'
	 */
	public String toString(){
		return ("g(" + (i) + "," + S.toString() + ") = " + distancia);
	}

	/**
	 * El metodo redefine la implementacion del metodo de su mismo nombre.
	 * Dos objetos 'NodoGtab' los consideramos iguales si tienen el mismo
	 * nodo 'i' y el mismo ConjuntoS, ignorando asi la distancia.
	 * Este metodo nos ayudara a realizar busquedas en tablas de 'NodoGtab'
	 */
	public boolean equals(Object a){
		/*
		 * Para saber que dos nodos son iguales comprobamos
		 * tanto sus valores 'i' como sus conjuntos 'S'.
		 * Si ambos valores son iguales podemos afirmar que 
		 * los dos nodos son iguales (aunque tengan distanta
		 * distancia).
		 * Este metodo lo usaremos para realizar busquedas en
		 * vectores de objetos 'NodoGtab'.
		 */
		NodoGtab nodo = (NodoGtab)a;
		if(this.i==nodo.getI() &&  this.S.equals(nodo.getS())){
			return true;
		}
		else {
			return false;
		}
	}

}

