package com.company;
/**
 * This is an Enum that contains the makes of the car
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #2
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 *
 */
public enum Make {
    FORD("FORD"), GMC("GMC"),
    CHEVY("CHEVY"), JEEP("JEEP"), DODGE("DODGE"),
    CHRYSLER("CHRYSLER"), LINCOLN("LINCOLN");

    /**
     * This is a constructor used to make the Make object
     *
     * @param makeName the make of the car
     */
    String makeName;
    Make(String makeName){
        this.makeName = makeName;
    }

    /**
     * This is a method to check whether the car make the user has entered
     * matches the constants in the enum that are valid to be serviced
     *
     * @return isValid
     *      true/false if the userResp is a valid enum Constant for car make
     */
    static public boolean isValid(String userResp) {
        Make[] makes = Make.values();
        for (Make make : makes)
            if (make.makeName.equals(userResp))
                return true;
        return false;
    }
}
