/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Clases.Persona;
import Clases.Relacion;
import Graph.Edge;
import Graph.GraphAL;
import Graph.Vertex;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author CltControl
 */
public class Main {
    public static void main(String[] args) {
        // Comparador personalizado para vértices (ciudades) basado en la longitud de sus nombres
        Comparator<String> cityComparator = (city1, city2) -> {
            int lengthComparison = Integer.compare(city1.length(), city2.length());
            if(lengthComparison == 0) {
                return city1.compareTo(city2);
            }
            return lengthComparison;
        };

        // Comparador personalizado para aristas (distancias) en orden inverso
        Comparator<Integer> distanceComparator = (dist1, dist2) -> -Integer.compare(dist1, dist2);

        // Crear un grafo no dirigido con comparadores personalizados
        //recordar, el primer tipo de dato geenrico, e este caso, el vertice es String
        /*
        el segundo tipo de dato es integer, el cual represetaria el metadata que esta ligado e el edge
        NO CONFUNDIR CON EL METADATA
        */
        
        GraphAL<String, Integer> graph = new GraphAL<>(cityComparator, distanceComparator, false);

        // Añadir vértices (ciudades)
        graph.addVertex("New York");
        graph.addVertex("Los Angeles");
        graph.addVertex("Miami");

        // Conectar ciudades con distancias
        graph.connect("New York", "Los Angeles", 3000, 3000); // distancia en millas, por ejemplo
        graph.connect("New York", "Miami", 1300, 1300);
        graph.connect("Los Angeles", "Miami", 2700, 2700);
        graph.connect("Miami", "Los Angeles", 1400, 1400);
        
        System.out.println("Vertices Totales del grafo:");
        for (Vertex<String, Integer> vertex : graph.getVertices()) {
            System.out.println(vertex.getContent());
        }
        
        System.out.println("\n");
        
        System.out.println("Mostrar las aristas del grafo, es decir, mostrar los vertices adjacentes del vertice actual");
        for (Vertex<String, Integer> vertex : graph.getVertices()) {
            System.out.println("vertice: ");
            System.out.println(vertex.getContent());
            System.out.println("Arcos: ");
            for(Edge<Integer, String> edge : vertex.getAristas()){
                System.out.println("Vertice Inicial: " + edge.getSource().getContent() + 
                        " .Vertice Destino: " + edge.getTarget().getContent() + 
                        " .Peso: " + edge.getWeight() + 
                        " .Metadata " + edge.getMetadata()); //Recordar que metadata es el valor del edge
            }
        }
        
        System.out.println("\n");
        
        System.out.println(graph);
        
        
        
        // Comparador personalizado para vértices (ciudades) basado en la longitud de sus nombres
        Comparator<Persona> personaComparator = new Comparator<>(){
            @Override
            public int compare(Persona o1, Persona o2) {
                int c = o1.getNombre().compareTo(o2.getNombre());
                return c;
            }
        };



        // Crear un grafo no dirigido con comparadores personalizados
        //recordar, el primer tipo de dato geenrico, e este caso, el vertice es String
        /*
        el segundo tipo de dato es integer, el cual represetaria el metadata que esta ligado e el edge
        NO CONFUNDIR CON EL METADATA
        */
        
        Comparator<Relacion> relacionComparator = new Comparator<>(){
            @Override
            public int compare(Relacion o1, Relacion o2) {
                int c = Integer.compare(o1.getTiempo(), o2.getTiempo());
                return c;
            }     
        };
        
        GraphAL<Persona, Relacion> graph2 = new GraphAL<>(personaComparator, relacionComparator, false);

        // Añadir vértices (ciudades)
        Persona persona1 = new Persona("David", 24, "ingeniero", "guayaquil");
        Persona persona2 = new Persona("Laura", 20, "cocinero", "quito");
        Persona persona3 = new Persona("Melanie", 29, "ingeniero", "guayaquil");
        Relacion relacion1 = new Relacion(2);
        Relacion relacion2 = new Relacion(5);
        Relacion relacion3 = new Relacion(4);
        Relacion relacion4 = new Relacion(2);
        
        graph2.addVertex(persona1);
        graph2.addVertex(persona2);
        graph2.addVertex(persona3);

        // Conectar ciudades con distancias
        graph2.connect(persona1, persona2, 3000, relacion1); // distancia en millas, por ejemplo
        graph2.connect(persona1, persona3, 1300, relacion2);
        graph2.connect(persona2, persona3, 2700, relacion3);
        graph2.connect(persona3, persona2, 1400, relacion4);
        
        System.out.println("Vertices Totales del grafo:");
        for (Vertex<Persona, Relacion> vertex : graph2.getVertices()) {
            System.out.println(vertex.getContent().getNombre());
        }
        
        System.out.println("\n");
        
        System.out.println("Mostrar las aristas del grafo, es decir, mostrar los vertices adjacentes del vertice actual");
        for (Vertex<Persona, Relacion> vertex : graph2.getVertices()) {
            System.out.println("vertice: ");
            System.out.println(vertex.getContent().getNombre());
            System.out.println("Arcos: ");
            for(Edge<Relacion, Persona> edge : vertex.getAristas()){
                System.out.println("Vertice Inicial: " + edge.getSource().getContent().getNombre() + 
                        " .Vertice Destino: " + edge.getTarget().getContent().getNombre() + 
                        " .Peso: " + edge.getWeight() + 
                        " .Metadata " + edge.getMetadata()); //Recordar que metadata es el valor del edge
            }
        }
        
        System.out.println("\n");
        
        System.out.println(graph2);
    }
}
