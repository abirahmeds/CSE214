package com.company;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *This is the driver class which runs the simulation
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #4
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class SevenFlags {
    public static void main(String[] args) throws
            IllegalArgumentException, ExceptionsClass.EmptyQueueException {
        Scanner sc = new Scanner(System.in);
        Ride[] rides = new Ride[4];
        int time = 0;

        try {
            //Enter the information needed ton start the simulation
            System.out.println("Welcome to Seven Flags!");

            System.out.print("\nPlease enter the number of regular customers:" +
                    " ");
            int regular = sc.nextInt();
            System.out.print("Please enter the number of silver customers: ");
            int silver = sc.nextInt();
            System.out.print("Please enter the number of gold customers: ");
            int gold = sc.nextInt();
            System.out.print("Please enter simulation length: ");
            int length = sc.nextInt();

            System.out.print("\nPlease enter the duration of Blue Scream of " +
                    "Death (minutes): ");
            int duration = sc.nextInt();
            System.out.print("Please enter the capacity of Blue Scream of Death: ");
            int capacity = sc.nextInt();
            System.out.print("Please enter the holding queue size for Blue Scream of Death: ");
            int hqsize = sc.nextInt();
            rides[0] = new Ride("Blue Scream of Death", duration, capacity, hqsize);

            System.out.print("\nPlease enter the duration of Kingda Knuth " +
                    "(minutes): ");
            duration = sc.nextInt();
            System.out.print("Please enter the capacity of Kingda Knuth: ");
            capacity = sc.nextInt();
            System.out.print("Please enter the holding queue size for Kingda Knuth: ");
            hqsize = sc.nextInt();
            rides[1] = new Ride("Kingda Knuth", duration, capacity, hqsize);

            System.out.print("\nPlease enter the duration of i386 Tower of " +
                    "Terror (minutes): ");
            duration = sc.nextInt();
            System.out.print("Please enter the capacity of i386 Tower of " +
                    "Terror: ");
            capacity = sc.nextInt();
            System.out.print("Please enter the holding queue size for i386 " +
                    "Tower of Terror: ");
            hqsize = sc.nextInt();
            rides[2] = new Ride("i386 Tower of Terror", duration, capacity, hqsize);

            System.out.print("\nPlease enter the duration of GeForce " +
                    "(minutes): ");
            duration = sc.nextInt();
            System.out.print("Please enter the capacity of GeForce: ");
            capacity = sc.nextInt();
            System.out.print("Please enter the holding queue size for " +
                    "GeForce: ");
            hqsize = sc.nextInt();
            rides[3] = new Ride("GeForce", duration, capacity, hqsize);
            mprint();

            ArrayList<Person> customerLine = customerLine(gold, silver, regular);
            assignRides(customerLine, rides);

            while (length >= time) {
                mprint();
                System.out.println("At Time " + time + ":\n");
                for(int i = 0; i < 4; i++){
                    rides[i].toStrr();
                    rides[i].simulate();
                    nprint();
                }
                time++;
            }
        } catch (InputMismatchException | ExceptionsClass.FullQueueException e) {
            e.getMessage();
        }
    }


    /**
     * This creates an array of the customers of each plan and adds them to a
     * ride based on the ride chosen from Random Generator
     *
     * @return customers
     *      returns the array of Person objects after creating them and
     *      placing them into their max amount of rides
     *
     */
    public static ArrayList<Person> customerLine(int gold, int silver, int regular)
            throws ExceptionsClass.FullQueueException {
        ArrayList<Person> customers =
                new ArrayList<Person>();
        int num = 1;
        int total = gold + silver + regular;
        while(total > 0) {
            if (gold > 0) {
                customers.add(new Person(num, "Gold"));
                gold--;
                total--;
            }
            if (silver > 0) {
                customers.add(new Person(num, "Silver"));
                silver--;
                total--;
            }

            if (regular > 0) {
                customers.add(new Person(num, "Regular"));
                regular--;
                total--;
            }
            num++;
        }
        return customers;
    }

    public static void assignRides(ArrayList<Person> customers, Ride[] rides)
            throws ExceptionsClass.FullQueueException {
        int num = 1;
        while (num < 4) {
            for (Person customer : customers) {
                if (customer.getMaxLines() == num) {
                    Ride ride = RandomGenerator.selectRide(rides);
                    ride.add(customer);
                    customer.setLines(ride);
                }
            }
            num++;
        }
    }

    public static void mprint(){
        System.out.println(
                "\n-------------------------------------------------" +
                "-----------------------------------------");
    }

    public static void nprint(){
        System.out.println("\n----------------");
    }
}
