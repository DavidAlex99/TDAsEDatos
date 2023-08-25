/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea_stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author alexx
 */
public class Essential {
    static Scanner sc = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        /*Deque<String> lista = new ArrayDeque<String>();
        StackDinamica<String> pila = new StackDinamica<>();
        String salida = "";
        String exp = "15 + 25";
        String[] partes = exp.split(" ");
        for(String parte: partes){
            lista.add(parte);
        }
        System.out.println(lista);
        while(lista.size()>0){
            String part = lista.removeFirst();
            System.out.println(part);
            System.out.println(pila);
            if (Character.isDigit(part.charAt(0))) {
                    //isNumeric = false;
                    salida = salida + part;      
            }
            if(part.charAt(0) >= '*' && part.charAt(0) <= '/'){
                while(!pila.isEmpty() && jerarquiaOp(part.charAt(0)) >= jerarquiaOp(pila.top().charAt(0))){
                    salida = salida + pila.pop();
                }
                pila.push(part);
            }
        }
        
        while(!pila.isEmpty()){
            salida = salida + pila.pop();
        }
        
        System.out.println(salida);*/
        /*Deque<String> stack = new ArrayDeque<String>();
        StackDinamica<Integer> pila = new StackDinamica<Integer>();
        
        System.out.println("Igrese una expresion:  ");
        //String entrada = sc.next();
        String exp = "13 16 +";
        //Condicion base
        if(exp == null || exp.length() ==0){
            System.out.println(-1);
        }
        String[] partes = exp.split(" ");
        for(String parte: partes){
            stack.add(parte);
        }
        System.out.println(stack);
        System.out.println(stack.size());
        
        while(stack.size()>0){
            //boolean isNumeric = true;
            String part = stack.removeFirst();
            System.out.println(part);
            System.out.println(stack);
            if (Character.isDigit(part.charAt(0))) {
                    //isNumeric = false;
                    int numero = Integer.parseInt(part);
                    pila.push(numero);
            }else{
                int x = pila.pop();
                int y = pila.pop();
                if(part.charAt(0) == '+'){
                    pila.push(y+x);
                }
                else if(part.charAt(0) == '-'){
                    pila.push(y-x);
                }
                else if(part.charAt(0) == '*'){
                    pila.push(y*x);
                }
                else if(part.charAt(0) == '/'){
                    pila.push(y/x);
                }
            }
            
            //si es un numero el string se lo pasa a la pila
            //if(isNumeric == true){
            //    int numero = Integer.parseInt(part);
            //    pila.push(numero);
            //}if()
            //pila.push(numero);
            //System.out.println(pila);
            //System.out.println(isNumeric);
            System.out.println(pila);
            System.out.println("Este es el top: " + pila.top());
        }*/
        
    }
    
    //Metodo para la jerarquila de la operacion, recibe como poarametro el signo en forma char
    public static int jerarquiaOp(char c){
        if(c == '*' || c == '/'){
            return 3;
        }
        if(c == '+' || c == '-'){
            return 4;
        }
        return 0;
    }
    
    public static int EvaluPosfija(){
        Deque<String> stack = new ArrayDeque<String>();
        StackDinamica<Integer> pila = new StackDinamica<Integer>();
        
        System.out.println("Igrese una expresion:  ");
        //String entrada = sc.next();
        String exp = "13 16 +";
        //Condicion base
        if(exp == null || exp.length() ==0){
            System.out.println(-1);
        }
        String[] partes = exp.split(" ");
        for(String parte: partes){
            stack.add(parte);
        }
        System.out.println(stack);
        /*for (String element : stack) {
            // Print the corresponding element
            System.out.println("Element : " + element);
        }*/
        System.out.println(stack.size());
        
        while(stack.size()>0){
            //boolean isNumeric = true;
            String part = stack.removeFirst();
            System.out.println(part);
            System.out.println(stack);
            /*for (int i = 0; i < part.length(); i++) {
                if (!Character.isDigit(part.charAt(i))) {
                    isNumeric = false;
                }
            }*/
            if (Character.isDigit(part.charAt(0))) {
                    //isNumeric = false;
                    int numero = Integer.parseInt(part);
                    pila.push(numero);
            }else{
                int x = pila.pop();
                int y = pila.pop();
                if(part.charAt(0) == '+'){
                    pila.push(y+x);
                }
                else if(part.charAt(0) == '-'){
                    pila.push(y-x);
                }
                else if(part.charAt(0) == '*'){
                    pila.push(y*x);
                }
                else if(part.charAt(0) == '/'){
                    pila.push(y/x);
                }
            }
            
            //si es un numero el string se lo pasa a la pila
            /*if(isNumeric == true){
                int numero = Integer.parseInt(part);
                pila.push(numero);
            }if()
            pila.push(numero);
            System.out.println(pila);
        System.out.println(isNumeric);*/
            System.out.println(pila);
            System.out.println("Este es el top: " + pila.top());
        }
        return(pila.top());
    }
        public static String InfijaToPosfija(String exp){
            Deque<String> lista = new ArrayDeque<String>();
            StackDinamica<String> pila = new StackDinamica<>();
            String salida = "";
            //String exp = "15 + 25";
            String[] partes = exp.split(" ");
            for(String parte: partes){
                lista.add(parte);
            }
            System.out.println(lista);
            while(lista.size()>0){
                String part = lista.removeFirst();
                System.out.println(part);
                System.out.println(pila);
                if (Character.isDigit(part.charAt(0))) {
                        //isNumeric = false;
                    salida = salida + part;      
                }   
                if(part.charAt(0) >= '*' && part.charAt(0) <= '/'){
                    while(!pila.isEmpty() && jerarquiaOp(part.charAt(0)) >= jerarquiaOp(pila.top().charAt(0))){
                        salida = salida + pila.pop();
                    }
                    pila.push(part);
                }
            }
        
            while(!pila.isEmpty()){
                salida = salida + pila.pop();
            }
        
            return salida;
        }
        
        
        
        /*StackDinamica<Character> pila = new StackDinamica<>();
        Deque<String> stack = new ArrayDeque<String>();
        System.out.println("Igrese una expresion:  ");
        //String entrada = sc.next();
        String entrada = "0 + 0 * 0";
        String salida = "";
        String[] lista = entrada.split(" ");
        for(String ls: lista){
            for(int i = 0; i<ls.length(); i++){
                if(entrada.charAt(i) >= '0' && entrada.charAt(i) <= '9'){
                salida = salida + entrada.charAt(i);
                }
                if(entrada.charAt(i) >= '*' && entrada.charAt(i) <= '/'){
                pila.push(entrada.charAt(i));
                }
            }
            
        }
        System.out.println("myInteger es de tipo " +  ((Object)stack).getClass().getSimpleName());
        System.out.println(stack);
        for (Iterator itr = stack.iterator();
             itr.hasNext();) {
            System.out.println(itr.next());
        }*/
        
        


        
        
    
    
    /*
    public static String InfijaToPostfija(){
        StackDinamica<Character> pila = new StackDinamica<>();
        System.out.println("Igrese una expresion:  ");
        String entrada = sc.next();
        
        
        //resultado final de la conversion
        String salida = "";
        for(int i=0; i< entrada.length(); i++){
            if(entrada.charAt(i) >= '0' && entrada.charAt(i) <= '9'){
                salida = salida + entrada.charAt(i);
            }
            if(entrada.charAt(i) >= '*' && entrada.charAt(i) <= '/'){
                pila.push(entrada.charAt(i));
            }
            if(entrada.charAt(i) == ')'){
                //si ya se llego al parentesis de cierre entonces se saca el operador de la pila y se la 
                //agtega en la cadena de salida
                salida = salida + pila.pop();
            }
        }
        while(!pila.isEmpty()){
            salida = salida + pila.pop();
        }
        
        return salida;
    }*/
    
    //Metodo para la jerarquila de la operacion, recibe como poarametro el signo en forma char
    /*public static int jerarquiaOp(char c){
        if(c == '*' || c == '/'){
            return 3;
        }
        if(c == '+' || c == '-'){
            return 4;
        }
        
    }*/
        
    /*public static String InfijaToPostfija(){
        Deque<String> lista = new ArrayDeque<String>();
        String exp = "";
        String[] partes = exp.split(" ");
        for(String parte: partes){
            lista.add(parte);
        }
        
    }*/
    
   
    
}
