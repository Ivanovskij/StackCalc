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
public class Infix {
    
    private final String input;
    private final StringBuilder output;
    private final Stack stackOper;
    private final String OPERATORS = "+-*/()";

    public Infix(String input) {
        this.input = input;
        stackOper = new Stack();
        output = new StringBuilder();
    }
    
    //--------------------------------------------------------
    public StringBuilder transform() {
        int length_input = input.length();
        
        for (int pos = 0; pos < length_input; pos++) {
            char current = input.charAt(pos);               // текущий эл-т во входной строке
            
            switch(current) 
            {
                case '(':                   // если открывающая скобка
                {                           // заносим ее в стэк
                    stackOper.push(current);
                    break;
                }
                case ')':       // если закрывающая скобка
                {               // берем все знаки по приотритету в скобках
                    gotParen(); // и находим закрывающую скобку
                    break;
                }
                case '+':
                case '-':
                {           // приоритет 1
                    gotOperation(current, 1); 
                    break;
                }
                case '*':
                case '/':
                {           // приоритет 2
                    gotOperation(current, 2);
                    break;
                }
                case ' ':
                {          // пропускаем пробел
                    // NOP
                    break;
                }
                default:
                {           // считываем все число
                    pos = gotNumber(current, pos);  // позиция после считывания числа
                    break;
                }
            }
            
        }
        
        while (!stackOper.isEmpty()) {          // все операции, что остались в стэке
            output.append(stackOper.pop());     // записываем в строку
            output.append(" ");
        }
        
        System.out.println("Postfix: " + output);
        return output;
    }

    //--------------------------------------------------------
    private int gotNumber(char current, int pos) {  // получение числа
        while (isDigit(current)) {          // пока цифра считываем
            output.append(current);
            pos = offset(pos, 1);           // сдвигаемся вперед на одну позицию
            current = next(current, pos);   // следующий оператор в входной строке
        }
        // если разделитель или оператор или конец строки, то присваиваем пробел
        if (isDelimetr(current) || isOperator(current) || isEndStr(current)) {
            output.append(" ");
        }
        // уменьшаем позицию на единицу
        // чтобы двигаться от предыдущего оператора
        // так как выше в цикле while: current = next(current, pos);
        // мы сдвинемся(грубо говоря, один раз сдвинемся зря) 
        // и если не уменьшим позицию
        // то не захватим предыдущий оператор
        pos--; 
        return pos;
    }

    //--------------------------------------------------------
    private void gotParen() {           // обработка закрывающей скобки
        while (!stackOper.isEmpty()) {
            char oper = (char) stackOper.pop();
            if (oper == '(') {
                break;
            } else {
                output.append(oper);
                output.append(" ");
            }
        }
    }

    //--------------------------------------------------------
    private void gotOperation(char current, int prior1) {   // обработка операций и их приоритетов
        while (!stackOper.isEmpty()) {
            char opTop = (char) stackOper.pop();
            if (opTop == '(') {
                stackOper.push(opTop);
                break;
            }

            int prior2;
            if (opTop == '+' || opTop == '-') {
                prior2 = 1;
            } else {
                prior2 = 2;
            }

            if (prior2 < prior1) {
                stackOper.push(opTop);
                break;
            } else {  
                output.append(opTop);
                output.append(" ");
            }
        }
        stackOper.push(current);
    }
    
    //--------------------------------------------------------
    private boolean isDigit(char opThis) {
        return (opThis >= '0' && opThis <= '9');
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

    //--------------------------------------------------------
    private boolean isDelimetr(char opThis) {
        return (opThis == ' ');
    }

    //--------------------------------------------------------
    private boolean isEndStr(char opThis) {
        return (opThis == '\0');
    }

    //--------------------------------------------------------
    private boolean isOperator(char opThis) {
        return (OPERATORS.indexOf(opThis) != -1);
    }
}
