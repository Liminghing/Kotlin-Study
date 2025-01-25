package com.jkweyu.interoperability.CallJavaFromKotlin;

import java.util.ArrayList;

public class CallJava {
    public static String message = "Hello from 자바";

    public static void add(int i, int j){
        System.out.println(i + "+" + j + "=" + (i+j));
    }

    public static ArrayList<Integer> getIntList(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        return integers;
    }
    public static ArrayList<Integer> getInt1List(){

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);integers.add(2);integers.add(3);

        return integers;
    }
    public static void is(){
        System.out.println("is is a reserved keyword in Kotlin :-) ");

    }
    public static void var(){
        System.out.println("var is a reserved keyword in Kotlin :-) ");
    }


}
