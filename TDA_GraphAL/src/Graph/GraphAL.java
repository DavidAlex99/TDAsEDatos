 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author CltControl
 */
public class GraphAL<V, E> {
    private List<Vertex<V, E>> vertices;
    private boolean isDirected;
    private Comparator<V> cmpVertice;
    private Comparator<E> cmpArista;    
    
    public GraphAL(Comparator<V> cmpVertice, Comparator<E> cmpArista, boolean isDirected){
        this.isDirected = isDirected;
        vertices = new ArrayList<>();
        this.cmpArista = cmpArista;
        this.cmpVertice = cmpVertice;
    }

    public List<Vertex<V, E>> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex<V, E>> vertices) {
        this.vertices = vertices;
    }

    public boolean isIsDirected() {
        return isDirected;
    }

    public void setIsDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public Comparator<V> getCmpVertice() {
        return cmpVertice;
    }

    public void setCmpVertice(Comparator<V> cmpVertice) {
        this.cmpVertice = cmpVertice;
    }

    public Comparator<E> getCmpArista() {
        return cmpArista;
    }

    public void setCmpArista(Comparator<E> cmpArista) {
        this.cmpArista = cmpArista;
    }
    
    
    
    public void addVertex(V content){
        if(findVertexByContent(content) == null){
            Vertex<V, E> newVertice = new Vertex<>(content);
            vertices.add(newVertice);
        }
    }
    
    //al momento de agreagr un vertice, verificar si no se encuentra ya agregado
    private Vertex<V,E> findVertexByContent(V content){
        for(Vertex<V, E> vertex: vertices){
            if(cmpVertice.compare(vertex.getContent(), content) == 0){
                return vertex;
            }
        }
        return null;
    }
    
    
    public boolean connect(V sourceContent, V targetContent, int weight, E metadata){
        if(sourceContent == null || targetContent == null){
            return false;
        }
        Vertex<V, E> verticeInicial = findVertexByContent(sourceContent);
        Vertex<V, E> verticeFinal = findVertexByContent(targetContent);
        if (verticeInicial != null && verticeFinal != null) {
            
            // Verifica si ya existe una conexión en la dirección opuesta
            for (Edge<E, V> edge : verticeFinal.getAristas()) {
                if ( (cmpVertice.compare(edge.getTarget().getContent(), verticeInicial.getContent()) == 0) &&
                        (edge.getWeight() == weight) ) {
                    return false; // Si existe, no agregues una nueva conexión
                }
            }
            
            Edge<E, V> edge = new Edge<>(verticeInicial, verticeFinal, weight, metadata);
            verticeInicial.getAristas().add(edge);
            if (!isDirected && cmpVertice.compare(sourceContent, targetContent) != 0) {
                Edge<E, V> reverseEdge = new Edge<>(verticeFinal, verticeInicial, weight, metadata);
                verticeFinal.getAristas().add(reverseEdge);
            }
        }
        return true;
    }
    
    public boolean disconnect(V content1, V content2){
        if(content1 == null || content2 == null){
            return false;
        }
        Vertex<V, E> v1 = findVertexByContent(content1);
        Vertex<V, E> v2 = findVertexByContent(content2);
        
        //Iteracion en la lista de listas, la linkedlist que contiene edges que pertenecen a cada vertice
        for(Edge<E, V> edgeDelete : v1.getAristas()){
            //Comparamos si se encunetra alguna conexion con el vertice vertex2
            if(cmpVertice.compare(edgeDelete.getTarget().getContent(), v2.getContent()) == 0){
                // Necesita crear un comparador enla clase main
                
                //Borrar la conexion del vertice Source al Target
                v1.getAristas().remove(edgeDelete);
                return true;
            }
        }
        
        if(!this.isDirected){
            //Iteracion en la lista de listas, la linkedlist que contiene edges que pertenecen a cada vertice
            for(Edge<E, V> edgeDelete : v2.getAristas()){
                //Comparamos si se encunetra alguna conexion con el vertice vertex2
                if(cmpVertice.compare(edgeDelete.getTarget().getContent(), v1.getContent()) == 0){
                    // Necesita crear un comparador enla clase main
                
                    //Borrar la conexion del vertice Source al Target
                    v2.getAristas().remove(edgeDelete);
                    return true;
                }
                return false;
            }
            
        }
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Vertex<V, E> vertex : vertices) {
            sb.append("Vertice ").append(vertex.getContent()).append(": Conectado a ");

            List<String> connections = new ArrayList<>();
            for (Edge<E, V> edge : vertex.getAristas()) {
                connections.add(edge.getTarget().getContent() + " via " + edge.getMetadata());
            }

            sb.append(connections.toString()).append("\n");
        }

        return sb.toString();
    }

    /*
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
    */
    
    /*
    BUSQUEDA POR ANCHURA
    A está conectado a B y C.
    B está conectado a A, D y E.
    C está conectado a A y E.
    D está conectado a B.
    E está conectado a B y C.
    
    Inicialización:

    Visita A y agrégalo a la cola.
    Marca A como visitado.
    Cola: A

    Primer nivel:

    Desencola A y visita todos sus vecinos (B y C).
    B y C son agregados a la cola y marcados como visitados.
    Cola: B, C

    Segundo nivel:

    Desencola B y visita todos sus vecinos (D y E) que aún no han sido visitados.
    D y E son agregados a la cola y marcados como visitados.
    Cola: C, D, E

    Tercer nivel:

    Desencola C. Todos sus vecinos (A y E) ya han sido visitados, así que no se añade nada nuevo a la cola.
    Cola: D, E

    Cuarto nivel:

    Desencola D. Todos sus vecinos (B) ya han sido visitados, así que no se añade nada nuevo a la cola.
    Cola: E

    Quinto nivel:

    Desencola E. Todos sus vecinos (B y C) ya han sido visitados, así que no se añade nada nuevo a la cola.
    Cola: (vacía)

    El algoritmo termina cuando la cola está vacía.

    Orden de visita: A, B, C, D, E.
    */

    
    public List<V> breadthFirstSearch(V startVertexContent) {
        List<V> visitedOrder = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        Vertex<V, E> startVertex = findVertexByContent(startVertexContent);

        if (startVertex == null) return visitedOrder;

        queue.add(startVertex);
        visitedOrder.add(startVertex.getContent());

        while (!queue.isEmpty()) {
            Vertex<V, E> currentVertex = queue.poll();

            for (Edge<E, V> edge : currentVertex.getAristas()) {
                Vertex<V, E> neighborVertex = edge.getTarget();

                if (!visitedOrder.contains(neighborVertex.getContent())) {
                    queue.add(neighborVertex);
                    visitedOrder.add(neighborVertex.getContent());

                    // Si el grafo no es dirigido, consideramos el nodo origen de la arista como un vecino también.
                    if (!isDirected && !visitedOrder.contains(edge.getSource().getContent())) {
                        queue.add(edge.getSource());
                        visitedOrder.add(edge.getSource().getContent());
                    }
                }
            }
        }

        return visitedOrder;
    }
    
    /*
    BUSQUEDA POR PROFUDIDAD
    Comienza en un nodo inicial (nodo de partida).
    Marca el nodo como visitado y agrégalo a la lista de visitados.
    Para cada vecino del nodo actual que aún no ha sido visitado:
    Si el grafo es dirigido, considera solo los nodos destino de las aristas.
    Si el grafo es no dirigido, considera tanto el nodo origen como el nodo destino de las aristas.
    Recursivamente repite los pasos anteriores para el vecino no visitado.
    Si todos los vecinos han sido visitados, retrocede y explora el siguiente nodo no visitado.
    */
    public List<V> depthFirstSearch(V startVertexContent) {
        List<V> visitedOrder = new ArrayList<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        Vertex<V, E> startVertex = findVertexByContent(startVertexContent);
        if (startVertex == null) return visitedOrder;

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> currentVertex = stack.pop();

            if (!visitedOrder.contains(currentVertex.getContent())) {
                visitedOrder.add(currentVertex.getContent());

                for (Edge<E, V> edge : currentVertex.getAristas()) {
                    Vertex<V, E> neighborVertex = edge.getTarget();

                    if (!visitedOrder.contains(neighborVertex.getContent())) {
                        stack.push(neighborVertex);
                    }

                    // Si el grafo no es dirigido, consideramos el nodo origen de la arista como un vecino también.
                    if (!isDirected && !visitedOrder.contains(edge.getSource().getContent())) {
                        stack.push(edge.getSource());
                    }
                }
            }
        }

        return visitedOrder;
    }

    /*
    Para buscar las compoentes conexas
    1. SEGUN el pseudocodigo proporcionado, buscaremos metodos conezas, y en ella se usa metodo para averiguar 
    si un vertices fue visitado
    */
    public Vertex<V, E> buscarVerticeNoVisitado(List<V> visited) {
        for (Vertex<V, E> vertex : vertices) {
            if (!visited.contains(vertex.getContent())) {
                return vertex;
            }
        }
        return null;
    }

    public List<List<V>> getConnectedComponents() {
        List<V> visited = new ArrayList<>(); 
        List<List<V>> componentes = new ArrayList<>();

        while (true) {
            Vertex<V, E> n = buscarVerticeNoVisitado(visited);
            if (n == null) 
                break;

            List<V> recorrido = breadthFirstSearch(n.getContent());
            visited.addAll(recorrido);
            componentes.add(recorrido);
        }

        return componentes;
    }
    
    //otra forma de buscar componentes grafos no dirigidos, bfs y dfs
    
    /*
    *Se pasa como parametro un parametro de tipo V indicando desde que vertice se va a comenzar a hacer el recorrido
    *La lista de boolean va a tener como posicion las misms posiciones de como se encuentran los vertices en la LinkedList
    */
    
    public LinkedList<V> breadthFiratSearch(V start){
        
        Vertex<V, E> startVertex = findVertexByContent(start);
        
        LinkedList<V> result = new LinkedList<>();
        
        Queue<Vertex<V, E>> q = new LinkedList<>();
        
        q.offer(startVertex);
        
        while(!q.isEmpty()){
            
            Vertex<V, E> v = q.poll();
            
            v.setVisited(true);
            
            result.add(v.getContent());
            
            LinkedList<Vertex<V, E>> adj = setAdjacentsPerVertex(v);
            
            for(Vertex<V, E> e : adj){
                
                if(!e.getVisited()){
                    
                    q.offer(e);
                    
                } 
            }
        }
        resetBooleanValuesVertex(result);
        
        return result;
    }
    
    
    public LinkedList<V> depthFiratSearch(V start){
        
        Vertex<V, E> startVertex = findVertexByContent(start);
        
        LinkedList<V> result = new LinkedList<>();
        
        Stack<Vertex<V, E>> pila = new Stack<>();
        
        pila.push(startVertex);
        
        while(!pila.isEmpty()){
            
            Vertex<V, E> v = pila.pop();
            
            if(!v.getVisited()){
                
                v.setVisited(true);
                
                result.add(v.getContent());
                
                LinkedList<Vertex<V, E>> adj = setAdjacentsPerVertex(v);
                
                for(Vertex<V, E> u : adj){
                    
                    if(!u.getVisited()){
                        
                        pila.push(u);
                        
                    }
                }
            }
        }
        
        resetBooleanValuesVertex(result);
        
        return result;
    }
    public LinkedList<LinkedList<V>> componentsGraph(){
        
        //Lista que contendra las lista, cada una siendo un componente
        LinkedList<LinkedList<V>> tempGraph = new LinkedList<>();
        //lista que representara cada componente
        LinkedList<V> component = new LinkedList<>();
        
        for(Vertex<V, E> v : vertices){
            if(!v.getVisited()){
                
                component.clear();
                
                componentsGraph(v, component);
                
                tempGraph.add(component);
            }
        }
        return tempGraph;
    }
    
    private void componentsGraph(Vertex<V, E> vertex, LinkedList<V> compList){
        
        compList.add(vertex.getContent());
        
        LinkedList<Vertex<V, E>> adj = setAdjacentsPerVertex(vertex);
        
        for(Vertex<V, E> w : adj){
            
            if(!w.getVisited()){
                
                componentsGraph(w, compList);
                
            }
        }     
    }
    
    private void resetBooleanValuesVertex(LinkedList<V> onlyContent){
        
        for(V v : onlyContent){
            
            Vertex<V, E> vertex = findVertexByContent(v);
            
            vertex.setVisited(false);
        }
    }
    
    //metodo para hacer linkedlist de los vertices adjacemtes al vertice que se pasa como parametro
    private LinkedList<Vertex<V,E>> setAdjacentsPerVertex(Vertex<V, E> vertex){
        //LinkedList para pomner los vertices adjacentes al vertice que se pasa como parametro
        
        LinkedList<Vertex<V,E>> adj = new LinkedList<>();
        
        for(Edge<E, V> edge : vertex.getAristas()){
            adj.add(edge.getTarget());
        }
        
        return adj;
    }
    
    Comparator<Integer> compareInteger = new Comparator<>(){
        @Override
        public int compare(Integer o1, Integer o2) {
            int c = Integer.compare(o1, o2);
            return c;
        }
        
    };
    
//    public Map<V, Integer> dijkstra(V startContent) {
//        // Paso 1: Inicializar estructuras de datos
//        Map<V, Integer> distances = new HashMap<>();
//        for (Vertex<V, E> vertex : vertices) {
//            distances.put(vertex.getContent(), Integer.MAX_VALUE);
//        }
//        distances.put(startContent, 0);
//
//        Set<V> visited = new HashSet<>();
//        PriorityQueue<VertexWrapper> queue = new PriorityQueue<>(compareInteger));
//
//        queue.add(new VertexWrapper(findVertex(startContent), 0));
//
//        // Paso 2 y 3: Procesar nodos
//        while (!queue.isEmpty()) {
//            VertexWrapper current = queue.poll();
//            if (visited.contains(current.vertex.getContent())) continue;
//
//            visited.add(current.vertex.getContent());
//
//            for (Edge<E, V> edge : current.vertex.getAristas()) {
//                V neighborContent = edge.getTarget().getContent();
//                if (visited.contains(neighborContent)) continue;
//
//                int newDistance = distances.get(current.vertex.getContent()) + edge.getWeight();
//                if (newDistance < distances.get(neighborContent)) {
//                    distances.put(neighborContent, newDistance);
//                    queue.add(new VertexWrapper(edge.getTarget(), newDistance));
//                }
//            }
//        }
//
//        return distances;
//    }
    
    //ITERADOR
    public Iterator<Vertex<V, E>> iterator() {
        return new GraphIterator(); // Devuelve una instancia de tu clase Iterator personalizada
    }
    
    //clase iterna que actuara de iterador, para iterar principalmnete sobre la lista de vertices
    private class GraphIterator implements Iterator<Vertex<V, E>>{
        private int currentIndex;
        
        public GraphIterator(){
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < vertices.size();
        }

        @Override
        public Vertex<V, E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return vertices.get(currentIndex++);
        }
    }
}
