package ggm.tallerarraylist_p01;

import tda.ArrayList;
import tda.List;

/**
 *
 * @author Admin
 */
public class TallerArrayList_P01 {

    public static void main(String[] args) {

        List list1 = new ArrayList<Integer>();
        System.out.println("Lista vacia? " + list1.isEmpty());
        System.out.println("Effective Size: " + list1.size());
        
        // anañadir elementos aquí
        System.out.println("TALLER");
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(3);
        list1.addLast(4);
        list1.addLast(5);
        list1.addLast(6);
        list1.addLast(7);
        list1.addLast(8);
        list1.addLast(9);
        list1.addLast(10);
        // llamar a toString aquí
        System.out.println(list1.toString());
        System.out.println("Lista vacia? " + list1.isEmpty());
        System.out.println("Effective Size: " + list1.size());
        // llamar a reverse, subList, y removeFirstNElements e imprimir resultados
        System.out.println("Llamado a removeFirstNElements List1()");
        list1.removeFirstNElements(2);
        System.out.println(list1.toString());
        System.out.println("Effective Size: " + list1.size());
        
        System.out.println("Llamado a reverse List1()");
        list1.reverse();
        System.out.println(list1.toString());
        System.out.println("Effective Size: " + list1.size());
        
        System.out.println("\n");
        System.out.println("Llamado a subList()");
        list1.subList(2,5);
        System.out.println("toString: " + list1.subList(2,5).toString());
        

        List list2 = new ArrayList<String>();
        // anañadir elementos aquí
        list2.addLast("A");
        list2.addLast("B");
        list2.addLast("C");
        list2.addLast("D");
        list2.addLast("E");
        list2.addLast("F");
        list2.addLast("G");
        list2.addLast("H");
        list2.addLast("I");
        list2.addLast("J");
        
        System.out.println("\n");
        
        // llamar a toString aquí
        System.out.println(list2.toString());
        System.out.println("Lista vacia? " + list2.isEmpty());
        System.out.println("Effective Size: " + list2.size());
        // llamar a reverse, subList, y removeFirstNElements e imprimir resultados
        System.out.println("Llamado a removeFirstNElements()");
        list2.removeFirstNElements(5);
        System.out.println("toString: " + list2.toString());
        System.out.println("Effective Size: " + list2.size());
        
        System.out.println("\n");
        
        System.out.println("Llamado a subList()");
        list2.subList(2,5);
        System.out.println("toString: " + list2.subList(2,5).toString());
        
        System.out.println("\n");
        
        System.out.println("Llamado a reverse()");
        list2.reverse();
        System.out.println("toString: " + list2.toString());
        System.out.println("Effective Size: " + list2.size());
        
        
    }
}
