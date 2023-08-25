package parte2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Parte2{
    
    public static <E> E findMaxElement(List<E> list, Comparator<E> comparator) {
        if (list.isEmpty()) {
            return null;
        }

        E maxElement = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            E currentElement = list.get(i);
            if (comparator.compare(currentElement, maxElement) > 0) {
                maxElement = currentElement;
            }
        }

        return maxElement;
    }  
    
    public static <T> List<T> eliminarDuplicados(List<T> lista, Comparator<T> cmp) {
        Queue<T> uniqueElements = new PriorityQueue<>(cmp);
        List<T> seenElements = new ArrayList<>();

        for (T element : lista) {
            if (!seenElements.contains(element)) {
                uniqueElements.add(element);
                seenElements.add(element);
            }
        }

        List<T> result = new ArrayList<>(uniqueElements);
        return result;
    }
    
    public static <T> int buscarIndice(List<T> lista, T e, Comparator<T> cmp) {
        //se elimina los elementos duplicados haciendo uso del metodo anterior
        List<T> removedElements = eliminarDuplicados(lista, cmp);
        Queue<Integer> indices = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        
        int currentIndex = 0;

        
        
        for (T element : removedElements) {
            if (cmp.compare(element, e) == 0) {
                indices.add(currentIndex);
            }
            currentIndex++;
        }

        if (!indices.isEmpty()) {
            return indices.poll();
        }

        return -1;
    }
    
    public static void main(String args[]){
        List<String> stringList = new ArrayList<>();
        stringList.add("manzana");
        stringList.add("banana");
        stringList.add("naranja");
        stringList.add("banana");
        stringList.add("manzana");

        List<Integer> intList = new ArrayList<>();
        intList.add(5);
        intList.add(10);
        intList.add(3);
        intList.add(10);
        intList.add(5);

        System.out.println("\nMaximo elemento:");
        String maxString = findMaxElement(stringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("Max String: " + maxString);
        
        Integer maxInt = findMaxElement(intList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("Max Integer: " + maxInt);
        
        System.out.println("\nEliminacion de elementos duplicados");
        List<String> uniqueStrings = eliminarDuplicados(stringList, new Comparator<String>(){
           @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            } 
        });
        System.out.println("Elementos String unicos: " + uniqueStrings);

        List<Integer> uniqueIntegers = eliminarDuplicados(intList, new Comparator<Integer>(){
           @Override
            public int compare(Integer o1, Integer o2) {
                // Implementación de la comparación específica para strings
                return o1.compareTo(o2);
            } 
        });
        System.out.println("Elementos Integer unicos: " + uniqueIntegers);
    
        System.out.println("\nBusqueda de elementos por indice");
        int index = buscarIndice(stringList, "naranja", new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("Indice: " + index);
        int index2 = buscarIndice(intList, 10, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("Indice: " + index2);
        
    }
}
