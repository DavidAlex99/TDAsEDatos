/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Heap;

import java.util.Comparator;

/**
 *
 * @author alexx
 */
public class Heap<E> {
    private Comparator<E> cmp;
    private E[] arreglo;
    private int MAX=100;
    private int effectiveSize;
    private boolean isMax;
    
    public Heap(Comparator<E> cmp){
        this.cmp = cmp;
        this.effectiveSize = 0;
        this.arreglo = (E[]) new Object[MAX];
    }
    
    //devuelve el indice del padre del indice que se esta pasando
    // (el inidce que se pasa podria ser de un hijo)
    public int parent(int index){
        return (index - 1)/2;
    }
    
    /*
    Los siguientes metodos es para averiguar los indices derecho e izquierdo
    de la posicion que se esta pasando
    */
    public int leftChild(int index){
        return (2*index)+1;
    }
    
    public int rightChild(int index){
        return (2*index)+2;
    }
    
    //si de la mitad del effectiveSize hasta todo, se enceuntra el indice que se pasa
    // entonces es ua hoja
    public boolean isLeaf(int index){
        return (index>=(this.effectiveSize/2)) && (index<this.effectiveSize);
    }
    
    public boolean isEmpty(){
        return this.effectiveSize == 0;
    }
    
    public void swap(int firstIndex, int lastIndex){
        E content = arreglo[firstIndex];
        arreglo[firstIndex] = arreglo[lastIndex];
        arreglo[lastIndex] = content;
    }
    
    /*
    como argumento se le pasa el indice del nodo padre, tya que en el nodo
    padre es que se hace el ajuste
    */
    private void ajustar(int index){
        //se pone como la raiz es el mayor de todos temporalmente
        int indexMayor =  index;
        //hjio izquierdo
        int indexIzq = leftChild(index);
        //hijo derecho
        int indexDer = rightChild(index);
        
        //comparaciones entre el nodo padre y cada hijo izquierdo y derecho
        if(indexIzq < this.effectiveSize && cmp.compare(arreglo[indexIzq], arreglo[index])>0){
            indexMayor = indexIzq;
        }
        
        if(indexDer < this.effectiveSize && cmp.compare(arreglo[indexDer], arreglo[index])>0){
            indexMayor = indexDer;
        }
        
        if(indexMayor != index){
            swap(index, indexMayor);
            /*
            Ajusta el indice mayor
            
            Puesto que ajustar() va de los indices actual hasta abajo, por eso se hace
            ajustar ajustar(indexMayor)
            */
            ajustar(indexMayor);
        }
    }
    
    /*
    Metodo para construir un Heap a artir de un array con algunso de sus 
    elementos desordenados
    Este metodo a diferencia de ajustar(), su direccion es de abajo hacia arraiba, o en vista
    del array, va del indice del medio(ultimo padre) hasta la raiz de todo el arbol
    */
    public void makeHeap(){
        int i;
        for(i = (this.effectiveSize/2)-1; i>=0; i--){
            ajustar(i);
        }
    }
    
    /*
    desencolar() ssiempre elimina el primer elemmento (raiz) del HEAP
    */
    public E desencolar(){
        E maxValue;
        if(!this.isEmpty()){
            //siempre el desencolar, es que el primer elemento(raiz) es el que sale
            maxValue = this.arreglo[0];
            //se hace un cambio entre el elemento de la primera posicion con el ultimo elemento
            swap(0, this.effectiveSize - 1);
            this.effectiveSize--;
            //se empieza a ajustar desde la raiz (cuyo cambio se hizo con el ultimo elemento)
            ajustar(0);
            return maxValue;
        }
        return null;
    }
    
    /*
    encolar() anade el elemento al final del arreglo y se va comparamdo con el padre, es decir se va construyendo
    */
    public void encolar(E element){
        if(this.effectiveSize >= arreglo.length){
            throw new IndexOutOfBoundsException("El heap está lleno");
        }
        arreglo[this.effectiveSize] = element;
        
        this.effectiveSize++;
        //se recostruye el heap, ya que tiene que comprarar de abajo hacia arriba
        makeHeap();
    }
    
    public void testeo() {
        for (int i = 0; i <= this.effectiveSize/2; i++) {
            System.out.print("padre: " + arreglo[i] + " izq : " +
                    arreglo[leftChild(i)] + " der :" + arreglo[rightChild(i)]);
            System.out.println("\n");
        }
    }
} 
