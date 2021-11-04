package com.company;
/**
 * The CarList implements a double-linked list data structure and maintains
 * the series between the head and tail reference
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #2
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class CarList extends ExceptionsClass{
    /**
     * These are the member variables of the CarList class
     */
    private Car data;
    private CarListNode head;
    private CarListNode tail;
    private CarListNode cursor;
    int length;

    /**
     * This is a default constructor which initializes this object to an empty
     * list of Cars
     *
     * <Post-condition>
     *      CarList initialized with head, tail and cursor set to null.
     */
    public CarList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.length = 0;
    }

    /**
     * This returns the total number of Cars in the list
     *
     * @return
     *      int number of Cars in this list
     */
    public int numCars() {
        return length;
    }

    /**
     * This returns the reference to the Car wrapped by the CarListNode
     * currently
     *
     * @return
     *      Car reference wrapped by the CarListNode currently referenced by
     *      the cursor
     */
    public Car getCursorCar() {
        return cursor.getData();
    }

    /**
     * This sets the cursor to the head, the start of the list. If head is
     * null, then there are no Car objects in the List and cursor is set to null
     */
    public void resetCursorToHead() {
        if (head != null){ cursor = head;
        }else{ cursor = null; }
    }

    /**
     * This sets the cursor to the tail, the end of the list. If tail is null,
     * then there are no Car objects in the List and cursor is set to null
     */
    public void resetCursorToTail() {
        if (tail != null){ cursor = tail;
        }else{ cursor = null; }
    }

    /**
     * This sets the cursor to the next CarListNode in the list
     *
     * @throws EndOfListException
     *      If cursor is at tail, throws exception
     */
    public void cursorForward() throws EndOfListException {
        if (length == 0 || cursor.getNext() == null) {
            throw new EndOfListException("You are at the end of the list. " +
                    "Cursor cannot be moved any further forward.");
        }else{ cursor = cursor.getNext(); }
    }

    /**
     * This sets the cursor to the previous CarListNode in the list
     *
     * @throws EndOfListException
     *      If cursor is at the head of the tail, throws exception
     */
    public void cursorBackward() throws EndOfListException {
        if (length == 0 || cursor.getPrev() == null) {
            throw new EndOfListException("You are at the head of the list. " +
                    "Cursor cannot be moved backwards.");
        }else{ cursor = cursor.getPrev(); }
    }

    /**
     * This inserts the indicated Car before the cursor
     *
     *<Pre-conditions>
     *      newCar is not null
     *
     *<Post-conditions>
     *      newCar has been wrapped in a new CarListNode object to be
     *      inserted into CarList
     *      If cursor was previously not null, the new CarListNode object is
     *      inserted before the cursor
     *      If the cursor was previously null, the new CarListNode object has
     *      been set as the new head, tail, and cursor.
     *
     * @throws IllegalArgumentException
     *      If newCar is null
     *
     * @param newCar
     *      This is the Car object to insert before the cursor
     */
    public void insertBeforeCursor(Car newCar) throws IllegalArgumentException {
        if (newCar == null) {
            throw new IllegalArgumentException("Invalid argument");
        } else {
            CarListNode newNode = new CarListNode(newCar);
            if (cursor == null){
                cursor = newNode;
                head = newNode;
                tail = newNode;
            }
            else if (cursor.getPrev() == null){
                newNode.setNext(cursor);
                cursor.setPrev(newNode);
                head = newNode;
            } else {
                newNode.setNext(cursor);
                cursor.getPrev().setNext(newNode);
                newNode.setPrev(cursor.getPrev());
                cursor.setPrev(newNode);
            }
            length++;
        }
    }



    /**
     * This inserts the Car newCar after the tail of the list
     *
     * <Pre-conditions>
     *      newCar is not null
     *
     *<Post-conditions>
     *      newCar has been wrapped in a new CarListNode object to be
     *      inserted into CarList
     *      If tail was previously not null, the new CarListNode object is
     *      inserted after the tail
     *      If the cursor was previously null, the new CarListNode object has
     *      been set as the new head, tail, and cursor.
     *      The tail is now the new CarListNode newNode
     * @throws IllegalArgumentException
     *        If newCar is null
     *
     * @param newCar
     *      This is the Car object to append to the tail
     */
    public void appendToTail(Car newCar) throws IllegalArgumentException {
        if(newCar == null) {
            throw new IllegalArgumentException("Invalid argument");
        }else {
            CarListNode newNode = new CarListNode(newCar);
            if (tail == null && head == null) {
                head = newNode;
                tail = newNode;
                cursor = newNode;
            } else {
                newNode.setPrev(tail);
                tail.setNext(newNode);
                tail = newNode;
            }
            length++;
        }
    }

    /**
     * This removes the CarListNode reference by the cursor
     *
     * <Pre-condition>
     *     cursor is not null
     *
     * <Post-condition>
     *     CarListNode is referenced by cursor and returns the Car inside
     *     All other CarListNodes in the list exist in the same Car as before
     *     The cursor now references previous CarListNode or the head if the
     *     cursor was previously referencing the head of the list
     *
     * @return
     *      The Car object inside after removing cursor reference
     *
     * @throws EndOfListException
     *      If cursor is null, EndOfListException is thrown
     */
    public Car removeCursor() throws EndOfListException {
        CarListNode cursorRef = cursor;
        if (cursor == null) throw new EndOfListException("There is no car at " +
                "the cursor to remove. Cursor is null.");
        else if (cursor == head && cursor == tail){
            cursor = null;
            head = null;
            tail = null;
            length = 0;
        }
        else if (cursor != head && cursor != tail) {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getPrev();
            cursorRef = cursor;
            length--;
        } else if (cursor == head){
            head = head.getNext();
            cursor = head;
            cursorRef = cursor;
            cursor.setPrev(null);
            length--;
        }else if (cursor == tail) {
            tail = tail.getPrev();
            cursor = tail;
            cursorRef = cursor;
            cursor.setNext(null);
            length--;
        }
        return cursorRef.getData();
    }

    /**
     * This sorts the CarList list by car Make and leaves it in the order of
     * whichever car arrived first
     *
     * <Pre-condition>
     *     head is not null
     *
     * <Post-condition>
     *     All the jobs in the jobs lists are sorted by car make
     */
    public void sort() {
        Car trade;
        CarListNode ref = null, i = null;
        if (head == null) return;
        else{
            for(ref = head; ref.getNext() != null; ref = ref.getNext()){
                for(i = ref.getNext(); i != null; i = i.getNext()){
                    if(ref.getData().getMake().toString().
                            compareTo(i.getData().getMake().toString()) > 0){
                        trade = ref.getData();
                        ref.setData(i.getData());
                        i.setData(trade);
                    }
                }
            }
        }
    }

    /**
     * This is the toString method for the job Lists and toStringFinished is
     * toString method for the the finished lists
     *
     * @return toString
     *      returns the printable representation of CarList
     *      and its data members
     *
     * @return toStringFinished
     *            returns the printable representation of CarList
     *            and its data members for the Finished List without a cursor
     */
    public String toString() {
        CarListNode current = head;
        String jobList = "";
        jobList += "Make\t\t\tOwner\n";
        jobList += "---------------------------------\n";
        if (current == null){
            jobList += "[empty]\n";
        }
        while (current != null) {
            for (int i = 0; i < length; i++) {
                Car car = current.getData();
                if (current == cursor) {
                    jobList += String.format("%-14s   %-10s",
                            "->" + car.getMake(),
                            car.getOwner());
                }
                else {
                    jobList += String.format("%-14s   %-10s", car.getMake(),
                            car.getOwner());
                }
                jobList += "\n";
                current = current.getNext();
            }
        }
        return jobList;
    }

    public String toStringFinished() {
        CarListNode current = head;
        String jobList = "";
        jobList += "Make\t\t\tOwner\n";
        jobList += "---------------------------------\n";
        if (current == null){
            jobList += "[empty]\n";
        }
        while (current != null) {
            for (int i = 0; i < length; i++) {
                Car car = current.getData();
                jobList += String.format("%-14s   %-10s", car.getMake(),
                            car.getOwner());
                jobList += "\n";
                current = current.getNext();
            }
        }
        return jobList;
    }
}

