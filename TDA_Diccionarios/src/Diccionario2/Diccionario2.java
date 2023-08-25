/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Diccionario2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author alexx
 */
public class Diccionario2 {
    public static void main(String args[]){
        //En un Hashmap los datos no vana  etar ordenados. Llaves van a ser unicas
        Map<Integer, String> diccionarioHash = new HashMap<>();
        Map<String, Integer> diccionarioHash1 = new HashMap<>();
        
        diccionarioHash.put(1, "Christhian");
        diccionarioHash.put(2, "Raul");
        diccionarioHash.put(7, "Sebas");
        diccionarioHash.put(4, "Aquino");
        diccionarioHash.put(1, "Chris");
        
        diccionarioHash1.put("4", 50);
        diccionarioHash1.put("1", 500);
        diccionarioHash1.put("2", 10);
        diccionarioHash1.put("3", 200);
        diccionarioHash1.put("4", 500);
        diccionarioHash1.put("5", 100);
        
        System.out.println("Iteracion del diccionario Integer-String");
        for(Entry<Integer,String> entry: diccionarioHash.entrySet()){
            System.out.println("Clave " + entry.getKey() + "-" + "Valor " + entry.getValue());
        }
        
        System.out.println("\nIteracion del diccionario String-Integer");
        for(Entry<String,Integer> entry: diccionarioHash1.entrySet()){
            System.out.println("Clave " + entry.getKey() + "-" + "Valor " + entry.getValue());
        }
        
        //HashMap, no importa el orden, pero una llave distinta, si hay mas llaves iguales, entonces agregar al
        //Hashmap el ultimo qu se agregar con la llave duplicada 
        
        //En un treemap los llaves se ordenanen su forma natural, pero de igusl manera ss llaves deben de ser unicas
        System.out.println("Diccionario TreeMap \n");
        Map<String, Integer> diccionarioTree = new TreeMap<>();
        
        diccionarioTree.put("199", 500);
        diccionarioTree.put("4", 50);
        diccionarioTree.put("1", 500);
        diccionarioTree.put("2", 10);
        diccionarioTree.put("3", 200);
        diccionarioTree.put("4", 500);
        diccionarioTree.put("5", 100);
        
        System.out.println("Iteracion del diccionario TreeMap String-Integer");
        for(Entry<String,Integer> entry: diccionarioTree.entrySet()){
            System.out.println("Clave " + entry.getKey() + "-" + "Valor " + entry.getValue());
        }
        
        System.out.println("Diccionario LinkedHashMap \n");
        Map<String, Integer> diccionarioLinked = new LinkedHashMap<>();
        
        //En LinkedHashMap los elementos se vana  agregar en el msimo orden de insercion, claro de igual manera
        //las laves van a ser unicas
        
        diccionarioLinked.put("B", 500);
        diccionarioLinked.put("G", 50);
        diccionarioLinked.put("A", 500);
        diccionarioLinked.put("G", 10);
        diccionarioLinked.put("Y", 200);
        diccionarioLinked.put("V", 500);
        diccionarioLinked.put("A", 100);
        
        System.out.println("Iteracion del diccionario LinkedHashMap String-Integer");
        for(Entry<String,Integer> entry: diccionarioLinked.entrySet()){
            System.out.println("Clave " + entry.getKey() + "-" + "Valor " + entry.getValue());
        }
        
        
    }
    
    
}