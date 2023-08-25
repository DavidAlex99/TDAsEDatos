package TDAs;

import java.util.Random;



public class ArrayList<E> implements List<E> {

    private int MAX_SIZE = 100;
    private E[] elements = null;
    private int effectiveSize = 0;

    public ArrayList() {
        this.elements = (E[]) (new Object[MAX_SIZE]);
        this.effectiveSize = 0;
    }

    private boolean isFull() {
        return elements.length == effectiveSize;
    }

    private void addCapacity() {
        E[] tmp = (E[]) new Object[MAX_SIZE * 2];
        for (int i = 0; i < MAX_SIZE; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        MAX_SIZE = MAX_SIZE * 2;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        effectiveSize = 0;
    }

    @Override
    public String toString() {
        String result = "[";
        if (isEmpty()) {
            return result + "]";
        } else {
            for (int i = 0; i < effectiveSize - 1; i++) {
                result += elements[i].toString() + ", ";
            }
            result += elements[effectiveSize - 1].toString() + "]";
            return result;
        }
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (MAX_SIZE == effectiveSize) {
            addCapacity();
        }
        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

    /* EN ESTE TALLER, USTED DEBE IMPLEMENTAR LOS SIGUIENTES MÉTODOS */
    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (MAX_SIZE == effectiveSize) {
            addCapacity();
        }
        int index = effectiveSize;
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(this.effectiveSize - 1);
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
        E elementToRemove = null;
        if (this.isEmpty() || index >= this.effectiveSize || index<0) {
            throw new IndexOutOfBoundsException();
        } else {
            elementToRemove = elements[index];
            for (int i = index; i < this.effectiveSize - 1; i++) {
                elements[i] = elements[i + 1];
            }
            this.effectiveSize--;
        }
        return elementToRemove;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements[index];
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    public void concat(ArrayList<E> newArrayList) {
        // Verificamos que el ArrayList recibido no sea null ...(1)
        if (newArrayList != null) {
            //Recorremos el ArrayList entrante
            for (int i = 0; i < newArrayList.size(); i++) {
                //Si nuestro ArrayList local se llena ...(2)
                if (isFull()) {
                    //(2)...aumentamos su capacidad 
                    addCapacity();
                }
                //Concatenamos al final del arreglo local
                //los elementos del arreglo entrante
                elements[effectiveSize++] = newArrayList.get(i);
            }
        } else {
            // (1)... caso contrario lanzamos una excepción 
            throw new NullPointerException();
        }
    }

    public static boolean separarParesImpares(ArrayList<Integer> lista) {
        if (lista == null) {
            return false;
        }
        if (lista.isEmpty()) {
            return false;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) % 2 == 1) {
                temp.addLast(lista.remove(i));
                i--;
            }
        }
        lista.concat(temp);
        return true;
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
        if (n <= 0 || n > effectiveSize) {
            return false;
        }
        for (int i = n; i < effectiveSize; i++) {
            elements[i - n] = elements[i];
        }
        effectiveSize -= n;
        return true;
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = effectiveSize - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            E temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        }
    }

    @Override
    public void rotate(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Invalid rotation amount");
        }

        int rotation = k % effectiveSize;
        if (rotation == 0) {
            return;  // No rotation needed
        }

        E[] temp = (E[]) new Object[effectiveSize];
        for (int i = 0; i < effectiveSize; i++) {
            temp[i] = elements[i];
        }

        for (int i = 0; i < effectiveSize; i++) {
            elements[(i + rotation) % effectiveSize] = temp[i];
        }
    }
}
