package com.company;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This is the PackageStack class which represents the stacks of packages that
 * are being delivered, allowing the packages to be organized and moved around
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #3
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class PackageStack{
    //Member variables
    private Stack<Package> stack;
    private final int CAPACITY = 7;
    private int stackSize;


    /**
     * This is a default constructor which initializes this object to an empty
     * stack of Packages
     *
     * <Post-condition>
     *      CarList initialized with head, tail and cursor set to null.
     */
    public PackageStack(){
        this.stack = new Stack<Package>();
        this.stackSize = 0;
        this.stack.setSize(CAPACITY);
    }

    /**
     * This pushes the Package p onto the top of the stack
     *
     * @throws FullStackException
     *      If the stack is at capacity
     *
     * @param p
     *      This is the Package object to push onto top of the stack
     */
    public void push(Package p) throws FullStackException {
        if (isFull()){
            throw new FullStackException("Stack is full.");
        }else {
            stack.push(p);
            stackSize++;
        }
    }

    /**
     * This removes the topmost Package of the stack
     *
     * @return
     *      Package at top of the stack
     *
     * @throws EmptyStackException
     *      If the stack is empty
     *
     */
    public Package pop() throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException();
        }else {
            stackSize--;
            return stack.pop();
        }
    }

    /**
     * This returns the topmost Package from the stack without removing it
     *
     * @return
     *      The topmost Package of the stack
     *
     * @throws EmptyStackException
     *      If the stack is empty
     *
     */
    public Package peek() throws EmptyStackException {
        if (stack.isEmpty()){
            throw new EmptyStackException();
        }else{
            return stack.peek();
        }
    }

    /**
     * This returns the number of packages in the stack
     *
     * @return
     *      int of the number of packages in the stack
     */
    public int getSize(){
        return stackSize;
    }

    /**
     * This removes all the packages of the stack
     */
    public void clear(){
        stack.clear();
        stackSize = 0;
    }

    /**
     * This returns the boolean value of whether the stack is empty or not
     *
     * @return
     *      true if stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return (stackSize == 0);
    }

    /**
     * This returns the boolean value of whether the stack is full or not
     *
     * @return
     *      true if stack is full, false otherwise
     */
    public boolean isFull(){
        return (stackSize > 6);
    }

    /**
     * This is the toString method for the PackageStack
     *
     * @return toString
     *      returns the printable representation of PackageStack
     *      and its data members
     */
    public String toString(){
        PackageStack temp = new PackageStack();
        String mailStack = "";
        if (stackSize == 0){
            mailStack += "empty.";
        }else{
            try {
                while (stack.peek() != null) {
                    temp.push(stack.pop());
                }
            }catch (FullStackException e) {
                    e.printStackTrace();
                }
            }
            try{
                while(temp.peek() != null){
                    mailStack += temp.peek().toString();
                    stack.push(temp.pop());
                }
            }catch (EmptyStackException e){
                e.getMessage();
            }
        return mailStack;

    }
}


