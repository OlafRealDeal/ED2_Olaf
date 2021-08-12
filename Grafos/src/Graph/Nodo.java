package Graph;

public class Nodo {     //Nodo que usa la class Lista
    public int Data;
    public Nodo Link;

    public Nodo(){
        Link = null;
    }
    
    public Nodo(int Data) {
        this.Data = Data;
        this.Link = null;
    }

    public int getData() {
        return Data;
    }

    public void setData(int Data) {
        this.Data = Data;
    }

    public Nodo getLink() {
        return Link;
    }

    public void setLink(Nodo Link) {
        this.Link = Link;
    }
}
