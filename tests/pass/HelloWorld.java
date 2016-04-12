// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package pass;

import java.lang.System;

public class HelloWorld {

    public static String message() {
    	int a = 2 + 2;
        return "Hello, World!";
    }

    public static void main(String[] args) {
        System.out.println(HelloWorld.message());
     	int b = 07;
     	b = 0x23;
     	b = 0b101;
  
    }

}