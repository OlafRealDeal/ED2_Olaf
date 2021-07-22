
import java.util.LinkedList;


public class ArbolM {
    private NodoM raiz;
    private int n;          //n=cantidad de nodos.
    
    public ArbolM(){
        raiz = null;
        n = 0;
    }
    
    
    public int getCantNodos(){
        return n;
    }
    public int cantNodos(){
        return cantNodos(raiz);
    }
    private int cantNodos(NodoM N){
        if (N==null)
            return 0;
        if (hoja(N))
            return 1;
        int ac=0;
        for (int i = 1; i <= NodoM.M; i++) {
            ac=ac+cantNodos(N.getHijo(i));
        }
        return ac+1;
    }
    public int altura(){
        return altura(raiz);
    }
    private int altura(NodoM N){
        if (N==null)
            return 0;
        if (hoja(N))
            return 1;
        int m=0;
        for (int i = 1; i <= NodoM.M; i++) {
            int h=altura(N.getHijo(1));
            if(h > m)
                m=h;
        }
        return m+1;
    }
    public void insertar(int x){
        if (raiz == null){
            raiz = new NodoM(x);
        }
        else{
            int i=0;
            NodoM ant=null, p=raiz;
            while (p != null){
                if (!p.isLleno()){  //Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }
                
                i = hijoDesc(p, x);
                if (i == -1)
                    return;     //x está en el nodo p.
                
                ant = p;
                p = p.getHijo(i);
            }
            
                //p llegó a null.  Crear una nueva hoja y engancharla a ant.
            NodoM nuevo = new NodoM(x);
            ant.setHijo(i, nuevo);
        }
        
        n++;
    }
    
    public boolean hnoCercano(int a , int b){ 
        return hnoCercano(raiz,a,b);
    }
    private boolean hnoCercano(NodoM N,int a ,int b ){
        if (N==null) 
            return false;
        if (hoja(N))
            return false;
        
        return true;
    }
    public void podar(){
        podar(raiz);
    }
    private void podar(NodoM N){
        if (N==null)
            return;
        if (hoja(N))
            N=null;
        else{
            for (int i = 1; i <= NodoM.M; i++) {
            podar(N.getHijo(i));
            }
            
            if(hoja(N))
                N=null;
        }
        
    }
    public void inorden(){
        System.out.print("Inorden:");
        if (raiz == null)
            System.out.println(" (Árbol vacío)");
        else{
            inorden(raiz);
            System.out.println();
        }
            
    }
    
    private void inorden(NodoM T){
        if (T != null){
            int z=T.cantDatasUsadas();      //z = índice de la última data usada.
            for (int i=1; i<=z; i++){
                inorden(T.getHijo(i));
                System.out.print(" "+T.getData(i));
            }
            inorden(T.getHijo(z+1));
        }
    }
    
    private int hijoDesc(NodoM N, int x){   //Corrutina de insertar.
        int i=1;
        while (i <= N.cantDatasUsadas()){
            if (x < N.getData(i))
                return i;
            
            if (x == N.getData(i))
                return -1;
            
            i++;
        }
        
        return N.cantDatasUsadas()+1;
    }
    
    private void print(NodoM N){
       for (int i=1; i<= N.cantDatasUsadas(); i++)
          System.out.print(" "+N.getData(i)); 
    }
    
    private boolean hoja(NodoM T){
        return (T !=null && T.cantHijos()==0);
    }
    
    public void niveles(){
        niveles("");
    }
    
    public void niveles(String nombreVar){
        if (nombreVar != null && nombreVar.length()>0)
            nombreVar = " del Arbol "+nombreVar;
        else
            nombreVar = "";
                    
        System.out.print("Niveles"+nombreVar+": ");
        
        if (raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(raiz);
    }
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
        private void niveles(NodoM T){   //Pre: T no es null.
        LinkedList <NodoM> colaNodos   = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();
        
        int nivelActual = 0;
        String coma="";
        
        colaNodos.addLast(T);
        colaNivel.addLast(1);
        
        do{
            NodoM p = colaNodos.pop();       //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.
            
            if (nivelP != nivelActual){ //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel "+nivelP+": ");
                nivelActual = nivelP;
                coma = "";
            }
            
            System.out.print(coma + p);
            coma = ", ";
            
            addHijos(colaNodos, colaNivel, p, nivelP);   
        }while (colaNodos.size() > 0);
        
        System.out.println();
    }
    
    private void addHijos(LinkedList <NodoM> colaNodos, LinkedList<Integer> colaNivel,  NodoM p, int nivelP){
        for (int i=1; i<=NodoM.M; i++){  //Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);
            
            if (hijo != null){
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP+1);
            }
        }
    }    
}


