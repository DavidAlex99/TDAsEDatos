package tda;

public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int MAX_SIZE = 100;
    private int effectiveSize;

    public ArrayList() {
        // elements = new E[100]; // NO vale
        elements = (E[]) new Object[MAX_SIZE];
        effectiveSize = 0;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return this.effectiveSize == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.effectiveSize; i++) {
            elements[i] = null;
        }
        this.effectiveSize = 0;
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            //throw new NullPointerException("Null elements are not accepted");
            return false;
        } else if (isEmpty()) {

            // si se quiere hacer el bacán
            // elements[effectiveSize++] = element;
            // con humildad
            elements[effectiveSize] = element;
            effectiveSize++;

            return true;
        }
        if (isFull()) {
            addCapacity();
        }
        // empujar para hacer espacio. Bit shifting
        for (int i = effectiveSize; i > 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        //con humildad
        //elements[effectiveSize] = element;
        //effectiveSize++;
        // con orgullo
        elements[effectiveSize++] = element;
        return true;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > effectiveSize) {
            // throw new IndexOutOfBoundsException("Invalid index: " + index);
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
            // elements[i+1] = elements[i]; MAL
        }
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E remove(int index) {
        E elementRemoved = null;
        if (index < 0) {
            return null;
        } else if (this.effectiveSize == 0 && index > 0) {
            return null;
        } else if (index >= this.effectiveSize) {

        }
        elementRemoved = elements[index];
        for (int i = index; i < this.effectiveSize; i++) {
            elements[i] = elements[i + 1];
        }
        this.effectiveSize--;

        return elementRemoved;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        E elementOld = null;
        if (index < 0 || effectiveSize == 0) {
            return null;
        }
        elementOld = elements[index];
        elements[index] = element;

        return elementOld;
    }

    private void addCapacity() {
        MAX_SIZE = MAX_SIZE * 2;
        E[] newElements = (E[]) new Object[MAX_SIZE];
        // copiando los elementos del arreglo viejo al nuevo
//        for (int i= 0; i<effectiveSize; i++) {
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        this.elements = newElements;
    }

    private boolean isFull() {
        return effectiveSize == MAX_SIZE;
    }

    public String toString() {
        String cadena = " ";
        int sizeExample = effectiveSize;
        int iterador;
        /*for(int i = 0; i < this.effectiveSize; i++){
            cadena = cadena + elements[i] + ", ";*/
        for (iterador = 0; iterador < sizeExample; iterador++) {
            cadena = cadena + elements[iterador] + ", ";
        }
        iterador++;
        if(iterador == effectiveSize){
            cadena = cadena + elements[iterador];
        }
        return cadena;
    }

    @Override
    public void reverse() {
        E[] newElements = (E[]) new Object[MAX_SIZE];

        int iteradorReverse = 0;
        for (int i = this.effectiveSize - 1; i >= 0; i--) {
            newElements[iteradorReverse] = elements[i];
            System.out.println(iteradorReverse);
            iteradorReverse++;
        }
        elements = newElements;
    }

    //argumentos son indices
    @Override
    public List<E> subList(int from, int to) {
        List<E> subList = new ArrayList<>();
        int iteradorSub = 0;
        for (int i = from - 1; i < to; i++) {
            subList.addLast(elements[i]);
            iteradorSub++;
        }
        return subList;
    }

    @Override
    public boolean removeFirstNElements(int n) {
        if (n <= 0) {
            return false;
        } else if (effectiveSize == 0) {
            return false;
        }
        for (int iterador = 0; iterador < n; iterador++) {
            remove(0);

        }
        return true;
    }

}
