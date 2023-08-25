/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Diccionario3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author alexx
 */
public class Main {
    public static void main(String args[]){
        Map<String, JugadorSeleccion> jugadores = new HashMap<>();
        jugadores.put("Casillas", new JugadorSeleccion(1, "Casillas", "Portero"));
        jugadores.put("Ramos", new JugadorSeleccion(15, "Ramos", "Lateral Derecho"));
        jugadores.put("Pique", new JugadorSeleccion(13, "Pique", "Central"));
        jugadores.put("Puyol", new JugadorSeleccion(5, "Puyol", "Central"));
        jugadores.put("Capdevila", new JugadorSeleccion(11, "Capdevila", "Lateral Izquierdo"));
        jugadores.put("Xabi", new JugadorSeleccion(14, "Xabi Alonso", "Medio Centro"));
        jugadores.put("Busquets", new JugadorSeleccion(16, "Busquets", "Medio Centro"));    
        jugadores.put("Xavi", new JugadorSeleccion(8, "Xavi Hernandez", "Centro Campista"));
        jugadores.put("Pedrito", new JugadorSeleccion(18, "Pedrito", "Interior Izquierdo"));
        jugadores.put("Iniesta", new JugadorSeleccion(6, "Iniesta", "Interior Derecho"));
        jugadores.put("Villa", new JugadorSeleccion(7, "Villa", "Delantero"));
        
        //Iteracion del diccionario usando un ITERATOR
        System.out.println("\niteracion con un ITERATOR");
        Iterator it = jugadores.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            System.out.println("Clave: " + key + ". valor: " + jugadores.get(key));
        }
        System.out.println("\nIteracion como si fuese un ARRAYLIST");
        //Iteracion como si fuese un ARRAYLIST
        for(Entry<String, JugadorSeleccion> m : jugadores.entrySet()){
            String clave = m.getKey();
            JugadorSeleccion valor = m.getValue();
            System.out.println(clave+"  ->  "+valor);
        }
    }
}
