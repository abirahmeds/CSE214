package com.company;
import java.util.ArrayList;

/**
 * This is the Ride class which represents the rides that are there
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class Ride {
    //Member variables
    private String Name;
    private int duration;
    private int timeLeft;
    private int capacity;
    private VirtualLine virtualLine;
    private HoldingQueue holdingQueue;
    private ArrayList<Person> peopleOnRide;
    private int ponRide;
    private int ridesCompleted;
    private boolean rideFinished = true;


    /**
     * This is the default Constructor for the Ride class
     *
     * @param Name
     *      Name of the ride
     *
     * @param duration
     *      the duration of the ride
     *
     * @param capacity
     *      the capacity of how many people can be on the ride
     *
     * @param hqsize
     *      the capacity of the holding queue
     */
    public Ride(String Name, int duration, int capacity, int hqsize){
        this.Name = Name;
        this.duration = duration;
        this.timeLeft = duration;
        this.capacity = capacity;
        virtualLine = new VirtualLine();
        holdingQueue = new HoldingQueue(hqsize);
        peopleOnRide = new ArrayList<Person>(capacity);
        ponRide = 0;
        ridesCompleted = 0;
    }

    /**
     * These next four are getter methods
     *
     * @return getName
     *      the name of the Ride
     *
     * @return getDuration
     *      the duration of the Ride
     *
     * @return getTimeLeft
     *      the time left on the Ride
     *
     * @return getCapacity
     *       the capacity of the Ride
     */
    public String getName() {
        return Name;
    }

    public int getDuration(){
        return duration;
    }

    public int getTimeLeft(){
        return timeLeft;
    }

    public int getCapacity() { return capacity; }

    /**
     * These next four are setter methods
     *
     * @return setName
     *      set the name of the Ride
     *
     * @return setDuration
     *      set duration of the Ride
     *
     * @return setTimeLeft
     *      sets the time left on the ride
     *
     * @return setCapacity
     *      set the capacity of the ride
     *
     */
    public void setName(String Name){ this.Name = Name; }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }

    public void setCapacity(int capacity) { this.capacity = capacity; }


    /**
     * This adds the person to the virtual line of the ride
     *
     * @throws IllegalArgumentException
     *      If the Person is null
     *
     * @param p
     *      The Person object for which to add
     *
     */
    public void add(Person p) throws ExceptionsClass.FullQueueException {
        if (virtualLine.isEmpty() && p.getStatus() == Status.Available) {
            if (holdingQueue.isEmpty() && rideFinished) {
                while(capacity > ponRide) {
                    p.setStatus(Status.OnRide);
                    peopleOnRide.add(p);
                    ponRide++;
                }
            }else{
                holdingQueue.enqueue(p);
                p.setStatus(Status.Holding);
            }
        }else{
            virtualLine.enqueue(p);
        }
    }

    /**
     * This simulates the ride for every minute that passes
     */
    public void simulate() throws ExceptionsClass.EmptyQueueException,
            ExceptionsClass.FullQueueException {
        if (duration == 0) {
            System.out.println("NO SIMULATION");
            return;
        }
        if (timeLeft == 0) {
            timeLeft = duration;
            rideFinished = true;
            ridesCompleted++;
            peopleOnRide.clear(); //ADD THESE PEOPLE BACK ONTO OTHER RIDES****
            ponRide = 0;
            if (holdingQueue.isEmpty()) {
                return;
            } else {
                for (int i = 0; i < capacity; i++) {
                    Person p = holdingQueue.dequeue();
                    p.setStatus(Status.OnRide);
                    peopleOnRide.add(p);
                    ponRide++;
                }
            }
            if (virtualLine.isEmpty()){
                return;
            }else {
                for (int i = 0; i < holdingQueue.getMaxSize(); i++) {
                    Person p = virtualLine.dequeue();
                    p.setStatus(Status.Holding);
                    holdingQueue.enqueue(p);
                }
            }
        }else{
            rideFinished = false;
        }
        toString();
        timeLeft--;
    }

    public void toStr() throws ExceptionsClass.EmptyQueueException, ExceptionsClass.FullQueueException {
        System.out.println(this.getName() + " - Time remaining: " + this.getTimeLeft() + " min");
        System.out.println("On Ride: ");
        for(int i = 0; i < ponRide; i++){
            System.out.print(peopleOnRide.get(i).toString() + ", ");
        }
        holdingQueue.toStr();
        virtualLine.toStr();
    }

    public void toStrr() throws ExceptionsClass.EmptyQueueException,
            ExceptionsClass.FullQueueException {
        System.out.println(this.getName() + " - Time remaining: " + this.getTimeLeft() + " min");
        System.out.println("On Ride: ");
        for(int i = 0; i < ponRide; i++){
            System.out.print(peopleOnRide.get(i).toString() + ", ");
        }
    }
}
