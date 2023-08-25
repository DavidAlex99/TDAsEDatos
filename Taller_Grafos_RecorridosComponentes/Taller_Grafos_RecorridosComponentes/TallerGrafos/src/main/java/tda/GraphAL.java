package tda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphAL<V, E> implements Graph<V, E> {

    private List<Vertex> vertices;
    private boolean isDirected;
    private Comparator<V> cmpVertices;
    private Comparator<E> cmpEdges;

    public GraphAL(boolean isDirected, Comparator<V> cmpVertices, Comparator<E> cmpEdges) {
        this.isDirected = isDirected;
        this.cmpVertices = cmpVertices;
        this.cmpEdges = cmpEdges;
        this.vertices = new LinkedList<>();
    }

    @Override
    public boolean addVertex(V content) {
        Vertex<V, E> newVertex = new Vertex<>(content);
        return (content == null || this.hasVertex(content)) ? false : vertices.add(newVertex);
    }

    @Override
    public boolean hasVertex(V content) {
        for (Vertex<V, E> v : vertices) {
            if (this.cmpVertices.compare(content, v.getContent()) == 0) {
                return true;
            }
        }
        return false;
    }

    private Vertex<V, E> findVertex(V content) {
        for (Vertex<V, E> v : vertices) {
            if (this.cmpVertices.compare(content, v.getContent()) == 0) {
                return v;
            }
        }
        return null;
    }

    @Override
    public boolean removeVertex(V content) {
        if (content == null) {
            return false;
        }
        Vertex<V, E> v = findVertex(content);
        if (v == null) {
            return false;
        }
        for (Vertex<V, E> vertex : vertices) {
            Iterator<Edge<E, V>> edgesIterator = vertex.getEdges().iterator();
            while (edgesIterator.hasNext()) {
                Edge<E, V> currentEdge = edgesIterator.next();
                Vertex<V, E> currentSource = currentEdge.getSource();
                Vertex<V, E> currentTarget = currentEdge.getTarget();
                if (this.cmpVertices.compare(currentSource.getContent(), v.getContent()) == 0
                        || this.cmpVertices.compare(currentTarget.getContent(), v.getContent()) == 0) {
                    edgesIterator.remove();
                }
            }
        }

        v.setContent(null);
        v.setEdges(null);
        vertices.remove(v);
        return true;

    }

    @Override
    public boolean connect(V source, V target) {
        return connect(source, source, 0, null);
    }

    @Override
    public boolean connect(V source, V target, int weight) {
        return connect(source, source, weight, null);
    }

    @Override
    public boolean connect(V source, V target, int weight, E metadata) {
        if (source == null || target == null) {
            return false;
        }

        Vertex<V, E> vSource = findVertex(source);
        Vertex<V, E> vTarget = findVertex(target);

        if (vSource == null || vTarget == null) {
            return false;
        }

        Edge<E, V> e = new Edge<>(vSource, vTarget, weight, metadata);

        vSource.getEdges().add(e);
        if (!this.isDirected) {
            Edge<E, V> e1 = new Edge<>(vTarget, vSource, weight, metadata);
            vTarget.getEdges().add(e1);
        }
        return true;
    }

    @Override
    public boolean removeEdge(V source, V target) {
        if (source == null || target == null) {
            return false;
        }
        Vertex<V, E> vSource = findVertex(source);
        Vertex<V, E> vTarget = findVertex(target);
        if (vSource == null || vTarget == null) {
            return false;
        }
        List<Edge<E, V>> edges = vSource.getEdges();
        Iterator<Edge<E, V>> edgesIterator = edges.iterator();
        while (edgesIterator.hasNext()) {
            Edge<E, V> e = edgesIterator.next();
            if (this.cmpVertices.compare(e.getTarget().getContent(), vTarget.getContent()) == 0) {
                edgesIterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder();
        v.append(" vertices = {");

        StringBuilder a = new StringBuilder();
        a.append(" edges = {");

        for (Vertex<V, E> vertex : vertices) {
            v.append(vertex.getContent());
            v.append(", ");
            for (Edge<E, V> e : vertex.getEdges()) {
                a.append(e.toString());
                a.append(", ");
            }
        }
        if (!vertices.isEmpty()) {
            v.delete(v.length() - 2, v.length());
        }
        if (a.length() > 4) {
            a.delete(a.length() - 2, a.length());
        }

        v.append("}");
        a.append("}");
        return v.toString() + "\n" + a.toString();
    }

    /*
    Grafo por anchura hace uso de cola
    */
    @Override
    public List<V> breadthTraversal(V start) {
        if(start == null){
            return null;
        }
        Queue<Vertex<V, E>> colaBreadth = new LinkedList<>();
        List<V> verticesVisitados = new ArrayList<>();
        
        //Se usa un vertice para encontrar el vertice por el contenido de argumento
        Vertex<V, E> verticeInicial = encontrarVertice(start);
        
        colaBreadth.offer(verticeInicial);
        verticesVisitados.add(start);
        
        while(!colaBreadth.isEmpty()){
            //System.out.println(colaBreadth);
            Vertex<V, E> verticeActual = colaBreadth.poll();
            
            for(Edge<E, V> edge: verticeActual.getEdges()){
                Vertex<V, E> verticeExtremo = edge.getTarget();
                
                if(!verticesVisitados.contains(verticeExtremo.getContent())){
                    colaBreadth.offer(verticeExtremo);
                    verticesVisitados.add(verticeExtremo.getContent());
                }
                
                // En caso de ser un grafo dirigido
                if (!isDirected && !verticesVisitados.contains(edge.getSource().getContent())) {
                    colaBreadth.add(edge.getSource());
                    verticesVisitados.add(edge.getSource().getContent());
                }
            }
        }
        return verticesVisitados;
    }
    
    /*
    Hace uso de pilas
    */
    @Override
    public List<V> depthTraversal(V start) {
        if(start == null){
            return null;
        }
        List<V> verticesVisitados = new ArrayList<>();
        Stack<Vertex<V, E>> stackDepth = new Stack<>();
        Vertex<V, E> verticeInicial = encontrarVertice(start);
        
        stackDepth.push(verticeInicial);
        verticesVisitados.add(start);
        
        while (!stackDepth.isEmpty()) {
            Vertex<V, E> currentVertex = stackDepth.pop();

            if (!verticesVisitados.contains(currentVertex.getContent())) {
                verticesVisitados.add(currentVertex.getContent());

                for (Edge<E, V> edge : currentVertex.getEdges()) {
                    Vertex<V, E> neighborVertex = edge.getTarget();

                    if (!verticesVisitados.contains(neighborVertex.getContent())) {
                        stackDepth.push(neighborVertex);
                    }

                    // En caso de ser un grafo dirigido
                    if (!isDirected && !verticesVisitados.contains(edge.getSource().getContent())) {
                        stackDepth.push(edge.getSource());
                    }
                }
            }
        }
        return verticesVisitados;
    }

    private Vertex<V, E> encontrarVertice(V start){
        //no hay necesidad cond. inicial, ya que se encarga breadthTraversal()
        for(Vertex<V, E> verticeEncontrado: vertices){
            if(this.cmpVertices.compare(start, verticeEncontrado.getContent()) == 0){
                return verticeEncontrado;
            }
        }
        return null;
    }
        

    public Vertex<V, E> buscarVerticeNoVisitado(List<V> visited) {
        for (Vertex<V, E> vertex : vertices) {
            if (!visited.contains(vertex.getContent())) {
                return vertex;
            }
        }
        return null;
    }

    public List<List<V>> getComponentesConexas() {
        List<V> verticesVisitados = new ArrayList<>(); 
        List<List<V>> componentes = new ArrayList<>();

        while (true) {
            Vertex<V, E> n = buscarVerticeNoVisitado(verticesVisitados);
            if (n == null) 
                break;

            List<V> recorrido = breadthTraversal(n.getContent());
            verticesVisitados.addAll(recorrido);
            componentes.add(recorrido);
        }

        return componentes;
    }
    
    
    /*
    Grafo estrictamente conexo
    */
    
    
    
    //Invertir las direcciones de las aristas para el grafo estrictamente conexo
    public GraphAL<V, E> getGrafoDirigidoInvertido() {
        GraphAL<V, E> grafoDirigidoInvertido = new GraphAL<>(true, cmpVertices, cmpEdges);

        for (Vertex<V, E> vertex : this.vertices) {
            grafoDirigidoInvertido.addVertex(vertex.getContent());
        }

        for (Vertex<V, E> vertex : this.vertices) {
            for (Edge<E, V> edge : vertex.getEdges()) {
                grafoDirigidoInvertido.connect(edge.getTarget().getContent(), edge.getSource().getContent(), 0, null);
            }
        }

        return grafoDirigidoInvertido;
    }

}
