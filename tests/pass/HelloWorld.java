// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package pass;

import java.lang.System;

public class HelloWorld {

    public static String message() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        System.out.println(HelloWorld.message());
        int a = 2 + 2;
        System.out.println(a);
    }

}