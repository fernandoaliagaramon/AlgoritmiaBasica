package algoritmoVoraz;

/**
 * Practicas Algoritmia Basica - Algoritmo voraz
 * El problema del viajante de comercio.
 * 
 * Clase Arista. La clase arista nos permite crear objetos
 * del tipo arista, los cuales contendr�n un nodo salida,
 * un nodo llegada y la distancia de la arista que une los
 * dos nodos.
 * 
 * @author Fernando Aliaga Ramon
 */
public class Arista {
	/**
	 * Identificador Nodo salida
	 */
	private int a;
	/**
	 * Identificador Nodo llegada
	 */
	private int b;
	/**
	 * Peso de la arista
	 */
	private int distancia;

	/**
	 * M�todo constructor de objetos Arista
	 * @param x Identificador Nodo salida
	 * @param y Identificador Nodo llegada
	 * @param d Peso de la arista
	 */
	public Arista(int x, int y, int d){
		a=x;
		b=y;
		distancia=d;
	}

	/**
	 * Imprime por pantalla una l�nea con los parametros
	 * de la arista.
	 */
	void print() {
		System.out.println("("+a+","+b+") = "+distancia);
	}

	/**
	 * M�todo que devuelve el identificador del nodo salida.
	 * @return  identificador del nodo salida
	 */
	public int i_arista() {
		return a;
	}

	/**
	 * M�todo que devuelve el identificador del nodo llegada.
	 * @return  identificador del nodo llegada
	 */
	public int f_arista() {
		return b;
	}    

	/**
	 * M�todo que devuelve el peso de la arista.
	 * @return peso de la arista
	 */
	public int distancia() {
		return distancia;
	}

}