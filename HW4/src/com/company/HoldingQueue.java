package com.company;
import java.util.ArrayList;

/**
 * This is the HoldingQueue class which represents the HoldingQueue of the
 * Rides, inherited from VirtualLine but with a maxSize
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class HoldingQueue extends VirtualLine{
    //Member variables
    private ArrayList<Person> holdingQueue;
    private final int maxSize; //Max size of the holding queue
    private int numPeople;

    /**
     * This is the default Constructor for the HoldingQueue class
     */
    public HoldingQueue(int maxSize) {
        this.maxSize = maxSize;
        holdingQueue = new ArrayList<Person>(maxSize);
        numPeople = 0;
    }

    /**
     * This enqueues the Person p into the HoldingQueue
     *
     * @throws com.company.ExceptionsClass.FullQueueException
     *      If the queue is at capacity
     *
     * @param p
     *      This is the Person object to enqueue
     */
    public void enqueue(Person p) throws FullQueueException {
        if (p == null) {
            throw new IllegalArgumentException("This input is invalid.");
        }
        if (numPeople == maxSize) throw new FullQueueException("This queue is" +
                " full");
        holdingQueue.add(p);
        numPeople++;
    }


    /**
     * This dequeues the Person p into the HoldingQueue
     *
     * @throws com.company.ExceptionsClass.EmptyQueueException
     *      If the queue is empty
     *
     * @return
     *      The Person object being dequeued
     */
    public Person dequeue() throws EmptyQueueException{
        if (!isEmpty()){ throw new EmptyQueueException("This queue is empty.");}
        Person first = holdingQueue.get(0);
        holdingQueue.remove(0);
        numPeople--;
        return first;
    }


    /**
     * This returns the first Person from the queue without removing them
     *
     * @throws com.company.ExceptionsClass.EmptyQueueException
     *      If the queue is empty
     *
     * @return
     *      The first Person in the queue
     */
    public Person peek() throws EmptyQueueException{
        if (holdingQueue.isEmpty()){
            throw new EmptyQueueException("This queue is empty.");
        }else{
            return holdingQueue.get(0);
        }
    }

    /**
     * This returns the boolean value of whether the queue is empty or not
     *
     * @return
     *      true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return holdingQueue.isEmpty();
    }

    /**
     * This returns the max size of the queue
     *
     * @return
     *      maxSize, the max size of the queue
     */
    public int getMaxSize(){
        return maxSize;
    }

    public void toStr() throws EmptyQueueException, FullQueueException {
        System.out.println("\nHolding Queue: ");
        if (this.peek() != null) {
            VirtualLine temp = new VirtualLine();
            while (this.peek() != null) {
                temp.enqueue(this.dequeue());
            }
            while (temp.peek() != null) {
                temp.peek().toString();
                this.enqueue(temp.dequeue());
            }

        }
    }
}
