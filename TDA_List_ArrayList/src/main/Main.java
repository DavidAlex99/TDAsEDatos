package main;

import TDAs.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> arraylist = new ArrayList<>();

        int i = 1;
        while (i <= 10) {
            arraylist.addLast(i++);

        }

        System.out.println(arraylist);

        arraylist.add(4, 11);
        System.out.println(arraylist);

        ArrayList<String> test = new ArrayList<>();
        System.out.println(test);

        test.addLast("Mundo");
        System.out.println(test);

        test.addFirst("Hola");
        System.out.println(test);

        test.add(2, "Estructuras");
        System.out.println(test);

        System.out.println(test.get(2));

        System.out.println(test.set(0, "Bienvenido"));
        System.out.println(test);

        test.remove(1);
        System.out.println(test);

        test.removeFirst();

        test.addLast("De");
        test.addLast("Datos");
        System.out.println(test);

        test.removeLast();
        System.out.println(test);

        test.clear();
        System.out.println(test);
        ArrayList<String> test2 = new ArrayList<>();
        test2.addLast("Datos");
        test2.addLast("Ayudantías");
        System.out.println(test2);

        test.concat(test2);
        System.out.println(test);
        ArrayList<Integer> test3 = new ArrayList<>();
        int k = 100;
        while (k > 0) {
            test3.addLast(k--);
        }

        System.out.println(test3);
        ArrayList.separarParesImpares(test3);
        System.out.println(test3);

        test3.clear();
        test3.addLast(98);
        test3.addLast(28);
        test3.addLast(38);
        test3.addLast(17);
        test3.addLast(7);
        test3.addLast(8);
        System.out.println(test3);
        ArrayList.separarParesImpares(test3);
        System.out.println(test3);
    }

}
