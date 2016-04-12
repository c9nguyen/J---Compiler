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
     	//success
        success();
  
     	//fail
     	fail();
    }
    
    static void success() {
        int a = 1;
        a = 0x2AE;
        a = 0b3;
        a = 04;
        a = 6___23;
        
        long b = 1l;
        b = 0x2l;
        b = 0b3l;
        b = 04l;
        b = 0x5__2;
        
        double c = 01;
        c = 0.2;
        c = 0.3d;
        c = 0.3D;
        c = 1.5;
        c = 1.6___7;
        
        float d = 1;
        d = 2f;
        d = 3F;
        d = 0.004;
     	d = 0x5f;
     	d = 06;
     	
     	boolean e = true;
     	e = false;
     	
     	/**
     	 * should be ignored
     	 */
     	
     	a = 2 / 3;
     	a /= 3;
     	a = a >>> 2;
     	a = a % 2;
     	a %= 2;
     	
    }
    
    static void fail() {
    	 int a = 1_;
         a = 0x_2;
         a = 0_x2;
         a = 0xT;
         a = 0x;
         a = 0b3;
         a = 08;
         a = _6;
        
         double b = 0..2;
         b = 0._2;
         b = 1_.2;
     	
    }

}