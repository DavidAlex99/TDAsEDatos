/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea_stack;

/**
 *
 * @author alexx
 */
public class Nodo<T> {
    private T elemento;
    //es decir el elemento que se encuentra por debajo del top, que sale cuando se le hace pop() al nodo top
    private Nodo<T> siguiente;
    
    public Nodo(T elemento, Nodo<T> siguiente){
        this.elemento = elemento;
        this.siguiente = siguiente;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
