/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.main;

import com.github.ivanovskij.parser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author IOAdmin
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("Input Expression: ");
            String expression = getString();
            if (expression.equals("")) {
                break;
            }
            Parser parser = new Parser(expression);
            System.out.println("Result = " + parser.parse());
        }
    }

    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String expr = br.readLine();
        return expr;
    }
    
}
