package fuerzaBruta;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Vector;


import lecturasFichero.FicheroAMultidimensional;
import main.SolucionViajante;


public class SolucionFuerzaBruta implements SolucionViajante{
	
	public Vector<Integer> sol;
	public int sum;
	private int[][] M;
	static final int ORIGEN_N = 0;
	
	

	public SolucionFuerzaBruta (String ruta) throws FileNotFoundException{
		FicheroAMultidimensional fm = new FicheroAMultidimensional(ruta);
		this.M=fm.leerFichero();
	}
	
	/*
	 * Recursión para permutar los elementos de un vector.
	 */
	// Más complicado que lo que parecía, tengo que recordar también cada vector
	@SuppressWarnings("unchecked")
	public int permutarV (Vector<Integer> vOrig, Vector<Integer> vAux, int index){
		if (vOrig.size()==1){
			vAux.addElement(vOrig.lastElement());
			System.out.println(vAux);
			CalcularDist(vAux);
			
			index++;
			return index;
		}
		else{
			Vector<Integer> vNext;
			Vector<Integer> vAux2;
			
			int indexAdq=index;
			index=0;
			while (index<vOrig.size()){
				
				//Copio valores de VOrig y vAux (Los utilizo para próximas iteraciones)
				vAux2=(Vector<Integer>) vAux.clone();
				vNext=(Vector<Integer>) vOrig.clone();
				
				//Añado al vAux2 el elemento y permuto el resto
				vAux2.addElement(vOrig.elementAt(index));
				vNext.removeElementAt(index);
				
				//System.out.println("PASA " + index);
				//System.out.println("vOrig: "+vOrig);
				//System.out.println("vAux: "+vAux);
				//System.out.println("vNext: "+vNext);
				//System.out.println("vAux2: "+vAux2);
				
				index=permutarV(vNext,vAux2,index);
				
				
				//System.out.println("PASA DESPUES PERM " + index);
				
			}
			indexAdq++;
			return indexAdq;
		}
	}
	
	/*
	 * Guarda en "sol" el vector solución con el orden de los nodos a recorrer.
	 * Guarda en "sum" la suma de la distancia de la solución
	 */
	public void CalcularDist (Vector<Integer> v){
		Iterator<Integer> it=v.iterator();
		Integer desde=ORIGEN_N;
		Integer hasta;
		int suma=0;
		
		while (it.hasNext()) {
			hasta= it.next();
			suma=suma + this.M[desde][hasta];
			desde=hasta;
		}
		
		//Vuevlo al nodo inicial
		suma=suma+this.M[desde][ORIGEN_N];
		
		//Guardo si mínimo
		if (suma < this.sum){
			this.sum = suma;
			this.sol = v;
		}
		
	}
	
	public void AlgFuerzaBruta(){
			
		
		Vector<Integer> v = new Vector<Integer>(M.length);
			for(int k=1; k<M.length; k++){
	    		v.add(k);
	    	}
		Vector<Integer> vAux = new Vector<Integer>(M.length);
		System.out.println("Permutaciones de : "+v);
		int i=0;
		permutarV(v,vAux,i);
	}

	@Override
	public void resolverViajante() {
		// TODO Auto-generated method stub
		
	}
	
	
}
