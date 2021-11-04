package com.company;
import java.util.ArrayList;

/**
 * This is the VirtualLine class which represents the VirtualLine the Persons
 * are on, which models a Queue
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class VirtualLine extends ExceptionsClass{
    //Member variables
    private ArrayList<Person> virtualLine;
    private int numPeople;

    /**
     * This is the default Constructor for the VirtualLine class
     */
    public VirtualLine(){
        virtualLine = new ArrayList<Person>();
        numPeople = 0;
    }

    /**
     * This enqueues the Person p into the VirtualLine
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
        virtualLine.add(p);
        numPeople++;
    }

    /**
     * This dequeues the Person p into the VirtualLine
     *
     * @throws com.company.ExceptionsClass.EmptyQueueException
     *      If the queue is empty
     *
     * @return
     *      The Person object being dequeued
     */
    public Person dequeue() throws EmptyQueueException{
        if (!isEmpty()){ throw new EmptyQueueException("This queue is empty.");}
        Person first = virtualLine.get(0);
        virtualLine.remove(0);
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
        if (virtualLine.isEmpty()){
            throw new EmptyQueueException("This queue is empty.");
        }else{
            return virtualLine.get(0);
        }
    }

    /**
     * This returns the boolean value of whether the queue is empty or not
     *
     * @return
     *      true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return virtualLine.isEmpty();
    }

    /**
     * This returns the number of People in the queue
     *
     * @return
     *      int of the number of Persons in the line
     */
    public int getNumPeople(){
        return getNumPeople();
    }

    /**
     * This is the toString method for the VirtualLine
     *
     * @return toString
     *      returns the printable representation of VirtualLine and its data
     *      members
     */
    public void toStr() throws EmptyQueueException, FullQueueException {
        System.out.println("\nVirtual Queue: ");
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

