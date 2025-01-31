package com.jkweyu.exception;

import java.io.IOException;

public class InteroperabilityExceptionJava {
    public static void main(String[] args) {
        try {
            InteroperabilityExceptionKt.testMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
