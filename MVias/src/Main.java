

public class Main {
    private static final int V[] = {50, 20, 30, 10, 5, 15, 35, 33};
    
    private static void cargarDatos(ArbolM A){
        for (int i=0; i < V.length; i++)
            A.add(V[i]);
    }
    
    public static void main(String[] args) {
       ArbolM A = new ArbolM();
       cargarDatos(A);
       A.niveles();
//        System.out.println(A.cantNodos());
//        System.out.println(A.cantHojas());
//        System.out.println(A.existe(50));
//        System.out.println(A.hnoCercano(10, 30));
//        System.out.println(A.hnoCercano(15, 33));
//A.podar();
//A.niveles();
//A.borrarHoja(15);
//A.niveles();
//System.out.println(A.superParent(10, 15));
//System.out.println(A.padre(15));
//System.out.println(A.tio(30, 33));
//System.out.println(A.sum());
//A.delHoja(15);
//System.out.println(A.hnoCercanoMejorado(10, 30));
//System.out.println(A.superParentMejorado(20, 15));
//        System.out.println(A.lastParent(30, 33));   
//System.out.println(A.lasParentDiff(35, 33));

    }
    
}
