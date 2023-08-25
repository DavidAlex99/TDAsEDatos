/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea_stack;

/**
 *
 * @author alexx
 */
public class StackDinamica<T> {
    //unico nodo qu se necesita que es el que se encuentra en el top
    private Nodo<T> top;
    //tamano de la pila
    private int tamano;
    
    //Siempre un constructor que incialize cuando el STACK es vacio
    public StackDinamica(){
        this.top = null;
        this.tamano = 0;
    }
    
    //metodo para saber si el STACK se encuentra vacio
    public boolean isEmpty(){
        return top == null;
    }
    
    //metodo para saber el tamano de la pila
    public int size(){
        return this.tamano;
    }
    
    //metodo para devolver el internior del nodo que se encuentr en el top
    public T top(){
        if(isEmpty()){
            return null;
        }else{
            return top.getElemento();
        }
    }
    
    public void push(T elemento){
        //el parametro se le pasa al nuevo nodo que sera el nuevo top, mietras
        //que el top anterior viene a ser el siguiente, es decir el nodo debajo
        //del nuevo top que estamos creando
        Nodo<T> ele = new Nodo<>(elemento, top);
        this.top = ele;
        this.tamano++;
    }
    
    public T pop(){
        if(isEmpty()){
            return null;
        }else{
            //guardamo el elemento del nodo top que estamos haciendo top()
            T elemento = top.getElemento();
            /*
            Despues de eliminar el top, es como que el nodo que se encoentraaba en el top entonces tenemos que ir al siguiente
            elemento que se encuentra abajao del 
            */
            //Esta es una referencia hacia el elmento que se encuentra abajo del top
            Nodo<T> aux = top.getSiguiente();
            top = null;
            top = aux;
            this.tamano--;
            return elemento;
        }    
    }
    
    public String toString(){
        if(isEmpty()){
            return "La pila esta vacia";
        }else{
            Nodo<T> aux = top;
            String resultado = "";
            while(aux!=null){
                //Como los nodos se recorre de arriba hacia abajo esta facil
                resultado += aux.toString();
                aux = aux.getSiguiente();
            }
            return resultado;
        }
    }
    
    public T peek(){
        return top.getElemento();
    }
}
