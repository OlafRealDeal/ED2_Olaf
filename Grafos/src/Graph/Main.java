package Graph;

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
       G.addArista(0, 3);
       G.addArista(0, 4);  
       G.addArista(3, 1);
       G.addArista(2, 5);
       G.addArista(2, 6);
       G.addArista(7, 8);

       System.out.println("G="+G);
       G.printListas();
//       G.dfs(0);
//        System.out.println(G.conexo());
//        G.cantIslas();
//        System.out.println(G.hayCamino(0, 3));   
//System.out.println(G.isLineal());
//System.out.println(G.costoCamino(0, 1));
//G.islaMenor();
//System.out.println(G.islaMenorMejorada());
    }
}
