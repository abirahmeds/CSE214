package com.company;
import java.util.Scanner;
/**
 * The OilChangeManager creates three instances of the CarList class to
 * provide Joe, Donny, and the Finished Jobs lists. This allows the user to
 * manipulate the lists from the following functions. Provides the menu
 * driven application and
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #2
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */

public class OilChangeManager extends CarList {
    public static void main(String[] args) throws EndOfListException,
            IllegalArgumentException {
        //These are the three instances of the CarList class for Joe, Donny,
        // and Finished List
        CarList j = new CarList();
        CarList d = new CarList();
        CarList finished = new CarList();
        boolean list; //If the list is Joe's list, it is true, false otherwise
        Scanner sc = new Scanner(System.in);
        String userResp = "";
        Car copy = null;
        Make make;
        String name = "";
        Car add;

        System.out.println("Welcome to Tony's Discount Oil Change Computer " +
                "Management System! It's way better than a rolodex, " +
                "cork board, post-its, and pre-chewed bubblegum!");

        while (!userResp.equals("q")) {
            System.out.println(
                    "Menu:\n"
                            + "L) Edit Job Lists for Joe and Donny\n"
                            + "M) Merge Job Lists\n"
                            + "P) Print Job Lists\n"
                            + "F) Paste car to end of finished car list\n"
                            + "S) Sort Job Lists\n"
                            + "Q) Quit\n"
                            + "\nPlease select an option: ");
            userResp = sc.nextLine().toLowerCase();
            switch (userResp) {
                case "l":
                    while(true) {
                        System.out.println("Please select a list - Joe (J) or "
                                + "Donny (D): ");
                        userResp = sc.nextLine().toLowerCase();
                        if ((userResp.equals("j") || (userResp.equals("d")))) {
                            list = (userResp.equals("j"));
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("\nMenu:\n" +
                                "A) Add a car to the end of the list\n"
                                + "F) Cursor Forward\n"
                                + "H) Cursor to Head\n"
                                + "T) Cursor to Tail\n"
                                + "B) Cursor Backward\n"
                                + "I) Insert car before cursor\n"
                                + "X) Cut car at cursor\n"
                                + "V) Paste before cursor\n"
                                + "R) Remove cursor\n"
                                + "Please select an option: ");
                            userResp = sc.nextLine().toLowerCase();
                            switch (userResp) {
                                case "a":
                                    System.out.println("Please enter vehicle " +
                                            "make (Ford, GMC, "
                                            + "Chevy, Jeep, Dodge, Chrysler, " +
                                            "and Lincoln): ");
                                    userResp = sc.nextLine().toUpperCase();
                                    if(!Make.isValid(userResp)) {
                                        System.out.println("We don't service " +
                                        userResp + "!");
                                        break;
                                    }else {
                                        make = Make.valueOf(userResp);
                                        System.out.println("Please enter " +
                                                "owner's name: ");
                                        name = sc.nextLine();
                                        add = new Car(make, name);
                                        ((list) ? j : d).appendToTail(add);
                                        System.out.println(name + "'s " +
                                                make + " has been " +
                                                "scheduled for an oil change "
                                                + "with " +
                                                ((list) ? "Joe" : "Donny") +
                                                " and has been added to his "
                                                + "list.");
                                    }
                                    break;
                                case "f":
                                    try {
                                        ((list) ? j : d).cursorForward();
                                        System.out.println("Cursor Moved " +
                                                "Forward " +
                                                "in " + ((list) ? "Joe" :
                                                "Donny") + "'s List.");
                                    }catch (EndOfListException s){
                                        s.printStackTrace();
                                    }
                                    break;
                                case "h":
                                    ((list) ? j : d).resetCursorToHead();
                                    System.out.println("Cursor Moved To Head in"
                                            + ((list) ? " Joe" : " Donny")
                                            + "'s List.");
                                    break;
                                case "t":
                                    ((list) ? j : d).resetCursorToTail();
                                    System.out.println("Cursor Moved To Tail in"
                                            +
                                            ((list) ? " Joe" : " Donny") +
                                            "'s List.");
                                    break;
                                case "b":
                                    try {
                                        ((list) ? j : d).cursorBackward();
                                        System.out.println("Cursor Moved " +
                                                "Backward in " +
                                                ((list) ? "Joe" : "Donny") +
                                                "'s List.");
                                    } catch (EndOfListException s) {
                                        s.printStackTrace();
                                    }
                                    break;
                                case "i":
                                    System.out.println("Please enter vehicle " +
                                            "make (Ford, GMC, "
                                            + "Chevy, Jeep, Dodge, Chrysler, " +
                                            "and Lincoln): ");
                                    userResp = sc.nextLine().toUpperCase();
                                    if(!Make.isValid(userResp)) {
                                        System.out.println("We don't service " +
                                                userResp + "!");
                                        break;
                                    }else {
                                        make = Make.valueOf(userResp);
                                        System.out.println("Please enter " +
                                                "owner's name: ");
                                        name = sc.nextLine();
                                        add = new Car(make, name);
                                        ((list) ? j : d).insertBeforeCursor(add);
                                        System.out.println(name + "'s " +
                                                make + " has been " +
                                                "scheduled for an oil change "
                                                + "with " +
                                                ((list) ? "Joe" : "Donny") +
                                                " and has been added to his "
                                                + "list before the cursor.");
                                    }
                                    break;
                                case "x":
                                    if(((list) ? j : d).length != 0) {
                                        copy = ((list) ? j : d).getCursorCar();
                                        ((list) ? j : d).removeCursor();
                                        System.out.println("Cursor cut in " +
                                                ((list) ? "Joe's" :
                                                        "Donny's") + " List.");
                                    }else{
                                        System.out.println("There is no car" +
                                                " at the cursor to cut.");
                                    }
                                    break;
                                case "v":
                                    if (copy != null) {
                                        ((list) ? j : d).insertBeforeCursor(copy);
                                        System.out.println("Cursor pasted in " +
                                                ((list) ?
                                                "Joe's" : "Donny's") + " List" +
                                                " before the cursor.");
                                        copy = null;
                                    } else {
                                        System.out.print("Nothing to paste.\n");
                                    }
                                    break;
                                case "r":
                                    if (((list) ? j : d).length >= 1) {
                                        try {
                                            ((list) ? j : d).removeCursor();
                                            System.out.println("Cursor removed "
                                                    + "in " + ((list) ? "Joe" +
                                                    "'s" : "Donny's") + " " +
                                                    "List");
                                        } catch (EndOfListException s) {
                                            s.printStackTrace();
                                        }
                                    }else{
                                        System.out.println("There is no car" +
                                                " at the cursor to remove.");
                                    }
                                    break;
                                case "q":
                                    System.out.println("Enjoy your retirement!");
                                    System.exit(0);
                                default:
                                    System.out.println("This is not an option" +
                                            ".");
                            }
                            break;
                    }
                    break;
                case "m":
                    while(true) {
                        System.out.println("Please select a destination list - "
                                + "Joe (J) or Donny (D): ");
                        userResp = sc.nextLine().toLowerCase();
                        list = (userResp.equals("j"));
                        if ((userResp.equals("j") || (userResp.equals("d")))) {
                            for (int i = 0; i <= ((list) ? d : j).numCars();
                                 i++) {
                                ((list) ? d : j).resetCursorToHead();
                                ((list) ? j : d).appendToTail
                                        (((list) ? d : j).getCursorCar());
                                ((list) ? d : j).removeCursor();
                            }
                            System.out.println(((list) ? "Donny's" : "Joe's") +
                                    " list merged into " + ((list) ? "Joe's."
                                    : "Donny's."));
                            break;
                        } else System.out.println("This is not an option.");
                    }
                    break;
                case "p":
                    System.out.println("Joe's List:");
                    System.out.println(j.toString());
                    System.out.println("Donny's List:");
                    System.out.println(d.toString());
                    System.out.println("Finished List:");
                    System.out.println(finished.toStringFinished());
                    break;
                case "f":
                    if (copy != null) {
                        finished.appendToTail(copy);
                        System.out.println("Car pasted in finished list");
                    }else{
                        System.out.println("There is no car to paste.");
                    }
                    break;
                case "s":
                    j.sort();
                    d.sort();
                    System.out.println("Joe's list and Donny's list have been" +
                            " sorted.");
                    break;
                case "q":
                    System.out.println("Enjoy your retirement!");
                    System.exit(0);
                default:
                    System.out.println("This is not an option. Please try " +
                            "again");
            }
        }
    }
}