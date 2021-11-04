package com.company;
/**
 * The CarListNode class wraps a Car object to allow it to be inserted into
 * the doubly linked-list data structure
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #2
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */
public class CarListNode {
    private Car data;
    private CarListNode next;
    private CarListNode prev;

    /**
     * This is a Default Constructor that wraps the car to allow it into the
     * doubly linked-list
     *
     *  <Pre-condition>
     *          initData is not null
     *
     *  <Post-condition>
     *          CarListNode has been initialized to wrap the indicated car and
     *          prev and next have been set to null
     *
     * @throws IllegalArgumentException
     *      if initData is null
     */
    public CarListNode(Car initData) throws IllegalArgumentException{
        super();
        this.next = null;
        this.prev = null;
        this.data = initData;
        if (initData == null) throw new IllegalArgumentException();
    }

    /**
     * These next two are getter methods
     *
     * @return getData
     *      the current CarListNode
     *
     * @return getNext , getPrev
     *      the next and previous CarListNode objects
     */
    public Car getData() {
        return this.data;
    }
    public CarListNode getNext() {
        return this.next;
    }
    public CarListNode getPrev() {
        return this.prev;
    }

    /**
     * These next two are setter methods
     *
     * @return getData
     *      sets the current CarListNode
     *
     * @return getNext , getPrev
     *      sets the next and previous CarListNode objects
     */
    public void setData(Car data){
        this.data = data;
    }
    public void setNext(CarListNode next) {
        this.next = next;
    }
    public void setPrev(CarListNode prev) {
        this.prev = prev;
    }
}
