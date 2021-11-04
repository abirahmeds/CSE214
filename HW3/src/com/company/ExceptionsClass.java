package com.company;

/**
 * This is the Exceptions class
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #3
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

class FullStackException extends Exception {
        public FullStackException(){
            super();
        }
        public FullStackException(String s) {
            super(s);
        }
}

class EmptyStackException extends Exception {
    public EmptyStackException(){
        super();
    }
    public EmptyStackException(String s) {
        super(s);
    }
}




