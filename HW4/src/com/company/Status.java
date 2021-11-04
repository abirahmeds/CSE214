package com.company;
/**
 * This is an Enum the status of the the Persons
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */
public enum Status {
    Available("Available"), Holding("Holding"),
    OnRide("OnRide");

    /**
     * This is a constructor used to set the status of the Person
     *
     * @param status the status of the Person
     */
    String status;
    Status(String status){
        this.status = status;
    }

}
