package main;

import TDAs.LinkedList;

public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> l1 = new LinkedList<>();

        System.out.println("está vacía?" + l1.isEmpty());

        System.out.println("Tamaño de la lista: " + l1.size());

        for (int i = 0; i < 5; i++) {
            l1.addFirst(i);
        }
        
        System.out.println("Tamaño de la lista después de insertar: " + l1.size());

        System.out.println(l1);

    }

}
