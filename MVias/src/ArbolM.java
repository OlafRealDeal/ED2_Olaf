
import com.sun.javafx.font.t2k.T2KFactory;
import java.util.LinkedList;
import jdk.nashorn.internal.runtime.PropertyMap;

public class ArbolM {

    private NodoM raiz;
    private int n;          //n=cantidad de nodos.

    public ArbolM() {
        raiz = null;
        n = 0;
    }

    public int getCantNodos() {
        return n;
    }

    public void add(int x) {
        if (raiz == null) {
            raiz = new NodoM(x);
        } else {
            int i = 0;
            NodoM ant = null, p = raiz;
            while (p != null) {
                if (!p.isLleno()) {  //Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }

                i = hijoDesc(p, x);
                if (i == -1) {
                    return;     //x está en el nodo p.
                }
                ant = p;
                p = p.getHijo(i);
            }

            //p llegó a null.  Crear una nueva hoja y engancharla a ant.
            NodoM nuevo = new NodoM(x);
            ant.setHijo(i, nuevo);
        }

        n++;
    }

    private boolean hoja(NodoM T) {
        return (T != null && T.cantHijos() == 0);
    }

    public void inorden() {
        System.out.print("Inorden:");
        if (raiz == null) {
            System.out.println(" (Árbol vacío)");
        } else {
            inorden(raiz);
            System.out.println();
        }

    }

    private void inorden(NodoM T) {
        if (T != null) {
            int z = T.cantDatasUsadas();      //z = índice de la última data usada.
            for (int i = 1; i <= z; i++) {
                inorden(T.getHijo(i));
                System.out.print(" " + T.getData(i));
            }
            inorden(T.getHijo(z + 1));
        }
    }

    private int hijoDesc(NodoM N, int x) {   //Corrutina de insertar.
        int i = 1;
        while (i <= N.cantDatasUsadas()) {
            if (x < N.getData(i)) {
                return i;
            }

            if (x == N.getData(i)) {
                return -1;
            }

            i++;
        }

        return N.cantDatasUsadas() + 1;
    }

    private void print(NodoM N) {
        for (int i = 1; i <= N.cantDatasUsadas(); i++) {
            System.out.print(" " + N.getData(i));
        }
    }

    public void niveles() {
        niveles("");
    }

    public void niveles(String nombreVar) {
        if (nombreVar != null && nombreVar.length() > 0) {
            nombreVar = " del Arbol " + nombreVar;
        } else {
            nombreVar = "";
        }

        System.out.print("Niveles" + nombreVar + ": ");

        if (raiz == null) {
            System.out.println("(Arbol vacío)");
        } else {
            niveles(raiz);
        }
    }
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------

    private void niveles(NodoM T) {   //Pre: T no es null.
        LinkedList<NodoM> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        do {
            NodoM p = colaNodos.pop();       //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.

            if (nivelP != nivelActual) { //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                coma = "";
            }

            System.out.print(coma + p);
            coma = ", ";

            addHijos(colaNodos, colaNivel, p, nivelP);
        } while (colaNodos.size() > 0);

        System.out.println();
    }

    private void addHijos(LinkedList<NodoM> colaNodos, LinkedList<Integer> colaNivel, NodoM p, int nivelP) {
        for (int i = 1; i <= NodoM.M; i++) {  //Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);

            if (hijo != null) {
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP + 1);
            }
        }
    }

    public int cantNodos() {
        return cantNodos(raiz);

    }

    private int cantNodos(NodoM T) {
        if (T == null) {
            return 0;
        }
        if (hoja(T)) {
            return 1;
        }
        int ac = 0;
        for (int i = 1; i <= NodoM.M; i++) {
            int h = cantNodos(T.getHijo(i));
            ac += h;
        }
        return ac + 1;
    }

    public int cantHojas() {
        return cantHojas(raiz);
    }

    private int cantHojas(NodoM T) {
        if (T == null) {
            return 0;
        }
        if (hoja(T)) {
            return 1;
        }
        int ac = 0;
        for (int i = 1; i <= NodoM.M; i++) {
            int h = cantHojas(T.getHijo(i));
            ac += h;
        }
        return ac;
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoM T) {
        if (T == null) {
            return 0;
        }
        if (hoja(T)) {
            return 1;
        }
        int mayor = 0;
        for (int i = 1; i <= NodoM.M; i++) {
            int h = altura(T.getHijo(i));
            if (h > mayor) {
                mayor = h;
            }
        }
        return mayor + 1;
    }

    public boolean existe(int x) {
        return existe(raiz, x);
    }

    private boolean existe(NodoM T, int x) {
        if (T == null) {
            return false;
        }
        if (hoja(T)) {
            return T.existe(x);
        }
        if (T.existe(x)) {
            return true;
        }
        for (int i = 1; i <= NodoM.M; i++) {
            boolean h = existe(T.getHijo(i), x);
            if (h) {
                return true;
            }
        }
        return false;
    }

    public boolean Existe(NodoM inicio, int dato) {
        NodoM aux = inicio;
        return Existe1(aux, dato);
    }

    private boolean Existe1(NodoM p, int dato) {
        if (p != null) {
            if (hoja(p) && p.existe(dato)) {
                return true;
            } else {
                if (p.existe(dato)) {
                    return true;
                }
                boolean encontrado = false;
                for (int i = 1; i <= p.M; i++) {
                    NodoM aux = p.getHijo(i);
                    encontrado = encontrado || Existe1(aux, dato);
                }
                return encontrado;
            }
        }
        return false;
    }

    public boolean existeOptimizado(int x) {
        return existeOptimizado(raiz, x);
    }

    private boolean existeOptimizado(NodoM T, int x) {
        if (T == null) {
            return false;
        }
        if (hoja(T)) {
            return T.existe(x);
        }
        int i = hijoDesc(T, x);
        if (i == -1) {
            return true;
        }
        return existeOptimizado(T.getHijo(i), x);

    }

    public boolean hnoCercano(int a, int b) {
        return hnoCercano(raiz, a, b);
    }

    private boolean hnoCercano(NodoM T, int a, int b) {
        if (T == null) {
            return false;
        }
        if (hoja(T)) {
            return false;
        }
        boolean B = false;
        boolean A = false;
        for (int i = 1; i <= T.cantHijos(); i++) {
            NodoM temp = T.getHijo(i);
            if (temp != null && temp.existe(a) && !temp.existe(b)) {
                A = true;
            }
            if (temp != null && temp.existe(b) && !temp.existe(a)) {
                B = true;
            }
        }
        for (int i = 1; i <= NodoM.M; i++) {
            hnoCercano(T.getHijo(i), a, b);
        }
        return A && B;
    }

    public boolean hnoCercanoMejorado(int a, int b) {
         return hnoCercanoMejorado(raiz, a, b);
    }

    private boolean hnoCercanoMejorado(NodoM T, int a, int b) {
        if (T == null) {
            return false;
        }
        if (hoja(T)) {
            return false;
        }
        for (int i = 1; i <= NodoM.M; i++) {
            if (T.getHijo(i) != null && T.getHijo(i + 1) != null) {
                if (T.getHijo(i).existe(a) && T.getHijo(i + 1).existe(b)) {
                    return true;

                }
            }
        }
        for (int i = 1; i <= NodoM.M; i++) {
           if( hnoCercanoMejorado(T.getHijo(i), a, b))
               return true;
        }
        return false;
    }

    public void podar() {
        raiz = podar(raiz);
    }

    private NodoM podar(NodoM T) {
        if (T == null) {
            return null;
        }
        if (hoja(T)) {
            return null;
        }
        for (int i = 1; i <= NodoM.M; i++) {
            T.setHijo(i, podar(T.getHijo(i)));
        }
        return T;
    }

    public void borrarHoja(int x) {
        NodoM aux = null;
        NodoM T = raiz;
        if (T == null) {
            return;
        }
        if (T.existe(x) && !hoja(T)) {
            return;
        } else if (T.existe(x) && hoja(T)) {
            raiz = null;
        }
        int h = hijoDesc(T, x);
        while (T.getHijo(h) != null) {
            aux = T;
            T = T.getHijo(h);
            if (T.existe(x) && hoja(T)) {
                aux.setHijo(h, null);
            } else {
                h = hijoDesc(T, x);
                aux = T;
                T = T.getHijo(h);
            }
            if (T.existe(x) && hoja(T)) {
                aux.setHijo(h, null);

            }
        }

    }

    public boolean superParent(int x, int z) {
        return superParent(raiz, x, z);
    }

    private boolean superParent(NodoM T, int x, int z) {
        if (T != null) {
            for (int i = 1; i < T.M; i++) {
                NodoM hijoIzq = T.getHijo(i);
                NodoM hijoDer = T.getHijo(i + 1);
                if ((T.getData(i) == x && Existe(hijoIzq, z) || T.getData(i) == x && Existe(hijoDer, z))) {
                    return true;
                }
            }
            boolean encontrado = false;
            for (int i = 1; i < T.M && !encontrado; i++) {
                NodoM aux = T.getHijo(i);
                encontrado = encontrado || superParent(aux, x, z);
            }
            return encontrado;
        }
        return false;
    }
    public boolean superParentMejorado(int x, int z) {
        return superParentMejorado(raiz, x, z);
    }
    private boolean superParentMejorado(NodoM T, int x, int z) {
        if(T==null)
            return false;
        if(hoja(T))
            return false;
        for(int i =1; i<= T.cantDatasUsadas(); i++){
            if(T.getData(i)==x && Existe(T.getHijo(i), z) || T.getData(i)==x && Existe(T.getHijo(i+1), z)){
                return true;             
            }
        }
        for(int i =1; i<= NodoM.M; i++){
            if(superParentMejorado(T.getHijo(i), x, z))
                return true;
        }
        return false;
    }
    public boolean sonHermanos(int a, int b) {
        return padre(a) == padre(b);
    }

    public NodoM padre(int x) { //DEVUELVE EL NODO PADRE DEL DATA ENVIADO
        return padre(raiz, x);
    }

    private NodoM padre(NodoM T, int x) {
        if (T != null) {
            for (int i = 1; i <= NodoM.M; i++) {
                NodoM aux = T.getHijo(i);
                if (aux != null && aux.existe(x)) {
                    return T;
                }
            }
            NodoM r = null;
            for (int i = 1; i <= NodoM.M && r == null; i++) {
                r = padre(T.getHijo(i), x);
            }
            return r;
        }
        return null;
    }

    public boolean tio(int t, int s) {
        return tio(t, s, null);
    }

    private boolean tio(int t, int s, NodoM T) {
        if (padre(s) != null && padre(padre(s).getData(1)) != null && padre(t) != null) {

            return (padre(padre(s).getData(1)) == padre(t) && !Existe(padre(s), t));

        } else {
            return false;
        }
    }

    public int sum() { // LA SUMA DE LOS DATAS DE LAS HOJAS MULTIPLICADO POR EL NUMERO DEL HIJO QUE LA SOSTIENE.
        return sum(raiz, 0);
    }

    private int sum(NodoM T, int nh) { 
        if (T == null) {
            return 0;
        }
        if (hoja(T)) {
            return getSuma(T) * nh;
        }
        int sum = 0;
        for (int i = 1; i <= NodoM.M; i++) {
            sum += sum(T.getHijo(i), i);
        }
        return sum;

    }

    private int getSuma(NodoM T) {
        int sum = 0;
        for (int i = 1; i <= T.cantDatasUsadas(); i++) {
            sum += sum + T.getData(i);
        }
        return sum;
    }

    public void delHoja(int sum) { //ELIMINA TODAS LAS HOJAS DONDE LA SUMA DE SUS DATAS ES IGUAL A SUM
        raiz = delHoja(raiz, sum);
    }

    private NodoM delHoja(NodoM T, int sum) {
        if (T == null) {
            return null;
        }
        if (hoja(T)) {
            if (getSuma(T) == sum) {
                return null;
            }
            return T;
        }
        for (int i = 1; i <= NodoM.M; i++) {
            T.setHijo(i, delHoja(T.getHijo(i), sum));
        }
        return T;
    }
//    public boolean lastParent1(int p, int h) {   //
//        NodoM aux = raiz;
//        return lastParent(aux, p, h);
//    }

//    private boolean lastParent1(NodoM x, int p, int h) {
//        if (x != null) {
//            if (x.existe(p)) {
//                for (int i = 1; i <= x.M; i++) {
//                    NodoM hijo = x.getHijo(i);
//                    if (hoja(hijo) && hijo.existe(h)) {
//                        return true;
//                    }
//
//                }
//            }
//            boolean encontrado = false;
//            for (int i = 1; i <= x.M; i++) {
//                NodoM aux = x.getHijo(i);
//                encontrado = encontrado || lastParent(aux, p, h);
//            }
//            return encontrado;
//        }
//        return false;
//    }
     public boolean lastParent(int a, int b){
        return lastParent(raiz, a, b);
    }
    private boolean lastParent(NodoM p, int a, int b){
        if (p!= null) {
            boolean respuesta= false;
            for (int i = 1; i <= p.M; i++) {
                if (p.existe(a)&& hoja(p.getHijo(i))&&p.getHijo(i).existe(b)) {
                    respuesta =true;
                }else{
                    respuesta= respuesta || lastParent(p.getHijo(i), a, b);
                }
            }
            return respuesta;                        
        }
        return false;
    }
      public boolean lasParentDiff(int padre, int hijo) {
        return lasParentDiff(raiz, padre, hijo);
    }

    private boolean lasParentDiff(NodoM p, int padre, int hijo) {
        if (p == null) {
            return false;
        } else {
            boolean respuesta = false;
            if (p.existe(padre)) {
                for (int i = 1; i <= p.M; i++) {
                    NodoM aux = p.getHijo(i);
                    if (hoja(aux) && aux.existe(hijo)) {
                        respuesta = true;
                    }
                }
            } else {
                for (int i = 1; i <= p.M; i++) {
                    NodoM aux = p.getHijo(i);
                    respuesta = respuesta || lasParentDiff(aux, padre, hijo);
                }
            }
            return respuesta;
        }
    }
    
}
