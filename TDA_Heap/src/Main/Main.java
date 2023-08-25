/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Class.Estudiante;
import Heap.Heap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author alexx
 */
public class Main {
    public static void main(String args[]){
        
        Comparator<Estudiante> cmp = new Comparator<Estudiante>(){
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        };
        
        Comparator<Estudiante> cmpProm = new Comparator<Estudiante>(){
            @Override
            public int compare(Estudiante o1, Estudiante o2) {
                return Double.compare(o2.getPromedio(), o1.getPromedio()); // Note el cambio de orden para hacerlo descendente
            }
        };
        
        LinkedList<Estudiante> estudiantes = new LinkedList<>();
        estudiantes.add(new Estudiante("Juan", 9.5));
        estudiantes.add(new Estudiante("María", 8.7));
        estudiantes.add(new Estudiante("Pedro", 7.9));
        estudiantes.add(new Estudiante("Andrés", 8.1));
        estudiantes.add(new Estudiante("Carlos", 9.2));
        estudiantes.add(new Estudiante("Laura", 8.3));
        estudiantes.add(new Estudiante("Luis", 7.5));
        estudiantes.add(new Estudiante("Sofía", 9.0));
        estudiantes.add(new Estudiante("Miguel", 8.9));
        estudiantes.add(new Estudiante("Diana", 7.8));
        
        
        Heap<Estudiante> heap = new Heap<>(cmpProm);

        Iterator<Estudiante> stdIterator = estudiantes.iterator();
        while(stdIterator.hasNext()){
            Estudiante estudianteActual = stdIterator.next();
            System.out.println(estudianteActual);
            System.out.println(estudianteActual.getNombre());
            heap.encolar(estudianteActual);
            
        }
        
        heap.testeo();
        
    }
}
