package Digraph;

public class Main {

    public static void main(String[] args){
       Grafo G = new Grafo();
       System.out.println("G="+G);
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();  
       G.addVertice();
       G.addArista(2, 8);
       G.addArista(8, 7);
       G.addArista(3, 6);
       G.addArista(3, 5);
       G.addArista(4, 0);
       G.addArista(5, 3);
       G.addArista(5, 4);
       G.addArista(6, 1);
       G.addArista(9, 9);
       
       G.printListas();
       
//       G.bfs(0);
//G.dfs(0);
//System.out.println(G.path(0));
//        System.out.println(G.path(1));
//        System.out.println(G.path(2));
//        System.out.println(G.path(3));
//        System.out.println(G.path(4));
//        System.out.println(G.path(5));
//        System.out.println(G.path(6));
//        System.out.println(G.path(7));
//        System.out.println(G.path(8));
//G.printHojas(0);
//G.printHojas(1);
//G.printHojas(2);
//G.printHojas(3);
//G.printHojas(4);
//System.out.println(G.kAlcanzable(5, 0, 3));

//G.dfsinvertido(1);


    }
}
