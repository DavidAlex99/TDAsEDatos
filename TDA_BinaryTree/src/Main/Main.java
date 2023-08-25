/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Tree.BinaryTree;
import java.util.Comparator;

/**
 *
 * @author alexx
 */
public class Main {
    public static void main(String args[]){
        BinaryTree<Integer> treeInt = new BinaryTree(0);
        treeInt.setLeft(new BinaryTree(1));
        treeInt.setRight(new BinaryTree(2));
        treeInt.getLeft().setLeft(new BinaryTree(3));
        treeInt.getLeft().setRight(new BinaryTree(4));
        treeInt.getRight().setLeft(new BinaryTree(5));
        treeInt.getRight().setRight(new BinaryTree(6));
        treeInt.getRight().getRight().setRight(new BinaryTree(7));
        
        Comparator<Integer> comparadorInt = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
            
        };
        
        BinaryTree<String> treeStr = new BinaryTree("cero");
        treeStr.setLeft(new BinaryTree("uno"));
        treeStr.setRight(new BinaryTree("dos"));
        treeStr.getLeft().setLeft(new BinaryTree("tres"));
        treeStr.getLeft().setRight(new BinaryTree("cuatro"));
        treeStr.getRight().setLeft(new BinaryTree("cinco"));
        treeStr.getRight().setRight(new BinaryTree("seis"));
        treeStr.getRight().getRight().setRight(new BinaryTree("siete"));
        
        Comparator<String> comparadorStr = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
            
        };
        
        System.out.println("conteo descendientes iterativo");
        System.out.println("Numero de descendientes Integer: " + treeInt.countDescendantsIterative());
        
        System.out.println("conteo descendientes recursivo");
        System.out.println("Numero de descendientes Integer: " + treeInt.countDescendantsRecursive());
        
        System.out.println("conteo descendientes iterativo");
        System.out.println("Numero de descendientes String: " + treeStr.countDescendantsIterative());
        
        System.out.println("conteo descendientes recursivo");
        System.out.println("Numero de descendientes String: " + treeStr.countDescendantsRecursive());
        
        System.out.println("\n");
        
        System.out.println("busqueda padre iterativo");
        System.out.println("Padre Integer: " + treeInt.findParentIterative(6, comparadorInt).getRoot());
        
        System.out.println("busqueda padre recursivo");
        System.out.println("Padre Integer: " + treeInt.findParentRecursive(6, comparadorInt).getRoot());
        
        System.out.println("busqueda padre iterativo");
        System.out.println("Padre String: " + treeStr.findParentIterative("cinco", comparadorStr).getRoot());
        
        System.out.println("busqueda padre recursivo");
        System.out.println("Padre String: " + treeStr.findParentRecursive("seis", comparadorStr).getRoot());

        System.out.println("\n");
        
        System.out.println("Conteo de nieveles iterativo");
        System.out.println("Niveles Integer: " + treeInt.countLevelsIterative());
        
        System.out.println("Conteo de nieveles iterativo");
        System.out.println("Niveles Integer: " + treeInt.countLevelsIterative());
        
        System.out.println("Conteo de nieveles iterativo");
        System.out.println("Niveles String: " + treeStr.countLevelsIterative());
        
        System.out.println("Conteo de nieveles iterativo");
        System.out.println("Niveles String: " + treeStr.countLevelsIterative());
        
        System.out.println("\n");
        
        System.out.println("isLeftly iterativo");
        System.out.println("Es Izquierdo Integer?: " + treeInt.isLeftlyIterative());
        
        System.out.println("isLeftly recursivo");
        System.out.println("Es Izquierdo Integer?: " + treeInt.isLeftlyRecursive());
        
        System.out.println("isLeftly iterativo");
        System.out.println("Es Izquierdo String?: " + treeStr.isLeftlyIterative());
        
        System.out.println("isLeftly recursivo");
        System.out.println("Es Izquierdo String?: " + treeStr.isLeftlyRecursive());
        
        System.out.println("\n");
        
        BinaryTree<Integer> treeInt2 = new BinaryTree(0);
        treeInt2.setLeft(new BinaryTree(1));
        treeInt2.setRight(new BinaryTree(2));
        treeInt2.getLeft().setLeft(new BinaryTree(3));
        treeInt2.getLeft().setRight(new BinaryTree(4));
        treeInt2.getRight().setLeft(new BinaryTree(5));
        treeInt2.getRight().setRight(new BinaryTree(6));
        treeInt2.getRight().getRight().setRight(new BinaryTree(7));
        
        BinaryTree<String> treeStr2 = new BinaryTree("cero");
        treeStr2.setLeft(new BinaryTree("uno"));
        treeStr2.setRight(new BinaryTree("dos"));
        treeStr2.getLeft().setLeft(new BinaryTree("tres"));
        treeStr2.getLeft().setRight(new BinaryTree("cuatro"));
        treeStr2.getRight().setLeft(new BinaryTree("cinco"));
        treeStr2.getRight().setRight(new BinaryTree("seis"));
        treeStr2.getRight().getRight().setRight(new BinaryTree("siete"));
        
        System.out.println("isIdentical iterativo");
        System.out.println("son identicos Integer?: " + treeInt.isIdenticalIterative(treeInt2, comparadorInt));
        
        System.out.println("isIdentical recursivo");
        System.out.println("son identicos Integer?: " + treeInt.isIdenticalRecursive(treeInt2, comparadorInt));
        
        System.out.println("isIdentical iterativo");
        System.out.println("son identicos String?: " + treeStr.isIdenticalIterative(treeStr2, comparadorStr));
        
        System.out.println("isIdentical recursivo");
        System.out.println("son identicos String?: " + treeStr.isIdenticalRecursive(treeStr2, comparadorStr));
        
        System.out.println("\n");
        
        System.out.println("largestValueOfEachLevel iteraivo");
        System.out.println("Numeros maximos de cada nivel Integer: ");
        treeInt.largestValueOfEachLevelIterative();
        
        System.out.println("largestValueOfEachLevel recursivo");
        System.out.println("Numeros maximos de cada nivel Integer: ");
        treeInt.largestValueOfEachLevelRecursive();

        
        System.out.println("\n");
        
        System.out.println("countNodesWithOnlyChild iterative");
        System.out.println("Numeros de nodos con un solo hijo Integer: " + treeInt.countNodesWithOnlyChildIterative());
        
        System.out.println("countNodesWithOnlyChild recursive");
        System.out.println("Numeros de nodos con un solo hijo Integer: " + treeInt.countNodesWithOnlyChildRecursive());
        
        System.out.println("countNodesWithOnlyChild iterative");
        System.out.println("Numeros de nodos con un solo hijo String: " + treeStr.countNodesWithOnlyChildIterative());
        
        System.out.println("countNodesWithOnlyChild recursive");
        System.out.println("Numeros de nodos con un solo hijo String: " + treeStr.countNodesWithOnlyChildRecursive());
        
        System.out.println("\n");
        
        System.out.println("isHeightBalanced iterativo");
        System.out.println("Arbol esta balanceado Integer?: " + treeInt.isHeightBalancedIterative());
        
        System.out.println("isHeightBalanced recursive");
        System.out.println("Arbol esta balanceado Integer?: " + treeInt.isHeightBalancedRecursive());
        
        System.out.println("isHeightBalanced iterativo");
        System.out.println("Arbol esta balanceado String?: " + treeStr.isHeightBalancedIterative());
        
        System.out.println("isHeightBalanced recursive");
        System.out.println("Arbol esta balanceado String?: " + treeStr.isHeightBalancedRecursive());
    }
}
