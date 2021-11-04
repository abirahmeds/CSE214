package com.company;
/**
 * The Car class inherits from CarList and creates a Car object
 * using the make of the car and owner name
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #1
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */
public class Car {
    //Member variables
    private Make make;
    private String owner;

    /**
     * This is a Constructor used to create a new Car object
     *
     * @param make  the make of the car
     * @param owner the name of the owner of the car
     */
    public Car(Make make, String owner) {
        this.make = make;
        this.owner = owner;
    }

    /**
     * These next two are getter methods
     *
     * @return getMake
     *      the make of the car
     *
     * @return getOwner
     *      the name of the owner
     */
    public Make getMake() {
        return make;
    }
    public String getOwner() {
        return owner;
    }

    /**
     * These next two are setter methods
     *
     * @return setMake
     *      set the make of the car
     *
     * @return setOwner
     *      set the name of the owner
     */
    public void setMake(Make make) {
        this.make = make;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
