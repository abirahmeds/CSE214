package com.company;
/**
 * This is the Package class which represents the mail packages that are being
 * delivered and picked up from the Irving Mailroom
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #3
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */
public class Package {
    //Member variables
    public String recipient;
    private int arrivalDate;
    private double weight;

    /**
     * This is a Constructor used to create a new Package object
     *
     * @param recipient the recipient of the package
     * @param arrivalDate the day that the package arrived
     * @param weight the weight of the package
     */
    public Package(String recipient, int arrivalDate, double weight){
        this.recipient = recipient;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }

    /**
     * These next three are getter methods
     *
     * @return getRecipient
     *      the recipient of the package
     *
     * @return getArrivalDate
     *      the day the package arrived on
     *
     * @return getWeight
     *      the weight of the package
     */
    public String getRecipient() {
        return recipient;
    }

    public int getArrivalDate() {
        return arrivalDate;
    }

    public double getWeight() {
        return weight;
    }


    /**
     * These next two are setter methods
     *
     * @return setRecipient
     *      set the recipient of the package
     *
     * @return setArrivalDate
     *      set the arrival date of the package
     *
     * @return setWeight
     *      sets the weight of the package
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setArrivalDate(int arrivalDate) { this.arrivalDate = arrivalDate; }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * This is the toString method for the Package object
     *
     * @return toString
     *      returns the printable representation of a package
     */
    public String toString(){
        return String.format("[%-5s%5s]  ", this.getRecipient(),
                this.getArrivalDate());
    }
}
