// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package pass;

import java.lang.System;

public class HelloWorld {

    public static String message() {
    	int a = 2 / 2;
        return "Hello, World!";
    }

    public static void main(String[] args) {
        System.out.println(HelloWorld.message());
     	double a = 0.2;
     	a = 022.2;
     	a = 0.2f;
     	a = 0.2d;
     	int b = 2;
     	long c = 2L;
     	c = 2l;
     			
     	char d = 'a';
     	boolean b = true;
    }

}