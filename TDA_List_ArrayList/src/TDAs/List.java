package TDAs;

public interface List<E> {
    
    /*** Métodos que deben ser implementados en el taller de la clase ArrayList ****/

    public int size();

    public boolean isEmpty();

    public void clear();

    public boolean addFirst(E element); // inserta el elemento element al inicio

    public boolean addLast(E element); // inserta el elemento element al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast(); // remueve el elemento al final de la lista

    public boolean add(int index, E element); // inserta element en la posición index

    public E remove(int index); // remueve y retorna el elemento en la posición index

    public E get(int index); // retorna el elemento ubicado en la posición index

    public E set(int index, E element); // setea el element en la posición index

    public String toString();
    
    // MÉTODOS DEL TALLER
    
    public void reverse();
    
    public List<E> subList(int from, int to);
    
    public boolean removeFirstNElements (int n);
    
    public void shuffle ();
    
    public void rotate (int k);

}
