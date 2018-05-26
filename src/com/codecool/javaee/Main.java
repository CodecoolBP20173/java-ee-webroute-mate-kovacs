package com.codecool.javaee;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer testServer = HttpServer.create(new InetSocketAddress(8080), 0);
        for(Method method : Class.forName("com.codecool.javaee.AnnoClass").getMethods()){
            if (method.getDeclaringClass().equals(AnnoClass.class)) {
                WebRoute route = method.getAnnotation(WebRoute.class);
                String path = route.path();
                testServer.createContext(path, new MyHandler());
            }
        }
        testServer.setExecutor(null);
        testServer.start();
    }


    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            String path = t.getRequestURI().toString();

            try {
                for (Method method : Class.forName("com.codecool.javaee.AnnoClass").getMethods()) {
                    WebRoute route = method.getAnnotation(WebRoute.class);
                    if (route.path().equals(path)) {
                        method.invoke(null);
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException ex){
                ex.printStackTrace();
            }

//            String response = "This is the response";
//            t.sendResponseHeaders(200, response.length());
//            OutputStream os = t.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
        }
    }

}