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
        String exp1 = "25 + 15 - 15";
        String exp2 = "6 12 3 + - 31 18 27 / + * 2 ^ 3 +";
        System.out.println("La conversion de expresion infija " + exp1 +" a posfija es: " + InfijaToPosfija(exp1));
        System.out.println("La evaluacion de la expresion posfija " + exp2 + " es: " + EvaluPosfija(exp2));
    }
    
    //Metodo para la jerarquila de la operacion, recibe como poarametro el signo en forma char
    public static int jerarquiaOp(char c){
        if(c == '^'){
            return 3;
        }
        if(c == '*' || c == '/'){
            return 4;
        }
        if(c == '+' || c == '-'){
            return 5;
        }
        return 0;
    }
    
    public static int EvaluPosfija(String exp){
        Deque<String> stack = new ArrayDeque<String>();
        StackDinamica<Integer> pila = new StackDinamica<Integer>();
        //Condicion base
        if(exp == null || exp.length() ==0){
            System.out.println(-1);
        }
        String[] partes = exp.split(" ");
        for(String parte: partes){
            stack.add(parte);
        }
        
        while(stack.size()>1){
            //boolean isNumeric = true;
            String part = stack.removeFirst();
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
        }
        return(pila.top());
    }
        public static String InfijaToPosfija(String exp){
            Deque<String> lista = new ArrayDeque<String>();
            StackDinamica<String> pila = new StackDinamica<>();
            String salida = "";
            String[] partes = exp.split(" ");
            for(String parte: partes){
                lista.add(parte);
            }
            while(lista.size()>0){
                String part = lista.removeFirst();
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

}
