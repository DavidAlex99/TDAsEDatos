/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Diccionario1;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author alexx
 */
public class HashMap1 {
    public static void main(String args[]){
        HashMap<String, Integer> inventario = new HashMap<>();

        Scanner sn = new Scanner(System.in);
        
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        sn.useDelimiter("\n");
        while (!salir) {

            System.out.println("1. Añadir producto");
            System.out.println("2. Aumentar stock de un producto");
            System.out.println("3. Eliminar stock de un producto");
            System.out.println("4. Mostrar productos y su stock");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                String producto;
                //stock es el stock que estamos como decir ingresando desde el usuario
                //stockActual es el stock que se encuentra verdaderamente para cada producto
                int stock, stockActual;
                switch (opcion) {
                    case 1:

                        System.out.println("Escribe el nombre del producto");
                        producto = sn.next();

                        if (inventario.containsKey(producto)) {
                            System.out.println("Ya existe el producto");
                        } else {
                            inventario.put(producto, 0);
                            System.out.println("Se ha añadido el producto");
                        }

                        break;
                    case 2:

                        System.out.println("Escribe el nombre del producto");
                        producto = sn.next();

                        if (inventario.containsKey(producto)) {

                            System.out.println("Introduce una cantidad");
                            stock = sn.nextInt();

                            if (stock > 0) {
                                stockActual = inventario.get(producto);
                                inventario.put(producto, stockActual + stock);
                                System.out.println("Se ha añadido " + stock + " de stock al producto " + producto);
                            } else {
                                System.out.println("No se puede añadir un stock negativo");
                            }

                        } else {
                            System.out.println("No existe el producto");
                        }

                        break;
                    case 3:
                        System.out.println("Escribe el nombre del producto: ");
                        producto = sn.next();
                        
                        if(inventario.containsKey(producto)){
                            System.out.println("Stock a eliminar");
                            stock = sn.nextInt();
                            
                            if(stock > 0){
                                stockActual = inventario.get(producto);
                                //aqui quitamso el cantidad de stock que contiene cada producto
                                if(stockActual > stock ){
                                    inventario.put(producto, stockActual - stock);
                                }else{
                                    System.out.println("No hay suficiente stock a eliminar");
                                    
                                }
                            }else{
                                System.out.println("No se puede eliminar un stock negativo");
                            }
                        }else{
                            System.out.println("No existe el producto");
                        }
                        break;
                    case 4:
                        //Recorreindo un Hashmap, pero ahofra se lo va a recorrer de una forma sencilla
                        //recorriendolo tomando las claves ue tiene el map
                        for(String p: inventario.keySet()){
                            stock = inventario.get(p);
                            System.out.println("Clave " + p + "Valor: " + stock);
                        }
                        break;
                    case 5:
                        System.out.println("Escribe el producto a eliminar");
                        producto = sn.next();
                        if(inventario.containsKey(producto)){
                            inventario.remove(producto);
                            System.out.println("Producto eliminado");
                        }else{
                            System.out.println("No existe el producto");
                        }
                        
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
        
}
