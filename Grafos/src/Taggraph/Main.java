package Taggraph;

public class Main {

    public static void main(String[] args){
       Grafo G = new Grafo();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addArista(0, 20, 2);
       G.addArista(1, 5, 4);
       G.addArista(1, 40, 2);
       G.addArista(3, 10, 1);
       G.addArista(3, 200, 2);
       G.addArista(3, 20, 4);
       G.addArista(4, 5, 0);
       System.out.println("G="+G);
       
       
       G.printListas();
////        System.out.println(G.shortestPath(3, 2));
////        System.out.println(G.shortestPath(3, 0));
////System.out.println(G.isAlcanzable(1, 0, 2));
////System.out.println(G.isAlcanzable(1, 0, 3));
////        System.out.println(G.isAlcanzable(3, 2, 10));

G.cantCaminos(3, 0);
    }
}
