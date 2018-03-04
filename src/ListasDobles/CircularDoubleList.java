package ListasDobles;

public class CircularDoubleList<T> {

    private Node<T> cabeza;
    private Node<T> cola;

    //Constructor
    public CircularDoubleList() {
        this.cabeza = this.cola = null;
    }

    //Constructor
    public CircularDoubleList(T dato) {
        ListasDobles.Node<T> n = new ListasDobles.Node<>(dato);
        this.cabeza = this.cola = n;
        this.cabeza.setNext(this.cola);
        this.cola.setNext(this.cabeza);
    }

    //Verificar si la lista está vacia
    public boolean estaVacia() {
        return this.cabeza == null;
    }

    //Regresa el tamaño de la lista
    public int tamaño() {
        if (estaVacia()) {
            return 0;
        } else {
            Node<T> aux = this.cabeza;
            int i = 0;
            do {
                aux = aux.getNext();
                i++;
            } while (aux != this.cabeza);
            return i;
        }
    }

    //Insertar un nodo de primero
    public void insertarPrimero(T dato) {
        Node<T> n = new Node<>(dato);
        if (estaVacia()) {
            this.cabeza = this.cola = n;
            this.cabeza.setNext(this.cola);
            this.cola.setPrev(this.cabeza);
        } else {
            n.setNext(this.cabeza);
            this.cola.setNext(n);
            this.cabeza = n;
            this.cabeza.getNext().setPrev(n);
            n.setPrev(this.cola);
        }
    }

    //Insertar un nodo de ultimo
    public void insertarUltimo(T dato) {
        Node<T> n = new Node<>(dato);
        if (estaVacia()) {
            this.cabeza = this.cola = n;
            this.cabeza.setNext(this.cola);
            this.cola.setPrev(this.cabeza);
        } else {
            n.setNext(this.cabeza);
            this.cola.setNext(n);
            this.cola = n;
            this.cabeza.getNext().setPrev(n);
            n.setPrev(this.cola);
        }
    }
    
    //Insertar un nodo en cualquier posición
    public void insertar(T dato, int i) {
        Node<T> n = new Node<>(dato);
        if (estaVacia() || i == 0) {
            this.insertarPrimero(dato);
        } else if (i == tamaño() - 1){
            this.insertarUltimo(dato);
        }else if (i < 0){
            this.insertar(dato,tamaño() + i);
        }else if(i > tamaño()){
            System.out.println("Error");
        }else{
            Node<T> aux = this.cabeza;
            int cont = 0;
            while(cont < i - 1){
                aux = aux.getNext();
                cont++;
            }
            n.setNext(aux.getNext());
            aux.setNext(n);
            n.getNext().setPrev(n);
            n.setPrev(aux);
        }
    }
    
    //Eliminar un nodo en la primera posición
    public T eliminarPrimero(){
        if(estaVacia()){
            return null;
        }else{
            Node<T> aux = this.cabeza;
            this.cabeza = this.cabeza.getNext();
            this.cola.setNext(this.cabeza);
            this.cabeza.setPrev(this.cola);
            aux.setNext(null);
            aux.setPrev(null);
            return aux.getDato();
        }
         
    }
    
    //Eliminar un nodo en la ultima posición
    public T eliminarUltimo(){
        if(estaVacia()){
            return null;
        }else{
            Node<T> aux = this.cola;
            this.cola = this.cola.getPrev();
            this.cola.setNext(this.cabeza);
            this.cabeza.setPrev(this.cola);
            aux.setNext(null);
            aux.setPrev(null);
            return aux.getDato();
        }
         
    }
    
    //Eliminar un nodo en cualquier posición
    public T eliminar(int i){
        if(estaVacia()||i == 0){
            return this.eliminarPrimero();
        }else if (i == tamaño() - 1){
            return this.eliminarUltimo();
        }else if (i < 0){
            return this.eliminar(tamaño() + i);
        }else if(i > tamaño()){
            System.out.println("Error");
            return null;
        }else{
            Node<T> aux = this.cabeza;
            int cont = 0;
            while(cont < i - 1){
                aux = aux.getNext();
                cont++;
            }
            Node<T> temp = aux.getNext().getNext();
            Node<T> eliminado = aux.getNext();
            aux.setNext(temp);
            temp.setPrev(aux);
            eliminado.setNext(null);
            eliminado.setPrev(null);
            return eliminado.getDato();
        }
         
    }
}