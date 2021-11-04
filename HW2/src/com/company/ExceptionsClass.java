package com.company;
/**
 * This is the Exceptions class
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #2
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class ExceptionsClass extends Exception{
    class EndOfListException extends Exception{
        public EndOfListException(String s){
            super("The cursor is at the end of the list and cannot be moved " +
                    "in this direction any further");
        }
    }
}
