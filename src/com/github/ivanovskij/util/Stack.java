/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.util;

/**
 *
 * @author IOAdmin
 */
public class Stack {
    
    private Entry top;

    public Stack() {
    }
    
    
    //--------------------------------------------------------
    public void push(Object value) {
        Entry newEntry = new Entry(value);
        newEntry.next = top;
        top = newEntry;
    }
    
    //--------------------------------------------------------
    public Object pop() {
        Object temp = top.data;
        top = top.next;
        return temp;
    }
    
    //--------------------------------------------------------
    public Object peek() {
        return top.data;
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (top == null);
    }

    //--------------------------------------------------------
    public void display(String msg) {
        System.out.print(msg + " | ");
        System.out.print("Stack (bottom-->top): ");
        Entry current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    private class Entry {
        
        private Object data;
        private Entry next;

        public Entry(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Entry{" + "data=" + data + '}';
        }
    }
}
