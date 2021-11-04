package com.company;
import java.util.ArrayList;

/**
 * This is the Person class which represents the Person objects that are
 * attending the amusement park
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class Person {
    //Member variables
    private int number; //The number assigned to the person in order
    private int maxLines; //The maximum number of lines the Person can be on
    private ArrayList<Ride> lines; //Holds an instance of each ride the person
    private Status status; //The status of the Person
    private static String plan; //The plan of person (Regular, Silver, Gold)
    private int numRides = 0;

    /**
     * This is the default Constructor for the Person class
     *
     * <Pre-conditions>
     *     int number must be positive
     *
     * @throws IllegalArgumentException
     *      If int number is not positive
     *
     * @param number
     *      integer id
     *
     * @param plan
     *      the plan the person is on
     */
    public Person(int number, String plan) throws IllegalArgumentException{
        if (number < 0) throw new IllegalArgumentException("This is not a " +
                "positive value");
        this.number = number;
        this.status = Status.Available;
        this.setPlan(plan);
        if(plan.equalsIgnoreCase("gold")) {
            this.setMaxLines(3);
        }else if (plan.equalsIgnoreCase("silver")){
            this.setMaxLines(2);
        }else this.setMaxLines(1);
        lines = new ArrayList<Ride>(maxLines);
    }

    /**
     * These next five are getter methods
     *
     * @return getNumber
     *      the number of the Person
     *
     * @return getMaxLines
     *      the max number of lines the Person can be on
     *
     * @return getLines
     *      the instances of the lines the person is on
     *
     * @return getStatus
     *       the Status of the Person
     *
     * @return getPlan
     *      the plan the Person is on
     */
    public int getNumber() { return number; }

    public int getMaxLines(){ return maxLines; }

    public ArrayList<Ride> getLines() { return lines; }

    public Status getStatus(){ return status; }

    public String getPlan(){ return plan; }

    /**
     * These next five are setter methods
     *
     * @return setNumber
     *      set the number of the Person
     *
     * @return setMaxLines
     *      set the max number of lines the Person can be on
     *
     * @return setLines
     *      sets instances of the lines the person is on
     *
     * @return setStatus
     *      set the Status of the Person
     *
     * @return setPlan
     *     set the plan the Person is on
     *
     */
    public void setNumber(int number) { this.number = number; }

    public void setMaxLines(int maxLines) { this.maxLines = maxLines; }

    public void setLines(Ride ride) { lines.add(ride); }

    public void setStatus(Status status) { this.status = status; }

    public void setPlan(String plan){ this.plan = plan; }

    public void addRide(){
        numRides++;
    }

    /**
     * This is the toString method for the Person object
     *
     * @return toString
     *      returns the printable representation of a Person
     */
    public String toString(){
        return String.format("%-5s%5s", this.getPlan(), this.getNumber());
    }
}
