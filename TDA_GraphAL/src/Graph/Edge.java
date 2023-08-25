/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

/**
 *
 * @author CltControl
 */
public class Edge<E, V> {
    private Vertex<V, E> source;
    private Vertex<V, E> target;
    private E metadata;
    private int weight;
    
    public Edge(Vertex<V, E> source, Vertex<V, E> target){
        this.metadata = null;  
        this.weight = 0;
        this.source = source;
        this.target = target;
    }
    
    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight){
        this.metadata = null;  
        this.weight = weight;
        this.source = source;
        this.target = target;
    }
    
    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight, E metadata){
        this.metadata = metadata;
        this.weight = weight;
        this.source = source;
        this.target = target;
    }
    
    //

    public Vertex<V, E> getSource() {
        return source;
    }

    public void setSource(Vertex<V, E> source) {
        this.source = source;
    }

    public Vertex<V, E> getTarget() {
        return target;
    }

    public void setTarget(Vertex<V, E> target) {
        this.target = target;
    }

    public E getMetadata() {
        return metadata;
    }

    public void setMetadata(E metadata) {
        this.metadata = metadata;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
}
