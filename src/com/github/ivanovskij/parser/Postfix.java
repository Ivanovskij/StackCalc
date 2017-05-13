/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.parser;

import com.github.ivanovskij.util.Stack;

/**
 *
 * @author IOAdmin
 */
public class Postfix {
    
    private final StringBuilder input;
    private final Stack stack;
    
    
    public Postfix(StringBuilder input) {
        this.input = input;
        stack = new Stack();
    }
    
    public int calculate() {
        int num1, num2, answer;
        String number;      // считывание числа
        
        int length_input = input.length();
        
        for (int pos = 0; pos < length_input; pos++) {
            char current = input.charAt(pos);
            stack.display("For " + current + "");
            
            number = "";
            if (isWhiteSpace(current)) {
                // пропускаем пробел
                // NOP
            } else if (isDigit(current)) {
                pos = gotNumber(current, number, pos);  // позиция после считывания числа
            } else {
                // считываем два числа из стэка
                num2 = (int) stack.pop();        
                num1 = (int) stack.pop();
                
                switch (current)    // выполняем операцию
                {
                    case '+':
                    {
                        answer = num1 + num2;
                        break;
                    }
                    case '-':
                    {
                        answer = num1 - num2;
                        break;
                    }
                    case '*':
                    {
                        answer = num1 * num2;
                        break;
                    }
                    case '/':
                    {
                        answer = num1 / num2;
                        break;
                    }
                    default: 
                    {
                        throw new RuntimeException("Operation not defined!");
                    }
                }
                // записываем ответ в стэк
                stack.push(answer);
            }
        }
        
        // конечный результат будет лежать в стэке
        answer = (int) stack.pop();
        return answer;
    }

    //--------------------------------------------------------
    private int gotNumber(char current, String number, int pos) throws NumberFormatException {
        while (isDigit(current)) {      // считыаем число
            number += current;
            pos = offset(pos, 1);
            current = next(current, pos);
            stack.display("For " + current);
        }
        pos--;  
        // кладем число в стэк
        stack.push(Integer.parseInt(number));
        return pos;
    }

    //--------------------------------------------------------
    private boolean isDigit(char current) {
        return (current >= '0' && current <= '9');
    }

    //--------------------------------------------------------
    private boolean isWhiteSpace(char current) {
        return (current == ' ');
    }
    
    //--------------------------------------------------------
    private int offset(int pos, int offsetPos) {
        pos += offsetPos;
        return pos;
    }
    
    //--------------------------------------------------------
    private char next(char opThis, int pos) {
        if (pos >= input.length()) { return '\0'; }
        opThis = input.charAt(pos);
        return opThis;
    }
}
