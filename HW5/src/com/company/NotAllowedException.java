package com.company;
/**
 * @author Abir Ahmed
 * ID 112751779
 * HW #5
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class NotAllowedException extends Exception{
    public NotAllowedException(String s){
        super("This function is not allowed here.");
    }
}
