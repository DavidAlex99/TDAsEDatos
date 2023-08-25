/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author CltControl
 */
public class Vertex<V, E> {
    private V content;
    private List<Edge<E, V>> aristas;
    private boolean  visited;
    
    public Vertex(V content){
        this.content = content;
        this.aristas = new ArrayList<>();
    }
    
    //metodo para anadir las aristas del presente vertice en la lista
    public void addArista(Edge<E, V> arista){
        aristas.add(arista);
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public List<Edge<E, V>> getAristas() {
        return aristas;
    }

    public void setAristas(List<Edge<E, V>> aristas) {
        this.aristas = aristas;
    }
    
    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public Iterator<Edge<E, V>> iterator() {
        return new VertexIterator(); // Devuelve una instancia de tu clase Iterator personalizada
    }
    
    //para iterar sobre la lista de aristas, perteneiente a este referencia de vertice
    private class VertexIterator implements Iterator<Edge<E, V>> {
        private int currentIndex;

        public VertexIterator() {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < aristas.size();
        }

        @Override
        public Edge<E, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return aristas.get(currentIndex++);
        }
    }
}
