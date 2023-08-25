package tda;

import java.util.LinkedList;
import java.util.List;

class Vertex<V, E> {

    private V content;
    private List<Edge<E, V>> edges;

    public Vertex(V content) {
        this.content = content;
        this.edges = new LinkedList<>();
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public List<Edge<E, V>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<E, V>> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return content.toString();
    }

}
