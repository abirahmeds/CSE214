package com.company;
/**
 * This is the Exceptions class
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class ExceptionsClass extends Exception{
    class FullQueueException extends Exception{
        public FullQueueException(String s){
            super("This queue is full");
        }
    }

    class EmptyQueueException extends Exception{
        public EmptyQueueException(String s){
            super("This queue is empty");
        }
    }
}
