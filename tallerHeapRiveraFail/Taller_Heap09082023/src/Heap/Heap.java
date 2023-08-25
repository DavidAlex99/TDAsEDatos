/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Heap;

import java.util.Comparator;

/**
 *
 * @author CltControl
 */
public class Heap<E> {
    private int MAX = 100;
    private int effectiveSize;
    private Comparator<E> cmp;
    private E[] arreglo;
    
    public Heap(Comparator<E> cmp){
        this.arreglo = (E[]) new Object[MAX];
        this.effectiveSize = 0;
        this.cmp = cmp;
    }
    
    private int hijoIzqIndex(int indexPadre){
        return (indexPadre*2)+1;
    }
    
    private int hijoDerIndex(int indexPadre){
        return (indexPadre*2)+2;
    }
    
    //metodo para cambiar posicion etre dos nodos
    private void cambio(int menorIndex, int superiorIndex){
        E content = arreglo[menorIndex];
        arreglo[menorIndex] = arreglo[superiorIndex];
        arreglo[superiorIndex] = content;
    }
    
    //se hace el ajuste desde la raiz del arbol(subarbol) con sus hijos, de arriba hacia abajo
    private void ajustar(int indexPadre){
        int indexMayor = indexPadre;
        int indexDer = hijoDerIndex(indexPadre);
        int indexIzq = hijoIzqIndex(indexPadre);
        //caso Base para salir de la recursividad
        if(indexPadre != indexMayor){
            //Si alguna o ambas condicionales anteriores se cumplieron, se compara con el valor de arreglo[indexPadre]
            cambio(indexPadre, indexMayor);
            ajustar(indexMayor);
        }
   
        //comparacion de la raiz del subarcbol con hijo izquierdo y derecho
        if(indexIzq < effectiveSize && cmp.compare(arreglo[indexIzq], arreglo[indexMayor])>0){
            indexMayor = indexIzq;
        }
        
        if(indexDer < effectiveSize && cmp.compare(arreglo[indexDer], arreglo[indexMayor])>0){
            indexMayor = indexDer;
        }
        
        System.out.println("IndexPadre: " + indexPadre);
        System.out.println("IndexIzq: " + indexIzq);
        System.out.println("IndexDer: " + indexDer);
        System.out.println("EffectiveSize: " + effectiveSize);
        
        
        
        
                
    }
    
    //reconstruir el heap desde abajo hacia arriba
    public void reconstruir(){
        int i;
        for(i = (this.effectiveSize/2)-1; i>=0; i--){
            ajustar(i);
        }
    }
    
    //elemento se anade al ultimo del HEAP
    public boolean encolar(E content){
        if(content == null){
            return false;
        }
        
        //content imprime el metodo toString
        arreglo[this.effectiveSize] = content;
        
        System.out.println("Contenido en encolar(): " + content);
        
        effectiveSize++;
        
        System.out.println("effectiveSize en encolar(): " + effectiveSize);
        
        
        System.out.println("testeo en encolar(): ");
        testeo();
        //por ende haay que reconstruir desde abajo hacia arriba
        reconstruir();
        return true;
    }
    
    //Se remueve el primer elemento del HEAP
    //se reemplaza el ultimo elemento con el primero
    //se ajusta desde el primer elemento(que antes era ultimo) del HEAP
    public E desencolar(){
        E removedContent = arreglo[0];
        
        arreglo[0] = arreglo[this.effectiveSize-1];
        
        effectiveSize--;
        
        ajustar(0);
        
        return removedContent;
    }
    
    public void testeo() {
        for (int i = 0; i <= this.effectiveSize/2; i++) {
            System.out.print("padre: " + arreglo[i] + " izq : " +
                    arreglo[hijoIzqIndex(i)] + " der :" + arreglo[hijoDerIndex(i)]);
            System.out.println("\n");
        }
    }

    
}
