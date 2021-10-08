package Preg2;

public class Nodo {     //Nodo de una Single-List. Puede adicionar mas campos.
    public int Data;
    public Nodo Link;

    public Nodo(){
        this(0);
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
    
    
    @Override
    public String toString(){
        return ""+Data;
    }
}
