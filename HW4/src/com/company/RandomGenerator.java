package com.company;
/**
 *This randomly selects a ride for the customer to be assigned to
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class RandomGenerator{
    public static Ride selectRide(Ride[] rides){
        return rides[(int)Math.random() * rides.length];
    }
}
