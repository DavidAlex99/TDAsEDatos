package main;

import TDAs.LinkedList;

/**
 *
 * @ispovala
 */
public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> l1 = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            l1.addLast(i);
        }

        System.out.println("\nLista Original");
        System.out.println(l1);
        System.out.println("SIZE: " + l1.size() + "\n");

        System.out.println("Remove First");
        System.out.println("Old First: " + l1.removeFirst());
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + " New First:" + l1.getFirst().getContent() + "\n");

        System.out.println("Remove Last");
        System.out.println("Old Last: " + l1.removeLast());
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + " New Last:" + l1.getLast().getContent() + "\n");

        System.out.println("ADD 0 in 0");
        l1.add(0, 0);
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + "\n");

        System.out.println("ADD 9 in 9");
        l1.add(9, 9);
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + "\n");

        System.out.println("ADD 44 in 4");
        l1.add(4, 44);
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + "\n");

        System.out.println("Remove index 0");
        System.out.println("Deleted: " + l1.remove(0));
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + "\n");

        System.out.println("Remove index 9");
        System.out.println("Deleted: " + l1.remove(9));
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + "\n");

        System.out.println("Remove index 3");
        System.out.println("Deleted: " + l1.remove(3));
        System.out.println(l1);
        System.out.println("SIZE:" + l1.size() + "\n");

        System.out.println("GET 0: " + l1.get(0));
        System.out.println("GET 7: " + l1.get(7));
        System.out.println("GET 4: " + l1.get(4) + "\n");

        System.out.println("SET 11 in 0: " + l1.set(0, 11));
        System.out.println("SET 88 in 7: " + l1.set(7, 88));
        System.out.println("SET 44 in 3: " + l1.set(3, 44));
        System.out.println(l1 + "\n");

        System.out.println("CLEAR");
        l1.clear();
        System.out.println("FIRST: " + l1.getFirst());
        System.out.println("LAST:  " + l1.getLast());
        System.out.println("EMPTY: " + l1.isEmpty());
        System.out.println("SIZE:  " + l1.size());

    }

}
