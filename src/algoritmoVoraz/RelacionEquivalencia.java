package algoritmoVoraz;
import java.util.Vector;

/**
 * Practicas Algoritmia Basica - Algoritmo voraz
 * El problema del viajante de comercio.
 * 
 * Clase RelacionEquivalencia. La clase arista nos permite crear 
 * objetos del tipo RelacionEquivalencia, los cuales representan
 * conjuntos de identificadores de los nodos de un grafo. Dicha clase
 * de equivalencia se usa en el algoritmo voraz para comprobar que no
 * haya ciclos en pasos intermedios de la solucion.
 * 
 * @author Fernando Aliaga Ramon
 */
public class RelacionEquivalencia {

	/**
	 * Vector de clases de equivalencia
	 */
	private Vector<Vector<Integer>> vector;

	/**
	 * Metodo constructor de objetos 'RelacionEquivalencia'
	 * @param numNodos numero de nodos iniciales
	 */
	public RelacionEquivalencia (int numNodos){
		this.vector = new Vector<Vector<Integer>> (numNodos);
		Vector<Integer> aux;
		for (int i=0; i<numNodos; i++){
			aux = new Vector<Integer>();
			aux.add(i+1);
			this.vector.add(aux);
		}
	}

	/**
	 * Imprime por pantalla el contenido del vector.
	 */
	public void pintar (){
		for (int i=0; i<vector.size(); i++){
			System.out.println(vector.get(i).toString());
		}
	}

	/**
	 * Fusiona las dos clases donde se encuentran 'NodoA' y 'NodoB'
	 * @param NodoA nodo cuya clase debe fusionarse
	 * @param NodoB nodo cuya clase debe fusionarse
	 */
	public void fusiona (int NodoA, int NodoB){
		if(vector.size()!=1){
			Vector<Integer> sumaClases = new Vector<Integer>();
			boolean encontrado = false;
			int i = 0;
			while(!encontrado){
				if(vector.get(i).contains(NodoA)){
					sumaClases.addAll(vector.get(i));
					vector.remove(i);
					encontrado=true;
				}
				else{
					i++;
				}
			}
			encontrado = false;
			i = 0;
			while(!encontrado){
				if(vector.get(i).contains(NodoB)){
					sumaClases.addAll(vector.get(i));
					vector.remove(i);
					vector.add(i, sumaClases);
					encontrado=true;
				}
				i++;
			}
		}
	}

	
	/**
	 * Devuelve true si una arista que conecta el 'NodoA' con el 
	 * 'NodoB' forma un ciclo dentro del grafo.
	 * @param NodoA arista A
	 * @param NodoB arista B
	 * @return true si una arista que conecta el 'NodoA' con el 
	 * 'NodoB' forma un ciclo dentro del grafo
	 */
	public boolean formanCiclo(int NodoA, int NodoB){
		boolean encontrado = false;
		int i = 0;
		while(!encontrado){
			if(vector.get(i).contains(NodoA)){
				encontrado=true;
				if(vector.get(i).contains(NodoB)){
					return true;
				}
			}
			else{
				i++;
			}
		}
		return false;
	}

	/**
	 * Devuelve el numero de clases almacenadas en el vector
	 * @return numero de clases almacenadas en el vector
	 */
	public int numClases(){
		return vector.size();
	}

}
