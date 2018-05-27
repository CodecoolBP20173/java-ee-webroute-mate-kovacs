package com.codecool.javaee;

public class AnnoClass {

    @WebRoute( path = "/test")
    public static String niceMethod(){
        System.out.println("This happens in the body of the method");
        return "Test";
    }

    @WebRoute( path = "/other")
    public static String otherNiceMethod(){
        System.out.println("This happens otherwise");
        return "Other";
    }
}
