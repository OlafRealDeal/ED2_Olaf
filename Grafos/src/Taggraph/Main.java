package Taggraph;

public class Main {

    public static void main(String[] args){
       Grafo G = new Grafo();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addArista(1, 40, 2);
       G.addArista(1, 60, 3);
       System.out.println("G="+G);
       
       G.printListas();
    }
}
