package Digraph;

public class Main {

    public static void main(String[] args){
       Grafo G = new Grafo();
       System.out.println("G="+G);
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addArista(0, 1);
       G.addArista(0, 2);
       G.printListas();
       
       G.bfs(0);
    }
}
