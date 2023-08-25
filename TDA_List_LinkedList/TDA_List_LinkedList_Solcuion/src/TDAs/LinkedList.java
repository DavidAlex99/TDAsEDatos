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
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
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
        if (!isEmpty()) {
            if (size() == 1) {
                last = null;
            }
            NodeList<E> node = first;
            first = first.getNext();
            node.setNext(null);
            return node.getContent();
        }
        return null;
    }

    @Override
    public E removeLast() {

        if (!isEmpty()) {
            NodeList<E> deleted = last;
            if (first == last) {
                return removeFirst();
            }
            if (first.getNext() == last) {

                last = first;
                first.setNext(null);
                return deleted.getContent();
            }
            NodeList<E> n;
            for (n = first; n.getNext().getNext() != last; n = n.getNext()) {
            }
            last = n.getNext();
            n.getNext().setNext(null);
            return deleted.getContent();
        }
        return null;
    }

    @Override
    public int size() {
        //metodo para hacer un conteo mediante el uso de un FOR
        int cont = 0;
        if (!isEmpty()) {
            NodeList<E> t;
            for (t = this.getFirst(); t != null; t = t.getNext()) {
                cont++;
            }
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    @Override
    public void add(int index, E element) {
        int size = size();
        if (element != null && index >= 0 && index <= size) {
            if (index == 0) {
                addFirst(element);
                //si el indice es igual al valorde SIZE entonces se anade al ultimo
            } else if (index == size) {
                addLast(element);
            } else {
                //si se quiere anade un elemento en un indice diferente
                NodeList<E> n = first;
                for (int i = 0; i < index - 1; i++) {
                    n = n.getNext();
                }
                NodeList<E> newNode = new NodeList(element);
                newNode.setNext(n.getNext());
                n.setNext(newNode);
            }
        }
    }

    @Override
    public E remove(int index) {
        int size = size();
        if (index >= 0 && index < size) {
            if (index == 0) {
                return removeFirst();
            } else if (index == size - 1) {
                return removeLast();
            } else {
                //nodo que hara referencia, porque se pondra ultimo del nodo a eliminar
                NodeList<E> n = first;

                for (int i = 0; i < index - 1; i++) {
                    n = n.getNext();
                }
                //se crea el nodo que se va a eliminar
                NodeList<E> deleted = n.getNext();
                n.setNext(n.getNext().getNext());
                deleted.setNext(null);
                return deleted.getContent();
            }
        }
        return null;
    }

    @Override
    public E get(int index) {
        int size = size();
        if (index >= 0 && index < size) {
            NodeList<E> n = first;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            return n.getContent();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        int size = size();
        if (index >= 0 && index < size) {
            NodeList<E> n = first;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            E old = n.getContent();
            n.setContent(element);
            return old;
        }
        return null;
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
