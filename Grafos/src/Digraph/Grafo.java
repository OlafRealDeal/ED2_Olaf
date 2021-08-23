package Digraph;

import java.util.LinkedList;

public class Grafo {  //Grafo Dirigido

    private static final int MAXVERTEX = 49;    //Máximo índice de V[]

    private Lista V[];
    private int n;       //"Dimensión" de V[]

    public Grafo() {
        V = new Lista[MAXVERTEX + 1];      //V[0..MAXVERTEX]
        n = -1;

        marca = new boolean[MAXVERTEX + 1];    //Iniciar la ED para el marcado de los vértices.
    }

    public boolean isVerticeValido(int v) {
        return (0 <= v && v <= n);
    }

    public void addVertice() {
        if (n == MAXVERTEX) {
            System.err.println("Grafo.addVertice: Demasiados vértices (solo se permiten " + (MAXVERTEX + 1) + ")");
            return;
        }

        n++;
        V[n] = new Lista();     //Crear un nuevo vértice sin adyacentes (o sea con su Lista de adyacencia vacía)
    }

    public int cantVertices() {
        return n + 1;
    }

    public int cantAristas() {
        int cont = 0;
        for (int i = 0; i <= n; i++) {
            cont += V[i].length();
            if (tieneLazo(i)) {
                cont++;
            }
        }
        return cont / 2;
    }

    public boolean tieneLazo(int v) {
        if (!isVerticeValido(v)) {
            return false;
        }
        return V[v].existe(v);
    }

    public void addArista(int u, int v) {  //Crea la arista u-->v
        String metodo = "addArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo)) {
            return;     //No existe el vertice u o el vertice v.
        }
        V[u].add(v);      //Adicionar v a la lista V[u]    
    }

    public void delArista(int u, int v) {
        String metodo = "delArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo)) {
            return;     //No existe el vertice u o el vertice v.
        }
        V[u].del(v);    //Quitar v a la lista V[u]
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

    public int menor(int u) {
        int menor = 999;
        for (int i = 0; i < V[u].length(); i++) {
            int k = V[u].get(i);
            if (k < menor && !isMarcado(k)) {
                menor = k;
            }
        }
        return menor;
    }

//    public boolean hayCamino(int u, int v) {       
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

    public String path(int u) {
        if (!isVerticeValido(u, "dfs")) {
            return "Error, " + u + " no es un vertice valido ";
        }
        desmarcarTodos();
        System.out.println("PATH:");
        return path1(u);
    }

    private String path1(int u) {
        marcar(u);
        int w = menor(u);
        if (isVerticeValido(w) && !isMarcado(w)) {
            return String.valueOf(u) + " " + path1(w);
        }
        return String.valueOf(u);

    }

    public void printHojas(int u) {
        if (cantVertices() == 0) {
            System.out.println("(Grafo Vacío)");
        }
        desmarcarTodos();
        System.out.print("La cantidad de hojas es : ");
        printHojas1(u);
        System.out.println();
    }

    private void printHojas1(int u) {  //
        String hojas = "";
        marcar(u);

        if (V[u].length() == 0) {
            hojas = hojas + String.valueOf(u);
            System.out.print(hojas + " ");
        } else if (V[u].length() >= 1) {
            for (int i = 0; i < V[u].length(); i++) {
                int w = V[u].get(i);
                if (!isMarcado(w)) {
                    printHojas1(w);
                }
            }           
        }
    }
    public boolean kAlcanzable(int u, int v, int k) {    // U PUEDE ALCANZAR V EN K PASOS O MAS.
        if (ExisteCamino(u, v)) {
            desmarcarTodos();
            return kAlcanzable1(u, v, k, 1);
        } else {
            return false;
        }

    }

    private boolean kAlcanzable1(int u, int v, int k, int pasos) {
        marcar(u);
        boolean alcanzado = false;
        for (int i = 0; i < V[u].length() && !alcanzado; i++) {
            int w = V[u].get(i);
            if (w == v && k >= pasos) {
                return true;
            } else {
                if (!isMarcado(w)) {
                    alcanzado = alcanzado || kAlcanzable1(w, v, k, pasos + 1);
                }
            }
        }
        return alcanzado;
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
    /*
        public boolean Nalcanzable(int u, int v, int pasos) {
        desmarcarTodos();
        return Nalcanzable(u, v, pasos, 0);
    }

    private boolean Nalcanzable(int u, int v, int pasos, int pasoActual) {
        marcar(u);
        if (u == v && pasoActual <= pasos) {
            return true;
        }

        boolean respuesta = false;
        for (int i = 0; i < V[u].length(); i++) {
            int w = V[u].get(i);
            if (!isMarcado(w)) {
                respuesta = respuesta || Nalcanzable(w, v, pasos, pasoActual + 1);
            }
        }
        return respuesta;
    }
      public boolean NAlcanzable(int a, int b, int pasos) {
        desmarcarTodos();
        LinkedList l = new LinkedList();
        return NAlcanzable1(a, b, pasos, l);
    }

    public boolean NAlcanzable1(int a, int b, int pasos, LinkedList l) {
        marcar(a);
        l.add(a);
        boolean respuesta=false;
        for (int i = 0; i < V[a].length(); i++) {
            int w = V[a].get(i);            
            if (!isMarcado(w)) {
                if (w == b && l.size()<= pasos) {                    
                    return true;
                }else{
                    respuesta= respuesta || NAlcanzable1(w, b, pasos, l);
                }
            }
        }
        l.removeLast();
        return respuesta;
    }
    */
        public void dfsinvertido(int v) {
        if (!isVerticeValido(v, "dfs")) {
            return;  //Validación. v no existe en el Grafo.
        }
        desmarcarTodos();
        System.out.print("DFS inv:");
        dfsinv(v);
        System.out.println();
    }

    private void dfsinv(int v) {  //mask-function de void dfs(int)
        System.out.print(" " + v);
        marcar(v);
        LinkedList<Integer> L1 = entrantes(v);
        for (int i = 0; i < L1.size(); i++) {   //for (cada w adyacente a v)
            int w = L1.get(i);
            if (!isMarcado(w)) {
                dfsinv(w);
            }
        }
    }

    public LinkedList<Integer> entrantes(int v) {
        LinkedList<Integer> L1 = new LinkedList<>();
        for (int i = 0; i < V.length; i++) {
            Lista L = V[i];
            if (L != null) {
                for (int j = 0; j < L.length(); j++) {
                    int w = L.get(j);
                    if (w == v) {
                        L1.add(i);
                    }
                }
            }

        }
        return L1;
    }
     public void delLazo(int u, int v) {
        if (!isVerticeValido(v, "dfs")) {
            return;  //Validación. v no existe en el Grafo.
        }
        if (ExisteCamino(u, v)) {
            desmarcarTodos();
            delLazo1(u, v);
        }
    }

    private void delLazo1(int u, int v) {
        marcar(u);
        for (int i = 0; i < V[u].length(); i++) {   //for (cada w adyacente a v)
            int w = V[u].get(i);
            delArista(u, w);            
                if (!isMarcado(w)) {
                    delLazo1(w, v);
                }           
        }        
    }
    
}
