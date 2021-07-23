

public class Main {
    private static final int V[] = {50, 20, 30, 10, 5, 15};
    
    private static void cargarDatos(ArbolM A){
        for (int i=0; i < V.length; i++)
            A.add(V[i]);
    }
    
    public static void main(String[] args) {
       ArbolM A = new ArbolM();
       cargarDatos(A);
       A.niveles();
    }
    
}
