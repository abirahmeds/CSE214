package com.company;
/**
 * @author Abir Ahmed
 * ID 112751779
 * HW #5
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class ElementNotFoundException extends Exception{
    public ElementNotFoundException(String s){
        super("Element not in the tree");
    }
}
