package Preg1;

public class Nodo {     //Nodo que usa la class Arbol
    public static final int M = 2;      //M=cantidad de hijos de éste nodo.
    
    public int Data;
    public Nodo HI, HD;
    

    public Nodo(){
        this(0);
    }
    
    public Nodo(int x){
        Data = x;
        HI=HD=null;
    }
    
    public void setData(int Data) {
        this.Data = Data;
    }

    public void setHI(Nodo HI) {
        this.HI = HI;
    }

    public void setHD(Nodo HD) {
        this.HD = HD;
    }

    
    public int getData() {
        return Data;
    }

    public Nodo getHI() {
        return HI;
    }

    public Nodo getHD() {
        return HD;
    }
    
    public int cantHijos(){ //Devuelve la cantidad de hijos (no-nulos) que tiene este Nodo.
        int c=0;
        
        for (int i=1; i<=M; i++){   //Contar los hijos no-nulos.
            if (getHijo(i) != null)
                c++;
        }
        
        return c;
    }
    
    
    public Nodo getHijo(int i){ //Pre: i=1 o i=2. Si i=1 devuelve HI; Si i=2 devuelve HD
        if (i< 1 || i>M){
            System.err.println("Nodo.getHijo: "+i+" no es un número de hijo correcto.  Use 1 ó 2.");
            return null;
        }
        
        return (i==1 ? HI : HD);   //hijo1=HijoIzq=HI, hijo2=HijoDer=HD
    }
    
    
    public void setHijo(int i, Nodo p){ //Pre: i=1 o i=2.
        if (i< 1 || i>M){
            System.err.println("Nodo.setHijo: "+i+" no es un número de hijo correcto.  Use 1 ó 2.");
            return;
        }
        
        if (i==1)
            HI = p;
        else
            HD = p;
    } 
    
    public int getHijoNull(){   //Devuelve el índice del 1er hijo nulo.
        for (int i=1; i<=M; i++){   
            if (getHijo(i)== null)
                return i;
        }
        
        return -1;  //El nodo no tiene hijos en null.
    }
    
    @Override
    public String toString(){
        return ""+Data;
    }
    
    
}
