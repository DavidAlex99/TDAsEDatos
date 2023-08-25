package tests;

import java.util.Comparator;
import java.util.List;
import tda.Graph;
import tda.GraphAL;

public class Main {
    public static void main(String[] args) {
        
        Comparator<String> cmpVertices = (s1, s2) -> {
            return s1.compareTo(s2);
        };
        
        Comparator<String> cmpEdges = (s1, s2) -> {
            return s1.compareTo(s2);
        };
        
        Graph<String, String> grafo = new GraphAL<>(true, cmpVertices, cmpEdges);

        grafo.addVertex("V1");
        grafo.addVertex("V2");
        grafo.addVertex("V3");
        grafo.addVertex("V4");
        grafo.addVertex("V5");
        grafo.addVertex("V6");
        grafo.addVertex("V7");
        grafo.addVertex("V8");
        grafo.addVertex("V9");
        grafo.addVertex("V10");

        grafo.connect("V1", "V2", 3, null);
        grafo.connect("V1", "V3", 4, null);
        grafo.connect("V1", "V5", 8, null);
        grafo.connect("V2", "V5", 5, null);
        grafo.connect("V3", "V5", 3, null);
        grafo.connect("V5", "V4", 7, null);
        grafo.connect("V5", "V6", 3, null);
        grafo.connect("V6", "V4", 2, null);
        
        //Creacion de una componente
        grafo.connect("V7", "V8", 2, null);
        grafo.connect("V8", "V10", 2, null);
        grafo.connect("V9", "V7", 2, null);
        grafo.connect("V10", "V7", 2, null);

        System.out.println(grafo);
        
        System.out.println("Búsqueda en Anchura");
        System.out.println(grafo.breadthTraversal("V1"));

        System.out.println("\nBúsqueda en Profundidad");
        System.out.println(grafo.depthTraversal("V1"));
        
        System.out.println("\nComponentes Conexas:");
        List<List<String>> componentes = grafo.getComponentesConexas();
        for (List<String> componente : componentes) {
            System.out.println(componente);
        }
    }
}
