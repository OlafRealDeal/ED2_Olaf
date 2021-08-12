package Graph;

public class Lista {    //ADT Lista (Ordenada y sin duplicados).
    private Nodo L;
    private int n;
    
    public Lista(){
        L = null;
        n = 0;
    }
    
    public void add(int x){ //Inserta x a la Lista.
        Nodo Ant = null;
        Nodo p   = L;
        
        while (p != null && x >= p.getData()){
            Ant = p;
            p = p.getLink();
        }
        
        Nodo nuevo;
        if (Ant == null){   //x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(x);
            nuevo.setLink(L);
            L = nuevo;
            n++;
        }
        else
            if (Ant.getData() != x){    //x no está en la lista.  Insertarlo entre Ant y p
                nuevo = new Nodo(x);
                Ant.setLink(nuevo);
                nuevo.setLink(p);
                n++;
            }
    }
    
    public void del(int x){     //Elimina x de la Lista, si existe.
        Nodo Ant = null;
        Nodo p   = L;
        
        while (p != null && x > p.getData()){
            Ant = p;
            p = p.getLink();
        }
        
        if (p != null && p.getData() == x){  //x existe en la Lista 
            if (Ant == null)
                L = L.getLink();    //x era el primero de la Lista
            else
                Ant.setLink(p.getLink());
            
            p.setLink(null);
            n--;    
        }
    }
    
    
    public boolean existe(int x){
        Nodo p = L;
        
        while (p != null && x > p.getData()){
            p = p.getLink();
        }
        
        return (p != null && p.getData() == x);
    }
    
    
    public int get(int k){  //Devuelve el k-ésimo elemento de la lista k=0, 1, ..., length()-1 
        Nodo p=L;
        int i=0;
        while (p != null){
            if (i==k)
                return p.getData();
            
            p = p.getLink();
            i++;
        }
        
        System.err.println("Lista.get: Fuera de rango");
        return -1;
    }
    
    public int length(){
        return n;
    }
    
    
    @Override
    public String toString(){ 
        String S = "[";
        String coma="";
       
        Nodo p  = L;
        while (p != null){
            S += coma + p.getData();
            coma=", ";
            p = p.getLink();
        }
       
       return S+"]";
    }
    
}
