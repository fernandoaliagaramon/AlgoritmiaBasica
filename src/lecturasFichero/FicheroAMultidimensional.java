package lecturasFichero;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FicheroAMultidimensional {
	
	private String ruta;
	
	public FicheroAMultidimensional(String rutaF){
		this.ruta = rutaF;
	}

	public int[][] leerFichero () throws FileNotFoundException{
		Scanner sc = new Scanner(new File(ruta));
		String linea = "";
		/*
		 * Calculamos las dimensiones del array.
		 */
        if(sc.hasNextLine()) {
            linea = sc.nextLine();
        }
        Scanner scLinea = new Scanner (linea);
        int numDatos = 0;
        while (scLinea.hasNextInt()){
        	numDatos++;
        	scLinea.nextInt();
        }
        /*
         * Rellenamos el array multidimensional con la
         * lectura de datos del fichero de texto.
         */
        int[][] M = new int[numDatos][numDatos];
        sc = new Scanner(new File(ruta));
        for(int i=0; i<numDatos; i++){
        	for(int j=0; j<numDatos; j++){
        		M[i][j] = sc.nextInt();
        	}
        }
        /*
         * Cerramos los objetos de tipo Scanner.
         */
        sc.close();
        scLinea.close();
        return M;
	}
	
}
