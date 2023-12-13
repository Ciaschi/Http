package com.example;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Server s1 = new Server();
        s1.openServer();

        for(;;)
            s1.accept();
            
            
    }
}
