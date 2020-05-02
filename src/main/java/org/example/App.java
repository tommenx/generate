package org.example;

import java.io.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        String path = "/Users/tommenx/Desktop/info.json";
        Parser parser = new Parser(path);
        List<Segment> list = parser.parse();
        Painter painter = new Painter.Builder()
                .header(10)
                .body(30)
                .segments(list)
                .build();

        String res = painter.paint();
        System.out.println(res);
    }
}
