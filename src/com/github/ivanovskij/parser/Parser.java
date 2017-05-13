/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.parser;

/**
 *
 * @author IOAdmin
 */
public class Parser {
    
    private final String input;
    private final Infix infix;

    public Parser(String input) {
        this.input = input;
        infix = new Infix(input);
    }
    
    public int parse() {
        StringBuilder postfixStr = infix.transform();
        int answer = new Postfix(postfixStr).calculate();
        return answer;
    }
}
