/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q1;

import java.util.function.UnaryOperator;

/**
 *
 * @author Abdallah_Ahmed
 */
public class Main {
    
    
    public static void main(String[] args) {
        
        //task a
        IntConsumer i = v -> System.out.printf("%d\n",v);
        i.accept(10);
        
        //task b
        UnaryOperator<String> op = String::toUpperCase;
        System.out.println(op.apply("Ali"));
        
        //task c
        myInterface<String> s = () -> {return "welcome to lambdas !";};
        System.out.println(s.applay());
        
        //task d
        UnaryOperator<Double> sqrt = Math::sqrt;
        System.out.println(sqrt.apply(8.0).intValue());
        
        //task e
        UnaryOperator<Integer> cube = value -> value*value*value;
        System.out.println(cube.apply(2));
        
    }
    
    interface myInterface<E>{
        public E applay();
    }
    
    interface IntConsumer{
        public void accept(int value);
    }
}