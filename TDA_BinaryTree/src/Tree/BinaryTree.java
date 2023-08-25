/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author alexx
 */
public class BinaryTree<E> {
    
    private BinaryTreeNode<E> root;
    
    public BinaryTree(){
        this.root = new BinaryTreeNode();
    }
    
    public BinaryTree(E content){
        this.root = new BinaryTreeNode<>(content);
    }
    
    public BinaryTree(BinaryTreeNode<E> root){
        this.root = root;
    }

    //Verificar si el arbol esta vacio
    public boolean isEmpty(){
        return this.root == null;
    }
    
    public E getRoot(){
        if(!this.isEmpty()){
            return root.getContent();
        }
        return null;
    }
    
    public boolean setLeft(BinaryTree<E> e){
        if(!this.isEmpty()){
            root.setLeft(e);
            return true;
        }
        return false;
    }
    
    public boolean setRight(BinaryTree<E> e){
        if(!this.isEmpty()){
            root.setRight(e);
            return true;
        }
        return false;
    }
    
    public BinaryTree<E> getLeft(){
        if(!this.isEmpty()){
            return root.getLeft();
        }
        return null;
    }
    
    public BinaryTree<E> getRight(){
        if(!this.isEmpty()){
            return root.getRight();
        }
        return null;
    }
    
    public boolean isLeaf(){
        if(!this.isEmpty()){
            return this.root.getLeft() == null && 
                    this.root.getRight() == null;
            
        }
        return false;
    }
    
    //metodo conteo descendientes iterativo
    public int countDescendantsIterative(){
        if(this.isEmpty()){
            return 0;
        }else{
            Stack<BinaryTree<E>> desc = new Stack<>();
            int contDesc = 0;
            
            if(this.getLeft() != null){
                desc.push(this.getLeft());;
            }
            if(this.getRight() != null){
                desc.push(this.getRight());
            }
            
            while(!desc.isEmpty()){
                BinaryTree<E> subTree = desc.pop();
                contDesc++;
                
                if(subTree.getLeft() != null){
                    desc.push(subTree.getLeft());;
                }
                if(subTree.getRight() != null){
                    desc.push(subTree.getRight());
                }
            }
            return contDesc;
        }
    }
    
    public int countDescendantsRecursive(){
        if(this.isEmpty()){
            return 0;
        } else if(this.isLeaf()){
            return 0;
        } else {
            int contDesc = 0;
            
            if(this.getLeft() != null){
                contDesc += this.getLeft().countDescendantsRecursive() + 1;
            }
            if(this.getRight() != null){
                contDesc += this.getRight().countDescendantsRecursive() + 1;
            }
            return contDesc;
        }
    }
    
    //devolver el padre dado un nodo (raiz no tiene padre)
    public BinaryTree<E> findParentIterative(E contenidoHijo, Comparator<E> comparador){
        if(contenidoHijo == null){
            return null;
        }
        if(this.isEmpty()){
            return null;
        }else{
            Stack<BinaryTree<E>> pila = new Stack<>();
            pila.push(this);
            
            while(!pila.isEmpty()){
                BinaryTree<E> padreActual = pila.pop();
                
                //puesto que es el mismo padre para sus dos hijos
                if((padreActual.getLeft() != null && comparador.compare(padreActual.getLeft().getRoot(), contenidoHijo) == 0) ||
                        padreActual.getRight() != null && comparador.compare(padreActual.getRight().getRoot(), contenidoHijo) == 0){
                    return padreActual;       
                }
                //condicional solo para la disponibilidad de hijos y su pusheo
                if(padreActual.getLeft() != null){
                    pila.push(padreActual.getLeft());
                }
                if(padreActual.getRight() != null){
                    pila.push(padreActual.getRight());
                }
            }
        }
        //Ninguno hijo se ha encontrado
        return null;
    }
    
    public BinaryTree<E> findParentRecursive(E contenidoHijo, Comparator<E> comparador){
        if(contenidoHijo == null || this.isEmpty()){
            return null;
        }else{
            BinaryTree<E> leftResult = null;
            BinaryTree<E> rightResult = null;
            
            if((this.getRight() != null && comparador.compare(this.getRight().getRoot(), contenidoHijo) == 0) ||
                (this.getLeft() != null && comparador.compare(this.getLeft().getRoot(), contenidoHijo) == 0)){
                return this;
            }
            
            if(this.getLeft() != null){
                leftResult = this.getLeft().findParentRecursive(contenidoHijo, comparador);
            }
            
            if(this.getRight() != null){
                rightResult = this.getRight().findParentRecursive(contenidoHijo, comparador);
            }
            
            if(leftResult != null){
                return leftResult;
            }else{
                return rightResult;
            }//El return es importante solo cuano devuelve el tipo de objeto
            //que indica e metodo
        }  
    }
    
    public int countLevelsIterative(){
        if(this.isEmpty()){
            return 0;
        }else{
            int contLevels = 0;
            Queue<BinaryTree<E>> cola = new LinkedList<>();
            cola.offer(this);
            
            while(!cola.isEmpty()){
                int sizeCola = cola.size();
                while(sizeCola > 0){
                    BinaryTree<E> subtree = cola.poll();
                    sizeCola--;
                    if(subtree.getLeft() != null){
                        cola.offer(subtree.getLeft());
                    }
                    if(subtree.getRight() != null){
                        cola.offer(subtree.getRight());
                    }
                }
                contLevels++;
            }
            return contLevels;
        }
    }
    
    public int countLevelsRecursive(){
        if(this.isEmpty()){
            return 0;
        }else{
            int leftTree = 0;
            int rightTree = 0;
            
            if(this.getLeft() != null){
                leftTree = this.getLeft().countLevelsRecursive();
            }
            if(this.getRight() != null){
                rightTree = this.getRight().countLevelsRecursive();
            }
            if(leftTree > rightTree){
                return leftTree + 1;
            }else{
                return rightTree + 1;
            }
        }
    }
    
    public boolean isLeftlyIterative(){
        /*
         un árbol binario es considerado zurdo si y solo si todos sus nodos cumplen la condición de tener más 
        descendientes en su subárbol izquierdo que en su subárbol derecho (o, si el nodo es una hoja, no tiene 
        descendientes). Si incluso un solo nodo en el árbol no cumple con esta condición, entonces el árbol entero 
        no se considera zurdo.
        */
        if(this.isEmpty() || this.isLeaf()){
            return true;
        } else {
            Queue<BinaryTree<E>> cola = new LinkedList<>();
            cola.offer(this);
            
            while(!cola.isEmpty()){
                BinaryTree<E> temp = cola.poll();
                int descMainLeft = 0;
                int descMainRight = 0;
                
                //Numero total de descendientes en el subarbol izquierdo principal
                if(temp.getLeft() != null){
                    descMainLeft = temp.getLeft().countDescendantsIterative();
                }
                if(temp.getRight() != null){
                    descMainRight = temp.getRight().countDescendantsIterative();
                }
                
                if(descMainLeft <= descMainRight){
                    //Por solo una falla en un nodo y en sus hijo, el arol ya no es zurdo
                    return false;
                }
                
                if(temp.getRight() != null){
                    cola.offer(temp.getRight());
                }
                if(temp.getLeft() != null){
                    cola.offer(temp.getLeft());
                }
            }  
            return true;
        }
    }
    
    public boolean isLeftlyRecursive(){
        if(this.isEmpty() || this.isLeaf()){
            return true;
        }else{
            int descMainLeft = 0;
            int descMainRight = 0;
            
            if(this.getLeft() != null){
                descMainLeft = this.getLeft().countDescendantsRecursive();
            }
            if(this.getRight() != null){
                descMainRight = this.getRight().countDescendantsRecursive();
            }
            
            if(descMainLeft <= descMainRight){
                return false;
            }
            
            //valores booleanos
            //subarbol izquierdo
            boolean isLeftlyLeft = true;
            //subarbol derecho
            boolean isLeftlyRight = true;
            
            if(this.getLeft() != null){
                isLeftlyLeft = this.getLeft().isLeftlyRecursive();
            }
            
            if(this.getRight() != null){
                isLeftlyRight = this.getRight().isLeftlyRecursive();
            }
            
            return isLeftlyLeft && isLeftlyRight;
        }
    }
    
    //metodo isIdenticla
    public boolean isIdenticalIterative(BinaryTree<E> otro, Comparator<E> cmp){
        if(this.isEmpty() && otro.isEmpty()){
            return true;
        }
        if(this.isEmpty() || otro.isEmpty()){
            return true;
        }
        
        Queue<BinaryTree<E>> cola1 = new LinkedList<>();
        Queue<BinaryTree<E>> cola2 = new LinkedList<>();
        cola1.offer(this);
        cola2.offer(otro);
        
        //Se usa el operador &&, por la condicion de que deben de ser iguales
        while(!cola1.isEmpty() && !cola2.isEmpty()){
            BinaryTree<E> nodo1 = cola1.poll();
            BinaryTree<E> nodo2 = cola2.poll();
            
            //el contenido de los nodos que se esta comparado son diferentes
            if(cmp.compare(nodo1.getRoot(), nodo2.getRoot()) != 0){
                return false;
            }
            
            if (nodo1.getLeft() != null) {
                cola1.offer(nodo1.getLeft());
            }
            if (nodo2.getLeft() != null) {
                cola2.offer(nodo2.getLeft());
            }

            if (nodo1.getRight() != null) {
                cola1.offer(nodo1.getRight());
            }
            if (nodo2.getRight() != null) {
                cola2.offer(nodo2.getRight());
            }
        }
        return cola1.isEmpty() && cola2.isEmpty();
        /*
            // Si una de las colas aún tiene elementos y la otra no, los árboles no son iguales
            return queue1.isEmpty() && queue2.isEmpty();
        */
    }
    
    public boolean isIdenticalRecursive(BinaryTree<E> otro, Comparator<E> cmp){
        if(this.isEmpty() && otro.isEmpty()){
            return true;
        }
        if(this.isEmpty() || otro.isEmpty()){
            return false;
        }
        if(cmp.compare(this.getRoot(), otro.getRoot()) != 0){
            return false;
        }
        
        boolean leftIdentico = false;
        boolean RightIdentico = false;
        if((this.getLeft() == null && otro.getLeft() == null) && (this.getRight() == null && otro.getRight() == null)){
            leftIdentico = true;
            RightIdentico = true;
        }
        if((this.getLeft() != null && this.getLeft().isIdenticalRecursive(otro.getLeft(), cmp)) && 
                (this.getRight() != null && this.getRight().isIdenticalRecursive(otro.getRight(), cmp))){
            leftIdentico = true;
            RightIdentico = true;
        }
        
        return leftIdentico && RightIdentico;
        
    }
    
    
    //Nivel maximo de cada nivel
    //Para que funcione con cualquier arbol de cualquier dato
    public void largestValueOfEachLevelIterative(){
        if(this.isEmpty()){
            return;
        }
        Queue<BinaryTree<Integer>> cola = new LinkedList<>();
        cola.offer((BinaryTree<Integer>) this);
        
        while(!cola.isEmpty()){
            int levelElements = cola.size();
            int maxValueLevel = 0;
            
            while(levelElements > 0){
                BinaryTree<Integer> temp = cola.poll();
                if(temp.getRoot() > maxValueLevel){
                    maxValueLevel = temp.getRoot();
                }
                
                if (temp.getLeft() != null) {
                    cola.offer((BinaryTree<Integer>) temp.getLeft());
                }
                if (temp.getRight() != null) {
                    cola.offer((BinaryTree<Integer>) temp.getRight());
                }
                //para disminuir de acuerdo que se retira de la cola
                levelElements--;
            }
            System.out.println(maxValueLevel);
        }
    }
    
    public void largestValueOfEachLevelRecursive(){
        List<Integer> largestValues = new ArrayList<>();
        largestValueOfEachLevelRecursive((BinaryTree<Integer>) this, largestValues, 0);
        for(Integer value: largestValues){
            System.out.println(value);
        }
    }
    
    private void largestValueOfEachLevelRecursive(BinaryTree<Integer> tree, List<Integer> arrNNumeros, int nivel){
        if(tree == null){
            return;
        }
        /*
        if(tree.isEmpty()){
            return;
        }
        */

        // Añadir el valor actual a la lista si es el primer nodo en este nivel
        if (nivel == arrNNumeros.size()) {
            arrNNumeros.add(tree.getRoot());
        } 
        else if (tree.getRoot() > arrNNumeros.get(nivel)) {
            arrNNumeros.set(nivel, tree.getRoot());
        }

        // Procesar los hijos del nodo actual
        largestValueOfEachLevelRecursive(tree.getLeft(), arrNNumeros, nivel + 1);
        largestValueOfEachLevelRecursive(tree.getRight(), arrNNumeros, nivel + 1);
    }
    
    //metodo contar nodos con un solo hijo
    public int countNodesWithOnlyChildIterative(){
        if(this.isEmpty()){
            return 0;
        }
        int contador = 0;
        Queue<BinaryTree<E>> cola = new LinkedList<>();
        cola.offer(this);
        
        while(!cola.isEmpty()){
            BinaryTree<E> temp = cola.poll();
            
            // Verifica si el nodo actual tiene un solo hijo
            if ((temp.getLeft() != null && temp.getRight() == null) 
                || (temp.getLeft() == null && temp.getRight() != null)) {
                contador++;
            }

            // Agrega los hijos del nodo actual a la cola
            if (temp.getLeft() != null) {
                cola.offer(temp.getLeft());
            }
            if (temp.getRight() != null) {
                cola.offer(temp.getRight());
            }
        }
        return contador;
    }
    
    public int countNodesWithOnlyChildRecursive(){
        if(this.isEmpty()){
            return 0;
        }
        else{
            int contador = 0;
            
            //verificacion del nodo raiz que se esta tratando
            if((this.getLeft() != null && this.getRight() == null) || 
                    (this.getLeft() == null && this.getRight() != null)){
                contador = 1;
            }
            
            //contador de arboles izq y der que se esta tratando
            int leftCont = 0;
            int rightCont = 0;
            if(this.getLeft() != null){
                leftCont = this.getLeft().countNodesWithOnlyChildRecursive();
            }
            if(this.getRight() != null){
                rightCont = this.getRight().countNodesWithOnlyChildRecursive();
            }
            return contador + leftCont + rightCont;
        }
    }
    
    //metodo arbol balanceado en altura
    public boolean isHeightBalancedIterative(){
        if(this.isEmpty()){
            return true;
        }
        
        //Los mismos elementos tendran el mismo orden en la pila
        Stack<BinaryTree<E>> tempNode = new Stack<>();
        //Aunque solo sirva para retirar el valor altura de lapila, ya quw no se usa para ada mas, pero permite
        //anetener el mismo orden con la pila tempNode
        Stack<Integer> tempNodeHeight = new Stack<>();
        
        tempNode.push(this);
        tempNodeHeight.push(this.countLevelsIterative());
        
        while(!tempNode.isEmpty()){
            BinaryTree<E> current = tempNode.pop();
            int altura = tempNodeHeight.pop();
            
            int leftAltura = 0;
            int rightAltura = 0;
            if(current.getLeft() != null){
                leftAltura = current.getLeft().countLevelsIterative();
            }
            if(current.getRight() != null){
                leftAltura = current.getRight().countLevelsIterative();
            }
            if (Math.abs(leftAltura - rightAltura) > 1) {
                return false;
            }

            if (current.getLeft() != null) {
                tempNode.push(current.getLeft());
                tempNodeHeight.push(leftAltura);
            }

            if (current.getRight() != null) {
                tempNode.push(current.getRight());
                tempNodeHeight.push(rightAltura);
            }
        }
        return true;
    }
    
    
    public boolean isHeightBalancedRecursive() {
        if (this.isEmpty()) {
            return true;
        }
        return isSubtreeBalancedRecursive(this) != -1;
    }

    private int isSubtreeBalancedRecursive(BinaryTree<E> currentNode) {
        if (currentNode == null) {
            return 0;
        }


        int leftHeight = isSubtreeBalancedRecursive(currentNode.getLeft());
        int rightHeight = isSubtreeBalancedRecursive(currentNode.getRight());

        // Si la altura de uno de los subárboles es -1, entonces el árbol no está balanceado
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // Si la diferencia de alturas es mayor que 1, el árbol no está balanceado
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Si el árbol está balanceado, devolvemos su altura
        return Math.max(leftHeight, rightHeight) + 1;
    }