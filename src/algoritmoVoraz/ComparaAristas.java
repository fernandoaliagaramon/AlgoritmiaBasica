package algoritmoVoraz;
import java.util.Comparator;


/**
 * Practicas Algoritmia Basica - Algoritmo voraz
 * El problema del viajante de comercio.
 * 
 * Clase ComparaArista. La clase ComparaAristas implementa
 * el comparador entre dos objetos de tipo Arista.
 * 
 * @author Fernando Aliaga Ramon
 */
public class ComparaAristas implements Comparator<Arista> {

	@Override
	public int compare(Arista o1, Arista o2) {
		int w_arista1 = ((Arista)o1).distancia();
		int w_arista2 = ((Arista)o2).distancia();
		if (w_arista1 == w_arista2)
			return (0);
		if (w_arista1 < w_arista2)
			return (-1);
		return (1);
	}



}