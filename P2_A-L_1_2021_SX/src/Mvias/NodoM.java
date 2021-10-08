package mvias;

public class NodoM {
    public static final int M=4;
    
    private int peso;      //Peso del puntero que sostiene a este Nodo.
                            //Si es la Raíz peso=0
    private NodoM hijo[];
    private int data[];
    private boolean[] usada;
    private int cantUsadas;
    
    public NodoM(){
        hijo  = new NodoM[M];
        data  = new int[M-1];
        usada = new boolean[M-1];
        
        for (int i=0; i < usada.length; i++){   //Solo poner valores por default.
            data[i]  = 0;
            usada[i] = false;
            hijo[i]  = null;
        }
        
        hijo[M-1]  = null;
        cantUsadas = 0;
    }
    
    
    public NodoM(int x){    //Crea el nodo e inserta el primer data x
        this();
        setData(1, x);
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
    
    public NodoM getHijo(int i){
        if (indexValido(i, hijo.length, "getHijo"))  
            return hijo[i-1];
      
        return null;
    }
    
    
    public void setHijo(int i, NodoM p){
        if (indexValido(i, hijo.length, "setHijo"))  
            hijo[i-1] = p;
    }
    
    public int getData(int i){
        if (indexValido(i, data.length, "getData"))  
            return data[i-1];
      
        return 0;
    }
    
    
    public void setData(int i, int x){
        if (indexValido(i, data.length, "setData")){  
            data[i-1] = x;
            
            if (isVacia(i))
                cantUsadas ++;      //El data[i-1] estaba sin datos.
            
            usada[i-1] = true;
        }    
    }
    
    public boolean existe(int x){   //Devuelve true si x existe en el nodo.
        for (int i = 0; i < data.length; i++) {
            if (usada[i] && data[i]==x)
                return true;
        }
        return false;
    }
    
    /**
     * inserta x en el data i-ésimo, desplazando los demas datas hacia la derecha.
     * <code>i=1,2,...,NodoM.M-1 </code>
     */
    public void insData(int i, int x){
        if (indexValido(i, data.length, "insData")){  
            expand(i-1);
            setData(i, x);
        }
    }
    
    public void insDataInOrden(int x){  //Inserta x en el nodo, manteniendolo ordenado.
        int i=0, n=cantDatasUsadas()-1;
        while (i <= n && x > data[i])   //Buscar la posición i de inserción.
            i++;
        
        if (i <= n && x ==data[i])
            return;     //x existe en el nodo.
        
        if (isLleno()){
            System.err.println("NodoM.insDataInOrden: "+x+" no se puede insertar porque el nodo está lleno.");
            return;
        }
        
        insData(i+1, x);    //Sumar +1 a la posición, porque insData usa los índices desde el 1.
    }
    
    public void setVacia(int i){
        if (indexValido(i, usada.length, "setVacia")){  
            if (isUsada(i))
                cantUsadas --;      //El data[i-1] estaba con datos.
            
            usada[i-1] = false;
        }    
    }
    
    /**
     * Devuelve true sii el data i-ésima está vacía.
     * Recuerde que <code>i=1,2,...,NodoM.M-1 </code>
     * @return 
     */
    public boolean isVacia(int i){
        if (indexValido(i, usada.length, "isVacia"))
            return !usada[i-1];
        
        return false;
    }

    /**
     * Devuelve true sii el data i-ésima está almacenando un valor.
     * Recuerde que <code>i=1,2,...,NodoM.M-1 </code>
     * @return 
     */
    public boolean isUsada(int i){
        if (indexValido(i, usada.length, "isLlena"))
            return usada[i-1];
        
        return false;
    }

    
    /**
     * Devuelve la cantidad de hijos <b> no-nulos </b> que tiene el nodo
     * @return 
     */
    public int cantHijos(){
        int c=0;
        for (NodoM h : hijo) {
            if (h != null) {
                c++;
            }
        }
        return c;
    }
    
    
    /**
     * Devuelve la cantidad de datas usadas (datas no-vacías) que tiene el nodo
     * @return
     */
    public int cantDatasUsadas(){
        return cantUsadas;
    }
    
    /**
     * Devuelve la cantidad de datas no usadas (datas vacías) que tiene el nodo
     * @return
     */
    public int cantDatasVacias(){
        return data.length - cantUsadas;
    }
    
    /**
     * Devuelve true si y solo si todos los datas del nodo están usadas.
     * @return 
     */
    public boolean isLleno(){
        return (cantDatasVacias()==0);
    }
    
    @Override
    public String toString(){
        String S = ""+peso+"-[";
        String pipe="";
       
        for (int i=0; i < data.length; i++){
            S += pipe + (usada[i]? data[i] : " ");
            pipe="|";
        }
       
       return S+"]";     
    }
    
    private boolean indexValido(int i, int n, String Metodo){
        boolean b = (1<=i && i <=n);
        if (!b)
            System.err.println("NodoM."+Metodo+": " + i +" está fuera de rango. Los índices van de 1 a "+n+".");
        
        return b;
    }
    
    private void copyData(int i, int k){    //Produce data[k] = data[i]
        data[k]  = data[i];
        usada[k] = usada[i];
    }
    
    private void expand(int j){
        for (int i = data.length-1; i>j; i--) { //Desplazar datas hacia la derecha, desde el data-j.
            copyData(i-1, i);
        }
        
        updateCantUsadas();
    }
    
    
    private void updateCantUsadas(){    //Recalcular cantUsadas.
        cantUsadas = 0;
        for (int i=0; i<usada.length; i++){ 
            if (usada[i])
                cantUsadas++;
        } 
    }
}


