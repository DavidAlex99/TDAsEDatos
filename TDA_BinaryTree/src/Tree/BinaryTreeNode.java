/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tree;

/**
 *
 * @author alexx
 */
public class BinaryTreeNode<E> {
    private E content;
    private BinaryTree<E> right;
    private BinaryTree<E> left;
    
    public BinaryTreeNode(){
        this.content = null;
        this.right = null;
        this.left = null;
    }
    
    public BinaryTreeNode(E content){
        this.content = content;
        this.left = null;
        this.right = null;
    }
    
    public BinaryTreeNode(E content, BinaryTree<E> left, BinaryTree<E> right){
        this.content = content;
        this.left = left;
        this.right = right;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }
    
    
}
