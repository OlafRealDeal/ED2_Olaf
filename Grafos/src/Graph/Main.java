package Graph;

public class Main {

    public static void main(String[] args){
       Grafo G = new Grafo();
       System.out.println("G="+G);
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addArista(0, 1);
       G.addArista(0, 2);
       G.addArista(2, 2);
       System.out.println("G="+G);
       
       
       G.printListas();
       G.bfs(0);
    }
}
