package Digraph;

public class Main {

    private static final int[] a={6,2, 0,1, 8,0, 8,1, 3,0, 3,1, 4,7, 5,7};
    private static final int[] b={2,6, 6,2, 6,8, 8,2, 5,0, 5,7, 7,0, 7,5, 9,1, 10,3};
    
    private static void cargar(Grafo G, int n, int aristas[]){
        int i=1;
        for (; i <= n; i++){    //Cargar n vertices
             G.addVertice();
        }
              
        for (i=0; i < aristas.length; i += 2) { //Cargar las aristas: VerticeOrigen, VerticeDestino
            G.addArista(aristas[i], aristas[i+1]);
        }
    }
    
    
    
    public static void main(String[] args){
        Grafo A = new Grafo();
        cargar(A, 9, a);        //Crear el Grafo A mostrado en el examen.
        
        Grafo B = new Grafo();
        cargar(B, 11, b);        //Crear el Grafo B mostrado en el examen.
     
//        A.printListas();       
        System.out.println( A.edges(2) );
        
//        B.printListas();
        System.out.println( A.edges(4) );
        A.dfs(0);
    }
}
