package com.codecool.javaee;

public class AnnoClass {

    @WebRoute( path = "/test")
    public static void niceMethod(){
        System.out.println("This happens in the body of the method");
    }

    @WebRoute( path = "/other")
    public static void otherNiceMethod(){
        System.out.println("This happens otherwise");
    }
}
