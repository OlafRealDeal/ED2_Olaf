
package Preg1;

public class Main {
    private static final int dataA[][]={{30,60,25},{60,70,15},{25,50,90},{70,35},
                                        {15,-1, 5},{50,-1,80},{90,20,10}};
    
    
    private static Arbol load(int v[][]){
        Arbol t=null;
        
        for (int i=0; i<v.length; i++){
            int padre = v[i][0];            //v[i] = {padre, hijoIzq, hijoDer}
            if (t==null)
                t = new Arbol(padre);
            
            int indexHijo = 1;
            for (int j=1; j<v[i].length; j++){
                int d = v[i][j];
                if (d >=0)
                    t.add(padre, d, indexHijo);
                
                indexHijo++;
            }
        }
        return t;
    }
    
    public static void main(String[] args) {  
        Arbol A = load(dataA);      //Cargar los datos del Arbol A, mostrado en el examen.      
        
        A.niveles();
        
        A.changeLeaf(80, 35);   //Pruebe aquí su método.
               
        A.niveles();
        
    }
    
}
