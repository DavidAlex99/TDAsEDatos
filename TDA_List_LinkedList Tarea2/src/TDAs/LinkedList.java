package TDAs;

public class LinkedList<E> implements List<E> {

    private NodeList<E> first;
    private NodeList<E> last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    public NodeList<E> getFirst() {
        return first;
    }

    public void setFirst(NodeList<E> first) {
        this.first = first;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    @Override
    public boolean addFirst(E e) {
        //Si el parametro representa un nodo con contenido vacio
        if (e == null) {
            return false;
        }
        //creacion de un nuevo nodo
        NodeList<E> nuevo = new NodeList<>(e);
        //asigna el nodo que esta apuntando a first al nuevo nodo. el first que se usa en getFirst se refiere
        //al metodo de esta clase
        nuevo.setNext(this.getFirst());
        if (this.isEmpty()) {
            this.setLast(nuevo);
        }
        this.setFirst(nuevo);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        if (this.isEmpty()) {
            this.setFirst(nuevo);
        } else {
            this.getLast().setNext(nuevo);
        }

        this.setLast(nuevo);

        return true;
    }

    @Override
    public E removeFirst() {
        //Nodo que nos permitira anular la referencia hacia el primer nodo
        NodeList<E> temp;
        E e = null;
        if(this.isEmpty()){
            return e;
        }
        //Si solo hay un nodo, se debehacer que la referencia hacia este primer nodo sea nula
        if(this.first.getNext()== null){
            temp = this.first;
            e = this.first.getContent();
            //asignamos la direccion de null
            temp.setNext(null);
        }
        //si el linkedlist tiene mas nodos
        temp = first.getNext();
        e = first.getContent();
        first.setNext(null);
        first = temp;
        
        
        return e;
    }

    @Override
    public E removeLast() {
        E e = null;
        //verificacion si el LinkedList se encuentra vacio
        if(this.isEmpty()){
            return e;
        }
        for(NodeList<E> iterador = this.first; iterador.getNext() != null; iterador.getNext()){
            //en el for, el nodo iterador llega al nodo antes del ultimo debido al condicional
            if(iterador.getNext()==this.last){
                e = this.last.getContent();
                iterador.setNext(null);
            }
    }
        return e;
    }

    @Override
    public int size() {
        int cont = 0;
        NodeList<E> t;
        for (t = this.getFirst(); t != null; t = t.getNext()) {
            cont++;
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    @Override
    public void clear() {
        //nodo iterador
        NodeList<E> temp = this.first;
        while(temp != null){
            //se hace el borrado desde la cabea hasta el final
            temp = first.getNext();
            first.setNext(null);
            first = temp;
        }
    }

    @Override
    public void add(int index, E element) {
        if(this.isEmpty() && index == 0){
            first.setContent(element);
        }
        int contador = 0;
        for(NodeList<E> nodoI = this.first; nodoI.getNext() != null; nodoI.getNext()){
            if(index == contador){
                NodeList<E> temp = first.getNext();
                nodoI.setContent(element);
                temp.setNext(nodoI);
            }
            //la variable contador va a contenedor como nuermo los nodos visitados pero exceptundo el ultimo
        contador++;
        }
    }

    @Override
    public E remove(int index) {
        E e = null;
        if(index < 0){
            //Se encuentra como null
            return e;
        }
        if(index == 0){
            //Corresponde al primer nodo, cabecera del LinkedList
            return this.first.getContent();
        }
        //A partir de aqui este fragmento lo consideraremos cuando el nodo se encuentra en posiciones intermedias de la
        //lista enlazada
        int contador = 1;
        //la variable contador lo inicilizamos con el valor de 1, con el motivo que cuando coincida con el valor del indice
        //que se esta pasando como parametro, el nodo iterador va a ser el nodo anterior al nodoque se quiere eliminar
        for(NodeList<E> iterador = this.first; iterador.getNext() != null; iterador.getNext()){
            if(index == contador){
                e = iterador.getContent();
                iterador.setNext(iterador.getNext().getNext()); 
            }
            contador++;
        }
        return e;
    }

    @Override
    public E get(int index) {
        E e = null;
        if(index < 0){
            //Se encuentra como null
            return e;
        }
        if(index == 0){
            //Corresponde al primer nodo, cabecera del LinkedList
            return this.first.getContent();
        }
        int contador = 0;
        //For donde se crea que actuara como un pointer iterador empezando desde el nodo first, con la condicion de que 
        //el nodo iterador sea diferente a null, puesto que anteriormente se uso la condicion cuando el indice era igual
        //al elemnto final, por redundancia el ultimo elemnto tiene como getnext() el valor de null
        for(NodeList<E> iterador = this.first; iterador.getNext() != null; iterador.getNext()){
            if(index == contador){
                e = iterador.getContent();
            }
            contador++;
        }
        return e;
    }

    @Override
    //Metodo para ubicar elemnto en un nodo que se encuentra en un indice especifico
    public E set(int index, E element) {
        //Se cuenta la cantidad de elemntos y dara una idea de los indices
        int contador = 0;
        NodeList<E> nodoI;
        for(nodoI = this.first; nodoI.getNext() != null; nodoI.getNext()){
            if(index == contador){
                nodoI.setContent(element);
            }
            //la variable contador va a contenedor como nuermo los nodos visitados pero exceptundo el ultimo
            contador++;
        }
        //este incremento igualar con el indice del ultimo nodo
        contador++;
        if(index == contador){
            this.last.setContent(element);
            return element;
        }
        else if(index > contador || index < 0){
            return null;
        }
        return element;
    }

    @Override
    public String toString() {
        String s = "";
        NodeList<E> t;
        for (t = this.getFirst(); t != null; t = t.getNext()) {
            s += t.getContent() + " ";
        }
        return s;
    }

}
