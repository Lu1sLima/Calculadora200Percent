public class Pilha{

    private Node header;
    private Node trailer;
    private int count;

    public class Node{
        public Double element;
        public Node next;
        public Node prev;
        
        public Node(Double element){
            this.element = element;
            next = null;
            prev = null;
        }
    }

    public Pilha(){
        header = new Node(null);
        trailer = new Node(null);
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    public void push(Double element){
        Node n = new Node(element);
        trailer.prev.next = n;
        n.prev = trailer.prev;
        trailer.prev = n;
        n.next = trailer;
        count++;

    }

    public Double pop(){
        if(count == 0)
            throw new IndexOutOfBoundsException();
        Double qual = trailer.prev.element;
        trailer.prev.prev.next = trailer;
        trailer.prev = trailer.prev.prev;
        count--;
        return qual;
    }

    public Double top(){
        return trailer.prev.element;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void clear(){
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        Node aux = header;
       for(int i = 0; i < count; i++){
           aux = aux.next;
            s.append(aux.element);
            if(i != count-1){
                s.append(", ");
            }
        }
        return s.toString();
    }

}