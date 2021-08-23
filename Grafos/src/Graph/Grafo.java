package Graph;

import java.awt.BorderLayout;
import java.util.LinkedList;

public class Grafo {  //Grafo NO-Dirigido

    private static final int MAXVERTEX = 49;    //Máximo índice de V[]

    private Lista V[];
    private int n;       //"Dimensión" de V[]

    public Grafo() {
        V = new Lista[MAXVERTEX + 1];      //V[0..MAXVERTEX]
        n = -1;

        marca = new boolean[MAXVERTEX + 1];    //Iniciar la ED para el marcado de los vértices.
    }

    public void addVertice() {
        if (n == MAXVERTEX) {
            //throw new Exception("Grafo.addVertice: Demasiados vértices (solo se permiten "+(MAXVERTEX+1)+")");
            System.err.println("Grafo.addVertice: Demasiados vértices (solo se permiten " + (MAXVERTEX + 1) + ")");
            return;
        }

        n++;
        V[n] = new Lista();     //Crear un nuevo vértice sin adyacentes (o sea con su Lista de adyacencia vacía)
    }

    public int cantVertices() {
        return n + 1;
    }

    public boolean isVerticeValido(int v) {
        return (0 <= v && v <= n);
    }

    public void addArista(int u, int v) {
        String metodo = "addArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo)) {
            return;     //No existe el vertice u o el vertice v.
        }
        V[u].add(v);
        V[v].add(u);
    }

    public void delArista(int u, int v) {
        String metodo = "delArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo)) {
            return;     //No existe el vertice u o el vertice v.
        }
        V[u].del(v);
        V[v].del(u);
    }

    public int cantAristas() {
        int cont = 0;
        for (int i = 0; i <= n; i++) {
            cont = cont + V[i].length();
            if (tieneLazo(i)) {
                cont++;        //Sumar como dos aristas a los lazos.
            }
        }

        return cont / 2;
    }

    public boolean tieneLazo(int v) { //Devuelve true sii el vertice v tiene un lazo.
        if (!isVerticeValido(v, "tieneLazo")) {
            return false;     //No existe el vertice v. 
        }
        return V[v].existe(v);
    }

    public void dfs(int v) {  //Recorrido Depth-First Search (en profundidad).
        if (!isVerticeValido(v, "dfs")) {
            return;  //Validación. v no existe en el Grafo.
        }
        desmarcarTodos();
        System.out.print("DFS:");
        dfs1(v);
        System.out.println();
    }

    private void dfs1(int v) {  //mask-function de void dfs(int)
        System.out.print(" " + v);
        marcar(v);

        for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
            int w = V[v].get(i);

            if (!isMarcado(w)) {
                dfs1(w);
            }
        }
    }

    public void dfsL(LinkedList<Integer> L1, int v) {  // este DFS pone en una Lista la isla
        if (!isVerticeValido(v, "dfs")) {
            return;  //Validación. v no existe en el Grafo.
        }
//        desmarcarTodos(); 
        dfsL1(L1, v);
    }

    private void dfsL1(LinkedList<Integer> L1, int v) {
        L1.add(v);
        marcar(v);

        for (int i = 0; i < V[v].length(); i++) {
            int w = V[v].get(i);

            if (!isMarcado(w)) {
                dfsL1(L1, w);
            }
        }
    }

    public void bfs(int u) {  //Recorrido Breadth-First Search (en anchura).
        if (!isVerticeValido(u, "bfs")) {
            return;  //Validación. u no existe en el Grafo. 
        }
        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>();  //"cola" = (vacía) = (empty)
        cola.add(u);     //Insertar u a la "cola" (u se inserta al final de la lista).
        marcar(u);

        System.out.print("BFS:");
        do {
            int v = cola.pop();         //Obtener el 1er elemento de la "cola".
            System.out.print(" " + v);

            for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
                int w = V[v].get(i);

                if (!isMarcado(w)) {
                    cola.add(w);
                    marcar(w);
                }
            }
        } while (!cola.isEmpty());

        System.out.println();
    }

    public void printListas() {  //Muestra las listas del Grafo.  Util para el programador de esta class
        if (cantVertices() == 0) {
            System.out.println("(Grafo Vacío)");
        } else {
            for (int i = 0; i <= n; i++) {
                System.out.println("V[" + i + "]-->" + V[i]);
            }
        }
    }

    @Override
    public String toString() {   //Este método debe ser cambiado si el grafo es dirigido.
        if (cantVertices() == 0) {
            return "(Grafo Vacío)";
        }

        //Mostrar el grafo en notación matemática
        desmarcarTodos();
        String S = "[";
        String coma = "";
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (V[i].existe(j)) { //j es ady a i.                
                    S += coma + "{" + i + "," + j + "}";    //Mostrar la arista i-j entre llaves.
                    coma = ", ";

                    marcar(i);
                    marcar(j);      //Decir que i y j SÍ tienen aristas
                }
            }

            if (!isMarcado(i)) { //El vértice i no tiene aristas, mostrarlo entre paréntesis.
                S += coma + "(" + i + ")";
                coma = ", ";
            }
        }

        return S + "]";
    }

    private boolean isVerticeValido(int v, String metodo) {
        boolean b = isVerticeValido(v);
        if (!b) {
            System.err.println("Grafo." + metodo + ": " + v + " no es un vértice del Grafo " + getIndicacion());
        }

        return b;
    }

    private String getIndicacion() {  //Corrutina de boolean isVerticeValido(int, String)
        switch (cantVertices()) {
            case 0:
                return "(el grafo no tiene vértices). ";
            case 1:
                return "(el 0 es el único vértice). ";
            case 2:
                return "(0 y 1 son los únicos vértices). ";
            default:
                return "(los vértices van de 0 a " + (cantVertices() - 1) + "). ";
        }
    }

//********* Para el marcado de los vértices
    private boolean marca[];

    private void desmarcarTodos() {
        for (int i = 0; i <= n; i++) {
            marca[i] = false;
        }
    }

    private void marcar(int u) {
        if (isVerticeValido(u)) {
            marca[u] = true;
        }
    }

    private void desmarcar(int u) {
        if (isVerticeValido(u)) {
            marca[u] = false;
        }
    }

    private boolean isMarcado(int u) {   //Devuelve true sii el vertice u está marcado.
        return marca[u];
    }

    public boolean conexo() {
        dfs(0);
        for (int i = 0; i <= n; i++) {
            if (!isMarcado(i)) {
                return false;
            }
        }
        return true;
    }

    public int getVerticeNoMarcado() {
        for (int i = 0; i <= n; i++) {
            if (!isMarcado(i)) {
                return i;
            }
        }
        return -1;
    }

    public int cantIslas() {
        desmarcarTodos();
        int cant = 0;
        int u;
        do {
            u = getVerticeNoMarcado();

            if (u != -1) {
                dfs1(u);
                cant++;
            }
        } while (u != -1);
        System.out.println("\n" + "Existen " + cant + " islas");
        return cant;
    }

//    public boolean hayCamino(int u, int v) {
//        if (!isVerticeValido(v)) {
//            return false;
//        }
//        desmarcarTodos();
//        return hayCamino1(u, v);
//    }
//
//    private boolean hayCamino1(int u, int v) {
//        marcar(u);
//        for (int i = 0; i < V[u].length(); i++) {
//            int w = V[u].get(i);
//            if (w == v) {
//                return true;
//            }
//            if (!isMarcado(w)) {
//                return hayCamino1(w, v);
//            }
//        }
//        return false;
//    }

    public boolean isLineal() {
        desmarcarTodos();
        return isLineal1(0);
    }

    private boolean isLineal1(int v) {
        marcar(v);
        if (V[v].length() > 1) {
            return false;
        }
        for (int i = 0; i < V[v].get(i); i++) {
            int w = V[v].get(i);

            if (!isMarcado(w)) {
                return isLineal1(w);
            }
        }
        return true;
    }

    public int costoCamino(int u, int v) {
        int costo = 0;
        if (ExisteCamino(u, v)) {
            int i = 0;
            while (i < V[u].length()) {
                if (V[u].existe(v)) {
                    return costo + u + v;
                }
                if (ExisteCamino(V[u].get(i), v)) {
                    costo += V[u].get(i) + u;
                    u = V[u].get(i);
                    i--;
                }
                i++;
            }
        } else {
            return -1;

        }
        return costo;
    }

    public int islaMenor() {
        LinkedList<Integer> isla = new LinkedList<>();
        int menor = Integer.MAX_VALUE;
        desmarcarTodos();
        for (int i = 0; V[i] != null; i++) {
            if (!isMarcado(i)) {
                dfsL(isla, i);
                if (isla.size() < menor) {
                    menor = isla.size();
                }
                isla = new LinkedList<>();
            }
        }
        System.out.println("La isla menor tiene " + menor + " islas");
        return menor;
    }

    public int islaMenorMejorada() {
        int may = cantVertices();
        desmarcarTodos();
        for (int i = 0; i < cantVertices(); i++){
            if(!isMarcado(i)){
                int c = cantNodos(i);
                if(c < may){
                    may = c;
                }
            }
        }
        return may;
 
    }

    private int cantNodos(int v) {
        marcar(v);
        int c = 1;
        for (int i = 0; i < V[v].length(); i++) {
            int w = V[v].get(i);

            if (!isMarcado(i)) {
                c += cantNodos(w);
            }
        }
        return c;
    }
         public boolean ExisteCamino(int u, int v) {
        desmarcarTodos();
        return ExisteCamino1(u, v);

    }

    private boolean ExisteCamino1(int u, int v) {
        marcar(u);
        boolean encontrado = false;
        for (int i = 0; i < V[u].length() && !encontrado; i++) {
            int w = V[u].get(i);
            if (w == v) {
                return true;
            } else {
                if (!isMarcado(w)) {
                    encontrado = encontrado || ExisteCamino1(w, v);
                }
            }
        }
        return encontrado;
    }

}
